package com.petz.pros.ui.main;

import com.petz.pros.data.DataManager;
import com.petz.pros.ui.base.BasePresenter;
import com.petz.pros.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created on : Feb 11, 2019
 * Author     : AndroidWave
 */
public class MainPresenter<V extends MainMvpView> extends BasePresenter<V>
        implements MainMvpPresenter<V> {
    @Inject
    public MainPresenter(DataManager manager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(manager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onViewPrepared() {

    }

    @Override
    public String getUserName() {
        return getDataManager().getUserName();
    }

    @Override
    public String getUserEmail() {
        return getDataManager().getUserEmail();
    }

    @Override
    public void userLogout() {
        getDataManager().setUserLoggedOut();
        getMvpView().navigateUserSelection();
    }
}
