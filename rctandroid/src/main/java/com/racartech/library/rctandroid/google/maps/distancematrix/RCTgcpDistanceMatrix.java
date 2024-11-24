package com.racartech.library.rctandroid.google.maps.distancematrix;

import android.content.Context;
import android.location.Address;

import androidx.annotation.NonNull;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.DirectionsApi;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.TrafficModel;
import com.google.maps.model.TransitMode;
import com.google.maps.model.TransitRoutingPreference;
import com.google.maps.model.TravelMode;
import com.google.maps.model.Unit;
import com.racartech.library.rctandroid.location.RCTlocation;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class RCTgcpDistanceMatrix {





    ///////////////////////////////////////

    public static ArrayList<DistanceMatrixResult> getMostEfficientRoute_NearestNeighbor(
            LatLng start_location,
            ArrayList<DistanceMatrixResult> destinations,
            LatLng end_location
    ) {
        // Resulting ordered route
        ArrayList<DistanceMatrixResult> orderedRoute = new ArrayList<>();

        // Set of visited destinations
        Set<LatLng> visited = new HashSet<>();
        LatLng currentLocation = start_location;

        // Add destinations into a working list
        ArrayList<DistanceMatrixResult> remainingDestinations = new ArrayList<>(destinations);

        while (!remainingDestinations.isEmpty()) {
            DistanceMatrixResult nearestResult = null;
            long shortestDuration = Long.MAX_VALUE; // Store shortest duration in seconds

            // Find the nearest unvisited destination
            for (DistanceMatrixResult result : remainingDestinations) {
                if (!visited.contains(result.destination) && result.origin.equals(currentLocation)) {
                    long travelDuration = result.duration.inSeconds; // Get travel duration in seconds
                    if (travelDuration < shortestDuration) {
                        shortestDuration = travelDuration;
                        nearestResult = result;
                    }
                }
            }

            if (nearestResult == null) {
                // No path found for the current location, break the loop
                break;
            }

            // Add the nearest destination to the ordered route
            orderedRoute.add(nearestResult);
            visited.add(nearestResult.destination);
            currentLocation = nearestResult.destination;

            // Remove the visited destination from the working list
            remainingDestinations.remove(nearestResult);
        }

        // Handle the final leg to the end location
        if (!currentLocation.equals(end_location)) {
            for (DistanceMatrixResult result : destinations) {
                if (result.origin.equals(currentLocation) && result.destination.equals(end_location)) {
                    orderedRoute.add(result);
                    break;
                }
            }
        }

        removedUnnecessaryLeg(orderedRoute);
        return orderedRoute;
    }

    private static void removedUnnecessaryLeg(ArrayList<DistanceMatrixResult> route_legs){

        int unnecessaryLegIndex;

        // Loop as long as there are unnecessary legs in the route
        while ((unnecessaryLegIndex = doHaveUnnecessaryLeg(route_legs)) >= 0) {
            // Remove the unnecessary leg from the list
            route_legs.remove(unnecessaryLegIndex);
        }

    }

    private static int doHaveUnnecessaryLeg(ArrayList<DistanceMatrixResult> route_legs){
        for(int index = 0; index<route_legs.size(); index++){
            DistanceMatrixResult current_leg = route_legs.get(index);
            if(     (current_leg.origin.latitude == current_leg.destination.latitude &&
                    current_leg.origin.longitude == current_leg.destination.longitude) ||
                    current_leg.duration.inSeconds == 0 ||
                    current_leg.distance.inMeters == 0
            ){
                return index;
            }
        }
        return -1;
    }


    //////////////////////////////////////////






    public static DistanceMatrixResults getDistanceMatrix_Departure(
            String apiKey,
             ArrayList<LatLng> origins,
             ArrayList<LatLng> destinations,
             DirectionsApi.RouteRestriction routeRestriction,
             TrafficModel trafficModel,
             TravelMode travelMode,
             ArrayList<TransitMode>transitModes,
             TransitRoutingPreference transitRoutingPreference,
             @NonNull Instant departureTime,
             @NonNull Unit units,
            long threadWait
    ){

        AtomicBoolean is_finished = new AtomicBoolean(false);
        AtomicReference<DistanceMatrixResults> atomic_value = new AtomicReference<>(null);

        getDistanceMatrix_System(
                apiKey,
                origins,
                destinations,
                routeRestriction,
                trafficModel,
                travelMode,
                transitModes,
                transitRoutingPreference,
                departureTime,
                null,
                units,
                is_finished,
                atomic_value
                );

        while(!is_finished.get()){
            try {
                Thread.sleep(threadWait);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return atomic_value.get();
    }

    public static DistanceMatrixResults getDistanceMatrix_Arrival(
            String apiKey,
            ArrayList<LatLng> origins,
            ArrayList<LatLng> destinations,
            DirectionsApi.RouteRestriction routeRestriction,
            TrafficModel trafficModel,
            TravelMode travelMode,
            ArrayList<TransitMode>transitModes,
            TransitRoutingPreference transitRoutingPreference,
            @NonNull Instant arrivalTime,
            @NonNull Unit units,
            long threadWait

    ){

        AtomicBoolean is_finished = new AtomicBoolean(false);
        AtomicReference<DistanceMatrixResults> atomic_value = new AtomicReference<>(null);

        getDistanceMatrix_System(
                apiKey,
                origins,
                destinations,
                routeRestriction,
                trafficModel,
                travelMode,
                transitModes,
                transitRoutingPreference,
                null,
                arrivalTime,
                units,
                is_finished,
                atomic_value
        );

        while(!is_finished.get()){
            try {
                Thread.sleep(threadWait);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return atomic_value.get();
    }


    private static void getDistanceMatrix_System(
            String API_KEY,
            ArrayList<LatLng> origins,
            ArrayList<LatLng> destinations,
            DirectionsApi.RouteRestriction routeRestriction,
            TrafficModel trafficModel,
            TravelMode travelMode,
            ArrayList<TransitMode>transitModes,
            TransitRoutingPreference transitRoutingPreference,
            Instant departureTime,
            Instant arrivalTime,
            @NonNull Unit units,
            AtomicBoolean is_finished,
            AtomicReference<DistanceMatrixResults> atomic_value

    ) {

        // Convert LatLng to com.google.maps.model.LatLng
        com.google.maps.model.LatLng[] originArray = origins.stream()
                .map(latLng -> new com.google.maps.model.LatLng(latLng.latitude, latLng.longitude))
                .toArray(com.google.maps.model.LatLng[]::new);

        com.google.maps.model.LatLng[] destinationArray = destinations.stream()
                .map(latLng -> new com.google.maps.model.LatLng(latLng.latitude, latLng.longitude))
                .toArray(com.google.maps.model.LatLng[]::new);

        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(API_KEY)
                .build();


        new Thread(() -> {
            try {

                DistanceMatrixApiRequest the_request = DistanceMatrixApi.newRequest(context);

                if(!origins.isEmpty() && !destinations.isEmpty()){
                    the_request = the_request.origins(originArray);
                    the_request = the_request.destinations(destinationArray);
                }

                if(routeRestriction != null){
                    the_request = the_request.avoid(routeRestriction);
                }

                if(transitModes != null){
                    if(!transitModes.isEmpty()) {
                        the_request = the_request.transitModes(convertToVarargs(transitModes));
                    }
                }

                if(trafficModel != null){
                    the_request = the_request.trafficModel(trafficModel);
                }

                if(travelMode != null){
                    the_request = the_request.mode(travelMode);
                }

                if(transitRoutingPreference != null){
                    the_request = the_request.transitRoutingPreference(transitRoutingPreference);
                }

                if(arrivalTime != null){
                    the_request = the_request.arrivalTime(arrivalTime);
                }

                if(departureTime != null){
                    the_request = the_request.departureTime(departureTime);
                }

                the_request = the_request.units(units);


                DistanceMatrix result = the_request.await();






                DistanceMatrixResults matrix_result = new DistanceMatrixResults(origins,destinations,result);


                atomic_value.set(matrix_result);
                is_finished.set(true);


                // Process the result (Example: Log response data)
//                for (int i = 0; i < result.rows.length; i++) {
//                    for (int j = 0; j < result.rows[i].elements.length; j++) {
//                        System.out.println("From: " + origins.get(i) + " To: " + destinations.get(j));
//                        System.out.println("Distance            : " + result.rows[i].elements[j].distance);
//                        System.out.println("Duration            : " + result.rows[i].elements[j].duration);
//                    }
//                }



            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static TransitMode[] convertToVarargs(ArrayList<TransitMode> transitModes) {
        if (transitModes == null || transitModes.isEmpty()) {
            return new TransitMode[0]; // Return an empty array if the input is null or empty
        }
        return transitModes.toArray(new TransitMode[0]);
    }





    public static void printTSP(Context context, ArrayList<DistanceMatrixResult> arraylist){
        System.out.println("------------------------------------------------------");
        for(
                int index = 0; index<arraylist.size(); index++
        ){


            DistanceMatrixResult current = arraylist.get(index);

            Address origin_address = RCTlocation.getAddress(context,current.origin.latitude,current.origin.longitude);
            Address destination_address = RCTlocation.getAddress(context,current.destination.latitude,current.destination.longitude);

            try {
                System.out.println("Origin STR      : ".concat(origin_address.getAddressLine(0)));
                System.out.println("Destination STR : ".concat(destination_address.getAddressLine(0)));
            }catch (Exception ignored){}
            System.out.println("Origin          : ".concat(String.valueOf(current.origin)));
            System.out.println("Destination     : ".concat(String.valueOf(current.destination)));
            System.out.println("Distance (M)    : ".concat(String.valueOf(current.distance.inMeters)));
            System.out.println("Duration (S)    : ".concat(String.valueOf(current.duration.inSeconds)));
            System.out.println("------------------------------------------------------");

        }
    }


}
