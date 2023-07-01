package com.racartech.library.rctandroid.math;

public class RCTaspectRatio {

    public static double calculateHeight(double aspectRatioWidth, double aspectRatioHeight, double width) {
        double calculatedRatio = aspectRatioWidth / aspectRatioHeight;
        double calculatedHeight = width / calculatedRatio;
        return calculatedHeight;
    }

    public static double calculateWidth(double aspectRatioWidth, double aspectRatioHeight, double height) {
        double calculatedRatio = aspectRatioWidth / aspectRatioHeight;
        double calculatedWidth = height * calculatedRatio;
        return calculatedWidth;
    }


}
