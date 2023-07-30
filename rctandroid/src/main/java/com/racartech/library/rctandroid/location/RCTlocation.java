package com.racartech.library.rctandroid.location;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class RCTlocation {


    public static double LAST_KNOWN_LATITUDE = 0;
    public static double LAST_KNOWN_LONGITUDE = 0;


    public final static int LATITUDE_INDEX = 0;
    public final static int LONGITUDE_INDEX = 1;

    public static Location LOCATION = null;

    public static double[] updateLastKnownLocation(Activity activity, Context app_context) {
        double[] loc_values = {0, 0};
        LocationManager locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(app_context,
                Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.checkSelfPermission(app_context, Manifest.permission.ACCESS_COARSE_LOCATION);
        }

        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        LAST_KNOWN_LATITUDE = location.getLatitude();
        LAST_KNOWN_LONGITUDE = location.getLongitude();
        loc_values[0] = LAST_KNOWN_LATITUDE;
        loc_values[1] = LAST_KNOWN_LONGITUDE;
        return loc_values;
    }

    public static double[] getDefaultLocationCoordinates() {
        return new double[]{14.5995, 120.9842};
    }

    public static void sortProximity(Location referenceLocation, ArrayList<Location> locations, String googleMapsApiKey) {
        // Define a Comparator to compare the travel distances between the locations and the reference location
        Comparator<Location> distanceComparator = new Comparator<Location>() {
            @Override
            public int compare(Location location1, Location location2) {
                // Calculate the travel distances between the locations and the reference location using the Google Maps Directions API
                float distance1 = getTravelDistance(referenceLocation, location1, googleMapsApiKey);
                float distance2 = getTravelDistance(referenceLocation, location2, googleMapsApiKey);
                // Compare the travel distances
                return Float.compare(distance1, distance2);
            }
        };
        // Sort the locations based on their travel distances to the reference location
        Collections.sort(locations, distanceComparator);
    }

    private static float getTravelDistance(Location origin, Location destination, String googleMapsApiKey) {
        // Construct a Google Maps Directions API request to get the travel distance and duration between the locations
        String url = "https://maps.googleapis.com/maps/api/directions/json" +
                "?origin=" + origin.getLatitude() + "," + origin.getLongitude() +
                "&destination=" + destination.getLatitude() + "," + destination.getLongitude() +
                "&mode=driving" +
                "&key=" + googleMapsApiKey;

        // Send the request to the Directions API and parse the JSON response to get the travel distance
        JsonObject response = sendRequestAndGetResponse(url);
        float distance = response.getAsJsonArray("routes")
                .get(0).getAsJsonObject()
                .getAsJsonArray("legs")
                .get(0).getAsJsonObject()
                .getAsJsonObject("distance")
                .get("value").getAsFloat();

        return distance;
    }

    private static JsonObject sendRequestAndGetResponse(String url) {
        try {
            URL requestUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) requestUrl.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder responseBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                responseBuilder.append(line);
            }
            reader.close();

            return JsonParser.parseString(responseBuilder.toString()).getAsJsonObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static void sortProximity(Location referenceLocation, ArrayList<Location> locations) {
        // Define a Comparator to compare the distances between the locations and the reference location
        Comparator<Location> distanceComparator = new Comparator<Location>() {
            @Override
            public int compare(Location location1, Location location2) {
                // Calculate the distances between the locations and the reference location
                float distance1 = location1.distanceTo(referenceLocation);
                float distance2 = location2.distanceTo(referenceLocation);
                // Compare the distances
                return Float.compare(distance1, distance2);
            }
        };
        // Sort the locations based on their distances to the reference location
        Collections.sort(locations, distanceComparator);
    }


    public static float getDistanceBetween_METER(Location start, Location end) {
        double startLat = start.getLatitude();
        double startLng = start.getLongitude();
        double endLat = end.getLatitude();
        double endLng = end.getLongitude();
        int radius = 6371; // Earth's radius in kilometers
        double dLat = Math.toRadians(endLat - startLat);
        double dLng = Math.toRadians(endLng - startLng);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(startLat)) * Math.cos(Math.toRadians(endLat)) *
                        Math.sin(dLng / 2) * Math.sin(dLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        float distance = (float) (radius * c * 1000); // Convert to meters
        return distance;
    }

    // Returns the latitude value of a Location object
    public static double getLatitude(Location location) {
        return location.getLatitude();
    }

    public static String getProvider(Location location) {
        return location.getProvider();
    }

    // Returns the longitude value of a Location object
    public static double getLongitude(Location location) {
        return location.getLongitude();
    }

    // Returns the altitude value of a Location object
    public static double getAltitude(Location location) {
        return location.getAltitude();
    }

    public static long getLocationMeasureTime(Location location) {
        return location.getTime();
    }

    public static Location getCurrentLocation(Context context) {
        // Check if the app has permission to access the device's location
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // App does not have permission, return null
            return null;
        }
        // Create a new instance of LocationManager
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        // Check if the location services are enabled
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            // Define a new LocationListener
            LocationListener locationListener = new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    // Do nothing
                }

                @Override
                public void onProviderDisabled(String provider) {
                    // Do nothing
                }

                @Override
                public void onProviderEnabled(String provider) {
                    // Do nothing
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {
                    // Do nothing
                }
            };

            // Request location updates from the GPS provider
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            // Get the last known location from the GPS provider
            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            return location;
        } else {
            // Location services are not enabled
            return null;
        }
    }







}
