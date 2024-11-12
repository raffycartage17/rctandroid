package com.racartech.library.rctandroid.location;

import android.location.Address;

public class RCTdistance {

    // Constants for WGS84 ellipsoid model of Earth
    private static final double SEMI_MAJOR_AXIS_MT = 6378137;
    private static final double SEMI_MINOR_AXIS_MT = 6356752.314245;
    private static final double FLATTENING = 1 / 298.257223563;
    private static final double ERROR_TOLERANCE = 1e-12;

    private static final double MILES_TO_METERS = 1609.34;
    private static final double MILES_TO_KILOMETERS = 1.60934;
    private static final double FEET_TO_METERS = 0.3048;
    private static final double FEET_TO_MILES = 5280.0;


    public static double calculateDistance_KM(RCTlocationData location_a, RCTlocationData location_b){
        return (calculateDistance_M(location_a.getAddress(), location_b.getAddress())/1000.0);
    }

    public static double calculateDistance_KM(Address point_a, Address point_b){
        return (mainCalculateDistance_M(
                point_a.getLatitude(),point_a.getLongitude(),
                point_b.getLatitude(), point_b.getLongitude()
        )/1000.0);
    }

    public static double calculateDistance_KM(double latitude1, double longitude1, double latitude2, double longitude2){
        return (mainCalculateDistance_M(latitude1,longitude1, latitude2, longitude2)/1000.0);
    }




    public static double calculateDistance_M(RCTlocationData location_a, RCTlocationData location_b){
        return calculateDistance_M(location_a.getAddress(), location_b.getAddress());
    }

    public static double calculateDistance_M(Address point_a, Address point_b){
        return mainCalculateDistance_M(
                point_a.getLatitude(),point_a.getLongitude(),
                point_b.getLatitude(), point_b.getLongitude()
        );
    }

    public static double calculateDistance_M(double latitude1, double longitude1, double latitude2, double longitude2){
        return mainCalculateDistance_M(latitude1,longitude1, latitude2, longitude2);
    }






    private static double mainCalculateDistance_M(double latitude1, double longitude1, double latitude2, double longitude2) {
        double U1 = Math.atan((1 - FLATTENING) * Math.tan(Math.toRadians(latitude1)));
        double U2 = Math.atan((1 - FLATTENING) * Math.tan(Math.toRadians(latitude2)));

        double sinU1 = Math.sin(U1);
        double cosU1 = Math.cos(U1);
        double sinU2 = Math.sin(U2);
        double cosU2 = Math.cos(U2);

        double longitudeDifference = Math.toRadians(longitude2 - longitude1);
        double previousLongitudeDifference;

        double sinSigma, cosSigma, sigma, sinAlpha, cosSqAlpha, cos2SigmaM;

        do {
            sinSigma = Math.sqrt(Math.pow(cosU2 * Math.sin(longitudeDifference), 2) +
                    Math.pow(cosU1 * sinU2 - sinU1 * cosU2 * Math.cos(longitudeDifference), 2));
            cosSigma = sinU1 * sinU2 + cosU1 * cosU2 * Math.cos(longitudeDifference);
            sigma = Math.atan2(sinSigma, cosSigma);
            sinAlpha = cosU1 * cosU2 * Math.sin(longitudeDifference) / sinSigma;
            cosSqAlpha = 1 - Math.pow(sinAlpha, 2);
            cos2SigmaM = cosSigma - 2 * sinU1 * sinU2 / cosSqAlpha;
            if (Double.isNaN(cos2SigmaM)) {
                cos2SigmaM = 0;
            }
            previousLongitudeDifference = longitudeDifference;
            double C = FLATTENING / 16 * cosSqAlpha * (4 + FLATTENING * (4 - 3 * cosSqAlpha));
            longitudeDifference = Math.toRadians(longitude2 - longitude1) + (1 - C) * FLATTENING * sinAlpha *
                    (sigma + C * sinSigma * (cos2SigmaM + C * cosSigma * (-1 + 2 * Math.pow(cos2SigmaM, 2))));
        } while (Math.abs(longitudeDifference - previousLongitudeDifference) > ERROR_TOLERANCE);

        double uSq = cosSqAlpha * (Math.pow(SEMI_MAJOR_AXIS_MT, 2) - Math.pow(SEMI_MINOR_AXIS_MT, 2)) / Math.pow(SEMI_MINOR_AXIS_MT, 2);

        double A = 1 + uSq / 16384 * (4096 + uSq * (-768 + uSq * (320 - 175 * uSq)));
        double B = uSq / 1024 * (256 + uSq * (-128 + uSq * (74 - 47 * uSq)));

        double deltaSigma = B * sinSigma * (cos2SigmaM + B / 4 * (cosSigma * (-1 + 2 * Math.pow(cos2SigmaM, 2)) -
                B / 6 * cos2SigmaM * (-3 + 4 * Math.pow(sinSigma, 2)) * (-3 + 4 * Math.pow(cos2SigmaM, 2))));

        double distanceMt = SEMI_MINOR_AXIS_MT * A * (sigma - deltaSigma);
        return distanceMt;
    }


    // Meters to Miles
    public static double metersToMiles(double meters) {
        return meters / MILES_TO_METERS;
    }

    // Miles to Meters
    public static double milesToMeters(double miles) {
        return miles * MILES_TO_METERS;
    }

    // Kilometers to Miles
    public static double kilometersToMiles(double kilometers) {
        return kilometers / MILES_TO_KILOMETERS;
    }

    // Miles to Kilometers
    public static double milesToKilometers(double miles) {
        return miles * MILES_TO_KILOMETERS;
    }

    // Meters to Feet
    public static double metersToFeet(double meters) {
        return meters / FEET_TO_METERS;
    }

    // Feet to Meters
    public static double feetToMeters(double feet) {
        return feet * FEET_TO_METERS;
    }

    // Feet to Miles
    public static double feetToMiles(double feet) {
        return feet / FEET_TO_MILES;
    }

    // Miles to Feet
    public static double milesToFeet(double miles) {
        return miles * FEET_TO_MILES;
    }



}
