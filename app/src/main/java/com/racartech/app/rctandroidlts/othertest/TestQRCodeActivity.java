package com.racartech.app.rctandroidlts.othertest;

import android.graphics.Bitmap;
import android.icu.util.TimeZone;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.racartech.app.rctandroidlts.R;
import com.racartech.library.rctandroid.code.RCTcodeQR;
import com.racartech.library.rctandroid.datatypes.RCTstring;
import com.racartech.library.rctandroid.file.RCTfile;
import com.racartech.library.rctandroid.media.RCTbitmap;
import com.racartech.library.rctandroid.security.RCTuuid;
import com.racartech.library.rctandroid.time.RCTdateTime;
import com.racartech.library.rctandroid.time.RCTdateTimeData;


public class TestQRCodeActivity extends AppCompatActivity {


    ImageButton display_imagebutton;
    Button generate_button;
    EditText qr_code_textbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_qr_code_activiy_layout);

        display_imagebutton = findViewById(R.id.tqcal_qr_display_imagebutton);
        generate_button = findViewById(R.id.tqcal_generate_button);
        qr_code_textbox = findViewById(R.id.tqcal_qr_textbox);

        generate_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                new Thread(new Runnable(){
                    @Override
                    public void run(){
                        //String text_to_generate = qr_code_textbox.getText().toString();
                        String text_to_generate = RCTstring.randomString(256,256);
                        System.out.println("-----------------------------------------------------------------");
                        System.out.println("Generated : ".concat(text_to_generate));
                        System.out.println("-----------------------------------------------------------------");
                        long start = System.currentTimeMillis();
                        Bitmap generated_qr_code =
                                RCTcodeQR.generateQRCode(
                                        text_to_generate,
                                        1000,
                                        1000);
                        long end = System.currentTimeMillis();
                        System.out.println("-----------------------------------------------------------------");
                        long elapsed_time = end-start;
                        System.out.println("Elapsed Time : ".concat(String.valueOf(elapsed_time)).concat(" ms"));
                        System.out.println("-----------------------------------------------------------------");

                        String timestamp = RCTdateTime.getTimestampYYYYMMDD(System.currentTimeMillis(), TimeZone.getDefault()
                        );
                        String save_path = RCTfile.getDir_ExternalStorageRoot().concat("/bpath/").concat(timestamp).concat(".png");
                        save_path = save_path.replace('-','_');
                        save_path = save_path.replace(' ','_');
                        save_path = save_path.replace(':','_');
                        System.out.println("-----------------------------------------------------------------");
                        System.out.println("Save Path : ".concat(save_path));
                        System.out.println("-----------------------------------------------------------------");

                        RCTbitmap.saveBitmapAsFile(generated_qr_code, save_path);

                        ////////////////////////////////////////////////////////////////////////////
                        ////////////////////////////////////////////////////////////////////////////
                        ////////////////////////////////////////////////////////////////////////////

                        Bitmap fetch_bitmap = RCTbitmap.getBitmapForFile(save_path);
                        String decoded_text = RCTcodeQR.decodeQRCode(fetch_bitmap);

                        System.out.println("-----------------------------------------------------------------");
                        if(decoded_text != null) {
                            System.out.println("Decoded   : ".concat(decoded_text));
                        }
                        System.out.println("-----------------------------------------------------------------");
                        boolean does_match = decoded_text.equals(text_to_generate);
                        System.out.println("Does Match : ".concat(String.valueOf(does_match)));
                        System.out.println("-----------------------------------------------------------------");




                            if(generated_qr_code != null){
                                runOnUiThread(new Runnable(){
                                    @Override
                                    public void run(){
                                        display_imagebutton.setImageBitmap(generated_qr_code);
                                    }
                                });
                            }
                    }
                }).start();

            }
        });



    }
}
