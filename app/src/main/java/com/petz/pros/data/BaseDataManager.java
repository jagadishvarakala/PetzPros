package com.petz.pros.data;

import android.content.Context;

import com.petz.pros.data.db.AppDatabase;
import com.petz.pros.data.db.model.User;
import com.petz.pros.data.network.RestApiHelper;
import com.petz.pros.data.network.pojo.FeedItem;
import com.petz.pros.data.network.pojo.LoginRequest;
import com.petz.pros.data.network.pojo.UserProfile;
import com.petz.pros.data.network.pojo.WrapperResponse;
import com.petz.pros.data.prefs.PreferencesHelper;
import com.petz.pros.data.utils.LoggedInMode;
import com.petz.pros.di.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class BaseDataManager implements DataManager {
    private static final String TAG = "BaseDataManager";


    private final Context mContext;
    private final AppDatabase mDatabase;
    private final PreferencesHelper mPreferencesHelper;
    private final RestApiHelper mApiHelper;

    @Inject
    public BaseDataManager(@ApplicationContext Context context,
                           AppDatabase database,
                           PreferencesHelper preferencesHelper,
                           RestApiHelper apiHelper) {
        mContext = context;
        mDatabase = database;
        mPreferencesHelper = preferencesHelper;
        mApiHelper = apiHelper;
    }

    @Override
    public void updateApiHeader(Long userId, String accessToken) {

    }

    @Override
    public void setUserLoggedOut() {
        logoutUser();
    }

    @Override
    public void updateUserInfo(int userId,String userType,
                               String firstname,
                               String lastname, String email, String mobile,String password) {
        mPreferencesHelper.setUserId(userId);
        mPreferencesHelper.setUserType(userType);
        mPreferencesHelper.setUserFirstName(firstname);
        mPreferencesHelper.setUserLastName(lastname);
        mPreferencesHelper.setUserName(firstname +" "+ lastname);
        mPreferencesHelper.setUserEmail(email);
        mPreferencesHelper.setUserMobile(mobile);
        mPreferencesHelper.setUserPassword(password);
    }

    @Override
    public void updateUserAddress(String address, String city, String state, String country, int zipcode) {
        mPreferencesHelper.setUserAddress(address);
        mPreferencesHelper.setUserCity(city);
        mPreferencesHelper.setUserCountry(country);
        mPreferencesHelper.setUserState(state);
        mPreferencesHelper.setUserZipCode(zipcode);
    }

    @Override
    public void updatePetDetails(int id,String type, String name, String breed, int age, boolean isFriendly, String about) {
        mPreferencesHelper.setPetId(id);
        mPreferencesHelper.setPetAbout(about);
        mPreferencesHelper.setPetIsFriendly(isFriendly);
        mPreferencesHelper.setPetAgeInYears(age);
        mPreferencesHelper.setPetBreed(breed);
        mPreferencesHelper.setPetName(name);
        mPreferencesHelper.setPetType(type);
    }

    @Override
    public void updateServiceList(ArrayList<Integer> serviceList, int chargePerHour) {
        mPreferencesHelper.setCareTackerServices(serviceList);
        mPreferencesHelper.setChargesPerHour(chargePerHour);
    }

    @Override
    public void updateBankDetails(String bankAccount, String bankName, String bankBranch, String bankIFSCCode) {
        mPreferencesHelper.setBankAccount(bankAccount);
        mPreferencesHelper.setBankName(bankName);
        mPreferencesHelper.setBankBranch(bankBranch);
        mPreferencesHelper.setBankIFSCCode(bankIFSCCode);
    }


    @Override
    public int getUserLoggedInMode() {
        return mPreferencesHelper.getUserLoggedInMode();
    }

    @Override
    public void setUserLoggedIn(LoggedInMode mode) {
        mPreferencesHelper.setUserLoggedIn(mode);
    }

    @Override
    public int getUserId() {
        return mPreferencesHelper.getUserId();
    }

    @Override
    public void setUserId(int userId) {
        mPreferencesHelper.setUserId(userId);
    }

    @Override
    public void setUserType(String userType) {
        mPreferencesHelper.setUserType(userType);
    }

    @Override
    public String getUserType() {
        return mPreferencesHelper.getUserType();
    }

    @Override
    public String getUserFirstName() {
        return mPreferencesHelper.getUserFirstName();
    }

    @Override
    public void setUserFirstName(String name) {
        mPreferencesHelper.setUserFirstName(name);
    }

    @Override
    public String getUserLastName() {
        return mPreferencesHelper.getUserLastName();
    }

    @Override
    public void setUserLastName(String name) {
            mPreferencesHelper.setUserLastName(name);
    }

    @Override
    public String getUserName() {
        return mPreferencesHelper.getUserName();
    }

    @Override
    public void setUserName(String userName) {
        mPreferencesHelper.setUserName(userName);
    }

    @Override
    public String getUserEmail() {
        return mPreferencesHelper.getUserEmail();
    }

    @Override
    public void setUserEmail(String email) {
        mPreferencesHelper.setUserEmail(email);
    }

    @Override
    public String getAccessToken() {
        return mPreferencesHelper.getAccessToken();
    }

    @Override
    public void setAccessToken(String accessToken) {
        mPreferencesHelper.getAccessToken();
    }

    @Override
    public String getUserMobile() {
        return mPreferencesHelper.getUserMobile();
    }

    @Override
    public void setUserMobile(String mobileNumber) {
        mPreferencesHelper.setUserMobile(mobileNumber);
    }

    @Override
    public String getUserPassword() {
        return mPreferencesHelper.getUserPassword();
    }

    @Override
    public void setUserPassword(String password) {
        mPreferencesHelper.setUserPassword(password);
    }

    @Override
    public String getUserAddress() {
        return mPreferencesHelper.getUserAddress();
    }

    @Override
    public void setUserAddress(String address) {
        mPreferencesHelper.setUserAddress(address);
    }

    @Override
    public String getUserCity() {
        return mPreferencesHelper.getUserCity();
    }

    @Override
    public void setUserCity(String city) {
        mPreferencesHelper.setUserCity(city);
    }

    @Override
    public String getUserState() {
        return mPreferencesHelper.getUserState();
    }

    @Override
    public void setUserState(String state) {
        mPreferencesHelper.setUserState(state);
    }

    @Override
    public String getUserCountry() {
        return mPreferencesHelper.getUserCountry();
    }

    @Override
    public void setUserCountry(String country) {
        mPreferencesHelper.setUserCountry(country);
    }

    @Override
    public int getUserZipCode() {
        return mPreferencesHelper.getUserZipCode();
    }

    @Override
    public void setUserZipCode(int zipCode) {
        mPreferencesHelper.setUserZipCode(zipCode);
    }

    @Override
    public String getPetType() {
        return mPreferencesHelper.getPetType();
    }

    @Override
    public void setPetType(String type) {
        mPreferencesHelper.setPetType(type);
    }

    @Override
    public String getPetName() {
        return mPreferencesHelper.getPetName();
    }

    @Override
    public void setPetName(String name) {
        mPreferencesHelper.setPetName(name);
    }

    @Override
    public String getPetBreed() {
        return mPreferencesHelper.getPetBreed();
    }

    @Override
    public void setPetBreed(String breed) {
        mPreferencesHelper.setPetBreed(breed);
    }

    @Override
    public int getPetAge() {
        return mPreferencesHelper.getPetAge();
    }

    @Override
    public void setPetAgeInYears(int age) {
        mPreferencesHelper.setPetAgeInYears(age);
    }

    @Override
    public boolean getPetIsFriendly() {
        return mPreferencesHelper.getPetIsFriendly();
    }

    @Override
    public void setPetIsFriendly(boolean isFriendly) {
        mPreferencesHelper.setPetIsFriendly(isFriendly);
    }

    @Override
    public String getPetAbout() {
        return mPreferencesHelper.getPetAbout();
    }

    @Override
    public void setPetAbout(String about) {
        mPreferencesHelper.setPetAbout(about);
    }


    @Override
    public boolean isFirstTime() {
        return mPreferencesHelper.isFirstTime();
    }

    @Override
    public void setFirstTime(boolean firstTime) {
        mPreferencesHelper.setFirstTime(firstTime);
    }

    @Override
    public void logoutUser() {
        mPreferencesHelper.logoutUser();
    }

    @Override
    public int getPetId() {
        return mPreferencesHelper.getPetId();
    }

    @Override
    public void setPetId(int id) {
        mPreferencesHelper.setPetId(id);
    }

    @Override
    public ArrayList<Integer> getCareTakerServices() {
        return mPreferencesHelper.getCareTakerServices();
    }

    @Override
    public void setCareTackerServices(ArrayList<Integer> services) {
        mPreferencesHelper.setCareTackerServices(services);
    }

    @Override
    public int getChargePerHour() {
        return mPreferencesHelper.getChargePerHour();
    }

    @Override
    public void setChargesPerHour(int chargesPerHour) {
        mPreferencesHelper.setChargesPerHour(chargesPerHour);
    }

    @Override
    public String getBankAccount() {
        return mPreferencesHelper.getBankAccount();
    }

    @Override
    public void setBankAccount(String account) {
        mPreferencesHelper.setBankAccount(account);
    }

    @Override
    public String getBankName() {
        return mPreferencesHelper.getBankName();
    }

    @Override
    public void setBankName(String name) {
        mPreferencesHelper.setBankName(name);
    }

    @Override
    public String getBankBranch() {
        return mPreferencesHelper.getBankBranch();
    }

    @Override
    public void setBankBranch(String branch) {
        mPreferencesHelper.setBankBranch(branch);
    }

    @Override
    public String getBankIFSCCode() {
        return mPreferencesHelper.getBankIFSCCode();
    }

    @Override
    public void setBankIFSCCode(String ifscCode) {
        mPreferencesHelper.setBankIFSCCode(ifscCode);
    }
}
