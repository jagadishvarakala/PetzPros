package com.petz.pros.ui.forgotpassword;

import com.petz.pros.ui.base.MvpView;

import okhttp3.ResponseBody;

public interface ForgotPasswordMvpView extends MvpView {

    String getInputEmail();

    String getUserType();

    void showInputEmailError(String message);

    void onForgotPasswordSuccess(ResponseBody message);
}
