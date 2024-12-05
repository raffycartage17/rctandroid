package com.racartech.library.rctandroid.time;


import android.icu.util.Calendar;
import android.icu.util.TimeZone;

import java.text.ParseException;

public class RCTdateTimeData {

    public final static long MILLIS_IN_WEEK = 604800000L;
    public final static long MILLIS_IN_DAY = 86400000L;
    public final static long MILLIS_IN_HOUR = 3600000L;
    public final static long MILLIS_IN_MINUTE = 60000L;
    public final static long MILLIS_IN_SECOND = 1000L;

    //Note , Month parameter should be passed as 1-12
    public int YEAR;
    public int MONTH;
    public int DATE;
    public int HOUR;
    public int MINUTE;
    public int SECOND;
    public int MILLISECOND;

    public int DAY_OF_WEEK;
    public int DAY_OF_YEAR;

    public long UNIX_EPOCH_MILLISECOND;

    public RCTdateTimeData(String timestamp, TimeZone time_zone) throws ParseException {
        long unix_epoch_ms = RCTdateTime.toMillis(timestamp,time_zone);
        Calendar current_time = Calendar.getInstance();
        current_time.setTimeInMillis(unix_epoch_ms); // Set time using milliseconds
        this.YEAR = current_time.get(Calendar.YEAR);
        this.MONTH = current_time.get(Calendar.MONTH) + 1; // Adding 1 as Calendar.MONTH is zero-based
        this.DATE = current_time.get(Calendar.DATE);
        this.HOUR = current_time.get(Calendar.HOUR_OF_DAY);
        this.MINUTE = current_time.get(Calendar.MINUTE);
        this.SECOND = current_time.get(Calendar.SECOND);
        this.MILLISECOND = current_time.get(Calendar.MILLISECOND);
        this.DAY_OF_WEEK = current_time.get(Calendar.DAY_OF_WEEK);
        this.DAY_OF_YEAR = current_time.get(Calendar.DAY_OF_YEAR);
        this.UNIX_EPOCH_MILLISECOND = unix_epoch_ms;
    }


    public RCTdateTimeData(long unix_epoch_millis) {
        Calendar current_time = Calendar.getInstance();
        current_time.setTimeInMillis(unix_epoch_millis); // Set time using milliseconds

        this.YEAR = current_time.get(Calendar.YEAR);
        this.MONTH = current_time.get(Calendar.MONTH) + 1; // Adding 1 as Calendar.MONTH is zero-based
        this.DATE = current_time.get(Calendar.DATE);
        this.HOUR = current_time.get(Calendar.HOUR_OF_DAY);
        this.MINUTE = current_time.get(Calendar.MINUTE);
        this.SECOND = current_time.get(Calendar.SECOND);
        this.MILLISECOND = current_time.get(Calendar.MILLISECOND);
        this.DAY_OF_WEEK = current_time.get(Calendar.DAY_OF_WEEK);
        this.DAY_OF_YEAR = current_time.get(Calendar.DAY_OF_YEAR);
        this.UNIX_EPOCH_MILLISECOND = unix_epoch_millis;
    }


    public RCTdateTimeData(int year) {
        this(year, 1, 0, 0, 0, 0, 0);
    }
    public RCTdateTimeData(int year, int month) {
        this(year, month, 1, 0, 0, 0, 0);
    }
    public RCTdateTimeData(int year, int month, int date) {
        this(year, month, date, 0, 0, 0, 0);
    }

    public RCTdateTimeData(int year, int month, int date, int hour) {
        this(year, month, date, hour, 0, 0, 0);
    }

    public RCTdateTimeData(int year, int month, int date, int hour, int minute) {
        this(year, month, date, hour, minute, 0, 0);
    }

    public RCTdateTimeData(int year, int month, int date, int hour, int minute, int second) {
        this(year, month, date, hour, minute, second, 0);
    }

    public RCTdateTimeData(int year, int month, int date, int hour, int minute, int second, int millisecond) {
        YEAR = year;
        MONTH = month;
        DATE = date;
        HOUR = hour;
        MINUTE = minute;
        SECOND = second;
        MILLISECOND = millisecond;

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, date, hour, minute, second);
        calendar.set(Calendar.MILLISECOND, millisecond);

        this.UNIX_EPOCH_MILLISECOND = calendar.getTimeInMillis();
        this.DAY_OF_WEEK = calendar.get(Calendar.DAY_OF_WEEK);
        this.DAY_OF_YEAR = calendar.get(Calendar.DAY_OF_YEAR);


        if(MONTH < 1 ){
            MONTH = 1;
        }else if(MONTH > 12){
            MONTH = 12;
        }

        if(DATE < 1){
            DATE = 1;
        }else if(DATE > RCTdateTime.getDayCountInMonth(MONTH,YEAR)){
            DATE = RCTdateTime.getDayCountInMonth(MONTH,YEAR);
        }

        if(HOUR < 0){
            HOUR = 0;
        }else if(HOUR > 23){
            HOUR = 23;
        }

        if(MINUTE < 0){
            MINUTE = 0;
        }else if(MINUTE > 59){
            MINUTE = 59;
        }

        if(SECOND < 0){
            SECOND = 0;
        }else if(SECOND > 59){
            SECOND = 59;
        }

        if(MILLISECOND < 0){
            MILLISECOND = 0;
        }else if(MILLISECOND > 999){
            MILLISECOND = 999;
        }




    }


    public RCTdateTimeData subtractWeek(int day){
        long subtract_millis = this.UNIX_EPOCH_MILLISECOND - (day*MILLIS_IN_WEEK);
        return new RCTdateTimeData(subtract_millis);
    }

    public RCTdateTimeData addWeek(int day){
        long add_millis = this.UNIX_EPOCH_MILLISECOND + (day*MILLIS_IN_WEEK);
        return new RCTdateTimeData(add_millis);
    }
    public RCTdateTimeData subtractDay(int day){
        long subtract_day_millis = this.UNIX_EPOCH_MILLISECOND - (day*MILLIS_IN_DAY);
        return new RCTdateTimeData(subtract_day_millis);
    }

    public RCTdateTimeData addDay(int day){
        long add_day_millis = this.UNIX_EPOCH_MILLISECOND + (day*MILLIS_IN_DAY);
        return new RCTdateTimeData(add_day_millis);
    }

    public RCTdateTimeData subtractHour(int hour){
        long subtract_hour_millis = this.UNIX_EPOCH_MILLISECOND - (hour*MILLIS_IN_HOUR);
        return new RCTdateTimeData(subtract_hour_millis);
    }
    public RCTdateTimeData addHour(int hour){
        long add_hour_millis = this.UNIX_EPOCH_MILLISECOND + (hour*MILLIS_IN_HOUR);
        return new RCTdateTimeData(add_hour_millis);
    }

    public RCTdateTimeData subtractMinute(int hour){
        long subtract_millis = this.UNIX_EPOCH_MILLISECOND - (hour*MILLIS_IN_MINUTE);
        return new RCTdateTimeData(subtract_millis);
    }
    public RCTdateTimeData addMinute(int hour){
        long add_millis = this.UNIX_EPOCH_MILLISECOND + (hour*MILLIS_IN_MINUTE);
        return new RCTdateTimeData(add_millis);
    }



    public RCTdateTimeData getExactYear(){
        return new RCTdateTimeData(this.YEAR);
    }
    public RCTdateTimeData getExactMonth(){
        return new RCTdateTimeData(this.YEAR, this.MONTH);
    }
    public RCTdateTimeData getExactDate(){
        return new RCTdateTimeData(this.YEAR, this.MONTH, this.DATE);
    }
    public RCTdateTimeData getExactHour(){
        return new RCTdateTimeData(this.YEAR, this.MONTH, this.DATE, this.HOUR);
    }

    public RCTdateTimeData getExactMinute(){
        return new RCTdateTimeData(this.YEAR, this.MONTH, this.DATE, this.HOUR, this.MINUTE);
    }
    public RCTdateTimeData getExactSecond(){
        return new RCTdateTimeData(this.YEAR, this.MONTH, this.DATE, this.HOUR, this.MINUTE,this.SECOND);
    }

    public boolean isLeapYear() {
        return RCTtime.isLeapYear(this.YEAR);
    }




    public void print(){
        System.out.println("Year        : ".concat(String.valueOf(this.YEAR)));
        System.out.println("Month       : ".concat(String.valueOf(this.MONTH)));
        System.out.println("Date        : ".concat(String.valueOf(this.DATE)));
        System.out.println("Hour        : ".concat(String.valueOf(this.HOUR)));
        System.out.println("Minute      : ".concat(String.valueOf(this.MINUTE)));
        System.out.println("Second      : ".concat(String.valueOf(this.SECOND)));
        System.out.println("Milisecond  : ".concat(String.valueOf(this.MILLISECOND)));
        System.out.println("Unix Epoch  : ".concat(String.valueOf(this.UNIX_EPOCH_MILLISECOND)));
    }





}


