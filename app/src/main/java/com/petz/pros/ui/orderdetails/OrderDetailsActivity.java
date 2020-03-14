package com.petz.pros.ui.orderdetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.petz.pros.R;
import com.petz.pros.databinding.ActivityOrderDetailsBinding;
import com.petz.pros.ui.base.BaseActivity;
import com.petz.pros.ui.bookingdetails.BookingResponse;
import com.petz.pros.ui.main.NavigationActivity;

import javax.inject.Inject;

public class OrderDetailsActivity extends BaseActivity implements OrderDetailsMvpView{

    @Inject
    OrderDetailsMvpPresenter<OrderDetailsMvpView> detailsMvpPresenter;

    private ActivityOrderDetailsBinding orderDetailsBinding;

    public static Intent getIntent(Context context, BookingResponse bookingResponse){
        Intent intent = new Intent(context,OrderDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("booking_response",bookingResponse);
        intent.putExtras(bundle);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        orderDetailsBinding = DataBindingUtil.setContentView(this,R.layout.activity_order_details);
        getActivityComponent().inject(this);
        detailsMvpPresenter.onAttach(OrderDetailsActivity.this);
        setUp();
    }

    @Override
    protected void setUp() {
      BookingResponse bookingResponse = (BookingResponse)  getIntent().getSerializableExtra("booking_response");
      bookingResponse.setCity(detailsMvpPresenter.getUserCity());
      bookingResponse.setState(detailsMvpPresenter.getUserState());
      orderDetailsBinding.setBooking(bookingResponse);
      orderDetailsBinding.setCallback(this);

        if( getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Order Details");
            getSupportActionBar().setElevation(0);
        }

    }

    @Override
    public void onClickDone() {
        onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, NavigationActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |  Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }
}
