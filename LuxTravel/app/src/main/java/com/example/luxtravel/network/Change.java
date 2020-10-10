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
        return "\n\nNext stop time\n" +
                "Arrival Time is:'" + arrivalTime + '\n' +
                "Departure Time is:'" + departureTime + '\n' +
                "Trip Head Sign is:'" + tripHeadSign + '\n' +
                "Rout Name is:'" + routName + '\n';
    }
}
