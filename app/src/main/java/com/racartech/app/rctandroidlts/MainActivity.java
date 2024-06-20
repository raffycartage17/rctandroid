package com.racartech.app.rctandroidlts;

import static android.provider.Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.maps.DirectionsApi;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.LatLng;
import com.google.maps.model.TravelMode;
import com.racartech.app.rctandroidlts.api.ApiKeyManager;
import com.racartech.app.rctandroidlts.firebase.FirestoreTest;
import com.racartech.app.rctandroidlts.functionbuttons.FunctionFive;
import com.racartech.app.rctandroidlts.functionbuttons.FunctionThree;
import com.racartech.app.rctandroidlts.functionbuttons.FunctionTwo;
import com.racartech.app.rctandroidlts.maps.MapsTestActivity;
import com.racartech.app.rctandroidlts.maps.MapsUtilTest;
import com.racartech.app.rctandroidlts.maps.TextToSpeechActivity;
import com.racartech.app.rctandroidlts.othertest.TestQRCodeActivity;
import com.racartech.app.rctandroidlts.resources.BuildConfig;
import com.racartech.app.rctandroidlts.window1.Window1;
import com.racartech.app.rctandroidlts.window1.Window2;
import com.racartech.library.rctandroid.file.RCTfile;
import com.racartech.library.rctandroid.google.firebase.storage.RCTfirebaseStorageTextFileWriter;
import com.racartech.library.rctandroid.google.maps.RCTgoogleMapsUtil;
import com.racartech.library.rctandroid.json.RCTorgJSON;
import com.racartech.library.rctandroid.location.RCTLocationData;
import com.racartech.library.rctandroid.location.RCTdistance;
import com.racartech.library.rctandroid.net.RCTinternet;
import com.racartech.library.rctandroid.notifications.RCTnotifications;
import com.racartech.library.rctandroid.permission.RCTpermission;
import com.racartech.library.rctandroid.time.RCTdateTimeData;
import com.racartech.library.rctandroid.time.RCTmillisecondToTimeData;
import com.racartech.library.rctandroid.time.RCTsecondsToTimeData;

import java.time.Instant;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    Button f1,f2,f3,f4,f5,f6,f7,f8,f9;
    Button window_1_btn,window_2_btn,window_3_btn,window_4_btn;
    Button textbox_enter;
    EditText textbox;
    TextView textview_1;
    TextView textview_2;
    TextView debug_textview;
    Switch switch_1,switch_2;

    ImageButton test_image_button_1;


    private static final int MANAGE_EXTERNAL_STORAGE_PERMISSION_CODE = 100;
    private static final int READ_EXTERNAL_STORAGE_PERMISSION_CODE = 101;
    private static final int WRITE_EXTERNAL_STORAGE_PERMISSION_CODE = 102;
    private int REQUEST_CODE_PERMISSIONS = 1001;
    private final String[] REQUIRED_PERMISSIONS = new String[]{"android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE","android.permission.READ_EXTERNAL_STORAGE"};

    //private static String TEST_FILE_URL = "https://firebasestorage.googleapis.com/v0/b/halalife-21bd8.appspot.com/o/halalife_plant_database.txt?alt=media&token=dd0bb6af-4c6e-449f-be86-9bbd42411d5a";

    private static String TEST_FILE_URL = "https://www.dropbox.com/home?preview=halalife_plant_database.txt";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp(MainActivity.this);


        f1 = findViewById(R.id.mm_f1_button);
        f2 = findViewById(R.id.mm_f2_button);
        f3 = findViewById(R.id.mm_f3_button);
        f4 = findViewById(R.id.mm_f4_button);
        f5 = findViewById(R.id.mm_f5_button);
        f6 = findViewById(R.id.mm_f6_button);
        f7 = findViewById(R.id.mm_f7_button);
        f8 = findViewById(R.id.mm_f8_button);
        f9 = findViewById(R.id.mm_f9_button);
        debug_textview = findViewById(R.id.mm_debug_textview);
        textbox = findViewById(R.id.mm_textbox);

        window_1_btn = findViewById(R.id.mm_window1_button);
        window_2_btn = findViewById(R.id.mm_window2_button);
        window_3_btn = findViewById(R.id.mm_window3_button);
        window_4_btn = findViewById(R.id.mm_window4_button);

        textbox_enter = findViewById(R.id.mm_textbox_enter_button);
        switch_1 = findViewById(R.id.mm_switch1);
        switch_2 = findViewById(R.id.mm_switch2);

        textview_1 = findViewById(R.id.mm_textview_1);
        textview_2 = findViewById(R.id.mm_textview_2);

        test_image_button_1 = findViewById(R.id.mm_test_image_button_1);










        int button_unpressed_color = MainActivity.this.getResources().getColor(R.color.orange,null);
        int button_pressed_color = MainActivity.this.getResources().getColor(R.color.yellow,null);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            RCTpermission.allowPermissions(this,new String[]{
                    Manifest.permission.CAMERA,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.MANAGE_EXTERNAL_STORAGE

            },0);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if(!Environment.isExternalStorageManager()){
                RCTpermission.allowPermission_MANAGE_EXTERNAL_STORAGE(MainActivity.this, 200);
            }
        }
        promptUserAllowPermission();



        String debug_file = RCTfile.getDir_ExternalStorageRoot().concat("/apath/debug.txt");
        RCTfile.createFile(debug_file);



        f1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //FunctionOne.launch(MainActivity.this, MainActivity.this);

                Intent intent = new Intent(MainActivity.this, MapsTestActivity.class);
                startActivity(intent);


            }
        });

        f2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FunctionTwo.class);
                startActivity(intent);
            }
        });

        f3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, FunctionThree.class);
                startActivity(intent);

            }
        });

        f4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, TextToSpeechActivity.class);
                startActivity(intent);

            }
        });

        f5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, FunctionFive.class);
                startActivity(intent);
            }
        });

        f6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                ArrayList<Integer> initial_arraylist = new ArrayList<>();
                initial_arraylist.add(5);
                initial_arraylist.add(108);
                initial_arraylist.add(100);
                String json_string = RCTorgJSON.arrayListIntegerToJSONString(initial_arraylist);
                System.out.println("--------------------------------------------");
                System.out.println("JSON String : ".concat(json_string));

                ArrayList<Integer> parsed_array = RCTorgJSON.jsonStringToArrayListInteger(json_string);
                for(int index = 0; index<parsed_array.size(); index++){
                    System.out.println("Index (".
                            concat(String.valueOf(index).
                                    concat(") : ").
                                    concat(String.valueOf(parsed_array.get(index)))));
                }
                System.out.println("--------------------------------------------");
            }
        });

        f7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent qrcode_intent = new Intent(MainActivity.this, TestQRCodeActivity.class);
                startActivity(qrcode_intent);
            }
        });

        f8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        String storage_dir = "test_write";
                        String storage_file = "aaa.txt";


                        RCTfirebaseStorageTextFileWriter storage_writer = new RCTfirebaseStorageTextFileWriter(
                                FirebaseStorage.getInstance(),
                                MainActivity.this,
                                storage_dir,
                                storage_file,
                                100,
                                RCTinternet.BYTES_IN_1MB,
                                60000,
                                60000
                        );

                        storage_writer.initialize();


                        if(!textbox.getText().toString().isEmpty()) {
                            ArrayList<String> file_contents = storage_writer.getFileContents();
                            file_contents.add(textbox.getText().toString());

                            System.out.println("--------------------------------------------");
                            System.out.println(">>>>>>>> F8 File Contents <<<<<<<<");
                            for(int index = 0; index< file_contents.size(); index++){
                                System.out.println(file_contents.get(index));
                            }
                            System.out.println("--------------------------------------------");
                            System.out.println("Override : ".concat(
                                    String.valueOf(storage_writer.override(file_contents))));
                            System.out.println("Save : ".concat(
                                    String.valueOf(storage_writer.save())));
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    textbox.setText("");
                                }
                            });
                        }
                    }
                }).start();



            }
        });

        f9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //MapsUtilTest.multiDestinationRouteWithInterval();
                FirestoreTest.test001();
            }
        });






        window_1_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Window1.class);
                startActivity(intent);
            }
        });
        window_2_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Window2.class);
                startActivity(intent);
            }
        });





    }



    private void promptUserAllowPermission(){
        System.out.println("--------------------------------------------");
        System.out.println("promptUserAllowPermission");
        System.out.println("--------------------------------------------");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            System.out.println("--------------------------------------------");
            System.out.println("Build.VERSION.SDK_INT >= Build.VERSION_CODES.R");
            System.out.println("--------------------------------------------");
            System.out.println("isExternalStorageManager : ".concat(String.valueOf(Environment.isExternalStorageManager())));
            System.out.println("--------------------------------------------");
            if(!Environment.isExternalStorageManager()){
                System.out.println("--------------------------------------------");
                System.out.println("Environment.isExternalStorageManager()");
                System.out.println("--------------------------------------------");
                Context app_context = MainActivity.this;
                Dialog request_dialog = new Dialog(app_context);
                request_dialog.setContentView(R.layout.standard_dialog_box);
                request_dialog.setCancelable(true);

                TextView dialog_title = request_dialog.findViewById(R.id.standard_dialog_title_textview);
                TextView dialog_description = request_dialog.findViewById(R.id.standard_dialog_description_textview);
                Button ok_button = request_dialog.findViewById(R.id.standard_dialog_right_operation_button);
                Button cancel_button = request_dialog.findViewById(R.id.standard_dialog_left_operation_button);
                ok_button.setText(app_context.getString(R.string.OK));
                cancel_button.setText(app_context.getString(R.string.Cancel));
                dialog_title.setText(app_context.getString(R.string.app_name));
                dialog_description.setText(app_context.getString(R.string.access_all_files_permission_dialog_request_description));
                request_dialog.show();
                ok_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent new_intent = new Intent(ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION, Uri.parse("package:" + BuildConfig.APPLICATION_ID));
                        startActivityForResult(new_intent, MANAGE_EXTERNAL_STORAGE_PERMISSION_CODE);
                        request_dialog.dismiss();
                    }
                });
                cancel_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RCTnotifications.makeToast_SHORT(app_context, "Permission Request Denied");
                        request_dialog.dismiss();
                    }
                });
            }
        }
        checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE,WRITE_EXTERNAL_STORAGE_PERMISSION_CODE);
        checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE,READ_EXTERNAL_STORAGE_PERMISSION_CODE);


    }

    private void checkPermission(String permission, int request_code){
        if(ContextCompat.checkSelfPermission(MainActivity.this,permission)== PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{permission},request_code);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }




}