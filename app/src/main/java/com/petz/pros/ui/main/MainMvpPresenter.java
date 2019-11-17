package com.petz.cleancode.ui.main;

import com.petz.cleancode.ui.base.MvpPresenter;

/**
 * Created on : Feb 11, 2019
 * Author     : AndroidWave
 */
public interface MainMvpPresenter<V extends MainMvpView> extends MvpPresenter<V> {
    void onViewPrepared();
}
