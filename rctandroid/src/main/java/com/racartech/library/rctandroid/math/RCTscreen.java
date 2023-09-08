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
}

