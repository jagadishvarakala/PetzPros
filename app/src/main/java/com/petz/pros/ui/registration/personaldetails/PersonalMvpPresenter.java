package com.petz.pros.ui.registration.personaldetails;

import com.petz.pros.ui.base.MvpPresenter;
import com.petz.pros.ui.main.ui.home.HomeMvpView;

public interface PersonalMvpPresenter<V extends PersonalMvpView> extends MvpPresenter<V> {
    void toValidateEmailPhone(String value, boolean isEmail);
}
