package com.racartech.library.rctandroid.google;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

public class RCTgoogleMaps {


    public static void viewOnMaps(Activity current_activity, double latitude, double longitude){
        String uri = "geo:" + latitude + "," + longitude + "?q=" + latitude + "," + longitude;
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        current_activity.startActivity(intent);
    }

    public static void viewOnMaps(Activity current_activity, String location_url){
        Uri locationUri = Uri.parse(location_url);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, locationUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        current_activity.startActivity(mapIntent);
    }





}
