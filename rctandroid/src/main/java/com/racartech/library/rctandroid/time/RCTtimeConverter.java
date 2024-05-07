package com.racartech.library.rctandroid.time;

import com.racartech.library.rctandroid.math.RCTrandom;

import java.text.DecimalFormat;

public class RCTtimeConverter{
    DecimalFormat f = new DecimalFormat("##.00");

    public static long convertSecond_ToMS(long second){
        return second*(1000L);
    }
    public static long convertMinute_ToMS(long minute){
        return minute*(60L*1000L);
    }
    public static long convertHour_ToMS(long hour){
        return hour*(60L*60L*1000L);
    }
    public static long convertDay_ToMS(long day){
        return day*(24L*60L*60L*1000L);
    }

    public static long convertYear_ToMS(long number_of_year){
        return number_of_year*(365L*24L*60L*60L*1000L);
    }

    public static long convertDecade_ToMS(long number_of_decades){
        return (number_of_decades*10L)*(365L*24L*60L*60L*1000L);
    }

    public static long convertCentury_ToMS(long number_of_century){
        return (number_of_century*100L)*(365L*24L*60L*60L*1000L);
    }

    public static long convertMillennium_ToMS(long number_of_millennium){
        return (number_of_millennium*1000L)*(365L*24L*60L*60L*1000L);
    }

    public static long convertMS_toSecond(long millisecond){
        return millisecond/(1000L);
    }
    public static long convertMS_toMinute(long millisecond){
        return millisecond/(60L*1000L);
    }
    public static long convertMS_toHour(long millisecond){
        return millisecond/(60L*60L*1000L);
    }
    public static long convertMS_toDay(long millisecond){
        return millisecond/(24L*60L*60L*1000L);
    }
    public static long convertMS_toYear(long millisecond){
        return millisecond/(365L*24L*60L*60L*1000L);
    }
    public static long convertMS_toDecade(long millisecond){
        return millisecond/(10L*365L*24L*60L*60L*1000L);
    }
    public static long convertMS_toCentury(long millisecond){
        return millisecond/(100L*365L*24L*60L*60L*1000L);
    }
    public static long convertMS_toMillennium(long millisecond){
        return millisecond/(1000L*365L*24L*60L*60L*1000L);
    }

    public static String convertMS_toTimeTag(long milliseconds){
        if(milliseconds<1000){
            return String.valueOf(milliseconds).concat("ms");
        }else if(milliseconds>=1000L && milliseconds<60000L){
            double unit_count = (double)milliseconds/1000L;
            DecimalFormat f = new DecimalFormat("##.00");
            if(unit_count>=2.0){
                return f.format(unit_count).concat(" seconds");
            }else{
                return f.format(unit_count).concat(" second");
            }
        }else if(milliseconds>=60000L && milliseconds<3600000L){
            double unit_count = (double)milliseconds/60000;
            DecimalFormat f = new DecimalFormat("##.00");
            if(unit_count>=2.0){
                return f.format(unit_count).concat(" minutes");
            }else{
                return f.format(unit_count).concat(" minute");
            }
        }else if(milliseconds>=3600000L && milliseconds<86400000L){
            double unit_count = (double)milliseconds/3600000;
            DecimalFormat f = new DecimalFormat("##.00");
            if(unit_count>=2.0){
                return f.format(unit_count).concat(" hours");
            }else{
                return f.format(unit_count).concat(" hour");
            }
        }else if(milliseconds>=86400000L){
            double unit_count = (double)milliseconds/86400000L;
            DecimalFormat f = new DecimalFormat("##.00");
            if(unit_count>=2.0){
                return f.format(unit_count).concat(" days");
            }else{
                return f.format(unit_count).concat(" day");
            }
        }
        return null;
    }

    public static String convertMS_toCompleteTimeTag_HourUnitMax(long milliseconds) {
        if (milliseconds < 1000) {
            return String.valueOf(milliseconds).concat("ms");
        } else if (milliseconds >= 1000L && milliseconds < 60000L) {
            double unit_count = (double) milliseconds / 1000L;
            DecimalFormat f = new DecimalFormat("##.00");
            if (unit_count >= 2.0) {
                return f.format(unit_count).concat(" seconds");
            } else {
                return f.format(unit_count).concat(" second");
            }
        } else if (milliseconds > 60000L) {
            double unit_count = (double) milliseconds / 60000;
            DecimalFormat f = new DecimalFormat("##.00");
            int whole_num = (int) unit_count;
            double decimal_part = unit_count-whole_num;
            if(unit_count >= 60.0){
                int hour_count = (int)Math.floor(unit_count/60);
                int minute_count = (int) (unit_count-(hour_count*60));
                int second_count = (int)Math.floor(decimal_part*60);
                String minute_count_text = String.valueOf(minute_count);
                String second_count_text = String.valueOf(second_count);
                if(minute_count<=9){
                    minute_count_text = "0".concat(minute_count_text);
                }
                if(second_count<=9){
                    second_count_text = "0".concat(second_count_text);
                }
                return String.valueOf(Math.round(hour_count)).concat(":")
                        .concat(minute_count_text).concat(":")
                        .concat(second_count_text);
            }else if (unit_count<60.0) {
                int minute_count = (int)unit_count;
                int second_count = (int)Math.floor(decimal_part*60);
                String minute_count_text = String.valueOf(minute_count);
                String second_count_text = String.valueOf(second_count);
                if(minute_count<=9){
                    minute_count_text = "0".concat(minute_count_text);
                }
                if(second_count<=9){
                    second_count_text = "0".concat(second_count_text);
                }
                return minute_count_text.concat(":").concat(second_count_text);
            }
        }
        return null;
    }




}
