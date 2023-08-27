package com.racartech.app.rctandroidlts.functionbuttons;

import android.app.Activity;
import android.content.Context;
import android.location.Address;

import com.google.firebase.firestore.FirebaseFirestore;
import com.racartech.library.rctandroid.google.firebase.firestore.RCTfirebaseFirestore;
import com.racartech.library.rctandroid.location.LocationData;
import com.racartech.library.rctandroid.location.RCTlocation;

import java.util.HashMap;

public class FunctionOne{


    public static void launch(Context context, Activity activity) {
        new Thread(new Runnable() {
            @Override
            public void run(){



                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        LocationData location_data = new LocationData(context,LocationData.MODE_CURRENT,100);
                        Address current_address = location_data.getAddress();

                        System.out.println("Admin Area      : ".concat(current_address.getAdminArea()));
                        System.out.println("Sub Admin Area  : ".concat(current_address.getSubAdminArea()));
                        System.out.println("Locality        : ".concat(current_address.getLocality()));



                    }
                }).start();


            }
        }).start();

    }




}
