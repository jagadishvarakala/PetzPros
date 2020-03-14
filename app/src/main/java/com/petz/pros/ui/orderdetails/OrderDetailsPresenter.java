package com.petz.pros.ui.orderdetails;

import com.petz.pros.data.DataManager;
import com.petz.pros.data.network.ApiClient;
import com.petz.pros.data.network.ApiInterface;
import com.petz.pros.ui.base.BasePresenter;
import com.petz.pros.ui.bookingdetails.BookingModel;
import com.petz.pros.ui.bookingdetails.BookingMvpPresenter;
import com.petz.pros.ui.bookingdetails.BookingMvpView;
import com.petz.pros.ui.bookingdetails.BookingResponse;
import com.petz.pros.utils.rx.SchedulerProvider;

import java.io.IOException;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created on : Feb 11, 2019
 * Author     : AndroidWave
 */
public class OrderDetailsPresenter<V extends OrderDetailsMvpView> extends BasePresenter<V>
        implements OrderDetailsMvpPresenter<V> {
    @Inject
    public OrderDetailsPresenter(DataManager manager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(manager, schedulerProvider, compositeDisposable);
    }


    @Override
    public String getUserCity() {
        return getDataManager().getUserCity();
    }

    @Override
    public String getUserState() {
        return getDataManager().getUserState();
    }
}
