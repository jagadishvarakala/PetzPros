package com.petz.pros.ui.registration.personaldetails;

import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.petz.pros.data.DataManager;
import com.petz.pros.data.network.ApiClient;
import com.petz.pros.data.network.ApiInterface;
import com.petz.pros.data.network.pojo.IsUserExitRequest;
import com.petz.pros.data.network.pojo.IsUserExitResponse;
import com.petz.pros.ui.base.BaseFragment;
import com.petz.pros.ui.base.BasePresenter;
import com.petz.pros.utils.Singleton;
import com.petz.pros.utils.rx.SchedulerProvider;

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
public class PersonalPresenter<V extends PersonalMvpView> extends BasePresenter<V>
        implements PersonalMvpPresenter<V> {
    @Inject
    public PersonalPresenter(DataManager manager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(manager, schedulerProvider, compositeDisposable);
    }


    @Override
    public void toValidateEmailPhone(String value,boolean isEmail) {
        if (getMvpView().isNetworkConnected() && !TextUtils.isEmpty(value)) {
            //Creating an object of our api interface
            ApiInterface api = ApiClient.getApiService();
            IsUserExitRequest isUserExitRequest = new IsUserExitRequest();
            isUserExitRequest.setUserName(value);
            isUserExitRequest.setUserType(Singleton.getInstance().selectedUserType);
            Call<IsUserExitResponse> call = api.isUserExit(isUserExitRequest);
            call.enqueue(new Callback<IsUserExitResponse>() {
                @Override
                public void onResponse(@NonNull Call<IsUserExitResponse> call, @NonNull Response<IsUserExitResponse> response) {
                    if (response.isSuccessful() && response.body()!= null) {
                        //Dismiss Dialog
                        getMvpView().hideLoading();
                        if(isEmail)
                            getMvpView().onSuccessEmailResponse(response.body().isStatus());
                        else
                            getMvpView().onSuccessMobileResponse(response.body().isStatus());
                    }
                }

                @Override
                public void onFailure(@NonNull Call<IsUserExitResponse> call, @NonNull Throwable t) {
                    //Dismiss Dialog
                    getMvpView().hideLoading();
                }
            });
        } else {
            getMvpView().onError("Internet Connection Not Available");
        }
    }
}
