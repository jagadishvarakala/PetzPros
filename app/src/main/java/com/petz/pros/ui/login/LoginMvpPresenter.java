package com.petz.pros.ui.login;

import com.petz.pros.ui.base.MvpPresenter;

public interface LoginMvpPresenter<V extends LoginMvpView> extends MvpPresenter<V> {
    void onLoginClick();

    void onSignUpLink();
}
