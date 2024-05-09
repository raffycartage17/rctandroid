package com.racartech.library.rctandroid.time;

import java.util.Calendar;


@Deprecated
public class RCTtimeData {

    public int YEAR = 0; // current year
    public int MONTH = 1; // 1 to 12 for the month
    public int DATE = 0; // start from 1
    public int HOUR = 0; // military hour 0-23
    public int MINUTE = 0; // 0-59
    public int SECOND = 0; // 0-59
    public int MILLISECOND = 0; // 0-999

    public RCTtimeData() {
        Calendar current_time = Calendar.getInstance();
        YEAR = current_time.get(Calendar.YEAR);
        MONTH = current_time.get(Calendar.MONTH) + 1; // Adding 1 as Calendar.MONTH is zero-based
        DATE = current_time.get(Calendar.DATE);
        HOUR = current_time.get(Calendar.HOUR_OF_DAY);
        MINUTE = current_time.get(Calendar.MINUTE);
        SECOND = current_time.get(Calendar.SECOND);
        MILLISECOND = current_time.get(Calendar.MILLISECOND);
    }

    public RCTtimeData(long millis) {
        // This is for the given millis
        Calendar given_time = Calendar.getInstance();
        given_time.setTimeInMillis(millis);
        YEAR = given_time.get(Calendar.YEAR);
        MONTH = given_time.get(Calendar.MONTH) + 1; // Adding 1 as Calendar.MONTH is zero-based
        DATE = given_time.get(Calendar.DATE);
        HOUR = given_time.get(Calendar.HOUR_OF_DAY);
        MINUTE = given_time.get(Calendar.MINUTE);
        SECOND = given_time.get(Calendar.SECOND);
        this.MILLISECOND = given_time.get(Calendar.MILLISECOND);
    }

    public RCTtimeData(int year, int month, int date, int hour, int minute, int second, int millisecond) {
        // Handle excess time
        if (second >= 60) {
            int extraMinutes = second / 60; // Calculate the number of excess minutes
            second %= 60; // Adjust the second value to be within 0-59 range
            minute += extraMinutes; // Add the excess minutes to the minute value
        }

        if (minute >= 60) {
            int extraHours = minute / 60; // Calculate the number of excess hours
            minute %= 60; // Adjust the minute value to be within 0-59 range
            hour += extraHours; // Add the excess hours to the hour value
        }

        if (hour >= 24) {
            int extraDays = hour / 24; // Calculate the number of excess days
            hour %= 24; // Adjust the hour value to be within 0-23 range
            date += extraDays; // Add the excess days to the date value
        }

        if (month >= 13) {
            int extraYears = month / 12; // Calculate the number of excess years
            month %= 12; // Adjust the month value to be within 1-12 range
            year += extraYears; // Add the excess years to the year value
        }

        // Handle excess days based on the number of days in each month
        while (date > getDaysInMonth(year, month)) {
            date -= getDaysInMonth(year, month); // Subtract the number of days in the current month
            month++; // Move to the next month

            if (month > 12) {
                month = 1; // Reset month to 1 if it exceeds 12
                year++; // Increment the year for the next month
            }
        }

        // Set the time values
        this.YEAR = year;
        this.MONTH = month;
        this.DATE = date;
        this.HOUR = hour;
        this.MINUTE = minute;
        this.SECOND = second;
        this.MILLISECOND = millisecond;
    }

    private int getDaysInMonth(int year, int month) {
        if (month == 2) {
            if (isLeapYear(year)) {
                return 29; // February has 29 days in a leap year
            } else {
                return 28; // February has 28 days in a non-leap year
            }
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            return 30; // April, June, September, and November have 30 days
        } else {
            return 31; // January, March, May, July, August, October, and December have 31 days
        }
    }

    private boolean isLeapYear(int year) {
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                return year % 400 == 0; // Divisible by 4, 100, and 400 (leap year)
            } else {
                return true; // Divisible by 4 but not by 100 (leap year)
            }
        } else {
            return false; // Not divisible by 4 (non-leap year)
        }
    }


    public boolean isLeapYear() {
        return RCTtime.isLeapYear(this.YEAR);
    }

    public long toMillis(){
        return RCTtime.getMillis(this);
    }




}

