package com.racartech.library.rctandroid.media;

import static com.racartech.library.rctandroid.file.RCTfile.imageFileToBase64;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


import java.io.ByteArrayOutputStream;
import java.util.Base64;

public class RCTbase64 {

    public static byte[] base64ToByteArray(String base64String) {
        return Base64.getDecoder().decode(base64String);
    }

    public static String byteArrayToBase64(byte[] byteArray) {
        return Base64.getEncoder().encodeToString(byteArray);
    }

    public static Bitmap toBitmap(String base64) {
        byte[] decodedBytes = android.util.Base64.decode(base64, android.util.Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);

    }

    public static String compressBase64Image(String base64Image, int quality) {
        try {
            byte[] decodedBytes = android.util.Base64.decode(base64Image, android.util.Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
            byte[] compressedBytes = outputStream.toByteArray();
            return android.util.Base64.encodeToString(compressedBytes, android.util.Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
