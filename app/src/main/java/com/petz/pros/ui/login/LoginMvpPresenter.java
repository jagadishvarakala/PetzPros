package com.petz.cleancode.ui.login;

import com.petz.cleancode.ui.base.MvpPresenter;

public interface LoginMvpPresenter<V extends LoginMvpView> extends MvpPresenter<V> {
    void onLoginClick();

    void onSignUpLink();
}
