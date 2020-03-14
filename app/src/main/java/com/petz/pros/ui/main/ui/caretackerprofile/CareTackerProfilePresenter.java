package com.petz.pros.ui.main.ui.caretackerprofile;

import androidx.annotation.NonNull;

import com.petz.pros.data.DataManager;
import com.petz.pros.data.network.ApiClient;
import com.petz.pros.data.network.ApiInterface;
import com.petz.pros.data.network.pojo.RegistrationRequest;
import com.petz.pros.ui.base.BasePresenter;
import com.petz.pros.ui.main.ui.profile.ProfileMvpPresenter;
import com.petz.pros.ui.main.ui.profile.ProfileMvpView;
import com.petz.pros.utils.rx.SchedulerProvider;

import java.io.IOException;

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
public class CareTackerProfilePresenter<V extends CareTackerProfileMvpView> extends BasePresenter<V>
        implements CareTackerProfileMvpPresenter<V> {
    @Inject
    public CareTackerProfilePresenter(DataManager manager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(manager, schedulerProvider, compositeDisposable);
    }


    @Override
    public int getUserId() {
        return getDataManager().getUserId();
    }

    @Override
    public String getUserType() {
        return getDataManager().getUserType();
    }

    @Override
    public int getPetId() {
        return getDataManager().getPetId();
    }

    @Override
    public RegistrationRequest getProfileDetails() {
        RegistrationRequest request = new RegistrationRequest();
        request.setId(getDataManager().getUserId());
        request.setFirstName(getDataManager().getUserFirstName());
        request.setLastName(getDataManager().getUserLastName());
        request.setEmailId(getDataManager().getUserEmail());
        request.setPhone(getDataManager().getUserMobile());
        request.setPassword(getDataManager().getUserPassword());
        request.setConfirmPassword(getDataManager().getUserPassword());
        request.setAddress(getDataManager().getUserAddress());
        request.setCity(getDataManager().getUserCity());
        request.setState(getDataManager().getUserState());
        request.setCountry(getDataManager().getUserCountry());
        request.setZipCode(getDataManager().getUserZipCode());
        request.setServiceIdList(getDataManager().getCareTakerServices());
        request.setChargePerHour(getDataManager().getChargePerHour());
        request.setBankName(getDataManager().getBankName());
        request.setBranch(getDataManager().getBankBranch());
        request.setAccountNumber(getDataManager().getBankAccount());
        request.setIfscCode(getDataManager().getBankIFSCCode());
        RegistrationRequest.PetDetails petDetails = new RegistrationRequest.PetDetails();
        petDetails.setId(getDataManager().getPetId());
        petDetails.setType(getDataManager().getPetType());
        petDetails.setName(getDataManager().getPetName());
        petDetails.setBreed(getDataManager().getPetBreed());
        petDetails.setAgeInYears(getDataManager().getPetAge());
        petDetails.setIsFriendly(getDataManager().getPetIsFriendly());
        petDetails.setAbout(getDataManager().getPetAbout());
        petDetails.setUserId(getDataManager().getUserId());
        request.setPetDetails(petDetails);
        return request;
    }

    @Override
    public void petOwnerProfileUpdate(RegistrationRequest registrationRequest) {
        if (getMvpView().isNetworkConnected()) {
            getMvpView().showLoading();
            //Creating an object of our api interface
            ApiInterface api = ApiClient.getApiService();
            Call<RegistrationRequest> call = api.petcaretakerProfileUpdate(registrationRequest);
            call.enqueue(new Callback<RegistrationRequest>() {
                @Override
                public void onResponse(@NonNull Call<RegistrationRequest> call, @NonNull Response<RegistrationRequest> response) {
                    if(response.code() == 200) {
                        if (response.isSuccessful()) {
                            //Dismiss Dialog
                            getMvpView().hideLoading();
                            getDataManager().updateUserInfo(response.body().getId(), registrationRequest.getUserType(), registrationRequest.getFirstName() , registrationRequest.getLastName(),
                                    registrationRequest.getEmailId(), registrationRequest.getPhone(),registrationRequest.getPassword());
                            getDataManager().updateUserAddress(registrationRequest.getAddress(),registrationRequest.getCity(),registrationRequest.getState(),
                                    registrationRequest.getCountry(),registrationRequest.getZipCode());
                            getDataManager().updateServiceList(registrationRequest.getServiceIdList(),registrationRequest.getChargePerHour());
                            getDataManager().updateBankDetails(registrationRequest.getAccountNumber(),registrationRequest.getBankName(),registrationRequest.getBranch(),registrationRequest.getIfscCode());
                            getMvpView().onSuccessProfileUpdate(response.body());

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
                public void onFailure(@NonNull Call<RegistrationRequest> call, @NonNull Throwable t) {
                    //Dismiss Dialog
                    getMvpView().hideLoading();
                }
            });
        } else {
            getMvpView().onError("Internet Connection Not Available");
        }
    }
}
