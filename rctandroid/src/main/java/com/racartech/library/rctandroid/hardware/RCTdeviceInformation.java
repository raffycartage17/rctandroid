package com.racartech.library.rctandroid.hardware;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.icu.util.TimeZone;
import android.os.BatteryManager;
import android.os.Build;
import android.provider.Settings;
import android.util.DisplayMetrics;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RCTdeviceInformation{

        public static long getTotalRAM(Context context) {
            ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            activityManager.getMemoryInfo(memoryInfo);

            // Total RAM in bytes
            long totalMemory = memoryInfo.totalMem;

            // Convert bytes to megabytes
            long totalMemoryMB = totalMemory / (1024 * 1024);

            return totalMemoryMB;
        }

        public static long getFreeRAM(Context context) {
            ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            activityManager.getMemoryInfo(memoryInfo);

            // Free RAM in bytes
            long freeMemory = memoryInfo.availMem;

            // Convert bytes to megabytes
            long freeMemoryMB = freeMemory / (1024 * 1024);

            return freeMemoryMB;
        }

    public static long getUsedRAM(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);

        // Used RAM in bytes
        long usedMemory = memoryInfo.totalMem - memoryInfo.availMem;

        // Convert bytes to megabytes
        long usedMemoryMB = usedMemory / (1024 * 1024);

        return usedMemoryMB;
    }

    public static int getScreenWidth(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return displayMetrics.widthPixels;
    }

    public static int getScreenHeight(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return displayMetrics.heightPixels;
    }

    public static String getModelName() {
        return Build.MODEL;
    }

    public static String getManufacturerName() {
        return Build.MANUFACTURER;
    }

    public static String getOSVersion() {
        return Build.VERSION.RELEASE;
    }

    public static int getAPILevel() {
        return Build.VERSION.SDK_INT;
    }

    public static float getScreenDensity(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return displayMetrics.density;
    }

    public static int getScreenDensityDpi(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return displayMetrics.densityDpi;
    }

    public static String getDefaultLocale() {
        return Locale.getDefault().toString();
    }

    public static String getTimeZone() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return TimeZone.getDefault().getID();
        }
        return null;
    }


    public static String getDeviceID(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static int getBatteryLevel(Context context) {
        Intent batteryIntent = context.registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        int level = batteryIntent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryIntent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        return Math.round(level * 100.0f / scale);
    }

    public static boolean isTablet(Context context) {
        int screenSize = context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK;
        return screenSize == Configuration.SCREENLAYOUT_SIZE_XLARGE || screenSize == Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    public static List<ApplicationInfo> getInstalledApps(Context context) {
        PackageManager pm = context.getPackageManager();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            List<PackageInfo> packages = pm.getInstalledPackages(0);
            List<ApplicationInfo> apps = new ArrayList<>();
            for (PackageInfo pkg : packages) {
                // Filter out system apps and updates to system apps
                if ((pkg.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0
                        && (pkg.applicationInfo.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) == 0) {
                    apps.add(pkg.applicationInfo);
                }
            }
            return apps;
        }else{
            return pm.getInstalledApplications(PackageManager.GET_META_DATA);
        }
    }

    public static String getDeviceModel() {
        return Build.MODEL;
    }













}
