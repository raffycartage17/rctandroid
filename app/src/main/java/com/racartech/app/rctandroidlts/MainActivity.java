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
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.crazzyghost.alphavantage.timeseries.response.StockUnit;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.DirectionsApi;
import com.google.maps.model.TrafficModel;
import com.google.maps.model.TravelMode;
import com.google.maps.model.Unit;
import com.racartech.app.rctandroidlts.api.ApiKeyManager;
import com.racartech.app.rctandroidlts.firebase.FirestoreTest;
import com.racartech.app.rctandroidlts.functionbuttons.Function10;
import com.racartech.app.rctandroidlts.functionbuttons.Function11;
import com.racartech.app.rctandroidlts.functionbuttons.Function12;
import com.racartech.app.rctandroidlts.functionbuttons.Function13;
import com.racartech.app.rctandroidlts.functionbuttons.Function14;
import com.racartech.app.rctandroidlts.functionbuttons.Function15;
import com.racartech.app.rctandroidlts.functionbuttons.Function16;
import com.racartech.app.rctandroidlts.functionbuttons.Function17;
import com.racartech.app.rctandroidlts.functionbuttons.Function18;
import com.racartech.app.rctandroidlts.functionbuttons.Function19;
import com.racartech.app.rctandroidlts.functionbuttons.Function20;
import com.racartech.app.rctandroidlts.functionbuttons.Function21;



import com.racartech.app.rctandroidlts.maps.MapsTestActivity;
import com.racartech.app.rctandroidlts.resources.BuildConfig;
import com.racartech.app.rctandroidlts.util.MiscellaneousDataUtil;
import com.racartech.app.rctandroidlts.windows.Window1;
import com.racartech.app.rctandroidlts.windows.Window2;
import com.racartech.app.rctandroidlts.windows.window3.Window3;
import com.racartech.library.rctandroid.file.RCTdirectory;
import com.racartech.library.rctandroid.file.RCTfile;
import com.racartech.library.rctandroid.finance.stock.alphavantage.DailyHistory;
import com.racartech.library.rctandroid.finance.stock.alphavantage.DailyHistoryUtil;
import com.racartech.library.rctandroid.google.firebase.firestore.RCTfirebaseFirestore;
import com.racartech.library.rctandroid.google.maps.distancematrix.DistanceMatrixResult;
import com.racartech.library.rctandroid.google.maps.distancematrix.DistanceMatrixResults;
import com.racartech.library.rctandroid.google.maps.distancematrix.RCTgcpDistanceMatrix;
import com.racartech.library.rctandroid.json.RCTgoogleGSON;
import com.racartech.library.rctandroid.media.RCTbitmap;
import com.racartech.library.rctandroid.media.image.RCTtranscribeImageToText;
import com.racartech.library.rctandroid.notifications.RCTnotifications;
import com.racartech.library.rctandroid.permission.RCTpermission;
import com.racartech.library.rctandroid.time.RCTdateTimeData;

import org.json.JSONObject;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    Button f1,f2,f3,f4,f5,f6,f7,f8,f9,f10,f11,f12,f13,f14,f15,f16,f17,f18,f19,f20,f21;
    Button window_1_btn,window_2_btn,window_3_btn,window_4_btn;
    Button textbox_enter;
    EditText textbox;
    TextView textview_1;
    TextView textview_2;
    TextView debug_textview;
    Switch switch_1,switch_2;

    ImageView test_imageview;


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

        //TODO Firebase Storage
        //TODO - rename ---, move ---, copy --- (for both single file and multiple file configuration)
        //TODO - getDownloadURL for multiple files
        //TODO - all methods should have a variant that support file paths aside from (directory, file_name) configuration


        f1 = findViewById(R.id.mm_f1_button);
        f2 = findViewById(R.id.mm_f2_button);
        f3 = findViewById(R.id.mm_f3_button);
        f4 = findViewById(R.id.mm_f4_button);
        f5 = findViewById(R.id.mm_f5_button);
        f6 = findViewById(R.id.mm_f6_button);
        f7 = findViewById(R.id.mm_f7_button);
        f8 = findViewById(R.id.mm_f8_button);
        f9 = findViewById(R.id.mm_f9_button);
        f10 = findViewById(R.id.mm_f10_button);
        f11 = findViewById(R.id.mm_f11_button);
        f12 = findViewById(R.id.mm_f12_button);
        f13 = findViewById(R.id.mm_f13_button);
        f14 = findViewById(R.id.mm_f14_button);
        f15 = findViewById(R.id.mm_f15_button);
        f16 = findViewById(R.id.mm_f16_button);
        f17 = findViewById(R.id.mm_f17_button);
        f18 = findViewById(R.id.mm_f18_button);
        f19 = findViewById(R.id.mm_f19_button);
        f20 = findViewById(R.id.mm_f20_button);
        f21 = findViewById(R.id.mm_f21_button);



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

        test_imageview = findViewById(R.id.mm_imageview);

        FirebaseSingleton firebase = FirebaseSingleton.getInstance();


        FirebaseFirestore fs_instace = firebase.getFirestore();
        FirebaseStorage storage_instance = firebase.getStorage();







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

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String source_dir = RCTfile.getDir_ExternalStorageRoot().concat("/apath");
                        ArrayList<String> the_files  = RCTdirectory.getImmediateImageFileList_ArrayList(source_dir);
                        ArrayList<String> base64s = RCTfile.imageFileToBase64WT(the_files,true, 20);
                        ArrayList<Bitmap> bitmaps = RCTbitmap.getBitmapWT(base64s,true);
                        ArrayList<String> new_file_path =
                                RCTfile.createPaths(
                                        source_dir,
                                        "zconverted_",
                                        "jpg",
                                        the_files.size(),
                                        true
                                );

                        System.out.println("New File Path Length : ".concat(String.valueOf(new_file_path.size())));

                        RCTbitmap.saveBitmapAsFile(bitmaps,new_file_path);

                    }
                }).start();


                /*
                Intent intent = new Intent(MainActivity.this, FunctionTwo.class);
                startActivity(intent);

                 */
            }
        });

        f3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

                        System.out.println(">>>>>>> 0");
                        String api_key = ApiKeyManager.getGoogleApiKey(FirebaseFirestore.getInstance());

                        System.out.println("API KEY : ".concat(api_key));

                        System.out.println(">>>>>>> 1");

                        ArrayList<LatLng> coords_points = new ArrayList<>();
                        coords_points.add(new LatLng(14.780549635766455, 120.87858700829007)); //Bulakan House
                        coords_points.add(new LatLng(14.60753322210268, 120.98537947788945)); //Central Market, Recto Manila
                        coords_points.add(new LatLng(14.553934398856514, 120.9946813975631)); //Triumph Tower, Gil Puyat, Makati
                        coords_points.add(new LatLng(14.535621756384321, 121.04328452484178)); //Ey GDS Taguig
                        coords_points.add(new LatLng(14.578543900528189, 121.05922551598768)); //Gold Loop Php 60 Parking, Ortigas
                        coords_points.add(new LatLng(14.58564782575032, 121.11696542474726)); // Cainta Apartment
                        coords_points.add(new LatLng(14.566913043022218, 121.02341474334251)); // PJL Corporate Center, Kalayaan Ave, Makati City
                        coords_points.add(new LatLng(14.823065781993558, 120.90084950945985)); // STI College Balagtas
                        coords_points.add(new LatLng(14.839696868627657, 120.86857903416946)); //Mark Justine Marasigan,RMM Garden Guiguinto Bulacan
                        coords_points.add(new LatLng(14.792815889115987, 120.93038840605449)); //Jan Israel Lopez House
                        //coords_points.add(new LatLng(14.760467997533768, 120.95833725974518)); //Russel Juanito House
                        //coords_points.add(new LatLng(14.537244858228158, 120.98256259503691)); //MOA North Parking


                        //Bulakan, San Fernando, Candaba, Baliwag,  Bulakan
//                        coords_points.add(new LatLng(15.072667222039405, 120.75500538804285));
//                        coords_points.add(new LatLng(14.780549635766455, 120.87858700829007));
//                        coords_points.add(new LatLng(14.842019283854158, 120.81225125951276));
//                        coords_points.add(new LatLng(14.963867465976588, 120.89716855079183));
//                        coords_points.add(new LatLng(15.020763317440231, 120.69954225435109));
//                        coords_points.add(new LatLng(15.019089057419626, 120.8646631561282));


                        try {
                            DistanceMatrixResults the_result = RCTgcpDistanceMatrix.getDistanceMatrix_Departure(
                                    api_key,
                                    coords_points,
                                    coords_points,
                                    DirectionsApi.RouteRestriction.TOLLS,
                                    TrafficModel.BEST_GUESS,
                                    TravelMode.DRIVING, null, null,
                                    Instant.now(),
                                    Unit.METRIC,
                                    100
                            );

                            System.out.println(">>>>>>> 2");
                            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                            System.out.println("HELLLLLLLLLLLLLO");
                            ArrayList<DistanceMatrixResult> tsp = RCTgcpDistanceMatrix.getMostEfficientRoute_NearestNeighbor(
                                    new LatLng(14.780549635766455, 120.87858700829007),
                                    the_result.DISTANCE_MATRIX_RESULT,
                                    new LatLng(14.780549635766455, 120.87858700829007)
                            );

                            RCTgcpDistanceMatrix.printTSP(MainActivity.this,tsp);

                            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

                            //the_result.print(MainActivity.this);
                        }catch (Exception ex){
                            ex.printStackTrace();
                        }




                    }
                }).start();

                //Intent intent = new Intent(MainActivity.this, FunctionThree.class);
                //startActivity(intent);

            }
        });

        f4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        String save_file_path = RCTfile.getDir_ExternalStorageRoot().concat("/apath/stock_data.txt");

                        String alpha_vantage_api_key = "F7UDEJXW4A5FIEA4";

                        ArrayList<String> datas = new ArrayList<>();



                        if(!RCTfile.doesFileExist(save_file_path)){
                            RCTfile.createFile(save_file_path);
                        }




                        String google_class_c_symbol = "GOOG";
                        String google_class_a_symbol = "GOOGL";
                        String microsoft_symbol = "MSFT";
                        String amazon_symbol = "AMZN";
                        String shopify_symbol = "SHOP";
//                        String fedex_symbol = "FDX";
//                        String ups_symbol = "UPS";
//                        String netflix_symbol = "NFLX";
//                        String tesla_symbol = "TSLA";

                        if(RCTfile.doesFileExist(save_file_path)) {
                            System.out.println("0");
                            datas.add(DailyHistoryUtil.toJSONObject(google_class_c_symbol, DailyHistoryUtil.getHistory(alpha_vantage_api_key, google_class_c_symbol)).toString());
                            System.out.println("1");
                            datas.add(DailyHistoryUtil.toJSONObject(google_class_a_symbol, DailyHistoryUtil.getHistory(alpha_vantage_api_key, google_class_a_symbol)).toString());
                            System.out.println("2");
                            datas.add(DailyHistoryUtil.toJSONObject(microsoft_symbol, DailyHistoryUtil.getHistory(alpha_vantage_api_key, microsoft_symbol)).toString());
                            System.out.println("3");
                            datas.add(DailyHistoryUtil.toJSONObject(amazon_symbol, DailyHistoryUtil.getHistory(alpha_vantage_api_key, amazon_symbol)).toString());
                            System.out.println("4");
                            datas.add(DailyHistoryUtil.toJSONObject(shopify_symbol, DailyHistoryUtil.getHistory(alpha_vantage_api_key, shopify_symbol)).toString());
                            System.out.println("5");
                        }

                        try {
                            RCTfile.overrideFile(save_file_path,datas);
                            System.out.println("finished");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        try{

                        ArrayList<String> save_contents = RCTfile.readFile_ArrayList(save_file_path);


                            JSONObject microsoft_data = new JSONObject(save_contents.get(2));
                            System.out.println(">>> Company : ".concat(microsoft_data.getString("company")));
                            List<StockUnit> units = DailyHistoryUtil.toStockUnitList(microsoft_data);
                            System.out.println("Microsoft Unit Size : ".concat(String.valueOf(units)));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }




                    }
                }).start();



            }
        });



        f5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                new Thread(new Runnable(){
                    @Override
                    public void run(){
                        String alpha_vantage_api_key = "F7UDEJXW4A5FIEA4";
                        DailyHistory daily_history = new DailyHistory(alpha_vantage_api_key,"GOOG");

                        RCTdateTimeData from_date = new RCTdateTimeData(2020,1,1);
                        RCTdateTimeData to_date = new RCTdateTimeData(2023,12,31);

                        long start = System.currentTimeMillis();
                        //
                        ArrayList<StockUnit> stock_units = DailyHistoryUtil.filterDataBaseOnTime(daily_history.getDAILY_HISTORY(),from_date,to_date);

                        System.out.println("-------------------------------------------------------");
                        for(int index = 0; index<stock_units.size(); index++){
                            StockUnit current = stock_units.get(index);
                            System.out.println("Stock Data Date : ".concat(current.getDate()));
                            System.out.println("-------------------------------------------------------");
                        }
                        long end = System.currentTimeMillis();
                        long elapsed_time = end-start;
                        System.out.println("Elapsed Time : ".concat(String.valueOf(elapsed_time)));

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

                        try {
                            String the_path = "/first_col/first_docu/second_col/second_docu";

                            String collection_path = RCTfirebaseFirestore.getCollectionPath(the_path);
                            String collection_name = RCTfirebaseFirestore.getCollectionNameFromPath(the_path);
                            String docu_name = RCTfirebaseFirestore.getDocumentNameFromPath(the_path);

                            System.out.println("-------------------------------------");
                            System.out.println("Raw Path        : ".concat(the_path));
                            System.out.println("Collection Path : ".concat(collection_path));
                            System.out.println("Collection Name : ".concat(collection_name));
                            System.out.println("Document Name   : ".concat(docu_name));
                            System.out.println("-------------------------------------");
                        }catch (Exception ex){
                            ex.printStackTrace();
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

        f10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Function10.entry(MainActivity.this, MainActivity.this);
                RCTfile.getDir_IntAppFiles()
            }
        });

        f11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                            }
                        });
                    }
                }).start();
                Function11.entry();
            }
        });

        f12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Function12.entry();
            }
        });

        f13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Function13.entry();
            }
        });

        f14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Function14.entry();
            }
        });

        f15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Function15.entry();
            }
        });

        f16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Function16.entry();
            }
        });

        f17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Function17.entry();
            }
        });

        f18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Function18.entry();
            }
        });

        f19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Function19.entry();
            }
        });

        f20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Function20.entry();
            }
        });

        f21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Function21.entry();
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
        window_3_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Window3.class);
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