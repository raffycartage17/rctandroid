package com.racartech.app.rctandroidlts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.racartech.library.rctandroid.calendar.RCTcalendar;
import com.racartech.library.rctandroid.file.RCTfile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
                ArrayList<String> aaa= RCTfile.getMountedDevices(getApplicationContext(),true,true);

                if(aaa.size() > 0){
                    Toast.makeText(MainActivity.this, "OTG : ".concat(String.valueOf(aaa.size())), Toast.LENGTH_SHORT).show();


                    String[] dirs = new String[aaa.size()];
                    for(int index = 0; index<dirs.length; index++){
                        dirs[index] = aaa.get(index);
                    }

                    String debug_file = RCTfile.getDir_ExternalStorageRoot().concat("/apath/lts_test_file.txt");
                    Toast.makeText(MainActivity.this, debug_file, Toast.LENGTH_SHORT).show();
                    if(!RCTfile.doesFileExist(debug_file)){
                        RCTfile.createFile(debug_file);
                    }

                    try{
                        RCTfile.overrideFile(debug_file,dirs);
                    }catch (IOException ignored) {}


                }else{
                    Toast.makeText(MainActivity.this, "Null", Toast.LENGTH_SHORT).show();
                }
            }
        });




    }
}