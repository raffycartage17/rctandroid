package com.racartech.library.rctandroid.location;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;

import androidx.core.app.ActivityCompat;

public class RCTlocation{



    public static double LAST_KNOWN_LATITUDE = 0;
    public static double LAST_KNOWN_LONGITUDE = 0;


    public final static int LATITUDE_INDEX = 0;
    public final static int LONGITUDE_INDEX = 1;
    public static double[] updateLastKnownLocation(Activity activity, Context app_context) {
        double[] loc_values = {0,0};
        LocationManager locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(app_context,
                Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.checkSelfPermission(app_context, Manifest.permission.ACCESS_COARSE_LOCATION);
        }

        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        LAST_KNOWN_LATITUDE = location.getLatitude();
        LAST_KNOWN_LONGITUDE = location.getLongitude();
        loc_values[0] = LAST_KNOWN_LATITUDE;
        loc_values[1] = LAST_KNOWN_LONGITUDE;
        return loc_values;
    }

    public static double[] getDefaultLocationCoordinates(){
        return new double[]{14.5995,120.9842};
    }




}
