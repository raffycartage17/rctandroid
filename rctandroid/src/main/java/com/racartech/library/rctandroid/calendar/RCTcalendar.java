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


    public final static SimpleDateFormat YYYY_MM_DD = new SimpleDateFormat("yyyy-MM-dd");
    public final static SimpleDateFormat MM_DD_YYYY = new SimpleDateFormat("MM-dd-yyyy");
    public final static SimpleDateFormat DD_MM_YYYY = new SimpleDateFormat("dd-MM-yyyy");
    public final static SimpleDateFormat YYYY_MM_DD_HH_MM_SS_SSS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");


    /**
     * Returns an integer array containing the individual year, month, day of the month,
     * hour, minute, second, and millisecond values of the specified date.
     *
     * @param date the date object to extract values from
     * @return an integer array containing the individual date values
     *         Index 0: year
     *         Index 1: month (1-12)
     *         Index 2: day of the month
     *         Index 3: hour of the day (0-23)
     *         Index 4: minute (0-59)
     *         Index 5: second (0-59)
     *         Index 6: millisecond (0-999)
     */
    public static int[] getDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int[] dateValues = new int[7];
        dateValues[0] = calendar.get(Calendar.YEAR);
        dateValues[1] = calendar.get(Calendar.MONTH) + 1; // add 1 because month is zero-indexed
        dateValues[2] = calendar.get(Calendar.DAY_OF_MONTH);
        dateValues[3] = calendar.get(Calendar.HOUR_OF_DAY);
        dateValues[4] = calendar.get(Calendar.MINUTE);
        dateValues[5] = calendar.get(Calendar.SECOND);
        dateValues[6] = calendar.get(Calendar.MILLISECOND);
        return dateValues;
    }









    /**
     * Returns the day of the week of the specified date.
     *
     * @param date the date to get the day of the week for
     * @return the day of the week as an integer, where Sunday is 1 and Saturday is 7
     */
    public static int getWeekDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * Returns the day of the week of the specified time in milliseconds.
     *
     * @param millis the time in milliseconds to get the day of the week for
     * @return the day of the week as an integer, where Sunday is 1 and Saturday is 7
     */
    public static int getWeekDay(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * Returns the day of the week of the specified date string, using the specified date format.
     *
     * @param date the date string to parse
     * @param formatter the date format to use for parsing the date string
     * @return the day of the week as an integer, where Sunday is 1 and Saturday is 7
     * @throws ParseException if the date string cannot be parsed using the specified format
     */
    public static int getWeekDay(String date, SimpleDateFormat formatter) throws ParseException {
        Date parsedDate = formatter.parse(date);
        return getWeekDay(parsedDate);
    }

    /**
     * Returns a date format that formats dates in the format "MM-dd-yyyy".
     *
     * @return a SimpleDateFormat object that formats dates in the format "MM-dd-yyyy"
     */
    public static SimpleDateFormat getDateFormatMMDDYYYY() {
        return new SimpleDateFormat("MM-dd-yyyy");
    }

    /**
     * Returns a date format that formats dates in the format "yyyy-MM-dd".
     *
     * @return a SimpleDateFormat object that formats dates in the format "yyyy-MM-dd"
     */
    public static SimpleDateFormat getDateFormatYYYYMMDD() {
        return new SimpleDateFormat("yyyy-MM-dd");
    }

    /**
     * Returns a date format that formats dates in the format "dd-MM-yyyy".
     *
     * @return a SimpleDateFormat object that formats dates in the format "dd-MM-yyyy"
     */
    public static SimpleDateFormat getDateFormatDDMMYYYY() {
        return new SimpleDateFormat("dd-MM-yyyy");
    }


    /**
     * Returns the formatted date string for the given time in milliseconds using the specified date format.
     * @param millis the time in milliseconds.
     * @param formatter the date format to use.
     * @return the formatted date string.
     */
    public static String getDate(long millis, SimpleDateFormat formatter) {
        Date date = new Date(millis);
        return formatter.format(date);
    }

    /**
     * Returns the time in milliseconds for the given date string using the specified date format.
     * @param date the date string.
     * @param formatter the date format to use.
     * @return the time in milliseconds.
     * @throws ParseException if the date string cannot be parsed according to the specified format.
     */
    public static long getMillis(String date, SimpleDateFormat formatter) throws ParseException {
        Date parsedDate = formatter.parse(date);
        return parsedDate.getTime();
    }

    /**
     * Returns the time in milliseconds for the given date.
     * @param date the date.
     * @return the time in milliseconds.
     */
    public static long getMillis(Date date) {
        return date.getTime();
    }

    /**
     * Returns a Date object for the given time in milliseconds.
     * @param millis the time in milliseconds.
     * @return the Date object.
     */
    public static Date getDate(long millis) {
        return new Date(millis);
    }

    /**
     * Returns the formatted date string for the given date using the specified date format.
     * @param date the date.
     * @param formatter the date format to use.
     * @return the formatted date string.
     */
    public static String getDate(Date date, SimpleDateFormat formatter) {
        return formatter.format(date);
    }


    /**
     * Returns an array of Date objects representing all the dates in the specified year.
     *
     * @param year The year to get the dates for.
     * @return An array of Date objects representing all the dates in the specified year.
     */
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

    /**
     * Returns an array of Date objects representing all the dates between the specified from and to dates.
     *
     * @param from The start date.
     * @param to The end date.
     * @return An array of Date objects representing all the dates between the specified from and to dates.
     */
    public static Date[] getDate(Date from, Date to) {
        List<Date> dates = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(from);
        while (calendar.getTime().before(to) || calendar.getTime().equals(to)) {
            dates.add(calendar.getTime());
            calendar.add(Calendar.DATE, 1);
        }
        return dates.toArray(new Date[dates.size()]);
    }




    /**
     * Returns the current date and time.
     *
     * @return A Date object representing the current date and time.
     */
    public static Date getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }

    /**
     * Returns a Date object representing the specified date and time.
     *
     * @param year The year.
     * @param month The month, where January is 1 and December is 12.
     * @param day_of_month The day of the month.
     * @param hour_of_day The hour of the day, in 24-hour format.
     * @param minute The minute.
     * @param seconds The seconds.
     * @param millis The milliseconds.
     * @return A Date object representing the specified date and time.
     */
    public static Date getDate(int year, int month, int day_of_month, int hour_of_day, int minute, int seconds, int millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day_of_month, hour_of_day, minute, seconds);
        calendar.set(Calendar.MILLISECOND, millis);
        return calendar.getTime();
    }

    /**
     * Returns the time difference in milliseconds between two dates.
     *
     * @param first_date The first date.
     * @param second_date The second date, which should be later than the first date.
     * @return The time difference in milliseconds between the two dates.
     */
    public static long getTimeDifference(Date first_date, Date second_date) {
        long diff = second_date.getTime() - first_date.getTime();
        return diff;
    }

    /**
     * Converts a duration in milliseconds to seconds.
     *
     * @param millis The duration in milliseconds.
     * @return The duration in seconds.
     */
    public static double millisToSecond(long millis) {
        return millis / 1000.0;
    }

    /**
     * Converts a duration in milliseconds to minutes.
     *
     * @param millis The duration in milliseconds.
     * @return The duration in minutes.
     */
    public static double millisToMinute(long millis) {
        double value = 1000.0 * 60.0;
        return millis / value;
    }

    /**
     * Converts a duration in milliseconds to hours.
     *
     * @param millis The duration in milliseconds.
     * @return The duration in hours.
     */
    public static double millisToHour(long millis) {
        double value = 1000.0 * 60.0 * 60.0;
        return millis / value;
    }

    /**
     * Converts a duration in milliseconds to days.
     *
     * @param millis The duration in milliseconds.
     * @return The duration in days.
     */
    public static double millisToDay(long millis) {
        double value = 1000 * 60.0 * 60.0 * 24.0;
        return millis / value;
    }

    /**
     * Converts seconds to milliseconds.
     *
     * @param second the number of seconds to convert
     * @return the equivalent number of milliseconds
     */
    public static double secondToMillis(long second) {
        return second * 1000.0;
    }

    /**
     * Converts minutes to milliseconds.
     *
     * @param minute the number of minutes to convert
     * @return the equivalent number of milliseconds
     */
    public static double minuteToMillis(long minute) {
        return minute * (1000.0 * 60.0);
    }

    /**
     * Converts hours to milliseconds.
     *
     * @param hour the number of hours to convert
     * @return the equivalent number of milliseconds
     */
    public static double hourToMillis(long hour) {
        return hour * (1000.0 * 60.0 * 60.0);
    }

    /**
     * Converts days to milliseconds.
     *
     * @param day the number of days to convert
     * @return the equivalent number of milliseconds
     */
    public static double dayToMillis(long day) {
        return day * (1000.0 * 60.0 * 60.0 * 24.0);
    }





    /**
     * Determines whether the given year is a leap year or not.
     *
     * @param year the year to check.
     * @return true if the given year is a leap year, false otherwise.
     */
    public static boolean isLeapYear(int year) {
        boolean isLeapYear;
        isLeapYear = (year % 4 == 0);
        isLeapYear = isLeapYear && (year % 100 != 0 || year % 400 == 0);
        return isLeapYear;
    }

    /**
     * Adds the specified number of days to the input date and returns the result.
     *
     * @param date the date to add days to.
     * @param daysToAdd the number of days to add to the input date.
     * @return a new Date object representing the result of adding the specified number of days to the
     * input date.
     */
    public static Date addDaysToDate(Date date, int daysToAdd) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, daysToAdd);
        return calendar.getTime();
    }

    /**
     * Subtracts the specified number of days from the input date and returns the result.
     *
     * @param date the date to subtract days from.
     * @param daysToSubtract the number of days to subtract from the input date.
     * @return a new Date object representing the result of subtracting the specified number of days
     * from the input date.
     */
    public static Date subtractDaysFromDate(Date date, int daysToSubtract) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -daysToSubtract);
        return calendar.getTime();
    }


    /**
     * Returns the number of days in the specified month of the year.
     *
     * @param year the year for the month (e.g. 2023)
     * @param month the month (0-11, where 0 is January and 11 is December)
     * @return the number of days in the specified month
     */
    public static int getDaysInMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, 1); // set to the first day of the month
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * Returns the date representing the first day of the month for the specified date.
     *
     * @param date the date for which to retrieve the first day of the month
     * @return the date representing the first day of the month
     */
    public static Date getFirstDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1); // set to the first day of the month
        return calendar.getTime();
    }

    /**
     * Returns the date representing the last day of the month for the specified date.
     *
     * @param date the date for which to retrieve the last day of the month
     * @return the date representing the last day of the month
     */
    public static Date getLastDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, lastDay); // set to the last day of the month
        return calendar.getTime();
    }

    /**
     * Returns the date resulting from adding the specified number of days, hours, minutes, seconds, and milliseconds to the given date.
     *
     * @param date the date to which to add time
     * @param days the number of days to add
     * @param hours the number of hours to add
     * @param minutes the number of minutes to add
     * @param seconds the number of seconds to add
     * @param milliseconds the number of milliseconds to add
     * @return the resulting date
     */
    public static Date addTimeToDate(Date date, int days, int hours, int minutes, int seconds, int milliseconds) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        calendar.add(Calendar.HOUR_OF_DAY, hours);
        calendar.add(Calendar.MINUTE, minutes);
        calendar.add(Calendar.SECOND, seconds);
        calendar.add(Calendar.MILLISECOND, milliseconds);
        return calendar.getTime();
    }


    /**
     * Returns the name of the month for the given month number (1-12).
     *
     * @param month the month number (1-12)
     * @return the name of the month
     */
    public static String getMonthName(int month) {
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();
        return months[month - 1];
    }

    /**
     * Returns the name of the weekday for the given date.
     *
     * @param date the date for which to get the weekday name
     * @return the name of the weekday
     */
    public static String getWeekdayName(Date date) {
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] weekdays = dfs.getWeekdays();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return weekdays[dayOfWeek];
    }

    /**
     * Returns the week number for the given date.
     *
     * @param date the date for which to get the week number
     * @return the week number
     */
    public static int getWeekNumber(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * Returns the number of days between the given start and end dates.
     *
     * @param start the start date
     * @param end the end date
     * @return the number of days between the start and end dates
     */
    public static long getDaysBetween(Date start, Date end) {
        long diff = end.getTime() - start.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }



    /**
     * Formats the given date according to the specified pattern.
     *
     * @param date The date to be formatted.
     * @param pattern The pattern to format the date in. The pattern follows the SimpleDateFormat syntax.
     * @return The formatted date string.
     */
    public static String formatDate(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.getDefault());
        return sdf.format(date);
    }

    /**
     * Parses the given string into a date object according to the specified pattern.
     *
     * @param dateString The date string to be parsed.
     * @param pattern The pattern to parse the date in. The pattern follows the SimpleDateFormat syntax.
     * @return The parsed date object.
     * @throws ParseException If the date string cannot be parsed according to the specified pattern.
     */
    public static Date parseDate(String dateString, String pattern) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.getDefault());
        return sdf.parse(dateString);
    }

    /**
     * Gets the month value from the given milliseconds.
     *
     * @param millis The milliseconds to get the month from.
     * @return The month value in the range of 0-11.
     */
    public static int getMonthFromMillis(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.get(Calendar.MONTH);
    }










}
