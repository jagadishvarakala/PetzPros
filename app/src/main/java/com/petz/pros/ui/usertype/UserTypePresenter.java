package com.petz.cleancode.ui.usertype;

import com.petz.cleancode.data.DataManager;
import com.petz.cleancode.ui.base.BasePresenter;
import com.petz.cleancode.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class UserTypePresenter<V extends UserTypeMvpView> extends BasePresenter<V>
        implements UserTypeMvpPresenter<V> {
    @Inject
    public UserTypePresenter(DataManager manager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(manager, schedulerProvider, compositeDisposable);
    }
}
