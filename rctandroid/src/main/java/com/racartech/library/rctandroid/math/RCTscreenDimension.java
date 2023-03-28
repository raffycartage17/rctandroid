package com.racartech.library.rctandroid.math;

public class RCTscreenDimension {

    private double dim_width = 0;
    private double dim_height = 0;

    public RCTscreenDimension(
            double screen_dimension_width,
            double screen_dimension_height){
        this.dim_width = screen_dimension_width;
        this.dim_height = screen_dimension_height;
    }

    public double getWidth(int screen_height){
        double calculated_width = (screen_height/dim_height)*dim_width;
        return calculated_width;
    }

    public double getHeight(int screen_width){
        double calculated_height = (screen_width/dim_width)*dim_height;
        return calculated_height;
    }

}
