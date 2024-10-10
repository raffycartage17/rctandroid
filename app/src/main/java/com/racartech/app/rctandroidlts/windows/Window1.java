package com.racartech.app.rctandroidlts.windows;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.racartech.app.rctandroidlts.R;
import com.racartech.library.rctandroid.file.RCTfile;

public class Window1 extends AppCompatActivity {

    ImageView image_view;
    Button file_picker_btn;
    Button dialog_btn;

    private static final int FILE_PICKER_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window1);

        image_view = findViewById(R.id.window1_imageview);
        file_picker_btn = findViewById(R.id.window1_filepicker_btn);
        dialog_btn = findViewById(R.id.window1_open_dialog_button);


        file_picker_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
                startActivityForResult(intent, FILE_PICKER_REQUEST_CODE);
            }
        });
        dialog_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(Window1.this);
                dialog.setContentView(R.layout.standard_dialog_box);
                dialog.setCancelable(true);
                dialog.show();
            }
        });

        /*
        new_filedir_dialog = new Dialog(app_context);
            new_filedir_dialog.setContentView(R.layout.newrename_dialog_box);
            new_filedir_dialog.setCancelable(true);
         */

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == FILE_PICKER_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Uri fileUri = data.getData();
            String picked_file_path = RCTfile.getAbsolutePathFromURI(getApplicationContext(),fileUri);
        }
    }









}