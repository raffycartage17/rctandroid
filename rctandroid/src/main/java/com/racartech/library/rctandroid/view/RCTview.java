package com.racartech.library.rctandroid.view;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;

public class RCTview {

    public static View inflateChildView(ViewGroup parent, @LayoutRes int layoutResId) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return inflater.inflate(layoutResId, parent, false);
    }


    public static ViewGroup getParentAsViewGroup(Activity activity, int resourceId) {
        View view = activity.findViewById(resourceId);
        if (view instanceof ViewGroup) {
            return (ViewGroup) view;
        } else {
            return null;
        }
    }

}
