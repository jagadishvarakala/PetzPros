package com.petz.pros.ui.main.ui.bookings;

import com.petz.pros.ui.base.MvpView;

import java.util.ArrayList;

import okhttp3.ResponseBody;

public interface BookingsMvpView extends MvpView {

    void showNoDataFound(String message);

    void updateBooking(ArrayList<BookingsModule> arrayLists);

    void onClickPayBtn(BookingsModule module);

    void onClickTrackBtn(BookingsModule bookingsModule);

    void onClickStartService(BookingsModule bookingsModule);

    void paymentDetailsUpdated(ResponseBody responseBody);

    void onClickServiceStart(BookingsModule bookingsModule);

    void onClickEndService(BookingsModule bookingsModule);
}
