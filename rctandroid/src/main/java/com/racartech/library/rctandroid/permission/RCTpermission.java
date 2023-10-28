package com.racartech.library.rctandroid.permission;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class RCTpermission{



    public static void allowPermissions(Activity activity,String[] permissions,int request_code){
        if (ContextCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted, prompt the user
            ActivityCompat.requestPermissions(activity,permissions,
                    request_code);
        }
    }


    public static void allowPermission_MANAGE_EXTERNAL_STORAGE(Activity activity, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (ContextCompat.checkSelfPermission(activity,
                    Manifest.permission.MANAGE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {

                // Permission is not granted, prompt the user

                ActivityCompat.requestPermissions(activity,
                        new String[]{Manifest.permission.MANAGE_EXTERNAL_STORAGE},
                        requestCode);
            }
        }
    }

    public static void allowPermission_WRITE_EXTERNAL_STORAGE(Activity activity, int requestCode) {
        if (ContextCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted, prompt the user
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    requestCode);
        }
    }

    public static void allowPermission_READ_EXTERNAL_STORAGE(Activity activity, int requestCode) {
        if (ContextCompat.checkSelfPermission(activity,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted, prompt the user
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    requestCode);
        }
    }

    public static void allowPermission_ACCESS_FINE_LOCATION(Activity activity, int requestCode) {
        if (ContextCompat.checkSelfPermission(activity,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted, prompt the user
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    requestCode);
        }
    }


    public static void allowPermission_CALL_PHONE(Activity activity, int requestCode) {
        if (ContextCompat.checkSelfPermission(activity,
                Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted, prompt the user
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.CALL_PHONE},
                    requestCode);
        }
    }

    public static void allowPermission_ACCESS_COARSE_LOCATION(Activity activity, int requestCode) {
        if (ContextCompat.checkSelfPermission(activity,
                Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted, prompt the user
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    requestCode);
        }
    }

    public static void allowPermission_CAMERA(Activity activity, int requestCode) {
        if (ContextCompat.checkSelfPermission(activity,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted, prompt the user
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.CAMERA},
                    requestCode);
        }
    }

    public static void allowPermission_INTERNET(Activity activity, int requestCode) {
        if (ContextCompat.checkSelfPermission(activity,
                Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted, prompt the user
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.INTERNET},
                    requestCode);
        }
    }

    public static void allowPermission_MANAGE_EXTERNAL_STORAGE(Activity activity) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            // For Android 11 and above, launch the app info screen with the MANAGE_EXTERNAL_STORAGE permission selected
            Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
            Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
            intent.setData(uri);
            activity.startActivity(intent);
        }else{
            // For Android 10 and below, launch the app info screen
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
            intent.setData(uri);
            activity.startActivity(intent);
        }
    }

}
