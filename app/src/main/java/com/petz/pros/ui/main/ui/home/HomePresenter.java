package com.petz.pros.ui.main.ui.home;

import com.petz.pros.data.DataManager;
import com.petz.pros.ui.base.BasePresenter;
import com.petz.pros.ui.main.MainMvpPresenter;
import com.petz.pros.ui.main.MainMvpView;
import com.petz.pros.utils.rx.SchedulerProvider;

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

    @Override
    public void onViewPrepared() {
        getMvpView().showLoading();
        getCompositeDisposable().add(getDataManager()
                        .getFeedList()
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(response -> {
                            if (!isViewAttached()) {
                                return;
                            }
                            getMvpView().hideLoading();
                                /**
                                 * Update view here
                                 */
                                getMvpView().updateFeed(response.getData());
                        }, error -> {
                            if (!isViewAttached()) {
                                return;
                            }
                            getMvpView().hideLoading();

                            /**
                             * manage all kind of error in single place
                             */
                            handleApiError(error);
                        }));
    }
}
