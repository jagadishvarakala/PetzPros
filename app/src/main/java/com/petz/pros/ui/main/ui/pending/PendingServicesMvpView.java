package com.petz.pros.ui.main.ui.pending;

import com.petz.pros.ui.base.MvpView;
import com.petz.pros.ui.main.ui.bookings.BookingsModule;

import java.util.ArrayList;

public interface PendingServicesMvpView extends MvpView {

    void showNoDataFound(String message);

    void updateBookings(ArrayList<BookingsModule> arrayLists);

    void onClickRequestAccept(BookingsModule bookingsModule, int position);

    void onClickRequestReject(BookingsModule bookingsModule,int position);

    void onSuccessUpdateBookingStatus(int position);
}
