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

import java.io.IOException;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CareTackerRegistrationPresenter<V extends CareTackerRegistrationMvpView> extends BasePresenter<V>
        implements CareTackerRegistrationMvpPresenter<V> {
    @Inject
    public CareTackerRegistrationPresenter(DataManager manager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(manager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void petCareTackerRegistration(RegistrationRequest registrationRequest) {
        if (getMvpView().isNetworkConnected()) {
            getMvpView().showLoading();
            //Creating an object of our api interface
            ApiInterface api = ApiClient.getApiService();
            Call<ResponseBody> call = api.petCareTackerRegistration(registrationRequest);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                    if(response.code() == 200) {
                        if (response.isSuccessful()) {
                            //Dismiss Dialog
                            getMvpView().hideLoading();
                            String userId = null;
                            try {
                                userId = response.body().string();
                                getDataManager().updateUserInfo(Integer.parseInt(userId), registrationRequest.getUserType(), registrationRequest.getFirstName() , registrationRequest.getLastName(),
                                        registrationRequest.getEmailId(), registrationRequest.getPhone(),registrationRequest.getPassword());
                                getDataManager().updateUserAddress(registrationRequest.getAddress(),registrationRequest.getCity(),registrationRequest.getState(),
                                        registrationRequest.getCountry(),registrationRequest.getZipCode());
                                getDataManager().updateServiceList(registrationRequest.getServiceIdList(),registrationRequest.getChargePerHour());
                                getDataManager().updateBankDetails(registrationRequest.getAccountNumber(),registrationRequest.getBankName(),registrationRequest.getBranch(),registrationRequest.getIfscCode());
                                getMvpView().onSuccessRegistration(response.body());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

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
