package com.racartech.app.rctandroidlts.maps;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.maps.DirectionsApi;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.LatLng;
import com.google.maps.model.TravelMode;
import com.racartech.app.rctandroidlts.api.ApiKeyManager;
import com.racartech.library.rctandroid.google.maps.RCTgoogleMapsUtil;
import com.racartech.library.rctandroid.json.RCTjson;
import com.racartech.library.rctandroid.time.RCTsecondsToTimeData;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class MapsUtilTest {


    public static void multiDestinationRoute(){
        new Thread(new Runnable() {
            @Override
            public void run() {

                LatLng origin = new LatLng(14.78073813, 120.87887886);

                ArrayList<LatLng> destinations = new ArrayList<>();

                /*
                destinations.add(new LatLng(18.19468676,120.59243305));
                destinations.add(new LatLng(18.3465677,121.64292501));
                destinations.add(new LatLng(14.82302884,120.90089204));

                 */

                destinations.add(new LatLng(15.04459116,120.67148187));

                ArrayList<DirectionsApi.RouteRestriction> route_rest = new ArrayList<>();
                route_rest.add(DirectionsApi.RouteRestriction.TOLLS);

                long start = System.currentTimeMillis();

                ArrayList<DirectionsRoute> multi_routes =
                        RCTgoogleMapsUtil.getMultiPointDirectionRoute(
                                ApiKeyManager.getGoogleApiKey(FirebaseFirestore.getInstance()),
                                origin,
                                destinations,
                                TravelMode.DRIVING,
                                route_rest,
                                Instant.now()
                        );

                double driving_distance = 0;
                long driving_time = 0;
                for(int index = 0; index< multi_routes.size(); index++){
                    driving_distance += RCTgoogleMapsUtil.getDrivingDistance_M(multi_routes.get(index));
                    driving_time += RCTgoogleMapsUtil.getDrivingTime_Seconds(multi_routes.get(index));
                }

                driving_distance = driving_distance/1000.0;

                List<com.google.android.gms.maps.model.LatLng> paths = RCTgoogleMapsUtil.getDirectionRoutePaths(multi_routes.get(0));

                long end = System.currentTimeMillis();
                long elapsed_time = end-start;


                System.out.println("----------------------------------------------------------------------");
                System.out.println("GOOGLE MAPS DIRECTIONS API RESULT");
                System.out.println("----------------------------------------------------------------------");
                System.out.println("Driving Distance : ".concat(String.valueOf(driving_distance)).concat(" KM"));
                System.out.println("Driving Time     : ".concat(String.valueOf(driving_time)).concat(" seconds"));
                System.out.println("Elapsed Time     : ".concat(String.valueOf(elapsed_time)).concat(" ms"));
                System.out.println("----------------------------------------------------------------------");
                RCTsecondsToTimeData seconds_time = new RCTsecondsToTimeData(driving_time);
                System.out.println("ST Day     : ".concat(String.valueOf(seconds_time.DAYS)));
                System.out.println("ST Hour    : ".concat(String.valueOf(seconds_time.HOURS)));
                System.out.println("ST Minute  : ".concat(String.valueOf(seconds_time.MINUTES)));
                System.out.println("ST Seconds : ".concat(String.valueOf(seconds_time.SECONDS)));
                System.out.println("----------------------------------------------------------------------");
                System.out.println("Paths Size : ".concat(String.valueOf(paths.size())));
                System.out.println("----------------------------------------------------------------------");


            }
        }).start();
    }


    public static void multiDestinationRouteWithInterval(){
        new Thread(new Runnable() {
            @Override
            public void run() {

                LatLng origin = new LatLng(14.78073813, 120.87887886);

                ArrayList<LatLng> destinations = new ArrayList<>();


                destinations.add(new LatLng(18.19468676,120.59243305));
                destinations.add(new LatLng(18.3465677,121.64292501));
                destinations.add(new LatLng(14.82302884,120.90089204));



                //destinations.add(new LatLng(15.04459116,120.67148187));

                ArrayList<DirectionsApi.RouteRestriction> route_rest = new ArrayList<>();
                route_rest.add(DirectionsApi.RouteRestriction.TOLLS);

                long start = System.currentTimeMillis();

                ArrayList<DirectionsRoute> multi_routes =
                        RCTgoogleMapsUtil.getMultiPointDirectionRoute(
                                ApiKeyManager.getGoogleApiKey(FirebaseFirestore.getInstance()),
                                origin,
                                destinations,
                                TravelMode.DRIVING,
                                route_rest,
                                Instant.now()
                        );

                double driving_distance = 0;
                long driving_time = 0;
                for(int index = 0; index< multi_routes.size(); index++){
                    driving_distance += RCTgoogleMapsUtil.getDrivingDistance_M(multi_routes.get(index));
                    driving_time += RCTgoogleMapsUtil.getDrivingTime_Seconds(multi_routes.get(index));
                }

                driving_distance = driving_distance/1000.0;

                List<com.google.android.gms.maps.model.LatLng> paths = new ArrayList<>();

                for(int index = 0; index< multi_routes.size(); index++){
                    paths.addAll(RCTgoogleMapsUtil.getDirectionRoutePathsWithInterval(multi_routes.get(index),10));
                }

                long end = System.currentTimeMillis();
                long elapsed_time = end-start;


                System.out.println("----------------------------------------------------------------------");
                System.out.println("GOOGLE MAPS DIRECTIONS API RESULT");
                System.out.println("----------------------------------------------------------------------");
                System.out.println("Driving Distance : ".concat(String.valueOf(driving_distance)).concat(" KM"));
                System.out.println("Driving Time     : ".concat(String.valueOf(driving_time)).concat(" seconds"));
                System.out.println("Elapsed Time     : ".concat(String.valueOf(elapsed_time)).concat(" ms"));
                System.out.println("----------------------------------------------------------------------");
                RCTsecondsToTimeData seconds_time = new RCTsecondsToTimeData(driving_time);
                System.out.println("ST Day     : ".concat(String.valueOf(seconds_time.DAYS)));
                System.out.println("ST Hour    : ".concat(String.valueOf(seconds_time.HOURS)));
                System.out.println("ST Minute  : ".concat(String.valueOf(seconds_time.MINUTES)));
                System.out.println("ST Seconds : ".concat(String.valueOf(seconds_time.SECONDS)));
                System.out.println("----------------------------------------------------------------------");
                System.out.println("Paths Size : ".concat(String.valueOf(paths.size())));
                System.out.println("----------------------------------------------------------------------");
                ArrayList<com.google.android.gms.maps.model.LatLng> all_paths = new ArrayList<>(paths);
                ArrayList<String> string_paths = new ArrayList<>();
                for(int index = 0; index<all_paths.size(); index++){
                    com.google.android.gms.maps.model.LatLng current_point = all_paths.get(index);
                    string_paths.add("(".
                            concat(String.valueOf(current_point.latitude)).
                            concat(",").
                            concat(String.valueOf(current_point.longitude)).concat(")")
                    );
                }
                String json_string = RCTjson.convertArrayListToString(string_paths);
                System.out.println("JSON String Length : ".concat(String.valueOf(json_string.length())));
                System.out.println("----------------------------------------------------------------------");

            }
        }).start();
    }

}
