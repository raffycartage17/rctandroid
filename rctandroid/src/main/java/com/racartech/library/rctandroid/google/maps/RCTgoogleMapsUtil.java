package com.racartech.library.rctandroid.google.maps;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;

import com.google.android.gms.maps.model.PolylineOptions;
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

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class RCTgoogleMapsUtil {
    /////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////
    /*
    String api_key = ApiKeyManager.getGoogleApiKey(firestore_instance);
    ArrayList<DirectionsApi.RouteRestriction> route_restrictions = new ArrayList<>();
    route_restrictions.add(DirectionsApi.RouteRestriction.TOLLS);
    TravelMode.DRIVING
    Instant.ofEpochMilli(System.currentTimeMillis())
     */

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


    public static DirectionsRoute getDirectionRoute(
            String api_key,
            com.google.maps.model.LatLng origin,
            com.google.maps.model.LatLng destination,
            TravelMode travel_mode,
            ArrayList<DirectionsApi.RouteRestriction> route_restriction,
            Instant departure_time
    ){
        boolean return_boolean = false;
        AtomicBoolean finished_boolean = new AtomicBoolean(false);
        AtomicReference<DirectionsRoute> atomic_value = new AtomicReference<>(null);

        getDirectionRoute_System(
                api_key,
                origin,
                destination,
                travel_mode,
                route_restriction,
                departure_time,
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


    public static DirectionsRoute getDirectionRoute(
            String api_key,
            com.google.maps.model.LatLng origin,
            com.google.maps.model.LatLng destination
    ){
        boolean return_boolean = false;
        AtomicBoolean finished_boolean = new AtomicBoolean(false);
        AtomicReference<DirectionsRoute> atomic_value = new AtomicReference<>(null);

        getDirectionRoute_System(
                api_key,
                origin,
                destination,
                TravelMode.DRIVING,
                new ArrayList<>(),
                Instant.now(),
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


    private static void getDirectionRoute_System(
            String api_key,
            com.google.maps.model.LatLng origin,
            com.google.maps.model.LatLng destination,
            TravelMode travel_mode,
            ArrayList<DirectionsApi.RouteRestriction> route_restriction,
            Instant departure_time,
            AtomicReference<DirectionsRoute> atomic_value,
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
                DirectionsRoute route = result.routes[0];

                atomic_value.set(route);
                finish_boolean.set(true);
            }

            @Override
            public void onFailure(Throwable e) {
                e.printStackTrace();
            }
        });

    }




    public static ArrayList<DirectionsRoute> getAllDirectionRoute(
            String api_key,
            com.google.maps.model.LatLng origin,
            com.google.maps.model.LatLng destination,
            TravelMode travel_mode,
            ArrayList<DirectionsApi.RouteRestriction> route_restriction,
            Instant departure_time
    ){
        boolean return_boolean = false;
        AtomicBoolean finished_boolean = new AtomicBoolean(false);
        AtomicReference<ArrayList<DirectionsRoute>> atomic_value = new AtomicReference<>(null);

        getAllDirectionRoute_System(
                api_key,
                origin,
                destination,
                travel_mode,
                route_restriction,
                departure_time,
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


    public static ArrayList<DirectionsRoute> getAllDirectionRoute(
            String api_key,
            com.google.maps.model.LatLng origin,
            com.google.maps.model.LatLng destination
    ){
        boolean return_boolean = false;
        AtomicBoolean finished_boolean = new AtomicBoolean(false);
        AtomicReference<ArrayList<DirectionsRoute>> atomic_value = new AtomicReference<>(null);

        getAllDirectionRoute_System(
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

    private static void getAllDirectionRoute_System(
            String api_key,
            com.google.maps.model.LatLng origin,
            com.google.maps.model.LatLng destination,
            TravelMode travel_mode,
            ArrayList<DirectionsApi.RouteRestriction> route_restriction,
            Instant departure_time,
            AtomicReference<ArrayList<DirectionsRoute>> atomic_value,
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

                List<com.google.android.gms.maps.model.LatLng> path = new ArrayList<>();

                ArrayList<DirectionsRoute> all_routes = new ArrayList<>(Arrays.asList(result.routes));
                atomic_value.set(all_routes);
                finish_boolean.set(true);
            }

            @Override
            public void onFailure(Throwable e) {
                e.printStackTrace();
            }
        });

    }



    public static double getDrivingDistance_M(
            DirectionsRoute direction_route
    ) {

        double total_distance = 0.0;
        List<com.google.android.gms.maps.model.LatLng> path = new ArrayList<>();
        DirectionsRoute route = direction_route;
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
        return total_distance;
    }

    public static long getDrivingTime_Seconds(DirectionsRoute direction_route){
        DirectionsRoute route = direction_route;
        if (route.legs != null && route.legs.length > 0) {
            long estimatedTimeInSeconds = 0;
            for (com.google.maps.model.DirectionsLeg leg : route.legs) {
                if (leg.duration != null) {
                    estimatedTimeInSeconds += leg.duration.inSeconds;
                }
            }
            return estimatedTimeInSeconds;
        }else{
            return -1;
        }
    }

    public static PolylineOptions getDirectionRoutePolylineOptions(
            DirectionsRoute direction_route
    ) {
        List<com.google.android.gms.maps.model.LatLng> path = new ArrayList<>();
        DirectionsRoute route = direction_route;
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
                }
            }
        }
        PolylineOptions polylineOptions = new PolylineOptions()
                .addAll(path)
                .color(Color.BLUE)
                .width(10);

        return polylineOptions;
    }


    public static List<com.google.android.gms.maps.model.LatLng> getDirectionRoutePaths(
            DirectionsRoute direction_route
    ) {
        List<com.google.android.gms.maps.model.LatLng> paths = new ArrayList<>();
        DirectionsRoute route = direction_route;
        DirectionsLeg[] legs = route.legs;
        for (int i = 0; i < legs.length; i++) {
            DirectionsLeg leg = legs[i];
            DirectionsStep[] steps = leg.steps;
            for (int j = 0; j < steps.length; j++) {
                DirectionsStep step = steps[j];
                List<com.google.maps.model.LatLng> decodePath = step.polyline.decodePath();
                for (int k = 0; k < decodePath.size(); k++) {
                    com.google.maps.model.LatLng point = decodePath.get(k);
                    paths.add(new com.google.android.gms.maps.model.LatLng(point.lat,point.lng));
                }
            }
        }
        return paths;
    }



    private static double distanceBetweenPoints(com.google.maps.model.LatLng point1, com.google.maps.model.LatLng point2) {
        double R = 6371000; // Radius of the Earth in meters
        double lat1 = Math.toRadians(point1.lat);
        double lon1 = Math.toRadians(point1.lng);
        double lat2 = Math.toRadians(point2.lat);
        double lon2 = Math.toRadians(point2.lng);

        double dLat = lat2 - lat1;
        double dLon = lon2 - lon1;

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(lat1) * Math.cos(lat2) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c;
    }




    /* Core Code

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


     */



}
