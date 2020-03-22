package com.petz.pros.utils;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TestClass {

    public static void main(String[] args) {

        int mrp = 400;
        double mints = 60;
       double perminitValue =  mrp/mints;
        double value = rate(30, perminitValue);
        System.out.println(perminitValue);
        System.out.println(value);

    }
    public static double rate(int minutes, double perHourCharge)
    {
        return perHourCharge*(minutes);
    }
}
