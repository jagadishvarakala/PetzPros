package com.petz.pros.ui.main.ui.history;

import com.google.gson.annotations.SerializedName;

public class HistoryModule {

    @SerializedName("Id")
    private int id;
    @SerializedName("OwnerId")
    private int ownerId;
    @SerializedName("CareTakerID")
    private int careTakerID;
    @SerializedName("Name")
    private String name;
    @SerializedName("Phone")
    private String phone;
    @SerializedName("Address")
    private String Address;
    @SerializedName("AppointmentDate")
    private String AppointmentDate;
    @SerializedName("StartTime")
    private String StartTime;
    @SerializedName("EndTime")
    private String EndTime;
    @SerializedName("TotalAmount")
    private int TotalAmount;
    @SerializedName("BookingStatus")
    private boolean BookingStatus;
    @SerializedName("PaymentStatus")
    private boolean PaymentStatus;
    @SerializedName("CareTaker")
    private Object careTacker;
    @SerializedName("ServiceName")
    private String ServiceName;


    public int getId() {
        return id;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public int getCareTakerID() {
        return careTakerID;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return Address;
    }

    public String getAppointmentDate() {
        return AppointmentDate;
    }

    public String getStartTime() {
        return StartTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public int getTotalAmount() {
        return TotalAmount;
    }

    public boolean isBookingStatus() {
        return BookingStatus;
    }

    public boolean isPaymentStatus() {
        return PaymentStatus;
    }

    public Object getCareTacker() {
        return careTacker;
    }

    public String getServiceName() {
        return ServiceName;
    }
}
