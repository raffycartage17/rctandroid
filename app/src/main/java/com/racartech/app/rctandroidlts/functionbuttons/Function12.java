package com.racartech.app.rctandroidlts.functionbuttons;

import com.racartech.library.rctandroid.google.firebase.firestore.RCTfirebaseFirestore;
import com.racartech.library.rctandroid.time.RCTtrueTime;
import com.racartech.library.rctandroid.time.server.GoogleTime;

public class Function12 {

    public static void entry(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                long google_time = GoogleTime.getNetworkTime();
                long true_time = RCTtrueTime.getTime();
                long device_time = System.currentTimeMillis();
                long truetime_difference = device_time-true_time;
                long googletime_difference = device_time-google_time;
                System.out.println("----------------------------------------");
                System.out.println("Google Time : ".concat(String.valueOf(google_time)));
                System.out.println("True   Time : ".concat(String.valueOf(true_time)));
                System.out.println("Device Time : ".concat(String.valueOf(device_time)));
                System.out.println("True   Time Difference : ".concat(String.valueOf(truetime_difference)));
                System.out.println("Google Time Difference : ".concat(String.valueOf(googletime_difference)));
                System.out.println("----------------------------------------");
            }
        }).start();


    }

}
