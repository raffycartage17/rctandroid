package com.racartech.app.rctandroidlts.windows.window3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.racartech.app.rctandroidlts.FirebaseSingleton;
import com.racartech.app.rctandroidlts.R;
import com.racartech.library.rctandroid.google.firebase.storage.RCTfirebaseStorage;
import com.racartech.library.rctandroid.location.RCTlocationData;

import java.util.ArrayList;
import java.util.HashMap;

public class Window3 extends AppCompatActivity {

    Button f1, f2, f3;
    FirebaseSingleton firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window3);

        f1 = findViewById(R.id.aw3_f1_button);
        f2 = findViewById(R.id.aw3_f2_button);
        f3 = findViewById(R.id.aw3_f3_button);

        firebase = FirebaseSingleton.getInstance();

        setClickListenerF1();
        setClickListenerF2();
        setClickListenerF3();

    }

    private void setClickListenerF1(){
        f1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        ArrayList<String> storage_paths = new ArrayList<>();
                        storage_paths.add(RCTfirebaseStorage.getFilePath("","root_file_1.txt"));
                        storage_paths.add(RCTfirebaseStorage.getFilePath("","root_file_3.txt"));
                        storage_paths.add(RCTfirebaseStorage.getFilePath("","root_file_4.txt"));
                        storage_paths.add(RCTfirebaseStorage.getFilePath("","root_file_5.txt"));
                        storage_paths.add(RCTfirebaseStorage.getFilePath("","root_file_6.txt"));
                        storage_paths.add(RCTfirebaseStorage.getFilePath("","root_file_7.txt"));

                        for(int index = 0; index< storage_paths.size(); index++){
                            System.out.println("Storage Path : ".concat(storage_paths.get(index)));
                        }


                        HashMap<String, String> downloads_urls = RCTfirebaseStorage.getDownloadURL(firebase.getStorage(),storage_paths, 100);
                        ArrayList<String> keys = new ArrayList<>(downloads_urls.keySet());

                        for(int index = 0; index<keys.size(); index++){
                            System.out.println(keys.get(index).concat(" = ").concat(downloads_urls.get(keys.get(index))));
                        }



                    }
                }).start();







            }
        });
    }

    private void setClickListenerF2(){
        f2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        RCTfirebaseStorage.renameFile_WaitProgress(
                                firebase.getStorage(),
                                null,
                                 "root_file_1.txt",
                                "renamed_root_file_1.txt",
                                50
                        );

                        RCTfirebaseStorage.renameFile(firebase.getStorage(),
                                null,
                                "root_file_2.txt",
                                "renamed_root_file_2.txt"
                        );

                        RCTfirebaseStorage.renameFile_WaitProgress(
                                firebase.getStorage(),
                                "test_folder",
                                "root_file_1.txt",
                                "renamed_root_file_1.txt",
                                50
                        );

                        RCTfirebaseStorage.renameFile(
                                firebase.getStorage(),
                                "test_folder",
                                "root_file_2.txt",
                                "renamed_root_file_2.txt"
                        );



                    }
                }).start();
            }
        });
    }

    private void setClickListenerF3(){

        f3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {



                        RCTlocationData current_location = new RCTlocationData(
                                Window3.this,
                                RCTlocationData.MODE_CURRENT,
                                500);


                        System.out.println("--------------------------------------------------------------");
                        System.out.println("Lat : ".concat(String.valueOf(current_location.getAddress().getLatitude())));
                        System.out.println("Lon : ".concat(String.valueOf(current_location.getAddress().getLongitude())));
                        System.out.println("Add : ".concat(String.valueOf(current_location.getAddress().getAddressLine(0))));
                        System.out.println("--------------------------------------------------------------");

                    }
                }).start();
            }
        });


    }






}
