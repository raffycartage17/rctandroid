package com.racartech.library.rctandroid.net;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.racartech.library.rctandroid.media.RCTbitmap;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class RCTinternet{


    public static boolean saveImageAsFile(URL image_url, String file_path){
        try {
            Bitmap image_bitmap = RCTbitmap.getBitmapForURL(image_url);
            RCTbitmap.saveBitmapAsFile(image_bitmap, file_path);
            return true;
        }catch(Exception ignored){
            return false;
        }
    }

    public static boolean saveImageAsFile(String image_url_string, String file_path){
        try {
            Bitmap image_bitmap = RCTbitmap.getBitmapForURL(new URL(image_url_string));
            RCTbitmap.saveBitmapAsFile(image_bitmap, file_path);
            return true;
        }catch(Exception ignored){
            return false;
        }
    }



    /*

    public static boolean downloadFile(URL url, String saveToFilepath) {
        try (BufferedInputStream in = new BufferedInputStream(url.openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(saveToFilepath)) {
            byte dataBuffer[] = new byte[8192];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 8192)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static boolean downloadFile(String urlString, String saveToFilepath) {
        try {
            URL url = new URL(urlString);
            BufferedInputStream in = new BufferedInputStream(url.openStream());
            FileOutputStream fileOutputStream = new FileOutputStream(saveToFilepath);
            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
            in.close();
            fileOutputStream.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

     */


    public static boolean downloadFile(String download_url, String save_to_file_path) {
        try {
            URL url = new URL(download_url);
            URLConnection connection = url.openConnection();
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);

            try (InputStream in = connection.getInputStream();
                 FileOutputStream out = new FileOutputStream(save_to_file_path)) {

                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
            }

            return true; // Successfully downloaded and saved the file.
        } catch (IOException e) {
            e.printStackTrace();
            return false; // Failed to download or save the file.
        }
    }

    public static boolean downloadFile(URL download_url, String save_to_file_path) {
        try {
            URLConnection connection = download_url.openConnection();
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);

            try (InputStream in = connection.getInputStream();
                 FileOutputStream out = new FileOutputStream(save_to_file_path)) {

                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
            }

            return true; // Successfully downloaded and saved the file.
        } catch (IOException e) {
            e.printStackTrace();
            return false; // Failed to download or save the file.
        }
    }


    public static boolean downloadFile(String download_url, String save_to_file_path, int connectionTimeout, int readTimeout) {
        try {
            URL url = new URL(download_url);
            URLConnection connection = url.openConnection();
            connection.setConnectTimeout(connectionTimeout);
            connection.setReadTimeout(readTimeout);

            try (InputStream in = connection.getInputStream();
                 FileOutputStream out = new FileOutputStream(save_to_file_path)) {

                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
            }

            return true; // Successfully downloaded and saved the file.
        } catch (IOException e) {
            e.printStackTrace();
            return false; // Failed to download or save the file.
        }
    }

    public static boolean downloadFile(URL download_url, String save_to_file_path, int connectionTimeout, int readTimeout) {
        try {
            URLConnection connection = download_url.openConnection();
            connection.setConnectTimeout(connectionTimeout);
            connection.setReadTimeout(readTimeout);

            try (InputStream in = connection.getInputStream();
                 FileOutputStream out = new FileOutputStream(save_to_file_path)) {

                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
            }

            return true; // Successfully downloaded and saved the file.
        } catch (IOException e) {
            e.printStackTrace();
            return false; // Failed to download or save the file.
        }
    }






    public static void downloadFile(String[] url_strings, String[] save_to_file_paths){
        if(save_to_file_paths.length >= url_strings.length){
            for(int index = 0; index< url_strings.length; index++){
                downloadFile(url_strings[index],save_to_file_paths[index]);
            }
        }
    }

    public static void downloadFile(URL[] urls, String[] save_to_file_paths){
        if(save_to_file_paths.length >= urls.length){
            for(int index = 0; index< urls.length; index++){
                downloadFile(urls[index],save_to_file_paths[index]);
            }
        }
    }



    public static void downloadFile_ArrayListString(ArrayList<String> url_strings, ArrayList<String> save_to_file_paths){
        if(save_to_file_paths.size() >= url_strings.size()){
            for(int index = 0; index< url_strings.size(); index++){
                downloadFile(url_strings.get(index),save_to_file_paths.get(index));
            }
        }
    }

    public static void downloadFile_ArrayListURL(ArrayList<URL> urls, ArrayList<String> save_to_file_paths){
        if(save_to_file_paths.size() >= urls.size()){
            for(int index = 0; index< urls.size(); index++){
                downloadFile(urls.get(index),save_to_file_paths.get(index));
            }
        }
    }


    public static boolean isInternetConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isConnected();
        }
        return false;
    }







}
