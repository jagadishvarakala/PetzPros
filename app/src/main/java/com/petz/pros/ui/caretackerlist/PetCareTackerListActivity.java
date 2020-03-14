package com.petz.pros.ui.caretackerlist;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.petz.pros.R;
import com.petz.pros.databinding.ActivityPetCareTackerListBinding;
import com.petz.pros.ui.base.BaseActivity;
import com.petz.pros.ui.bookingdetails.BookingActivity;
import com.petz.pros.ui.bookingdetails.BookingModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

public class PetCareTackerListActivity extends BaseActivity implements CareTackerMvpView {

    @Inject
    CareTackerListMvpPresenter<CareTackerMvpView> listMvpPresenter;
    private ActivityPetCareTackerListBinding tackerListBinding;
    private List<CareTackersModel> movieList = new ArrayList<>();
    private CareTackersAdapter mAdapter;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private int mSelectedYear, mSelectedMonth, mSelectedDay, mSelectedStartHour, mSelectedStartMinute,mSelectedEndHour,mSelectedEndMinte;
    private double totalAmount;


    public static Intent getStartIntent(Context context, int serviceId,int mSelectedYear, int mSelectedMonth, int mSelectedDay, int mSelectedStartHour, int mSelectedStartMinute, int mSelectedEndHour, int mSelectedEndMinte) {
        Intent intent = new Intent(context, PetCareTackerListActivity.class);
        intent.putExtra("service_id",serviceId);
        intent.putExtra("selected_year",mSelectedYear);
        intent.putExtra("selected_month",mSelectedMonth);
        intent.putExtra("selected_day",mSelectedDay);
        intent.putExtra("selected_star_hr",mSelectedStartHour);
        intent.putExtra("selected_star_min",mSelectedStartMinute);
        intent.putExtra("selected_end_hr",mSelectedEndHour);
        intent.putExtra("selected_end_min",mSelectedEndMinte);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tackerListBinding = DataBindingUtil.setContentView(this, R.layout.activity_pet_care_tacker_list);
        getActivityComponent().inject(this);
        listMvpPresenter.onAttach(PetCareTackerListActivity.this);
        setUp();
    }

    @Override
    protected void setUp() {
        if( getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mSelectedYear = getIntent().getIntExtra("selected_year",0);
        mSelectedMonth = getIntent().getIntExtra("selected_month",0);
        mSelectedDay = getIntent().getIntExtra("selected_day",0);
        mSelectedStartHour = getIntent().getIntExtra("selected_star_hr",0);
        mSelectedStartMinute = getIntent().getIntExtra("selected_star_min",0);
        mSelectedEndHour = getIntent().getIntExtra("selected_end_hr",0);
        mSelectedEndMinte = getIntent().getIntExtra("selected_end_min",0);


        tackerListBinding.dateEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayDatePicker();
            }
        });

        tackerListBinding.startTimeEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayTimePicker(true);
            }
        });
        tackerListBinding.endTimeEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayTimePicker(false);
            }
        });
        tackerListBinding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listMvpPresenter.validateServiceDetails(getIntent().getIntExtra("service_id",0));
            }
        });
        mAdapter = new CareTackersAdapter(movieList,this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        tackerListBinding.careTackerListRecyclerView.setLayoutManager(mLayoutManager);
        tackerListBinding.careTackerListRecyclerView.setItemAnimator(new DefaultItemAnimator());
        tackerListBinding.careTackerListRecyclerView.setAdapter(mAdapter);

        listMvpPresenter.getCareTackers(getIntent().getIntExtra("service_id",0));

    }

    @Override
    public void updateAvailableCareTackers(List<CareTackersModel> body) {
        movieList.addAll(body);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public String getServiceDate() {
        String time = mSelectedYear + "-" + mSelectedMonth+"-"+ mSelectedDay;
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault());
        Date date = null;
        try {
            date = fmt.parse(time );
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat fmtOut = new SimpleDateFormat("yyyy/MMM/dd",Locale.getDefault());
        return fmtOut.format(date);
    }

    @Override
    public String getStartTime() {
        String time = mSelectedStartHour + ":" + mSelectedStartMinute;
        SimpleDateFormat fmt = new SimpleDateFormat("HH:mm",Locale.getDefault());
        Date date = null;
        try {
            date = fmt.parse(time );
        } catch (ParseException e) {

            e.printStackTrace();
        }
        SimpleDateFormat fmtOut = new SimpleDateFormat("hh:mm aa",Locale.getDefault());
        return fmtOut.format(date);
    }

    @Override
    public String getEndTime() {
        String time = mSelectedEndHour + ":" + mSelectedEndMinte;
        SimpleDateFormat fmt = new SimpleDateFormat("HH:mm",Locale.getDefault());
        Date date = null;
        try {
            date = fmt.parse(time );
        } catch (ParseException e) {

            e.printStackTrace();
        }
        SimpleDateFormat fmtOut = new SimpleDateFormat("hh:mm aa",Locale.getDefault());
        return fmtOut.format(date);
    }

    @Override
    public void showErrorMessageDate(String message) {
        tackerListBinding.dateEdt.setError(message);
    }

    @Override
    public void showErrorMessageStartTime(String message) {
        tackerListBinding.startTimeEdt.setError(message);
    }

    @Override
    public void showErrorMessageEndTime(String message) {
        tackerListBinding.endTimeEdt.setError(message);
    }

    @Override
    public void onClickBookBtn(CareTackersModel movie) {
        BookingModel bookingModel  = new BookingModel();
        bookingModel.setOwnerId(listMvpPresenter.getOwnerId());
        bookingModel.setCareTakerID(movie.mId);
        bookingModel.setName(listMvpPresenter.getOwnerName());
        bookingModel.setPhone(listMvpPresenter.getOwnerPhone());
        bookingModel.setAddress(listMvpPresenter.getOwnerAddress());
        bookingModel.setServiceId(getIntent().getIntExtra("service_id",0));
        bookingModel.setServiceName(getServiceName(getIntent().getIntExtra("service_id",0)));
        bookingModel.setServiceDate(getServiceDate());
        bookingModel.setServiceStartTime(getStartTime());
        bookingModel.setServiceEndTime(getEndTime());
        bookingModel.setServiceTotalTime(getTotalTime(getStartTime(),getEndTime(),false,0));
        bookingModel.setServiceTotalAmount(getTotalTime(getStartTime(),getEndTime(),true,movie.getmChargePerHour()));
        bookingModel.setAppointmentDate(getSelectedDate());
        bookingModel.setStartTime(getSelectedStartTime());
        bookingModel.setEndTime(getSelectedEndTime());
        bookingModel.setTotalAmount(totalAmount);
        bookingModel.setServiceRefTypeId(getIntent().getIntExtra("service_id",0));
        bookingModel.setCareTackersModel(movie);
        bookingModel.setFCMDeviceId(movie.getFCMDeviceId());
        startActivity(BookingActivity.getStartIntent(this,bookingModel,movie));
    }

    private void displayDatePicker(){
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        mSelectedYear = year;
                        mSelectedMonth = monthOfYear;
                        mSelectedDay = dayOfMonth;
                        tackerListBinding.dateEdt.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);


                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }
    private  String getSelectedDate() {

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        cal.set(mSelectedYear, mSelectedMonth, mSelectedDay);
        Date date = cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:MM:SS", Locale.ENGLISH);

        return sdf.format(date);
    }
    private  String getSelectedStartTime() {

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        cal.set(mSelectedYear, mSelectedMonth, mSelectedDay,mSelectedStartHour,mSelectedStartMinute);
        Date date = cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:MM:SS", Locale.ENGLISH);

        return sdf.format(date);
    }

    private  String getSelectedEndTime() {

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        cal.set(mSelectedYear, mSelectedMonth, mSelectedDay,mSelectedEndHour,mSelectedEndMinte);
        Date date = cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:MM:SS", Locale.ENGLISH);

        return sdf.format(date);
    }

    private void displayTimePicker(boolean isStartTime){
        // Get Current Time
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {
                        if(isStartTime) {
                            mSelectedStartHour = hourOfDay;
                            mSelectedStartMinute = minute;
                            tackerListBinding.startTimeEdt.setText(hourOfDay + ":" + minute);
                        }else{
                            mSelectedEndHour = hourOfDay;
                            mSelectedEndMinte = minute;
                            tackerListBinding.endTimeEdt.setText(hourOfDay + ":" + minute);
                        }
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }

    private String getServiceName(int serviceId){
        if(serviceId == 1){
            return "dog walking";
        }else if(serviceId == 2){
            return "dog boarding";
        }else if(serviceId == 3){
            return "dog sitting";
        }else if(serviceId == 4){
            return "dog day care";
        }else if(serviceId == 5){
            return "cat boarding";
        }else if(serviceId == 6){
            return "cat sitting";
        }else if(serviceId == 7){
            return "drop in visits";
        }
        return null;
    }

    private String getTotalTime(String startTime, String endTime, boolean isPrice, int perHourCharge){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        Date startDate = null;
        try {
            startDate = simpleDateFormat.parse(startTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date endDate = null;
        try {
            endDate = simpleDateFormat.parse(endTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long difference = endDate.getTime() - startDate.getTime();
        if(difference<0)
        {
            Date dateMax = null;
            try {
                dateMax = simpleDateFormat.parse("24:00");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date dateMin = null;
            try {
                dateMin = simpleDateFormat.parse("00:00");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            difference=(dateMax.getTime() -startDate.getTime() )+(endDate.getTime()-dateMin.getTime());
        }
        int days = (int) (difference / (1000*60*60*24));
        int hours = (int) ((difference - (1000*60*60*24*days)) / (1000*60*60));
        int min = (int) (difference - (1000*60*60*24*days) - (1000*60*60*hours)) / (1000*60);
        Log.i("log_tag","Hours: "+hours+", Mins: "+min);

        if(isPrice){
            totalAmount =  hours*perHourCharge;
          return String.valueOf(totalAmount);
        }
        return String.format("%02d:%02d", hours, min);



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {// API 5+ solution
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
