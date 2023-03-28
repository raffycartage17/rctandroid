package com.racartech.library.rctandroid.notifications;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class RCTnotifications{
    public static void makeToast_LONG(Context app_context, String text){
        Toast.makeText(app_context, text, Toast.LENGTH_LONG).show();
    }
    public static void makeToast_SHORT(Context app_context, String text){
        Toast.makeText(app_context, text, Toast.LENGTH_SHORT).show();
    }

    public static void makeToast_LONG_MainThread(Activity current_activity, String text){
        current_activity.runOnUiThread(new Runnable() {
            @Override
            public void run(){
                Toast.makeText(current_activity.getApplicationContext(), text, Toast.LENGTH_LONG).show();
            }
        });
    }
    public static void makeToast_SHORT_MainThread(Activity current_activity, String text){
        current_activity.runOnUiThread(new Runnable() {
            @Override
            public void run(){
                Toast.makeText(current_activity.getApplicationContext(), text, Toast.LENGTH_LONG).show();
            }
        });
    }



    public static void makeSnackbar(View view, String notif_text, int length){
        Snackbar.make(view,notif_text,length).show();
    }

}
