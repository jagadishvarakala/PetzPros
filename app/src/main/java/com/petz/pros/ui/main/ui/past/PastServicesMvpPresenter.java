package com.petz.pros.ui.main.ui.past;

import com.petz.pros.ui.base.MvpPresenter;
import com.petz.pros.ui.main.ui.pending.PendingServicesMvpView;

public interface PastServicesMvpPresenter<V extends PastServicesMvpView> extends MvpPresenter<V> {

    void getBookingsDetails();
}
