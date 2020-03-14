package com.petz.pros.ui.main.ui.profile;

import com.petz.pros.data.network.pojo.RegistrationRequest;
import com.petz.pros.ui.base.MvpView;

import okhttp3.ResponseBody;

public interface ProfileMvpView extends MvpView {

    void onSuccessProfileUpdate(RegistrationRequest body);

}
