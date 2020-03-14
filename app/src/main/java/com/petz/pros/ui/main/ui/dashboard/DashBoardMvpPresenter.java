package com.petz.pros.ui.main.ui.dashboard;

import com.petz.pros.data.network.pojo.RegistrationRequest;
import com.petz.pros.ui.base.MvpPresenter;

public interface DashBoardMvpPresenter<V extends DashBoardMvpView> extends MvpPresenter<V> {

    String getUserName();

    void onClickPendingServices();

    void onClickRejectedServices();

    void onClickPerServices();

    void onClickUpComing();

    void onClickProfile();

    void onClickMore();

    void onClickLogout();

    RegistrationRequest getProfileDetails(boolean isAvailable);

    void petOwnerProfileUpdate(RegistrationRequest registrationRequest);

    void careTackerLogin();
}
