package com.petz.pros.ui.main.ui.pending;

import com.petz.pros.ui.base.MvpPresenter;
import com.petz.pros.ui.main.ui.bookings.BookingsModule;
import com.petz.pros.ui.main.ui.bookings.BookingsMvpView;

public interface PendingServicesMvpPresenter<V extends PendingServicesMvpView> extends MvpPresenter<V> {

    void getBookingsDetails();

    void updateBookingStatus(BookingsModule bookingsModule,int position);
}
