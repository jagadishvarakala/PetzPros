package com.petz.pros.ui.splash;

import com.petz.pros.ui.base.MvpPresenter;
import com.petz.pros.ui.usertype.UserTypeMvpView;

public interface SplashMvpPresenter<V extends SplashMvpView> extends MvpPresenter<V> {

    boolean isUserLogin();

    String getUserType();
}
