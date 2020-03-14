package com.petz.pros.ui.caretackerlist;

import com.petz.pros.ui.base.MvpPresenter;
import com.petz.pros.ui.main.MainMvpView;

/**
 * Created on : Feb 11, 2019
 * Author     : AndroidWave
 */
public interface CareTackerListMvpPresenter<V extends CareTackerMvpView> extends MvpPresenter<V> {

    void validateServiceDetails(int serviceId);

    void getCareTackers(int serviceId);

    int getOwnerId();

    String getOwnerName();

    String getOwnerPhone();

    String getOwnerAddress();
}
