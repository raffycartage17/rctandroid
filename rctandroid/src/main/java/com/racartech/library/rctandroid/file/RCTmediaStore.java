package com.racartech.library.rctandroid.file;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.File;
import java.util.ArrayList;

public class RCTmediaStore{








    public static ArrayList<String> getImageDirectories(Context mContext) {
        ArrayList<String> directories = new ArrayList<>();

        ContentResolver contentResolver = mContext.getContentResolver();
        Uri queryUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;


        String[] projection = new String[]{
                MediaStore.Images.Media.DATA
        };

        String includeImages = MediaStore.Images.Media.MIME_TYPE + " LIKE 'image/%' ";
        String excludeGif =  " AND " + MediaStore.Images.Media.MIME_TYPE + " != 'image/gif' " + " AND " + MediaStore.Images.Media.MIME_TYPE + " != 'image/giff' ";
        String selection =  includeImages + excludeGif;

        Cursor cursor = contentResolver.query(queryUri, projection, selection, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {

                @SuppressLint("Range") String photoUri = cursor.getString(cursor.getColumnIndex(projection[0]));
                if(!directories.contains(new File(photoUri).getParent())){
                    directories.add(new File(photoUri).getParent());
                }

            } while (cursor.moveToNext());
        }
        return directories;
    }

    public static ArrayList<String> getVideoDirectories(Context mContext) {
        ArrayList<String> directories = new ArrayList<>();

        ContentResolver contentResolver = mContext.getContentResolver();
        Uri queryUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;


        String[] projection = new String[]{
                MediaStore.Video.Media.DATA
        };

        String includeImages = MediaStore.Video.Media.MIME_TYPE + " LIKE 'video/%' ";
        //String excludeGif =  " AND " + MediaStore.Video.Media.MIME_TYPE + " != 'image/gif' " + " AND " + MediaStore.Images.Media.MIME_TYPE + " != 'image/giff' ";
        String selection =  includeImages;

        Cursor cursor = contentResolver.query(queryUri, projection, selection, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {

                @SuppressLint("Range") String photoUri = cursor.getString(cursor.getColumnIndex(projection[0]));
                if(!directories.contains(new File(photoUri).getParent())){
                    directories.add(new File(photoUri).getParent());
                }

            } while (cursor.moveToNext());
        }
        return directories;
    }

    public static ArrayList<String> getAudioDirectories(Context mContext) {
        ArrayList<String> directories = new ArrayList<>();

        ContentResolver contentResolver = mContext.getContentResolver();
        Uri queryUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;


        String[] projection = new String[]{
                MediaStore.Audio.Media.DATA
        };

        String includeImages = MediaStore.Audio.Media.MIME_TYPE + " LIKE 'audio/%' ";
        //String excludeGif =  " AND " + MediaStore.Video.Media.MIME_TYPE + " != 'image/gif' " + " AND " + MediaStore.Images.Media.MIME_TYPE + " != 'image/giff' ";
        String selection =  includeImages;

        Cursor cursor = contentResolver.query(queryUri, projection, selection, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {

                @SuppressLint("Range") String photoUri = cursor.getString(cursor.getColumnIndex(projection[0]));
                if(!directories.contains(new File(photoUri).getParent())){
                    directories.add(new File(photoUri).getParent());
                }

            } while (cursor.moveToNext());
        }
        return directories;
    }

    public static void reScanMediaStore(Context app_context,String the_path){
        //Nothing for now
    }
    public static void reScanMediaStore(Context app_context){
        //Nothing for now
    }

    public static ArrayList<String> reScanAndGetImageDirectories(Context app_context){
        reScanMediaStore(app_context);
        return getImageDirectories(app_context);
    }


}
