package com.petz.pros.ui.main.about;

import com.petz.pros.data.DataManager;
import com.petz.pros.ui.base.BasePresenter;
import com.petz.pros.ui.main.ui.more.MoreMvpPresenter;
import com.petz.pros.ui.main.ui.more.MoreMvpView;
import com.petz.pros.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class AboutPresenter<V extends AboutMvpView> extends BasePresenter<V>
        implements AboutMvpPresenter<V> {

    @Inject
    public AboutPresenter(DataManager manager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(manager, schedulerProvider, compositeDisposable);
    }


}
