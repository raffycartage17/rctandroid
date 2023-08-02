package com.racartech.app.rctandroidlts.functionbuttons;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;
import com.racartech.library.rctandroid.file.RCTfile;
import com.racartech.library.rctandroid.google.firebase.firestore.RCTfirestore;
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

                String collection_name = "Bulacan";
                String document_name = "Malolos";

                ArrayList<String> file_contents = new ArrayList<>();
                file_contents.add("line_one");
                file_contents.add("line_two");
                file_contents.add("line_three");
                file_contents.add("line_four");
                file_contents.add("line_five");

                FirebaseFirestore firestore_instance = FirebaseFirestore.getInstance();

                System.out.println("--------------------------------------Finished Process--------------------------------------");
                long start_a = System.currentTimeMillis();
                //RCTfirestore.createDocument_WaitProgress(collection_name,document_name, 200);
                //RCTfirestore.createDocument(collection_name,document_name);

                /*
                RCTfirestore.createField_WaitProgress(
                        firestore_instance,
                        "/Bulacan/Towns/Balagtas",
                        "List",
                        "area_list",
                        "Bulakan, Obando, Malolos, Balagtas, Marilao",200);

                String field_value = RCTfirestore.readField(
                        "/Bulacan/Towns/Balagtas",
                        "List",
                        "area_list",
                        200,
                        firestore_instance);

                 */
                ArrayList<String> field_list = RCTfirestore.getDocumentList(
                        firestore_instance,
                        "/Bulacan/Towns/Balagtas",
                        200);
                for(int index = 0; index<field_list.size(); index++){
                    System.out.println("Document (".concat(String.valueOf(index)).concat(") : ").concat(field_list.get(index)));
                }
                //System.out.println("Field Value : ".concat(field_value));
                long end_a = System.currentTimeMillis();
                System.out.println("Elapse Time With Wait : ".concat(String.valueOf((end_a-start_a))));
                //System.out.println("URL : ".concat(file_url));
                System.out.println("--------------------------------------------------------------------------------------------");
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run(){
                        Toast.makeText(context, "Finished Process", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).start();
    }

    public static void test2(Context context, Activity activity){
        System.out.println("-------------------------------------------Test 2-------------------------------------------");
        System.out.println("--------------------------------------------------------------------------------------------");
    }





}
