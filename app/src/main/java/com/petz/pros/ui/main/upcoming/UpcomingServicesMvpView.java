package com.petz.pros.ui.main.upcoming;

import com.petz.pros.ui.base.MvpView;
import com.petz.pros.ui.main.ui.bookings.BookingsModule;

import java.util.ArrayList;

public interface UpcomingServicesMvpView extends MvpView {

    void showNoDataFound(String message);

    void updateBookings(ArrayList<ArrayList<BookingsModule>> arrayLists);

    void onSuccessStartService(BookingsModule bookingsModule);

    void onSuccessEndService();
}
