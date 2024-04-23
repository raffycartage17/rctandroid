package com.racartech.app.rctandroidlts.functionbuttons;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.icu.util.Calendar;
import android.widget.DatePicker;

import com.racartech.library.rctandroid.phone.RCTdial;
import com.racartech.library.rctandroid.time.RCTsimpleDateTimeData;

public class FunctionOne{


    public static void launch(Context context, Activity activity) {
        openDatePicker(context);
    }


    private static void openDatePicker(Context context) {
        // Get the current date
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        // Create a DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                System.out.println("Year    : ".concat(String.valueOf(year)));
                System.out.println("Month   : ".concat(String.valueOf(month)));
                System.out.println("Day     : ".concat(String.valueOf(day)));
            }
        }, year, month, dayOfMonth);


        datePickerDialog.getDatePicker().setMinDate(
                new RCTsimpleDateTimeData(System.currentTimeMillis()).UNIX_EPOCH_MILLISECOND);
        datePickerDialog.getDatePicker().setMaxDate(
                new RCTsimpleDateTimeData(System.currentTimeMillis()).addDay(7).UNIX_EPOCH_MILLISECOND);



        datePickerDialog.show();
    }



    /*



import java.util.Calendar;
import java.util.Locale;

public class RCTsimpleDateTimeData {

    //Note , Month parameter should be passed as 1-12
    public int YEAR;
    public int MONTH;
    public int DATE;
    public int HOUR;
    public int MINUTE;
    public int SECOND;
    public int MILLISECOND;

    public long UNIX_EPOCH_MILLISECOND;

    public RCTsimpleDateTimeData(long unix_epoch_millis) {
        Calendar current_time = Calendar.getInstance();
        current_time.setTimeInMillis(unix_epoch_millis); // Set time using milliseconds

        YEAR = current_time.get(Calendar.YEAR);
        MONTH = current_time.get(Calendar.MONTH) + 1; // Adding 1 as Calendar.MONTH is zero-based
        DATE = current_time.get(Calendar.DATE);
        HOUR = current_time.get(Calendar.HOUR_OF_DAY);
        MINUTE = current_time.get(Calendar.MINUTE);
        SECOND = current_time.get(Calendar.SECOND);
        MILLISECOND = current_time.get(Calendar.MILLISECOND);
        this.UNIX_EPOCH_MILLISECOND = unix_epoch_millis;
    }

    public RCTsimpleDateTimeData(int year, int month, int day) {
        this(year, month, day, 0, 0, 0, 0);
    }

    public RCTsimpleDateTimeData(int year, int month, int day, int hour) {
        this(year, month, day, hour, 0, 0, 0);
    }

    public RCTsimpleDateTimeData(int year, int month, int day, int hour, int minute) {
        this(year, month, day, hour, minute, 0, 0);
    }

    public RCTsimpleDateTimeData(int year, int month, int day, int hour, int minute, int second) {
        this(year, month, day, hour, minute, second, 0);
    }

    public RCTsimpleDateTimeData(int year, int month, int day, int hour, int minute, int second, int millisecond) {
        YEAR = year;
        MONTH = month;
        DATE = day;
        HOUR = hour;
        MINUTE = minute;
        SECOND = second;
        MILLISECOND = millisecond;

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day, hour, minute, second);
        calendar.set(Calendar.MILLISECOND, millisecond);

        this.UNIX_EPOCH_MILLISECOND = calendar.getTimeInMillis();
    }


    public String getTimeStamp_YYYY_MM_DD(){
        // Create a timestamp string with this format "YYYY-MM-DD"
        return String.format(Locale.US, "%04d-%02d-%02d", YEAR, MONTH, DATE);
    }

    public String getTimeStamp_YYYY_MM_DD_HH_MM(){
        // Create a timestamp string with this format "YYYY-MM-DD-HH-MM"
        return String.format(Locale.US, "%04d-%02d-%02d-%02d-%02d", YEAR, MONTH, DATE, HOUR, MINUTE);
    }

    public String getTimeStamp_YYYY_MM_DD_HH_MM_SS(){
        // Create a timestamp string with this format "YYYY-MM-DD-HH-MM-SS"
        return String.format(Locale.US, "%04d-%02d-%02d-%02d-%02d-%02d", YEAR, MONTH, DATE, HOUR, MINUTE, SECOND);
    }

}



     */







}
