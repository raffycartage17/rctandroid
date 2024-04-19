package com.racartech.app.rctandroidlts.functionbuttons;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.racartech.app.rctandroidlts.R;

import java.util.Locale;

public class FunctionTwo extends AppCompatActivity{
    Button the_button;
    int hour, minute;
    int PICK_FILE_REQUEST_CODE = 200;
    ImageView image_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.function_two_activity_layout);
        the_button = findViewById(R.id.f2al_button);
        image_view = findViewById(R.id.f2al_imageview);

        the_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                //intent.setType("*/*");  //All
                intent.setType("image/*"); //Image
                //intent.setType("video/*"); //Video
                //intent.setType("audio/*"); //Audio
                startActivityForResult(intent, PICK_FILE_REQUEST_CODE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_FILE_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Uri selectedFileUri = data.getData();
            image_view.setImageURI(selectedFileUri);
        }
    }



    public void popTimePicker(){
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener()
        {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute)
            {
                hour = selectedHour;
                minute = selectedMinute;
                the_button.setText(String.format(Locale.getDefault(), "%02d:%02d",hour, minute));
            }
        };



        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                R.style.StyleTimePickerDialog,
                onTimeSetListener,
                hour,
                minute,
                false);


        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }



}


