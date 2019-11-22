package com.petz.pros.ui.main.ui.home;

import com.petz.pros.ui.base.MvpPresenter;

public interface HomeMvpPresenter<V extends HomeMvpView> extends MvpPresenter<V> {
    void onViewPrepared();
}
