package com.petz.pros.ui.main.ui.bookings;

import com.google.gson.annotations.SerializedName;

public class UpdatePayment {

    @SerializedName("bookingDetails")
    private BookingDetails bookingDetails;

    @SerializedName("paymentDetails")
    private PaymentDetails paymentDetails;

    public BookingDetails getBookingDetails() {
        return bookingDetails;
    }

    public void setBookingDetails(BookingDetails bookingDetails) {
        this.bookingDetails = bookingDetails;
    }

    public PaymentDetails getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(PaymentDetails paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    public static class BookingDetails{
        @SerializedName("Id")
        private int id;
        @SerializedName("OwnerId")
        private int ownerId;
        @SerializedName("CareTakerID")
        private int careTackerId;
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
        @SerializedName("CareTaker")
        private Object careTaker;
        @SerializedName("FCMDeviceId")
        private String FCMDeviceId;

        public String getFCMDeviceId() {
            return FCMDeviceId;
        }

        public void setFCMDeviceId(String FCMDeviceId) {
            this.FCMDeviceId = FCMDeviceId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getOwnerId() {
            return ownerId;
        }

        public void setOwnerId(int ownerId) {
            this.ownerId = ownerId;
        }

        public int getCareTackerId() {
            return careTackerId;
        }

        public void setCareTackerId(int careTackerId) {
            this.careTackerId = careTackerId;
        }

        public int getServiceRefTypeId() {
            return serviceRefTypeId;
        }

        public void setServiceRefTypeId(int serviceRefTypeId) {
            this.serviceRefTypeId = serviceRefTypeId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getAppointmentDate() {
            return appointmentDate;
        }

        public void setAppointmentDate(String appointmentDate) {
            this.appointmentDate = appointmentDate;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public double getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(double totalAmount) {
            this.totalAmount = totalAmount;
        }

        public String isBookingStatus() {
            return bookingStatus;
        }

        public void setBookingStatus(String bookingStatus) {
            this.bookingStatus = bookingStatus;
        }

        public String isPaymentStatus() {
            return paymentStatus;
        }

        public void setPaymentStatus(String paymentStatus) {
            this.paymentStatus = paymentStatus;
        }

        public String getServiceName() {
            return serviceName;
        }

        public void setServiceName(String serviceName) {
            this.serviceName = serviceName;
        }

        public Object getCareTaker() {
            return careTaker;
        }

        public void setCareTaker(Object careTaker) {
            this.careTaker = careTaker;
        }
    }

    public static class CareTaker{
        @SerializedName("Id")
        private int id;
        @SerializedName("FirstName")
        private String firstName;
        @SerializedName("LastName")
        private String lastName;
        @SerializedName("EmailId")
        private String emailid;
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
        private int zipcode;
        @SerializedName("ChargePerHour")
        private double chargePerHour;
        @SerializedName("IsWalkerAvailable")
        private boolean isWalkerAvailable;
        @SerializedName("UserRefTypeID")
        private int userRefTypeID;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getEmailid() {
            return emailid;
        }

        public void setEmailid(String emailid) {
            this.emailid = emailid;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

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

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public int getZipcode() {
            return zipcode;
        }

        public void setZipcode(int zipcode) {
            this.zipcode = zipcode;
        }

        public double getChargePerHour() {
            return chargePerHour;
        }

        public void setChargePerHour(double chargePerHour) {
            this.chargePerHour = chargePerHour;
        }

        public boolean isWalkerAvailable() {
            return isWalkerAvailable;
        }

        public void setWalkerAvailable(boolean walkerAvailable) {
            isWalkerAvailable = walkerAvailable;
        }

        public int getUserRefTypeID() {
            return userRefTypeID;
        }

        public void setUserRefTypeID(int userRefTypeID) {
            this.userRefTypeID = userRefTypeID;
        }
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
    }

}
