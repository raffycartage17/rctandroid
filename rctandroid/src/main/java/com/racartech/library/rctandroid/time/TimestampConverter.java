package com.racartech.library.rctandroid.time;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.text.ParseException;

public class TimestampConverter {

    // Base formatter method
    protected static String formatTimestamp(long millis, String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern, Locale.getDefault());
        formatter.setTimeZone(TimeZone.getTimeZone("UTC")); // Set to UTC for ISO standard
        return formatter.format(new Date(millis));
    }

    // Format: YYYYMMDD
    public static String toYYYYMMDD(long millis) {
        return formatTimestamp(millis, "yyyyMMdd");
    }

    // Format: YYYYMMDD-Hour
    public static String toYYYYMMDD_HH(long millis) {
        return formatTimestamp(millis, "yyyyMMdd-HH");
    }

    // Format: YYYYMMDD-HourMinute
    public static String toYYYYMMDD_HHMM(long millis) {
        return formatTimestamp(millis, "yyyyMMdd-HHmm");
    }

    // Format: YYYYMMDD-HourMinuteSecond
    public static String toYYYYMMDD_HHMMSS(long millis) {
        return formatTimestamp(millis, "yyyyMMdd-HHmmss");
    }

    // Format: YYYYMMDD-HourMinuteSecondMillis
    public static String toYYYYMMDD_HHMMSS_SSS(long millis) {
        return formatTimestamp(millis, "yyyyMMdd-HHmmssSSS");
    }

    public static long toMillis(String timestamp) throws ParseException {
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
                formatter.setTimeZone(TimeZone.getTimeZone("UTC")); // Ensure UTC for ISO standard
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
