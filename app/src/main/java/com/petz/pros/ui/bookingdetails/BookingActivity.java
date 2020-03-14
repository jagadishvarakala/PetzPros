package com.petz.pros.ui.bookingdetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.petz.pros.R;
import com.petz.pros.databinding.ActivityBookingBinding;
import com.petz.pros.ui.base.BaseActivity;
import com.petz.pros.ui.caretackerlist.CareTackersModel;
import com.petz.pros.ui.orderdetails.OrderDetailsActivity;

import javax.inject.Inject;

public class BookingActivity extends BaseActivity implements BookingMvpView {

    @Inject
    BookingMvpPresenter<BookingMvpView> bookingMvpPresenter;
    private ActivityBookingBinding activityBookingBinding;


    public static Intent getStartIntent(Context context,BookingModel bookingModel, CareTackersModel careTackersModel) {
        Intent intent = new Intent(context, BookingActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("care_tacker_details",careTackersModel);
        bundle.putSerializable("service_details",bookingModel);
        intent.putExtras(bundle);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityBookingBinding = DataBindingUtil.setContentView(this, R.layout.activity_booking);
        getActivityComponent().inject(this);
        bookingMvpPresenter.onAttach(BookingActivity.this);
        setUp();
    }

    @Override
    protected void setUp() {
        Bundle bundle = getIntent().getExtras();
        BookingModel  bookingModel = (BookingModel) bundle.getSerializable("service_details");
        CareTackersModel careTackersModel = (CareTackersModel) bundle.getSerializable("care_tacker_details");
        activityBookingBinding.setBookingDetails(bookingModel);
        activityBookingBinding.setServiceProvider(careTackersModel);
        activityBookingBinding.setCallback(bookingMvpPresenter);

       if( getSupportActionBar() != null)
           getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {// API 5+ solution
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void bookingConfirmSuccess(BookingResponse bookingResponse) {
        startActivity(OrderDetailsActivity.getIntent(this,bookingResponse));
    }
}
