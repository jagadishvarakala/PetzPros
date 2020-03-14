package com.petz.pros.ui.main.contact;

import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.petz.pros.data.DataManager;
import com.petz.pros.data.network.ApiClient;
import com.petz.pros.data.network.ApiInterface;
import com.petz.pros.ui.base.BasePresenter;
import com.petz.pros.ui.main.about.AboutMvpPresenter;
import com.petz.pros.ui.main.about.AboutMvpView;
import com.petz.pros.utils.CommonUtils;
import com.petz.pros.utils.rx.SchedulerProvider;

import java.io.IOException;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactPresenter<V extends ContactMvpView> extends BasePresenter<V>
        implements ContactMvpPresenter<V> {

    @Inject
    public ContactPresenter(DataManager manager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(manager, schedulerProvider, compositeDisposable);
    }


    @Override
    public void validateForm() {

        if(TextUtils.isEmpty(getMvpView().getName())){
            getMvpView().showErrorName("Required");
            return;
        }
        if(TextUtils.isEmpty(getMvpView().getEmail())){
            getMvpView().showErrorEmail("Required");
            return;
        }
        if(!CommonUtils.isValidEmail(getMvpView().getEmail())){
            getMvpView().showErrorEmail("Enter valid email");
            return;
        }
        if(TextUtils.isEmpty(getMvpView().getPhone())){
            getMvpView().showErrorPhone("Required");
            return;
        }
        if(getMvpView().getPhone().length() < 10){
            getMvpView().showErrorPhone("Enter valid mobile number");
            return;
        }
        if(TextUtils.isEmpty(getMvpView().getSubject())){
            getMvpView().showErrorSubject("Required");
            return;
        }

        SendMessageRequest messageRequest = new SendMessageRequest();
        messageRequest.setName(getMvpView().getName());
        messageRequest.setEmail(getMvpView().getEmail());
        messageRequest.setPhone(getMvpView().getPhone());
        messageRequest.setSubject(getMvpView().getSubject());
        messageRequest.setMessage(getMvpView().getMessage());
        submitSendMessage(messageRequest);

    }

    @Override
    public void submitSendMessage(SendMessageRequest sendMessageRequest) {
        if (getMvpView().isNetworkConnected()) {
            //Creating an object of our api interface
            ApiInterface api = ApiClient.getApiService();
            Call<ResponseBody> call = api.sendMessage(sendMessageRequest);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                    if(response.code() == 200) {
                        if (response.isSuccessful()) {
                            //Dismiss Dialog
                            getMvpView().hideLoading();
                            String userId = null;

                            try {
                                getMvpView().showMessage(response.errorBody().string());
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
