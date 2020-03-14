package com.petz.pros.ui.splash;

import com.petz.pros.data.DataManager;
import com.petz.pros.ui.base.BasePresenter;
import com.petz.pros.ui.usertype.UserTypeMvpPresenter;
import com.petz.pros.ui.usertype.UserTypeMvpView;
import com.petz.pros.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class SplashPresenter<V extends SplashMvpView> extends BasePresenter<V>
        implements SplashMvpPresenter<V> {
    @Inject
    public SplashPresenter(DataManager manager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(manager, schedulerProvider, compositeDisposable);
    }

    @Override
    public boolean isUserLogin() {
        return getDataManager().getUserId() != 0;
    }

    @Override
    public String getUserType() {
        return getDataManager().getUserType();
    }
}
