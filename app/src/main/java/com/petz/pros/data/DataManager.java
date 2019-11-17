package com.petz.cleancode.data;

import com.petz.cleancode.data.db.dao.UserDao;
import com.petz.cleancode.data.network.RestApiHelper;
import com.petz.cleancode.data.prefs.PreferencesHelper;
import com.petz.cleancode.data.utils.LoggedInMode;

public interface DataManager extends UserDao, PreferencesHelper, RestApiHelper {
    void updateApiHeader(Long userId, String accessToken);

    void setUserLoggedOut();

    void updateUserInfo(
            String accessToken,
            Long userId,
            LoggedInMode loggedInMode,
            String userName,
            String email,
            String profilePicPath);
}
