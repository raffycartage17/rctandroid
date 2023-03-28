package com.racartech.library.rctandroid.time;


import android.os.Build;

import androidx.annotation.RequiresApi;

import com.racartech.library.rctandroid.array.RCTarray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.time.temporal.ChronoUnit;
import java.time.temporal.UnsupportedTemporalTypeException;
import java.util.Calendar;
import java.util.TimeZone;

@RequiresApi(api = Build.VERSION_CODES.O)
public class RCTtime {
    private final static Calendar timeAndDate = Calendar.getInstance();
    public static final DateTimeFormatter default_date_format_rctjava = DateTimeFormatter.ofPattern("MM dd yyyy");
    public static final String[] MONTH_LIST = {"January","February","March","April","May","June",
            "July","August","September","October","November","December"};
    public static final String[] MONTH_LIST_BIG_LETTERS =
            {"JANUARY","FEBRUARY","MARCH","APRIL","MAY","JUNE",
                    "JULY","AUGUST","SEPTEMBER","OCTOBER","NOVEMBER","DECEMBER"};
    public static final String[] MONTH_LIST_SMALL_LETTERS = {"january","february","march","april","may","june",
            "july","august","september","october","november","december"};



    public static int getYear(){
        return timeAndDate.get(Calendar.YEAR);
    }

    public static int getMonth(){
        return timeAndDate.get(Calendar.MONTH)+1;
    }

    public static int getDate(){
        return timeAndDate.get(Calendar.DAY_OF_MONTH);
    }

    public static int getHour(){
        return timeAndDate.get(Calendar.HOUR_OF_DAY);
    }

    public static int getMinute(){
        return timeAndDate.get(Calendar.MINUTE);
    }

    public static int getSecond(){
        return timeAndDate.get(Calendar.SECOND);
    }

    public static int getMillisecond(){
        return timeAndDate.get(Calendar.MILLISECOND);
    }

    public static int getDayOfWeek(){
        return timeAndDate.get(Calendar.DAY_OF_WEEK);
    }

    public static String getTimeZone(){
        Calendar cal = Calendar.getInstance();
        long milliDiff = cal.get(Calendar.ZONE_OFFSET);
        String [] ids = TimeZone.getAvailableIDs();
        for (String id : ids) {
            TimeZone tz = TimeZone.getTimeZone(id);
            if (tz.getRawOffset() == milliDiff) {
                return id;
            }
        }
        return null;
    }




    public static double getUnixSecond(){
        return (System.currentTimeMillis()/1000.0);
    }


    public static String getDateAndTimeStampName(){
        String year = String.valueOf(getYear());
        String month = String.valueOf(getMonth());
        String date = String.valueOf(getDate());
        String hour = String.valueOf(getHour());
        String minute = String.valueOf(getMinute());
        String second = String.valueOf(getSecond());
        return year.concat("-").
                concat(month).concat("-").
                concat(date).concat("-").
                concat(hour).concat("-").
                concat(minute).concat("-").
                concat(second);
    }


    /**
     * <h2>Description</h2>
     * The format for the start_date and end_date array is as follows, the array's must have a maximum size of 7.
     * <br>
     * If the array size is less than 7 for example with a size of 3. The index 0 will always be the year, index 1 will the the month and so on
     * <br>
     * Index 0 : Year count
     * <br>
     * Index 1 : Month count
     *  <br>
     * Index 2 : Day count
     * <br>
     * Index 3 : Hour count
     * <br>
     * Index 4 : Minute count
     * <br>
     * Index 5 : Second count
     * <br>
     * Index 6 : Millisecond count
     * @author  Rafael Andaya Cartagena
     * @version 1.0.0
     * @return
     * Returns an array of int with the size of 7.
     * <br>
     * Index 0 : Year count
     * <br>
     * Index 1 : Month count
     *  <br>
     * Index 2 : Day count
     * <br>
     * Index 3 : Hour count
     * <br>
     * Index 4 : Minute count
     * <br>
     * Index 5 : Second count
     * <br>
     * Index 6 : Millisecond count
     * @since 04-05-2021
     */
    public static int[] calculate_YearMonthDay(int[] start_date,int[] end_date){
        int[] calculated_time = new int[7];
        if(start_date.length < 7){
            int[] new_array = RCTarray.copy(start_date);
            for(int index = 0; index<start_date.length; index++){
                new_array[index] = start_date[index];
            }
            start_date = new_array;
        }
        if(start_date.length > 7){
            start_date = RCTarray.deletePart(start_date,7,start_date.length-1);
        }

        return calculated_time;
    }


    /**
     * <h2>Description</h2>
     * The format for the start_date and end_date array is MM dd yyyy by default, but you can specify which format you prefer in the third parameter
     * @author  Rafael Andaya Cartagena
     * @version 1.0.0
     * @since 04-05-2021
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static long daySince(String start_date, String end_date){
        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        final LocalDate firstDate = LocalDate.parse(start_date, default_date_format_rctjava);
        final LocalDate secondDate = LocalDate.parse(end_date, default_date_format_rctjava);
        return ChronoUnit.DAYS.between(firstDate, secondDate);
    }
    /**
     * <h2>Description</h2>
     * The format for the start_date and end_date array is MM dd yyyy by default, but you can specify which format you prefer in the third parameter
     * @author  Rafael Andaya Cartagena
     * @version 1.0.0
     * @since 04-05-2021
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static long daySince(String start_date, String end_date, String date_format){
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(date_format);
        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        final LocalDate firstDate = LocalDate.parse(start_date, formatter);
        final LocalDate secondDate = LocalDate.parse(end_date, formatter);
        return ChronoUnit.DAYS.between(firstDate, secondDate);
    }

    /**
     * <h2>Description</h2>
     * The format for the start_date and end_date array is MM dd yyyy by default, but you can specify which format you prefer in the third parameter
     * @author  Rafael Andaya Cartagena
     * @version 1.0.0
     * @since 04-05-2021
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static long daySince(int[] start_date, int[] end_date){
        String converted_start_date = dayAndMonth_Int_To_String_Converter(start_date[0]).concat(" ").
                concat(dayAndMonth_Int_To_String_Converter(start_date[1])).concat(" ").
                concat(year_Int_To_String_Converter(start_date[2]));
        String converted_end_date = dayAndMonth_Int_To_String_Converter(end_date[0]).concat(" ").
                concat(dayAndMonth_Int_To_String_Converter(end_date[1])).concat(" ").
                concat(year_Int_To_String_Converter(end_date[2]));
        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        final LocalDate firstDate = LocalDate.parse(converted_start_date, default_date_format_rctjava);
        final LocalDate secondDate = LocalDate.parse(converted_end_date, default_date_format_rctjava);
        return ChronoUnit.DAYS.between(firstDate, secondDate);
    }

    private static String dayAndMonth_Int_To_String_Converter(int value){
        String converted = String.valueOf(value);
        if(converted.length() < 2){
            converted = "0".concat(converted);
        }
        return converted;
    }
    private static String year_Int_To_String_Converter(int value){
        String converted = String.valueOf(value);
        if(converted.length() < 4){
            String additonal_zeros = "";
            int offset = 4-converted.length();
            for(int index = 0; index<offset; index++){
                additonal_zeros = additonal_zeros.concat("0");
            }
            converted = additonal_zeros.concat(converted);
        }
        return converted;
    }






    public static boolean isLeapYear(int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        return cal.getActualMaximum(Calendar.DAY_OF_YEAR) > 365;
    }


    public static int getDayCountInMonth(int target_month,int year){
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






}
