package com.racartech.library.rctandroid.snippets;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;

import com.racartech.library.rctandroid.R;
import com.racartech.library.rctandroid.file.RCTfile;

public class SnippetFilePicker extends AppCompatActivity {


    ImageView image_view;
    Button file_picker_btn;

    private static final int FILE_PICKER_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.snippet_standard_layout);

        image_view = findViewById(R.id.snippet_test_layout_imageview);
        file_picker_btn = findViewById(R.id.snippet_test_layout_test_button);


        file_picker_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/ /*");    //Remove the second / in intent.setType("*/ /*");
                startActivityForResult(intent, FILE_PICKER_REQUEST_CODE);
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == FILE_PICKER_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Uri fileUri = data.getData();
            String picked_file_path = RCTfile.getAbsolutePathFromURI(getApplicationContext(),fileUri);
        }
    }


    //File Picker Code
    /* Activity Code
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

    private static final int FILE_PICKER_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window1);

        image_view = findViewById(R.id.window1_imageview);
        file_picker_btn = findViewById(R.id.window1_filepicker_btn);


        file_picker_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/ /*");    //Remove the second / in intent.setType("*/ /*");
    startActivityForResult(intent, FILE_PICKER_REQUEST_CODE);
}
        });

                }

@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == FILE_PICKER_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
        Uri fileUri = data.getData();
        String picked_file_path = RCTfile.getAbsolutePathFromURI(getApplicationContext(),fileUri);
        }
        }

     */





}
