package com.racartech.app.rctandroidlts.functionbuttons;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
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
                System.out.println("-------------------------------------------Test 2-------------------------------------------");
                FirebaseStorage instance = FirebaseStorage.getInstance();
                String download_url = RCTfirebaseStorage.uploadFile(
                        instance,
                        RCTfile.getDir_ExternalStorageRoot().concat("/apath/test.jpg"),
                        "test_folder",
                        "uploaded_image_raf.jpg",200
                );
                System.out.println("--------------------------------------------------------------------------------------------");
                System.out.println("Download URL : ".concat(download_url));
                System.out.println("--------------------------------------------------------------------------------------------");
            }
        }).start();


    }





}
