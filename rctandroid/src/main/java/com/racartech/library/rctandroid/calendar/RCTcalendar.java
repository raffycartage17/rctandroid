package com.racartech.library.rctandroid.calendar;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class RCTcalendar{


    public static int getWeekDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    public static int getWeekDay(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    public static int getWeekDay(String date, SimpleDateFormat formatter) throws ParseException {
        Date parsedDate = formatter.parse(date);
        return getWeekDay(parsedDate);
    }



    public static SimpleDateFormat getDateFormatMMDDYYYY() {
        return new SimpleDateFormat("MM-dd-yyyy");
    }

    public static SimpleDateFormat getDateFormatYYYYMMDD() {
        return new SimpleDateFormat("yyyy-MM-dd");
    }

    public static SimpleDateFormat getDateFormatDDMMYYYY() {
        return new SimpleDateFormat("dd-MM-yyyy");
    }





    public static String getDate(long millis, SimpleDateFormat formatter) {
        Date date = new Date(millis);
        return formatter.format(date);
    }

    public static long getMillis(String date, SimpleDateFormat formatter) throws ParseException {
        Date parsedDate = formatter.parse(date);
        return parsedDate.getTime();
    }

    public static long getMillis(Date date) {
        return date.getTime();
    }

    public static Date getDate(long millis) {
        return new Date(millis);
    }

    public static String getDate(Date date, SimpleDateFormat formatter) {
        return formatter.format(date);
    }

    public static Date[] getDatesInYear(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.DAY_OF_YEAR, 1);
        List<Date> dates = new ArrayList<>();
        while (calendar.get(Calendar.YEAR) == year) {
            dates.add(calendar.getTime());
            calendar.add(Calendar.DAY_OF_YEAR, 1);
        }
        return dates.toArray(new Date[0]);
    }








    public static String[] getNextDates(int no_of_days,String pattern){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.getDefault());
        String[] dates = new String[no_of_days];
        dates[0] = sdf.format(calendar.getTime());


        for (int i = 1; i < no_of_days; i++) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            dates[i] = sdf.format(calendar.getTime());
        }
        return dates;
    }


    public static Date getCurrentDate(){
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }

    public static Date getDate(int year, int month, int day_of_month, int hour_of_day, int minute, int seconds, int millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month-1, day_of_month, hour_of_day, minute, seconds);
        calendar.set(Calendar.MILLISECOND, millis);
        Date date = calendar.getTime();
        return date;
    }

    //Second date should be the later date/time
    public static long getTimeDifference(Date first_date, Date second_date) {
        long diff = second_date.getTime() - first_date.getTime();
        return diff;
    }

    public static double millisToSecond(long millis){
        return millis/1000.0;
    }
    public static double millisToMinute(long millis){
        double value = 1000.0*60.0;
        return millis/value;
    }
    public static double millisToHour(long millis){
        double value = 1000.0*60.0*60.0;
        return millis/value;
    }

    public static double millisToDay(long millis){
        double value = 1000*60.0*60.0*24.0;
        return millis/value;
    }

    public static double secondToMillis(long second){
        return second*1000.0;
    }
    public static double minuteToMillis(long minute){
        return minute*(1000.0*60.0);
    }
    public static double hourToMillis(long hour){
        return hour*(1000.0*60.0*60.0);
    }
    public static double dayToMillis(long day){
        return day*(1000.0*60.0*60.0*24.0);
    }


    public static int[] getTimeDataValues(Date date) {
        int[] values = new int[7];
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        values[0] = calendar.get(Calendar.YEAR);
        values[1] = calendar.get(Calendar.MONTH) + 1; // add 1 because January is 0
        values[2] = calendar.get(Calendar.DAY_OF_MONTH);
        values[3] = calendar.get(Calendar.HOUR_OF_DAY); // use HOUR_OF_DAY to get 24-hour format
        values[4] = calendar.get(Calendar.MINUTE);
        values[5] = calendar.get(Calendar.SECOND);
        values[6] = calendar.get(Calendar.MILLISECOND);
        return values;
    }

    public static boolean isLeapYear(int year) {
        boolean isLeapYear;
        isLeapYear = (year % 4 == 0);
        isLeapYear = isLeapYear && (year % 100 != 0 || year % 400 == 0);
        return isLeapYear;
    }

    public static Date addDaysToDate(Date date, int daysToAdd) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, daysToAdd);
        return calendar.getTime();
    }

    public static Date subtractDaysFromDate(Date date, int daysToSubtract) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -daysToSubtract);
        return calendar.getTime();
    }

    public static int getDaysInMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1); // set to the first day of the month
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public static Date getFirstDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1); // set to the first day of the month
        return calendar.getTime();
    }

    public static Date getLastDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, lastDay); // set to the last day of the month
        return calendar.getTime();
    }

    public static Date addTimeToDate(Date date, int hours, int minutes, int seconds, int milliseconds) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, hours);
        calendar.add(Calendar.MINUTE, minutes);
        calendar.add(Calendar.SECOND, seconds);
        calendar.add(Calendar.MILLISECOND, milliseconds);
        return calendar.getTime();
    }

    public static String getMonthName(int month) {
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();
        return months[month - 1];
    }

    public static String getWeekdayName(Date date) {
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] weekdays = dfs.getWeekdays();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return weekdays[dayOfWeek];
    }

    public static int getWeekNumber(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    public static long getDaysBetween(Date start, Date end) {
        long diff = end.getTime() - start.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public static String formatDate(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.getDefault());
        return sdf.format(date);
    }

    public static Date parseDate(String dateString, String pattern) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.getDefault());
        return sdf.parse(dateString);
    }


    //0-11
    public static int getMonthFromMillis(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.get(Calendar.MONTH);
    }









}
