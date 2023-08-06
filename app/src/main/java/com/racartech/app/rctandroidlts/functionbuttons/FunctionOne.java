package com.racartech.app.rctandroidlts.functionbuttons;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.racartech.library.rctandroid.datatypes.RCTstring;
import com.racartech.library.rctandroid.file.RCTfile;
import com.racartech.library.rctandroid.google.firebase.firestore.RCTfirebaseFirestore;
import com.racartech.library.rctandroid.google.firebase.storage.RCTfirebaseStorage;

import java.util.ArrayList;

public class FunctionOne{


    public static void launch(Context context, Activity activity){

        Toast.makeText(context, "Button Pressed", Toast.LENGTH_SHORT).show();
        ArrayList<String> to_delete = new ArrayList<>();
        to_delete.add("file_2.txt");
        to_delete.add("file_3.txt");
        to_delete.add("file_4.txt");
        to_delete.add("file_5.txt");
        to_delete.add("file_6.txt");
        to_delete.add("file_7.txt");
        to_delete.add("file_8.txt");
        to_delete.add("file_9.txt");

        new Thread(new Runnable() {
            @Override
            public void run() {
                FirebaseStorage instance = FirebaseStorage.getInstance();

            }
        }).start();
    }

    public static void test2(Context context, Activity activity){


        new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<String> file_contents = new ArrayList<>();
                file_contents.add("Rafael Cartagena 1");
                file_contents.add("Rafael Cartagena 2");
                file_contents.add("Rafael Cartagena 3");
                file_contents.add("Rafael Cartagena 4");
                file_contents.add("Rafael Cartagena 5");
                RCTfirebaseStorage.createDirectory(FirebaseStorage.getInstance(),"aaa/aab","placeholder_1.txt");
                System.out.println("Directory Created");

            }
        }).start();


    }





}
