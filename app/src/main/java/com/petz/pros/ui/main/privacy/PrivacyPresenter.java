package com.petz.pros.ui.main.privacy;

import com.petz.pros.data.DataManager;
import com.petz.pros.ui.base.BasePresenter;
import com.petz.pros.ui.main.about.AboutMvpPresenter;
import com.petz.pros.ui.main.about.AboutMvpView;
import com.petz.pros.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class PrivacyPresenter<V extends PrivacyMvpView> extends BasePresenter<V>
        implements PrivacyMvpPresenter<V> {

    @Inject
    public PrivacyPresenter(DataManager manager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(manager, schedulerProvider, compositeDisposable);
    }


}
