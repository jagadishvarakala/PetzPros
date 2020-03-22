package com.petz.pros.ui.main.ui.pending;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.petz.pros.R;
import com.petz.pros.databinding.FragmentBookingsBinding;
import com.petz.pros.ui.base.BaseActivity;
import com.petz.pros.ui.base.BaseFragment;
import com.petz.pros.ui.bookingdetails.BookingModel;
import com.petz.pros.ui.main.ui.bookings.BookingsModule;
import com.petz.pros.ui.main.ui.bookings.BookingsMvpPresenter;
import com.petz.pros.ui.main.ui.bookings.BookingsMvpView;
import com.petz.pros.ui.main.ui.bookings.adapter.BookingsAdapter;
import com.petz.pros.ui.main.ui.caretackerprofile.CareTackerProfileActivity;
import com.petz.pros.ui.main.ui.pending.adapter.PendingAdapter;

import java.util.ArrayList;

import javax.inject.Inject;


public class PendingServicesActivity extends BaseActivity implements PendingServicesMvpView {

    @Inject
    PendingServicesMvpPresenter<PendingServicesMvpView> bookingMvpPresenter;
    private FragmentBookingsBinding fragmentBookingsBinding;

    private PendingAdapter bookingsAdapter;
    private ArrayList<BookingsModule> modelArrayList = new ArrayList<>();

    public static Intent getIntent(Context context){
        return new Intent(context, PendingServicesActivity.class);
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
        modelArrayList.clear();
        bookingsAdapter.setEmployeeList(modelArrayList);
        fragmentBookingsBinding.nodataFoundText.setText(message);
    }

    @Override
    public void updateBookings(ArrayList<BookingsModule> arrayLists) {
        modelArrayList.clear();
        //modelArrayList = arrayLists;
        for(int i=0; i< arrayLists.size(); i++){
            if(arrayLists.get(i).isBookingStatus().equalsIgnoreCase("noaction")){
                modelArrayList.add(arrayLists.get(i));
            }
        }
        if(modelArrayList.size() > 0) {
            bookingsAdapter.setEmployeeList(modelArrayList);
        }else {
            showNoDataFound("No Pending Services");
        }
    }

    @Override
    public void onClickRequestAccept(BookingsModule bookingsModule,int position) {
        bookingsModule.setBookingStatus("accepted");
        bookingMvpPresenter.updateBookingStatus(bookingsModule,position);
    }

    @Override
    public void onClickRequestReject(BookingsModule bookingsModule,int position) {
        bookingsModule.setBookingStatus("rejected");
        bookingMvpPresenter.updateBookingStatus(bookingsModule,position);
    }

    @Override
    public void onSuccessUpdateBookingStatus(int position) {
//        modelArrayList.remove(position);
//        bookingsAdapter.notifyItemChanged(position);
        bookingMvpPresenter.getBookingsDetails();
    }

    @Override
    protected void setUp() {
        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // bind RecyclerView
        RecyclerView recyclerView = fragmentBookingsBinding.viewEmployees;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        bookingsAdapter = new PendingAdapter();
        recyclerView.setAdapter(bookingsAdapter);
        bookingsAdapter.setClickListener(this);
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