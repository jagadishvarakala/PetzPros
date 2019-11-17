package com.petz.pros.ui.registration;

import com.petz.pros.data.DataManager;
import com.petz.pros.ui.base.BasePresenter;
import com.petz.pros.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class OwnerRegistrationPresenter<V extends OwnerRegistrationMvpView> extends BasePresenter<V>
        implements OwnerRegistrationMvpPresenter<V> {
    @Inject
    public OwnerRegistrationPresenter(DataManager manager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(manager, schedulerProvider, compositeDisposable);
    }
}
