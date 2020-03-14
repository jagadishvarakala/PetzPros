package com.petz.pros.data;

import com.petz.pros.data.db.dao.UserDao;
import com.petz.pros.data.network.RestApiHelper;
import com.petz.pros.data.prefs.PreferencesHelper;
import com.petz.pros.data.utils.LoggedInMode;

import java.util.ArrayList;

public interface DataManager extends  PreferencesHelper {
    void updateApiHeader(Long userId, String accessToken);

    void setUserLoggedOut();

    void updateUserInfo(
            int userId,
            String userType,
            String firstname,
            String lastname,
            String email,
            String profilePicPath,
            String password);

    void updateUserAddress(String address,
                           String city,
                           String state,
                           String country,
                           int zipcode);

    void updatePetDetails(int id,String type,String name,String breed,int age, boolean isFriendly,String about);

    void updateServiceList(ArrayList<Integer> serviceList,int chargePerHour);

    void updateBankDetails(String bankAccount,String bankName, String bankBranch,String bankIFSCCode);
}
