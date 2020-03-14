package com.petz.pros.ui.main.ui.rejected;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.petz.pros.R;
import com.petz.pros.databinding.FragmentBookingsBinding;
import com.petz.pros.ui.base.BaseActivity;
import com.petz.pros.ui.main.ui.bookings.BookingsModule;
import com.petz.pros.ui.main.ui.bookings.adapter.BookingsAdapter;
import com.petz.pros.ui.main.ui.caretackerprofile.CareTackerProfileActivity;
import com.petz.pros.ui.main.ui.pending.PendingServicesMvpPresenter;
import com.petz.pros.ui.main.ui.pending.PendingServicesMvpView;
import com.petz.pros.ui.main.ui.rejected.adapter.RejectedAdapter;

import java.util.ArrayList;

import javax.inject.Inject;


public class RejectedServicesActivity extends BaseActivity implements RejectedServicesMvpView {

    @Inject
    RejectedServicesMvpPresenter<RejectedServicesMvpView> bookingMvpPresenter;
    private FragmentBookingsBinding fragmentBookingsBinding;

    private RejectedAdapter bookingsAdapter;

    public static Intent getIntent(Context context){
        return new Intent(context, RejectedServicesActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentBookingsBinding = DataBindingUtil.setContentView(this,R.layout.fragment_bookings);
        getActivityComponent().inject(this);
        bookingMvpPresenter.onAttach(this);
        setUp();
    }



    @Override
    public void showNoDataFound(String message) {
        fragmentBookingsBinding.nodataFoundText.setText(message);
    }

    @Override
    public void updateBookings(ArrayList<BookingsModule> arrayLists) {
        ArrayList<BookingsModule> modelArrayList = new ArrayList<>();
        for(int i=0; i< arrayLists.size(); i++){
            if(arrayLists.get(i).isBookingStatus() != null && arrayLists.get(i).isBookingStatus().equalsIgnoreCase("rejected")){
                modelArrayList.add(arrayLists.get(i));
            }
        }
        if(modelArrayList.size()>0) {
            bookingsAdapter.setEmployeeList(modelArrayList);
        }else{
            showNoDataFound("No Rejected Services");
        }
    }

    @Override
    protected void setUp() {
        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // bind RecyclerView
        RecyclerView recyclerView = fragmentBookingsBinding.viewEmployees;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        bookingsAdapter = new RejectedAdapter();
        recyclerView.setAdapter(bookingsAdapter);

        bookingMvpPresenter.getBookingsDetails();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }
}