package com.petz.pros.ui.main;

import com.petz.pros.data.network.pojo.FeedItem;
import com.petz.pros.ui.base.MvpView;

import java.util.List;

/**
 * Created on : Feb 11, 2019
 * Author     : AndroidWave
 */
public interface MainMvpView extends MvpView {
    void updateFeed(List<FeedItem> feedItemList);

    void navigateUserSelection();
}
