package com.racartech.app.rctandroidlts.functionbuttons;

import android.app.Activity;
import android.content.Context;

import com.racartech.library.rctandroid.location.LocationData;

public class FunctionOne{


    public static void launch(Context context, Activity activity) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                LocationData location_data = new LocationData(context,LocationData.MODE_CURRENT,100);
                System.out.println("---------------------------------------------------------------");
                System.out.println("Address Line : ".concat(location_data.getAddress().getAddressLine(0)));
                System.out.println("---------------------------------------------------------------");
            }
        }).start();

    }




}
