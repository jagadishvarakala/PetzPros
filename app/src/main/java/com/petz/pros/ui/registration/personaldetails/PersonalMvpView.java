package com.petz.pros.ui.registration.personaldetails;

import com.petz.pros.data.network.pojo.FeedItem;
import com.petz.pros.ui.base.MvpView;

import java.util.List;

import okhttp3.ResponseBody;

public interface PersonalMvpView extends MvpView {

    void onSuccessEmailResponse(boolean b);

    void onSuccessMobileResponse(boolean b);
}
