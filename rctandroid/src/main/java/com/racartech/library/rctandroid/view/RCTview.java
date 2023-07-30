package com.racartech.library.rctandroid.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

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



    @SuppressLint("ClickableViewAccessibility")
    public static void addButtonPressedFilters(ImageButton image_button,int pressed_color){
        image_button.setOnTouchListener((view, motionEvent) -> {
            int action_taken = motionEvent.getAction();
            switch(action_taken){
                case MotionEvent.ACTION_DOWN:
                    image_button.setColorFilter(pressed_color);
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    image_button.clearColorFilter();
                    break;
                default:
                    break ;
            }
            return false;
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    public static void addButtonPressedFilters_TextColor(Button the_button, int unpressed_color, int pressed_color){
        the_button.setOnTouchListener((view, motionEvent) -> {
            int action_taken = motionEvent.getAction();
            switch(action_taken){
                case MotionEvent.ACTION_DOWN:
                    the_button.setTextColor(pressed_color);
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    the_button.setTextColor(unpressed_color);
                    break;
                default:
                    break ;
            }
            return false;
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    public static void addButtonPressedFilters(Button the_button, int unpressed_color, int pressed_color){
        the_button.setOnTouchListener((view, motionEvent) -> {
            int action_taken = motionEvent.getAction();
            switch(action_taken){
                case MotionEvent.ACTION_DOWN:
                    the_button.setBackgroundColor(pressed_color);
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    the_button.setBackgroundColor(unpressed_color);
                    break;
                default:
                    break ;
            }
            return false;
        });
    }





}
