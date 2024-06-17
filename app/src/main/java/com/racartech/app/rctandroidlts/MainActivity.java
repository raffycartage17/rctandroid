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
import com.racartech.app.rctandroidlts.functionbuttons.FunctionFive;
import com.racartech.app.rctandroidlts.functionbuttons.FunctionThree;
import com.racartech.app.rctandroidlts.functionbuttons.FunctionTwo;
import com.racartech.app.rctandroidlts.maps.MapsTestActivity;
import com.racartech.app.rctandroidlts.maps.TextToSpeechActivity;
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





        RCTpermission.allowPermissions(this,new String[]{
                Manifest.permission.CAMERA,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION

        },0);
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
                System.out.println("----------------------------------------------------------------");
                long cached_current_millis = System.currentTimeMillis();

                RCTdateTimeData dt_data = new RCTdateTimeData(cached_current_millis);
                // Print out every variable from dt_data object
                System.out.println("Year: " + dt_data.YEAR);
                System.out.println("Month: " + dt_data.MONTH);
                System.out.println("Date: " + dt_data.DATE);
                System.out.println("Hour: " + dt_data.HOUR);
                System.out.println("Minute: " + dt_data.MINUTE);
                System.out.println("Second: " + dt_data.SECOND);
                System.out.println("Millisecond: " + dt_data.MILLISECOND);
                System.out.println("Unix Epoch Millisecond: " + dt_data.UNIX_EPOCH_MILLISECOND);
                System.out.println("----------------------------------------------------------------");
                RCTdateTimeData new_data = new RCTdateTimeData(
                        dt_data.YEAR,
                        dt_data.MONTH,
                        dt_data.DATE,
                        dt_data.HOUR,
                        dt_data.MINUTE,
                        dt_data.SECOND,
                        dt_data.MILLISECOND
                );
                System.out.println("Year: " + new_data.YEAR);
                System.out.println("Month: " + new_data.MONTH);
                System.out.println("Date: " + new_data.DATE);
                System.out.println("Hour: " + new_data.HOUR);
                System.out.println("Minute: " + new_data.MINUTE);
                System.out.println("Second: " + new_data.SECOND);
                System.out.println("Millisecond: " + new_data.MILLISECOND);
                System.out.println("Unix Epoch Millisecond: " + new_data.UNIX_EPOCH_MILLISECOND);


                System.out.println("----------------------------------------------------------------");
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
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        /*
                        System.out.println("---------------------------------------------------------------");

                        double my_house_lat = 14.78073813;
                        double my_house_lng = 120.87887886;


                        double ref_lat = 14.60728483;
                        double ref_lng = 120.98522199;



                        RCTLocationData my_house_location = new RCTLocationData(MainActivity.this, my_house_lat,my_house_lng);
                        RCTLocationData reference_location = new RCTLocationData(MainActivity.this, ref_lat,ref_lng);

                        Address my_house_address = my_house_location.getAddress();
                        Address ref_address = reference_location.getAddress();
                        System.out.println("House Address : ".concat(String.valueOf(my_house_address.getAddressLine(0))));
                        System.out.println("Refer Address : ".concat(String.valueOf(ref_address.getAddressLine(0))));
                        System.out.println("---------------------------------------------------------------");
                        System.out.println("Distance M  : ".concat(String.valueOf(RCTdistance.calculateDistance_M(my_house_address, ref_address))));
                        System.out.println("Distance KM : ".concat(String.valueOf(RCTdistance.calculateDistance_KM(my_house_address, ref_address))));

                        System.out.println("---------------------------------------------------------------");

                        ArrayList<DirectionsApi.RouteRestriction> route_rest = new ArrayList<>();
                        route_rest.add(DirectionsApi.RouteRestriction.TOLLS);
                        DirectionsRoute direction_route =
                                RCTgoogleMapsUtil.getAllDirectionRoute(
                                        ApiKeyManager.getGoogleApiKey(FirebaseFirestore.getInstance()),
                                        new LatLng(my_house_address.getLatitude(), my_house_address.getLongitude()),
                                        new LatLng(ref_address.getLatitude(), ref_address.getLongitude()),
                                        TravelMode.DRIVING,
                                        route_rest,
                                        Instant.now()
                                ).get(0);

                        System.out.println("---------------------------------------------------------------");
                        double driving_distance = RCTgoogleMapsUtil.getDrivingDistance_M(direction_route);
                        System.out.println("Driving Distance : ".concat(String.valueOf(driving_distance)));
                        System.out.println("Driving Time Sec : ".concat(String.valueOf(RCTgoogleMapsUtil.getDrivingTime_Seconds(direction_route))));



                        System.out.println("---------------------------------------------------------------");

                         */

                        LatLng origin = new LatLng(14.78073813, 120.87887886);

                        ArrayList<LatLng> destinations = new ArrayList<>();
                        destinations.add(new LatLng(18.19468676,120.59243305));
                        destinations.add(new LatLng(18.3465677,121.64292501));
                        destinations.add(new LatLng(14.82302884,120.90089204));

                        ArrayList<DirectionsApi.RouteRestriction> route_rest = new ArrayList<>();
                        route_rest.add(DirectionsApi.RouteRestriction.TOLLS);

                        long start = System.currentTimeMillis();

                        ArrayList<DirectionsRoute> multi_routes =
                                RCTgoogleMapsUtil.getMultiPointDirectionRoute(
                                        ApiKeyManager.getGoogleApiKey(FirebaseFirestore.getInstance()),
                                        origin,
                                        destinations,
                                        TravelMode.DRIVING,
                                        route_rest,
                                        Instant.now()
                                );

                        double driving_distance = 0;
                        long driving_time = 0;
                        for(int index = 0; index< multi_routes.size(); index++){
                            driving_distance += RCTgoogleMapsUtil.getDrivingDistance_M(multi_routes.get(index));
                            driving_time += RCTgoogleMapsUtil.getDrivingTime_Seconds(multi_routes.get(index));
                        }

                        driving_distance = driving_distance/1000.0;
                        long end = System.currentTimeMillis();
                        long elapsed_time = end-start;

                        System.out.println("----------------------------------------------------------------------");
                        System.out.println("Driving Distance : ".concat(String.valueOf(driving_distance)));
                        System.out.println("Driving Time     : ".concat(String.valueOf(driving_time)));
                        System.out.println("Elapsed Time     : ".concat(String.valueOf(elapsed_time)));
                        System.out.println("----------------------------------------------------------------------");


                        System.out.println("----------------------------------------------------------------------");
                        RCTsecondsToTimeData seconds_time = new RCTsecondsToTimeData(driving_time);
                        System.out.println("ST Day     : ".concat(String.valueOf(seconds_time.DAYS)));
                        System.out.println("ST Hour    : ".concat(String.valueOf(seconds_time.HOURS)));
                        System.out.println("ST Minute  : ".concat(String.valueOf(seconds_time.MINUTES)));
                        System.out.println("ST Seconds : ".concat(String.valueOf(seconds_time.SECONDS)));
                        System.out.println("----------------------------------------------------------------------");
                        RCTmillisecondToTimeData ms_time = new RCTmillisecondToTimeData((driving_time*1000)+240);
                        System.out.println("MST Day     : ".concat(String.valueOf(ms_time.DAYS)));
                        System.out.println("MST Hour    : ".concat(String.valueOf(ms_time.HOURS)));
                        System.out.println("MST Minute  : ".concat(String.valueOf(ms_time.MINUTES)));
                        System.out.println("MST Seconds : ".concat(String.valueOf(ms_time.SECONDS)));
                        System.out.println("MST Millise : ".concat(String.valueOf(ms_time.MILLISECONDS)));
                        System.out.println("----------------------------------------------------------------------");


                    }
                }).start();
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if(!Environment.isExternalStorageManager()){
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