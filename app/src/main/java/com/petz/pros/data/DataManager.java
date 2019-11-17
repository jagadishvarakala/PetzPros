package com.petz.pros.data;

import com.petz.pros.data.db.dao.UserDao;
import com.petz.pros.data.network.RestApiHelper;
import com.petz.pros.data.prefs.PreferencesHelper;
import com.petz.pros.data.utils.LoggedInMode;

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
