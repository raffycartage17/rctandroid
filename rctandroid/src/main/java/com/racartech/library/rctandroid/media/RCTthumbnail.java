package com.racartech.library.rctandroid.media;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.os.Build;
import android.provider.MediaStore;
import android.util.Size;

import com.racartech.library.rctandroid.file.RCTdirectory;
import com.racartech.library.rctandroid.file.RCTfile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class RCTthumbnail{

    public static int THUMBNAIL_MINIKIND_WIDTH = 512;
    public static int THUMBNAIL_MINIKIND_HEIGHT = 384;


    public static String[] THUMBNAILABLE_FILE_EXTENSIONS = {"jpg","jpeg","png","mp4"};

    public static void createAndSaveThumbnailAsFile(
            Context app_context,
            String thumbnailable_file_path,
            String generated_thumbnail_file_path,
            int thumbnail_width,
            int thumbnail_height){
        File thumbnail_file = new File(generated_thumbnail_file_path);
        File target_file = new File(thumbnailable_file_path);
        Bitmap thumb_bit = null;
        thumb_bit = getBitmapThumbnail(target_file,app_context,thumbnail_width,thumbnail_height);
        if(thumb_bit != null){
            RCTbitmap.saveBitmapAsFile(thumb_bit,thumbnail_file);
        } else {
            RCTfile.createFile(thumbnail_file);
        }
    }




    public static synchronized boolean doesDirectoryContainThumbnailableFiles(String[] file_paths){
        for(int index = 0; index< file_paths.length; index++){
            String current_path = file_paths[index];
            if(RCTfile.isPathAFile(current_path)){
                for(int i = 0; i < THUMBNAILABLE_FILE_EXTENSIONS.length; i++){
                    if(RCTfile.getFileExtension(current_path).equals(THUMBNAILABLE_FILE_EXTENSIONS[i])){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static synchronized boolean doesDirectoryContainThumbnailableFiles(File[] file_paths){
        for(int index = 0; index< file_paths.length; index++){
            String current_path = file_paths[index].getAbsolutePath();
            if(RCTfile.isPathAFile(current_path)){
                for(int i = 0; i < THUMBNAILABLE_FILE_EXTENSIONS.length; i++){
                    if(RCTfile.getFileExtension(current_path).equals(THUMBNAILABLE_FILE_EXTENSIONS[i])){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static synchronized boolean doesDirectoryContainThumbnailableFiles(ArrayList<String> file_paths){
        for(int index = 0; index< file_paths.size(); index++){
            String current_path = file_paths.get(index);
            if(RCTfile.isPathAFile(current_path)){
                for(int i = 0; i < THUMBNAILABLE_FILE_EXTENSIONS.length; i++){
                    if(RCTfile.getFileExtension(current_path).equals(THUMBNAILABLE_FILE_EXTENSIONS[i])){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static synchronized boolean doesDirectoryContainThumbnailableFiles_FileArrayList(ArrayList<File> file_paths){
        for(int index = 0; index< file_paths.size(); index++){
            String current_path = file_paths.get(index).getAbsolutePath();
            if(RCTfile.isPathAFile(current_path)){
                for(int i = 0; i < THUMBNAILABLE_FILE_EXTENSIONS.length; i++){
                    if(RCTfile.getFileExtension(current_path).equals(THUMBNAILABLE_FILE_EXTENSIONS[i])){
                        return true;
                    }
                }
            }
        }
        return false;
    }


    public static String getThumbnailFolder(String parent_dir){
        return parent_dir.concat("/.thumbnails");
    }
    public static void setUpThumbnailFolder(String parent_dir){
        String thumbnail_folder = parent_dir.concat("/.thumbnails");
        if(!RCTfile.doesDirectoryExist(thumbnail_folder)){
            RCTdirectory.createDirectory(parent_dir.concat("/.thumbnails"));
        }
    }

    public static boolean generateThumbnailForFile(String file_path){
        setUpThumbnailFolder(RCTfile.getParentDirectory(file_path));
        return true;
    }

    public static boolean generateThumbnailForFile(File the_file){
        setUpThumbnailFolder(RCTfile.getParentDirectory(the_file.getAbsolutePath()));
        return true;
    }

    public static void generateThumbnailForAllImageInDevice(Context app_context,String db_file_path){
        String[] target_dir = null;
        try{
            target_dir = RCTfile.readFile(db_file_path);
        }catch(IOException ignored){}
        if(target_dir != null){
            generateThumbnailsForThisFiles(
                    app_context,
                    target_dir,
                    THUMBNAIL_MINIKIND_WIDTH,
                    THUMBNAIL_MINIKIND_HEIGHT);
        }
    }

    public static void generateThumbnailForAllVideoInDevice(Context app_context,String db_file_path){
        String[] target_dir = null;
        try{
            target_dir = RCTfile.readFile(db_file_path);
        }catch(IOException ignored){}
        if(target_dir != null){
            generateThumbnailsForThisFiles(
                    app_context,
                    target_dir,
                    THUMBNAIL_MINIKIND_WIDTH,
                    THUMBNAIL_MINIKIND_HEIGHT);
        }
    }

    public static void generateThumbnailForAllImageInDevice(Context app_context,String[] target_dir){
        if(target_dir != null){
            generateThumbnailsForThisFiles(
                    app_context,
                    target_dir,
                    THUMBNAIL_MINIKIND_WIDTH,
                    THUMBNAIL_MINIKIND_HEIGHT);
        }
    }

    public static void generateThumbnailForAllVideoInDevice(Context app_context,String[] target_dir){
        if(target_dir != null){
            generateThumbnailsForThisFiles(
                    app_context,
                    target_dir,
                    THUMBNAIL_MINIKIND_WIDTH,
                    THUMBNAIL_MINIKIND_HEIGHT);
        }
    }

    public static synchronized void generateThumbnailsForThisFiles(Context app_context,
                                                                   String[] target_dir,
                                                                   int thumbnail_width,
                                                                   int thumbnail_height){
        for(int index = 0; index<target_dir.length; index++){
            String current_dir = target_dir[index];
            String[] current_immediate_filedir = RCTdirectory.getImmediateFileAndSubDir(current_dir);
            generateThumbnailsForThisFiles(
                    app_context,
                    current_dir,
                    current_immediate_filedir,
                    thumbnail_width,
                    thumbnail_height);
        }

    }

    public static synchronized boolean generateThumbnailsForThisFiles(
            Context app_context,
            String parent_dir,
            String[] file_list,
            int thumbnail_width,
            int thumbnail_height){

        boolean does_need_refresh = false;

        if(doesDirectoryContainThumbnailableFiles(file_list)){
            setUpThumbnailFolder(parent_dir);
            for(int index = 0; index < file_list.length; index++) {
                if (RCTfile.isPathAFile(file_list[index])){
                    if(shouldEvenCheck_ThumbnailExist(RCTfile.getFileExtension(file_list[index]).toLowerCase())) {
                        String current_file_thumbnail_file_path = getFile_ThumbnailFilePath(file_list[index]);
                        File thumbnail_file = new File(current_file_thumbnail_file_path);
                        File the_file = new File(file_list[index]);
                        if (!RCTfile.doesFileExist(thumbnail_file)) {
                            Bitmap thumb_bit = null;
                            thumb_bit = getBitmapThumbnail(the_file,app_context,thumbnail_width,thumbnail_height);
                            if(thumb_bit != null){
                                RCTbitmap.saveBitmapAsFile(thumb_bit, current_file_thumbnail_file_path);
                                does_need_refresh = true;
                            } else {
                                RCTfile.createFile(thumbnail_file);
                            }
                        }else{
                            long file_size_bytes = RCTfile.getSize(thumbnail_file);
                            if(file_size_bytes == 0){
                                Bitmap thumb_bit = null;
                                thumb_bit = getBitmapThumbnail(the_file,app_context,thumbnail_width,thumbnail_height);
                                if(thumb_bit != null){
                                    RCTbitmap.saveBitmapAsFile(thumb_bit, current_file_thumbnail_file_path);
                                    does_need_refresh = true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return does_need_refresh;
    }

    public static synchronized boolean generateThumbnailsForThisFiles(
            Context app_context,
            String parent_dir,
            ArrayList<String> file_list,
            int thumbnail_width,
            int thumbnail_height){

        boolean does_need_refresh = false;

        if(doesDirectoryContainThumbnailableFiles(file_list)){
            RCTthumbnail.setUpThumbnailFolder(parent_dir);
            for(int index = 0; index < file_list.size(); index++) {
                if (RCTfile.isPathAFile(file_list.get(index))){
                    if(shouldEvenCheck_ThumbnailExist(RCTfile.getFileExtension(file_list.get(index)).toLowerCase())) {
                        String current_file_thumbnail_file_path = getFile_ThumbnailFilePath(file_list.get(index));
                        File thumbnail_file = new File(current_file_thumbnail_file_path);
                        File the_file = new File(file_list.get(index));
                        if (!RCTfile.doesFileExist(thumbnail_file)) {
                            Bitmap thumb_bit = null;
                            thumb_bit = getBitmapThumbnail(the_file,app_context,thumbnail_width,thumbnail_height);
                            if(thumb_bit != null){
                                RCTbitmap.saveBitmapAsFile(thumb_bit, current_file_thumbnail_file_path);
                                does_need_refresh = true;
                            } else {
                                RCTfile.createFile(thumbnail_file);
                            }
                        }else{
                            long file_size_bytes = RCTfile.getSize(thumbnail_file);
                            if(file_size_bytes == 0){
                                Bitmap thumb_bit = null;
                                thumb_bit = getBitmapThumbnail(the_file,app_context,thumbnail_width,thumbnail_height);
                                if(thumb_bit != null){
                                    RCTbitmap.saveBitmapAsFile(thumb_bit, current_file_thumbnail_file_path);
                                    does_need_refresh = true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return does_need_refresh;
    }


    public static synchronized boolean generateThumbnailsForThisFiles_FileArrayList(
            Context app_context,
            String parent_dir,
            ArrayList<File> file_list,
            int thumbnail_width,
            int thumbnail_height){

        boolean does_need_refresh = false;

        if(doesDirectoryContainThumbnailableFiles_FileArrayList(file_list)){
            RCTthumbnail.setUpThumbnailFolder(parent_dir);
            for(int index = 0; index < file_list.size(); index++) {
                if (RCTfile.isPathAFile(file_list.get(index))){
                    if(shouldEvenCheck_ThumbnailExist(RCTfile.getFileExtension(file_list.get(index)).toLowerCase())) {
                        String current_file_thumbnail_file_path = getFile_ThumbnailFilePath(file_list.get(index).getAbsolutePath());
                        File thumbnail_file = new File(current_file_thumbnail_file_path);
                        File the_file = file_list.get(index);
                        if (!RCTfile.doesFileExist(thumbnail_file)) {
                            Bitmap thumb_bit = null;
                            thumb_bit = getBitmapThumbnail(the_file,app_context,thumbnail_width,thumbnail_height);
                            if(thumb_bit != null){
                                RCTbitmap.saveBitmapAsFile(thumb_bit, current_file_thumbnail_file_path);
                                does_need_refresh = true;
                            } else {
                                RCTfile.createFile(thumbnail_file);
                            }
                        }else{
                            long file_size_bytes = RCTfile.getSize(thumbnail_file);
                            if(file_size_bytes == 0){
                                Bitmap thumb_bit = null;
                                thumb_bit = getBitmapThumbnail(the_file,app_context,thumbnail_width,thumbnail_height);
                                if(thumb_bit != null){
                                    RCTbitmap.saveBitmapAsFile(thumb_bit, current_file_thumbnail_file_path);
                                    does_need_refresh = true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return does_need_refresh;
    }




    public static synchronized Bitmap getThumbnail_Image_CropCenter(
            File the_file,
            Context app_context,
            int height,
            int width){

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            // Use BitmapFactory to decode the file into a Bitmap
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(the_file.getAbsolutePath(), options);
            options.inSampleSize = calculateInSampleSize(options, width, height);
            options.inJustDecodeBounds = false;
            Bitmap the_bitmap = BitmapFactory.decodeFile(the_file.getAbsolutePath(), options);

            // Crop the bitmap to center square
            try {
                int dimension = getSquareCropDimensionForBitmap(the_bitmap);
                return ThumbnailUtils.extractThumbnail(the_bitmap, dimension, dimension);
            }catch(NullPointerException ignored){
                return null;
            }

        }else{
            // Use loadThumbnail() to get a thumbnail of the file
            Bitmap the_bitmap = null;
            try{
                the_bitmap = app_context.getContentResolver().loadThumbnail(
                        RCTfile.getUriForFile(app_context,the_file),
                        new Size(width,height),
                        null);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Crop the bitmap to center square
            try {
                int dimension = getSquareCropDimensionForBitmap(Objects.requireNonNull(the_bitmap));
                return ThumbnailUtils.extractThumbnail(the_bitmap, dimension, dimension);
            }catch(NullPointerException ignored){
                return null;
            }
        }
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = Math.min(heightRatio, widthRatio);
        }

        return inSampleSize;
    }



    public static synchronized Bitmap getThumbnail_Video_CropCenter(File the_file, Context app_context, int t_width, int t_height){

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            Bitmap the_bitmap = ThumbnailUtils.createVideoThumbnail(the_file.getAbsolutePath(), MediaStore.Video.Thumbnails.MINI_KIND);
            if(the_bitmap == null) {
                return null;
            }
            int dimension = getSquareCropDimensionForBitmap(the_bitmap);
            return ThumbnailUtils.extractThumbnail(the_bitmap, dimension, dimension);
        }else{

            Size the_size = new Size(t_width,t_height);
            Bitmap the_bitmap = null;
            try {
                the_bitmap = ThumbnailUtils.createVideoThumbnail(the_file, the_size, null);
            }catch(IOException ignored){}
            int dimension = 0;
            try {
                dimension = getSquareCropDimensionForBitmap(Objects.requireNonNull(the_bitmap));
            }catch(NullPointerException ignored){
                return null;
            }
            return ThumbnailUtils.extractThumbnail(the_bitmap, dimension, dimension);
        }
    }




    private static int getSquareCropDimensionForBitmap(Bitmap bitmap){
        return Math.min(bitmap.getWidth(), bitmap.getHeight());
    }

    private static boolean shouldEvenCheck_ThumbnailExist(String file_extension){
        boolean should_check;
        switch (file_extension) {
            case "png":
            case "jpeg":
            case "jpg":
            case "tiff":
            case "raw":
            case "mp4":
            case "mov":
            case "wmv":
            case "avi":
            case "flv":
            case "mkv":
            case "webm":
                should_check = true;
                break;
            default:
                should_check = false;
                break;
        }
        return should_check;
    }

    private static Bitmap getBitmapThumbnail(
            File thumbnail_file,
            Context app_context,
            int thumbnail_width,
            int thumbnail_height){

        String file_extension = RCTfile.getFileExtension(thumbnail_file);
        Bitmap thumb_bit = null;
        switch (file_extension) {
            case "png":
            case "jpeg":
            case "jpg":
            case "tiff":
            case "raw":
                thumb_bit = RCTthumbnail.getThumbnail_Image_CropCenter(thumbnail_file, app_context,thumbnail_height,thumbnail_width);
                break;
            case "mp4":
            case "mov":
            case "wmv":
            case "avi":
            case "flv":
            case "mkv":
            case "webm":
                thumb_bit = RCTthumbnail.getThumbnail_Video_CropCenter(thumbnail_file, app_context, THUMBNAIL_MINIKIND_WIDTH, THUMBNAIL_MINIKIND_HEIGHT);
                break;
        }
        return thumb_bit;
    }


    public static String getFile_ThumbnailFilePath(String file_path){
        String thumbnail_folder = RCTthumbnail.getThumbnailFolder(RCTfile.getParentDirectory(file_path));
        return thumbnail_folder.concat("/").concat(RCTfile.getFileName(file_path)).concat(".rcthumb");
    }




}
