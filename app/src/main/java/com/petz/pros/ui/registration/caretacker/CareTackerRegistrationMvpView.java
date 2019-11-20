package com.petz.pros.ui.registration.caretacker;

import com.petz.pros.ui.base.MvpView;

import okhttp3.ResponseBody;

public interface CareTackerRegistrationMvpView extends MvpView {

    void onSuccessRegistration(ResponseBody body);
}
