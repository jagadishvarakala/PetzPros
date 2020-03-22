package com.petz.pros.ui.main.upcoming;

import com.google.gson.annotations.SerializedName;

public class StartServiceReq {

    @SerializedName("Id")
    private int id;
    @SerializedName("BookingStatus")
    private String bookingStatus;
    @SerializedName("PaymentStatus")
    private String paymentStatus;
    @SerializedName("ActualStartTime")
    private String actualStartTime;
    @SerializedName("ActualEndTime")
    private String actualEndTime;

    public void setId(int id) {
        this.id = id;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public void setActualStartTime(String actualStartTime) {
        this.actualStartTime = actualStartTime;
    }

    public void setActualEndTime(String actualEndTime) {
        this.actualEndTime = actualEndTime;
    }
}
