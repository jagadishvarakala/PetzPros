package com.petz.pros.data.network;

import com.petz.pros.data.network.pojo.FeedItem;
import com.petz.pros.data.network.pojo.LoginRequest;
import com.petz.pros.data.network.pojo.UserProfile;
import com.petz.pros.data.network.pojo.WrapperResponse;

import java.util.List;

import io.reactivex.Single;

public interface RestApiHelper {

    Single<WrapperResponse<UserProfile>> doLoginApiCall(LoginRequest request);

    Single<WrapperResponse<List<FeedItem>>> getFeedList();
}
