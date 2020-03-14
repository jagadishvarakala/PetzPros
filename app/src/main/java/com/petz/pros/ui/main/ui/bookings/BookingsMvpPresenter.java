package com.petz.pros.ui.main.ui.bookings;

import com.petz.pros.ui.base.MvpPresenter;

public interface BookingsMvpPresenter<V extends BookingsMvpView> extends MvpPresenter<V> {

    void getBookingsDetails();

    void storePaymentDetails(UpdatePayment bookingsModule);

    boolean isPetOwner();
}
