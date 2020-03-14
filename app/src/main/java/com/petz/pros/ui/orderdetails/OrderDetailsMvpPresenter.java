package com.petz.pros.ui.orderdetails;

import com.petz.pros.ui.base.MvpPresenter;
import com.petz.pros.ui.bookingdetails.BookingModel;
import com.petz.pros.ui.bookingdetails.BookingMvpView;

/**
 * Created on : Feb 11, 2019
 * Author     : AndroidWave
 */
public interface OrderDetailsMvpPresenter<V extends OrderDetailsMvpView> extends MvpPresenter<V> {

    String getUserCity();

    String getUserState();
}
