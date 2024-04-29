package com.racartech.library.rctandroid.time;


import java.util.Calendar;
import java.util.Locale;

public class RCTsimpleDateTimeData {

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

    public RCTsimpleDateTimeData(long unix_epoch_millis) {
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


    public RCTsimpleDateTimeData(int year) {
        this(year, 1, 0, 0, 0, 0, 0);
    }
    public RCTsimpleDateTimeData(int year, int month) {
        this(year, month, 1, 0, 0, 0, 0);
    }
    public RCTsimpleDateTimeData(int year, int month, int date) {
        this(year, month, date, 0, 0, 0, 0);
    }

    public RCTsimpleDateTimeData(int year, int month, int date, int hour) {
        this(year, month, date, hour, 0, 0, 0);
    }

    public RCTsimpleDateTimeData(int year, int month, int date, int hour, int minute) {
        this(year, month, date, hour, minute, 0, 0);
    }

    public RCTsimpleDateTimeData(int year, int month, int date, int hour, int minute, int second) {
        this(year, month, date, hour, minute, second, 0);
    }

    public RCTsimpleDateTimeData(int year, int month, int date, int hour, int minute, int second, int millisecond) {
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



    public RCTsimpleDateTimeData subtractWeek(int day){
        long subtract_millis = this.UNIX_EPOCH_MILLISECOND - (day*MILLIS_IN_WEEK);
        return new RCTsimpleDateTimeData(subtract_millis);
    }

    public RCTsimpleDateTimeData addWeek(int day){
        long add_millis = this.UNIX_EPOCH_MILLISECOND + (day*MILLIS_IN_WEEK);
        return new RCTsimpleDateTimeData(add_millis);
    }
    public RCTsimpleDateTimeData subtractDay(int day){
        long subtract_day_millis = this.UNIX_EPOCH_MILLISECOND - (day*MILLIS_IN_DAY);
        return new RCTsimpleDateTimeData(subtract_day_millis);
    }

    public RCTsimpleDateTimeData addDay(int day){
        long add_day_millis = this.UNIX_EPOCH_MILLISECOND + (day*MILLIS_IN_DAY);
        return new RCTsimpleDateTimeData(add_day_millis);
    }

    public RCTsimpleDateTimeData subtractHour(int hour){
        long subtract_hour_millis = this.UNIX_EPOCH_MILLISECOND - (hour*MILLIS_IN_HOUR);
        return new RCTsimpleDateTimeData(subtract_hour_millis);
    }
    public RCTsimpleDateTimeData addHour(int hour){
        long add_hour_millis = this.UNIX_EPOCH_MILLISECOND + (hour*MILLIS_IN_HOUR);
        return new RCTsimpleDateTimeData(add_hour_millis);
    }

    public RCTsimpleDateTimeData subtractMinute(int hour){
        long subtract_millis = this.UNIX_EPOCH_MILLISECOND - (hour*MILLIS_IN_MINUTE);
        return new RCTsimpleDateTimeData(subtract_millis);
    }
    public RCTsimpleDateTimeData addMinute(int hour){
        long add_millis = this.UNIX_EPOCH_MILLISECOND + (hour*MILLIS_IN_MINUTE);
        return new RCTsimpleDateTimeData(add_millis);
    }



    public RCTsimpleDateTimeData getExactYear(){
        return new RCTsimpleDateTimeData(this.YEAR);
    }
    public RCTsimpleDateTimeData getExactMonth(){
        return new RCTsimpleDateTimeData(this.YEAR, this.MONTH);
    }
    public RCTsimpleDateTimeData getExactDate(){
        return new RCTsimpleDateTimeData(this.YEAR, this.MONTH, this.DATE);
    }
    public RCTsimpleDateTimeData getExactHour(){
        return new RCTsimpleDateTimeData(this.YEAR, this.MONTH, this.DATE, this.HOUR);
    }

    public RCTsimpleDateTimeData getExactMinute(){
        return new RCTsimpleDateTimeData(this.YEAR, this.MONTH, this.DATE, this.HOUR, this.MINUTE);
    }
    public RCTsimpleDateTimeData getExactSecond(){
        return new RCTsimpleDateTimeData(this.YEAR, this.MONTH, this.DATE, this.HOUR, this.MINUTE,this.SECOND);
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


