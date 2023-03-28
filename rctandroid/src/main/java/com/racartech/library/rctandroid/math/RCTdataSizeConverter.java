package com.racartech.library.rctandroid.math;

import java.text.DecimalFormat;

public class RCTdataSizeConverter {


    public static double bytesToKilobytes(double bytes){
        return bytes/1024.0;
    }

    public static double bytesToMegabytes(double bytes){
        return bytes/(1024.0*1024.0);
    }

    public static double bytesToGigabytes(double bytes){
        return bytes/(1024.0*1024.0*1024.0);
    }
    public static double bytesToTerabytes(double bytes){
        return bytes/(1024.0*1024.0*1024.0*1024.0);
    }

    public static double bytesToPetabytes(double bytes){
        return bytes/(1024.0*1024.0*1024.0*1024.0*1024.0);
    }

    public static double kilobytesToBytes(double kilobytes){
        return kilobytes*1024.0;
    }
    public static double megabytesToBytes(double megabytes){
        return megabytes*(1024.0*1024.0);
    }

    public static double gigabytesToBytes(double gigabytes){
        return gigabytes*(1024.0*1024.0*1024.0);
    }

    public static double terabytesToBytes(double terabytes){
        return terabytes*(1024.0*1024.0*1024.0*1024.0);
    }

    public static double petabytesToBytes(double petabytes){
        return petabytes*(1024.0*1024.0*1024.0*1024.0*1024.0);
    }

    public static String getSymbol(long bytes){
        if(bytes < 1024.0){
            return "B";
        }
        if(bytes >= 1024.0 && bytes <= ((1024.0 * 1024.0)-1)){
            return "KB";
        }
        if(bytes >= (1024.0 * 1024.0)-1 && bytes <= ((1024.0 * 1024.0 * 1024.0)-1)){
            return "MB";
        }
        if(bytes >= (1024.0 * 1024.0 * 1024.0)-1 && bytes <= ((1024.0 * 1024.0 * 1024.0 * 1024.0)-1)){
            return "GB";
        }
        if(bytes >= (1024.0 * 1024.0 * 1024.0 * 1024.0)-1 && bytes <= ((1024.0 * 1024.0 * 1024.0 * 1024.0 * 1024.0)-1)){
            return "TB";
        }
        if(bytes >= (1024.0 * 1024.0 * 1024.0 * 1024.0 * 1024.0)-1 && bytes <= ((1024.0 * 1024.0 * 1024.0 * 1024.0 * 1024.0 * 1024.0)-1)){
            return "PB";
        }
        return "unknown unit";
    }
    public static double getConvertedValue_BasedOnSymbol(long bytes){
        if(bytes < 1024.0){
            return bytes;
        }
        if(bytes >= 1024.0 && bytes <= ((1024.0 * 1024.0)-1)){
            return bytesToKilobytes(bytes);
        }
        if(bytes >= (1024.0 * 1024.0)-1 && bytes <= ((1024.0 * 1024.0 * 1024.0)-1)){
            return bytesToMegabytes(bytes);
        }
        if(bytes >= (1024.0 * 1024.0 * 1024.0)-1 && bytes <= ((1024.0 * 1024.0 * 1024.0 * 1024.0)-1)){
            return bytesToGigabytes(bytes);
        }
        if(bytes >= (1024.0 * 1024.0 * 1024.0 * 1024.0)-1 && bytes <= ((1024.0 * 1024.0 * 1024.0 * 1024.0 * 1024.0)-1)){
            return bytesToTerabytes(bytes);
        }
        if(bytes >= (1024.0 * 1024.0 * 1024.0 * 1024.0 * 1024.0)-1 && bytes <= ((1024.0 * 1024.0 * 1024.0 * 1024.0 * 1024.0 * 1024.0)-1)){
            return bytesToPetabytes(bytes);
        }
        return 0;
    }
    public static String show2DecimalPlacesOnly(double num){
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(num);
    }



}
