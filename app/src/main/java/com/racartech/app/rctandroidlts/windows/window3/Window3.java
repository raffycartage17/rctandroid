package com.racartech.app.rctandroidlts.windows.window3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.racartech.app.rctandroidlts.FirebaseSingleton;
import com.racartech.app.rctandroidlts.R;
import com.racartech.library.rctandroid.file.RCTfile;
import com.racartech.library.rctandroid.google.firebase.firestore.RCTfirebaseFirestore;
import com.racartech.library.rctandroid.google.firebase.storage.RCTfirebaseStorage;

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

    }

    private void setClickListenerF1(){
        f1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        System.out.println("---------------------------------------------------------------");

                        String root_dir = RCTfile.getDir_ExternalStorageRoot().concat("/apath");
                        String test_text_filepath = root_dir.concat("/test_file.txt");
                        String test_pict_filepath = root_dir.concat("/test_file.png");

                        try {




                            RCTfirebaseStorage.uploadFile(
                                    firebase.getStorage(),
                                    test_text_filepath,
                                    "",
                                    "root_file_1.txt",
                                    100);
                        }catch (Exception ex){
                            ex.printStackTrace();
                        }

                        System.out.println("Check Point 1");

                        try {
                            RCTfirebaseStorage.uploadFile(
                                    firebase.getStorage(),
                                    test_text_filepath,
                                    "/",
                                    "root_file_2.txt",
                                    100);
                        }catch (Exception ex){
                            ex.printStackTrace();
                        }

                        System.out.println("Check Point 2");

                        try {
                            RCTfirebaseStorage.uploadFile(
                                    firebase.getStorage(),
                                    test_text_filepath,
                                    null,
                                    "root_file_3.txt",
                                    100);
                        }catch (Exception ex){
                            ex.printStackTrace();
                        }

                        System.out.println("Check Point 3");

                        System.out.println("---------------------------------------------------------------");



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






}
