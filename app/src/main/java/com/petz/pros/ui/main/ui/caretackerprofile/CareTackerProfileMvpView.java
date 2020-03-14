package com.petz.pros.ui.main.ui.caretackerprofile;

import com.petz.pros.data.network.pojo.RegistrationRequest;
import com.petz.pros.ui.base.MvpView;

import okhttp3.ResponseBody;

public interface CareTackerProfileMvpView extends MvpView {

    void onSuccessProfileUpdate(RegistrationRequest body);

}
