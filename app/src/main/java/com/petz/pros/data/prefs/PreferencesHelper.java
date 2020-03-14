package com.petz.pros.data.prefs;

import com.petz.pros.data.utils.LoggedInMode;

import java.util.ArrayList;

public interface PreferencesHelper {
    int getUserLoggedInMode();

    void setUserLoggedIn(LoggedInMode mode);

    int getUserId();

    void setUserId(int userId);

    void setUserType(String userType);

    String getUserType();

    String getUserFirstName();

    void setUserFirstName(String name);

    String getUserLastName();

    void setUserLastName(String name);

    String getUserName();

    void setUserName(String userName);

    String getUserEmail();

    void setUserEmail(String email);

    String getAccessToken();

    void setAccessToken(String accessToken);

    String getUserMobile();

    void setUserMobile(String mobileNumber);

    String getUserPassword();

    void setUserPassword(String password);

    String getUserAddress();

    void setUserAddress(String address);

    String getUserCity();

    void setUserCity(String city);

    String getUserState();

    void setUserState(String state);

    String getUserCountry();

    void setUserCountry(String country);

    int getUserZipCode();

    void setUserZipCode(int zipCode);

    String getPetType ();

    void setPetType(String type);

    String getPetName();

    void setPetName(String name);

    String getPetBreed();

    void setPetBreed(String breed);

    int getPetAge();

    void setPetAgeInYears(int age);

    boolean getPetIsFriendly();

    void setPetIsFriendly(boolean isFriendly);

    String getPetAbout();

    void setPetAbout(String about);

    boolean isFirstTime();

    void setFirstTime(boolean firstTime);

    void logoutUser();

    int getPetId();

    void setPetId(int id);

    ArrayList<Integer> getCareTakerServices();

    void setCareTackerServices(ArrayList<Integer> services);

    int getChargePerHour();

    void setChargesPerHour(int chargesPerHour);

    String getBankAccount();

    void setBankAccount(String account);

    String getBankName();

    void setBankName(String name);

    String getBankBranch();

    void setBankBranch(String branch);

    String getBankIFSCCode();

    void setBankIFSCCode(String ifscCode);
}
