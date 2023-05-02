package com.racartech.library.rctandroid.net;

import android.graphics.Bitmap;

import com.racartech.library.rctandroid.media.RCTbitmap;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

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

    public static boolean downloadFile(URL url, String saveToFilepath) {
        try (BufferedInputStream in = new BufferedInputStream(url.openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(saveToFilepath)) {
            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
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


}
