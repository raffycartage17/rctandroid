package com.racartech.library.rctandroid.media;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import com.racartech.library.rctandroid.file.RCTdirectory;
import com.racartech.library.rctandroid.file.RCTfile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class RCTbitmap{


    public static Bitmap getBitmapForURI(Context context,Uri the_uri){
        try {
            return MediaStore.Images.Media.getBitmap(context.getContentResolver(), the_uri);
        }catch (IOException ignored){
            return null;
        }
    }


    public static Bitmap getBitmapForURL(URL image_url) {
        Bitmap bitmap = null;
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) image_url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            bitmap = BitmapFactory.decodeStream(input);
        } catch (IOException e) {
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return bitmap;
    }

    public static Bitmap getBitmapForURL(String image_url_string) {
        Bitmap bitmap = null;
        HttpURLConnection connection = null;
        try {
            URL image_url = new URL(image_url_string);
            connection = (HttpURLConnection) image_url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            bitmap = BitmapFactory.decodeStream(input);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return bitmap;
    }


    public static void saveBitmapAsFile(Bitmap bitmap, String target_new_bitmap_file_path) {
        if (isExternalStorageWritable()) {
            saveImage(bitmap,target_new_bitmap_file_path);
        }else{
            //prompt the user or do something
        }
    }

    public static void saveBitmapAsFile(Bitmap bitmap, File bitmap_file_path) {
        if (isExternalStorageWritable()) {
            saveImage(bitmap,bitmap_file_path.getAbsolutePath());
        }else{
            //prompt the user or do something
        }
    }

    public static Bitmap getBitmapForFile(String file_path){
        return BitmapFactory.decodeFile(file_path);
    }

    public static Bitmap getBitmapForFile(File file_path){
        return BitmapFactory.decodeFile(file_path.getAbsolutePath());
    }
    public static Drawable convertToDrawable(Bitmap the_bitmap, Context app_context){
        Drawable d = new BitmapDrawable(app_context.getResources(), the_bitmap);
        return  d;
    }
    public static Bitmap resize(Bitmap the_bitmap,int width, int height){
        return Bitmap.createScaledBitmap(the_bitmap, width, height, false);
    }
    private static void saveImage(Bitmap finalBitmap,String target_new_bitmap_file_path) {
        String tnbfp_parent_dir = RCTfile.getParentDirectory(target_new_bitmap_file_path);
        if(!RCTfile.doesDirectoryExist(tnbfp_parent_dir)){
            RCTdirectory.createDirectory(tnbfp_parent_dir);
        }
        File file = new File(target_new_bitmap_file_path);
        if (file.exists()) file.delete ();
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* Checks if external storage is available for read and write */
    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }
}
