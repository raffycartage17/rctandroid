package com.racartech.library.rctandroid.google.maps;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;

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
import com.google.maps.model.LatLng;
import com.google.maps.model.TrafficModel;
import com.google.maps.model.TravelMode;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class RCTgoogleMapsUtil {


    public static void viewOnMaps(Activity current_activity, double latitude, double longitude){
        String uri = "geo:" + latitude + "," + longitude + "?q=" + latitude + "," + longitude;
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        current_activity.startActivity(intent);
    }

    public static void viewOnMaps(Activity current_activity, String location_url){
        Uri locationUri = Uri.parse(location_url);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, locationUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        current_activity.startActivity(mapIntent);
    }


    /*
    String api_key = ApiKeyManager.getGoogleApiKey(firestore_instance);
    ArrayList<DirectionsApi.RouteRestriction> route_restrictions = new ArrayList<>();
    route_restrictions.add(DirectionsApi.RouteRestriction.TOLLS);
    TravelMode.DRIVING
    Instant.ofEpochMilli(System.currentTimeMillis())
     */

    public static double getDrivingDistance(
            String api_key,
            com.google.maps.model.LatLng origin,
            com.google.maps.model.LatLng destination,
            TravelMode travel_mode,
            ArrayList<DirectionsApi.RouteRestriction> route_restriction,
            Instant departure_time){

        boolean return_boolean = false;
        AtomicBoolean finished_boolean = new AtomicBoolean(false);
        AtomicDouble atomic_value = new AtomicDouble(0.0);
        getDrivingDirections_System(
                api_key,origin,
                destination,
                travel_mode,
                route_restriction,
                departure_time,
                atomic_value,
                finished_boolean);
        while(!return_boolean){
            if(!finished_boolean.get()){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }else{
                return_boolean = true;
            }
        }
        return atomic_value.get();
    }

    public static double getDrivingDistance(
            String api_key,
            com.google.maps.model.LatLng origin,
            com.google.maps.model.LatLng destination){

        boolean return_boolean = false;
        AtomicBoolean finished_boolean = new AtomicBoolean(false);
        AtomicDouble atomic_value = new AtomicDouble(0.0);

        getDrivingDirections_System(
                api_key,
                origin,
                destination,
                TravelMode.DRIVING,
                new ArrayList<>(),
                Instant.ofEpochMilli(System.currentTimeMillis()),
                atomic_value,
                finished_boolean
        );
        while(!return_boolean){
            if(!finished_boolean.get()){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }else{
                return_boolean = true;
            }
        }
        return atomic_value.get();
    }


    private static void getDrivingDirections_System(
            String api_key,
            com.google.maps.model.LatLng origin,
            com.google.maps.model.LatLng destination,
            TravelMode travel_mode,
            ArrayList<DirectionsApi.RouteRestriction> route_restriction,
            Instant departure_time,
            AtomicDouble atomic_value,
            AtomicBoolean finish_boolean) {





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
                List<com.google.android.gms.maps.model.LatLng> path = new ArrayList<>();
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
                            path.add(new com.google.android.gms.maps.model.LatLng(point.lat,point.lng));

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

                atomic_value.set(total_distance);
                finish_boolean.set(true);
            }

            @Override
            public void onFailure(Throwable e) {
                e.printStackTrace();
            }
        });

    }

    private static double distanceBetweenPoints(com.google.maps.model.LatLng point1, com.google.maps.model.LatLng point2) {
        Location loc1 = new Location("");
        loc1.setLatitude(point1.lat);
        loc1.setLongitude(point1.lng);

        Location loc2 = new Location("");
        loc2.setLatitude(point2.lat);
        loc2.setLongitude(point2.lng);

        return loc1.distanceTo(loc2);
    }


    /*


    public static String readField(
            String collection_path,
            String document_path,
            String fieldName,
            long thread_wait,
            FirebaseFirestore instance){

        boolean return_boolean = false;
        AtomicBoolean finished_boolean = new AtomicBoolean(false);
        AtomicReference<String> atomic_value = new AtomicReference<>(null);
        FirestoreUtil.readField(
                instance,
                collection_path,
                document_path,
                fieldName,
                finished_boolean,
                atomic_value);
        while(!return_boolean){
            if(!finished_boolean.get()){
                try {
                    Thread.sleep(thread_wait);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }else{
                return_boolean = true;
            }
        }
        return atomic_value.get();
    }


     */





}
