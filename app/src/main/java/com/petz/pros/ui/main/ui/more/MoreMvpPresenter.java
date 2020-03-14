package com.petz.pros.ui.main.ui.more;

import com.petz.pros.ui.base.MvpPresenter;

public interface MoreMvpPresenter <V extends MoreMvpView> extends MvpPresenter<V> {

    void onClickAbout();

    void onClickFAQ();

    void onClickContact();

    void onClickTermsConditions();

    void onClickPrivacy();
}
