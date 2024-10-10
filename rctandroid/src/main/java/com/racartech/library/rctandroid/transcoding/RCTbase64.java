package com.racartech.library.rctandroid.transcoding;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Base64;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;

public class RCTbase64{



    ////////////////////////////////////////////////////

    public static Bitmap base64ToBitmap(String base64String){
        byte[] decodedString = Base64.decode(base64String, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }

    public static Bitmap base64ToBitmap(String base64String, int base64_flag){
        byte[] decodedString = Base64.decode(base64String, base64_flag);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }


    ////////////////////////////////////////////////////

    public static String bitmapToBase64(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }

    public static String bitmapToBase64(
            Bitmap bitmap,
            Bitmap.CompressFormat compress_format,
            int quality,
            int base64_flag) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(compress_format, quality, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(byteArray, base64_flag);
    }



    ////////////////////////////////////////////////////

    public static String fileToBase64(String file_path) throws IOException {
        File file = new File(file_path);
        FileInputStream file_input_stream = new FileInputStream(file);
        byte[] fileBytes = new byte[(int) file.length()];
        file_input_stream.read(fileBytes);
        file_input_stream.close();
        return Base64.encodeToString(fileBytes, Base64.DEFAULT);
    }

    public static String fileToBase64(String file_path, int base64_flag) throws IOException {
        File file = new File(file_path);
        FileInputStream file_input_stream = new FileInputStream(file);
        byte[] fileBytes = new byte[(int) file.length()];
        file_input_stream.read(fileBytes);
        file_input_stream.close();
        return Base64.encodeToString(fileBytes, base64_flag);
    }

    ////////////////////////////////////////////////////


    public static String fileToBase64(URI file_path) throws IOException {
        File file = new File(file_path);
        FileInputStream file_input_stream = new FileInputStream(file);
        byte[] fileBytes = new byte[(int) file.length()];
        file_input_stream.read(fileBytes);
        file_input_stream.close();
        return Base64.encodeToString(fileBytes, Base64.DEFAULT);
    }

    public static String fileToBase64(URI file_path, int base64_flag) throws IOException {
        File file = new File(file_path);
        FileInputStream file_input_stream = new FileInputStream(file);
        byte[] fileBytes = new byte[(int) file.length()];
        file_input_stream.read(fileBytes);
        file_input_stream.close();
        return Base64.encodeToString(fileBytes, base64_flag);
    }

    ////////////////////////////////////////////////////

    public static void base64ToFile(String base64_string, String file_path) throws IOException {
        byte[] file_bytes = Base64.decode(base64_string, Base64.DEFAULT);

        FileOutputStream file_output_stream = new FileOutputStream(file_path);
        file_output_stream.write(file_bytes);
        file_output_stream.close();
    }

    public static void base64ToFile(String base64_string, String file_path, int base64_flag) throws IOException {
        byte[] file_bytes = Base64.decode(base64_string, base64_flag);

        FileOutputStream file_output_stream = new FileOutputStream(file_path);
        file_output_stream.write(file_bytes);
        file_output_stream.close();
    }






}
