package com.petz.pros.ui.bookingdetails;

import com.google.gson.annotations.SerializedName;
import com.petz.pros.ui.caretackerlist.CareTackersModel;

import java.io.Serializable;

public class BookingModel implements Serializable {

    private int serviceId;
    @SerializedName("ServiceName")
    private String serviceName;
    private String serviceDate;
    private String serviceStartTime;
    private String serviceEndTime;
    private String serviceTotalTime;
    private String serviceTotalAmount;

    @SerializedName("OwnerId")
    private int ownerId;
    @SerializedName("CareTakerID")
    private int careTakerID;
    @SerializedName("ServiceRefTypeId")
    private int serviceRefTypeId;
    @SerializedName("Name")
    private String name;
    @SerializedName("Phone")
    private String phone;
    @SerializedName("Address")
    private String address;
    @SerializedName("AppointmentDate")
    private String appointmentDate;
    @SerializedName("StartTime")
    private String startTime;
    @SerializedName("EndTime")
    private String EndTime;
    @SerializedName("TotalAmount")
    private double totalAmount;
    @SerializedName("BookingStatus")
    private String bookingStatus;
    @SerializedName("PaymentStatus")
    private String paymentStatus;
    @SerializedName("CareTaker")
    private CareTackersModel careTackersModel;

    public String getFCMDeviceId() {
        return FCMDeviceId;
    }

    public void setFCMDeviceId(String FCMDeviceId) {
        this.FCMDeviceId = FCMDeviceId;
    }

    @SerializedName("FCMDeviceId")
    private String FCMDeviceId;

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(String serviceDate) {
        this.serviceDate = serviceDate;
    }

    public String getServiceStartTime() {
        return serviceStartTime;
    }

    public void setServiceStartTime(String serviceStartTime) {
        this.serviceStartTime = serviceStartTime;
    }

    public String getServiceEndTime() {
        return serviceEndTime;
    }

    public void setServiceEndTime(String serviceEndTime) {
        this.serviceEndTime = serviceEndTime;
    }

    public String getServiceTotalTime() {
        return serviceTotalTime;
    }

    public void setServiceTotalTime(String serviceTotalTime) {
        this.serviceTotalTime = serviceTotalTime;
    }

    public String getServiceTotalAmount() {
        return serviceTotalAmount;
    }

    public void setServiceTotalAmount(String serviceTotalAmount) {
        this.serviceTotalAmount = serviceTotalAmount;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public void setCareTakerID(int careTakerID) {
        this.careTakerID = careTakerID;
    }

    public void setServiceRefTypeId(int serviceRefTypeId) {
        this.serviceRefTypeId = serviceRefTypeId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public void setCareTackersModel(CareTackersModel careTackersModel) {
        this.careTackersModel = careTackersModel;
    }

    @SerializedName("ActualStartTime")
    private String ActualStartTime;
    @SerializedName("ActualEndTime")
    private String ActualEndTime;
    @SerializedName("isServiceStart")
    private boolean isServiceStart;
    @SerializedName("isServiceEnd")
    private boolean isServiceEnd;

    public String getActualStartTime() {
        return ActualStartTime;
    }

    public String getActualEndTime() {
        return ActualEndTime;
    }

    public boolean isServiceStart() {
        return isServiceStart;
    }

    public boolean isServiceEnd() {
        return isServiceEnd;
    }
}
