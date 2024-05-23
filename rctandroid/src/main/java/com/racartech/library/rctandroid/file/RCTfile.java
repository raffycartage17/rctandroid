package com.racartech.library.rctandroid.file;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.webkit.MimeTypeMap;

import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.racartech.library.rctandroid.array.RCTarray;
import com.racartech.library.rctandroid.math.RCTdataSizeConverter;
import com.racartech.library.rctandroid.notifications.RCTnotifications;

import org.apache.commons.io.FileUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UncheckedIOException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

public class RCTfile{

    public final static int RELATIVE_DEVICE_STORAGE = 0;
    public final static int RELATIVE_SDCARD_STORAGE = 1;



    public final static int FILE_TYPE_IMAGE = 0;
    public final static int FILE_TYPE_VIDEO = 1;
    public final static int FILE_TYPE_AUDIO = 2;
    public final static int FILE_TYPE_DOCUMENT = 3;



    public static int getFileType(String file_path){
        if(isFile_Image(file_path)){
            return FILE_TYPE_IMAGE;
        }
        if(isFile_Video(file_path)){
            return FILE_TYPE_VIDEO;
        }
        if(isFile_Audio(file_path)){
            return FILE_TYPE_AUDIO;
        }
        if(isFile_Document(file_path)){
            return FILE_TYPE_DOCUMENT;
        }
        return -1;
    }


    /* Snippet

        try{
            RCTfile.readLine(new RCTfile.ReadLineCallback(){
                @Override
                public String getFilePath(){
                    return file_path;
                }

                @Override
                public void getLineData(int index, String line_data){
                }

            });
        }catch(IOException ignored){}

     */


    /**
     * <h2>Description</h2>
     * Finds if a word or char sequence is present inside the file.
     * <br>
     * Return >=0 = exist, index of the line
     * <br>
     * Return -1 = Not exist
     * @author Rafael Andaya Cartagena
     * @since 07-30-2023
     */
    public static int doesContainCharSequence_FirstAppearance(String file_path, String char_sequence) throws IOException{
        ArrayList<String> file_contents = readFile_ArrayList(file_path);
        for(int index = 0; index<file_contents.size(); index++){
            if(file_contents.get(index).contains(char_sequence)){
                return index;
            }
        }
        return -1;
    }


    public static void createFile(String directory, ArrayList<String> file_names){
        for (int index = 0; index < file_names.size(); index++) {
            createFile(directory.concat("/").concat(file_names.get(index)));
        }
    }


    public static boolean createAndOverride(String file_path,String[] file_contents) throws IOException {
        RCTfile.createFile(file_path);
        return RCTfile.overrideFile(file_path, file_contents);
    }

    public static boolean createAndOverride(String file_path,ArrayList<String> file_contents) throws IOException {
        RCTfile.createFile(file_path);
        return RCTfile.overrideFile(file_path, file_contents);
    }

    public static boolean createAndOverride(String file_path,List<String> file_contents) throws IOException {
        RCTfile.createFile(file_path);
        return RCTfile.overrideFile(file_path, file_contents);
    }

    public static boolean createAndOverride(String file_path,String file_content) throws IOException {
        RCTfile.createFile(file_path);
        return RCTfile.overrideFile(file_path,new String[]{file_content});
    }





    public static boolean overrideFile(String file_path,String file_contents) throws IOException{
        return overrideFile(file_path,new String[]{file_contents});
    }

    public static boolean overrideFile(File file_path,String file_contents) throws IOException{
        return overrideFile(file_path.getAbsolutePath(),new String[]{file_contents});
    }




    public static ArrayList<File> convert_FileArrayList(ArrayList<String> file_paths, boolean check_if_exist){
        ArrayList<File> the_files = new ArrayList<>();
        if(check_if_exist){
            for(int index = 0; index<file_paths.size(); index++){
                File current_file = new File(file_paths.get(index));
                if(RCTfile.doesFileExist(current_file)) {
                    the_files.add(current_file);
                }
            }
        }else{
            for(int index = 0; index<file_paths.size(); index++){
                the_files.add(new File(file_paths.get(index)));
            }
        }
        return the_files;
    }



    public static ArrayList<File> convert_FileArrayList(List<String> file_paths, boolean check_if_exist){
        ArrayList<File> the_files = new ArrayList<>();
        if(check_if_exist){
            for(int index = 0; index<file_paths.size(); index++){
                File current_file = new File(file_paths.get(index));
                if(RCTfile.doesFileExist(current_file)) {
                    the_files.add(current_file);
                }
            }
        }else{
            for(int index = 0; index<file_paths.size(); index++){
                the_files.add(new File(file_paths.get(index)));
            }
        }
        return the_files;
    }

    public static ArrayList<File> convert_FileArrayList(String[] file_paths, boolean check_if_exist){
        ArrayList<File> the_files = new ArrayList<>();
        if(check_if_exist){
            for(int index = 0; index<file_paths.length; index++){
                File current_file = new File(file_paths[index]);
                if(RCTfile.doesFileExist(current_file)) {
                    the_files.add(current_file);
                }
            }
        }else{
            for(int index = 0; index< file_paths.length; index++){
                the_files.add(new File(file_paths[index]));
            }
        }
        return the_files;
    }



    public static ArrayList<String> convert_StringArrayList(ArrayList<File> files, boolean check_if_exist){
        ArrayList<String> the_files = new ArrayList<>();
        if(check_if_exist){
            for(int index = 0; index<files.size(); index++){
                if(RCTfile.doesFileExist(files.get(index))){
                    the_files.add(files.get(index).getAbsolutePath());
                }
            }
        }else{
            for(int index = 0; index<files.size(); index++){
                the_files.add(files.get(index).getAbsolutePath());
            }
        }
        return the_files;
    }

    public static ArrayList<String> convert_StringArrayList(List<File> files, boolean check_if_exist){
        ArrayList<String> the_files = new ArrayList<>();
        if(check_if_exist){
            for(int index = 0; index<files.size(); index++){
                if(RCTfile.doesFileExist(files.get(index))){
                    the_files.add(files.get(index).getAbsolutePath());
                }
            }
        }else{
            for(int index = 0; index<files.size(); index++){
                the_files.add(files.get(index).getAbsolutePath());
            }
        }
        return the_files;
    }

    public static ArrayList<String> convert_StringArrayList(File[] files, boolean check_if_exist){
        ArrayList<String> the_files = new ArrayList<>();
        if(check_if_exist){
            for(int index = 0; index<files.length; index++){
                if(RCTfile.doesFileExist(files[index])){
                    the_files.add(files[index].getAbsolutePath());
                }
            }
        }else{
            for(int index = 0; index<files.length; index++){
                the_files.add(files[index].getAbsolutePath());
            }
        }
        return the_files;
    }



    public static void createFile_String(ArrayList<String> files){
        for(int index = 0; index<files.size(); index++){
            RCTfile.createFile(files.get(index));
        }
    }

    public static void createFile_File(ArrayList<File> files){
        for(int index = 0; index<files.size(); index++){
            RCTfile.createFile(files.get(index));
        }
    }

    public static void createFile_String(String[] files){
        for(int index = 0; index<files.length; index++){
            RCTfile.createFile(files[index]);
        }
    }

    public static void createFile_File(File[] files){
        for(int index = 0; index<files.length; index++){
            RCTfile.createFile(files[index]);
        }
    }



    public static void openFile(Context app_context,String title,File the_file){
        //TODO - View multiple files instead of 1 only
        Intent file_intent = new Intent(Intent.ACTION_VIEW);
        file_intent.setAction(Intent.ACTION_VIEW);
        file_intent.setDataAndType(RCTfile.getUriForFile(app_context,the_file), RCTfile.getMimeType(the_file));
        file_intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        file_intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        file_intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        Intent open_file_intent = Intent.createChooser(file_intent,title);
        open_file_intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try{
            app_context.startActivity(open_file_intent);
        } catch (ActivityNotFoundException e) {
            RCTnotifications.makeToast_SHORT(app_context,"No app can open this type of file");
        }
    }



    public static String getAbsolutePathFromURI(Context context, Uri uri) {
        String filePath = "";
        if (DocumentsContract.isDocumentUri(context, uri)) {
            // For Android 4.4 and above
            String documentId = DocumentsContract.getDocumentId(uri);
            String[] split = documentId.split(":");
            String type = split[0];

            if ("image".equals(type)) {
                Uri contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                String selection = MediaStore.Images.Media._ID + "=?";
                String[] selectionArgs = new String[] { split[1] };

                filePath = getDataColumn(context, contentUri, selection, selectionArgs);
            }
        } else {
            // For older versions of Android
            String[] projection = { MediaStore.Images.Media.DATA };
            Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);
            if (cursor != null) {
                int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                if (cursor.moveToFirst()) {
                    filePath = cursor.getString(columnIndex);
                }
                cursor.close();
            }
        }

        return filePath;
    }

    private static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = { column };
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return null;
    }


    public static ArrayList<String> PHYSICAL_DISKS_ROOT_DIR = new ArrayList<>();



    public static Bitmap loadAsBitmap(String file_path){
        return BitmapFactory.decodeFile(file_path);
    }

    public static Bitmap loadAsBitmap(File file){
        return BitmapFactory.decodeFile(file.getAbsolutePath());
    }

    public static Bitmap loadAsBitmap(Context context,String relative_file_path, int relative){
        String relative_dir = null;
        switch(relative){
            case RELATIVE_SDCARD_STORAGE:
                relative_dir = getExternalSdCardPath(context).concat("/");
                break;
            default:
                relative_dir = getDir_ExternalStorageRoot().concat("/");
                break;
        }
        return loadAsBitmap(relative_dir.concat(relative_file_path));
    }








    public static void sort(List<File> file_list, Comparator<File> comparator) {
        Collections.sort(file_list, comparator);
    }


    public static String getDeviceName(Context context, String device_root_path){
        String device_name = null;

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            StorageManager storageManager = (StorageManager) context.getSystemService(Context.STORAGE_SERVICE);
            List<StorageVolume> storageVolumes = storageManager.getStorageVolumes();
            for (StorageVolume storageVolume : storageVolumes) {
                String mountPath = storageVolume.getDirectory().getAbsolutePath();
                if (mountPath.equals(device_root_path)) {
                    String label = storageVolume.getDescription(context);
                    device_name = label;
                    break;
                }
            }
        }else{
            File[] volumes = new File("/storage").listFiles();
            for (File volume : volumes) {
                String mountPath = volume.getAbsolutePath();
                if (mountPath.equals(device_root_path)) {
                    File[] files = volume.listFiles();
                    for (File file : files) {
                        if (file.getName().equals("Android")) {
                            continue;
                        }
                        device_name = file.getName();
                        break;
                    }
                    break;
                }
            }
        }

        return device_name;
    }



    public static ArrayList<String> getMountedDevices(Context context,boolean exclude_device_storage,boolean exclude_sd_card) {

        ArrayList<String> mountPaths = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) {
            BufferedReader bufferedReader = null;
            try {
                bufferedReader = new BufferedReader(new FileReader("/proc/mounts"));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    if (line.contains("vfat") || line.contains("/mnt")) {
                        String[] parts = line.split(" ");
                        String mountPath = parts[1];
                        if (mountPath != null) {
                            if (mountPath.startsWith("/mnt/media_rw") || mountPath.startsWith("/storage") ||
                                    mountPath.startsWith("/mnt/external_sd") || mountPath.startsWith("/mnt/sdcard") ||
                                    mountPath.startsWith("/mnt/ext_sd")) {
                                mountPaths.add(mountPath);
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            StorageManager storageManager = (StorageManager) context.getSystemService(Context.STORAGE_SERVICE);
            StorageVolume[] volumes;

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                volumes = storageManager.getStorageVolumes().toArray(new StorageVolume[0]);
            } else {
                try {
                    Method getVolumeList = storageManager.getClass().getMethod("getVolumeList");
                    volumes = (StorageVolume[]) getVolumeList.invoke(storageManager);
                } catch (Exception e) {
                    volumes = new StorageVolume[0];
                }
            }

            for (StorageVolume volume : volumes) {
                String mountPath = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    File path = volume.getDirectory();
                    if (path != null) {
                        mountPath = path.getAbsolutePath();
                    }
                } else {
                    try {
                        Method getPath = volume.getClass().getMethod("getPath");
                        mountPath = (String) getPath.invoke(volume);
                    } catch (Exception e) {
                        mountPath = null;
                    }
                }
                if (mountPath != null) {
                    if (mountPath.startsWith("/mnt/media_rw") || mountPath.startsWith("/storage") ||
                            mountPath.startsWith("/mnt/external_sd") || mountPath.startsWith("/mnt/sdcard") ||
                            mountPath.startsWith("/mnt/ext_sd")) {
                        mountPaths.add(mountPath);
                    }
                }
            }
        }
        
        if(exclude_device_storage){
            mountPaths.remove(getDir_ExternalStorageRoot());
        }
        if(exclude_sd_card){
            mountPaths.remove(getExternalSdCardPath(context));
        }
        return mountPaths;
    }




    public static ArrayList<File> getOTGDirectories(Context context){
        String my_otg = "0D020BC30D020BC3";
        String mount_dir = "/mnt/media_rw/0D020BC30D020BC3";
        File[] otgDirectories = new File(mount_dir).listFiles();
        ArrayList<File> list_files = new ArrayList<>(Arrays.asList(otgDirectories));


        return list_files;
    }

    public static ArrayList<String> getOTGDirectories_Test(Context context) {
        File mediaStorageDir = new File("/mnt/media_rw");
        File[] mediaDirs = mediaStorageDir.listFiles();
        ArrayList<String> devicePaths = new ArrayList<String>();

        for (File dir : mediaDirs) {
            if (dir.isDirectory()) {
                String path = dir.getAbsolutePath();
                devicePaths.add(path);
            }
        }
        return devicePaths;

    }







    public static boolean isFile_Image(String the_file){
        return isExtension_Image(RCTfile.getFileExtension(the_file).toLowerCase());
    }

    public static boolean isFile_Video(String the_file){
        return isExtension_Video(RCTfile.getFileExtension(the_file).toLowerCase());
    }
    public static boolean isFile_Audio(String the_file){
        return isExtension_Audio(RCTfile.getFileExtension(the_file).toLowerCase());
    }
    public static boolean isFile_Document(String the_file){
        return isExtension_Documents(RCTfile.getFileExtension(the_file).toLowerCase());
    }

    public static boolean isFile_Image(File the_file){
        return isExtension_Image(RCTfile.getFileExtension(the_file).toLowerCase());
    }

    public static boolean isFile_Video(File the_file){
        return isExtension_Video(RCTfile.getFileExtension(the_file).toLowerCase());
    }
    public static boolean isFile_Audio(File the_file){
        return isExtension_Audio(RCTfile.getFileExtension(the_file).toLowerCase());
    }
    public static boolean isFile_Document(File the_file){
        return isExtension_Documents(RCTfile.getFileExtension(the_file).toLowerCase());
    }


    ////////////////////////////////////////////////////////////////////

    public static boolean isExtension_Image(String file_extension){
        return RCTarray.contains(RCTFileFormat.FILE_EXTENSION_IMAGE,file_extension,false);
    }

    public static boolean isExtension_Video(String file_extension){
        return RCTarray.contains(RCTFileFormat.FILE_EXTENSION_VIDEO,file_extension,false);
    }
    public static boolean isExtension_Audio(String file_extension){
        return RCTarray.contains(RCTFileFormat.FILE_EXTENSION_AUDIO,file_extension,false);
    }
    public static boolean isExtension_Documents(String file_extension){
        file_extension = file_extension.toLowerCase();
        boolean bool_flag;
        switch(file_extension){
            case "docx":
            case "accdb":
            case "doc":
            case "pptx":
            case "ppt":
            case "pub":
            case "rtf":
            case "xlsx":
            case "pdf":
                bool_flag = true;
                break;
            default:
                bool_flag = false;
                break;
        }
        return bool_flag;
    }






    public static String getRecent(String[] file_paths){
        long most_recent_timer = 0;
        String most_recent_file_path = null;
        for(int index = 0; index<file_paths.length; index++){
            File current_file = new File(file_paths[index]);
            long current_timer = current_file.lastModified();
            if(current_timer>most_recent_timer){
                most_recent_timer = current_timer;
                most_recent_file_path = file_paths[index];
            }
        }
        return most_recent_file_path;
    }

    public static String getRecent(ArrayList<String> file_paths){
        long most_recent_timer = 0;
        String most_recent_file_path = null;
        for(int index = 0; index<file_paths.size(); index++){
            File current_file = new File(file_paths.get(index));
            long current_timer = current_file.lastModified();
            if(current_timer>most_recent_timer){
                most_recent_timer = current_timer;
                most_recent_file_path = file_paths.get(index);
            }
        }
        return most_recent_file_path;
    }




    /*

        try{
            RCTfile.readLine(new RCTfile.ReadLineCallback(){
                @Override
                public String getFilePath(){
                    return file_path;
                }

                @Override
                public void getLineData(int index, String line_data){
                }

            });
        }catch(IOException ignored){}

     */

    public static void readLine(ReadLineCallback callback) throws IOException {
        //This code can read 10 million lines in just 1.2 seconds
        String filePath = callback.getFilePath();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath), StandardCharsets.UTF_8)) {
            String lineData;
            int index = 0;
            while ((lineData = br.readLine()) != null) {
                callback.getLineData(index, lineData);
                index++;
            }
        }
    }


    public interface ReadLineCallback {
        String getFilePath();
        void getLineData(int index,String line_data);
    }



    public static String readLine(String target_file_path, int target_line_index){
        int current_line = 0;
        try {
            try (BufferedReader br = Files.newBufferedReader(Paths.get(target_file_path), StandardCharsets.UTF_8)) {
                for (String line = null; (line = br.readLine()) != null; ){
                    if(current_line == target_line_index){
                        return line;
                    }else{
                        current_line++;
                    }
                }
            }
        }catch (IOException ignored){}
        return null;
    }


    public static String readLine(File target_file, int target_line_index){
        int current_line = 0;
        try {
            try (BufferedReader br = Files.newBufferedReader(Paths.get(target_file.getAbsolutePath()), StandardCharsets.UTF_8)) {
                for (String line = null; (line = br.readLine()) != null; ){
                    if(current_line == target_line_index){
                        return line;
                    }else{
                        current_line++;
                    }
                }
            }
        }catch (IOException ignored){}
        return null;
    }



    public static String[] readLine(String target_file_path, int[] target_line_index){
        String[] values = new String[target_line_index.length];
        int current_tli = 0;
        int current_line = 0;
        try {
            try (BufferedReader br = Files.newBufferedReader(Paths.get(target_file_path), StandardCharsets.UTF_8)) {
                for (String line = null; (line = br.readLine()) != null; ) {
                    if(current_tli == target_line_index.length){
                        break;
                    }
                    if(current_line == target_line_index[current_tli]){
                        values[current_tli] = line;
                        current_tli++;
                    }else{
                    }
                    current_line++;
                }
            }
        }catch (IOException ignored){}
        return values;
    }

    public static String[] readLine(File target_file, int[] target_line_index){
        String[] values = new String[target_line_index.length];
        int current_tli = 0;
        int current_line = 0;
        try {
            try (BufferedReader br = Files.newBufferedReader(target_file.toPath(), StandardCharsets.UTF_8)) {
                for (String line = null; (line = br.readLine()) != null; ) {
                    if(current_tli == target_line_index.length){
                        break;
                    }
                    if(current_line == target_line_index[current_tli]){
                        values[current_tli] = line;
                        current_tli++;
                    }else{
                    }
                    current_line++;
                }
            }
        }catch (IOException ignored){}
        return values;
    }

    public static String[] readLine(String target_file_path, int from_index, int to_index){
        int line_count = (to_index-from_index)+1;
        String[] values = new String[line_count];
        int current_index = 0;
        int current_line = 0;
        try {
            try (BufferedReader br = Files.newBufferedReader(Paths.get(target_file_path), StandardCharsets.UTF_8)) {
                for (String line = null; (line = br.readLine()) != null; ) {
                    if(current_line > to_index){
                        break;
                    }
                    if(current_line >= from_index){
                        values[current_index] = line;
                        current_index++;
                    }
                    current_line++;
                }
            }
        }catch (IOException ignored){}
        return values;
    }

    public static String[] readLine(File target_file, int from_index, int to_index){
        int line_count = (to_index-from_index)+1;
        String[] values = new String[line_count];
        int current_index = 0;
        int current_line = 0;
        try {
            try (BufferedReader br = Files.newBufferedReader(target_file.toPath(), StandardCharsets.UTF_8)) {
                for (String line = null; (line = br.readLine()) != null; ) {
                    if(current_line > to_index){
                        break;
                    }
                    if(current_line >= from_index){
                        values[current_index] = line;
                        current_index++;
                    }
                    current_line++;
                }
            }
        }catch (IOException ignored){}
        return values;
    }




    public static boolean createFile_WithStringData(String file_path,String[] file_contents){
        try{
            File new_file = new File(file_path);
            if(!new_file.exists()) {
                new_file.createNewFile();
                try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new_file)))) {
                    for (String new_file_datum : file_contents) {
                        bw.write(new_file_datum);
                        bw.newLine();
                    }
                    bw.flush();
                    return true;
                }
            }else{
                return false;
            }
        }catch (IOException ignored){
            return  false;
        }
    }

    public static boolean createFile_WithStringData(String file_path,ArrayList<String> file_contents){
        try{
            File new_file = new File(file_path);
            if(!new_file.exists()) {
                new_file.createNewFile();
                try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new_file)))) {
                    for (String new_file_datum : file_contents) {
                        bw.write(new_file_datum);
                        bw.newLine();
                    }
                    bw.flush();
                    return true;
                }
            }else{
                return false;
            }
        }catch (IOException ignored){
            return  false;
        }
    }



    public static String[] removeAllNonExistingFiles(String[] files_path) {
        String[] i_files_path = new String[files_path.length];
        System.arraycopy(files_path, 0, i_files_path, 0, files_path.length);
        for(int index = 0; index<i_files_path.length; index++){
            if(!doesFileExist(i_files_path[index])){
                i_files_path[index] = null;
            }
        }
        i_files_path = RCTarray.removeAllNull(i_files_path);
        return i_files_path;
    }
    public static ArrayList<String> removeAllNonExistingFiles_ArrayList(String[] file_path){
        ArrayList<String> verified_file_paths = new ArrayList<>();
        for(int index = 0; index< file_path.length; index++){
            if(isPathAFile(file_path[index])){
                if(doesFileExist(file_path[index])){
                    verified_file_paths.add(file_path[index]);
                }
            }else{
                if(doesDirectoryExist(file_path[index])){
                    verified_file_paths.add(file_path[index]);
                }
            }
        }
        return verified_file_paths;
    }
    public static ArrayList<String> removeAllNonExistingFiles_ArrayList(ArrayList<String> file_path){
        ArrayList<String> verified_file_paths = new ArrayList<>();
        for(int index = 0; index< file_path.size(); index++){
            if(isPathAFile(file_path.get(index))){
                if(doesFileExist(file_path.get(index))){
                    verified_file_paths.add(file_path.get(index));
                }
            }else{
                if(doesDirectoryExist(file_path.get(index))){
                    verified_file_paths.add(file_path.get(index));
                }
            }
        }
        return verified_file_paths;
    }

    public static ArrayList<String>extractFilesWithExtension(String[] file_paths,String file_extension){
        file_extension = file_extension.toLowerCase();
        ArrayList<String> extracted_files = new ArrayList<>();
        for(int index = 0; index< file_paths.length; index++){
            if(getFileExtension(file_paths[index]).toLowerCase().equals(file_extension)){
                extracted_files.add(file_paths[index]);
            }
        }
        return extracted_files;
    }

    public static Uri getUriForFile(Context context, File file){
        return FileProvider.getUriForFile(context, context.getPackageName() + ".provider", file);
    }
    public static String getMimeType(File file){
        MimeTypeMap mime_type_map = MimeTypeMap.getSingleton();
        return mime_type_map.getMimeTypeFromExtension(RCTfile.getFileExtension(file));
    }

    /**
     * <h2>Description</h2>
     * Will clear any data inside the file.
     * @author  Rafael Andaya Cartagena
     * @version 1.0
     * @param target_file_path the absolute path of the file to be cleared
     * @since 04-30-2021
     */
    @SuppressWarnings("EmptyTryBlock")
    public static void clearData(String target_file_path) throws IOException{
        File myObj = new File(target_file_path);
        FileOutputStream fos = new FileOutputStream(myObj);
        try(BufferedWriter ignored = new BufferedWriter(new OutputStreamWriter(fos))){
        }
    }

    /**
     * <h2>Description</h2>
     * Clear data of the target_files
     * @author  Rafael Andaya Cartagena
     * @version 1.0
     * @param file_paths absolute path of the target files
     * @since 05-01-2021
     */
    @SuppressWarnings("EmptyTryBlock")
    public static void clearData(String[] file_paths) throws IOException{
        for(String file_path1 : file_paths){
            if(doesFileExist(file_path1)){
                File myObj = new File(file_path1);
                FileOutputStream fos = new FileOutputStream(myObj);
                try(BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos))){
                }
            }
        }
    }

    public static String getLastModifiedTime_DDMMYY_HHMMSS(String target_path){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            // Compatible implementation for Android 4.4 and higher
            try {
                File file = new File(target_path);
                DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                return sdf.format(new Date(file.lastModified()));
            } catch (Exception ignored) {
                return null;
            }
        } else {
            // Original implementation for Android versions higher than 8.0 (API 26)
            try {
                long millis_time = Files.getLastModifiedTime(Paths.get(target_path)).toMillis();
                DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                return sdf.format(new Date(millis_time));
            } catch (IOException ignored) {
                return null;
            }
        }
    }

    public static String getLastModifiedTime_DDMMYY(String target_path) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            File file = new File(target_path);
            if (file.exists()) {
                Date lastModified = new Date(file.lastModified());
                DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                return sdf.format(lastModified);
            } else {
                return null;
            }
        } else {
            try {
                long millis_time = Files.getLastModifiedTime(Paths.get(target_path)).toMillis();
                DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                return sdf.format(new Date(millis_time));
            } catch (IOException ignored) {
                return null;
            }
        }
    }

    public static String getLastModifiedTime_MMDDYY_HHMMSS(String target_path){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            try {
                File file = new File(target_path);
                Date lastModified = new Date(file.lastModified());
                DateFormat sdf = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
                return sdf.format(lastModified);
            } catch (Exception e) {
                return null;
            }
        } else {
            try {
                long millis_time = Files.getLastModifiedTime(Paths.get(target_path)).toMillis();
                DateFormat sdf = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
                return sdf.format(new Date(millis_time));
            } catch (IOException ignored){
                return null;
            }
        }
    }

    public static String getLastModifiedTime_MMDDYY(String target_path) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            File file = new File(target_path);
            long last_modified = file.lastModified();
            DateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
            return sdf.format(new Date(last_modified));
        } else {
            try {
                long millis_time = Files.getLastModifiedTime(Paths.get(target_path)).toMillis();
                DateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
                return sdf.format(new Date(millis_time));
            } catch (IOException ignored) {
                return null;
            }
        }
    }


    public static String getFileNameWithoutExtension(String target_path){
        String reg_name = RCTfile.getFileName(target_path);
        boolean do_have_dot = false;
        int last_dot_index = -1;
        for(int index = 0; index<reg_name.length(); index++){
            if(reg_name.charAt(index) == '.'){
                do_have_dot = true;
                last_dot_index = index;
            }
        }
        if(do_have_dot){
            return reg_name.substring(0,last_dot_index);
        }else{
            return reg_name;
        }
    }


    public static boolean isSDCARDAvailable(Context context) {
        File[] storages = ContextCompat.getExternalFilesDirs(context, null);
        if (storages.length > 1 && storages[0] != null && storages[1] != null)
            return true;
        else
            return false;

    }



    public static String getExternalSdCardPath(Context context){
        if(isSDCARDAvailable(context)) {
            String final_dir = "/";
            File[] listed_media = ContextCompat.getExternalFilesDirs(context, null);
            if (listed_media.length > 1) {
                String[] path_sections = listed_media[1].getAbsolutePath().split("/");
                int target_int = -1;
                for (int index = 0; index < path_sections.length; index++) {
                    if (path_sections[index].equals("Android")) {
                        target_int = index - 1;
                    }
                }
                if (path_sections[0].length() == 0) {
                    for (int index = 1; index <= target_int; index++) {
                        if (index != target_int) {
                            final_dir = final_dir.concat(path_sections[index]).concat("/");
                        } else {
                            final_dir = final_dir.concat(path_sections[index]);
                        }
                    }
                }
                if (path_sections[0].length() > 0) {
                    for (int index = 0; index <= target_int; index++) {
                        if (index != target_int) {
                            final_dir = final_dir.concat(path_sections[index]).concat("/");
                        } else {
                            final_dir = final_dir.concat(path_sections[index]);
                        }
                    }
                }
            }
            return final_dir;
        }else{
            return null;
        }
    }


    public static long getSize(String[] file_paths){
        long total_size = 0;
        for(int index = 0; index<file_paths.length; index++){
            if(isPathAFile(file_paths[index])){
                total_size += getSize(file_paths[index]);
            }else{
                total_size += getDirectorySize(file_paths[index]);
            }
        }
        return total_size;
    }
    public static long getSize(ArrayList<String> file_paths){
        long total_size = 0;
        for(int index = 0; index<file_paths.size(); index++){
            if(isPathAFile(file_paths.get(index))){
                total_size += getSize(file_paths.get(index));
            }else{
                total_size += getDirectorySize(file_paths.get(index));
            }
        }
        return total_size;
    }




    public static long getDirectorySize(String dir_path) {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            // If the Android version is less than API 26, use the below implementation
            long size = 0;
            try {
                File dir = new File(dir_path);
                if (dir.isDirectory()) {
                    String[] files = dir.list();
                    for (String file : files) {
                        String path = dir_path + "/" + file;
                        File f = new File(path);
                        if (f.isFile()) {
                            size += f.length();
                        } else {
                            size += getDirectorySize(path);
                        }
                    }
                } else {
                    size = dir.length();
                }
            } catch (Exception ignored) {}
            return size;
        } else {
            // If the Android version is API 26 or later, use the below implementation
            Path path = new File(dir_path).toPath();
            long size = 0;
            try (Stream<Path> walk = Files.walk(path)) {
                size = walk
                        .filter(Files::isRegularFile)
                        .mapToLong(p -> {
                            try {
                                return Files.size(p);
                            } catch (IOException ignored){
                                return 0L;
                            }
                        })
                        .sum();

            } catch (IOException ignored) {}
            return size;
        }
    }


    public static String getRootDisk(String file_path){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            String root = "/";
            int index = file_path.indexOf(root, 1);
            if (index > 0) {
                return file_path.substring(0, index);
            } else {
                return root;
            }
        }else{
            return new File(file_path).toPath().getRoot().toString();
        }
    }


    public static String getParentDirectory(String path){
        File my_file = new File(path);
        return my_file.getParent();
    }



    public static String getFileExtension(String file_path){
        String file_name = getFileName(file_path);
        String[] name_sections = file_name.split("\\.");
        return name_sections[name_sections.length-1];
    }

    public static String getFileExtension(File the_file){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            String file_name = the_file.getName();
            int dot_index = file_name.lastIndexOf(".");
            if (dot_index == -1) {
                return "";
            }
            return file_name.substring(dot_index + 1);
        }else{
            String file_name = Paths.get(the_file.getAbsolutePath()).getFileName().toString();
            String[] name_sections = file_name.split("\\.");
            return name_sections[name_sections.length-1];
        }
    }






    public static String getDirectoryName(String target_path){
        File file = new File(target_path);
        String fileName;
        if(file.exists()){
            fileName = file.getName();
        }else{
            fileName = null;
        }
        return fileName;
    }
    public static String getFileName(String target_path) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            // compatible implementation for Android versions below Oreo (API 26)
            int lastIndexOfSlash = target_path.lastIndexOf("/");
            return target_path.substring(lastIndexOfSlash + 1);
        } else {
            // optimized implementation for Android Oreo (API 26) and above
            Path p = Paths.get(target_path);
            return p.getFileName().toString();
        }
    }


    public static String mergeDirName(String root_dir,String child_dir){
        return root_dir.concat("/").concat(child_dir);
    }
    public static String removeDirSection(String directory,int section){
        String[] sections = directory.split("/");
        sections = RCTarray.delete(sections,section);
        String processed_dir = "/";
        for(int index = 1; index<sections.length; index++){
            if(index == 1){
                processed_dir = processed_dir.concat(sections[index]);
            }else{
                processed_dir = processed_dir.concat("/").concat(sections[index]);
            }
        }
        return processed_dir;
    }




    public static String getDir_IntAppFiles(Context app_context){
        return app_context.getFilesDir().getAbsolutePath();
    }
    public static String getDir_IntAppCache(Context app_context){
        return app_context.getCacheDir().getAbsolutePath();
    }
    public static String getDir_IntAppData(Context app_context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            // compatible implementation for Android versions below Oreo (API 26)
            return app_context.getApplicationInfo().dataDir;
        } else {
            // optimized implementation for Android Oreo (API 26) and above
            return app_context.getDataDir().getAbsolutePath();
        }
    }

    public static String getDir_IntAppCodeCache(Context app_context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            // compatible implementation for Android versions below Oreo (API 26)
            File cacheDir = app_context.getCacheDir();
            File codeCacheDir = new File(cacheDir, "code_cache");
            return codeCacheDir.getAbsolutePath();
        } else {
            // optimized implementation for Android Oreo (API 26) and above
            return app_context.getCodeCacheDir().getAbsolutePath();
        }
    }

    public static String getDir_IntAppNoBackupFiles(Context app_context){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            // For devices running Android 4.4 (API level 19) or lower
            File file = new File(app_context.getFilesDir(), "no_backup");
            if (!file.exists()) {
                file.mkdir();
            }
            return file.getAbsolutePath();
        } else {
            // For devices running Android 5.0 (API level 21) or higher
            return app_context.getNoBackupFilesDir().getAbsolutePath();
        }
    }

    public static String getDir_PackageCodePath(Context app_context){
        return app_context.getPackageCodePath();
    }
    public static String getDir_PackageResourcePath(Context app_context){
        return app_context.getPackageResourcePath();
    }
    public static String getDir_ExtAppFiles(Context app_context){
        return app_context.getExternalFilesDir(null).getAbsolutePath();
    }
    public static String getDir_ExtAppCache(Context app_context){
        return app_context.getExternalCacheDir().getAbsolutePath();
    }
    public static String getDir_ExternalStorageRoot(){
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    public static String getDir_IntAppStorage(Context app_context){
        String to_processed_dir = getDir_IntAppFiles(app_context);
        return removeDirSection(to_processed_dir,to_processed_dir.split("/").length-1);
    }




    public static void renameFile(String directory, String file_name, String new_name) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            File file = new File(directory, file_name);
            if (file.exists()) {
                File new_file = new File(directory, new_name);
                if (file.renameTo(new_file)) {
                } else {
                }
            } else {
            }
        } else {
            Path source_file = Paths.get(directory, file_name);
            try {
                Files.move(source_file, source_file.resolveSibling(new_name));
            } catch (IOException ignored) {
            }
        }
    }

    public static void renameFile(String directory, String file_name, String new_name_without_extension, String new_extension) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            // For devices running Android 4.4 (API level 19) or lower
            // Use the File class to rename the file
            File file = new File(directory, file_name);
            if (file.exists()) {
                String old_extension = file_name.substring(file_name.lastIndexOf(".") + 1);
                String new_file_name = new_name_without_extension + "." + new_extension;
                File new_file = new File(directory, new_file_name);
                if (file.renameTo(new_file)) {
                    // File renamed successfully
                } else {
                    // Failed to rename file
                }
            } else {
                // File does not exist
            }
        } else {
            // For devices running Android 8.0 (API level 26) or higher
            // Use the java.nio.file package to rename the file
            Path source_file = Paths.get(directory, file_name);
            String new_file_name = new_name_without_extension + "." + new_extension;
            try {
                Files.move(source_file, source_file.resolveSibling(new_file_name));
                // File renamed successfully
            } catch (IOException ignored) {
                // Failed to rename file
            }
        }
    }


    public static String renameFileExtension(String file_path, String new_extension){
        return getFileNameWithoutExtension(file_path).concat(".").concat(new_extension);
    }

    public static void renameDirectory(String directory, String new_name){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            File dir = new File(directory);
            File newDir = new File(dir.getParent(), new_name);
            if (dir.exists() && dir.isDirectory()) {
                boolean success = dir.renameTo(newDir);
                if (!success) {
                    // Handle error
                }
            }
        } else {
            Path source_dir = Paths.get(directory);
            try {
                Files.move(source_dir, source_dir.resolveSibling(new_name));
            } catch (IOException ignored) {
            }
        }
    }




    public static boolean createFile(String directory,String file_name){
        try{
            File new_file = new File(directory,file_name);
            if(!new_file.exists()) {
                new_file.createNewFile();
                return true;
            }else{
                return false;
            }
        }catch (IOException ignored){
            return  false;
        }
    }


    public static boolean createFile(String file_path){
        try{
            File new_file = new File(file_path);
            if(!new_file.exists()) {
                return new_file.createNewFile();
            }else{
                return false;
            }
        }catch (IOException ignored){
            return  false;
        }
    }

    public static boolean createFile(File the_file){
        try{
            if(!the_file.exists()) {
                return the_file.createNewFile();
            }else{
                return false;
            }
        }catch (IOException ignored){
            return  false;
        }
    }




    public static boolean overrideFile(String directory,String file_name,String[] file_contents) throws IOException{
        File target_directory = new File(directory);
        if(target_directory.exists()){
            File target_file = new File(target_directory, file_name);
            if(target_file.exists()){
                try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(target_file)))) {
                    for (String new_file_datum : file_contents) {
                        bw.write(new_file_datum);
                        bw.newLine();
                    }
                    bw.flush();
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean overrideFile(String file_path,String[] file_contents) throws IOException{
        File target_file = new File(file_path);
        if(target_file.exists()){
            try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(target_file)))) {
                for (String new_file_datum : file_contents) {
                    bw.write(new_file_datum);
                    bw.newLine();
                }
                bw.flush();
                return true;
            }
        }
        return false;
    }






    public static boolean overrideFile_FilePaths(String file_path,List<File> file_list) throws IOException{
        File target_file = new File(file_path);
        if(target_file.exists()){
            try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(target_file)))) {
                for (int i = 0; i < file_list.size(); i++) {
                    String new_file_datum = file_list.get(i).getAbsolutePath();
                    bw.write(new_file_datum);
                    bw.newLine();
                }
                bw.flush();
                return true;
            }
        }
        return false;
    }



    public static boolean overrideFile(String file_path,ArrayList<String> file_contents) throws IOException{
        File target_file = new File(file_path);
        if(target_file.exists()){
            try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(target_file)))) {
                for (String new_file_datum : file_contents) {
                    bw.write(new_file_datum);
                    bw.newLine();
                }
                bw.flush();
                return true;
            }
        }
        return false;
    }

    public static boolean overrideFile(String file_path, List<String> file_contents) throws IOException{
        File target_file = new File(file_path);
        if(target_file.exists()){
            try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(target_file)))) {
                for (String new_file_datum : file_contents) {
                    bw.write(new_file_datum);
                    bw.newLine();
                }
                bw.flush();
                return true;
            }
        }
        return false;
    }

    /**
     * <h2>Description</h2>
     * Reads the contents of the file and returns it as a 1D string array
     * @author Rafael Andaya Cartagena
     * @version 1.0
     * @since 04-29-2021
     * @return String[] - returns a 1D string array, each index representing a line value of the file.
     * @param the_file the file object fo the target file.
     */

    public static String[] readFile(File the_file) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(the_file.getAbsolutePath()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines.toArray(new String[0]);
    }


    /**
     * <h2>Description</h2>
     * Reads the contents of the file and returns it as a 1D string array
     * @author Rafael Andaya Cartagena
     * @version 1.0
     * @since 04-29-2021
     * @return String[] - returns a 1D string array, each index representing a line value of the file.
     * @param file_path absolute path of the target file.
     */

    public static String[] readFile(String file_path) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file_path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines.toArray(new String[0]);
    }

    public static String[] readFileFromURL(String url) throws IOException {
        List<String> lines = new ArrayList<>();
        URLConnection conn = new URL(url).openConnection();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines.toArray(new String[0]);
    }

    public static boolean overrideFileFromURL(String url_token, String[] file_contents) throws IOException {
        URL url = new URL(url_token);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("PUT");
        OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
        for (String line : file_contents) {
            writer.write(line);
            writer.write("\n");
        }
        writer.close();
        return connection.getResponseCode() == HttpURLConnection.HTTP_OK;
    }





    public static List<String> readFile_List(File file) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file.getAbsolutePath()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }

    public static List<String> readFile_List(String file_path) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file_path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }

    public static ArrayList<String> readFile_ArrayList(File file) throws IOException {
        ArrayList<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file.getAbsolutePath()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }

    public static ArrayList<String> readFile_ArrayList(String file_path) throws IOException {
        ArrayList<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file_path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }


    public static boolean deleteFile(String directory, String file_name){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            // Implement compatible code for Android 4.4 (API 19) to Android 6.0 (API 23)
            File td_file = new File(directory, file_name);
            if(td_file.exists()){
                return td_file.delete();
            }else{
                return false;
            }
        } else {
            // Implement optimized code for Android 7.0 (API 24) and above
            Path td_path = Paths.get(directory, file_name);
            try {
                Files.delete(td_path);
                return true;
            } catch (IOException e) {
                return false;
            }
        }
    }


    public static boolean deleteFile(String file_path){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            File td_file = new File(file_path);
            if(td_file.exists()){
                return td_file.delete();
            }else{
                return false;
            }
        }else{
            File td_file = new File(file_path);
            if(td_file.exists()){
                try {
                    return td_file.delete();
                }catch(UncheckedIOException ignored){
                    return false;
                }
            }else{
                return false;
            }
        }
    }


    public static void deleteFile(ArrayList<String> file_paths){
        for(int index = 0; index<file_paths.size(); index++){
            try {
                deleteFile(file_paths.get(index));
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }

    /**
     * <h2>Description</h2>
     * Reads the contents of the files and returns all of the combined contents of all the files in a single ArrayList String</>
     * @author Rafael Andaya Cartagena
     * @version 1.0
     * @since 2024-05-14
     */
    public static ArrayList<String> readFile_Combine(ArrayList<String> file_paths){
        ArrayList<String> file_contents = new ArrayList<>();
        for(int index = 0; index<file_paths.size(); index++){
            try{
                file_contents.addAll(readFile_ArrayList(file_paths.get(index)));
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        return file_contents;
    }

    /**
     * <h2>Description</h2>
     * Reads the contents of the files and returns ArrayList[ArrayList[String]], where each index represents the ArrayList[String] of each file
     * @author Rafael Andaya Cartagena
     * @version 1.0
     * @since 2024-05-14
     */
    public static ArrayList<ArrayList<String>> readFile(ArrayList<String> file_paths){
        ArrayList<ArrayList<String>> individual_file_contents = new ArrayList<>();
        for(int index = 0; index<file_paths.size(); index++){
            try{
                ArrayList<String> current_file_contents = new ArrayList<>(readFile_ArrayList(file_paths.get(index)));
                individual_file_contents.add(current_file_contents);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        return individual_file_contents;
    }







    public static void moveFile(String src_file_path, String dest_dir){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            File src_file = new File(src_file_path);
            File dest_directory = new File(dest_dir);
            if (!dest_directory.exists()) {
                dest_directory.mkdirs();
            }
            File dest_file = new File(dest_directory, src_file.getName());
            src_file.renameTo(dest_file);
        } else {
            Path source_file = Paths.get(src_file_path);
            Path dest_file_path = Paths.get(dest_dir, getFileName(src_file_path));
            try {
                Files.move(source_file, dest_file_path);
            } catch (IOException ignored) {}
        }
    }





    public static String[] getPathSections(String target_path){
        return target_path.split("/");
    }

    public static boolean copyFile(String src_dir,String src_filename,
                                   String dest_dir){
        try {
            FileUtils.copyFileToDirectory(new File(src_dir,src_filename),new File(dest_dir));
            return true;
        } catch (IOException e) {
            return false;
        }

    }
    public static boolean copyFile(String src_filepath,String dest_dir){
        try {
            FileUtils.copyFileToDirectory(new File(src_filepath),new File(dest_dir));
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * <h2>Description</h2>
     * Returns true if the absolute directory is a folder , false otherwise
     * @author  Rafael Andaya Cartagena
     * @version 1.0
     * @since 05-01-2021
     */
    public static boolean isPathADirectory(String target_file_path){
        File file = new File(target_file_path);
        return file.isDirectory();
    }


    public static boolean appendLastLine(String directory,String file_name,String new_data){
        File file = new File(directory,file_name);
        try {
            FileWriter fr = new FileWriter(file, true);
            fr.write(new_data);
            fr.close();
            return true;
        }catch(IOException e){
            return false;
        }
    }

    public static void appendFile(String file_path,String new_data){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file_path, true))) {
            writer.write(new_data);
            writer.newLine();
        }catch(IOException ignored){
        }
    }

    public static void appendFile(File file,String new_data){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file.getAbsolutePath(), true))) {
            writer.write(new_data);
            writer.newLine();
        }catch(IOException ignored){
        }
    }

    /**
     * <h2>Description</h2>
     * Returns true if the absolute directory is a file , false otherwise
     * @author  Rafael Andaya Cartagena
     * @version 1.0
     * @since 05-01-2021
     */
    public static boolean isPathAFile(String path){
        File file = new File(path);
        return file.isFile();
    }









































    public static boolean doesFileExist(String directory,String file_name){
        return new File(new File(directory),file_name).exists();
    }
    public static boolean doesDirectoryExist(String directory){
        return new File(directory).exists();
    }

    public static boolean doesFileExist(String file_path){
        return new File(file_path).exists();
    }
    public static boolean doesFileExist(File file_path){
        return file_path.exists();
    }



    public static int getLineCount(String directory,String file_name){
        File target_file = new File(directory, file_name);
        int line_count = 0;
        try (FileReader fr = new FileReader(target_file)){
            BufferedReader br = new BufferedReader(fr);
            while(br.readLine() !=null){
                line_count++;
            }
        }catch (IOException ignored){}
        return line_count;
    }

    public static int getLineCount(String file_path){
        File target_file = new File(file_path);
        int line_count = 0;
        try (FileReader fr = new FileReader(target_file)){
            BufferedReader br = new BufferedReader(fr);
            while(br.readLine() !=null){
                line_count++;
            }
        }catch (IOException ignored){}
        return line_count;
    }

    public static double getSize(String directory,String file_name){
        File myFile = new File(directory,file_name);
        double fileSize = -1;
        if(myFile.exists()){
            fileSize = myFile.length();
        }
        return fileSize;
    }




    public static long getSize(String file_path){
        File myFile = new File(file_path);
        if(myFile.exists()){
            return myFile.length();
        }
        return 0;
    }
    public static long getSize(File the_file){
        if(the_file.exists()){
            return the_file.length();
        }
        return 0;
    }

    public static boolean isPathAFile(File the_file){
        return the_file.isFile();
    }
    public static boolean isPathADirectory(File the_file){
        return the_file.isDirectory();
    }











    public static void createDirectory_ExternalStorage(String directory_relative_to_external_storage){
        String[] dir_sections = directory_relative_to_external_storage.split("/");
        String current_dir_path = getDir_ExternalStorageRoot();
        for(int index = 0; index<dir_sections.length; index++){
            current_dir_path = current_dir_path.concat("/").concat(dir_sections[index]);
            File target_directory = new File(current_dir_path);
            if(!target_directory.exists()){
                target_directory.mkdir();
            }
        }
    }
    public static void createDirectory_IntAppStorage(Context app_context,String directory_relative_to_external_storage){
        String[] dir_sections = directory_relative_to_external_storage.split("/");
        String current_dir_path = getDir_IntAppStorage(app_context);
        for(int index = 0; index<dir_sections.length; index++){
            current_dir_path = current_dir_path.concat("/").concat(dir_sections[index]);
            File target_directory = new File(current_dir_path);
            if(!target_directory.exists()){
                target_directory.mkdir();
            }
        }
    }
    public static boolean deleteDirectory(String directory){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            File dir = new File(directory);
            if(dir.exists()){
                File[] files = dir.listFiles();
                if(files != null){
                    for(File file : files){
                        if(file.isDirectory()){
                            deleteDirectory(file.getAbsolutePath());
                        }else{
                            file.delete();
                        }
                    }
                }
                return dir.delete();
            }else{
                return false;
            }
        }else{
            if(doesDirectoryExist(directory)){
                Path target_path = Paths.get(directory);
                try{
                    Files.walk(target_path)
                            .sorted(Comparator.reverseOrder())
                            .map(Path::toFile)
                            .forEach(File::delete);
                }catch(IOException | UncheckedIOException ignored){}
                return true;
            }else{
                return false;
            }
        }

    }

    public static String getFileSizeInString_WithSymbol(String file_path){
        long size_in_bytes = getSize(file_path);
        String size_symbol = RCTdataSizeConverter.getSymbol(size_in_bytes);
        double converted_value = RCTdataSizeConverter.getConvertedValue_BasedOnSymbol(size_in_bytes);
        String string_converted_value = RCTdataSizeConverter.show2DecimalPlacesOnly(converted_value);
        return string_converted_value.concat(" ").concat(size_symbol);
    }

    public static boolean cleanDirectory(String directory_path){

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            if(doesDirectoryExist(directory_path)){
                String[] dir_contents = RCTdirectory.getImmediateFileAndSubDir(directory_path);
                for (int index = 0; index < dir_contents.length; index++) {
                    if (isPathAFile(dir_contents[index])) {
                        RCTfile.deleteFile(dir_contents[index]);
                    } else {
                        deleteDirectory(dir_contents[index]);
                    }
                }
                return true;
            }else{
                return false;
            }
        }else{
            if(doesDirectoryExist(directory_path)){
                try {
                    String[] dir_contents = RCTdirectory.getImmediateFileAndSubDir(directory_path);
                    for (int index = 0; index < dir_contents.length; index++) {
                        if (isPathAFile(dir_contents[index])) {
                            RCTfile.deleteFile(dir_contents[index]);
                        } else {
                            deleteDirectory(dir_contents[index]);
                        }
                    }
                    return true;
                }catch (UncheckedIOException ignored){
                    return false;
                }
            }else{
                return false;
            }
        }
    }




    protected static boolean deleteDirectory_ExtStorageRelative(String directory){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            // Compatible implementation for API < 26 (inclusive)
            File target = new File(directory);
            if (!target.exists()) {
                return false;
            }
            File[] files = target.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        deleteDirectory_ExtStorageRelative(file.getAbsolutePath());
                    } else {
                        file.delete();
                    }
                }
            }
            return target.delete();
        } else {
            // Optimized implementation for API >= 26
            if(doesDirectoryExist_Exclusive(directory)){
                Path target_path = Paths.get(directory);
                try{
                    Files.walk(target_path)
                            .sorted(Comparator.reverseOrder())
                            .map(Path::toFile)
                            .forEach(File::delete);
                }catch(IOException ignored){
                    return false;
                }
                return true;
            }else{
                return false;
            }
        }
    }

    //Directory must be relative to External Storage Root Directory
    protected static boolean doesDirectoryExist_Exclusive(String directory){
        return new File(directory).exists();
    }



    public void helloExp(){

    }


    public static String[] listAllFilesAndSubDirectories_ExtStorageRelative(String directory) throws IOException{
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            //ChatGPT - Always read and follow the comments for instructions
            //ChatGPT - Fill this block with the compatible implementation of the code on the else block base on the if condition
            //ChatGPT - Maintain the same functionality
            //ChatGPT - Optimize your generated code in this block
            //ChatGPT - Your generated code here in this block shall be compatible at minimum Android 4.4 (API 19)
            //ChatGPT - Don't include or add comments in the code you will generate
            //ChatGPT - Don't change the conditions in the if-condition
            File dir = new File(Environment.getExternalStorageDirectory(), directory);
            List<String> fileList = new ArrayList<>();
            File[] files = dir.listFiles();
            if (files != null) {
                for (File file : files) {
                    fileList.add(file.getAbsolutePath());
                    if (file.isDirectory()) {
                        fileList.addAll(Arrays.asList(listAllFilesAndSubDirectories_ExtStorageRelative(file.getAbsolutePath().substring(file.getAbsolutePath().indexOf(directory)))));
                    }
                }
            }
            String[] fileListArray = new String[fileList.size()];
            return fileList.toArray(fileListArray);
        }else {
            //ChatGPT - Always read and follow the comments for instructions
            //ChatGPT - Optimize this block, and maintain the same functionality
            //ChatGPT - Don't include or add comments in the code you will generate
            Path start = Paths.get(Environment.getExternalStorageDirectory().getAbsolutePath(), directory);
            try (Stream<Path> stream = Files.walk(start, Integer.MAX_VALUE)) {
                return stream
                        .map(String::valueOf)
                        .sorted()
                        .toArray(String[]::new);
            }
        }
    }



    public static boolean doesPathMatchDir(String source_path,String key_path){
        if(source_path.length() >= key_path.length()){
            String trim_source_path = source_path.substring(0,key_path.length());
            return trim_source_path.equals(key_path);
        }
        return false;
    }
    public static boolean doesPathMatchDir(String source_path,String[] key_path){
        for(int index = 0; index< key_path.length; index++){
            if(source_path.length() >= key_path[index].length()){
                String trim_source_path = source_path.substring(0,key_path[index].length());
                if(trim_source_path.equals(key_path[index])){
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean doesPathMatchDir(String source_path,ArrayList<String> key_path){
        for(int index = 0; index< key_path.size(); index++){
            if(source_path.length() >= key_path.get(index).length()){
                String trim_source_path = source_path.substring(0,key_path.get(index).length());
                if(trim_source_path.equals(key_path.get(index))){
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * <h2>Description</h2>
     * Returns the value of a give line if it contains the target_data. Only the line of the first appearance of the target_data will be returned
     * @author  Rafael Andaya Cartagena
     * @version 1.0
     * @param file_path target file abosulte file
     * @param target_data the data to match
     * @param case_sensitive the data to match
     * @since 02-24-2023
     */
    public static String returnLineDataWithDataAppearanceOf(String file_path, String target_data, boolean case_sensitive) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file_path))) {
            String line_data;
            String lowercase_target_data = case_sensitive ? target_data : target_data.toLowerCase();
            StringBuilder builder = new StringBuilder();
            builder.append(lowercase_target_data);
            while ((line_data = reader.readLine()) != null){
                if(!case_sensitive){
                    line_data = line_data.toLowerCase();
                }

                if(containsWord(line_data,target_data)){
                    return line_data;
                }
            }
        }
        return null;
    }

    /**
     * <h2>Description</h2>
     * Returns the value of a give line if it contains the target_data. All lines that contain the target_data will be returned
     * @author  Rafael Andaya Cartagena
     * @version 1.0
     * @param file_path target file abosulte file
     * @param target_data the data to match
     * @param case_sensitive the data to match
     * @since 02-24-2023
     */
    public static ArrayList<String> returnLinesDataWithDataAppearanceOf(String file_path, String target_data, boolean case_sensitive) throws IOException {
        ArrayList<String> lines_data = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file_path))) {
            String line_data;
            String lowercase_target_data = case_sensitive ? target_data : target_data.toLowerCase();
            StringBuilder builder = new StringBuilder();
            builder.append(lowercase_target_data);
            while ((line_data = reader.readLine()) != null){
                if(!case_sensitive){
                    line_data = line_data.toLowerCase();
                }
                if(containsWord(line_data,target_data)){
                    lines_data.add(line_data);
                }
            }
        }
        return lines_data;
    }

    public static boolean createDirectory(String directory_path){
        File file = new File(directory_path);
        return file.mkdir();
    }


    private static boolean containsWord(String inputString, String targetWord) {
        String[] words = inputString.split("\\s+");
        for (String word : words) {
            if (word.equalsIgnoreCase(targetWord)) {
                return true;
            }
        }
        return false;
    }
























}
