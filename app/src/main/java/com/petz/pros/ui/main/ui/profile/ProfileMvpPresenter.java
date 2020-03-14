package com.petz.pros.ui.main.ui.profile;

import com.petz.pros.data.network.pojo.RegistrationRequest;
import com.petz.pros.ui.base.MvpPresenter;

public interface ProfileMvpPresenter<V extends ProfileMvpView> extends MvpPresenter<V> {

    int getUserId();
    String getUserType();

    int getPetId();

    RegistrationRequest getProfileDetails();

    void petOwnerProfileUpdate(RegistrationRequest registrationRequest);
}
