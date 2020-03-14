package com.petz.pros.ui.main.ui.past;

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

import java.util.ArrayList;

import javax.inject.Inject;


public class PastServicesActivity extends BaseActivity implements PastServicesMvpView {

    @Inject
    PastServicesMvpPresenter<PastServicesMvpView> bookingMvpPresenter;
    private FragmentBookingsBinding fragmentBookingsBinding;

    private PastBookingsAdapter bookingsAdapter;

    public static Intent getIntent(Context context){
        return new Intent(context, PastServicesActivity.class);
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
    public void updateBookings(ArrayList<ArrayList<BookingsModule>> arrayLists) {
        if (arrayLists.get(0).size() > 0)
            bookingsAdapter.setEmployeeList(arrayLists.get(0));
        else
            showNoDataFound("Past Services Not Available");
    }

    @Override
    protected void setUp() {
        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // bind RecyclerView
        RecyclerView recyclerView = fragmentBookingsBinding.viewEmployees;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        bookingsAdapter = new PastBookingsAdapter();
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