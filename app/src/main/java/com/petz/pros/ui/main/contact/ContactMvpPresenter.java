package com.petz.pros.ui.main.contact;

import com.petz.pros.ui.base.MvpPresenter;
import com.petz.pros.ui.main.about.AboutMvpView;

public interface ContactMvpPresenter<V extends ContactMvpView> extends MvpPresenter<V> {

    void validateForm();

    void submitSendMessage(SendMessageRequest sendMessageRequest);

}
