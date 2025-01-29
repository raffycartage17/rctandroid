package com.racartech.app.rctandroidlts.functionbuttons;

import android.app.Activity;
import android.content.Context;

import com.google.firebase.firestore.FirebaseFirestore;
import com.racartech.library.rctandroid.google.firebase.firestore.RCTfirebaseFirestore;

public class Function10 {


    public static void entry(Activity activity, Context context){

        new Thread(new Runnable() {
            @Override
            public void run() {


                String collection = "test_collection";
                String document = "test_document_2";
                String field = "test_field";

                String value = RCTfirebaseFirestore.readField(
                        FirebaseFirestore.getInstance(),
                        collection,
                        document,
                        field,
                        50
                        );

                System.out.println("-----------------------------------");
                System.out.println(value);
                System.out.println("-----------------------------------");

                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                    }
                });
            }
        }).start();

    }


}
