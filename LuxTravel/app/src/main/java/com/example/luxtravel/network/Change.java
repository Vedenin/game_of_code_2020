package com.example.luxtravel.network;

import com.google.gson.annotations.SerializedName;

public class Change {



    @SerializedName("arrivalTime")
    public String arrivalTime;
    @SerializedName("departureTime")
    public String departureTime;
    @SerializedName("tripHeadSign")
    public String tripHeadSign;
    @SerializedName("routName")
    public String routName;


    @Override
    public String toString() {
        return "Change{" +
                "arrivalTime='" + arrivalTime + '\'' +
                ", departureTime='" + departureTime + '\'' +
                ", tripHeadSign='" + tripHeadSign + '\'' +
                ", routName='" + routName + '\'' +
                '}';
    }
}




//"arrivalTime":"16:25:20",
//        "departureTime":"16:25:20",
//        "tripHeadSign":"Luxembourg Centre Hospitalier",
//        "routName":"13"