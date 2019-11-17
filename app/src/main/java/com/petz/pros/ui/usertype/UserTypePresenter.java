package com.petz.pros.ui.usertype;

import com.petz.pros.data.DataManager;
import com.petz.pros.ui.base.BasePresenter;
import com.petz.pros.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class UserTypePresenter<V extends UserTypeMvpView> extends BasePresenter<V>
        implements UserTypeMvpPresenter<V> {
    @Inject
    public UserTypePresenter(DataManager manager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(manager, schedulerProvider, compositeDisposable);
    }
}
