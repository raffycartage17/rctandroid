package com.racartech.app.rctandroidlts.functionbuttons;

import android.app.Activity;
import android.content.Context;

import com.google.firebase.firestore.FirebaseFirestore;
import com.racartech.library.rctandroid.google.firebase.firestore.RCTfirebaseFirestore;
import com.racartech.library.rctandroid.location.LocationData;

import java.util.HashMap;

public class FunctionOne{


    public static void launch(Context context, Activity activity) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HashMap<String, Object> document_data = RCTfirebaseFirestore.readDocument(FirebaseFirestore.getInstance(),"Bulacan","Towns",200);
                System.out.println("Document Keys Size : ".concat(String.valueOf(document_data.keySet().size())));


            }
        }).start();

    }




}
