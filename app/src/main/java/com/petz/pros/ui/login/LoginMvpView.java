package com.petz.pros.ui.login;

import com.petz.pros.ui.base.MvpView;

import okhttp3.ResponseBody;

public interface LoginMvpView extends MvpView {
    void onLoginSuccess(ResponseBody response);

    String getEmail();

    String getPassword();

    String getUserType();

    void showInputEmailError(String errorMessage);

    void showInputPasswordError(String errorMessage);

    void setPassword(String userId);

    void setEmail(String password);
}
