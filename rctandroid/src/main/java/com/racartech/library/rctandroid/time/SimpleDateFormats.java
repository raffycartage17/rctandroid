package com.racartech.library.rctandroid.time;


import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class SimpleDateFormats {

    public static int ddmmyy = 0;
    public static int mmddyy = 1;

    private static String[] formats = {
            "dd-MM-yyyy HH:mm:ss",
            "MM-dd-yyyy HH:mm:ss",
    };

    public static DateFormat getSimpleDateFormat(int format) {
        return (DateFormat) new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    }



}
