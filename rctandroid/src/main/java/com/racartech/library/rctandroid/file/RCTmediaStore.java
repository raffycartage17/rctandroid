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

        try (Cursor cursor = contentResolver.query(queryUri, projection, selection, null, null)) {
            if (cursor != null) {
                int columnIndex = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
                while (cursor.moveToNext()) {
                    String photoUri = cursor.getString(columnIndex);
                    if (!directories.contains(new File(photoUri).getParent())) {
                        directories.add(new File(photoUri).getParent());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return directories;
    }

    public static ArrayList<String> getDocumentDirectories(Context mContext) {
        ArrayList<String> directories = new ArrayList<>();

        ContentResolver contentResolver = mContext.getContentResolver();
        Uri queryUri = MediaStore.Files.getContentUri("external");

        String[] projection = new String[]{
                MediaStore.Files.FileColumns.DATA
        };

        String includeDocuments = MediaStore.Files.FileColumns.MIME_TYPE + " LIKE 'application/%' OR " + MediaStore.Files.FileColumns.MIME_TYPE + " LIKE 'text/%' ";
        String selection =  includeDocuments;

        try (Cursor cursor = contentResolver.query(queryUri, projection, selection, null, null)) {
            if (cursor != null) {
                int columnIndex = cursor.getColumnIndex(MediaStore.Files.FileColumns.DATA);
                while (cursor.moveToNext()) {
                    String documentUri = cursor.getString(columnIndex);
                    if (!directories.contains(new File(documentUri).getParent())) {
                        directories.add(new File(documentUri).getParent());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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

        String includeVideos = MediaStore.Video.Media.MIME_TYPE + " LIKE 'video/%' ";
        String selection = includeVideos;

        try (Cursor cursor = contentResolver.query(queryUri, projection, selection, null, null)) {
            if (cursor != null) {
                int columnIndex = cursor.getColumnIndex(MediaStore.Video.Media.DATA);
                while (cursor.moveToNext()) {
                    String videoUri = cursor.getString(columnIndex);
                    if (!directories.contains(new File(videoUri).getParent())) {
                        directories.add(new File(videoUri).getParent());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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

        String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";

        try (Cursor cursor = contentResolver.query(queryUri, projection, selection, null, null)) {
            if (cursor != null && cursor.moveToFirst()) {
                int dataIndex = cursor.getColumnIndex(MediaStore.Audio.Media.DATA);
                do {
                    String path = cursor.getString(dataIndex);
                    if (path != null) {
                        File parent = new File(path).getParentFile();
                        if (parent != null) {
                            String parentPath = parent.getAbsolutePath();
                            if (!directories.contains(parentPath)) {
                                directories.add(parentPath);
                            }
                        }
                    }
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
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
