package com.petz.pros.ui.main.faq;

import com.petz.pros.data.DataManager;
import com.petz.pros.ui.base.BasePresenter;
import com.petz.pros.ui.main.about.AboutMvpPresenter;
import com.petz.pros.ui.main.about.AboutMvpView;
import com.petz.pros.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class FaqPresenter<V extends FaqMvpView> extends BasePresenter<V>
        implements FaqMvpPresenter<V> {

    @Inject
    public FaqPresenter(DataManager manager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(manager, schedulerProvider, compositeDisposable);
    }


}
