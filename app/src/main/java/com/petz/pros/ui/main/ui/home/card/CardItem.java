package com.petz.pros.ui.main.ui.home.card;


import android.graphics.drawable.Drawable;

public class CardItem {

    private int mTextResource;
    private int mTitleResource;
    private int serviceId;
    private Drawable serviceImage;

    public CardItem(int title, int text, int id, Drawable serviceImage) {
        mTitleResource = title;
        mTextResource = text;
        this.serviceImage = serviceImage;
        serviceId = id;
    }

    public int getText() {
        return mTextResource;
    }

    public int getTitle() {
        return mTitleResource;
    }

    public int getServiceId() {
        return serviceId;
    }

    public Drawable getServiceImage() {
        return serviceImage;
    }
}
