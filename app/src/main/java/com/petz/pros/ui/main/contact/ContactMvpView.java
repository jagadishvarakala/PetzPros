package com.petz.pros.ui.main.contact;

import com.petz.pros.ui.base.MvpView;

public interface ContactMvpView extends MvpView {

    String getName();

    String getEmail();

    String getPhone();

    String getSubject();

    String getMessage();

    void showErrorName(String message);

    void showErrorEmail(String message);

    void showErrorPhone(String message);

    void showErrorSubject(String message);

}
