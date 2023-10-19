package com.racartech.library.rctandroid.math;

import android.content.Context;
import android.util.DisplayMetrics;

public class RCTscreen {
    public static int dpToPx(Context context, int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float density = displayMetrics.density;
        return Math.round(dp * density);
    }

    public static int pxToDp(Context context, int px) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float density = displayMetrics.density;
        return Math.round(px / density);
    }


    public static int[] to1080(int originalWidth, int originalHeight) {
        return convertToResolution(originalWidth, originalHeight, 1920, 1080);
    }

    public static int[] to720(int originalWidth, int originalHeight) {
        return convertToResolution(originalWidth, originalHeight, 1280, 720);
    }

    public static int[] to480(int originalWidth, int originalHeight) {
        return convertToResolution(originalWidth, originalHeight, 854, 480);
    }

    public static int[] convertToResolution(int originalWidth, int originalHeight, int targetWidth, int targetHeight) {
        int newWidth;
        int newHeight;

        double aspectRatio = (double) originalWidth / originalHeight;

        if (aspectRatio > 1) {
            newWidth = targetWidth;
            newHeight = (int) (targetWidth / aspectRatio);
        } else {
            newHeight = targetHeight;
            newWidth = (int) (targetHeight * aspectRatio);
        }

        int[] newResolution = {newWidth, newHeight};
        return newResolution;
    }
}

