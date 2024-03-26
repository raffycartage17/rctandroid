package com.racartech.library.rctandroid.location;

public class RCTcoordinates {

    public static double degreesToMeters(double degrees) {
        return degrees * 111320;
    }

    public static double metersToLatitude(double meters) {
        return meters / 111320;
    }


}
