package com.petz.pros.ui.tracking;

import com.google.gson.annotations.SerializedName;

public class UpdateGeoReq {

    @SerializedName("BookingId")
    private int bookingId;
    @SerializedName("GeoDataPath")
    private String geoDataPath;

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public void setGeoDataPath(String geoDataPath) {
        this.geoDataPath = geoDataPath;
    }
}
