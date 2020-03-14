package com.petz.pros.ui.main.ui.rejected;

import com.petz.pros.ui.base.MvpView;
import com.petz.pros.ui.main.ui.bookings.BookingsModule;

import java.util.ArrayList;

public interface RejectedServicesMvpView extends MvpView {

    void showNoDataFound(String message);

    void updateBookings(ArrayList<BookingsModule> arrayLists);
}
