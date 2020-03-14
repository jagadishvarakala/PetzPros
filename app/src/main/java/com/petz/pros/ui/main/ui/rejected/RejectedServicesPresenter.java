package com.petz.pros.ui.main.ui.rejected;

import com.petz.pros.data.DataManager;
import com.petz.pros.data.network.ApiClient;
import com.petz.pros.data.network.ApiInterface;
import com.petz.pros.ui.base.BasePresenter;
import com.petz.pros.ui.main.ui.bookings.BookingsModule;
import com.petz.pros.ui.main.ui.pending.PendingServicesMvpPresenter;
import com.petz.pros.ui.main.ui.pending.PendingServicesMvpView;
import com.petz.pros.utils.rx.SchedulerProvider;

import java.io.IOException;
import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created on : Feb 11, 2019
 * Author     : AndroidWave
 */
public class RejectedServicesPresenter<V extends RejectedServicesMvpView> extends BasePresenter<V>
        implements RejectedServicesMvpPresenter<V> {
    @Inject
    public RejectedServicesPresenter(DataManager manager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(manager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void getBookingsDetails() {
        if (getMvpView().isNetworkConnected()) {
            getMvpView().showLoading();
            //Creating an object of our api interface
            ApiInterface api = ApiClient.getApiService();

            Call<ArrayList<BookingsModule>> call = api.getNotifications(getDataManager().getUserId() );

            call.enqueue(new Callback<ArrayList<BookingsModule>>() {
                @Override
                public void onResponse(Call<ArrayList<BookingsModule>> call, Response<ArrayList<BookingsModule>> response) {

                    if (response.code() == 200) {
                        if (response.isSuccessful()) {
                            //Dismiss Dialog
                            getMvpView().hideLoading();
                            getMvpView().updateBookings(response.body());
                        }
                    }else if(response.code() == 404){
                        getMvpView().hideLoading();
                        try {
                            getMvpView().showNoDataFound(response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }

                @Override
                public void onFailure(Call<ArrayList<BookingsModule>> call, Throwable t) {
                    //Dismiss Dialog
                    getMvpView().hideLoading();
                }
            });
        } else {
            getMvpView().onError("Internet Connection Not Available");

        }
    }
}
