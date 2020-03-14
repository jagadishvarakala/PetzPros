package com.petz.pros.ui.bookingdetails;

import com.petz.pros.data.DataManager;
import com.petz.pros.data.network.ApiClient;
import com.petz.pros.data.network.ApiInterface;
import com.petz.pros.ui.base.BasePresenter;
import com.petz.pros.ui.caretackerlist.CareTackerListMvpPresenter;
import com.petz.pros.ui.caretackerlist.CareTackerMvpView;
import com.petz.pros.ui.caretackerlist.CareTackersModel;
import com.petz.pros.ui.caretackerlist.CareTackersRequest;
import com.petz.pros.utils.rx.SchedulerProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created on : Feb 11, 2019
 * Author     : AndroidWave
 */
public class BookingPresenter<V extends BookingMvpView> extends BasePresenter<V>
        implements BookingMvpPresenter<V> {
    @Inject
    public BookingPresenter(DataManager manager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(manager, schedulerProvider, compositeDisposable);
    }


    @Override
    public void onClickConfirmBooking(BookingModel bookingModel) {
        if (getMvpView().isNetworkConnected()) {
            getMvpView().showLoading();
            //Creating an object of our api interface
            ApiInterface api = ApiClient.getApiService();

            Call<BookingResponse> call = api.bookingConfirm(bookingModel );

            call.enqueue(new Callback<BookingResponse>() {
                @Override
                public void onResponse(Call<BookingResponse> call, Response<BookingResponse> response) {

                    if (response.code() == 200) {
                        if (response.isSuccessful()) {
                            //Dismiss Dialog
                            getMvpView().hideLoading();
                            getMvpView().bookingConfirmSuccess(response.body());

                        }
                    }else if(response.code() == 404){
                        getMvpView().hideLoading();
                        try {
                            getMvpView().showMessage(response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }

                @Override
                public void onFailure(Call<BookingResponse> call, Throwable t) {
                    //Dismiss Dialog
                    getMvpView().hideLoading();
                }
            });
        } else {
            getMvpView().onError("Internet Connection Not Available");

        }
    }
}
