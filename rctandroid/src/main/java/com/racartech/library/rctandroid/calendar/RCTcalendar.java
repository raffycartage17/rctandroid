package com.racartech.library.rctandroid.calendar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class RCTcalendar{

    public final static String PATTERN_MM_DD = "MM/dd";
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


}
