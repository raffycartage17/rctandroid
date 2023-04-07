package com.racartech.app.rctandroidlts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.racartech.app.rctandroidlts.window1.Window1;
import com.racartech.library.rctandroid.calendar.RCTcalendar;
import com.racartech.library.rctandroid.file.RCTfile;
import com.racartech.library.rctandroid.hardware.RCTdiskInformation;
import com.racartech.library.rctandroid.math.RCTdataSizeConverter;

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


        f1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> mount_paths = RCTfile.getMountedDevices(getApplicationContext(),true,true);
                String disk_path = mount_paths.get(0);
                double total_disk_space = RCTdataSizeConverter.bytesToGigabytes(RCTdiskInformation.getDisk_TotalDiskSpace(disk_path));
                double used_disk_space = RCTdataSizeConverter.bytesToGigabytes(RCTdiskInformation.getDisk_UsedDiskSpace(disk_path));
                Toast.makeText(MainActivity.this, "Total Disk GB : ".concat(String.valueOf(total_disk_space)), Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, "Used  Disk GB : ".concat(String.valueOf(used_disk_space)), Toast.LENGTH_SHORT).show();
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
}