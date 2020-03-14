
package com.petz.pros.data.network.pojo;


import android.graphics.drawable.Drawable;


@SuppressWarnings("unused")
public class FeedItem {

   private String serviceName;
   private int serviceId;
   private Drawable serviceImage;
   private String description;

    public FeedItem( int serviceId,String serviceName, Drawable serviceImage,String desc) {
        this.serviceName = serviceName;
        this.serviceId = serviceId;
        this.serviceImage = serviceImage;
        this.description = desc;
    }

    public String getServiceName() {
        return serviceName;
    }

    public int getServiceId() {
        return serviceId;
    }

    public Drawable getServiceImage() {
        return serviceImage;
    }

    public String getDescription() {
        return description;
    }
}
