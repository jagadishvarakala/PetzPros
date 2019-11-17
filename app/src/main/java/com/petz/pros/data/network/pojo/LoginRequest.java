package com.petz.cleancode.data.network.pojo;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class LoginRequest {

    @SerializedName("EmailId")
    private String mEmailId;
    @SerializedName("Password")
    private String mPassword;
    @SerializedName("UserType")
    private String mUserType;


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
