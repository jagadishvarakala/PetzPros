package com.petz.pros.ui.registration.caretacker;

import com.petz.pros.data.network.pojo.RegistrationRequest;
import com.petz.pros.ui.base.MvpPresenter;
import com.petz.pros.ui.registration.OwnerRegistrationMvpView;

public interface CareTackerRegistrationMvpPresenter<V extends OwnerRegistrationMvpView> extends MvpPresenter<V> {

    void petOwnerRegistration(RegistrationRequest registrationRequest);


}
