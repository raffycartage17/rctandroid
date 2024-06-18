package com.racartech.app.rctandroidlts.firebase;

import com.google.firebase.firestore.FirebaseFirestore;
import com.racartech.library.rctandroid.google.firebase.firestore.RCTfirebaseFirestore;

import java.util.HashMap;

public class FirestoreTest{


    public static void test001(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                String collection = "test_collection";
                String document = "test_document";

                HashMap<String, Object> document_data = new HashMap<>();
                document_data.put("a", "AAA");
                document_data.put("b", "BBB");
                document_data.put("c", "CCC");
                document_data.put("d", "DDD");
                document_data.put("e", "EEE");

                boolean success = RCTfirebaseFirestore.setDocumentData_WaitProgress(
                        FirebaseFirestore.getInstance(),
                        collection,
                        document,
                        document_data,
                        100
                );
                System.out.println("----------------------------------------------------------------------------------");
                System.out.println("Test Success : ".concat(String.valueOf(success)));
                System.out.println("----------------------------------------------------------------------------------");
            }
        }).start();

    }



}
