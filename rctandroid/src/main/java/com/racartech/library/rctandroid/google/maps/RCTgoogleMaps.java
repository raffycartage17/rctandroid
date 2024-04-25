

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
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.common.util.concurrent.AtomicDouble;
import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.PendingResult;
import com.google.maps.model.DirectionsLeg;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.DirectionsStep;
import com.google.maps.model.TrafficModel;
import com.google.maps.model.TravelMode;
import com.racartech.library.rctandroid.R;
import com.racartech.library.rctandroid.file.RCTfile;
import com.racartech.library.rctandroid.location.RCTLocationData;
import com.racartech.library.rctandroid.location.RCTfacingDirectionListener;
import com.racartech.library.rctandroid.location.RCTlocation;
import com.racartech.library.rctandroid.location.RCTlocationUpdateListener;
import com.racartech.library.rctandroid.logging.RCTloggingLocationData;
import com.racartech.library.rctandroid.media.RCTbitmap;
import com.racartech.library.rctandroid.media.RCTdrawable;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public class RCTgoogleMaps extends FrameLayout implements OnMapReadyCallback, RCTfacingDirectionListener.FacingDirectionListener {



    private OnCurrentDirectionsTotalDistanceCalculatedListener CurrentDirectionTotalDistanceCalculatedListener;
    private OnCurrentLocationUpdated CurrentLocationUpdatedListener;


    public static int DESTINATION_TYPE_DEFAULT_STOPOVER = 0;
    public static int DESTINATION_TYPE_ROUTE_POINT = 1;
    public static int DESTINATION_TYPE_GAS = 2;
    public static int DESTINATION_TYPE_FOOD = 3;
    public static int DESTINATION_TYPE_SLEEPOVER = 4;
    public static int DESTINATION_TYPE_VEHICLE_MAINTENANCE = 5;

    private String SETTINGS_FILE_PATH = null;

    public GoogleMap googleMap;
    public Marker DROPPED_PIN_MARKER;
    public MapView mapView;

    private double base_visible_area = -1.0;

    private Activity activity;

    private RCTfacingDirectionListener FACING_DIRECTION_LISTENER = null;

    private Marker FACING_DIRECTION_ARROW;

    private double CAMERA_ZOOM_LEVEl = -1000.0F;

    private AtomicLong CIRCLE_SIZE_LAST_UPDATE = new AtomicLong(0);
    public volatile ArrayList<Marker> DESTINATIONS_MARKER = new ArrayList<>();

    private boolean is_initialized = false;



    public volatile AtomicDouble CURRENT_DIRECTIONS_TOTAL_DISTANCE = new AtomicDouble(0.0);

    private volatile AtomicInteger CURRENT_DIRECTIONS_POINTS_COUNT = new AtomicInteger(0);
    private volatile AtomicInteger PROCESSED_DIRECTIONS_POINTS_COUNTER = new AtomicInteger(0);


    private volatile Circle CURRENT_LOCATION_CIRCLE = null;

    public volatile AtomicReference<RCTLocationData> CURRENT_LOCATION_DATA = new AtomicReference<>(null);

    public volatile boolean CAMERA_FOLLOW_ON_LOCATION_UPDATE = true;

    public volatile AtomicDouble CURRENT_LOCATION_LATITUDE = new AtomicDouble(-1000);
    public volatile AtomicDouble CURRENT_LOCATION_LONGITUDE = new AtomicDouble(-1000);

    public boolean FIRST_AUTO_LOCATION_UPDATE_FOLLOW_CAMERA = true;
    private boolean FIRST_AUTO_LOCATION_UPDATE = false;

    public float DEFAULT_MAX_ZOOM = 21.0F; //21 is the most zoom

    private RCTlocationUpdateListener location_update_listener;


    public RCTgoogleMaps(@NonNull Context context, Activity the_activity, long update_interval, long fastest_interval) {
        super(context);
        init();
        activity = the_activity;
    }

    public RCTgoogleMaps(@NonNull Context context, @Nullable AttributeSet attrs, Activity the_activity, long update_interval, long fastest_interval) {
        super(context, attrs);
        init();
        activity = the_activity;
    }

    public RCTgoogleMaps(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, Activity the_activity, long update_interval, long fastest_interval) {
        super(context, attrs, defStyleAttr);
        init();
        activity = the_activity;
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
                CURRENT_LOCATION_LATITUDE.set(getFileSettingsLastKnownLatitude(settings_file_contents));
                CURRENT_LOCATION_LONGITUDE.set(getFileSettingsLastKnownLongitude(settings_file_contents));
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
        the_file_contents.add("LAST_KNOWN_LATITUDE=".concat(String.valueOf(CURRENT_LOCATION_LATITUDE))); //Index 1
        the_file_contents.add("LAST_KNOWN_LONGITUDE=".concat(String.valueOf(CURRENT_LOCATION_LONGITUDE))); //Index 2
        the_file_contents.add("CAMERA_ZOOM_LEVEL=".concat(String.valueOf(CAMERA_ZOOM_LEVEl))); //Index 3
        saveSettingsDataFileContents(the_file_contents);
    }

    public void setFirstAutoLocationUpdateFollowCamera(boolean mode){
        this.FIRST_AUTO_LOCATION_UPDATE_FOLLOW_CAMERA = true;
    }
    public void setDefaultMaxZoom(float zoom_level){
        this.DEFAULT_MAX_ZOOM = zoom_level;
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                if (DROPPED_PIN_MARKER != null) {
                    DROPPED_PIN_MARKER.remove();
                }
                DROPPED_PIN_MARKER = googleMap.addMarker(new MarkerOptions().position(latLng));
                // Retrieve the coordinates
                double marker_latitude = latLng.latitude;
                double marker_longitude = latLng.longitude;

                com.google.maps.model.LatLng origin = new com.google.maps.model.LatLng(
                        CURRENT_LOCATION_LATITUDE.get(),
                        CURRENT_LOCATION_LONGITUDE.get());

                com.google.maps.model.LatLng destination = new com.google.maps.model.LatLng(
                        marker_latitude,
                        marker_longitude);

                // Pass the coordinates to the activity
                onPinDrop(DROPPED_PIN_MARKER,marker_latitude, marker_longitude);


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
                        CURRENT_LOCATION_LATITUDE.get() > -200 &&
                        CURRENT_LOCATION_LONGITUDE.get() > -200
                ) {
                    refreshCurrentLocationCircleLocation(true,true,
                            CURRENT_LOCATION_LATITUDE.get(),
                            CURRENT_LOCATION_LONGITUDE.get());
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

        location_update_listener = new RCTlocationUpdateListener(getContext(), new RCTlocationUpdateListener.LocationUpdateListener() {
            @Override
            public void onLocationUpdate(double latitude, double longitude) {
                updateCurrentLocation(latitude,longitude);
            }
        }, 500, 200);


        double last_known_latitude = -1000;
        double last_known_longitude = -1000;
        float last_known_zoom_level = -1000;
        ArrayList<String> settings_file_contents = getSettingsDataFileContents();
        last_known_latitude = getFileSettingsLastKnownLatitude(settings_file_contents);
        last_known_longitude = getFileSettingsLastKnownLongitude(settings_file_contents);
        last_known_zoom_level = getFileSettingsZoomLevel(settings_file_contents);

        if(last_known_latitude > -200.0 && last_known_longitude > -200.0 && last_known_zoom_level > -200){
            CURRENT_LOCATION_LATITUDE.set(last_known_latitude);
            CURRENT_LOCATION_LONGITUDE.set(last_known_longitude);
            CAMERA_ZOOM_LEVEl = last_known_zoom_level;
            updateCurrentLocation(
                    last_known_latitude,
                    last_known_longitude);
        }
    }


    public interface OnPinDropListener {
        void onPinDrop(Marker new_marker, double latitude, double longitude);
    }

    private OnPinDropListener mListener;

    public void setOnPinDropListener(OnPinDropListener listener) {
        mListener = listener;
    }

    private void onPinDrop(Marker new_marker, double latitude, double longitude) {
        if (mListener != null) {
            mListener.onPinDrop(new_marker, latitude, longitude);
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
                            fda_bitmap = RCTbitmap.resize(fda_bitmap,128,128);
                            BitmapDescriptor the_icon = BitmapDescriptorFactory.fromBitmap(fda_bitmap);

                            FACING_DIRECTION_ARROW = googleMap.addMarker(new MarkerOptions()
                                    .position(new LatLng(CURRENT_LOCATION_LATITUDE.get(), CURRENT_LOCATION_LONGITUDE.get()))
                                    .icon(the_icon));

                        }else{
                            FACING_DIRECTION_ARROW.setPosition(new LatLng(
                                    CURRENT_LOCATION_LATITUDE.get(),
                                    CURRENT_LOCATION_LONGITUDE.get()));
                        }




                        if(is_initialized){
                            float camera_zoom = googleMap.getCameraPosition().zoom;

                            if (camera_zoom < 3.0) {
                                camera_zoom = 21.0F;
                            }
                            if (move_camera) {
                                if (!max_zoom) {
                                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(specificLocation, DEFAULT_MAX_ZOOM));
                                } else {
                                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(specificLocation, 21.0F));
                                }
                            }else{
                                if(!FIRST_AUTO_LOCATION_UPDATE){
                                    if(FIRST_AUTO_LOCATION_UPDATE_FOLLOW_CAMERA){
                                        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(specificLocation, DEFAULT_MAX_ZOOM));
                                    }
                                    FIRST_AUTO_LOCATION_UPDATE = true;
                                }
                            }

                        }else{
                            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(specificLocation, 21.0F));
                            is_initialized = true;
                        }

                    }
                });


            }
        }).start();
    }


    public void reCalculateCurrentLocationCircleSize(){

        long current_time_millis = System.currentTimeMillis();
        if((current_time_millis - CIRCLE_SIZE_LAST_UPDATE.get()) > 100) {
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


    private void updateCurrentLocation(double latitude, double longitude) {




        CURRENT_LOCATION_LATITUDE.set(latitude);
        CURRENT_LOCATION_LONGITUDE.set(longitude);
        if(RCTgoogleMaps.this.CURRENT_LOCATION_DATA.get() == null) {
            RCTgoogleMaps.this.CURRENT_LOCATION_DATA.set(new RCTLocationData(RCTlocation.getAddress(getContext(), latitude, longitude)));
        }else{
            RCTgoogleMaps.this.CURRENT_LOCATION_DATA.get().setAddress(RCTlocation.getAddress(getContext(), latitude, longitude));
        }

        refreshCurrentLocationCircleLocation(CAMERA_FOLLOW_ON_LOCATION_UPDATE,false, latitude,longitude);
        reCalculateCurrentLocationCircleSize();

        if(RCTloggingLocationData.IS_LOGGING_ENABLED.get() && RCTloggingLocationData.LOG_FILE_PATH != null) {
            RCTloggingLocationData.add(latitude, longitude);
        }
        notifyCurrentLocationUpdated(latitude, longitude);

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

    public void getDirections(String api_key,
                              com.google.maps.model.LatLng origin,
                              ArrayList<com.google.maps.model.LatLng> destinations,
                              TravelMode travel_mode,
                              ArrayList<DirectionsApi.RouteRestriction> route_restriction,
                              Instant departure_time){

        ArrayList<Integer> destination_types = new ArrayList<>();
        for(int index = 0; index<destinations.size(); index++){
            destination_types.add(DESTINATION_TYPE_DEFAULT_STOPOVER);
        }

        CURRENT_DIRECTIONS_POINTS_COUNT.set(destinations.size());


        getDirections(
                api_key,
                origin,
                destinations,
                destination_types,
                travel_mode,
                route_restriction,
                departure_time);
    }

    private void getDirections(String api_key,
                               com.google.maps.model.LatLng origin,
                               ArrayList<com.google.maps.model.LatLng> destinations,
                               ArrayList<Integer> destinations_type,
                               TravelMode travel_mode,
                               ArrayList<DirectionsApi.RouteRestriction> route_restriction,
                               Instant departure_time){



        DESTINATIONS_MARKER.clear();
        ArrayList<com.google.maps.model.LatLng> origins = new ArrayList<>();


        origins.add(origin);
        for(int index = 0; index<(destinations.size()-1); index++){
            origins.add(destinations.get(index));
        }

        for(int index = 0; index<destinations.size(); index++){
            LatLng converted_latlng = new LatLng(destinations.get(index).lat,destinations.get(index).lng);
            Marker new_marker = googleMap.addMarker(new MarkerOptions().position(converted_latlng));
            DESTINATIONS_MARKER.add(new_marker);
        }

        for(int index = 0; index<origins.size(); index++){
            getDrivingDirections_System(api_key,origins.get(index),destinations.get(index),travel_mode,route_restriction,departure_time);
        }
    }

    private void getDrivingDirections_System(
            String api_key,
            com.google.maps.model.LatLng origin,
            com.google.maps.model.LatLng destination,
            TravelMode travel_mode,
            ArrayList<DirectionsApi.RouteRestriction> route_restriction,
            Instant departure_time) {

        GeoApiContext geoApiContext = new GeoApiContext.Builder()
                .apiKey(api_key)
                .build();
        DirectionsApiRequest request = DirectionsApi.newRequest(geoApiContext);
        request.origin(origin);
        request.destination(destination);
        request.mode(travel_mode);
        request.departureTime(departure_time);
        for(int index = 0; index<route_restriction.size(); index++){
            request.avoid(route_restriction.get(index));
        }
        request.trafficModel(TrafficModel.BEST_GUESS);
        request.setCallback(new PendingResult.Callback<DirectionsResult>() {
            @Override
            public void onResult(DirectionsResult result) {


                double total_distance = 0.0;
                List<LatLng> path = new ArrayList<>();
                DirectionsRoute route = result.routes[0];
                DirectionsLeg[] legs = route.legs;
                for (int i = 0; i < legs.length; i++) {
                    DirectionsLeg leg = legs[i];
                    DirectionsStep[] steps = leg.steps;
                    for (int j = 0; j < steps.length; j++) {
                        DirectionsStep step = steps[j];
                        List<com.google.maps.model.LatLng> decodePath = step.polyline.decodePath();
                        for (int k = 0; k < decodePath.size(); k++) {
                            com.google.maps.model.LatLng point = decodePath.get(k);
                            path.add(new LatLng(point.lat,point.lng));

                            if (k > 0) {
                                com.google.maps.model.LatLng prevPoint = decodePath.get(k - 1);
                                total_distance += distanceBetweenPoints(point, prevPoint);
                            }

                        }
                    }
                }

                PolylineOptions polylineOptions = new PolylineOptions()
                        .addAll(path)
                        .color(Color.BLUE)
                        .width(10);

                //Computer Total Length Here in meters

                double finalTotal_distance = total_distance;
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        googleMap.addPolyline(polylineOptions);
                        CURRENT_DIRECTIONS_TOTAL_DISTANCE.set(
                                CURRENT_DIRECTIONS_TOTAL_DISTANCE.get()+
                                        finalTotal_distance);
                        PROCESSED_DIRECTIONS_POINTS_COUNTER.set(PROCESSED_DIRECTIONS_POINTS_COUNTER.get()+1);

                        if(PROCESSED_DIRECTIONS_POINTS_COUNTER.get() == CURRENT_DIRECTIONS_POINTS_COUNT.get()){
                            setCurrentDirectionsTotalDistance(CURRENT_DIRECTIONS_TOTAL_DISTANCE.get());
                            CURRENT_DIRECTIONS_TOTAL_DISTANCE.set(0.0);
                            PROCESSED_DIRECTIONS_POINTS_COUNTER.set(0);
                            CURRENT_DIRECTIONS_POINTS_COUNT.set(0);
                        }

                    }
                });
            }

            @Override
            public void onFailure(Throwable e) {
                e.printStackTrace();
            }
        });

    }


    private double distanceBetweenPoints(com.google.maps.model.LatLng point1, com.google.maps.model.LatLng point2) {
        Location loc1 = new Location("");
        loc1.setLatitude(point1.lat);
        loc1.setLongitude(point1.lng);

        Location loc2 = new Location("");
        loc2.setLatitude(point2.lat);
        loc2.setLongitude(point2.lng);

        return loc1.distanceTo(loc2);
    }



    public interface OnCurrentDirectionsTotalDistanceCalculatedListener {
        void OnCurrentDirectionsTotalDistanceCalculatedListener(double total_distance);
    }

    // Method to set the listener
    public void setOnCurrentDirectionsTotalDistanceCalculated(OnCurrentDirectionsTotalDistanceCalculatedListener listener) {
        this.CurrentDirectionTotalDistanceCalculatedListener = listener;
    }

    // Method to be called
    public double setCurrentDirectionsTotalDistance(double total_distance) {


        if (CurrentDirectionTotalDistanceCalculatedListener != null) {
            CurrentDirectionTotalDistanceCalculatedListener.OnCurrentDirectionsTotalDistanceCalculatedListener(total_distance);
        }

        return total_distance;
    }

    //OnCurrentLocationUpdated CurrentLocationUpdatedListener;

    public interface OnCurrentLocationUpdated {
        void OnCurrentLocationUpdated(double latitude, double longitude);
    }

    // Method to set the listener
    public void setOnCurrentLocationUpdated(OnCurrentLocationUpdated listener) {
        this.CurrentLocationUpdatedListener = listener;
    }

    // Method to be called
    public LatLng notifyCurrentLocationUpdated(double latitude, double longitude) {


        if (CurrentLocationUpdatedListener != null) {
            CurrentLocationUpdatedListener.OnCurrentLocationUpdated(latitude, longitude);
        }

        return new LatLng(latitude,longitude);
    }




    public void moveCamera(double latitude, double longitude){
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude,longitude), 21.0F));
    }

    public void moveCamera(double latitude, double longitude, float zoom_level){
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude,longitude), zoom_level));
    }


    public void moveCamera(LatLng camera_latlng){
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(camera_latlng, 21.0F));
    }

    public void moveCamera(LatLng camera_latlng, float zoom_level){
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(camera_latlng, zoom_level));
    }

    public Marker addMarker(double latitude, double longitude){
        return googleMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)));
    }

    public Marker addMarker(
            double latitude,
            double longitude,
            BitmapDescriptor marker_icon){

        return googleMap.addMarker(new MarkerOptions().
                position(new LatLng(latitude, longitude)).
                icon(marker_icon));
    }

    public Marker addMarker(MarkerOptions new_marker){
        return googleMap.addMarker(new_marker);
    }








}





