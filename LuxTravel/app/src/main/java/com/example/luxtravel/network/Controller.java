package com.example.luxtravel.network;

import android.util.Log;

import com.example.luxtravel.view.MapsActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Controller  {

    //static final String BASE_URL = "https://git.eclipse.org/r/";
    static final String BASE_URL = "http://163a9f4e4073.ngrok.io/";


    static final String HISTORY_URL = "http://212.224.118.153:18081/";
    //

    public void getButStopTimer(MapsActivity.BusStopCallback callback, String id) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        GerritAPI gerritAPI = retrofit.create(GerritAPI.class);

        Call<List<Change>> call = gerritAPI.loadChanges(id);
        call.enqueue(new Callback<List<Change>>() {
            @Override
            public void onResponse(Call<List<Change>> call, Response<List<Change>> response) {
                Log.e("!!!!!!!!!!!", "REq=");

                if (response.isSuccessful()) {
                    Log.e("!!!!!!!!!!!", "isSuccessful=" + response.code());
                    List<Change> changesList = response.body();
                    callback.onReadyBusTop(changesList);
                    //changesList.forEach(change -> Log.e("!!!!!!!!!!!", "SBJ=" + change.toString()));
                } else {
                    System.out.println(response.errorBody());
                    Log.e("!!!!!!!!!!!", "SBJ ERROR=" + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<Change>> call, Throwable t) {
                t.printStackTrace();
            }

        });
    }

    public void start2(MapsActivity.PinsCallback callback) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        GerritAPI gerritAPI = retrofit.create(GerritAPI.class);

        Call<List<BusStop>> call = gerritAPI.loadChanges2();
        call.enqueue(new Callback<List<BusStop>>() {
            @Override
            public void onResponse(Call<List<BusStop>> call, Response<List<BusStop>> response) {
                Log.e("!!!!!!!!!!!", "REq=");

                if (response.isSuccessful()) {
                    Log.e("!!!!!!!!!!!", "isSuccessful=" + response.code());
                    List<BusStop> changesList = response.body();
                    changesList.forEach(change -> Log.e("!!!!!!!!!!!", "SBJ=" + change.toString()));
                    callback.onReadyBusTop(changesList);
                } else {
                    System.out.println(response.errorBody());
                    Log.e("!!!!!!!!!!!", "SBJ ERROR=" + response.errorBody());
                }
            }

            public void onFailure(Call<List<BusStop>> call, Throwable t) {
                t.printStackTrace();
            }

        });

    }

    public void getHistoryData(MapsActivity.HistoryCallback callback) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HISTORY_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        GerritAPI gerritAPI = retrofit.create(GerritAPI.class);

        Call<List<BusStop>> call = gerritAPI.loadHistory();
        call.enqueue(new Callback<List<BusStop>>() {
            @Override
            public void onResponse(Call<List<BusStop>> call, Response<List<BusStop>> response) {
                Log.e("!!!!!!!!!!!", "REq=");

                if (response.isSuccessful()) {
                    Log.e("!!!!!!!!!!!", "isSuccessful=" + response.code());
                    List<BusStop> changesList = response.body();
                    changesList.forEach(change -> Log.e("!!!!!!!!!!!", "SBJ=" + change.toString()));
                    callback.onReadyBusTop(changesList);
                } else {
                    System.out.println(response.errorBody());
                    Log.e("!!!!!!!!!!!", "SBJ ERROR=" + response.errorBody());
                }
            }

            public void onFailure(Call<List<BusStop>> call, Throwable t) {
                t.printStackTrace();
            }

        });

    }

}