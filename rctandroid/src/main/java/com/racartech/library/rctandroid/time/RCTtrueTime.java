package com.racartech.library.rctandroid.time;

import com.instacart.library.truetime.TrueTime;

import java.io.IOException;

public class RCTtrueTime{

    public static TrueTime trueTime = TrueTime.build();

    public static synchronized void initialize(){
        try {
            trueTime.initialize();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static synchronized long getTime(){
        return TrueTime.now().getTime();
    }

}
