package com.petz.pros.ui.forgotpassword;

import android.text.TextUtils;

import com.petz.pros.data.DataManager;
import com.petz.pros.data.network.ApiClient;
import com.petz.pros.data.network.ApiInterface;
import com.petz.pros.data.network.pojo.LoginRequest;
import com.petz.pros.ui.base.BasePresenter;
import com.petz.pros.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordPresenter<V extends ForgotPasswordMvpView> extends BasePresenter<V>
        implements ForgotPasswordMvpPresenter<V> {
    @Inject
    public ForgotPasswordPresenter(DataManager manager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(manager, schedulerProvider, compositeDisposable);
    }


    @Override
    public void validateForm() {
           if(TextUtils.isEmpty(getMvpView().getInputEmail())){
               getMvpView().showInputEmailError("Email cannot be Blank");
           }else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(getMvpView().getInputEmail()).matches()){
               getMvpView().showInputEmailError("Invalid Email");
           }else {
               if(getMvpView().getUserType().equalsIgnoreCase("pet owner")){
                   ownerForgotPassword();
               }else{
                   careTackerForgotPassword();
               }
           }
    }

    @Override
    public void ownerForgotPassword() {
        if (getMvpView().isNetworkConnected()) {
            getMvpView().showLoading();
            //Creating an object of our api interface
            ApiInterface api = ApiClient.getApiService();
            LoginRequest loginRequest = new LoginRequest();
            loginRequest.setmEmailId(getMvpView().getInputEmail());
            loginRequest.setmPassword(null);
            loginRequest.setmUserType(getMvpView().getUserType());

            Call<ResponseBody> call = api.petOwnerForgotPassword(loginRequest );

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.code() == 200) {
                        //Dismiss Dialog
                        getMvpView().hideLoading();
                        getMvpView().onForgotPasswordSuccess(response.body());
                    }else if(response.code() == 404){
                        //Dismiss Dialog
                        getMvpView().hideLoading();
                        getMvpView().showMessage("Could not send email, Please try again !");
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

    @Override
    public void careTackerForgotPassword() {
        if (getMvpView().isNetworkConnected()) {
            getMvpView().showLoading();
            //Creating an object of our api interface
            ApiInterface api = ApiClient.getApiService();
            LoginRequest loginRequest = new LoginRequest();
            loginRequest.setmEmailId(getMvpView().getInputEmail());
            loginRequest.setmPassword(null);
            loginRequest.setmUserType(getMvpView().getUserType());

            Call<ResponseBody> call = api.petCareTackerForgotPassword(loginRequest );

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.code() == 200) {
                        //Dismiss Dialog
                        getMvpView().hideLoading();
                        getMvpView().onForgotPasswordSuccess(response.body());
                    }else if(response.code() == 404){
                        //Dismiss Dialog
                        getMvpView().hideLoading();
                        getMvpView().showMessage("Could not send email, Please try again !");
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
