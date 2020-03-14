package com.petz.pros.ui.main.upcoming;

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
import com.petz.pros.ui.main.ui.bookings.BookingsMvpView;
import com.petz.pros.ui.main.ui.bookings.adapter.BookingsAdapter;
import com.petz.pros.ui.tracking.UpdatingLocationActivity;

import java.util.ArrayList;

import javax.inject.Inject;

import okhttp3.ResponseBody;


public class UpcomingServicesActivity extends BaseActivity implements UpcomingServicesMvpView , BookingsMvpView {

    @Inject
    UpcomingServicesMvpPresenter<UpcomingServicesMvpView> bookingMvpPresenter;
    private FragmentBookingsBinding fragmentBookingsBinding;

    private BookingsAdapter bookingsAdapter;
    private BookingsMvpView bookingsMvpView;

    public static Intent getIntent(Context context){
        return new Intent(context, UpcomingServicesActivity.class);
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
    public void updateBooking(ArrayList<BookingsModule> arrayLists) {

    }


    @Override
    public void onClickPayBtn(BookingsModule module) {

    }

    @Override
    public void onClickTrackBtn(BookingsModule bookingsModule) {

    }

    @Override
    public void onClickStartService(BookingsModule bookingsModule) {
       bookingMvpPresenter.startService(bookingsModule);
    }

    @Override
    public void paymentDetailsUpdated(ResponseBody responseBody) {

    }

    @Override
    public void updateBookings(ArrayList<ArrayList<BookingsModule>> arrayLists) {
        if (arrayLists.get(1).size() > 0)
            bookingsAdapter.setEmployeeList(arrayLists.get(1));
        else
            showNoDataFound("UpComing Services Not Available");
    }

    @Override
    public void onSuccessStartService(BookingsModule bookingsModule) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("tracking_info",bookingsModule);
        startActivity(new Intent(getApplicationContext(), UpdatingLocationActivity.class).putExtras(bundle));
    }

    @Override
    protected void setUp() {
        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // bind RecyclerView
        RecyclerView recyclerView = fragmentBookingsBinding.viewEmployees;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        bookingsMvpView = this;
        bookingsAdapter = new BookingsAdapter();
        bookingsAdapter.setOwner(bookingMvpPresenter.isPetOwner());
        bookingsAdapter.setClickListener(bookingsMvpView);
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