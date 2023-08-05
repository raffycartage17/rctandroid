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

                System.out.println("-------------------------------------------Test 2-------------------------------------------");
                long start = System.currentTimeMillis();
                ArrayList<String> local_file_paths = new ArrayList<>();
                local_file_paths.add(RCTfile.getDir_ExternalStorageRoot().concat("/apath/test_0.jpg"));
                local_file_paths.add(RCTfile.getDir_ExternalStorageRoot().concat("/apath/test_1.jpg"));
                local_file_paths.add(RCTfile.getDir_ExternalStorageRoot().concat("/apath/test_2.jpg"));
                local_file_paths.add(RCTfile.getDir_ExternalStorageRoot().concat("/apath/test_3.jpg"));
                ArrayList<String> uploaded_file_names = new ArrayList<>();
                for(int index = 0; index<local_file_paths.size(); index++){
                    uploaded_file_names.add(RCTstring.randomString(64,64));
                }
                FirebaseStorage instance = FirebaseStorage.getInstance();
                ArrayList<String> download_urls = RCTfirebaseStorage.uploadFiles(
                        instance,
                        local_file_paths,
                        "test_folder",
                        uploaded_file_names,200
                );
                long end = System.currentTimeMillis();
                System.out.println("--------------------------------------------------------------------------------------------");
                for(int index = 0; index<download_urls.size(); index++){
                    System.out.println("Download URL : ".concat(download_urls.get(index)));
                }

                System.out.println("--------------------------------------------------------------------------------------------");
                System.out.println("Elapse Time : ".concat(String.valueOf(end-start)));
                System.out.println("--------------------------------------------------------------------------------------------");

            }
        }).start();


    }





}
