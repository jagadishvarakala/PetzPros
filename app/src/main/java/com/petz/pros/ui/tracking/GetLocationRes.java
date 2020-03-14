package com.petz.pros.ui.tracking;

import com.google.gson.annotations.SerializedName;

public class GetLocationRes {

    @SerializedName("ID")
    private int id;
    @SerializedName("BookingId")
    private int bookingId;
    @SerializedName("GeoDataPath")
    private String geoDataPath;


    public int getId() {
        return id;
    }

    public int getBookingId() {
        return bookingId;
    }

    public String getGeoDataPath() {
        return geoDataPath;
    }
}
