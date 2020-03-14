package com.petz.pros.ui.main.ui.dashboard;

import androidx.annotation.NonNull;

import com.google.firebase.iid.FirebaseInstanceId;
import com.petz.pros.data.DataManager;
import com.petz.pros.data.network.ApiClient;
import com.petz.pros.data.network.ApiInterface;
import com.petz.pros.data.network.pojo.LoginRequest;
import com.petz.pros.data.network.pojo.RegistrationRequest;
import com.petz.pros.ui.base.BasePresenter;
import com.petz.pros.utils.rx.SchedulerProvider;

import java.io.IOException;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashBoardPresenter<V extends DashBoardMvpView> extends BasePresenter<V>
        implements DashBoardMvpPresenter<V> {
    @Inject
    public DashBoardPresenter(DataManager manager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(manager, schedulerProvider, compositeDisposable);
    }

    @Override
    public String getUserName() {
     return getDataManager().getUserName();
    }

    @Override
    public void onClickPendingServices() {
        getMvpView().onClickPendingService();
    }

    @Override
    public void onClickRejectedServices() {
        getMvpView().onCLickRejectedService();
    }

    @Override
    public void onClickPerServices() {
getMvpView().onClickPetService();
    }

    @Override
    public void onClickUpComing() {
        getMvpView().onClickUpComingService();
    }

    @Override
    public void onClickProfile() {
        getMvpView().onClickProfile();
    }

    @Override
    public void onClickMore() {
        getMvpView().onCLickMore();
    }

    @Override
    public void onClickLogout() {
        getDataManager().logoutUser();
        getMvpView().onClickLogout();
    }

    @Override
    public RegistrationRequest getProfileDetails(boolean isAvailable) {
        RegistrationRequest request = new RegistrationRequest();
        request.setId(getDataManager().getUserId());
        request.setWalkerAvailable(isAvailable);
        request.setUserType(getDataManager().getUserType());
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
            registrationRequest.setFCMDeviceId(FirebaseInstanceId.getInstance().getToken());
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


    @Override
    public void careTackerLogin() {
        if (getMvpView().isNetworkConnected()) {
            //Creating an object of our api interface
            ApiInterface api = ApiClient.getApiService();
            LoginRequest loginRequest = new LoginRequest();
            loginRequest.setmEmailId(getDataManager().getUserEmail());
            loginRequest.setmPassword(getDataManager().getUserPassword());
            loginRequest.setmUserType(getDataManager().getUserType());
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
                            getMvpView().onAvalibulity(response.body().isWalkerAvailable());
                        }
                    }else if(response.code() == 404){
                        getMvpView().hideLoading();
                        try {
                            getMvpView().showMessage(response.errorBody().string());
                            getMvpView().invalidCrediential();
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
}
