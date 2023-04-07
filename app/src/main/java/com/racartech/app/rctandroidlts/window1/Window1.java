package com.racartech.app.rctandroidlts.window1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.documentfile.provider.DocumentFile;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.racartech.app.rctandroidlts.R;
import com.racartech.library.rctandroid.file.RCTfile;

public class Window1 extends AppCompatActivity {

    ImageView image_view;
    Button file_picker_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window1);

        image_view = findViewById(R.id.window1_imageview);
        file_picker_btn = findViewById(R.id.window1_filepicker_btn);

    }





}