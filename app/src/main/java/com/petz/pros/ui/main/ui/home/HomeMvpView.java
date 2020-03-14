package com.petz.pros.ui.main.ui.home;

import com.petz.pros.data.network.pojo.FeedItem;
import com.petz.pros.ui.base.MvpView;
import com.petz.pros.ui.main.ui.home.card.CardItem;

import java.util.List;

public interface HomeMvpView extends MvpView {
    void updateFeed(List<FeedItem> feedItemList);

    void openBottomSheet(CardItem item);

    void onClickProceed(int mSelectedYear, int mSelectedMonth, int mSelectedDay, int mSelectedStartHour, int mSelectedStartMinute, int mSelectedEndHour, int mSelectedEndMinte);

}
