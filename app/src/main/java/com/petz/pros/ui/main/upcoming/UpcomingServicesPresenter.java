package com.petz.pros.ui.main.upcoming;

import com.petz.pros.data.DataManager;
import com.petz.pros.data.network.ApiClient;
import com.petz.pros.data.network.ApiInterface;
import com.petz.pros.ui.base.BasePresenter;
import com.petz.pros.ui.main.ui.bookings.BookingsModule;
import com.petz.pros.ui.main.ui.pending.PendingServicesMvpPresenter;
import com.petz.pros.ui.main.ui.pending.PendingServicesMvpView;
import com.petz.pros.utils.CommonUtils;
import com.petz.pros.utils.rx.SchedulerProvider;

import java.io.IOException;
import java.util.ArrayList;

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
public class UpcomingServicesPresenter<V extends UpcomingServicesMvpView> extends BasePresenter<V>
        implements UpcomingServicesMvpPresenter<V> {
    @Inject
    public UpcomingServicesPresenter(DataManager manager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(manager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void getBookingsDetails() {
        if (getMvpView().isNetworkConnected()) {
            getMvpView().showLoading();
            //Creating an object of our api interface
            ApiInterface api = ApiClient.getApiService();

            Call<ArrayList<ArrayList<BookingsModule>>> call = api.getPastFutureHistory(getDataManager().getUserId() );

            call.enqueue(new Callback<ArrayList<ArrayList<BookingsModule>>>() {
                @Override
                public void onResponse(Call<ArrayList<ArrayList<BookingsModule>>> call, Response<ArrayList<ArrayList<BookingsModule>>> response) {

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
                public void onFailure(Call<ArrayList<ArrayList<BookingsModule>>> call, Throwable t) {
                    //Dismiss Dialog
                    getMvpView().hideLoading();
                }
            });
        } else {
            getMvpView().onError("Internet Connection Not Available");

        }
    }

    @Override
    public boolean isPetOwner() {
        return getDataManager().getUserType().equalsIgnoreCase("pet owner");
    }

    @Override
    public void startService(BookingsModule bookingsModule) {
        if (getMvpView().isNetworkConnected()) {
            getMvpView().showLoading();
            //Creating an object of our api interface
            ApiInterface api = ApiClient.getApiService();
            StartServiceReq startServiceReq = new StartServiceReq();
            startServiceReq.setId(bookingsModule.getId());
            startServiceReq.setBookingStatus("accepted");
            startServiceReq.setPaymentStatus("accepted");
            startServiceReq.setActualStartTime(CommonUtils.getCurrentDateTime());

            Call<ResponseBody> call = api.START_SERVICE_REQ_CALL(startServiceReq );

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                    if (response.code() == 200) {
                        if (response.isSuccessful()) {
                            //Dismiss Dialog
                            getMvpView().hideLoading();
                           getMvpView().onSuccessStartService(bookingsModule);
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
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    //Dismiss Dialog
                    getMvpView().hideLoading();
                }
            });
        } else {
            getMvpView().onError("Internet Connection Not Available");

        }
    }
}
