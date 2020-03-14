package com.petz.pros.ui.bookingdetails;

import com.petz.pros.ui.base.MvpPresenter;
import com.petz.pros.ui.caretackerlist.CareTackerMvpView;

/**
 * Created on : Feb 11, 2019
 * Author     : AndroidWave
 */
public interface BookingMvpPresenter<V extends BookingMvpView> extends MvpPresenter<V> {

        void onClickConfirmBooking(BookingModel bookingModel);
}
