

package com.racartech.library.rctandroid.google.maps;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Location;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.common.util.concurrent.AtomicDouble;
import com.racartech.library.rctandroid.R;
import com.racartech.library.rctandroid.location.RCTLocationData;
import com.racartech.library.rctandroid.location.RCTlocation;
import com.racartech.library.rctandroid.logging.RCTloggingLocationData;

import java.util.concurrent.atomic.AtomicReference;

public class RCTgoogleMapsDropPin extends FrameLayout implements OnMapReadyCallback{





    public GoogleMap googleMap;
    public Marker currentMarker;
    public MapView mapView;

    private double base_visible_area = -1.0;

    private Activity activity;




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
                reCalculateCurrentLocationCircleSize();
            }
        });


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


    public void reCalculateCurrentLocationCircleSize() {
        if (CURRENT_LOCATION_CIRCLE != null) {

            double current_visible_area = calculateVisibleArea();

            if (base_visible_area < 0.0) {
                base_visible_area = current_visible_area;
                //current_location_circle.setRadius();
            } else {

                double multiplier = current_visible_area / base_visible_area;
                CURRENT_LOCATION_CIRCLE.setRadius(multiplier);
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
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.racartech.library.rctandroid.R;
import com.racartech.library.rctandroid.file.RCTfile;
import com.racartech.library.rctandroid.location.RCTLocationData;
import com.racartech.library.rctandroid.location.RCTlocation;
import com.racartech.library.rctandroid.logging.RCTloggingLocationData;
import com.racartech.library.rctandroid.media.RCTbitmap;
import com.racartech.library.rctandroid.media.RCTdrawable;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class RCTgoogleMapsDropPin extends FrameLayout implements OnMapReadyCallback, SensorEventListener {


    String LOCATION_LOG_FILE_PATH = null;

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

    AtomicReference<RCTLocationData> CURRENT_LOCATION_DATA = new AtomicReference<>(null);

    public AtomicBoolean CAMERA_FOLLOW_CURRENT_LOCATION = new AtomicBoolean(true);


    //fahclmbd = facing_arrow_head_current_location_marker_base_distance

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
        haveAccelerometer = sensorManager.registerListener((SensorEventListener) this, accelerometer, SensorManager.SENSOR_DELAY_GAME);
        haveMagnetometer = sensorManager.registerListener((SensorEventListener) this, magnetometer, SensorManager.SENSOR_DELAY_GAME);
        haveSensor = haveAccelerometer && haveMagnetometer;

        ///////////////////////////////////////////////////////////////////////////////////////////////////////
        //Log
        this.LOCATION_LOG_FILE_PATH = RCTfile.getDir_IntAppFiles(getContext()).concat("/rctandroid_location_log.txt");
        RCTloggingLocationData.setFilePath(this.LOCATION_LOG_FILE_PATH);
        ///////////////////////////////////////////////////////////////////////////////////////////////////////


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

        refreshCurrentLocationCircleLocation(true,-1000, -1000);

        getLocationUpdates(1000);


        googleMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
            @Override
            public boolean onMyLocationButtonClick() {
                if(CURRENT_LOCATION_DATA != null && current_location_circle != null && facing_direction_marker != null) {
                    refreshCurrentLocationCircleLocation(true,
                            CURRENT_LOCATION_DATA.get().getAddress().getLatitude(),
                            CURRENT_LOCATION_DATA.get().getAddress().getLongitude());
                    updateFacingDirectionArrowHeadLocation(
                            CURRENT_LOCATION_DATA.get().getAddress().getLatitude(),
                            CURRENT_LOCATION_DATA.get().getAddress().getLongitude());
                }
                return false;
            }
        });


        googleMap.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
            @Override
            public void onCameraMove() {
                reCalculateCurrentLocationCircleSize();
                updateFacingDirectionArrowHeadLocation();
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
        updateFacingArrowDirectionOrientation();
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

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


    private void refreshCurrentLocationCircleLocation(boolean move_camera, double current_latitude, double current_longitude) {


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

                        if (current_location_circle == null) {
                            current_location_circle = googleMap.addCircle(new CircleOptions()
                                    .center(new LatLng(
                                            finalLatitude,
                                            finalLongitude
                                    ))
                                    .radius(1)
                                    .strokeColor(Color.WHITE)
                                    .fillColor(Color.BLUE));
                        }else{
                            current_location_circle.setCenter(new LatLng(finalLatitude,finalLongitude));
                        }

                        if (CURRENT_LOCATION_DATA != null) {
                            if (facing_direction_marker == null) {
                                Bitmap fdmi_bitmap = RCTdrawable.convertToBitmap(AppCompatResources.getDrawable(getContext(), R.drawable.facing_direction_arrow_head));
                                fdmi_bitmap = RCTbitmap.resize(fdmi_bitmap, 256, 256);
                                LatLng new_facing_direction_marker_lat_lng = new LatLng(
                                        finalLatitude,
                                        finalLongitude);
                                facing_direction_marker = googleMap.addMarker(new MarkerOptions().
                                        position(new_facing_direction_marker_lat_lng).
                                        icon(BitmapDescriptorFactory.fromBitmap(fdmi_bitmap)));
                            } else {
                                facing_direction_marker.setPosition(new LatLng(
                                        finalLatitude,
                                        finalLongitude));
                            }
                        }


                        if(move_camera){
                            float camera_zoom = googleMap.getCameraPosition().zoom;
                            if(camera_zoom < 3.0){
                                camera_zoom = 21.0F;
                            }
                            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(specificLocation, camera_zoom)); // Adjust zoom level as needed
                        }

                    }
                });


            }
        }).start();
    }


    public void reCalculateCurrentLocationCircleSize() {
        if (current_location_circle != null) {

            double current_visible_area = calculateVisibleArea();

            if (base_visible_area < 0.0) {
                base_visible_area = current_visible_area;
                //current_location_circle.setRadius();
            } else {

                double multiplier = current_visible_area / base_visible_area;
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

    private void updateFacingDirectionArrowHeadLocation() {
        if (facing_direction_marker != null && CURRENT_LOCATION_DATA != null) {
            facing_direction_marker.setPosition(new LatLng(
                    (CURRENT_LOCATION_DATA.get().getAddress().getLatitude()),
                    CURRENT_LOCATION_DATA.get().getAddress().getLongitude()));
        }
    }

    private void updateFacingDirectionArrowHeadLocation(double latitude, double longitude) {
        if (facing_direction_marker != null) {
            facing_direction_marker.setPosition(new LatLng(latitude, longitude));
        }
    }

    private void updateFacingArrowDirectionOrientation() {
        SensorManager.getRotationMatrix(rotationMatrix, null,
                accelerometerReading, magnetometerReading);
        SensorManager.getOrientation(rotationMatrix, orientationAngles);

        float azimuthInDegrees = (float) Math.toDegrees(orientationAngles[0]);


        if (current_location_circle != null &&
                CURRENT_LOCATION_DATA != null &&
                facing_direction_marker != null) {
            facing_direction_marker.setRotation(azimuthInDegrees);
        }

        // "orientationAngles" now has up-to-date information.
    }

    public void updateCurrentLocationData() {
        if(this.CURRENT_LOCATION_DATA != null) {
            Address updated_location_address = new RCTLocationData(
                    getContext(),
                    RCTLocationData.MODE_CURRENT,
                    200).getAddress();
            this.CURRENT_LOCATION_DATA.get().setAddress(RCTlocation.getAddress(
                    getContext(),
                    updated_location_address.getLatitude(),
                    updated_location_address.getLongitude()));
        }
    }


    private void getLocationUpdates(long update_per_millis) {
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
                    if(RCTgoogleMapsDropPin.this.CURRENT_LOCATION_DATA.get() == null) {
                        RCTgoogleMapsDropPin.this.CURRENT_LOCATION_DATA.set(new RCTLocationData(RCTlocation.getAddress(getContext(), latitude, longitude)));
                    }else{
                        RCTgoogleMapsDropPin.this.CURRENT_LOCATION_DATA.get().setAddress(RCTlocation.getAddress(getContext(), latitude, longitude));
                    }
                    updateFacingDirectionArrowHeadLocation(latitude,longitude);
                    refreshCurrentLocationCircleLocation(CAMERA_FOLLOW_CURRENT_LOCATION.get(), latitude,longitude);
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

}


 */



