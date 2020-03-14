package com.petz.pros.ui.main.ui.home;

import com.petz.pros.R;
import com.petz.pros.data.DataManager;
import com.petz.pros.data.network.pojo.FeedItem;
import com.petz.pros.ui.base.BasePresenter;
import com.petz.pros.ui.main.MainMvpPresenter;
import com.petz.pros.ui.main.MainMvpView;
import com.petz.pros.utils.rx.SchedulerProvider;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created on : Feb 11, 2019
 * Author     : AndroidWave
 */
public class HomePresenter<V extends HomeMvpView> extends BasePresenter<V>
        implements HomeMvpPresenter<V> {
    @Inject
    public HomePresenter(DataManager manager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(manager, schedulerProvider, compositeDisposable);
    }



}
