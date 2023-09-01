package com.racartech.app.rctandroidlts.functionbuttons;

import android.app.Activity;
import android.content.Context;
import android.location.Address;

import com.google.firebase.firestore.FirebaseFirestore;
import com.racartech.library.rctandroid.google.firebase.firestore.RCTfirebaseFirestore;
import com.racartech.library.rctandroid.location.LocationData;
import com.racartech.library.rctandroid.location.RCTlocation;
import com.racartech.library.rctandroid.net.RCTinternet;

import java.util.HashMap;

public class FunctionOne{


    public static void launch(Context context, Activity activity) {
        new Thread(new Runnable() {
            @Override
            public void run(){
                boolean location_status = RCTlocation.isLocationEnabled(context);
                System.out.println("Location Status : ".concat(String.valueOf(location_status)));
            }
        }).start();

    }




}
