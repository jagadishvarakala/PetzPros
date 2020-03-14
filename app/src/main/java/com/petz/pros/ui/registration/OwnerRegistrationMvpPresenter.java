package com.petz.pros.ui.registration;

import com.petz.pros.data.network.pojo.RegistrationRequest;
import com.petz.pros.ui.base.MvpPresenter;

public interface OwnerRegistrationMvpPresenter<V extends OwnerRegistrationMvpView> extends MvpPresenter<V> {

    void petOwnerRegistration(RegistrationRequest registrationRequest);


}
