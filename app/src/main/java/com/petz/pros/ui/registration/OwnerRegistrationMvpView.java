package com.petz.pros.ui.registration;

import com.petz.pros.ui.base.MvpView;

import okhttp3.ResponseBody;

public interface OwnerRegistrationMvpView extends MvpView {

    void onSuccessRegistration(ResponseBody body);
}
