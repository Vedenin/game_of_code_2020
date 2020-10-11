package com.example.luxtravel.view

import android.Manifest
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.luxtravel.R
import com.example.luxtravel.network.BusStop
import com.example.luxtravel.network.Change
import com.example.luxtravel.network.Controller
import com.example.luxtravel.utils.TTS
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationListener
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

/**
 * https://www.vogella.com/tutorials/Retrofit/article.html
 */
class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
    GoogleApiClient.OnConnectionFailedListener, LocationListener, GoogleMap.OnMarkerClickListener {

    private lateinit var mMap: GoogleMap
    var mLocationRequest: LocationRequest? = null
    var mGoogleApiClient: GoogleApiClient? = null
    var mLastLocation: Location? = null
    var mCurrLocationMarker: Marker? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        findViewById<View>(R.id.emergencyButton).setOnClickListener { view ->
            // todo: clean history markers
            mMap.clear()
            getButStopsPins()
            // getButStops("000200419024");
        }

        findViewById<View>(R.id.busStopsButton).setOnClickListener { view ->
            val phone = "+352 111 111 111"
            val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null))
            startActivity(intent)
        }
    }

    private fun getHistoryData() {
        getButHistoryPins()
    }

    private fun getButStops(id: String) {
        val controller = Controller()
        controller.getButStopTimer(object : BusStopCallback {
            override fun onReadyBusTop(list: List<Change>) {
                TTS(this@MapsActivity, list.toString())
                AlertDialog.Builder(this@MapsActivity)
                    .setTitle("Closest Schedule")
                    .setMessage(list.toString())
                    .setCancelable(false)
                    .setPositiveButton(
                        "ok"
                    ) { dialog, which ->
                        // Whatever...
                    }.show()
            }
        }, id)
    }

    fun getButStopsPins() {
        val controller = Controller()
        controller.start2(object : PinsCallback {
            override fun onReadyBusTop(list: List<BusStop>) {

                list.forEach {

                    val sydney = LatLng(it.lat.toDouble(), it.lon.toDouble())
                    val m = mMap.addMarker(
                        MarkerOptions()
                            .position(sydney)
                            .title(it.name)
                            .snippet(it.name)
                    )
                    m.tag = it.id

                }.takeIf { (0..100).random().equals(2) }
                getButStops(list[0].id)
            }
        })
    }

    fun getButHistoryPins() {
        val controller = Controller()
        controller.getHistoryData(object : HistoryCallback {
            override fun onReadyBusTop(list: List<BusStop>) {

                list.forEach {

                    val sydney = LatLng(it.lon.toDouble(), it.lat.toDouble())
                    val m = mMap.addMarker(
                        MarkerOptions()
                            .position(sydney)
                            .title(it.name)
                            .snippet(it.name)
                    )
                    //   m.tag = it.id

                }.takeIf { (0..100).random().equals(2) }
            }

        })
    }

    interface HistoryCallback {
        fun onReadyBusTop(list: List<BusStop>);
    }

    interface PinsCallback {
        fun onReadyBusTop(list: List<BusStop>);
    }

    interface BusStopCallback {
        fun onReadyBusTop(list: List<Change>);
    }

    override fun onMarkerClick(p0: Marker?): Boolean {
        return true
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.setOnMarkerClickListener {
            TTS(this, it.title)
            it.showInfoWindow()
            if (it.tag != null) {
                getButStops(it.tag.toString());
            }
            true
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
                == PackageManager.PERMISSION_GRANTED
            ) {
                buildGoogleApiClient();
                mMap.setMyLocationEnabled(true);
            } else {
                checkLocationPermission();
            }
        } else {
            buildGoogleApiClient();
            mMap.isMyLocationEnabled = true;
        }
        getHistoryData()
    }

    @Synchronized
    protected fun buildGoogleApiClient() {
        mGoogleApiClient = GoogleApiClient.Builder(this)
            .addConnectionCallbacks(this)
            .addOnConnectionFailedListener(this)
            .addApi(LocationServices.API)
            .build()
        mGoogleApiClient!!.connect()
    }

    override fun onConnected(p0: Bundle?) {
        mLocationRequest = LocationRequest()
        mLocationRequest!!.interval = 15000
        mLocationRequest!!.fastestInterval = 15000
        mLocationRequest!!.priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            == PackageManager.PERMISSION_GRANTED
        ) {
            LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient,
                mLocationRequest,
                this
            )
        }
    }

    override fun onConnectionSuspended(p0: Int) {
        Toast.makeText(this, "onConnectionSuspended", Toast.LENGTH_LONG).show()
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        Toast.makeText(this, "onConnectionFailed", Toast.LENGTH_LONG).show()
    }

    override fun onLocationChanged(location: Location) {
        val latLng = LatLng(location.latitude, location.longitude)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15F));
    }

    private val MY_PERMISSIONS_REQUEST_LOCATION = 99
    private fun checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED
        ) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            ) {
                AlertDialog.Builder(this)
                    .setTitle("Location Permission Needed")
                    .setMessage("This app needs the Location permission, please accept to use location functionality")
                    .setPositiveButton(
                        "OK"
                    ) { _, _ -> //Prompt the user once explanation has been shown
                        ActivityCompat.requestPermissions(
                            this@MapsActivity,
                            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                            MY_PERMISSIONS_REQUEST_LOCATION
                        )
                    }
                    .create()
                    .show()
            } else {
                ActivityCompat.requestPermissions(
                    this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    MY_PERMISSIONS_REQUEST_LOCATION
                )
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_LOCATION -> {
                if (grantResults.size > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
                ) {
                    if (ContextCompat.checkSelfPermission(
                            this,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {
                        if (mGoogleApiClient == null) {
                            buildGoogleApiClient()
                        }
                        mMap.setMyLocationEnabled(true)
                    }
                } else {
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show()
                }
                return
            }
        }
    }

}