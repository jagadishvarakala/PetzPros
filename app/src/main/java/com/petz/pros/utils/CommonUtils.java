package com.petz.pros.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.petz.pros.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.Deflater;


/**
 * Created on : Jan 19, 2019
 * Author     : AndroidWave
 * Email    : info@androidwave.com
 */
public class CommonUtils {
    private CommonUtils() {
        // This utility class is not publicly instantiable
    }

    public static ProgressDialog showLoadingDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.show();
        if (progressDialog.getWindow() != null) {
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        progressDialog.setContentView(R.layout.progress_dialog);
        ImageView imageView = progressDialog.findViewById(R.id.pb_loading);
        Glide.with(context).load(R.raw.loader).into(imageView);

        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }
    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    public static boolean validatePassword(final String password){
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "[a-zA-Z0-9\\!\\@\\#\\$]{6,24}";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();
    }

    public static String convertDate(String dateString){

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss",Locale.US);
        Date date = null;
        try {
            date = formatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat newFormat = new SimpleDateFormat("dd-MMM-yyyy",Locale.US);
        return newFormat.format(date);
    }

    public static String convertTime(String time){

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss",Locale.getDefault());
        Date date = null;
        try {
            date = formatter.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat newFormat = new SimpleDateFormat("hh:mm a",Locale.getDefault());
        return newFormat.format(date);
    }

    public static String convertMonth(String appointmentDate){
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
        Date date = null;
        try {
            date = formatter.parse(appointmentDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat newFormat = new SimpleDateFormat("MMM", Locale.getDefault());
        return newFormat.format(date);
    }

    public static String convertAppointDate(String appointmentDate){
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
        Date date = null;
        try {
            date = formatter.parse(appointmentDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat newFormat = new SimpleDateFormat("dd", Locale.getDefault());
        return newFormat.format(date);
    }

    public static String convertAppointYear(String appointmentDate){
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
        Date date = null;
        try {
            date = formatter.parse(appointmentDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat newFormat = new SimpleDateFormat("YYYY", Locale.getDefault());
        return newFormat.format(date);
    }

    public static String getCurrentDateTime(){
        Calendar c = Calendar.getInstance();
        System.out.println("Current time => "+c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss",Locale.getDefault());
        return  df.format(c.getTime());
    }

    public static String convertHHMMDD(String appointmentDate){
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
        Date date = null;
        try {
            date = formatter.parse(appointmentDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat newFormat = new SimpleDateFormat("dd-MMM-yyyy hh:mm a", Locale.getDefault());
        return newFormat.format(date);
    }

    public static String convertedStringDate(String date){
        SimpleDateFormat dateFormat= new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        SimpleDateFormat newFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        try {
            Date d=dateFormat.parse(date);
           return newFormat.format(d);
        }
        catch(Exception e) {
            //java.text.ParseException: Unparseable date: Geting error
            System.out.println("Excep"+e);
        }
        return "";
    }
}
