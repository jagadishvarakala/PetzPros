package com.petz.pros.ui.main.ui.bookings;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;
import com.petz.pros.utils.CommonUtils;

import java.io.Serializable;

public class BookingsModule implements Serializable {

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
    private double TotalAmount;
    @SerializedName("BookingStatus")
    private String BookingStatus ;
    @SerializedName("PaymentStatus")
    private String PaymentStatus;
    @SerializedName("CareTaker")
    private Object careTacker;
    @SerializedName("ServiceName")
    private String ServiceName;
    @SerializedName("FCMDeviceId")
    private String FCMDeviceId;
    @SerializedName("ActualStartTime")
    private String ActualStartTime;
    @SerializedName("ActualEndTime")
    private String ActualEndTime;
    @SerializedName("isServiceStart")
    private boolean isServiceStart;
    @SerializedName("isServiceEnd")
    private boolean isServiceEnd;

    public void setName(String name) {
        this.name = name;
    }

    public String getActualStartTime() {
        return CommonUtils.convertTime(ActualStartTime);
    }

    public String getActualEndTime() {
        return CommonUtils.convertTime(ActualEndTime);
    }

    public boolean isServiceStart() {
        return isServiceStart;
    }

    public boolean isServiceEnd() {
        return isServiceEnd;
    }

    public String getFCMDeviceId() {
        return FCMDeviceId;
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
        return CommonUtils.convertDate(AppointmentDate);
    }

    public String getStartTime() {
        return CommonUtils.convertTime(StartTime);
    }

    public String getEndTime() {
        return CommonUtils.convertTime(EndTime);
    }

    public double getTotalAmount() {
        return TotalAmount;
    }


    public void setBookingStatus(String bookingStatus) {
        BookingStatus = bookingStatus;
    }

    public String isBookingStatus() {

        return BookingStatus;
    }


    public String isPaymentStatus() {
        return PaymentStatus;
    }

    public Object getCareTacker() {
        return careTacker;
    }

    public String getServiceName() {
        return ServiceName;
    }

    @SerializedName("paymentDetails")
    private PaymentDetails paymentDetails;

    public BookingsModule.PaymentDetails getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(BookingsModule.PaymentDetails paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    public static class PaymentDetails{

        @SerializedName("TransactionId")
        private String TransactionId;
        @SerializedName("CreatedDateTime")
        private String CreatedDateTime;
        @SerializedName("Tax")
        private double Tax;
        @SerializedName("Shipping")
        private double Shipping;
        @SerializedName("SubTotal")
        private double SubTotal;
        @SerializedName("TotalAmountPaid")
        private double TotalAmountPaid;
        @SerializedName("FkBookingId")
        private int FkBookingId;

        public String getTransactionId() {
            return TransactionId;
        }

        public void setTransactionId(String transactionId) {
            TransactionId = transactionId;
        }

        public String getCreatedDateTime() {
            return CreatedDateTime;
        }

        public void setCreatedDateTime(String createdDateTime) {
            CreatedDateTime = createdDateTime;
        }

        public double getTax() {
            return Tax;
        }

        public void setTax(double tax) {
            Tax = tax;
        }

        public double getShipping() {
            return Shipping;
        }

        public void setShipping(double shipping) {
            Shipping = shipping;
        }

        public double getSubTotal() {
            return SubTotal;
        }

        public void setSubTotal(double subTotal) {
            SubTotal = subTotal;
        }

        public double getTotalAmountPaid() {
            return TotalAmountPaid;
        }

        public void setTotalAmountPaid(double totalAmountPaid) {
            TotalAmountPaid = totalAmountPaid;
        }

        public int getFkBookingId() {
            return FkBookingId;
        }

        public void setFkBookingId(int fkBookingId) {
            FkBookingId = fkBookingId;
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
}
