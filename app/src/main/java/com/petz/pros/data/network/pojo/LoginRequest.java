package com.petz.pros.data.network.pojo;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class LoginRequest {

    @SerializedName("FCMDeviceId")
    private String FCMDeviceId;

    @SerializedName("EmailId")
    private String mEmailId;
    @SerializedName("Password")
    private String mPassword;
    @SerializedName("UserType")
    private String mUserType;

    public void setFCMDeviceId(String FCMDeviceId) {
        this.FCMDeviceId = FCMDeviceId;
    }

    public void setmEmailId(String mEmailId) {
        this.mEmailId = mEmailId;
    }

    public void setmPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    public void setmUserType(String mUserType) {
        this.mUserType = mUserType;
    }
}
