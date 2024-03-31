

package com.racartech.library.rctandroid.google.maps;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.location.Address;
import android.location.Location;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.common.util.concurrent.AtomicDouble;
import com.racartech.library.rctandroid.R;
import com.racartech.library.rctandroid.file.RCTfile;
import com.racartech.library.rctandroid.location.RCTLocationData;
import com.racartech.library.rctandroid.location.RCTfacingDirectionListener;
import com.racartech.library.rctandroid.location.RCTlocation;
import com.racartech.library.rctandroid.logging.RCTloggingLocationData;
import com.racartech.library.rctandroid.media.RCTbitmap;
import com.racartech.library.rctandroid.media.RCTdrawable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public class RCTgoogleMapsDropPin extends FrameLayout implements OnMapReadyCallback, RCTfacingDirectionListener.FacingDirectionListener {


    private String SETTINGS_FILE_PATH = null;



    public GoogleMap googleMap;
    public Marker currentMarker;
    public MapView mapView;

    private double base_visible_area = -1.0;

    private Activity activity;

    private RCTfacingDirectionListener FACING_DIRECTION_LISTENER = null;

    private Marker FACING_DIRECTION_ARROW;

    private double CAMERA_ZOOM_LEVEl = -1000.0F;

    private AtomicLong CIRCLE_SIZE_LAST_UPDATE = new AtomicLong(0);




    private volatile Circle CURRENT_LOCATION_CIRCLE = null;

    public volatile AtomicReference<RCTLocationData> CURRENT_LOCATION_DATA = new AtomicReference<>(null);

    public volatile boolean CAMERA_FOLLOW_ON_LOCATION_UPDATE = true;

    private volatile AtomicDouble current_location_latitude = new AtomicDouble(-1000);
    private volatile AtomicDouble current_location_longitude = new AtomicDouble(-1000);

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

        FACING_DIRECTION_LISTENER = new RCTfacingDirectionListener(getContext(),this);
        FACING_DIRECTION_LISTENER.setEnabled(true);

        CIRCLE_SIZE_LAST_UPDATE.set(System.currentTimeMillis());
        setSettingsFileData();



    }

    public void setSettingsFileData(){
        SETTINGS_FILE_PATH = RCTfile.getDir_IntAppFiles(getContext()).concat("/rctandroid_gmdp_settings.txt");
        if(!RCTfile.doesFileExist(SETTINGS_FILE_PATH)) {
            RCTfile.createFile(SETTINGS_FILE_PATH);
            ArrayList<String> default_file_contents = new ArrayList<>();
            default_file_contents.add("CAMERA_FOLLOW_ON_CURRENT_LOCATION_UPDATE=true"); //Index 0
            default_file_contents.add("LAST_KNOWN_LATITUDE=-1000.0"); //Index 1
            default_file_contents.add("LAST_KNOWN_LONGITUDE=-1000.0"); //Index 2
            default_file_contents.add("CAMERA_ZOOM_LEVEL=-1000.0"); //Index 3
            saveSettingsDataFileContents(default_file_contents);
        }else{
            try {
                ArrayList<String> settings_file_contents = getSettingsDataFileContents();
                CAMERA_FOLLOW_ON_LOCATION_UPDATE = getFileSettingsCameraFollowOnCurrentLocationUpdate(settings_file_contents);
                current_location_latitude.set(getFileSettingsLastKnownLatitude(settings_file_contents));
                current_location_longitude.set(getFileSettingsLastKnownLongitude(settings_file_contents));
                CAMERA_ZOOM_LEVEl = getFileSettingsZoomLevel(settings_file_contents);
            }catch (Exception ex){
                RCTfile.delete_File(SETTINGS_FILE_PATH);
                setSettingsFileData();
            }
        }
    }


    public boolean getFileSettingsCameraFollowOnCurrentLocationUpdate(ArrayList<String> file_contents){
        try {
            if (RCTfile.doesFileExist(SETTINGS_FILE_PATH)) {
                String[] line_data = file_contents.get(0).split("=");
                boolean data_value = Boolean.parseBoolean(line_data[1]);
                return data_value;
            } else {
                return true;
            }
        }catch (Exception ex){
            return true;
        }
    }

    public double getFileSettingsLastKnownLatitude(ArrayList<String> file_contents){
        try {
            if (RCTfile.doesFileExist(SETTINGS_FILE_PATH)) {
                String[] line_data = file_contents.get(1).split("=");
                double data_value = Double.parseDouble(line_data[1]);
                return data_value;
            } else {
                return -1000.0;
            }
        }catch (Exception ex){
            return -1000.0;
        }
    }

    public double getFileSettingsLastKnownLongitude(ArrayList<String> file_contents){
        try{
            if(RCTfile.doesFileExist(SETTINGS_FILE_PATH)) {
                String[] line_data = file_contents.get(2).split("=");
                double data_value = Double.parseDouble(line_data[1]);
                return data_value;
            }else{
                return -1000.0;
            }
        }catch (Exception ex){
            return -1000.0;
        }

    }

    public float getFileSettingsZoomLevel(ArrayList<String> file_contents){
        try{
            if(RCTfile.doesFileExist(SETTINGS_FILE_PATH)) {
                String[] line_data = file_contents.get(3).split("=");
                double data_value = Float.parseFloat(line_data[1]);
                return (float) data_value;
            }else{
                return (float) -1000.0;
            }
        }catch (Exception ex){
            return (float) -1000.0;
        }
    }

    public ArrayList<String> getSettingsDataFileContents(){
        try {
            return RCTfile.readFile_ArrayList(SETTINGS_FILE_PATH);
        }catch (IOException ignored){
            return null;
        }
    }

    public void saveSettingsDataFileContents(ArrayList<String> file_contents){
        try {
            RCTfile.overrideFile(SETTINGS_FILE_PATH,file_contents);
        }catch (IOException ignored){}
    }

    public void saveCurrentSettings(){
        ArrayList<String> the_file_contents = new ArrayList<>();
        the_file_contents.add("CAMERA_FOLLOW_ON_CURRENT_LOCATION_UPDATE=".concat(String.valueOf(CAMERA_FOLLOW_ON_LOCATION_UPDATE))); //Index 0
        the_file_contents.add("LAST_KNOWN_LATITUDE=".concat(String.valueOf(current_location_latitude))); //Index 1
        the_file_contents.add("LAST_KNOWN_LONGITUDE=".concat(String.valueOf(current_location_longitude))); //Index 2
        the_file_contents.add("CAMERA_ZOOM_LEVEL=".concat(String.valueOf(CAMERA_ZOOM_LEVEl))); //Index 3
        saveSettingsDataFileContents(the_file_contents);
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

        googleMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
            @Override
            public boolean onMyLocationButtonClick() {
                if(
                        CURRENT_LOCATION_DATA.get() != null &&
                        CURRENT_LOCATION_CIRCLE != null &&
                        current_location_latitude.get() > -200 &&
                        current_location_longitude.get() > -200
                ) {
                    refreshCurrentLocationCircleLocation(true,true,
                            current_location_latitude.get(),
                            current_location_longitude.get());
                }
                return false;
            }
        });


        googleMap.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
            @Override
            public void onCameraMove() {
                CAMERA_ZOOM_LEVEl = googleMap.getCameraPosition().zoom;
                reCalculateCurrentLocationCircleSize();
            }
        });


        double last_known_latitude = -1000;
        double last_known_longitude = -1000;
        float last_known_zoom_level = -1000;
        ArrayList<String> settings_file_contents = getSettingsDataFileContents();
        last_known_latitude = getFileSettingsLastKnownLatitude(settings_file_contents);
        last_known_longitude = getFileSettingsLastKnownLongitude(settings_file_contents);
        last_known_zoom_level = getFileSettingsZoomLevel(settings_file_contents);

        if(last_known_latitude > -200.0 && last_known_longitude > -200.0 && last_known_zoom_level > -200){
            current_location_latitude.set(last_known_latitude);
            current_location_longitude.set(last_known_longitude);
            CAMERA_ZOOM_LEVEl = last_known_zoom_level;


            LatLng cache_latlng = new LatLng(last_known_latitude,last_known_longitude);
            prefetchMapTiles(cache_latlng,10000);

            updateCurrentLocation(
                    last_known_latitude,
                    last_known_longitude);

        }


    }


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


    private void refreshCurrentLocationCircleLocation(boolean move_camera, boolean max_zoom, double current_latitude, double current_longitude) {


        new Thread(new Runnable() {
            @Override
            public void run() {

                double latitude = -999.0;
                double longitude = -999.0;
                if (current_latitude < -200.0 && current_longitude < -200.0) {
                    updateCurrentLocationData();
                    latitude = CURRENT_LOCATION_DATA.get().getAddress().getLatitude();
                    longitude = CURRENT_LOCATION_DATA.get().getAddress().getLongitude();
                } else {
                    latitude = current_latitude;
                    longitude = current_longitude;
                }

                double finalLatitude = latitude;
                double finalLongitude = longitude;
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        LatLng specificLocation = new LatLng(
                                finalLatitude,
                                finalLongitude);

                        if (CURRENT_LOCATION_CIRCLE == null) {
                            CURRENT_LOCATION_CIRCLE = googleMap.addCircle(new CircleOptions()
                                    .center(new LatLng(
                                            finalLatitude,
                                            finalLongitude
                                    ))
                                    .radius(1)
                                    .strokeColor(Color.WHITE)
                                    .fillColor(Color.BLUE));
                        }else{
                            CURRENT_LOCATION_CIRCLE.setCenter(new LatLng(finalLatitude,finalLongitude));
                        }


                        if(FACING_DIRECTION_ARROW == null){
                            Bitmap fda_bitmap = RCTdrawable.convertToBitmap(
                                    AppCompatResources.getDrawable(getContext(),R.drawable.facing_direction_arrow_head));
                            fda_bitmap = RCTbitmap.resize(fda_bitmap,256,256);
                            BitmapDescriptor the_icon = BitmapDescriptorFactory.fromBitmap(fda_bitmap);

                            FACING_DIRECTION_ARROW = googleMap.addMarker(new MarkerOptions()
                                    .position(new LatLng(current_location_latitude.get(), current_location_longitude.get()))
                                    .icon(the_icon));
                        }else{
                            FACING_DIRECTION_ARROW.setPosition(new LatLng(
                                    current_location_latitude.get(),
                                    current_location_longitude.get()));
                        }




                        if(move_camera){
                            float camera_zoom = googleMap.getCameraPosition().zoom;

                            if(camera_zoom < 3.0){
                                camera_zoom = 21.0F;
                            }
                            if(!max_zoom) {
                                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(specificLocation, camera_zoom));
                            }else{
                                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(specificLocation, 21.0F));
                            }
                        }

                    }
                });


            }
        }).start();
    }


    public void reCalculateCurrentLocationCircleSize(){

        long current_time_millis = System.currentTimeMillis();
        if((current_time_millis - CIRCLE_SIZE_LAST_UPDATE.get()) > 200) {
            if (CURRENT_LOCATION_CIRCLE != null) {

                double current_visible_area = calculateVisibleArea();

                if (base_visible_area < 0.0) {
                    base_visible_area = current_visible_area;
                } else {
                    double multiplier = current_visible_area / base_visible_area;
                    CURRENT_LOCATION_CIRCLE.setRadius(multiplier);
                }
                CIRCLE_SIZE_LAST_UPDATE.set(current_time_millis);
            }
        }


    }





    private double calculateVisibleArea() {
        if (googleMap != null) {
            LatLngBounds visibleBounds = googleMap.getProjection().getVisibleRegion().latLngBounds;
            double visibleArea = calculateArea(visibleBounds);
            // Now you have the visible area in square meters
            return visibleArea;
        } else {
            return -1.0;
        }
    }

    private double calculateArea(LatLngBounds bounds) {
        double radiusEarth = 6371009; // Earth's radius in meters
        double lat1 = Math.toRadians(bounds.southwest.latitude);
        double lon1 = Math.toRadians(bounds.southwest.longitude);
        double lat2 = Math.toRadians(bounds.northeast.latitude);
        double lon2 = Math.toRadians(bounds.northeast.longitude);

        // Delta values
        double deltaLat = lat2 - lat1;
        double deltaLon = lon2 - lon1;

        // Calculate the area using Spherical Trigonometry
        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2) +
                Math.cos(lat1) * Math.cos(lat2) *
                        Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double area = radiusEarth * radiusEarth * c;
        return area;
    }


    public void updateCurrentLocationData() {
        if(this.CURRENT_LOCATION_DATA != null) {
            try {
                Address updated_location_address = new RCTLocationData(
                        getContext(),
                        RCTLocationData.MODE_CURRENT,
                        200).getAddress();
                this.CURRENT_LOCATION_DATA.get().setAddress(RCTlocation.getAddress(
                        getContext(),
                        updated_location_address.getLatitude(),
                        updated_location_address.getLongitude()));
            }catch(NullPointerException ignored){}
        }
    }


    public void getAutoLocationUpdates(long update_per_millis) {
        FusedLocationProviderClient fusedLocationClient = LocationServices.getFusedLocationProviderClient(getContext());
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(update_per_millis);
        locationRequest.setFastestInterval(update_per_millis);


        LocationCallback locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                // Handle location updates here
                Location location = locationResult.getLastLocation();
                if (location != null) {
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();

                    current_location_latitude.set(latitude);
                    current_location_longitude.set(longitude);
                    if(RCTgoogleMapsDropPin.this.CURRENT_LOCATION_DATA.get() == null) {
                        RCTgoogleMapsDropPin.this.CURRENT_LOCATION_DATA.set(new RCTLocationData(RCTlocation.getAddress(getContext(), latitude, longitude)));
                    }else{
                        RCTgoogleMapsDropPin.this.CURRENT_LOCATION_DATA.get().setAddress(RCTlocation.getAddress(getContext(), latitude, longitude));
                    }

                    refreshCurrentLocationCircleLocation(CAMERA_FOLLOW_ON_LOCATION_UPDATE,false, latitude,longitude);
                    reCalculateCurrentLocationCircleSize();

                    ///////////////////////////////////////////////////////////////////////////////////////////////////////
                    //Log
                    RCTloggingLocationData.add(latitude,longitude);
                    ///////////////////////////////////////////////////////////////////////////////////////////////////////

                }
            }
        };

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);
    }



    public void updateCurrentLocation(double latitude, double longitude) {

        current_location_latitude.set(latitude);
        current_location_longitude.set(longitude);

        if(RCTgoogleMapsDropPin.this.CURRENT_LOCATION_DATA.get() == null) {
            RCTgoogleMapsDropPin.this.CURRENT_LOCATION_DATA.set(new RCTLocationData(RCTlocation.getAddress(getContext(), latitude, longitude)));
        }else{
            RCTgoogleMapsDropPin.this.CURRENT_LOCATION_DATA.get().setAddress(RCTlocation.getAddress(getContext(), latitude, longitude));
        }

        refreshCurrentLocationCircleLocation(CAMERA_FOLLOW_ON_LOCATION_UPDATE,false, latitude,longitude);
        reCalculateCurrentLocationCircleSize();


        if(RCTloggingLocationData.IS_LOGGING_ENABLED.get() && RCTloggingLocationData.LOG_FILE_PATH != null) {
            RCTloggingLocationData.add(latitude, longitude);
        }


    }



    public void setCameraFollowLocationUpdate(boolean follow_location_update){
        this.CAMERA_FOLLOW_ON_LOCATION_UPDATE = follow_location_update;
    }

    public boolean getCameraFollowLocationUpdate(){
        return this.CAMERA_FOLLOW_ON_LOCATION_UPDATE;
    }



    @Override
    public void onFacingDirectionUpdate(float azimuth_in_degrees) {
        if(googleMap != null && FACING_DIRECTION_ARROW != null){
            FACING_DIRECTION_ARROW.setRotation((azimuth_in_degrees -googleMap.getCameraPosition().bearing));
        }
    }



    // Example method to prefetch map tiles for a given radius around a location
    private void prefetchMapTiles(LatLng center, double radiusInMeters) {
        // Calculate bounds for prefetching
        LatLngBounds bounds = calculateBounds(center, radiusInMeters);

        // Capture map snapshot for the calculated bounds
        googleMap.snapshot(new GoogleMap.SnapshotReadyCallback() {
            @Override
            public void onSnapshotReady(Bitmap bitmap) {
                // Store the snapshot bitmap locally for offline use
                // You may save it to a file or cache it in memory
                // Handle storing and managing the snapshot bitmap here
            }
        });
    }

    // Helper method to calculate bounds based on center and radius
    private LatLngBounds calculateBounds(LatLng center, double radiusInMeters) {
        // Convert radius from meters to degrees
        double radiusInDegrees = radiusInMeters / 111000.0;

        // Calculate bounds
        double north = center.latitude + radiusInDegrees;
        double south = center.latitude - radiusInDegrees;
        double east = center.longitude + radiusInDegrees;
        double west = center.longitude - radiusInDegrees;

        // Return LatLngBounds
        return new LatLngBounds(new LatLng(south, west), new LatLng(north, east));
    }


}





