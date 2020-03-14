package com.petz.pros.ui.main;

import com.petz.pros.ui.base.MvpPresenter;

/**
 * Created on : Feb 11, 2019
 * Author     : AndroidWave
 */
public interface MainMvpPresenter<V extends MainMvpView> extends MvpPresenter<V> {
    void onViewPrepared();

    String getUserName();

    String getUserEmail();

    void userLogout();
}
