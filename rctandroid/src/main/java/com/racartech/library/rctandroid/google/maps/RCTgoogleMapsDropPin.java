package com.racartech.library.rctandroid.google.maps;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.racartech.library.rctandroid.R;
import com.racartech.library.rctandroid.location.RCTLocationData;

public class RCTgoogleMapsDropPin extends FrameLayout implements OnMapReadyCallback {

    private GoogleMap googleMap;
    private Marker currentMarker;
    private MapView mapView;


    private Activity activity;

    private Circle current_location_circle = null;

    public RCTgoogleMapsDropPin(@NonNull Context context, Activity the_activity) {
        super(context);
        init();
        activity = the_activity;
    }

    public RCTgoogleMapsDropPin(@NonNull Context context, @Nullable AttributeSet attrs, Activity the_activity) {
        super(context, attrs);
        init();
    }

    public RCTgoogleMapsDropPin(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, Activity the_activity) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.library_map_drop_pin, this);
        mapView = findViewById(R.id.lmdp_map_view);
        mapView.onCreate(null);
        mapView.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                if (currentMarker != null) {
                    currentMarker.remove();
                }
                currentMarker = googleMap.addMarker(new MarkerOptions().position(latLng));
                // Retrieve the coordinates
                double latitude = latLng.latitude;
                double longitude = latLng.longitude;
                // Pass the coordinates to the activity
                onPinDrop(latitude, longitude);


            }
        });

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Request location permission if not granted
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }

        googleMap.setMyLocationEnabled(true);
        refreshCurrentLocationMarker();



        googleMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
            @Override
            public boolean onMyLocationButtonClick() {
                refreshCurrentLocationMarker();
                return false;
            }
        });
        

    }

    // Define an interface to communicate with the activity
    public interface OnPinDropListener {
        void onPinDrop(double latitude, double longitude);
    }

    private OnPinDropListener mListener;

    // Method to set the listener
    public void setOnPinDropListener(OnPinDropListener listener) {
        mListener = listener;
    }

    // Method to notify the activity when pin is dropped
    private void onPinDrop(double latitude, double longitude) {
        if (mListener != null) {
            mListener.onPinDrop(latitude, longitude);
        }
    }



    private void refreshCurrentLocationMarker(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                RCTLocationData location_data = new RCTLocationData(getContext(), RCTLocationData.MODE_CURRENT, 200);

                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        LatLng specificLocation = new LatLng(
                                location_data.getAddress().getLatitude(),
                                location_data.getAddress().getLongitude());
                        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(specificLocation, 30)); // Adjust zoom level as needed

                        if(current_location_circle != null){
                            current_location_circle.remove();
                        }
                        current_location_circle = googleMap.addCircle(new CircleOptions()
                                .center(new LatLng(
                                        location_data.getAddress().getLatitude(),
                                        location_data.getAddress().getLongitude()
                                ))
                                .radius(1)
                                .strokeColor(Color.WHITE)
                                .fillColor(Color.BLUE));

                    }
                });



            }
        }).start();
    }



}
