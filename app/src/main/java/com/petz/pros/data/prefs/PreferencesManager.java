package com.petz.pros.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.petz.pros.data.utils.LoggedInMode;
import com.petz.pros.di.ApplicationContext;
import com.petz.pros.di.PreferenceInfo;
import com.petz.pros.root.AppConstant;

import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.inject.Inject;

public class PreferencesManager implements PreferencesHelper {

    private static final String PREF_KEY_USER_LOGGED_IN_MODE = "PREF_KEY_LOGGED_IN_MODE";
    private static final String PREF_KEY_USER_ID = "PREF_KEY_USER_ID";
    private static final String PREF_KEY_USER_TYPE = "PREF_KEY_USER_TYPE";
    private static final String PREF_KEY_USER_MOBILE = "PREF_KEY_CURRENT_MOBILE";
    private static final String PREF_KEY_USER_ADDRESS = "PREF_KEY_USER_ADDRESS";
    private static final String PREF_KEY_USER_NAME = "PREF_KEY_CURRENT_USER_NAME";
    private static final String PREF_KEY_USER_EMAIL = "PREF_KEY_CURRENT_USER_EMAIL";
    private static final String PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN";
    private static final String PREF_KEY_FIRST_TIME = "PREF_KEY_FIRST_TIME";
    private static final String PREF_KEY_FIRST_NAME = "PREF_KEY_FIRST_NAME";
    private static final String PREF_KEY_LAST_NAME = "PREF_KEY_LAST_NAME";
    private static final String PREF_KEY_USER_CITY = "PREF_KEY_USER_CITY";
    private static final String PREF_KEY_USER_STATE = "PREF_KEY_USER_STATE";
    private static final String PREF_KEY_USER_COUNTRY = "PREF_KEY_USER_COUNTRY";
    private static final String PREF_KEY_USER_ZIPCODE = "PREF_KEY_USER_ZIPCODE";
    private static final String PREF_KEY_PET_TYPE = "PREF_KEY_PET_TYPE";
    private static final String PREF_KEY_PET_NAME = "PREF_KEY_PET_NAME";
    private static final String PREF_KEY_PET_BREED = "PREF_KEY_PET_BREED";
    private static final String PREF_KEY_PET_AGE = "PREF_KEY_PET_AGE";
    private static final String PREF_KEY_PET_FRIENDLY = "PREF_KEY_PET_FRIENDLY";
    private static final String PREF_KEY_PET_ABOUT = "PREF_KEY_PET_ABOUT";
    private static final String PREF_KEY_USER_PASSWORD = "PREF_KEY_PASSWORD";
    private static final String PREF_KEY_PET_ID = "PREF_KEY_PET_ID";
    private static final String PREF_KEY_CHARGE_PER_HOUR = "PREF_KEY_CHARGE_PER_HOUR";
    private static final String PREF_KEY_BANK_ACCOUNT = "PREF_KEY_BANK_ACCOUNT";
    private static final String PREF_KEY_BANK_NAME = "PREF_KEY_BANK_NAME";
    private static final String PREF_KEY_BANK_BRANCH = "PREF_KEY_BANK_BRANCH";
    private static final String PREF_KEY_BANK_IFSC_CODE = "PREF_KEY_BANK_IFSC_CODE";

    private final SharedPreferences mPrefs;
    private Context mAppContext;

    @Inject
    public PreferencesManager(@ApplicationContext Context context,
                              @PreferenceInfo String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
        mAppContext = context;
    }

    @Override
    public String getUserName() {
        return mPrefs.getString(PREF_KEY_USER_NAME, null);
    }

    @Override
    public void setUserName(String userName) {
        mPrefs.edit().putString(PREF_KEY_USER_NAME, userName).apply();
    }

    @Override
    public String getUserEmail() {
        return mPrefs.getString(PREF_KEY_USER_EMAIL, null);
    }

    @Override
    public void setUserEmail(String email) {
        mPrefs.edit().putString(PREF_KEY_USER_EMAIL, email).apply();
    }



    @Override
    public int getUserLoggedInMode() {
        return mPrefs.getInt(PREF_KEY_USER_LOGGED_IN_MODE,
                LoggedInMode.LOGGED_IN_MODE_LOGOUT.getType());
    }

    @Override
    public void setUserLoggedIn(LoggedInMode mode) {
        mPrefs.edit().putInt(PREF_KEY_USER_LOGGED_IN_MODE, mode.getType()).apply();
    }

    @Override
    public int getUserId() {
        return mPrefs.getInt(PREF_KEY_USER_ID, 0);
    }

    @Override
    public void setUserId(int mUserId) {
        mPrefs.edit().putInt(PREF_KEY_USER_ID, mUserId).apply();
    }

    @Override
    public void setUserType(String userType) {
        mPrefs.edit().putString(PREF_KEY_USER_TYPE, userType).apply();
    }

    @Override
    public String getUserType() {
        if(mPrefs.getString(PREF_KEY_USER_TYPE, "").equalsIgnoreCase("petowner")){
            return "pet owner";
        }else if(mPrefs.getString(PREF_KEY_USER_TYPE, "").equalsIgnoreCase("petcaretaker")){
            return "pet care taker";
        }
        return mPrefs.getString(PREF_KEY_USER_TYPE, "");
    }

    @Override
    public String getUserFirstName() {
        return mPrefs.getString(PREF_KEY_FIRST_NAME, null);
    }

    @Override
    public void setUserFirstName(String name) {
        mPrefs.edit().putString(PREF_KEY_FIRST_NAME, name).apply();
    }

    @Override
    public String getUserLastName() {
        return mPrefs.getString(PREF_KEY_LAST_NAME, null);
    }

    @Override
    public void setUserLastName(String name) {
        mPrefs.edit().putString(PREF_KEY_LAST_NAME, name).apply();
    }

    @Override
    public String getAccessToken() {
        return mPrefs.getString(PREF_KEY_ACCESS_TOKEN, null);
    }

    @Override
    public void setAccessToken(String accessToken) {
        mPrefs.edit().putString(PREF_KEY_ACCESS_TOKEN, accessToken).apply();
    }

    @Override
    public String getUserMobile() {
        return mPrefs.getString(PREF_KEY_USER_MOBILE, "");
    }

    @Override
    public void setUserMobile(String mobileNumber) {
        mPrefs.edit().putString(PREF_KEY_USER_MOBILE, mobileNumber).apply();
    }

    @Override
    public String getUserPassword() {
        return mPrefs.getString(PREF_KEY_USER_PASSWORD, "");
    }

    @Override
    public void setUserPassword(String password) {
        mPrefs.edit().putString(PREF_KEY_USER_PASSWORD, password).apply();
    }

    @Override
    public String getUserAddress() {
        return mPrefs.getString(PREF_KEY_USER_ADDRESS, "");
    }

    @Override
    public void setUserAddress(String address) {
        mPrefs.edit().putString(PREF_KEY_USER_ADDRESS, address).apply();
    }

    @Override
    public String getUserCity() {
        return mPrefs.getString(PREF_KEY_USER_CITY, "");
    }

    @Override
    public void setUserCity(String city) {
        mPrefs.edit().putString(PREF_KEY_USER_CITY, city).apply();
    }

    @Override
    public String getUserState() {
        return mPrefs.getString(PREF_KEY_USER_STATE, "");
    }

    @Override
    public void setUserState(String state) {
        mPrefs.edit().putString(PREF_KEY_USER_STATE, state).apply();
    }

    @Override
    public String getUserCountry() {
        return mPrefs.getString(PREF_KEY_USER_COUNTRY, "");
    }

    @Override
    public void setUserCountry(String country) {
        mPrefs.edit().putString(PREF_KEY_USER_COUNTRY, country).apply();
    }

    @Override
    public int getUserZipCode() {
        return mPrefs.getInt(PREF_KEY_USER_ZIPCODE, 0);
    }

    @Override
    public void setUserZipCode(int zipCode) {
        mPrefs.edit().putInt(PREF_KEY_USER_ZIPCODE, zipCode).apply();
    }

    @Override
    public String getPetType() {
        return mPrefs.getString(PREF_KEY_PET_TYPE, "");
    }

    @Override
    public void setPetType(String type) {
        mPrefs.edit().putString(PREF_KEY_PET_TYPE, type).apply();
    }

    @Override
    public String getPetName() {
        return mPrefs.getString(PREF_KEY_PET_NAME, "");
    }

    @Override
    public void setPetName(String name) {
        mPrefs.edit().putString(PREF_KEY_PET_NAME, name).apply();
    }

    @Override
    public String getPetBreed() {
        return mPrefs.getString(PREF_KEY_PET_BREED, "");
    }

    @Override
    public void setPetBreed(String breed) {
        mPrefs.edit().putString(PREF_KEY_PET_BREED, breed).apply();
    }

    @Override
    public int getPetAge() {
        return mPrefs.getInt(PREF_KEY_PET_AGE, 0);
    }

    @Override
    public void setPetAgeInYears(int age) {
        mPrefs.edit().putInt(PREF_KEY_PET_AGE, age).apply();
    }

    @Override
    public boolean getPetIsFriendly() {
        return mPrefs.getBoolean(PREF_KEY_PET_FRIENDLY, false);
    }

    @Override
    public void setPetIsFriendly(boolean isFriendly) {
        mPrefs.edit().putBoolean(PREF_KEY_PET_FRIENDLY, isFriendly).apply();
    }

    @Override
    public String getPetAbout() {
        return mPrefs.getString(PREF_KEY_PET_ABOUT, "");
    }

    @Override
    public void setPetAbout(String about) {
        mPrefs.edit().putString(PREF_KEY_PET_ABOUT, about).apply();
    }


    @Override
    public boolean isFirstTime() {
        SharedPreferences pref = mAppContext.getSharedPreferences(AppConstant.SHARED_PREF, 0);
        return pref.getBoolean(PREF_KEY_FIRST_TIME, true);
    }

    @Override
    public void setFirstTime(boolean firstTime) {
        SharedPreferences pref = mAppContext.getSharedPreferences(AppConstant.SHARED_PREF, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(PREF_KEY_FIRST_TIME, firstTime);
        editor.apply();
    }

    @Override
    public void logoutUser() {
        mPrefs.edit().clear().apply();
    }

    @Override
    public int getPetId() {
        return mPrefs.getInt(PREF_KEY_PET_ID, 0);
    }

    @Override
    public void setPetId(int id) {
        mPrefs.edit().putInt(PREF_KEY_PET_ID, id).apply();
    }

    @Override
    public ArrayList<Integer> getCareTakerServices() {
        String savedString = mPrefs.getString(PREF_KEY_SERVICE_LIST, "");
        StringTokenizer st = new StringTokenizer(savedString, ",");
        ArrayList<Integer> savedList = new ArrayList<>();
        int count = st.countTokens();
        for (int i = 0; i < count; i++) {
            savedList.add(Integer.parseInt(st.nextToken())) ;
        }
        return savedList;
    }
    private static final String PREF_KEY_SERVICE_LIST = "PREF_KEY_SERVICE_LIST";
    @Override
    public void setCareTackerServices(ArrayList<Integer> services) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < services.size(); i++) {
            str.append(services.get(i)).append(",");
        }
        mPrefs.edit().putString(PREF_KEY_SERVICE_LIST, str.toString()).apply();
    }

    @Override
    public int getChargePerHour() {
        return mPrefs.getInt(PREF_KEY_CHARGE_PER_HOUR, 0);
    }

    @Override
    public void setChargesPerHour(int chargesPerHour) {
        mPrefs.edit().putInt(PREF_KEY_CHARGE_PER_HOUR, chargesPerHour).apply();
    }

    @Override
    public String getBankAccount() {
        return mPrefs.getString(PREF_KEY_BANK_ACCOUNT, "");
    }

    @Override
    public void setBankAccount(String account) {
        mPrefs.edit().putString(PREF_KEY_BANK_ACCOUNT, account).apply();
    }

    @Override
    public String getBankName() {
        return mPrefs.getString(PREF_KEY_BANK_NAME, "");
    }

    @Override
    public void setBankName(String name) {
        mPrefs.edit().putString(PREF_KEY_BANK_NAME, name).apply();
    }

    @Override
    public String getBankBranch() {
        return mPrefs.getString(PREF_KEY_BANK_BRANCH, "");
    }

    @Override
    public void setBankBranch(String branch) {
        mPrefs.edit().putString(PREF_KEY_BANK_BRANCH, branch).apply();
    }

    @Override
    public String getBankIFSCCode() {
        return mPrefs.getString(PREF_KEY_BANK_IFSC_CODE, "");
    }

    @Override
    public void setBankIFSCCode(String ifscCode) {
        mPrefs.edit().putString(PREF_KEY_BANK_IFSC_CODE, ifscCode).apply();
    }

}
