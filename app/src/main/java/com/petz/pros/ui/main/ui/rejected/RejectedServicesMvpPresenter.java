package com.petz.pros.ui.main.ui.rejected;

import com.petz.pros.ui.base.MvpPresenter;
import com.petz.pros.ui.main.ui.pending.PendingServicesMvpView;

public interface RejectedServicesMvpPresenter<V extends RejectedServicesMvpView> extends MvpPresenter<V> {

    void getBookingsDetails();
}
