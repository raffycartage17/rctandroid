package com.racartech.app.rctandroidlts.maps;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.model.Marker;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.maps.DirectionsApi;
import com.google.maps.model.LatLng;
import com.google.maps.model.TravelMode;
import com.racartech.app.rctandroidlts.R;
import com.racartech.app.rctandroidlts.api.ApiKeyManager;
import com.racartech.library.rctandroid.file.RCTfile;
import com.racartech.library.rctandroid.google.maps.RCTgoogleMaps;
import com.racartech.library.rctandroid.google.maps.RCTgoogleMapsUtil;
import com.racartech.library.rctandroid.location.RCTfacingDirectionListener;
import com.racartech.library.rctandroid.location.RCTlocationUpdateListener;
import com.racartech.library.rctandroid.logging.RCTloggingLocationData;

import java.time.Instant;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class MapsTestActivity extends AppCompatActivity implements
        RCTgoogleMaps.OnPinDropListener,
        RCTfacingDirectionListener.FacingDirectionListener
{

    private FrameLayout mapContainer;
    private RCTgoogleMaps googleMaps = null;
    private Button
            add_map_view_button,
            get_directions_button,
            more_button,
            reset_direction_button,
            function_button;

    private volatile AtomicReference<ArrayList<LatLng>> PIN_POINTS = new AtomicReference<>(new ArrayList<>());


    ImageView facing_direction_compass;

    RCTfacingDirectionListener facing_direction_listener;

    private FirebaseFirestore firestore_instance;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maps_test_activity_layout);



        mapContainer = findViewById(R.id.map_container);



        facing_direction_compass = findViewById(R.id.mtcl_facing_direction_compass_imageview);
        add_map_view_button = findViewById(R.id.maps_test_add_map_view_button);
        more_button = findViewById(R.id.maps_test_more_options_button);
        function_button = findViewById(R.id.mtcl_function_button);

        get_directions_button = findViewById(R.id.maps_test_directions_button);
        reset_direction_button = findViewById(R.id.maps_test_reset_directions_button);

        setLoggingFilePath();
        RCTloggingLocationData.IS_LOGGING_ENABLED.set(false);

        facing_direction_listener = new RCTfacingDirectionListener(this, this);

        firestore_instance = FirebaseFirestore.getInstance();


        //locationUpdate = new RCTlocationUpdateListener(this);
        //locationUpdate.getLocationUpdates(MapsTestActivity.this, 1000);


        addMapView();
        add_map_view_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMapView();
            }
        });

        more_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMoreDialog();
            }
        });

        function_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        String api_key = ApiKeyManager.getGoogleApiKey(firestore_instance);
                        LatLng origin_coordinates = new LatLng(
                                googleMaps.CURRENT_LOCATION_LATITUDE.get(),
                                googleMaps.CURRENT_LOCATION_LONGITUDE.get());

                        double total_driving_distance =
                                RCTgoogleMapsUtil.getDrivingDistance(api_key,origin_coordinates,PIN_POINTS.get().get(0));
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run(){
                                Toast.makeText(MapsTestActivity.this,
                                        "Driving Distance : ".concat(String.valueOf(total_driving_distance)),
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).start();

                /*
                LatLng origin_coordinates = new LatLng(
                        googleMaps.CURRENT_LOCATION_LATITUDE.get(),
                        googleMaps.CURRENT_LOCATION_LONGITUDE.get()
                );

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        String api_key = ApiKeyManager.getGoogleApiKey(firestore_instance);
                        ArrayList<DirectionsApi.RouteRestriction> route_restrictions = new ArrayList<>();
                        route_restrictions.add(DirectionsApi.RouteRestriction.TOLLS);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run(){

                                googleMaps.getDirections(
                                        api_key,
                                        origin_coordinates,
                                        PIN_POINTS.get(),
                                        TravelMode.DRIVING,
                                        route_restrictions,
                                        Instant.ofEpochMilli(System.currentTimeMillis())
                                );
                            }
                        });
                    }
                }).start();
                 */


            }
        });


        get_directions_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                LatLng origin_coordinates = new LatLng(
                        googleMaps.CURRENT_LOCATION_LATITUDE.get(),
                        googleMaps.CURRENT_LOCATION_LONGITUDE.get()
                );

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        String api_key = ApiKeyManager.getGoogleApiKey(firestore_instance);
                        ArrayList<DirectionsApi.RouteRestriction> route_restrictions = new ArrayList<>();
                        route_restrictions.add(DirectionsApi.RouteRestriction.TOLLS);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run(){

                                googleMaps.getDirections(
                                        api_key,
                                        origin_coordinates,
                                        PIN_POINTS.get(),
                                        TravelMode.DRIVING,
                                        route_restrictions,
                                        Instant.ofEpochMilli(System.currentTimeMillis())
                                );
                            }
                        });
                    }
                }).start();

            }
        });

        reset_direction_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PIN_POINTS.get().clear();
            }
        });

    }

    private void addMapView() {

        if(googleMaps != null){
            googleMaps = null;
        }

        googleMaps = new RCTgoogleMaps(
                MapsTestActivity.this,
                MapsTestActivity.this,
                500,
                200
        );
        googleMaps.setOnPinDropListener(this); // Set listener
        mapContainer.addView(googleMaps);
        googleMaps.setCameraFollowLocationUpdate(false);
        googleMaps.setDefaultMaxZoom(17.0F);
        googleMaps.setFirstAutoLocationUpdateFollowCamera(true);

        googleMaps.setOnCurrentDirectionsTotalDistanceCalculated(new RCTgoogleMaps.OnCurrentDirectionsTotalDistanceCalculatedListener() {
            @Override
            public void OnCurrentDirectionsTotalDistanceCalculatedListener(double total_distance) {
                System.out.println("--------------------------------------------");
                System.out.println("Method Called Listener");
                System.out.println("Total Distance : ".concat(String.valueOf(total_distance)));
                System.out.println("--------------------------------------------");
            }
        });

        googleMaps.setOnCurrentLocationUpdated(new RCTgoogleMaps.OnCurrentLocationUpdated() {
            @Override
            public void OnCurrentLocationUpdated(double latitude, double longitude) {
                System.out.println("----------------------------------------------------");
                System.out.println("OnCurrentLocationUpdated");
                System.out.println("Latitude  : ".concat(String.valueOf(latitude)));
                System.out.println("Longitude : ".concat(String.valueOf(longitude)));
                System.out.println("----------------------------------------------------");
            }
        });


    }

    private void showMoreDialog(){

        Dialog dialog = new Dialog(MapsTestActivity.this);
        dialog.setContentView(R.layout.maps_test_activity_more_menu_dialog_layout);
        dialog.setCancelable(false);


        Window window = dialog.getWindow();
        window.setEnterTransition(new android.transition.Fade(android.transition.Fade.IN));
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button close_button = dialog.findViewById(R.id.mtam_close_button);
        Button clear_button = dialog.findViewById(R.id.mtam_clear_log_button);
        Button enable_logging_button = dialog.findViewById(R.id.mtam_enable_logging_button);
        Button disable_logging_button = dialog.findViewById(R.id.mtam_disable_logging_button);
        Button archived_current_record_button = dialog.findViewById(R.id.mtam_archived_current_record_button);
        Button camera_follow_on_location_update_toggle_button = dialog.findViewById(R.id.mtam_camera_follow_on_location_update_toggle_button);

        if(googleMaps.CAMERA_FOLLOW_ON_LOCATION_UPDATE){
            camera_follow_on_location_update_toggle_button.
                    setBackgroundColor(MapsTestActivity.this.getColor(R.color.blue));
            camera_follow_on_location_update_toggle_button.setText("ENABLED");
        }else{
            camera_follow_on_location_update_toggle_button.
                    setBackgroundColor(MapsTestActivity.this.getColor(R.color.dark_gray_text_color));
            camera_follow_on_location_update_toggle_button.setText("DISABLED");
        }

        if(RCTloggingLocationData.IS_LOGGING_ENABLED.get()){
            disable_logging_button.setBackgroundColor(MapsTestActivity.this.getColor(R.color.dark_gray_text_color));
            enable_logging_button.setBackgroundColor(MapsTestActivity.this.getColor(R.color.blue));
        }else{
            enable_logging_button.setBackgroundColor(MapsTestActivity.this.getColor(R.color.dark_gray_text_color));
            disable_logging_button.setBackgroundColor(MapsTestActivity.this.getColor(R.color.blue));
        }


        close_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });



        camera_follow_on_location_update_toggle_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(googleMaps.CAMERA_FOLLOW_ON_LOCATION_UPDATE){
                    camera_follow_on_location_update_toggle_button.
                            setBackgroundColor(MapsTestActivity.this.getColor(R.color.dark_gray_text_color));
                    camera_follow_on_location_update_toggle_button.setText("DISABLED");
                    googleMaps.setCameraFollowLocationUpdate(false);
                }else{
                    camera_follow_on_location_update_toggle_button.
                            setBackgroundColor(MapsTestActivity.this.getColor(R.color.blue));
                    camera_follow_on_location_update_toggle_button.setText("ENABLED");
                    googleMaps.setCameraFollowLocationUpdate(true);
                }
            }
        });

        enable_logging_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                disable_logging_button.setBackgroundColor(MapsTestActivity.this.getColor(R.color.dark_gray_text_color));
                enable_logging_button.setBackgroundColor(MapsTestActivity.this.getColor(R.color.blue));
                RCTloggingLocationData.setLoggingActive();
                Toast.makeText(MapsTestActivity.this, "Logging Enabled", Toast.LENGTH_SHORT).show();
            }
        });

        disable_logging_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enable_logging_button.setBackgroundColor(MapsTestActivity.this.getColor(R.color.dark_gray_text_color));
                disable_logging_button.setBackgroundColor(MapsTestActivity.this.getColor(R.color.blue));
                RCTloggingLocationData.setLoggingInActive();
                Toast.makeText(MapsTestActivity.this, "Logging Disabled", Toast.LENGTH_SHORT).show();
            }
        });


        archived_current_record_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RCTloggingLocationData.archiveCurrentRecord();
                Toast.makeText(MapsTestActivity.this, "Archived Current Record", Toast.LENGTH_SHORT).show();
            }
        });






        clear_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RCTloggingLocationData.clearLogFile();
                Toast.makeText(MapsTestActivity.this, "Location log cleared", Toast.LENGTH_SHORT).show();
            }
        });


        dialog.show();


    }


    public void setLoggingFilePath(){
        String location_log_filepath = RCTfile.getDir_IntAppFiles(MapsTestActivity.this).concat("/rctandroid_location_log.txt");
        RCTloggingLocationData.setFilePath(location_log_filepath);
    }



    // Implement the method from OnPinDropListener interface
    @Override
    public void onPinDrop(Marker new_marker, double latitude, double longitude) {
        runOnUiThread(new Runnable() {
            @Override
            public void run(){
                PIN_POINTS.get().add(new LatLng(latitude,longitude));
            }
        });

    }



    @Override
    public void onFacingDirectionUpdate(float azimuth_in_degrees){
        if(googleMaps != null){
            if(googleMaps.googleMap != null){
                facing_direction_compass.setRotation((azimuth_in_degrees - googleMaps.googleMap.getCameraPosition().bearing));
            }
        }

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        facing_direction_listener.setEnabled(false);

        finish();
    }



    @Override
    protected void onPause() {
        super.onPause();
        if(googleMaps != null) {
            googleMaps.saveCurrentSettings();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(googleMaps != null) {
            googleMaps.saveCurrentSettings();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(googleMaps != null) {
            googleMaps.saveCurrentSettings();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(googleMaps != null) {
            googleMaps.setSettingsFileData();
        }
    }


}
