package com.racartech.library.rctandroid.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
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
    public static void addButtonPressedFilters(
            Button the_button,
            int pressed_color,
            int unpressed_color){
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


    @SuppressLint("ClickableViewAccessibility")
    public static void addButtonPressedFilters_WithOriginalColor(
            ImageButton image_button,
            int pressed_color,
            int unpressed_color){
        image_button.setOnTouchListener((view, motionEvent) -> {
            int action_taken = motionEvent.getAction();
            switch(action_taken){
                case MotionEvent.ACTION_DOWN:
                    image_button.setColorFilter(pressed_color);
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    image_button.setColorFilter(unpressed_color);
                    break;
                default:
                    break ;
            }
            return false;
        });
    }



    @SuppressLint("ClickableViewAccessibility")
    public static void addButtonPressedFilters(View the_view, int pressed_color, int original_color){
        the_view.setOnTouchListener((view, motionEvent) -> {
            int action_taken = motionEvent.getAction();
            switch(action_taken){
                case MotionEvent.ACTION_DOWN:
                    the_view.setBackgroundColor(pressed_color);
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    the_view.setBackgroundColor(original_color);
                    break;
                default:
                    break ;
            }
            return false;
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    public static void addButtonPressedFilters(ImageButton image_button, int pressed_color, int original_color){
        image_button.setOnTouchListener((view, motionEvent) -> {
            int action_taken = motionEvent.getAction();
            switch(action_taken){
                case MotionEvent.ACTION_DOWN:
                    image_button.setColorFilter(pressed_color);
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    image_button.setColorFilter(original_color);
                    break;
                default:
                    break ;
            }
            return false;
        });
    }


    @SuppressLint("ClickableViewAccessibility")
    public static void addButtonPressedFilters(View the_view, Drawable pressed_color, Drawable original_color){
        the_view.setOnTouchListener((view, motionEvent) -> {
            int action_taken = motionEvent.getAction();
            switch(action_taken){
                case MotionEvent.ACTION_DOWN:
                    the_view.setBackground(pressed_color);
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    the_view.setBackground(original_color);
                    break;
                default:
                    break ;
            }
            return false;
        });
    }


    @SuppressLint("ClickableViewAccessibility")
    public static void addTextViewPressedFilters_BackgroundColor(TextView text_view, int pressed_color, int original_color){
        text_view.setOnTouchListener((view, motionEvent) -> {
            int action_taken = motionEvent.getAction();
            switch(action_taken){
                case MotionEvent.ACTION_DOWN:
                    text_view.setBackgroundColor(pressed_color);
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    text_view.setBackgroundColor(original_color);
                    break;
                default:
                    break ;
            }
            return false;
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    public static void addTextViewPressedFilters_TextColor(TextView text_view, int pressed_color, int original_color){
        text_view.setOnTouchListener((view, motionEvent) -> {
            int action_taken = motionEvent.getAction();
            switch(action_taken){
                case MotionEvent.ACTION_DOWN:
                    text_view.setTextColor(pressed_color);
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    text_view.setTextColor(original_color);
                    break;
                default:
                    break ;
            }
            return false;
        });
    }


    @SuppressLint("ClickableViewAccessibility")
    public static void addButtonPressedFilters(ImageButton image_button, int color){
        image_button.setOnTouchListener((view, motionEvent) -> {
            int action_taken = motionEvent.getAction();
            switch(action_taken){
                case MotionEvent.ACTION_DOWN:
                    image_button.setColorFilter(color);
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





}
