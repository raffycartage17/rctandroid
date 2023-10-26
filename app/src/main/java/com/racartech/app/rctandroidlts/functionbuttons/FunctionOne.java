package com.racartech.app.rctandroidlts.functionbuttons;

import android.app.Activity;
import android.content.Context;
import android.location.Address;

import com.google.firebase.firestore.FirebaseFirestore;
import com.racartech.library.rctandroid.file.RCTfile;
import com.racartech.library.rctandroid.google.firebase.firestore.RCTfirebaseFirestore;
import com.racartech.library.rctandroid.location.LocationData;
import com.racartech.library.rctandroid.location.RCTlocation;
import com.racartech.library.rctandroid.net.RCTinternet;
import com.racartech.library.rctandroid.time.RCTtime;
import com.racartech.library.rctandroid.time.RCTtimeData;

import java.util.HashMap;
import java.util.List;

public class FunctionOne{


    public static void launch(Context context, Activity activity) {
        new Thread(new Runnable() {
            @Override
            public void run(){
                long start = System.currentTimeMillis();
                String query_address = "Washington";
                List<Address> results = RCTlocation.searchAddress(context,query_address,20);
                System.out.println("------------------------------------------------------------------------");
                System.out.println("------------------------------------------------------------------------");
                for(int index = 0; index<results.size(); index++){
                    System.out.println("Result (".concat(String.valueOf(index)).concat(") : ").concat(
                            results.get(index).getAddressLine(0)));
                    System.out.println(results.get(index).getMaxAddressLineIndex());

                }
                System.out.println("------------------------------------------------------------------------");
                System.out.println("------------------------------------------------------------------------");
                long end = System.currentTimeMillis();
                System.out.println("------------------------------------------------------------------------");
                System.out.println("Elapse Time : ".concat(String.valueOf((end-start))));
                System.out.println("------------------------------------------------------------------------");
            }
        }).start();

    }




}
