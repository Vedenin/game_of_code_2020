package com.example.luxtravel.network;

import com.google.gson.annotations.SerializedName;

public class BusStop {

    @SerializedName("id")
    public String id;
    @SerializedName("name")
    public String name;
    @SerializedName("lat")
    public String lat;
    @SerializedName("lon")
    public String lon;


//    @Override
//    public String toString() {
//        return "\n\nNext stop time\n" +
//                "Arrival Time is:'" + arrivalTime + '\n' +
//                "Departure Time is:'" + departureTime + '\n' +
//                "Trip Head Sign is:'" + tripHeadSign + '\n' +
//                "Rout Name is:'" + routName + '\n';
//    }


    @Override
    public String toString() {
        return "BusStop{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", lat='" + lat + '\'' +
                ", lon='" + lon + '\'' +
                '}';
    }
}
