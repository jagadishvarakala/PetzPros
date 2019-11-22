package com.petz.pros.ui.main.ui.home;

import com.petz.pros.data.network.pojo.FeedItem;
import com.petz.pros.ui.base.MvpView;

import java.util.List;

public interface HomeMvpView extends MvpView {
    void updateFeed(List<FeedItem> feedItemList);
}
