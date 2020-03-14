package com.petz.pros.ui.main.ui.history;

import com.petz.pros.ui.base.MvpPresenter;

public interface HistoryMvpPresenter<V extends HistoryMvpView> extends MvpPresenter<V> {

    void getHistoryDetails();
}
