package com.racartech.library.rctandroid.google.maps;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.racartech.library.rctandroid.R;
import com.racartech.library.rctandroid.location.RCTLocationData;
import com.racartech.library.rctandroid.media.RCTbitmap;
import com.racartech.library.rctandroid.media.RCTdrawable;

public class RCTgoogleMapsDropPin extends FrameLayout implements OnMapReadyCallback, SensorEventListener{


    /////

    private SensorManager sensorManager;
    private final float[] accelerometerReading = new float[3];
    private final float[] magnetometerReading = new float[3];

    private final float[] rotationMatrix = new float[9];
    private final float[] orientationAngles = new float[3];



    public GoogleMap googleMap;
    public Marker currentMarker;
    public MapView mapView;

    public double base_visible_area = -1.0;

    private Activity activity;
    private Sensor accelerometer;
    private Sensor magnetometer;

    private boolean haveSensor = false;
    private boolean haveAccelerometer = false;
    private boolean haveMagnetometer = false;


    public Circle current_location_circle = null;
    public Marker facing_direction_marker = null;

    RCTLocationData location_data = null;


    //fahclmbd = facing_arrow_head_current_location_marker_base_distance
    private double fahclmbd = 0.00001;

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

        // Initialize sensor manager
        sensorManager = (SensorManager) getContext().getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        // Check if the device has the required sensors
        haveAccelerometer = sensorManager.registerListener((SensorEventListener) this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        haveMagnetometer = sensorManager.registerListener((SensorEventListener) this, magnetometer, SensorManager.SENSOR_DELAY_NORMAL);
        haveSensor = haveAccelerometer && haveMagnetometer;


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


        googleMap.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
            @Override
            public void onCameraMove() {
                float zoomLevel = googleMap.getCameraPosition().zoom;
                double elevation = googleMap.getCameraPosition().tilt;
                reCalculateCurrentLocationCircleSize(googleMap.getCameraPosition());
                resetAndReCalculateFacingDirectionArrowHead();
            }
        });


    }






    // Get readings from accelerometer and magnetometer. To simplify calculations,
    // consider storing these readings as unit vectors.
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            System.arraycopy(event.values, 0, accelerometerReading,
                    0, accelerometerReading.length);
        } else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            System.arraycopy(event.values, 0, magnetometerReading,
                    0, magnetometerReading.length);
        }
        updateOrientationAngles();
    }







    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }


    public void updateOrientationAngles() {
        SensorManager.getRotationMatrix(rotationMatrix, null,
                accelerometerReading, magnetometerReading);
        SensorManager.getOrientation(rotationMatrix, orientationAngles);

        float azimuthInDegrees = (float) Math.toDegrees(orientationAngles[0]);


        if(current_location_circle != null &&
           location_data != null &&
           facing_direction_marker != null)
        {
            facing_direction_marker.setRotation(azimuthInDegrees);
        }

        // "orientationAngles" now has up-to-date information.
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



    private void refreshCurrentLocationMarker(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                location_data = new RCTLocationData(getContext(), RCTLocationData.MODE_CURRENT, 200);
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        LatLng specificLocation = new LatLng(
                                location_data.getAddress().getLatitude(),
                                location_data.getAddress().getLongitude());
                        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(specificLocation, 21)); // Adjust zoom level as needed

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


                        if(location_data != null){
                            if (facing_direction_marker == null) {
                                LatLng new_facing_direction_marker_lat_lng = new LatLng(
                                        location_data.getAddress().getLatitude()+fahclmbd,
                                        location_data.getAddress().getLongitude());
                                facing_direction_marker = googleMap.addMarker(new MarkerOptions().position(new_facing_direction_marker_lat_lng));
                                Bitmap fdmi_bitmap = RCTdrawable.convertToBitmap(AppCompatResources.getDrawable(getContext(),R.drawable.facing_direction_arrow_head));
                                fdmi_bitmap = RCTbitmap.resize(fdmi_bitmap,100,100);
                                facing_direction_marker.setIcon(BitmapDescriptorFactory.fromBitmap(fdmi_bitmap));
                                System.out.println("-------------------------------------------------------------");
                                System.out.println("Facing direction marker set");
                                System.out.println("-------------------------------------------------------------");
                            }else{
                                facing_direction_marker.setPosition(new LatLng(
                                        location_data.getAddress().getLatitude()+fahclmbd,
                                        location_data.getAddress().getLongitude()));
                            }
                        }

                    }
                });



            }
        }).start();
    }


    public void reCalculateCurrentLocationCircleSize(CameraPosition camera_position){

        //System.out.println("----------------------------------------------------------------------");
        //System.out.println("Zoom            : ".concat(String.valueOf(camera_position.zoom)));
        //System.out.println("Visible Area    : ".concat(String.valueOf(calculateVisibleArea())));
        if(current_location_circle != null) {

            double current_visible_area = calculateVisibleArea();

            if(base_visible_area < 0.0){
                base_visible_area = current_visible_area;
                //current_location_circle.setRadius();
            }else{

                double multiplier = current_visible_area/base_visible_area;
                current_location_circle.setRadius(multiplier);
            }




            //System.out.println("Circle Size : ".concat(String.valueOf(current_location_circle.getRadius())));
        }


    }



    private double calculateVisibleArea() {
        if (googleMap != null) {
            LatLngBounds visibleBounds = googleMap.getProjection().getVisibleRegion().latLngBounds;
            double visibleArea = calculateArea(visibleBounds);
            // Now you have the visible area in square meters
            return visibleArea;
        }else{
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

    private void resetAndReCalculateFacingDirectionArrowHead(){
        System.out.println("------------------------------------------------");
        System.out.println("resetAndReCalculateFacingDirectionArrowHead");
        System.out.println("------------------------------------------------");
        if(facing_direction_marker != null && location_data != null) {
            facing_direction_marker.setPosition(new LatLng(
                    (location_data.getAddress().getLatitude() + fahclmbd),
                    location_data.getAddress().getLongitude()));

        }
    }





}


/*

package com.racartech.library.rctandroid.google.maps;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.racartech.library.rctandroid.R;
import com.racartech.library.rctandroid.location.RCTLocationData;
import com.racartech.library.rctandroid.media.RCTbitmap;
import com.racartech.library.rctandroid.media.RCTdrawable;

public class RCTgoogleMapsDropPin extends FrameLayout implements OnMapReadyCallback, SensorEventListener{

    public GoogleMap googleMap;
    public Marker currentMarker;
    public MapView mapView;

    public double base_visible_area = -1.0;

    private Activity activity;
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private Sensor magnetometer;
    private float[] accelerometerReading = new float[3];
    private float[] magnetometerReading = new float[3];
    private boolean haveSensor = false;
    private boolean haveAccelerometer = false;
    private boolean haveMagnetometer = false;
    private float[] rotationMatrix = new float[9];
    private float[] orientationAngles = new float[3];

    public Circle current_location_circle = null;
    public Marker facing_direction_marker = null;

    RCTLocationData location_data = null;


    //fahclmbd = facing_arrow_head_current_location_marker_base_distance
    private double fahclmbd = 0.00001;

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

        // Initialize sensor manager
        sensorManager = (SensorManager) getContext().getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        // Check if the device has the required sensors
        haveAccelerometer = sensorManager.registerListener((SensorEventListener) this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        haveMagnetometer = sensorManager.registerListener((SensorEventListener) this, magnetometer, SensorManager.SENSOR_DELAY_NORMAL);
        haveSensor = haveAccelerometer && haveMagnetometer;


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


        googleMap.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
            @Override
            public void onCameraMove() {
                float zoomLevel = googleMap.getCameraPosition().zoom;
                double elevation = googleMap.getCameraPosition().tilt;
                reCalculateCurrentLocationCircleSize(googleMap.getCameraPosition());
                resetAndReCalculateFacingDirectionArrowHead();
            }
        });
        

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        SensorManager.getRotationMatrix(rotationMatrix, null, accelerometerReading, magnetometerReading);
        float[] oritent = SensorManager.getOrientation(rotationMatrix, orientationAngles);
        // orientationAngles[0] contains the azimuth (orientation angle) in radians
        // Convert it to degrees
        float azimuthInDegrees = (float) Math.toDegrees(orientationAngles[0]);
        if(facing_direction_marker != null){
            facing_direction_marker.setRotation(azimuthInDegrees);

        }
        updateOrientationAngles();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }


    private void updateOrientationAngles() {
        if (haveSensor) {
            SensorManager.getRotationMatrix(rotationMatrix, null, accelerometerReading, magnetometerReading);
            SensorManager.getOrientation(rotationMatrix, orientationAngles);
            // orientationAngles[0] contains the azimuth (orientation angle) in radians
            // Convert it to degrees
            float azimuthInDegrees = (float) Math.toDegrees(orientationAngles[0]);
            // Use azimuthInDegrees as the user's current orientation
            // You can do something with this value here

            if(current_location_circle != null && location_data != null){
                if (facing_direction_marker == null) {
                    LatLng new_facing_direction_marker_lat_lng = new LatLng(
                            location_data.getAddress().getLatitude()+fahclmbd,
                            location_data.getAddress().getLongitude());
                    facing_direction_marker = googleMap.addMarker(new MarkerOptions().position(new_facing_direction_marker_lat_lng));
                    Bitmap fdmi_bitmap = RCTdrawable.convertToBitmap(AppCompatResources.getDrawable(getContext(),R.drawable.facing_direction_arrow_head));
                    fdmi_bitmap = RCTbitmap.resize(fdmi_bitmap,100,100);
                    facing_direction_marker.setIcon(BitmapDescriptorFactory.fromBitmap(fdmi_bitmap));

                }else{
                    facing_direction_marker.setPosition(new LatLng(
                            location_data.getAddress().getLatitude()+fahclmbd,
                            location_data.getAddress().getLongitude()));
                }



            }



        }
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
                location_data = new RCTLocationData(getContext(), RCTLocationData.MODE_CURRENT, 200);

                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        LatLng specificLocation = new LatLng(
                                location_data.getAddress().getLatitude(),
                                location_data.getAddress().getLongitude());
                        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(specificLocation, 21)); // Adjust zoom level as needed

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


    public void reCalculateCurrentLocationCircleSize(CameraPosition camera_position){

        //System.out.println("----------------------------------------------------------------------");
        //System.out.println("Zoom            : ".concat(String.valueOf(camera_position.zoom)));
        //System.out.println("Visible Area    : ".concat(String.valueOf(calculateVisibleArea())));
        if(current_location_circle != null) {

            double current_visible_area = calculateVisibleArea();

            if(base_visible_area < 0.0){
                base_visible_area = current_visible_area;
                //current_location_circle.setRadius();
            }else{

                double multiplier = current_visible_area/base_visible_area;
                current_location_circle.setRadius(multiplier);
            }




            //System.out.println("Circle Size : ".concat(String.valueOf(current_location_circle.getRadius())));
        }


    }



    private double calculateVisibleArea() {
        if (googleMap != null) {
            LatLngBounds visibleBounds = googleMap.getProjection().getVisibleRegion().latLngBounds;
            double visibleArea = calculateArea(visibleBounds);
            // Now you have the visible area in square meters
            return visibleArea;
        }else{
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

    private void resetAndReCalculateFacingDirectionArrowHead(){
        System.out.println("------------------------------------------------");
        System.out.println("resetAndReCalculateFacingDirectionArrowHead");
        System.out.println("------------------------------------------------");
        if(facing_direction_marker != null && location_data != null) {
            facing_direction_marker.setPosition(new LatLng(
                    (location_data.getAddress().getLatitude() + fahclmbd),
                    location_data.getAddress().getLongitude()));

        }
    }





}

 */
