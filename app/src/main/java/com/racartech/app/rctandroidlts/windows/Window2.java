package com.racartech.app.rctandroidlts.windows;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.racartech.app.rctandroidlts.R;
import com.racartech.library.rctandroid.file.RCTfile;
import com.racartech.library.rctandroid.media.RCTbitmap;
import com.racartech.library.rctandroid.transcoding.RCTbase64;

import java.io.IOException;

public class Window2 extends AppCompatActivity {


    private ImageView image_view;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window2);

        image_view = findViewById(R.id.window2_imageview);
        button = findViewById(R.id.window2_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                new Thread(new Runnable(){
                    @Override
                    public void run(){


                        long start = System.currentTimeMillis();

                        String file_test_path = RCTfile.getDir_ExternalStorageRoot().concat("/apath/test.pdf");

                        String base_64_string = null;
                        try{
                            base_64_string = RCTbase64.fileToBase64(file_test_path);
                        }catch(IOException ignored){}
                        Bitmap second_bitmap = RCTbase64.base64ToBitmap(base_64_string);

                        String copied_file_path = RCTfile.getDir_ExternalStorageRoot().concat("/bpath/copied_test.pdf");

                        try{
                            RCTbase64.base64ToFile(base_64_string,copied_file_path);
                        }catch(IOException ignored){}

                        runOnUiThread(new Runnable(){
                            @Override
                            public void run(){
                                long end = System.currentTimeMillis();
                                long elapsed_time = end-start;
                                System.out.println("-------------------------------------");
                                System.out.println("Elapsed Time : ".concat(String.valueOf(elapsed_time)));
                                System.out.println("-------------------------------------");
                            }
                        });
                    }
                }).start();





            }
        });


    }
}
