package com.petz.pros.ui.main.ui.past;

import com.petz.pros.ui.base.MvpView;
import com.petz.pros.ui.main.ui.bookings.BookingsModule;

import java.util.ArrayList;

public interface PastServicesMvpView extends MvpView {

    void showNoDataFound(String message);

    void updateBookings(ArrayList<ArrayList<BookingsModule>> arrayLists);
}
