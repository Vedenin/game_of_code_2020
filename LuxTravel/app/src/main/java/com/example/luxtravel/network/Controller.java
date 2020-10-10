package com.example.luxtravel.network;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Controller implements Callback<List<Change>> {

    //static final String BASE_URL = "https://git.eclipse.org/r/";
    static final String BASE_URL = "http://3d2d87943bb0.ngrok.io/";

    //

    public void getButStopTimer() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        GerritAPI gerritAPI = retrofit.create(GerritAPI.class);

        Call<List<Change>> call = gerritAPI.loadChanges();
        call.enqueue(this);

    }


    public void start2() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        GerritAPI gerritAPI = retrofit.create(GerritAPI.class);

        Call<List<Change>> call = gerritAPI.loadChanges2();
        call.enqueue(new Callback<List<Change>>() {
            @Override
            public void onResponse(Call<List<Change>> call, Response<List<Change>> response) {
                Log.e("!!!!!!!!!!!", "REq=" );

                if(response.isSuccessful()) {
                    Log.e("!!!!!!!!!!!", "isSuccessful=" + response.code());
                    List<Change> changesList = response.body();
                    changesList.forEach(change -> Log.e("!!!!!!!!!!!", "SBJ=" + change.toString()));
                } else {
                    System.out.println(response.errorBody());
                    Log.e("!!!!!!!!!!!", "SBJ ERROR=" + response.errorBody());
                }
            }

            public void onFailure(Call<List<Change>> call, Throwable t) {
                t.printStackTrace();
            }

        });

    }

    @Override
    public void onResponse(Call<List<Change>> call, Response<List<Change>> response) {
        Log.e("!!!!!!!!!!!", "REq=" );

        if(response.isSuccessful()) {
            Log.e("!!!!!!!!!!!", "isSuccessful=" + response.code());
            List<Change> changesList = response.body();
            changesList.forEach(change -> Log.e("!!!!!!!!!!!", "SBJ=" + change.toString()));
        } else {
            System.out.println(response.errorBody());
            Log.e("!!!!!!!!!!!", "SBJ ERROR=" + response.errorBody());
        }
    }

    @Override
    public void onFailure(Call<List<Change>> call, Throwable t) {
        t.printStackTrace();
    }
}