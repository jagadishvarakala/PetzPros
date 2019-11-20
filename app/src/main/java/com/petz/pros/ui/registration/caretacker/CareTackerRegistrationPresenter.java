package com.petz.pros.ui.registration.caretacker;

import androidx.annotation.NonNull;

import com.petz.pros.data.DataManager;
import com.petz.pros.data.network.ApiClient;
import com.petz.pros.data.network.ApiInterface;
import com.petz.pros.data.network.pojo.RegistrationRequest;
import com.petz.pros.ui.base.BasePresenter;
import com.petz.pros.ui.registration.OwnerRegistrationMvpPresenter;
import com.petz.pros.ui.registration.OwnerRegistrationMvpView;
import com.petz.pros.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CareTackerRegistrationPresenter<V extends OwnerRegistrationMvpView> extends BasePresenter<V>
        implements OwnerRegistrationMvpPresenter<V> {
    @Inject
    public CareTackerRegistrationPresenter(DataManager manager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(manager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void petOwnerRegistration(RegistrationRequest registrationRequest) {
        if (getMvpView().isNetworkConnected()) {
            //Creating an object of our api interface
            ApiInterface api = ApiClient.getApiService();
            Call<ResponseBody> call = api.petOwnerRegistration(registrationRequest);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                    if (response.isSuccessful()) {
                        //Dismiss Dialog
                        getMvpView().hideLoading();
                        getMvpView().onSuccessRegistration(response.body());
                    }
                }

                @Override
                public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                    //Dismiss Dialog
                    getMvpView().hideLoading();
                }
            });
        } else {
            getMvpView().onError("Internet Connection Not Available");
        }
    }
}
