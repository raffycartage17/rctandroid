package com.racartech.library.rctandroid.time;

import java.io.IOException;
import java.text.ParseException;
import android.icu.text.SimpleDateFormat;
import java.util.ArrayList;
import android.icu.util.Calendar;
import java.util.Date;
import java.util.Locale;
import android.icu.util.TimeZone;

public class RCTdateTime{


    public static void test(){
        RCTdateTimeData time_data = new RCTdateTimeData(System.currentTimeMillis());
    }

    /**
     * <h2>Description</h2>
     * Returns time difference between 2 between the two parameters object in millisecond (x - y)
     * @author Rafael Andaya Cartagena
     * @since 2024-05-09
     */
    public static long getTimeDifference(RCTdateTimeData x, RCTdateTimeData y){
        return x.UNIX_EPOCH_MILLISECOND - y.UNIX_EPOCH_MILLISECOND;
    }

    /**
     * <h2>Description</h2>
     * Returns time difference between 2 between the two parameters unix epoch millisecond value (x - y)
     * @author Rafael Andaya Cartagena
     * @since 2024-05-09
     */
    public static long getTimeDifference(long x, long y){
        return x-y;
    }



    /**
     * <h2>Description</h2>
     * Returns time difference between 2 between the two parameters unix epoch millisecond value (x - y)
     * @author Rafael Andaya Cartagena
     * @since 2024-05-09
     */
    public static long getWeekDifference(RCTdateTimeData x, RCTdateTimeData y){
        long ms_difference = getTimeDifference(x,y);
        return ms_difference/RCTdateTimeData.MILLIS_IN_WEEK;
    }

    /**
     * <h2>Description</h2>
     * Returns time difference between 2 between the two parameters unix epoch millisecond value (x - y)
     * @author Rafael Andaya Cartagena
     * @since 2024-05-09
     */
    public static double getWeekDifference_Exact(RCTdateTimeData x, RCTdateTimeData y){
        long ms_difference = getTimeDifference(x,y);
        return (((double)ms_difference)/((double)RCTdateTimeData.MILLIS_IN_WEEK));
    }


    /**
     * <h2>Description</h2>
     * Returns time difference between 2 between the two parameters unix epoch millisecond value (x - y)
     * @author Rafael Andaya Cartagena
     * @since 2024-05-09
     */
    public static long getDayDifference(RCTdateTimeData x, RCTdateTimeData y){
        long ms_difference = getTimeDifference(x,y);
        return ms_difference/RCTdateTimeData.MILLIS_IN_DAY;
    }

    /**
     * <h2>Description</h2>
     * Returns time difference between 2 between the two parameters unix epoch millisecond value (x - y)
     * @author Rafael Andaya Cartagena
     * @since 2024-05-09
     */
    public static double getDayDifference_Exact(RCTdateTimeData x, RCTdateTimeData y){
        long ms_difference = getTimeDifference(x,y);
        return (((double)ms_difference)/((double)RCTdateTimeData.MILLIS_IN_DAY));
    }


    /**
     * <h2>Description</h2>
     * Returns time difference between 2 between the two parameters unix epoch millisecond value (x - y)
     * @author Rafael Andaya Cartagena
     * @since 2024-05-09
     */
    public static long getHourDifference(RCTdateTimeData x, RCTdateTimeData y){
        long ms_difference = getTimeDifference(x,y);
        return ms_difference/RCTdateTimeData.MILLIS_IN_HOUR;
    }

    /**
     * <h2>Description</h2>
     * Returns time difference between 2 between the two parameters unix epoch millisecond value (x - y)
     * @author Rafael Andaya Cartagena
     * @since 2024-05-09
     */
    public static double getHourDifference_Exact(RCTdateTimeData x, RCTdateTimeData y){
        long ms_difference = getTimeDifference(x,y);
        return (((double)ms_difference)/((double)RCTdateTimeData.MILLIS_IN_HOUR));
    }

    /**
     * <h2>Description</h2>
     * Returns time difference between 2 between the two parameters unix epoch millisecond value (x - y)
     * @author Rafael Andaya Cartagena
     * @since 2024-05-09
     */
    public static long getMinuteDifference(RCTdateTimeData x, RCTdateTimeData y){
        long ms_difference = getTimeDifference(x,y);
        return ms_difference/RCTdateTimeData.MILLIS_IN_MINUTE;
    }

    /**
     * <h2>Description</h2>
     * Returns time difference between 2 between the two parameters unix epoch millisecond value (x - y)
     * @author Rafael Andaya Cartagena
     * @since 2024-05-09
     */
    public static double getMinuteDifference_Exact(RCTdateTimeData x, RCTdateTimeData y){
        long ms_difference = getTimeDifference(x,y);
        return (((double)ms_difference)/((double)RCTdateTimeData.MILLIS_IN_MINUTE));
    }

    /**
     * <h2>Description</h2>
     * Returns time difference between 2 between the two parameters unix epoch millisecond value (x - y)
     * @author Rafael Andaya Cartagena
     * @since 2024-05-09
     */
    public static long getSecondDifference(RCTdateTimeData x, RCTdateTimeData y){
        long ms_difference = getTimeDifference(x,y);
        return ms_difference/RCTdateTimeData.MILLIS_IN_SECOND;
    }

    /**
     * <h2>Description</h2>
     * Returns time difference between 2 between the two parameters unix epoch millisecond value (x - y)
     * @author Rafael Andaya Cartagena
     * @since 2024-05-09
     */
    public static double getSecondDifference_Exact(RCTdateTimeData x, RCTdateTimeData y){
        long ms_difference = getTimeDifference(x,y);
        return (((double)ms_difference)/((double)RCTdateTimeData.MILLIS_IN_SECOND));
    }







    /**
     * <h2>Description</h2>
     * Returns a months day count, month parameter use 1-12
     * @author Rafael Andaya Cartagena
     * @since 2024-05-09
     */
    public static int getDayCountInMonth(int month,int year){

        int target_month = (month-1);
        switch(target_month){
            case 0:
            case 4:
            case 6:
            case 7:
            case 9:
            case 11:
            case 2:
                return 31;
            case 1:
                if(isLeapYear(target_month)){
                    return 29;
                }else{
                    return 28;
                }
            case 3:
            case 10:
            case 5:
            case 8:
                return 30;
        }
        return -1;
    }

    public static boolean isLeapYear(int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        return cal.getActualMaximum(Calendar.DAY_OF_YEAR) > 365;
    }





    public static String getMonthName(int month){ //1-12
        String month_name = "null";
        switch (month){
            case 1:
                month_name = "January";
                break;
            case 2:
                month_name = "February";
                break;
            case 3:
                month_name = "March";
                break;
            case 4:
                month_name = "April";
                break;
            case 5:
                month_name = "May";
                break;
            case 6:
                month_name = "June";
                break;
            case 7:
                month_name = "July";
                break;
            case 8:
                month_name = "August";
                break;
            case 9:
                month_name = "September";
                break;
            case 10:
                month_name = "October";
                break;
            case 11:
                month_name = "November";
                break;
            case 12:
                month_name = "December";
                break;
        }

        return month_name;
    }







    public static int calculateAge(RCTdateTimeData birthdate, RCTdateTimeData sample_time){
        int age = sample_time.YEAR - birthdate.YEAR;

        // Check if the birthday for this year has passed, if not, subtract 1 from age
        if (sample_time.MONTH < birthdate.MONTH ||
                (sample_time.MONTH == birthdate.MONTH && sample_time.DATE < birthdate.DATE)) {
            age--;
        }
        return age;
    }




    private static int convert24HRto12HR(int hour){
        if(hour > 12){
            return hour-12;
        }else{
            return hour;
        }
    }

    private static String convertIntToDoubleDigitString(int the_int){
        if(the_int >= 0 && the_int <= 9){
            return "0".concat(String.valueOf(the_int));
        }else{
            return String.valueOf(the_int);
        }
    }


    private static String getAM_PM(int hour){
        if(hour >= 0 && hour <= 11){
            return "AM";
        }else if(hour >= 12 && hour <= 23){
            return "PM";
        }
        return null;

    }



    public static ArrayList<Long> convertDateToMS(ArrayList<Date> dates){
        ArrayList<Long> ms_dates = new ArrayList<>();
        for(int index = 0; index<dates.size(); index++){
            ms_dates.add(dates.get(index).getTime());
        }
        return ms_dates;
    }

    public static ArrayList<RCTdateTimeData> convertDateToRCTdateTimeData(ArrayList<Date> dates){
        ArrayList<RCTdateTimeData> ms_time_datas = new ArrayList<>();
        for(int index = 0; index<dates.size(); index++){
            ms_time_datas.add(new RCTdateTimeData(dates.get(index).getTime()));
        }
        return ms_time_datas;
    }




    protected static String formatTimestamp(long millis, String pattern, TimeZone time_zone) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern, Locale.getDefault());
        formatter.setTimeZone(time_zone);

        return formatter.format(new Date(millis));
    }

    // Format: YYYYMMDD
    public static String getTimestampYYYYMMDD(long millis, TimeZone time_zone) {
        return formatTimestamp(millis, "yyyyMMdd", time_zone);
    }

    // Format: YYYYMMDD-Hour
    public static String getTimestampYYYYMMDD_HH(long millis, TimeZone time_zone) {
        return formatTimestamp(millis, "yyyyMMdd-HH", time_zone);
    }

    // Format: YYYYMMDD-HourMinute
    public static String getTimestampYYYYMMDD_HHMM(long millis, TimeZone time_zone) {
        return formatTimestamp(millis, "yyyyMMdd-HHmm", time_zone);
    }

    // Format: YYYYMMDD-HourMinuteSecond
    public static String getTimestampYYYYMMDD_HHMMSS(long millis, TimeZone time_zone) {
        return formatTimestamp(millis, "yyyyMMdd-HHmmss", time_zone);
    }

    // Format: YYYYMMDD-HourMinuteSecondMillis
    public static String getTimestampYYYYMMDD_HHMMSS_SSS(long millis, TimeZone time_zone) {
        return formatTimestamp(millis, "yyyyMMdd-HHmmssSSS", time_zone);
    }

    public static long toMillis(String timestamp, TimeZone time_zone) throws ParseException {
        // List of possible patterns, ordered by specificity
        String[] patterns = {
                "yyyyMMdd-HHmmssSSS",
                "yyyyMMdd-HHmmss",
                "yyyyMMdd-HHmm",
                "yyyyMMdd-HH",
                "yyyyMMdd"
        };

        for (String pattern : patterns) {
            try {
                SimpleDateFormat formatter = new SimpleDateFormat(pattern, Locale.getDefault());
                formatter.setTimeZone(time_zone); // Ensure UTC for ISO standard
                Date date = formatter.parse(timestamp);
                if (date != null) {
                    return date.getTime();
                }
            } catch (ParseException ignored) {
                // Continue to the next pattern
            }
        }
        throw new ParseException("No matching pattern found for timestamp: " + timestamp, 0);
    }






}
