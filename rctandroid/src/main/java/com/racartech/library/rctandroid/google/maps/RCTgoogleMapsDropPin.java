package com.racartech.library.rctandroid.google.maps;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.racartech.library.rctandroid.R;

public class RCTgoogleMapsDropPin extends FrameLayout implements OnMapReadyCallback {

    private GoogleMap googleMap;
    private Marker currentMarker;
    private MapView mapView;

    public RCTgoogleMapsDropPin(@NonNull Context context) {
        super(context);
        init();
    }

    public RCTgoogleMapsDropPin(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RCTgoogleMapsDropPin(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
}


/*
import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.racartech.library.rctandroid.R;

public class RCTgoogleMapsDropPin extends FrameLayout implements OnMapReadyCallback {

    private GoogleMap googleMap;
    private Marker currentMarker;
    private MapView mapView;

    public RCTgoogleMapsDropPin(@NonNull Context context) {
        super(context);
        init();
    }

    public RCTgoogleMapsDropPin(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RCTgoogleMapsDropPin(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
                // You can do something with the coordinates here
            }
        });
    }
}

 */
