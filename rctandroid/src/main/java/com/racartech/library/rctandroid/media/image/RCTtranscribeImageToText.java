package com.racartech.library.rctandroid.media.image;
import android.content.Context;
import android.graphics.Bitmap;

import com.googlecode.tesseract.android.TessBaseAPI;
import com.racartech.library.rctandroid.file.RCTfile;
import com.racartech.library.rctandroid.net.RCTinternet;

import java.io.File;

public class RCTtranscribeImageToText {
    private TessBaseAPI tessBaseAPI;

    public final static String LANGUAGE_ENGLISH = "eng";

    public RCTtranscribeImageToText(Context context, String download_url, String language) {
        tessBaseAPI = new TessBaseAPI();
        String model_root_directory = context.getFilesDir().getAbsolutePath().concat("/tesseract/");

        // Create the tessdata folder if it doesn't exist
        File directory = new File(model_root_directory.concat("tessdata/"));
        if (!directory.exists()) {
            directory.mkdirs();
        }

        String model_file_path = directory.getAbsolutePath().concat("/").concat(language).concat(".traineddata");
        if(!RCTfile.doesFileExist(model_file_path)){
            RCTinternet.downloadFile(download_url,model_file_path,1048576);
        }

        // Initialize Tesseract with the data path and language
        tessBaseAPI.init(model_root_directory, language);
    }


    /**
     * <h2>Description</h2>
     * Root directory should contain a sub directory named "tessdata", and the trained model should be inside root_directory+tessdata
     */

    public RCTtranscribeImageToText(
            String download_url,
            String language,
            String root_directory
    ){
        tessBaseAPI = new TessBaseAPI();

        // Create the tessdata folder if it doesn't exist
        File directory = new File(root_directory.concat("/").concat("tessdata/"));
        if (!directory.exists()) {
            directory.mkdirs();
        }

        String model_file_path = directory.getAbsolutePath().concat("/").concat(language).concat(".traineddata");
        if(!RCTfile.doesFileExist(model_file_path)){
            RCTinternet.downloadFile(download_url,model_file_path,1048576);
        }

        // Initialize Tesseract with the data path and language
        tessBaseAPI.init(root_directory, language);

    }




    public String getTextFromBitmap(Bitmap bitmap) {
        tessBaseAPI.setImage(bitmap);
        return tessBaseAPI.getUTF8Text();
    }

    public void close() {
        if (tessBaseAPI != null) {
            tessBaseAPI.clear();
            tessBaseAPI.recycle();
        }
    }
}
