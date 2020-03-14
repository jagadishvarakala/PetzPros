package com.petz.pros.ui.main.ui.caretackerprofile;

import com.petz.pros.data.network.pojo.RegistrationRequest;
import com.petz.pros.ui.base.MvpPresenter;
import com.petz.pros.ui.main.ui.profile.ProfileMvpView;

public interface CareTackerProfileMvpPresenter<V extends CareTackerProfileMvpView> extends MvpPresenter<V> {

    int getUserId();
    String getUserType();

    int getPetId();

    RegistrationRequest getProfileDetails();

    void petOwnerProfileUpdate(RegistrationRequest registrationRequest);
}
