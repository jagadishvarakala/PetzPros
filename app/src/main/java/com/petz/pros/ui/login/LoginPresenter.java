package com.petz.pros.ui.login;

import android.os.Handler;

import com.google.firebase.iid.FirebaseInstanceId;
import com.petz.pros.data.DataManager;
import com.petz.pros.data.network.ApiClient;
import com.petz.pros.data.network.ApiInterface;
import com.petz.pros.data.network.pojo.LoginRequest;
import com.petz.pros.data.network.pojo.RegistrationRequest;
import com.petz.pros.ui.base.BasePresenter;
import com.petz.pros.utils.CommonUtils;
import com.petz.pros.utils.rx.SchedulerProvider;

import java.io.IOException;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter<V extends LoginMvpView> extends BasePresenter<V>
        implements LoginMvpPresenter<V> {
    @Inject
    public LoginPresenter(DataManager manager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(manager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onLoginClick() {
        if (getMvpView() != null) {
            if (getMvpView().getEmail().trim().equals("") ) {
                getMvpView().showInputEmailError("Email cannot be Blank");
            }else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(getMvpView().getEmail()).matches()){
                getMvpView().showInputEmailError("Invalid Email");
            }else if(getMvpView().getPassword().trim().equals("")){
                getMvpView().showInputPasswordError("Password cannot be Blank");
            }
//            else if(!CommonUtils.validatePassword(getMvpView().getPassword().trim())){
//                getMvpView().showInputPasswordError("Password must contain at least four characters, including uppercase, lowercase letters and numbers");
//            }
            else {
                getMvpView().showLoading();
                if(getMvpView().getUserType().equalsIgnoreCase("pet owner")){
                    ownerLogin();
                }else{
                    careTackerLogin();
                }
            }

        }


    }

    @Override
    public void onSignUpLink() {

    }

    @Override
    public void ownerLogin() {
        if (getMvpView().isNetworkConnected()) {
            //Creating an object of our api interface
            ApiInterface api = ApiClient.getApiService();
            LoginRequest loginRequest = new LoginRequest();
            loginRequest.setmEmailId(getMvpView().getEmail());
            loginRequest.setmPassword(getMvpView().getPassword());
            loginRequest.setmUserType(getMvpView().getUserType());
            loginRequest.setFCMDeviceId(FirebaseInstanceId.getInstance().getToken());
            getMvpView().showLoading();
            Call<RegistrationRequest> call = api.ownerLogin(loginRequest );

            call.enqueue(new Callback<RegistrationRequest>() {
                @Override
                public void onResponse(Call<RegistrationRequest> call, Response<RegistrationRequest> response) {

                    if (response.code() == 200) {
                        if (response.isSuccessful()) {
                            //Dismiss Dialog
                            getMvpView().hideLoading();
                            getDataManager().updateUserInfo(response.body().getId(), response.body().getUserType(), response.body().getFirstName() , response.body().getLastName(),
                                    response.body().getEmailId(), response.body().getPhone(),response.body().getPassword());
                            getDataManager().updateUserAddress(response.body().getAddress(),response.body().getCity(),response.body().getState(),
                                    response.body().getCountry(),response.body().getZipCode());
                            getDataManager().updatePetDetails(response.body().getPetDetails().getId(),response.body().getPetDetails().getType(),response.body().getPetDetails().getName(),
                                    response.body().getPetDetails().getBreed(),response.body().getPetDetails().getAgeInYears(),response.body().getPetDetails().isFriendly(),response.body().getPetDetails().getAbout());

                            getMvpView().onLoginSuccess("Successfully Login");
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
                public void onFailure(Call<RegistrationRequest> call, Throwable t) {
                    //Dismiss Dialog
                    getMvpView().hideLoading();
                }
            });
        } else {
            getMvpView().onError("Internet Connection Not Available");

        }
    }

    @Override
    public void careTackerLogin() {
        if (getMvpView().isNetworkConnected()) {
            //Creating an object of our api interface
            ApiInterface api = ApiClient.getApiService();
            LoginRequest loginRequest = new LoginRequest();
            loginRequest.setmEmailId(getMvpView().getEmail());
            loginRequest.setmPassword(getMvpView().getPassword());
            loginRequest.setmUserType(getMvpView().getUserType());
            loginRequest.setFCMDeviceId(FirebaseInstanceId.getInstance().getToken());
            getMvpView().showLoading();
            Call<RegistrationRequest> call = api.careTackerLogin(loginRequest );

            call.enqueue(new Callback<RegistrationRequest>() {
                @Override
                public void onResponse(Call<RegistrationRequest> call, Response<RegistrationRequest> response) {

                    if (response.code() == 200) {
                        if (response.isSuccessful()) {
                            //Dismiss Dialog
                            getMvpView().hideLoading();
                            getDataManager().updateUserInfo(response.body().getId(), response.body().getUserType(), response.body().getFirstName() , response.body().getLastName(),
                                    response.body().getEmailId(), response.body().getPhone(),response.body().getPassword());
                            getDataManager().updateUserAddress(response.body().getAddress(),response.body().getCity(),response.body().getState(),
                                    response.body().getCountry(),response.body().getZipCode());
                            getDataManager().updateServiceList(response.body().getServiceIdList(),response.body().getChargePerHour());
                            getDataManager().updateBankDetails(response.body().getAccountNumber(),response.body().getBankName(),response.body().getBranch(),response.body().getIfscCode());
                            getMvpView().onLoginCareTaker("Successfully Login");
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
                public void onFailure(Call<RegistrationRequest> call, Throwable t) {
                    //Dismiss Dialog
                    getMvpView().hideLoading();
                }
            });
        } else {
            getMvpView().onError("Internet Connection Not Available");

        }
    }

    private void showUsers() {

    }
}
