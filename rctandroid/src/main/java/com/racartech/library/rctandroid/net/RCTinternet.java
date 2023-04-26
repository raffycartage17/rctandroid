package com.racartech.library.rctandroid.net;

import android.graphics.Bitmap;

import com.racartech.library.rctandroid.media.RCTbitmap;

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


}
