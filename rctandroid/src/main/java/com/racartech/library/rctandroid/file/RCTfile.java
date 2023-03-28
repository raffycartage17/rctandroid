package com.racartech.library.rctandroid.file;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.webkit.MimeTypeMap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.racartech.library.rctandroid.array.RCTarray;
import com.racartech.library.rctandroid.math.RCTdataSizeConverter;

import org.apache.commons.io.FileUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class RCTfile extends AppCompatActivity {


    public static ArrayList<String> PHYSICAL_DISKS_ROOT_DIR = new ArrayList<>();



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



    public static String readLine(String target_file_path, int target_line_index){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            StringBuilder builder = new StringBuilder();
            int current_line = 0;
            try {
                FileInputStream fis = new FileInputStream(target_file_path);
                BufferedReader br = new BufferedReader(new InputStreamReader(fis, StandardCharsets.UTF_8));
                String line;
                while ((line = br.readLine()) != null) {
                    if(current_line == target_line_index){
                        return line;
                    }else{
                        current_line++;
                    }
                }
                br.close();
            }catch (IOException ignored){}
            return null;
        }else{
            try {
                return Files.lines(Paths.get(target_file_path), StandardCharsets.UTF_8)
                        .skip(target_line_index)
                        .findFirst()
                        .orElse(null);
            } catch (IOException ignored) {}
            return null;
        }
    }


    public static String readLine(File target_file, int target_line_index){

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            StringBuilder builder = new StringBuilder();
            int current_line = 0;
            try {
                FileInputStream fis = new FileInputStream(target_file);
                BufferedReader br = new BufferedReader(new InputStreamReader(fis, StandardCharsets.UTF_8));
                String line;
                while ((line = br.readLine()) != null) {
                    if(current_line == target_line_index){
                        return line;
                    }else{
                        current_line++;
                    }
                }
                br.close();
            }catch (IOException ignored){}
            return null;
        }else{
            try {
                return Files.lines(target_file.toPath(), StandardCharsets.UTF_8)
                        .skip(target_line_index)
                        .findFirst()
                        .orElse(null);
            } catch (IOException ignored) {}
            return null;
        }
    }



    public static String[] readLine(String target_file_path, int[] target_line_index){

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            String[] values = new String[target_line_index.length];
            int current_tli = 0;
            int current_line = 0;
            try {
                FileInputStream fis = new FileInputStream(target_file_path);
                BufferedReader br = new BufferedReader(new InputStreamReader(fis, StandardCharsets.UTF_8));
                String line;
                while ((line = br.readLine()) != null) {
                    if(current_tli == target_line_index.length){
                        break;
                    }
                    if(current_line == target_line_index[current_tli]){
                        values[current_tli] = line;
                        current_tli++;
                    }
                    current_line++;
                }
                br.close();
            }catch (IOException ignored){}
            return values;
        }else{
            AtomicInteger current_tli = new AtomicInteger(0);
            try {
                return Files.lines(Paths.get(target_file_path), StandardCharsets.UTF_8)
                        .filter(line -> IntStream.of(target_line_index).anyMatch(tli -> tli == current_tli.getAndIncrement()))
                        .toArray(String[]::new);
            } catch (IOException ignored) {}
            return new String[target_line_index.length];
        }
    }



    public static String[] readLine(File target_file, int[] target_line_index){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            String[] values = new String[target_line_index.length];
            int current_tli = 0;
            int current_line = 0;
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(target_file), "UTF-8"));
                String line;
                while ((line = br.readLine()) != null) {
                    if (current_tli == target_line_index.length) {
                        break;
                    }
                    if (current_line == target_line_index[current_tli]) {
                        values[current_tli] = line;
                        current_tli++;
                    }
                    current_line++;
                }
                br.close();
            } catch (IOException ignored) {}
            return values;
        } else {
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
                        }
                        current_line++;
                    }
                }
            }catch (IOException ignored){}
            return values;
        }
    }





    public static String[] readLine(String target_file_path, int from_index, int to_index) {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            // Compatible implementation for Android versions prior to Oreo
            int line_count = (to_index - from_index) + 1;
            String[] values = new String[line_count];
            int current_index = 0;
            int current_line = 0;
            try {
                BufferedReader br = new BufferedReader(new FileReader(target_file_path));
                String line;
                while ((line = br.readLine()) != null) {
                    if (current_line > to_index) {
                        break;
                    }
                    if (current_line >= from_index) {
                        values[current_index] = line;
                        current_index++;
                    }
                    current_line++;
                }
                br.close();
            } catch (IOException ignored) {}
            return values;
        } else {
            // Implementation for Android Oreo and above
            int line_count = (to_index - from_index) + 1;
            String[] values = new String[line_count];
            int current_index = 0;
            int current_line = 0;
            try {
                try (BufferedReader br = Files.newBufferedReader(Paths.get(target_file_path), StandardCharsets.UTF_8)) {
                    for (String line = null; (line = br.readLine()) != null; ) {
                        if (current_line > to_index) {
                            break;
                        }
                        if (current_line >= from_index) {
                            values[current_index] = line;
                            current_index++;
                        }
                        current_line++;
                    }
                }
            } catch (IOException ignored) {}
            return values;
        }
    }


    public static String[] readLine(File target_file, int from_index, int to_index) {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            // Compatible implementation for Android versions prior to Oreo
            int line_count = (to_index - from_index) + 1;
            String[] values = new String[line_count];
            int current_index = 0;
            int current_line = 0;
            try {
                BufferedReader br = new BufferedReader(new FileReader(target_file));
                String line;
                while ((line = br.readLine()) != null) {
                    if (current_line > to_index) {
                        break;
                    }
                    if (current_line >= from_index) {
                        values[current_index] = line;
                        current_index++;
                    }
                    current_line++;
                }
                br.close();
            } catch (IOException ignored) {}
            return values;
        } else {
            // Implementation for Android Oreo and above
            int line_count = (to_index - from_index) + 1;
            String[] values = new String[line_count];
            int current_index = 0;
            int current_line = 0;
            try {
                try (BufferedReader br = Files.newBufferedReader(target_file.toPath(), StandardCharsets.UTF_8)) {
                    for (String line = null; (line = br.readLine()) != null; ) {
                        if (current_line > to_index) {
                            break;
                        }
                        if (current_line >= from_index) {
                            values[current_index] = line;
                            current_index++;
                        }
                        current_line++;
                    }
                }
            } catch (IOException ignored) {}
            return values;
        }
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

    public static boolean delete_File(String file_path){
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
                        RCTfile.delete_File(dir_contents[index]);
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
                            RCTfile.delete_File(dir_contents[index]);
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

    public static boolean deleteFile_Exclusive(String path){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            File td_file = new File(path);
            if(td_file.exists()){
                return td_file.delete();
            }else{
                return false;
            }
        }else{
            File td_file = new File(path);
            if(td_file.exists()){
                try {
                    return td_file.delete();
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
