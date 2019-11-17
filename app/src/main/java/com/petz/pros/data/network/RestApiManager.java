package com.petz.pros.data.network;

import com.petz.pros.data.network.pojo.FeedItem;
import com.petz.pros.data.network.pojo.LoginRequest;
import com.petz.pros.data.network.pojo.UserProfile;
import com.petz.pros.data.network.pojo.WrapperResponse;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class RestApiManager implements RestApiHelper {

    NetworkService mService;

    @Inject
    public RestApiManager(NetworkService apiService) {
        mService = apiService;
    }

    @Override
    public Single<WrapperResponse<UserProfile>> doLoginApiCall(LoginRequest request) {
        return mService.doLoginApiCall(request);
    }

    @Override
    public Single<WrapperResponse<List<FeedItem>>> getFeedList() {
        return mService.getFeedList();
    }
}
