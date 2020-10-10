package com.example.luxtravel.network;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GerritAPI {

    @GET("stops/")
    Call<List<Change>> loadChanges2();

    @GET("stops/000200403009/stopTimes?limit=25")
    Call<List<Change>> loadChanges();
}