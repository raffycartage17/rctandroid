package com.racartech.app.rctandroidlts.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.racartech.app.rctandroidlts.R;
import com.racartech.library.rctandroid.net.RCTinternet;

import java.util.concurrent.atomic.AtomicBoolean;

public class ProgressDialog {

    public static void showDialog(Activity activity, Context context) {
        AtomicBoolean progress = new AtomicBoolean(false);
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.progress_dialog_layout);
        dialog.setCancelable(true);

        TextView text_view = dialog.findViewById(R.id.progress_dialog_textview);
        text_view.setText("Done : false");

        dialog.show();
        new Thread(new Runnable() {
            @Override
            public void run(){
                //RCTinternet.downloadFile()
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run(){
                    }
                });
            }
        }).start();


    }

}
