package com.petz.cleancode.ui.registration;

import com.petz.cleancode.data.DataManager;
import com.petz.cleancode.ui.base.BasePresenter;
import com.petz.cleancode.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class OwnerRegistrationPresenter<V extends OwnerRegistrationMvpView> extends BasePresenter<V>
        implements OwnerRegistrationMvpPresenter<V> {
    @Inject
    public OwnerRegistrationPresenter(DataManager manager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(manager, schedulerProvider, compositeDisposable);
    }
}
