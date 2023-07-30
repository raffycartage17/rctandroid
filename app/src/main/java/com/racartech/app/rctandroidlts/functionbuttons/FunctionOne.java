package com.racartech.app.rctandroidlts.functionbuttons;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.racartech.library.rctandroid.file.RCTfile;
import com.racartech.library.rctandroid.google.firebase.storage.RCTfirebaseStorage;
import com.racartech.library.rctandroid.net.RCTinternet;
import com.racartech.library.rctandroid.security.RCThashing;
import com.racartech.library.rctandroid.time.RCTtime;
import com.racartech.library.rctandroid.time.RCTtimeData;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
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
                /*
                long start = System.currentTimeMillis();
                RCTfirebaseStorage.clearDirectory_ImmediateFile("to_folder/bbb");
                long end = System.currentTimeMillis();
                System.out.println("Elapse Time No Wait   : ".concat(String.valueOf((end-start))));
                 */

                String target_file = "test_override.txt";
                String target_directory = "source_directory/d";

                ArrayList<String> file_contents = new ArrayList<>();
                file_contents.add("line_one");
                file_contents.add("line_two");
                file_contents.add("line_three");
                file_contents.add("line_four");
                file_contents.add("line_five");

                long start_a = System.currentTimeMillis();
                String file_url = RCTfirebaseStorage.createOverrideFile_GetURL(target_file,file_contents,200);
                RCTfirebaseStorage.deleteFile_WaitProgress(target_file,200);
                long end_a = System.currentTimeMillis();
                System.out.println("Elapse Time With Wait : ".concat(String.valueOf((end_a-start_a))));
                System.out.println("URL : ".concat(file_url));
                System.out.println("--------------------------------------Finished Process--------------------------------------");
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run(){
                        Toast.makeText(context, "Finished Process", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).start();
    }





}
