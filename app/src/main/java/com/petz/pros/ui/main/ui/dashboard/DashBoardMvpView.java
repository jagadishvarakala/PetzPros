package com.petz.pros.ui.main.ui.dashboard;

import com.petz.pros.data.network.pojo.RegistrationRequest;
import com.petz.pros.ui.base.MvpView;

public interface DashBoardMvpView extends MvpView {

    void onClickPendingService();

    void onCLickRejectedService();

    void onClickPetService();

    void onClickUpComingService();

    void onClickProfile();

    void onCLickMore();

    void onClickLogout();

    void onSuccessProfileUpdate(RegistrationRequest body);

    void onAvalibulity(boolean isAvailabl);

    void invalidCrediential();
}
