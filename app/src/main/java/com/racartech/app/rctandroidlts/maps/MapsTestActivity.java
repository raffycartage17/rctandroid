package com.racartech.app.rctandroidlts.maps;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.racartech.app.rctandroidlts.R;
import com.racartech.library.rctandroid.file.RCTfile;
import com.racartech.library.rctandroid.google.maps.RCTgoogleMapsDropPin;
import com.racartech.library.rctandroid.location.RCTfacingDirectionListener;
import com.racartech.library.rctandroid.location.RCTlocation;
import com.racartech.library.rctandroid.location.RCTlocationUpdateListener;
import com.racartech.library.rctandroid.logging.RCTloggingLocationData;

public class MapsTestActivity extends AppCompatActivity implements
        RCTgoogleMapsDropPin.OnPinDropListener,
        RCTlocationUpdateListener.LocationUpdateListener,
        RCTfacingDirectionListener.FacingDirectionListener
{

    private FrameLayout mapContainer;
    private RCTgoogleMapsDropPin customMapView;
    private Button add_map_view_button, view_log_button, more_button;

    TextView long_value, lat_value, address_value;

    ImageView facing_direction_compass;

    RCTfacingDirectionListener facing_direction_listener;

    RCTlocationUpdateListener locationUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maps_test_activity_layout);



        mapContainer = findViewById(R.id.map_container);
        long_value = findViewById(R.id.mtcl_long_value);
        lat_value = findViewById(R.id.mtcl_lat_value);
        address_value = findViewById(R.id.mtcl_address_value);

        facing_direction_compass = findViewById(R.id.mtcl_facing_direction_compass_imageview);
        add_map_view_button = findViewById(R.id.maps_test_add_map_view_button);
        more_button = findViewById(R.id.maps_test_more_options_button);

        setLoggingFilePath();
        RCTloggingLocationData.IS_LOGGING_ENABLED.set(false);

        facing_direction_listener = new RCTfacingDirectionListener(this, this);


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




    }

    private void addMapView() {
        customMapView = new RCTgoogleMapsDropPin(MapsTestActivity.this, MapsTestActivity.this);
        customMapView.setOnPinDropListener(this); // Set listener
        mapContainer.addView(customMapView);
        customMapView.getAutoLocationUpdates(1000);
        customMapView.setCameraFollowLocationUpdate(true);
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

        if(customMapView.CAMERA_FOLLOW_ON_LOCATION_UPDATE){
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
                if(customMapView.CAMERA_FOLLOW_ON_LOCATION_UPDATE){
                    camera_follow_on_location_update_toggle_button.
                            setBackgroundColor(MapsTestActivity.this.getColor(R.color.dark_gray_text_color));
                    camera_follow_on_location_update_toggle_button.setText("DISABLED");
                    customMapView.setCameraFollowLocationUpdate(false);
                }else{
                    camera_follow_on_location_update_toggle_button.
                            setBackgroundColor(MapsTestActivity.this.getColor(R.color.blue));
                    camera_follow_on_location_update_toggle_button.setText("ENABLED");
                    customMapView.setCameraFollowLocationUpdate(true);
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
    public void onPinDrop(double latitude, double longitude) {
        lat_value.setText(String.valueOf(latitude));
        long_value.setText(String.valueOf(longitude));
        Address marker_address = RCTlocation.getAddress(MapsTestActivity.this,latitude,longitude);
        address_value.setText(marker_address.getAddressLine(0));
    }

    @Override
    public void onLocationUpdate(double latitude, double longitude) {
        customMapView.updateCurrentLocation(latitude, longitude);
    }

    @Override
    public void onFacingDirectionUpdate(float azimuth_in_degrees){
        if(customMapView != null){
            if(customMapView.googleMap != null){
                facing_direction_compass.setRotation((azimuth_in_degrees - customMapView.googleMap.getCameraPosition().bearing));
            }
        }

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        facing_direction_listener.setEnabled(false);

        finish();
    }
}
