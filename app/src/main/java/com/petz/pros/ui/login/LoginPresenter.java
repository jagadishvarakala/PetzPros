package com.petz.pros.ui.login;

import android.os.Handler;

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
            }else if(getMvpView().getPassword().trim().length() > 4){
                getMvpView().showInputPasswordError("password more then 4 characters");
            } else {
                getMvpView().showLoading();
                new Handler().postDelayed(() -> {
                    if (!isViewAttached()) {
                        return;
                    }
                    showUsers();
                    // set demo user
//                    UserProfile mProfile = new UserProfile();
//                    mProfile.setFirstName("Dinesh");
//                    mProfile.setLastName("Kumar");
//                    mProfile.setEmail("dinesh@gmail.com");
//                    mProfile.setUserId("1");
//                    //update preferences
//                    getDataManager().updateUserInfo("access toekn", 1l, LoggedInMode.LOGGED_IN_MODE_SERVER, "", mProfile.getEmail(), "");
//                    getMvpView().onLoginSuccess(mProfile);
//
//                    getMvpView().hideLoading();
                }, 1000);

            }

        }


    }

    @Override
    public void onSignUpLink() {

    }

    private void showUsers() {


        if (getMvpView().isNetworkConnected()) {


            //Creating an object of our api interface
            ApiInterface api = ApiClient.getApiService();
            LoginRequest loginRequest = new LoginRequest();
            loginRequest.setmEmailId(getMvpView().getEmail());
            loginRequest.setmPassword(getMvpView().getPassword());
            loginRequest.setmUserType(getMvpView().getUserType());

            Call<ResponseBody> call = api.userLogin(loginRequest );

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()) {
                        //Dismiss Dialog
                        getMvpView().hideLoading();
                        getMvpView().onLoginSuccess(response.body());
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
