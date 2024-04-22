package com.racartech.library.rctandroid.time;


import java.util.Calendar;

public class RCTsimpleDateTimeData {
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

}


