package com.petz.pros.data.network.pojo;

import com.google.gson.annotations.SerializedName;

public class IsUserExitResponse {
    @SerializedName("status")
    private boolean status;

    public boolean isStatus() {
        return status;
    }
}
