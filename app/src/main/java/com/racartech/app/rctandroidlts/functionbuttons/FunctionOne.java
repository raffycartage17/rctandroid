package com.racartech.app.rctandroidlts.functionbuttons;

import android.app.Activity;
import android.content.Context;
import android.location.Address;

import com.google.firebase.firestore.FirebaseFirestore;
import com.racartech.library.rctandroid.file.RCTfile;
import com.racartech.library.rctandroid.google.firebase.firestore.RCTfirebaseFirestore;
import com.racartech.library.rctandroid.location.LocationData;
import com.racartech.library.rctandroid.location.RCTlocation;
import com.racartech.library.rctandroid.net.RCTinternet;
import com.racartech.library.rctandroid.time.RCTtime;
import com.racartech.library.rctandroid.time.RCTtimeData;

import java.util.HashMap;

public class FunctionOne{


    public static void launch(Context context, Activity activity) {
        new Thread(new Runnable() {
            @Override
            public void run(){
                long start = System.currentTimeMillis();
                String download_url = "https://firebasestorage.googleapis.com/v0/b/racartech-81aff.appspot.com/o/test_file%2Ftest_file.zip?alt=media&token=705fdb44-cb1c-48da-962c-36a796ceffe5";
                String file_path = RCTfile.getDir_IntAppFiles(context).concat("/download_test.zip");
                RCTinternet.downloadFile(download_url,file_path);
                long end = System.currentTimeMillis();
                System.out.println("------------------------------------------------------------------------");
                System.out.println("Elapse Time : ".concat(String.valueOf((end-start))));
                System.out.println("------------------------------------------------------------------------");
            }
        }).start();

    }




}
