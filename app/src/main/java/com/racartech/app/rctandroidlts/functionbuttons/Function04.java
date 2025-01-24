package com.racartech.app.rctandroidlts.functionbuttons;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.storage.FirebaseStorage;
import com.racartech.app.rctandroidlts.R;
import com.racartech.library.rctandroid.google.firebase.storage.RCTfirebaseStorage;

public class Function04 extends AppCompatActivity
{


    Button test_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.function_three_activity_layout);

        test_button = findViewById(R.id.f3a_test_button);


        String fire_storage_dir = "test_folder";
        String fire_storage_filename = "aaa.txt";

        test_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String file_path = RCTfirebaseStorage.cacheFileDefault(
                                Function04.this,
                                FirebaseStorage.getInstance(),
                                fire_storage_dir,
                                fire_storage_filename,
                                "txt",
                                100
                        );



                        System.out.println("------------------------------------");
                        System.out.println("File Path : ".concat(file_path));
                        System.out.println("------------------------------------");
                    }
                }).start();




            }
        });

    }



}


