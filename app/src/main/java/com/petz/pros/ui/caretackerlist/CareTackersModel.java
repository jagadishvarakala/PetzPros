package com.petz.pros.ui.caretackerlist;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CareTackersModel implements Serializable {

    @SerializedName("Id")
    public int mId;
    @SerializedName("FirstName")
    public String mFirstName;
    @SerializedName("LastName")
    public String mLastName;
    @SerializedName("EmailId")
    public String mEmailId;
    @SerializedName("Phone")
    public String mPhone;
    @SerializedName("Address")
    public String mAddress;
    @SerializedName("City")
    public String mCity;
    @SerializedName("State")
    public String mState;
    @SerializedName("Country")
    public String mCountry;
    @SerializedName("ZipCode")
    public String mZipCode;
    @SerializedName("ChargePerHour")
    public int mChargePerHour;
    @SerializedName("IsWalkerAvailable")
    public boolean mIsWalkerAvailable;

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmFirstName() {
        return mFirstName;
    }

    public void setmFirstName(String mFirstName) {
        this.mFirstName = mFirstName;
    }

    public String getmLastName() {
        return mLastName;
    }

    public void setmLastName(String mLastName) {
        this.mLastName = mLastName;
    }

    public String getmEmailId() {
        return mEmailId;
    }

    public void setmEmailId(String mEmailId) {
        this.mEmailId = mEmailId;
    }

    public String getmPhone() {
        return mPhone;
    }

    public void setmPhone(String mPhone) {
        this.mPhone = mPhone;
    }

    public String getmAddress() {
        return mAddress;
    }

    public void setmAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public String getmCity() {
        return mCity;
    }

    public void setmCity(String mCity) {
        this.mCity = mCity;
    }

    public String getmState() {
        return mState;
    }

    public void setmState(String mState) {
        this.mState = mState;
    }

    public String getmCountry() {
        return mCountry;
    }

    public void setmCountry(String mCountry) {
        this.mCountry = mCountry;
    }

    public String getmZipCode() {
        return mZipCode;
    }

    public void setmZipCode(String mZipCode) {
        this.mZipCode = mZipCode;
    }

    public int getmChargePerHour() {
        return mChargePerHour;
    }

    public void setmChargePerHour(int mChargePerHour) {
        this.mChargePerHour = mChargePerHour;
    }

    public boolean ismIsWalkerAvailable() {
        return mIsWalkerAvailable;
    }

    public void setmIsWalkerAvailable(boolean mIsWalkerAvailable) {
        this.mIsWalkerAvailable = mIsWalkerAvailable;
    }
    @SerializedName("FCMDeviceId")
    private String FCMDeviceId;

    public String getFCMDeviceId() {
        return FCMDeviceId;
    }

    public void setFCMDeviceId(String FCMDeviceId) {
        this.FCMDeviceId = FCMDeviceId;
    }
}
