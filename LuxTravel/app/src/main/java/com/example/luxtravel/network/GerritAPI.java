package com.example.luxtravel.network;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GerritAPI {

    @GET("stops/")
    Call<List<BusStop>> loadChanges2();

    @GET("stops/{id}/stopTimes?limit=25")
    Call<List<Change>> loadChanges(@Path("id") String id);


    @GET("stops/")
    Call<List<BusStop>> loadHistory();
}