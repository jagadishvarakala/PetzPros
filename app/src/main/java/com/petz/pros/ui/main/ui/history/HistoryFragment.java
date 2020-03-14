package com.petz.pros.ui.main.ui.history;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.petz.pros.R;
import com.petz.pros.databinding.FragmentHistoryBinding;
import com.petz.pros.ui.base.BaseFragment;
import com.petz.pros.ui.main.ui.bookings.BookingsModule;
import com.petz.pros.ui.main.ui.history.adapter.HistoryAdapter;
import com.petz.pros.ui.tracking.TrackingActivity;

import java.util.ArrayList;

import javax.inject.Inject;


public class HistoryFragment extends BaseFragment implements HistoryMvpView {

    @Inject
    HistoryMvpPresenter<HistoryMvpView> bookingMvpPresenter;
    private FragmentHistoryBinding fragmentBookingsBinding;

    private HistoryAdapter bookingsAdapter;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        fragmentBookingsBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_history, container, false);
        getActivityComponent().inject(this);
        bookingMvpPresenter.onAttach(this);
        return fragmentBookingsBinding.getRoot();
    }

    @Override
    protected void setUp(View view) {
        // bind RecyclerView
        RecyclerView recyclerView = fragmentBookingsBinding.viewEmployees;
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseActivity()));
        recyclerView.setHasFixedSize(true);

         bookingsAdapter = new HistoryAdapter();
        recyclerView.setAdapter(bookingsAdapter);
        bookingsAdapter.setHistoryMvpView(this);
        bookingMvpPresenter.getHistoryDetails();
    }

    @Override
    public void showNoDataFound(String message) {
        fragmentBookingsBinding.nodataFoundText.setText(message);
    }

    @Override
    public void updateHistory(ArrayList<ArrayList<BookingsModule>> arrayLists) {
        ArrayList<BookingsModule> bookingsModules = new ArrayList<>();
        for(int i=0; i< arrayLists.size(); i++) {
        bookingsModules.addAll(arrayLists.get(i));
        }
        if(bookingsModules.size() > 0) {
            bookingsAdapter.setEmployeeList(bookingsModules);
        }else{
            showNoDataFound("No Data Found");
        }
    }

    @Override
    public void onClickTrackBtn(BookingsModule bookingsModule) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("tracking_info",bookingsModule);
        startActivity(new Intent(getBaseActivity(), TrackingActivity.class).putExtras(bundle));
    }
}