package com.petz.pros.data.network.pojo;

import com.google.gson.annotations.SerializedName;

public class IsUserExitRequest {

    @SerializedName("UserName")
    private String userName;
    @SerializedName("UserType")
    private String userType;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
