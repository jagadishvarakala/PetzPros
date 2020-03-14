package com.petz.pros.ui.bottomsheet;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.textfield.TextInputLayout;
import com.petz.pros.R;
import com.petz.pros.ui.main.ui.home.HomeMvpView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class BottomSheetFragment extends BottomSheetDialogFragment {

    private int mYear, mMonth, mDay, mHour, mMinute;
    private int mSelectedYear, mSelectedMonth, mSelectedDay, mSelectedStartHour, mSelectedStartMinute,mSelectedEndHour,mSelectedEndMinte;
    private EditText appointmentDateText,startDatetxt,endDateTxt;
    private TextInputLayout appointTextInput ,startTextInput,endTextInput;
    private HomeMvpView clickListiner;
    public BottomSheetFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setClicklisterner(HomeMvpView clicklisterner){
        this.clickListiner = clicklisterner;
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet, container, false);
        appointmentDateText = view.findViewById(R.id.apponitment_date);
        startDatetxt = view.findViewById(R.id.start_time);
        endDateTxt = view.findViewById(R.id.end_time);
         appointTextInput = view.findViewById(R.id.text_input_layout);
         startTextInput = view.findViewById(R.id.start_text_input_layout);
         endTextInput = view.findViewById(R.id.end_text_input_layout);
        Button btnSubmit = view.findViewById(R.id.proceed_btn);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(appointmentDateText.getText().toString())){
                    appointTextInput.setError("Select Date");
                    return;
                }else if(TextUtils.isEmpty(startDatetxt.getText().toString())){
                    startTextInput.setError("Select Start Time");
                    return;
                }else if(TextUtils.isEmpty(endDateTxt.getText().toString())){
                    endTextInput.setError("Select End Time");
                    return;
                }
                SimpleDateFormat sdf = new SimpleDateFormat("hh:mm", Locale.getDefault());
                Date inTime = null;
                Date outTime = null;
                try {
                    inTime = sdf.parse(startDatetxt.getText().toString());
                    outTime = sdf.parse(endDateTxt.getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                if (outTime != null) {
                    if(isTimeAfter(inTime, outTime)) {
                        endTextInput.setError("Start time exceeded End time");
                    }else{
                        if(clickListiner != null){
                            dismiss();
                            clickListiner.onClickProceed(mSelectedYear,mSelectedMonth,mSelectedDay,mSelectedStartHour,mSelectedStartMinute,mSelectedEndHour,mSelectedEndMinte);
                        }
                    }
                }
            }
        });
        LinearLayout linearLayout = view.findViewById(R.id.apponitment_date_layout);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayDatePicker();
            }
        });
        LinearLayout linearLayout1 = view.findViewById(R.id.start_time_layout);
        linearLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayTimePicker(true);
            }
        });
        LinearLayout linearLayout2 = view.findViewById(R.id.end_time_layout);
        linearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayTimePicker(false);
            }
        });


        return view;
    }
    private void displayDatePicker(){
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        mSelectedYear = year;
                        mSelectedMonth = monthOfYear;
                        mSelectedDay = dayOfMonth;
                        appointmentDateText.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        appointTextInput.setError(null);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }

    private void displayTimePicker(boolean isStartTime){
        // Get Current Time
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {
                        String time = hourOfDay + ":" + minute;

                        SimpleDateFormat fmt = new SimpleDateFormat("HH:mm",Locale.getDefault());
                        Date date = null;
                        try {
                            date = fmt.parse(time );
                        } catch (ParseException e) {

                            e.printStackTrace();
                        }

                        SimpleDateFormat fmtOut = new SimpleDateFormat("hh:mm aa",Locale.getDefault());

                        String formattedTime=fmtOut.format(date);

                        if(isStartTime) {
                            mSelectedStartHour = hourOfDay;
                            mSelectedStartMinute = minute;
                            startDatetxt.setText(formattedTime);
                            startTextInput.setError(null);
                        }else{
                            mSelectedEndHour = hourOfDay;
                            mSelectedEndMinte = minute;
                            endDateTxt.setText(formattedTime);
                            endTextInput.setError(null);
                        }
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }

    private boolean isTimeAfter(Date startTime, Date endTime) {
        return !endTime.after(startTime);
    }
}
