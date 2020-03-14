package com.petz.pros.ui.caretackerlist;

import android.text.TextUtils;

import com.petz.pros.data.DataManager;
import com.petz.pros.data.network.ApiClient;
import com.petz.pros.data.network.ApiInterface;
import com.petz.pros.data.network.pojo.LoginRequest;
import com.petz.pros.data.network.pojo.RegistrationRequest;
import com.petz.pros.ui.base.BasePresenter;
import com.petz.pros.ui.main.MainMvpPresenter;
import com.petz.pros.ui.main.MainMvpView;
import com.petz.pros.utils.rx.SchedulerProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created on : Feb 11, 2019
 * Author     : AndroidWave
 */
public class CareTackerListPresenter<V extends CareTackerMvpView> extends BasePresenter<V>
        implements CareTackerListMvpPresenter<V> {
    @Inject
    public CareTackerListPresenter(DataManager manager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(manager, schedulerProvider, compositeDisposable);
    }


    @Override
    public void validateServiceDetails(int serviceId) {
        if(TextUtils.isEmpty(getMvpView().getServiceDate())){
            getMvpView().showErrorMessageDate("Select Date");
        }else if(TextUtils.isEmpty(getMvpView().getStartTime())){
            getMvpView().showErrorMessageStartTime("Select Start Time");
        }else if(TextUtils.isEmpty(getMvpView().getEndTime())){
            getMvpView().showErrorMessageEndTime("Select End Time");
        }else {
            getCareTackers(serviceId);
        }
    }

    @Override
    public void getCareTackers(int serviceId) {
        if (getMvpView().isNetworkConnected()) {
            getMvpView().showLoading();
            //Creating an object of our api interface
            ApiInterface api = ApiClient.getApiService();
            CareTackersRequest careTackersRequest = new CareTackersRequest();
            ArrayList<Integer> arrayList = new ArrayList<>();
            arrayList.add(serviceId);
            careTackersRequest.setmServiceIdList(arrayList);
            careTackersRequest.setmZipCode(getDataManager().getUserZipCode());
            Call<List<CareTackersModel>> call = api.availableCareTackers(careTackersRequest );

            call.enqueue(new Callback<List<CareTackersModel>>() {
                @Override
                public void onResponse(Call<List<CareTackersModel>> call, Response<List<CareTackersModel>> response) {

                    if (response.code() == 200) {
                        if (response.isSuccessful()) {
                            //Dismiss Dialog
                            getMvpView().hideLoading();
                            getMvpView().updateAvailableCareTackers(response.body());
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
                public void onFailure(Call<List<CareTackersModel>> call, Throwable t) {
                    //Dismiss Dialog
                    getMvpView().hideLoading();
                }
            });
        } else {
            getMvpView().onError("Internet Connection Not Available");

        }
    }

    @Override
    public int getOwnerId() {
        return getDataManager().getUserId();
    }

    @Override
    public String getOwnerName() {
        return getDataManager().getUserName();
    }

    @Override
    public String getOwnerPhone() {
        return getDataManager().getUserMobile();
    }

    @Override
    public String getOwnerAddress() {
        return getDataManager().getUserAddress();
    }
}
