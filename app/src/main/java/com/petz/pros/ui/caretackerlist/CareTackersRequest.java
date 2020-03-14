package com.petz.pros.ui.caretackerlist;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CareTackersRequest {

    @SerializedName("ZipCode")
    private int mZipCode;
    @SerializedName("ServiceIdList")
    private ArrayList<Integer> mServiceIdList;

    public void setmZipCode(int mZipCode) {
        this.mZipCode = mZipCode;
    }

    public void setmServiceIdList(ArrayList<Integer> mServiceIdList) {
        this.mServiceIdList = mServiceIdList;
    }
}
