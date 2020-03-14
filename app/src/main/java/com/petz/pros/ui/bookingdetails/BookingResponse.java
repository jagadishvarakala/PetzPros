package com.petz.pros.ui.bookingdetails;

import com.google.gson.annotations.SerializedName;
import com.petz.pros.data.DataManager;
import com.petz.pros.data.prefs.PreferencesManager;
import com.petz.pros.utils.CommonUtils;

import java.io.Serializable;

public class BookingResponse implements Serializable {

    @SerializedName("Id")
    private int id;
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
    private String endTime;
    @SerializedName("TotalAmount")
    private double totalAmount;
    @SerializedName("BookingStatus")
    private String bookingStatus;
    @SerializedName("PaymentStatus")
    private String paymentStatus;
    @SerializedName("ServiceName")
    private String serviceName;
    @SerializedName("FCMDeviceId")
    private String FCMDeviceId;
    @SerializedName("CareTaker")
    private CareTakerObj  careTaker;

    private String city;
    private String state;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public int getCareTakerID() {
        return careTakerID;
    }

    public int getServiceRefTypeId() {
        return serviceRefTypeId;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getFCMDeviceId() {
        return FCMDeviceId;
    }

    public CareTakerObj getCareTaker() {
        return careTaker;
    }

    public static class CareTakerObj implements Serializable{
        @SerializedName("Id")
        private int id;
        @SerializedName("FirstName")
        private String firstName;
        @SerializedName("LastName")
        private String lastName;
        @SerializedName("EmailId")
        private String emailId;
        @SerializedName("Password")
        private String password;
        @SerializedName("Phone")
        private String phone;
        @SerializedName("Address")
        private String address;
        @SerializedName("City")
        private String city;
        @SerializedName("State")
        private String state;
        @SerializedName("Country")
        private String country;
        @SerializedName("ZipCode")
        private int zipCode;
        @SerializedName("ChargePerHour")
        private double chargePerHour;
        @SerializedName("IsWalkerAvailable")
        private boolean isWalkerAvailable;
        @SerializedName("UserRefTypeID")
        private int userRefTypeID;

        public int getId() {
            return id;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getEmailId() {
            return emailId;
        }

        public String getPassword() {
            return password;
        }

        public String getPhone() {
            return phone;
        }

        public String getAddress() {
            return address;
        }

        public String getCity() {
            return city;
        }

        public String getState() {
            return state;
        }

        public String getCountry() {
            return country;
        }

        public int getZipCode() {
            return zipCode;
        }

        public double getChargePerHour() {
            return chargePerHour;
        }

        public boolean isWalkerAvailable() {
            return isWalkerAvailable;
        }

        public int getUserRefTypeID() {
            return userRefTypeID;
        }
    }

    public String getAppointmentDateMonth(){
      return CommonUtils.convertMonth(appointmentDate);
    }

    public String getAppointmentDateDate(){
      return CommonUtils.convertAppointDate(appointmentDate);
    }

    public String getAppointmentDateYear(){
      return CommonUtils.convertAppointYear(appointmentDate);
    }

    public String getAppointmentStartTime(){
        return CommonUtils.convertTime(startTime);
    }

    public String getAppointmentEndTime(){
        return CommonUtils.convertTime(endTime);
    }

}
