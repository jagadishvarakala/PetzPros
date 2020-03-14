package com.petz.pros.ui.bookingdetails;

import com.petz.pros.ui.base.MvpView;
import com.petz.pros.ui.caretackerlist.CareTackersModel;

import java.util.List;

/**
 * Created on : Feb 11, 2019
 * Author     : AndroidWave
 */
public interface BookingMvpView extends MvpView {

    void bookingConfirmSuccess(BookingResponse bookingResponse);

}
