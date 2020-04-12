package com.petz.pros.ui.main.upcoming;

import com.petz.pros.ui.base.MvpPresenter;
import com.petz.pros.ui.main.ui.bookings.BookingsModule;
import com.petz.pros.ui.main.ui.pending.PendingServicesMvpView;

public interface UpcomingServicesMvpPresenter<V extends UpcomingServicesMvpView> extends MvpPresenter<V> {

    void getBookingsDetails();

    boolean isPetOwner();

    void startService(BookingsModule bookingsModule);

    void endService(BookingsModule bookingsModule);
}
