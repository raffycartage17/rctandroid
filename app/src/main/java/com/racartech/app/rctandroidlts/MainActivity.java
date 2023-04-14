package com.racartech.app.rctandroidlts;

import static android.provider.Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.racartech.app.rctandroidlts.window1.Window1;
import com.racartech.library.rctandroid.calendar.RCTcalendar;
import com.racartech.library.rctandroid.file.RCTdirectory;
import com.racartech.library.rctandroid.file.RCTfile;
import com.racartech.library.rctandroid.hardware.RCTdiskInformation;
import com.racartech.library.rctandroid.math.RCTdataSizeConverter;
import com.racartech.library.rctandroid.notifications.RCTnotifications;
import com.racartech.library.rctandroid.permission.RCTpermission;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {


    Button f1,f2,f3,f4,f5,f6,f7,f8,f9;
    Button window_1_btn,window_2_btn,window_3_btn,window_4_btn;
    Button textbox_enter;
    EditText textbox;
    TextView textview_1;
    TextView textview_2;
    Switch switch_1,switch_2;


    private static final int MANAGE_EXTERNAL_STORAGE_PERMISSION_CODE = 100;
    private static final int READ_EXTERNAL_STORAGE_PERMISSION_CODE = 101;
    private static final int WRITE_EXTERNAL_STORAGE_PERMISSION_CODE = 102;
    private int REQUEST_CODE_PERMISSIONS = 1001;
    private final String[] REQUIRED_PERMISSIONS = new String[]{"android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE","android.permission.READ_EXTERNAL_STORAGE"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        f1 = findViewById(R.id.mm_f1_button);
        f2 = findViewById(R.id.mm_f2_button);
        f3 = findViewById(R.id.mm_f3_button);
        f4 = findViewById(R.id.mm_f4_button);
        f5 = findViewById(R.id.mm_f5_button);
        f6 = findViewById(R.id.mm_f6_button);
        f7 = findViewById(R.id.mm_f7_button);
        f8 = findViewById(R.id.mm_f8_button);
        f9 = findViewById(R.id.mm_f9_button);

        window_1_btn = findViewById(R.id.mm_window1_button);
        window_2_btn = findViewById(R.id.mm_window2_button);
        window_3_btn = findViewById(R.id.mm_window3_button);
        window_4_btn = findViewById(R.id.mm_window4_button);

        textbox_enter = findViewById(R.id.mm_textbox_enter_button);
        switch_1 = findViewById(R.id.mm_switch1);
        switch_2 = findViewById(R.id.mm_switch2);

        textview_1 = findViewById(R.id.mm_textview_1);
        textview_2 = findViewById(R.id.mm_textview_2);


        RCTpermission.allowPermissions(this,new String[]{
                Manifest.permission.CAMERA,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION

        },0);
        promptUserAllowPermission();


        f1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });





        window_1_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Window1.class);
                startActivity(intent);
            }
        });




    }

    private void promptUserAllowPermission(){
        if(!Environment.isExternalStorageManager()){
            Context app_context = MainActivity.this;
            Dialog request_dialog = new Dialog(app_context);
            request_dialog.setContentView(R.layout.standard_dialog_box);
            request_dialog.setCancelable(true);

            TextView dialog_title = request_dialog.findViewById(R.id.standard_dialog_title_textview);
            TextView dialog_description = request_dialog.findViewById(R.id.standard_dialog_description_textview);
            Button ok_button = request_dialog.findViewById(R.id.standard_dialog_right_operation_button);
            Button cancel_button = request_dialog.findViewById(R.id.standard_dialog_left_operation_button);
            ok_button.setText(app_context.getString(R.string.OK));
            cancel_button.setText(app_context.getString(R.string.Cancel));
            dialog_title.setText(app_context.getString(R.string.app_name));
            dialog_description.setText(app_context.getString(R.string.access_all_files_permission_dialog_request_description));
            request_dialog.show();
            ok_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent new_intent = new Intent(ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION, Uri.parse("package:" + BuildConfig.APPLICATION_ID));
                    startActivityForResult(new_intent, MANAGE_EXTERNAL_STORAGE_PERMISSION_CODE);
                    request_dialog.dismiss();
                }
            });
            cancel_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RCTnotifications.makeToast_SHORT(app_context, "Permission Request Denied");
                    request_dialog.dismiss();
                }
            });
        }
        checkPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE,WRITE_EXTERNAL_STORAGE_PERMISSION_CODE);
        checkPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE,READ_EXTERNAL_STORAGE_PERMISSION_CODE);


    }

    private void checkPermission(String permission, int request_code){
        if(ContextCompat.checkSelfPermission(MainActivity.this,permission)== PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{permission},request_code);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


}