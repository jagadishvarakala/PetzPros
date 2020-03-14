package com.petz.pros.ui.main.ui.history;

import com.petz.pros.ui.base.MvpView;
import com.petz.pros.ui.main.ui.bookings.BookingsModule;

import java.util.ArrayList;

public interface HistoryMvpView extends MvpView {

    void showNoDataFound(String message);

    void updateHistory(ArrayList<ArrayList<BookingsModule>> arrayLists);

    void onClickTrackBtn(BookingsModule bookingsModule);
}
