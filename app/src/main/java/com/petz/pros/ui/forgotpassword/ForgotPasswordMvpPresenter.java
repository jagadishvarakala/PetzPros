package com.petz.pros.ui.forgotpassword;

import com.petz.pros.ui.base.MvpPresenter;

public interface ForgotPasswordMvpPresenter<V extends ForgotPasswordMvpView> extends MvpPresenter<V> {

    void validateForm();

    void submitForm();
}
