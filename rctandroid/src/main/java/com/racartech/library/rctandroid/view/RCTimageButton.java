package com.racartech.library.rctandroid.view;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.MotionEvent;
import android.widget.ImageButton;

public class RCTimageButton {

    public static int DEFAULT_COLOR = Color.parseColor("#ECFBFD");
    @SuppressLint("ClickableViewAccessibility")
    public void addButtonPressedFilters(ImageButton image_button){
        image_button.setOnTouchListener((view, motionEvent) -> {
            int action_taken = motionEvent.getAction();
            switch(action_taken){
                case MotionEvent.ACTION_DOWN:
                    image_button.setColorFilter(DEFAULT_COLOR);
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
