package com.example.luxtravel.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.example.luxtravel.R
import com.example.luxtravel.view.ui.login.UserActivity

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        findViewById<View>(R.id.startLabel).setOnClickListener { view ->
            startActivity(Intent(this, MapsActivity::class.java))
        }

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            startActivity(Intent(this, UserActivity::class.java))
        }
    }
}