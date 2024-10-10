package com.racartech.app.rctandroidlts;

import static android.provider.Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
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
import com.racartech.app.rctandroidlts.firebase.FirestoreTest;
import com.racartech.app.rctandroidlts.functionbuttons.FunctionThree;
import com.racartech.app.rctandroidlts.functionbuttons.FunctionTwo;
import com.racartech.app.rctandroidlts.maps.MapsTestActivity;
import com.racartech.app.rctandroidlts.maps.TextToSpeechActivity;
import com.racartech.app.rctandroidlts.resources.BuildConfig;
import com.racartech.app.rctandroidlts.util.MiscellaneousDataUtil;
import com.racartech.app.rctandroidlts.windows.Window1;
import com.racartech.app.rctandroidlts.windows.Window2;
import com.racartech.library.rctandroid.file.RCTfile;
import com.racartech.library.rctandroid.google.firebase.firestore.RCTfirebaseFirestore;
import com.racartech.library.rctandroid.json.RCTgoogleGSON;
import com.racartech.library.rctandroid.media.RCTbitmap;
import com.racartech.library.rctandroid.media.image.RCTtranscribeImageToText;
import com.racartech.library.rctandroid.notifications.RCTnotifications;
import com.racartech.library.rctandroid.permission.RCTpermission;
import com.racartech.library.rctandroid.time.RCTdateTimeData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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


    RCTtranscribeImageToText transcriber = null;


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


                String collection = "test_collection";
                String document = "test_document";
                String field_name = "test_field_object";
                new Thread(new Runnable(){
                    @Override
                    public void run(){
                        TestObjectRCT test_object = new TestObjectRCT(
                                100,
                                "Rafael",
                                "Andaya",
                                "Cartagena",
                                new RCTdateTimeData(2000,4,25).UNIX_EPOCH_MILLISECOND,
                                27.1
                        );

                        try {
                            RCTfirebaseFirestore.createField(
                                    FirebaseFirestore.getInstance(),
                                    collection,
                                    document,
                                    field_name,
                                    test_object
                            );
                        }catch (Exception ex){
                            ex.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        f6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                new Thread(new Runnable(){
                    @Override
                    public void run(){
                        ArrayList<String> test_list_string = new ArrayList<>();
                        test_list_string.add("zero");
                        test_list_string.add("one");
                        test_list_string.add("two");
                        test_list_string.add("three");
                        test_list_string.add("four");
                        test_list_string.add("five");
                        ArrayList<Integer> test_list_int = new ArrayList<>();
                        test_list_int.add(0);
                        test_list_int.add(1);
                        test_list_int.add(2);
                        test_list_int.add(3);
                        test_list_int.add(4);

                        Map<String, Object> document_data = new HashMap<>();
                        document_data.put("id", 10);
                        document_data.put("first_name", "Rafael");
                        document_data.put("middle_name", "Andaya");
                        document_data.put("last_name", "Cartagena");
                        document_data.put("birth_ms", 956635200000L);
                        document_data.put("test_list_string",test_list_string);
                        document_data.put("test_list_integer",test_list_int);
                        document_data.put("test_null", null);


                        String json_string = RCTgoogleGSON.mapToJsonString(document_data);
                        HashMap<String, Object> desir_map = RCTgoogleGSON.jsonStringToHashMap(json_string);
                        System.out.println("--------------------------------------------------");
                        System.out.println("JSON String : ".concat(json_string));
                        System.out.println("--------------------------------------------------");


                    }
                }).start();
            }
        });

        f7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {


                        String sd_card_root = RCTfile.getExternalSdCardPath(MainActivity.this);

                        String download_url =  MiscellaneousDataUtil.
                                getLanguageTrainedModel_English(FirebaseStorage.getInstance());


                        String test_image_path = RCTfile.getDir_ExternalStorageRoot().concat("/apath/test_2.png");
                        Bitmap test_bitmap = RCTbitmap.getBitmapForFile(test_image_path);

                        long start = System.currentTimeMillis();
                        if(transcriber == null) {
                            System.out.println("Transcriber is NULL");
                            /*
                            transcriber = new RCTtranscribeImageToText(
                                    MainActivity.this,
                                    download_url,
                                    RCTtranscribeImageToText.LANGUAGE_ENGLISH
                            );
                             */

                            //RCTfile.getDir_ExternalStorageRoot()
                            transcriber = new RCTtranscribeImageToText(
                                    download_url,
                                    RCTtranscribeImageToText.LANGUAGE_ENGLISH,
                                    sd_card_root.concat("/bpath")
                            );
                        }
                        String extractedText = transcriber.getTextFromBitmap(test_bitmap);
                        long end = System.currentTimeMillis();
                        long elapsed_time = end-start;

                        System.out.println("--------------------------------------------------------");
                        System.out.println(extractedText);
                        System.out.println("--------------------------------------------------------");
                        System.out.println("Elapsed Time : ".concat(String.valueOf(elapsed_time)));
                        System.out.println("--------------------------------------------------------");

                        //transcriber.close();



                    }
                }).start();
            }
        });

        f8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        long start = System.currentTimeMillis();

                        String collection = "Bulacan";
                        String document = "Cities";
                        String field = "Baliwag";

                        /*
                        String field_value = RCTfirebaseFirestore.readField(FirebaseFirestore.getInstance(),
                                collection,document,field,100);
                        System.out.println("-------------------------------------------------------------------------");
                        System.out.println("Field Value : ".concat(field_value));
                        System.out.println("-------------------------------------------------------------------------");

                         */


                        long document_size = RCTfirebaseFirestore.getDocumentSize(
                                FirebaseFirestore.getInstance(),
                                collection,document,
                                100);

                        long end = System.currentTimeMillis();
                        long elapsed_time = end-start;

                        System.out.println("-------------------------------------------------------------------------");
                        System.out.println("Document Size (Bytes) : ".concat(String.valueOf(document_size)));
                        System.out.println("-------------------------------------------------------------------------");
                        System.out.println("Elapse Time (ms)      : ".concat(String.valueOf(elapsed_time)));
                        System.out.println("--------------------------------------------------------------------------");





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