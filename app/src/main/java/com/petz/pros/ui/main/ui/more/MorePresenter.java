package com.petz.pros.ui.main.ui.more;

import com.petz.pros.data.DataManager;
import com.petz.pros.ui.base.BasePresenter;
import com.petz.pros.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class MorePresenter<V extends MoreMvpView> extends BasePresenter<V>
        implements MoreMvpPresenter<V> {

    @Inject
    public MorePresenter(DataManager manager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(manager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onClickAbout() {
        getMvpView().onClickAbout();
    }

    @Override
    public void onClickFAQ() {
        getMvpView().onClickFAQ();
    }

    @Override
    public void onClickContact() {
        getMvpView().onClickContact();
    }

    @Override
    public void onClickTermsConditions() {
        getMvpView().onClickTermsConditions();
    }

    @Override
    public void onClickPrivacy() {
        getMvpView().onClickPrivacy();
    }
}
