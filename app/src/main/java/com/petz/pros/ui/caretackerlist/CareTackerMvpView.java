package com.petz.pros.ui.caretackerlist;

import com.petz.pros.ui.base.MvpView;

import java.util.List;

/**
 * Created on : Feb 11, 2019
 * Author     : AndroidWave
 */
public interface CareTackerMvpView extends MvpView {

    void updateAvailableCareTackers(List<CareTackersModel> body);

    String getServiceDate();

    String getStartTime();

    String getEndTime();

    void showErrorMessageDate(String message);

    void showErrorMessageStartTime(String message);

    void showErrorMessageEndTime(String message);

    void onClickBookBtn(CareTackersModel movie);

    void noDataFound(String message);


}
