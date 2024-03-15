package com.racartech.library.rctandroid.snippets;

import android.content.Context;
import android.widget.Toast;

public class SnippetFilePicker {


    public static void test(Context context){
        Toast.makeText(context, "Android Basic", Toast.LENGTH_SHORT).show();
        /*
    NOTE : this is a snippet class intended to help developers to fasten there production
           by providing them easy to use code common code snippets
        */
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
