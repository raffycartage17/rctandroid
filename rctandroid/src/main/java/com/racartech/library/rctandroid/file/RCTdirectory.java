package com.racartech.library.rctandroid.file;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.racartech.library.rctandroid.array.RCTarray;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RCTdirectory{


    public final static int SORT_TYPE_DIRECTORY_FIRST = 0;
    public final static int SORT_TYPE_FILE_FIRST = 1;

    public final static int SORT_MODE_ALPHABETICAL_ASCENDING = 0;
    public final static int SORT_MODE_ALPHABETICAL_DESCENDING = 1;
    public final static int SORT_MODE_MODIFIED_TIME_NEWEST_FIRST= 2;
    public final static int SORT_MODE_MODIFIED_TIME_OLDEST_FIRST= 3;
    public final static int SORT_MODE_SIZE_SMALLEST_FIRST= 2;
    public final static int SORT_MODE_SIZE_LARGEST_FIRST= 2;


    public static ArrayList<String> getMountedStorageHomePath(Context app_context){
        ArrayList<String> home_paths = new ArrayList<>();
        //Main Storage
        home_paths.add(RCTfile.getDir_ExternalStorageRoot());
        String sd_card_slot = RCTfile.getExternalSdCardPath(app_context);
        if(sd_card_slot != null){
            home_paths.add(sd_card_slot);
        }
        return null;
    }

    public static List<String> getDisk_ImmediateSubFileDir(String disk_root_path,boolean remove_android_folder){
        List<String> file_dir_list = getImmediateFileAndSubDir_ArrayListString(disk_root_path);
        if(remove_android_folder && file_dir_list != null){
            file_dir_list.remove(disk_root_path.concat("/Android"));
        }
        return file_dir_list;
    }

    public static List<File> getMainStorage_AllSubDirectories(){
        String main_storage_path = RCTfile.getDir_ExternalStorageRoot();
        List<File> all_dir_list = new ArrayList<>();
        List<String> immediate_file_dir_list = getImmediateFileAndSubDir_ArrayListString(main_storage_path);
        if(immediate_file_dir_list != null) {
            immediate_file_dir_list.remove(main_storage_path.concat("/Android"));
        }

        for(int index = 0; index<immediate_file_dir_list.size(); index++){
            try {
                all_dir_list.addAll(Objects.requireNonNull(listAllSubDirectories(new File(immediate_file_dir_list.get(index)))));
            }catch (IOException ignored){}
        }
        return all_dir_list;
    }





    public static long getFileDirCount(String target_dir){
        long file_count = 0;
        File[] immediate_filedir = getImmediateFileAndSubDir_File(target_dir);
        for(int index = 0; index<immediate_filedir.length; index++){
            try {
                if (RCTfile.isPathADirectory(immediate_filedir[index])) {
                    List<File> contents = listAllFilesAndSubDirectories(immediate_filedir[index]);
                    if(contents != null){
                        file_count += contents.size();
                    }
                } else {
                    file_count++;
                }
            }catch (IOException ignored){}
        }
        return file_count;
    }

    public static List<File> listAllFileAndSubDir_Safe_ListFile(String target_dir){
        File[] immediate_filedir = getImmediateFileAndSubDir_File(target_dir);
        List<File> all_filedir_list = new ArrayList<>();
        for(int index = 0; index<immediate_filedir.length; index++){
            try {
                if (RCTfile.isPathADirectory(immediate_filedir[index])) {
                    List<File> contents = listAllFilesAndSubDirectories(immediate_filedir[index]);
                    if(contents != null){
                        all_filedir_list.addAll(contents);
                    }
                } else {
                    all_filedir_list.add(immediate_filedir[index]);
                }
            }catch (IOException ignored){}
        }
        return all_filedir_list;
    }

    public static List<String> listAllFileAndSubDir_Safe_ListString(String target_dir){
        File[] immediate_filedir = getImmediateFileAndSubDir_File(target_dir);
        List<String> all_filedir_list = new ArrayList<>();
        for(int index = 0; index<immediate_filedir.length; index++){
            try {
                if (RCTfile.isPathADirectory(immediate_filedir[index])) {
                    List<String> contents = listAllFilesAndSubDirectories_ListString(immediate_filedir[index].getAbsolutePath());
                    if(contents != null){
                        all_filedir_list.addAll(contents);
                    }
                } else {
                    all_filedir_list.add(immediate_filedir[index].getAbsolutePath());
                }
            }catch (IOException ignored){}
        }
        return all_filedir_list;
    }





    public static void sortDir_Relative(String[] directories){
        for(int index = 0; index< directories.length; index++){
            String current_dir = directories[index];
            String parent_dir_name = RCTfile.getParentDirectory(current_dir);
            String dir_name = RCTfile.getDirectoryName(current_dir);
            directories[index] = dir_name.concat("<>").concat(parent_dir_name);
        }
        Arrays.sort(directories);
        for(int index = 0; index< directories.length; index++){
            String current_path = directories[index];
            String[] splits = current_path.split("<>");
            directories[index] = splits[1].concat("/").concat(splits[0]);
        }
    }

    public static void sortDir_Relative(String[] directories,Comparator<String> comparator){
        for(int index = 0; index< directories.length; index++){
            String current_dir = directories[index];
            String parent_dir_name = RCTfile.getParentDirectory(current_dir);
            String dir_name = RCTfile.getDirectoryName(current_dir);
            directories[index] = dir_name.concat("<>").concat(parent_dir_name);
        }
        Arrays.sort(directories,comparator);
        for(int index = 0; index< directories.length; index++){
            String current_path = directories[index];
            String[] splits = current_path.split("<>");
            directories[index] = splits[1].concat("/").concat(splits[0]);
        }
    }


    public static void sortDir_Relative(ArrayList<String> directories){
        for(int index = 0; index< directories.size(); index++){
            String current_dir = directories.get(index);
            String parent_dir_name = RCTfile.getParentDirectory(current_dir);
            String dir_name = RCTfile.getDirectoryName(current_dir);
            directories.set(index,dir_name.concat("<>").concat(parent_dir_name));
        }
        Collections.sort(directories);
        for(int index = 0; index< directories.size(); index++){
            String current_path = directories.get(index);
            String[] splits = current_path.split("<>");
            directories.set(index,splits[1].concat("/").concat(splits[0]));
        }
    }
    public static void sortDir_Relative(ArrayList<String> directories,Comparator<String> comparator){

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            for(int index = 0; index< directories.size(); index++){
                String current_dir = directories.get(index);
                String parent_dir_name = RCTfile.getParentDirectory(current_dir);
                String dir_name = RCTfile.getDirectoryName(current_dir);
                directories.set(index,dir_name.concat("<>").concat(parent_dir_name));
            }

            Collections.sort(directories, comparator);

            for(int index = 0; index< directories.size(); index++){
                String current_path = directories.get(index);
                String[] splits = current_path.split("<>");
                directories.set(index,splits[1].concat("/").concat(splits[0]));
            }

        }else{
            for(int index = 0; index< directories.size(); index++){
                String current_dir = directories.get(index);
                String parent_dir_name = RCTfile.getParentDirectory(current_dir);
                String dir_name = RCTfile.getDirectoryName(current_dir);
                directories.set(index,dir_name.concat("<>").concat(parent_dir_name));
            }
            directories.sort(comparator);
            for(int index = 0; index< directories.size(); index++){
                String current_path = directories.get(index);
                String[] splits = current_path.split("<>");
                directories.set(index,splits[1].concat("/").concat(splits[0]));
            }
        }

    }







    public static ArrayList<String> getAllImageDir(Context app_context,String directory){
        String[] immediate_items = getImmediateFileAndSubDir(directory);
        String android_folder = RCTfile.getDir_ExternalStorageRoot().concat("/Android");
        ArrayList<String> new_dir_list = new ArrayList<>();
        if(immediate_items != null){
            if (immediate_items.length > 0) {
                for (int index = 0; index < immediate_items.length; index++) {
                    if (RCTfile.isPathADirectory(immediate_items[index])) {
                        if (!immediate_items[index].equals(android_folder)) {
                            try {
                                List<String> all_items = listAllFilesAndSubDirectories_ListString(immediate_items[index]);
                                for (int dex = 0; dex < all_items.size(); dex++) {
                                    if (RCTfile.isPathAFile(all_items.get(dex))) {
                                        if (RCTfile.isFile_Image(all_items.get(dex))) {
                                            String parent_dir = RCTfile.getParentDirectory(all_items.get(dex));
                                            if (!new_dir_list.contains(parent_dir)) {
                                                new_dir_list.add(parent_dir);
                                            }
                                        }
                                    }
                                }
                            } catch (IOException ignored) {
                            }
                        }
                    }
                }
            }
        }




        return new_dir_list;
    }

    public static ArrayList<String> getAllVideoDir(String directory){
        String[] immediate_items = getImmediateFileAndSubDir(directory);
        String android_folder = RCTfile.getDir_ExternalStorageRoot().concat("/Android");
        ArrayList<String> new_dir_list = new ArrayList<>();
        if(immediate_items != null){
            if (immediate_items.length > 0) {
                for (int index = 0; index < immediate_items.length; index++) {
                    if (RCTfile.isPathADirectory(immediate_items[index])) {
                        if (!immediate_items[index].equals(android_folder)) {
                            try {
                                List<String> all_items = listAllFilesAndSubDirectories_ListString(immediate_items[index]);
                                for (int dex = 0; dex < all_items.size(); dex++) {
                                    if (RCTfile.isPathAFile(all_items.get(dex))) {
                                        if (RCTfile.isFile_Video(all_items.get(dex))) {
                                            String parent_dir = RCTfile.getParentDirectory(all_items.get(dex));
                                            if (!new_dir_list.contains(parent_dir)) {
                                                new_dir_list.add(parent_dir);
                                            }
                                        }
                                    }
                                }
                            } catch (IOException ignored) {
                            }
                        }
                    }
                }
            }
        }
        return new_dir_list;
    }

    public static ArrayList<String> getAllAudioDir(String directory){
        String[] immediate_items = getImmediateFileAndSubDir(directory);
        String android_folder = RCTfile.getDir_ExternalStorageRoot().concat("/Android");
        ArrayList<String> new_dir_list = new ArrayList<>();
        if(immediate_items != null){
            if (immediate_items.length > 0) {
                for (int index = 0; index < immediate_items.length; index++) {
                    if (RCTfile.isPathADirectory(immediate_items[index])) {
                        if (!immediate_items[index].equals(android_folder)) {
                            try {
                                List<String> all_items = listAllFilesAndSubDirectories_ListString(immediate_items[index]);
                                for (int dex = 0; dex < all_items.size(); dex++) {
                                    if (RCTfile.isPathAFile(all_items.get(dex))) {
                                        if (RCTfile.isFile_Audio(all_items.get(dex))) {
                                            String parent_dir = RCTfile.getParentDirectory(all_items.get(dex));
                                            if (!new_dir_list.contains(parent_dir)) {
                                                new_dir_list.add(parent_dir);
                                            }
                                        }
                                    }
                                }
                            } catch (IOException ignored) {
                            }
                        }
                    }
                }
            }
        }
        return new_dir_list;
    }

    public static ArrayList<String> getAllDocumentDir(String directory){
        String[] immediate_items = getImmediateFileAndSubDir(directory);
        String android_folder = RCTfile.getDir_ExternalStorageRoot().concat("/Android");
        ArrayList<String> new_dir_list = new ArrayList<>();
        if(immediate_items != null){
            if (immediate_items.length > 0) {
                for (int index = 0; index < immediate_items.length; index++) {
                    if (RCTfile.isPathADirectory(immediate_items[index])) {
                        if (!immediate_items[index].equals(android_folder)) {
                            try {
                                List<String> all_items = listAllFilesAndSubDirectories_ListString(immediate_items[index]);
                                for (int dex = 0; dex < all_items.size(); dex++) {
                                    if (RCTfile.isPathAFile(all_items.get(dex))) {
                                        if (RCTfile.isFile_Document(all_items.get(dex))) {
                                            String parent_dir = RCTfile.getParentDirectory(all_items.get(dex));
                                            if (!new_dir_list.contains(parent_dir)) {
                                                new_dir_list.add(parent_dir);
                                            }
                                        }
                                    }
                                }
                            } catch (IOException ignored) {
                            }
                        }
                    }
                }
            }
        }
        return new_dir_list;
    }



    public static void sortList(String[] filedir_list, boolean directory_first){
        String[] temp_list = new String[filedir_list.length];
        temp_list = RCTarray.copy(filedir_list);
        int current_index = 0;
        if(directory_first){
            for(int index = 0; index<temp_list.length; index++){
                if(RCTfile.isPathADirectory(temp_list[index])){
                    filedir_list[current_index] = temp_list[index];
                    current_index++;
                }
            }
            for(int index = 0; index<temp_list.length; index++){
                if(RCTfile.isPathAFile(temp_list[index])){
                    filedir_list[current_index] = temp_list[index];
                    current_index++;
                }
            }
        }else{
            for(int index = 0; index<temp_list.length; index++){
                if(RCTfile.isPathAFile(temp_list[index])){
                    filedir_list[current_index] = temp_list[index];
                    current_index++;
                }
            }
            for(int index = 0; index<temp_list.length; index++){
                if(RCTfile.isPathADirectory(temp_list[index])){
                    filedir_list[current_index] = temp_list[index];
                    current_index++;
                }
            }
        }
    }

    public static void sortList(String[] filedir_list, Comparator<String> comparator, boolean directory_first){
        String[] temp_list = new String[filedir_list.length];
        Arrays.sort(temp_list,comparator);
        temp_list = RCTarray.copy(filedir_list);
        int current_index = 0;
        if(directory_first){
            for(int index = 0; index<temp_list.length; index++){
                if(RCTfile.isPathADirectory(temp_list[index])){
                    filedir_list[current_index] = temp_list[index];
                    current_index++;
                }
            }
            for(int index = 0; index<temp_list.length; index++){
                if(RCTfile.isPathAFile(temp_list[index])){
                    filedir_list[current_index] = temp_list[index];
                    current_index++;
                }
            }
        }else{
            for(int index = 0; index<temp_list.length; index++){
                if(RCTfile.isPathAFile(temp_list[index])){
                    filedir_list[current_index] = temp_list[index];
                    current_index++;
                }
            }
            for(int index = 0; index<temp_list.length; index++){
                if(RCTfile.isPathADirectory(temp_list[index])){
                    filedir_list[current_index] = temp_list[index];
                    current_index++;
                }
            }
        }
    }


    public static void sortList(ArrayList<String> filedir_list, boolean directory_first){
        if(directory_first){
            ArrayList<String> temp_list = new ArrayList<>();
            temp_list.addAll(filedir_list);
            filedir_list.clear();
            for(int index = 0; index<temp_list.size(); index++){
                if(RCTfile.isPathADirectory(temp_list.get(index))){
                    filedir_list.add(temp_list.get(index));
                }
            }
            for(int index = 0; index<temp_list.size(); index++){
                if(RCTfile.isPathAFile(temp_list.get(index))){
                    filedir_list.add(temp_list.get(index));
                }
            }
        }else{
            ArrayList<String> temp_list = new ArrayList<>();
            temp_list.addAll(filedir_list);
            filedir_list.clear();
            for(int index = 0; index<temp_list.size(); index++){
                if(RCTfile.isPathAFile(temp_list.get(index))){
                    filedir_list.add(temp_list.get(index));
                }
            }
            for(int index = 0; index<temp_list.size(); index++){
                if(RCTfile.isPathADirectory(temp_list.get(index))){
                    filedir_list.add(temp_list.get(index));
                }
            }
        }
    }

    public static void sortList(ArrayList<String> filedir_list,Comparator<String> comparator,boolean directory_first){
        filedir_list.sort(comparator);
        if(directory_first){
            ArrayList<String> temp_list = new ArrayList<>();
            temp_list.addAll(filedir_list);
            filedir_list.clear();
            for(int index = 0; index<temp_list.size(); index++){
                if(RCTfile.isPathADirectory(temp_list.get(index))){
                    filedir_list.add(temp_list.get(index));
                }
            }
            for(int index = 0; index<temp_list.size(); index++){
                if(RCTfile.isPathAFile(temp_list.get(index))){
                    filedir_list.add(temp_list.get(index));
                }
            }
        }else{
            ArrayList<String> temp_list = new ArrayList<>();
            temp_list.addAll(filedir_list);
            filedir_list.clear();
            for(int index = 0; index<temp_list.size(); index++){
                if(RCTfile.isPathAFile(temp_list.get(index))){
                    filedir_list.add(temp_list.get(index));
                }
            }
            for(int index = 0; index<temp_list.size(); index++){
                if(RCTfile.isPathADirectory(temp_list.get(index))){
                    filedir_list.add(temp_list.get(index));
                }
            }
        }
    }



    /*
    public static void sortList(ArrayList<String> filedir_list,int sort_type,int sort_mode,boolean show_hidden_files){
        ArrayList<String> directories = new ArrayList<>();
        ArrayList<String> files = new ArrayList<>();

        for(int index = 0; index<filedir_list.size(); index++){
            if(RCTfile.isPathADirectory(filedir_list.get(index))){
                directories.add(filedir_list.get(index));
            }else{
                files.add(filedir_list.get(index));
            }
        }
        filedir_list.clear();
        directories.sort(String.CASE_INSENSITIVE_ORDER);
        files.sort(String.CASE_INSENSITIVE_ORDER);

        switch(sort_mode){
            case SORT_MODE_ALPHABETICAL_ASCENDING:
        }
    }
     */




    public static long getFileCount(String dir_path){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            File directory = new File(dir_path);
            if (directory.exists()) {
                return countFiles(directory);
            }
            return 0;
        }else{
            Path directory_path = new File(dir_path).toPath();
            try{
                return (int)Files.walk(directory_path)
                        .parallel()
                        .filter(p -> !p.toFile().isDirectory())
                        .count();
            }catch(IOException ignored){}
            return 0;
        }

    }

    private static int countFiles(File directory) {
        int count = 0;
        if (directory != null && directory.exists()) {
            if (directory.isDirectory()) {
                File[] files = directory.listFiles();
                if (files != null) {
                    for (File file : files) {
                        if (file.isDirectory()) {
                            count += countFiles(file);
                        } else {
                            count++;
                        }
                    }
                }
            } else {
                count = 1;
            }
        }
        return count;
    }

    public static int getFileCount(String[] dir_path){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            int total_files = 0;
            for(int index = 0; index<dir_path.length; index++){
                if(isPathADirectory(dir_path[index])){
                    File directory = new File(dir_path[index]);
                    if (directory.exists() && directory.isDirectory()) {
                        File[] files = directory.listFiles();
                        for (int i = 0; i < files.length; i++) {
                            if (files[i].isFile()) {
                                total_files++;
                            }
                        }
                    }
                }else{
                    total_files++;
                }
            }
            return total_files;
        }else{
            int total_files = 0;
            for(int index = 0; index<dir_path.length; index++){
                if(isPathADirectory(dir_path[index])){
                    Path directory_path = new File(dir_path[index]).toPath();
                    try {
                        total_files += (int) Files.walk(directory_path)
                                .parallel()
                                .filter(p -> !p.toFile().isDirectory())
                                .count();
                    } catch (IOException ignored) {
                    }
                }else{
                    total_files++;
                }
            }
            return total_files;
        }
    }

    public static int getFileCount(ArrayList<String> dir_path){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            int total_files = 0;
            for(int index = 0; index<dir_path.size(); index++){
                if(isPathADirectory(dir_path.get(index))){
                    File directory = new File(dir_path.get(index));
                    if (directory.exists() && directory.isDirectory()) {
                        File[] files = directory.listFiles();
                        for (int i = 0; i < files.length; i++) {
                            if (files[i].isFile()) {
                                total_files++;
                            }
                        }
                    }
                }else{
                    total_files++;
                }
            }
            return total_files;
        }else{
            int total_files = 0;
            for(int index = 0; index<dir_path.size(); index++){
                if(isPathADirectory(dir_path.get(index))){
                    Path directory_path = new File(dir_path.get(index)).toPath();
                    try {
                        total_files += (int) Files.walk(directory_path)
                                .parallel()
                                .filter(p -> !p.toFile().isDirectory())
                                .count();
                    } catch (IOException ignored) {
                    }
                }else{
                    total_files++;
                }
            }
            return total_files;
        }
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
    /**
     * <h2>Description</h2>
     * Creates every section of the target_path if that section didnt exist yet. User must specify if the last section is a file via true, and false it is a directory
     * @author  Rafael Andaya Cartagena
     * @version 1.0
     * @param target_path the absolute path of the directory to be created
     * @param isLastSectionFile put true if the last section is meant to be a file, false otherwise
     * @since 04-30-2021
     */
    public static void createPathSectionBySection(String target_path, boolean isLastSectionFile) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            String[] target_path_sections = target_path.split("/");
            String recon_path = "";
            for (String name : target_path_sections) {
                if (!name.isEmpty()) {
                    recon_path += "/" + name;
                    if (!RCTfile.doesDirectoryExist(recon_path)) {
                        createDirectory(recon_path);
                    }
                }
            }
            if (isLastSectionFile && !target_path.endsWith("/")) {
                RCTfile.createFile(RCTfile.getParentDirectory(target_path), RCTfile.getFileName(target_path));
            }
        } else {
            String[] target_path_sections = target_path.split("/");
            if (!target_path_sections[0].equals(".")) {
                Path p = Paths.get(target_path);
                int name_count = p.getNameCount();
                StringBuilder recon_path = new StringBuilder();
                for (Path name : p) {
                    recon_path.append("/").append(name);
                    if (!name.equals(p.getFileName())) {
                        createDirectory(recon_path.toString());
                    } else {
                        if (isLastSectionFile) {
                            RCTfile.createFile(RCTfile.getParentDirectory(recon_path.toString()), RCTfile.getFileName(recon_path.toString()));
                        } else {
                            createDirectory(recon_path.toString());
                        }
                    }
                }
            } else {
                Path p = Paths.get(target_path);
                int name_count = p.getNameCount();
                StringBuilder recon_path = new StringBuilder(".");
                for (Path name : p) {
                    recon_path.append("/").append(name);
                    if (!name.equals(p.getFileName())) {
                        createDirectory(recon_path.toString());
                    } else {
                        if (isLastSectionFile) {
                            RCTfile.createFile(RCTfile.getParentDirectory(recon_path.toString()), RCTfile.getFileName(recon_path.toString()));
                        } else {
                            createDirectory(recon_path.toString());
                        }
                    }
                }
            }
        }
    }





    /**
     * <h2>Description</h2>
     * Delete the target directory
     * @author  Rafael Andaya Cartagena
     * @version 1.0
     * @since 05-01-2021
     */
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

    public static void moveDirectory(String src_dir,String dest_dir){
        try{
            FileUtils.moveDirectory(new File(src_dir),new File(dest_dir.concat("/").concat(RCTfile.getDirectoryName(src_dir))));
        }catch (IOException ignored){}

    }
    public static String replaceParentDir(String old_parent_dir,String new_parent_dir,String target_path){
        return target_path.replaceFirst(old_parent_dir,new_parent_dir);
    }



    public static void moveDirectoryContents(String src_dir,String dest_dir){
        copyDirectoryContents(src_dir,dest_dir);
        deleteDirectory(src_dir);
    }



    public static void copyDirectoryContents(String src, String dest) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            // Use recursive function to copy directory contents
            File sourceDir = new File(src);
            File destinationDir = new File(dest, sourceDir.getName());
            if (sourceDir.isDirectory()) {
                createDirectory(destinationDir.getAbsolutePath());
                String[] children = sourceDir.list();
                for (int i = 0; i < children.length; i++) {
                    copyDirectoryContents(new File(sourceDir, children[i]).getPath(), destinationDir.getAbsolutePath());
                }
            } else {
                RCTfile.copyFileToDirectory(src, dest);
            }
        }else{
            try {
                Files.walk(Paths.get(src)).forEach(a -> {
                    Path b = Paths.get(dest, a.toString().substring(src.length()));
                    try {
                        if (!a.toString().equals(src))
                            Files.copy(a, b, StandardCopyOption.REPLACE_EXISTING);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }catch (IOException ignored){}
        }
    }

    public static void copyDirectory(String src, String dest_dir){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            if (doesDirectoryExist(src)) {
                File src_dir = new File(src);
                File[] files = src_dir.listFiles();
                if (files != null) {
                    for (File file : files) {
                        String dest_file_path = dest_dir + "/" + file.getName();
                        if (file.isDirectory()) {
                            createDirectory(dest_file_path);
                            copyDirectory(file.getAbsolutePath(), dest_file_path);
                        } else {
                            RCTfile.copyFileToDirectory(file.getAbsolutePath(), dest_file_path);
                        }
                    }
                }
            }
        } else {
            String directory_name = RCTfile.getDirectoryName(src);
            if(directory_name != null) {
                String dest = dest_dir.concat("/").concat(directory_name);
                if (!doesDirectoryExist(dest)) {
                    createPathSectionBySection(dest, false);
                }
                try {
                    Files.walk(Paths.get(src)).forEach(a -> {
                        Path b = Paths.get(dest, a.toString().substring(src.length()));
                        try {
                            if (!a.toString().equals(src))
                                Files.copy(a, b, StandardCopyOption.REPLACE_EXISTING);
                        } catch (IOException ignored) {
                        }
                    });
                } catch (IOException ignored) {
                }
            }
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

    public static String[] getImmediateFileAndSubDir(String parent_dir) {
        File[] files = new File(parent_dir).listFiles();
        try {
            String[] list = new String[files.length];
            for (int i = 0; i < files.length; i++) {
                list[i] = files[i].getAbsolutePath();
            }
            return list;
        }catch (NullPointerException e){
            return null;
        }
    }

    public static File[] getImmediateFileAndSubDir_FileArray(String parent_dir){
        File[] the_files = new File(parent_dir).listFiles();
        if(the_files != null){
            return the_files;
        }else{
            return new File[0];
        }
    }

    public static ArrayList<File> getImmediateFileAndSubDir_FileArrayList(String parent_dir) {
        File[] the_files = new File(parent_dir).listFiles();
        ArrayList<File> fileList = new ArrayList<>();
        if (the_files != null) {
            for (File file : the_files) {
                fileList.add(file);
            }
        }
        return fileList;
    }




    public static List<String> getImmediateFileAndSubDir_ListString(String parent_dir) {
        File[] files = new File(parent_dir).listFiles();
        try {
            List<String> the_list = new ArrayList<>();
            for (int i = 0; i < files.length; i++) {
                the_list.add(files[i].getAbsolutePath());
            }
            return the_list;
        }catch (NullPointerException e){
            return null;
        }
    }

    public static ArrayList<String> getImmediateFileAndSubDir_ArrayListString(String parent_dir) {
        File[] files = new File(parent_dir).listFiles();
        try {
            ArrayList<String> the_list = new ArrayList<>();
            for (int i = 0; i < files.length; i++) {
                the_list.add(files[i].getAbsolutePath());
            }
            return the_list;
        }catch (NullPointerException e){
            return null;
        }
    }

    public static ArrayList<String> getImmediateFileAndSubDir_ArrayListString(String parent_dir,String exclude,boolean is_exclude_a_path) {
        //is_exclude_a_path = if true, will treat String exclude as a path, if false, will treat is as a name
        File[] files = new File(parent_dir).listFiles();
        try {
            ArrayList<String> the_list = new ArrayList<>();
            for (int i = 0; i < files.length; i++) {
                the_list.add(files[i].getAbsolutePath());
            }

            if(exclude != null){
                if(is_exclude_a_path){
                    the_list.remove(exclude);
                }else{
                    String new_exclude = parent_dir.concat("/").concat(exclude);
                    the_list.remove(new_exclude);
                }
            }
            return the_list;
        }catch (NullPointerException e){
            return null;
        }
    }

    public static ArrayList<String> getImmediateFileAndSubDir_ArrayListString(File parent_dir) {
        File[] files = parent_dir.listFiles();
        try {
            ArrayList<String> the_list = new ArrayList<>();
            for (int i = 0; i < files.length; i++) {
                the_list.add(files[i].getAbsolutePath());
            }
            return the_list;
        }catch (NullPointerException e){
            return null;
        }
    }





    public static File[] getImmediateFileAndSubDir_File(String parent_dir) {
        File[] files = new File(parent_dir).listFiles();
        return files;
    }
    public static int getImmediateItemCount(String parent_dir) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            // Generate code compatible with Android 4.4 (API 19) here
            File directory = new File(parent_dir);
            File[] files = directory.listFiles();
            if (files != null) {
                return files.length;
            } else {
                return 0;
            }
        } else {
            try (Stream<Path> files = Files.list(Paths.get(parent_dir))) {
                return (int) files.count();
            } catch (IOException ignored) {
            }
            return 0;
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



    public static long getLastModifiedTime_Millis(String target_path){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            File file = new File(target_path);
            if(file.exists()) {
                return file.lastModified();
            }
            return 0;

        }else{
            try {
                return Files.getLastModifiedTime(Paths.get(target_path)).toMillis();
            } catch (IOException ignored){
                return 0;
            }
        }
    }









    public static String[] getImmediateSubDir(String directory){
        File target_directory = new File(directory);
        String[] directories = target_directory.list(new FilenameFilter() {
            @Override
            public boolean accept(File current, String name) {
                return new File(current, name).isDirectory();
            }
        });
        return directories;
    }

    public static List<String> getImmediateSubDir_List(String directory){
        File target_directory = new File(directory);
            List<String> directories = Arrays.asList(Objects.requireNonNull(target_directory.list(new FilenameFilter() {
                @Override
                public boolean accept(File current, String name) {
                    return new File(current, name).isDirectory();
                }
            })));
        return directories;
    }


    private static String fixExtension(String extension){
        if(extension.charAt(0)!='.'){
            return ".".concat(extension);
        }
        return extension;
    }

    public static String[] getAllSubDirectories(String target_directory){
        String[] all_files = new String[0];
        int directory_count = 0;
        try{
            all_files = listAllFilesAndSubDirectories(target_directory);
            for(int index = 0; index< all_files.length; index++){
                if(RCTfile.isPathADirectory(all_files[index])){
                    directory_count++;
                }
            }
            String[] directory_list = new String[directory_count];
            int current_index = 0;
            for(int index = 0; index< all_files.length; index++){
                if(RCTfile.isPathADirectory(all_files[index])){
                    directory_list[current_index] = all_files[index];
                    current_index++;
                }
            }
            return directory_list;
        }catch(IOException ignored){}
        return null;
    }
    public static String[] getAllFiles(String target_directory,boolean include_file_in_all_sub_directories){
        String[] all_files = new String[0];
        int file_count = 0;
        try{
            all_files = listAllFilesAndSubDirectories(target_directory);
            for(int index = 0; index< all_files.length; index++){
                if(RCTfile.isPathAFile(all_files[index])){
                    file_count++;
                }
            }
            String[] directory_list = new String[file_count];
            int current_index = 0;
            for(int index = 0; index< all_files.length; index++){
                if(RCTfile.isPathAFile(all_files[index])){
                    directory_list[current_index] = all_files[index];
                    current_index++;
                }
            }
            return directory_list;
        }catch(IOException ignored){}
        return null;
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

    public static boolean doesDirectoryExist(String path) {
        File folder = new File(path);
        return folder.exists();
    }
    public static boolean createDirectory(String directory_path){
        File file = new File(directory_path);
        return file.mkdir();
    }



    /**
     * <h2>Description</h2>
     * Returns all the absolute path of immediate files and sub directories of the parent folder. Exluding all files inside the sub directories and sub dir of immidiate sub directory
     * @author  Rafael Andaya Cartagena
     * @version 1.0
     * @param parent_directory_path absolute path of the target parent folder
     * @since 05-01-2021
     */
    public static String[] getFileList(String parent_directory_path, String file_extension) {
        String extension = fixExtension(file_extension);
        File f = new File(parent_directory_path);
        FilenameFilter filter = (f1, name) -> name.endsWith(extension);
        File[] files = f.listFiles(filter);
        assert files != null;
        String[] list = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            list[i] = files[i].toString();
        }
        return list;
    }


    /**
     * <h2>Description</h2>
     * Returns all files inside the parent_directory , excluding su_directories and file of the sib directories
     * @author  Rafael Andaya Cartagena
     * @version 1.0
     * @param parent_directory_path absolute path of the target parent folder
     * @since 05-03-2021
     */
    public static String[] getImmediateFileList_ExludeSubDir(String parent_directory_path) {
        File f = new File(parent_directory_path);
        File[] files = f.listFiles();
        assert files != null;
        String[] file_list = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            file_list[i] = files[i].toString();
        }
        boolean[] is_files = new boolean[file_list.length];
        for(int index = 0; index< file_list.length; index++){
            is_files[index] = files[index].isFile();
        }
        return RCTarray.deleteIndexesBasedOnBooleanArray(file_list,is_files);
    }

    public static String[] getImmediateImageFileList(String parent_directory_path) {
        File f = new File(parent_directory_path);
        File[] files = f.listFiles();
        assert files != null;
        String[] file_list = new String[files.length];
        for (int index = 0; index < files.length; index++) {
            file_list[index] = files[index].toString();
        }

        boolean[] is_files = new boolean[file_list.length];
        for(int index = 0; index< file_list.length; index++){
            if(files[index].isFile()){
                is_files[index] = RCTfile.isExtension_Image(RCTfile.getFileExtension(files[index]).toLowerCase());
            }
        }
        return RCTarray.deleteIndexesBasedOnBooleanArray(file_list,is_files);
    }

    public static ArrayList<String> getImmediateImageFileList_ArrayList(String parent_directory_path) {
        File f = new File(parent_directory_path);
        File[] files = f.listFiles();
        assert files != null;
        ArrayList<String> file_list = new ArrayList<>();
        for (int index = 0; index < files.length; index++) {
            if(files[index].isFile()){
                if(RCTfile.isExtension_Image(RCTfile.getFileExtension(files[index]).toLowerCase())){
                    file_list.add(files[index].toString());
                }
            }
        }
        return file_list;
    }







    public static String[] getImmediateVideoFileList(String parent_directory_path) {
        File f = new File(parent_directory_path);
        File[] files = f.listFiles();
        assert files != null;
        String[] file_list = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            file_list[i] = files[i].toString();
        }
        boolean[] is_files = new boolean[file_list.length];
        for(int index = 0; index< file_list.length; index++){
            if(files[index].isFile()){
                is_files[index] = RCTfile.isExtension_Video(RCTfile.getFileExtension(files[index]).toLowerCase());
            }
        }
        return RCTarray.deleteIndexesBasedOnBooleanArray(file_list,is_files);
    }

    public static ArrayList<String> getImmediateVideoFileList_ArrayList(String parent_directory_path) {
        File f = new File(parent_directory_path);
        File[] files = f.listFiles();
        assert files != null;
        ArrayList<String> file_list = new ArrayList<>();
        for (int index = 0; index < files.length; index++) {
            if(files[index].isFile()){
                if(RCTfile.isExtension_Video(RCTfile.getFileExtension(files[index]).toLowerCase())){
                    file_list.add(files[index].toString());
                }
            }
        }
        return file_list;
    }




    public static String[] getImmediateAudioFileList(String parent_directory_path) {
        File f = new File(parent_directory_path);
        File[] files = f.listFiles();
        assert files != null;
        String[] file_list = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            file_list[i] = files[i].toString();
        }
        boolean[] is_files = new boolean[file_list.length];
        for(int index = 0; index< file_list.length; index++){
            if(files[index].isFile()){
                is_files[index] = RCTfile.isExtension_Audio(RCTfile.getFileExtension(files[index]).toLowerCase());
            }
        }
        return RCTarray.deleteIndexesBasedOnBooleanArray(file_list,is_files);
    }


    public static String[] getImmediateDocumentsFileList(String parent_directory_path) {
        File f = new File(parent_directory_path);
        File[] files = f.listFiles();
        assert files != null;
        String[] file_list = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            file_list[i] = files[i].toString();
        }
        boolean[] is_files = new boolean[file_list.length];
        for(int index = 0; index< file_list.length; index++){
            if(files[index].isFile()){
                is_files[index] = RCTfile.isExtension_Documents(RCTfile.getFileExtension(files[index]).toLowerCase());
            }
        }
        return RCTarray.deleteIndexesBasedOnBooleanArray(file_list,is_files);
    }



    public static ArrayList<File> getImmediateImageFile_FileArrayList(String parent_directory_path){
        File f = new File(parent_directory_path);
        File[] files = f.listFiles();
        ArrayList<File> the_files = new ArrayList<>();
        if(files != null){
            for(int index = 0; index < files.length; index++){
                if(RCTfile.isPathAFile(files[index])){
                    if(RCTfile.isFile_Image(files[index])){
                        the_files.add(files[index]);
                    }
                }
            }
        }
        return the_files;
    }
    public static ArrayList<File> getImmediateVideoFile_FileArrayList(String parent_directory_path){
        File f = new File(parent_directory_path);
        File[] files = f.listFiles();
        ArrayList<File> the_files = new ArrayList<>();
        if(files != null){
            for(int index = 0; index < files.length; index++){
                if(RCTfile.isPathAFile(files[index])){
                    if(RCTfile.isFile_Video(files[index])){
                        the_files.add(files[index]);
                    }
                }
            }
        }
        return the_files;
    }
    public static ArrayList<File> getImmediateAudioFile_FileArrayList(String parent_directory_path){
        File f = new File(parent_directory_path);
        File[] files = f.listFiles();
        ArrayList<File> the_files = new ArrayList<>();
        if(files != null){
            for(int index = 0; index < files.length; index++){
                if(RCTfile.isPathAFile(files[index])){
                    if(RCTfile.isFile_Audio(files[index])){
                        the_files.add(files[index]);
                    }
                }
            }
        }
        return the_files;
    }

    public static ArrayList<File> getImmediateDocumentFile_FileArrayList(String parent_directory_path){
        File f = new File(parent_directory_path);
        File[] files = f.listFiles();
        ArrayList<File> the_files = new ArrayList<>();
        if(files != null){
            for(int index = 0; index < files.length; index++){
                if(RCTfile.isPathAFile(files[index])){
                    if(RCTfile.isFile_Document(files[index])){
                        the_files.add(files[index]);
                    }
                }
            }
        }
        return the_files;
    }




    /**
     * <h2>Description</h2>
     * Returns all files
     * @author  Rafael Andaya Cartagena
     * @version 1.0
     * @param parent_directory_path absolute path of the target parent folder
     * @since 05-03-2021
     */
    public static String[] listAllFilesAndSubDirectories(String parent_directory_path) throws IOException, NullPointerException {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {

            ArrayList<String> fileList = new ArrayList<>();
            Stack<File> stack = new Stack<>();
            File root = new File(parent_directory_path);
            if (!root.isDirectory()) {
                return null;
            }
            stack.push(root);

            while (!stack.isEmpty()) {
                File currentFile = stack.pop();
                if (currentFile.isDirectory()) {
                    fileList.add(currentFile.getAbsolutePath());
                    File[] files = currentFile.listFiles();
                    if (files != null && files.length > 0) {
                        for (File file : files) {
                            stack.push(file);
                        }
                    }
                } else {
                    fileList.add(currentFile.getAbsolutePath());
                }
            }

            return fileList.toArray(new String[0]);
        } else {
            try {
                Object[] filesAndDirectories = null;
                Path start = Paths.get(parent_directory_path);
                try (Stream<Path> stream = Files.walk(start, Integer.MAX_VALUE)) {
                    filesAndDirectories = stream
                            .map(String::valueOf)
                            .sorted().toArray();
                }
                return Arrays.copyOf(Objects.requireNonNull(filesAndDirectories), filesAndDirectories.length, String[].class);
            } catch (UncheckedIOException | NullPointerException ignored) {
                return null;
            }
        }
    }


    public static List<String> listAllFilesAndSubDirectories_ListString(String parent_directory_path) throws IOException, UncheckedIOException, NullPointerException {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            try {
                ArrayList<String> filesAndDirectories = new ArrayList<>();
                File[] files = new File(parent_directory_path).listFiles();
                if (files != null) {
                    for (File file : files) {
                        filesAndDirectories.add(file.getAbsolutePath());
                        if (file.isDirectory()) {
                            List<String> subDirFiles = listAllFilesAndSubDirectories_ListString(file.getAbsolutePath());
                            if (subDirFiles != null) {
                                filesAndDirectories.addAll(subDirFiles);
                            }
                        }
                    }
                }
                return filesAndDirectories;
            } catch (NullPointerException ignored) {
                return null;
            }
        } else {
            try {
                Path the_path = new File(parent_directory_path).toPath();
                try (Stream<Path> stream = Files.walk(the_path, Integer.MAX_VALUE)) {
                    return stream.map(String::valueOf).sorted().collect(Collectors.toList());
                }
            } catch (UncheckedIOException ignored) {
                return null;
            }
        }

    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public static List<String> listAllFilesAndSubDirectories(Path the_path) throws IOException {
        List<String> files = new ArrayList<>();
        try (Stream<Path> stream = Files.walk(the_path, Integer.MAX_VALUE)) {
            return stream.map(String::valueOf).sorted().collect(Collectors.toList());
        } catch (UncheckedIOException ignored) {
            return null;
        }
    }






    public static List<File> listAllFilesAndSubDirectories(File directory) throws IOException{
        List<File> files = new ArrayList<>();

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {

            Stack<File> stack = new Stack<>();
            stack.push(directory);

            while (!stack.empty()) {
                File dir = stack.pop();
                File[] dirFiles = dir.listFiles();
                if (dirFiles != null) {
                    for (File file : dirFiles) {
                        if (file.isDirectory()) {
                            stack.push(file);
                        }
                        files.add(file);
                    }
                }
            }

            return files;

        } else {
            try {
                Path start = directory.toPath();
                try (Stream<Path> stream = Files.walk(start, Integer.MAX_VALUE)) {
                    return stream.map(Path::toFile).sorted().collect(Collectors.toList());
                }
            } catch (UncheckedIOException ignored) {
                return null;
            }
        }
    }

    public static ArrayList<File> listAllFilesAndSubDirectories_ArrayList(File directory) throws IOException{
        ArrayList<File> files = new ArrayList<>();

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {

            Stack<File> stack = new Stack<>();
            stack.push(directory);

            while (!stack.empty()) {
                File dir = stack.pop();
                File[] dirFiles = dir.listFiles();
                if (dirFiles != null) {
                    for (File file : dirFiles) {
                        if (file.isDirectory()) {
                            stack.push(file);
                        }
                        files.add(file);
                    }
                }
            }

            return files;

        } else {
            try {
                Path start = directory.toPath();
                try (Stream<Path> stream = Files.walk(start, Integer.MAX_VALUE)) {
                    return (ArrayList<File>) stream.map(Path::toFile).sorted().collect(Collectors.toList());
                }
            } catch (UncheckedIOException ignored) {
                return null;
            }
        }
    }

    public static List<File> listAllSubDirectories(File directory) throws IOException{
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            List<File> directories = new ArrayList<>();
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        directories.add(file);
                        directories.addAll(listAllSubDirectories(file));
                    }
                }
            }
            return directories;
        }else{
            try {
                Path start = directory.toPath();
                try (Stream<Path> stream = Files.walk(start, 1)) {
                    return stream.filter(p -> Files.isDirectory(p)).map(Path::toFile).sorted().collect(Collectors.toList());
                }
            }catch (UncheckedIOException ignored){
                return null;
            }
        }
    }

    public static ArrayList<File> listAllSubDirectories_ArrayList(File directory) throws IOException{
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            ArrayList<File> directories = new ArrayList<>();
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        directories.add(file);
                        directories.addAll(listAllSubDirectories(file));
                    }
                }
            }
            return directories;
        }else{
            try {
                Path start = directory.toPath();
                try (Stream<Path> stream = Files.walk(start, 1)) {
                    return (ArrayList<File>) stream.filter(p -> Files.isDirectory(p)).map(Path::toFile).sorted().collect(Collectors.toList());
                }
            }catch (UncheckedIOException ignored){
                return null;
            }
        }
    }





    public static String[] extractFilesFromList(String[] list){
        int valid_file_count = 0;
        for (String s : list) {
            if (isPathAFile(s)) {
                valid_file_count++;
            }
        }
        String[] new_list = new String[valid_file_count];
        int current_index = 0;
        for (String s : list) {
            if (isPathAFile(s)) {
                new_list[current_index] = s;
                current_index++;
            }
        }
        return new_list;
    }

    public static ArrayList<File> extractFiles(ArrayList<File> files){
        ArrayList<File> the_files = new ArrayList<>();
        for(int index = 0; index<files.size(); index++){
            if(RCTfile.isPathAFile(files.get(index))){
                the_files.add(files.get(index));
            }
        }
        return the_files;
    }

    public static ArrayList<File> extractFiles(File[] files){
        ArrayList<File> the_files = new ArrayList<>();
        for(int index = 0; index<files.length; index++){
            if(RCTfile.isPathAFile(files[index])){
                the_files.add(files[index]);
            }
        }
        return the_files;
    }

    public static ArrayList<File> extractDirectories(ArrayList<File> files){
        ArrayList<File> the_files = new ArrayList<>();
        for(int index = 0; index<files.size(); index++){
            if(RCTfile.isPathADirectory(files.get(index))){
                the_files.add(files.get(index));
            }
        }
        return the_files;
    }

    public static ArrayList<File> extractDirectories(File[] files){
        ArrayList<File> the_files = new ArrayList<>();
        for(int index = 0; index<files.length; index++){
            if(RCTfile.isPathADirectory(files[index])){
                the_files.add(files[index]);
            }
        }
        return the_files;
    }




    public static String[] extractFilesFromList(ArrayList<String> list){
        int valid_file_count = 0;
        for (String s : list) {
            if (isPathAFile(s)) {
                valid_file_count++;
            }
        }
        String[] new_list = new String[valid_file_count];
        int current_index = 0;
        for (String s : list) {
            if (isPathAFile(s)) {
                new_list[current_index] = s;
                current_index++;
            }
        }
        return new_list;
    }



    public static String[] extractDirectoriesFromList(String[] list){
        int valid_file_count = 0;
        for (String s : list) {
            if (isPathADirectory(s)) {
                valid_file_count++;
            }
        }
        String[] new_list = new String[valid_file_count];
        int current_index = 0;
        for (String s : list){
            if (isPathADirectory(s)) {
                new_list[current_index] = s;
                current_index++;
            }
        }
        return new_list;
    }

    public static String[] extractDirectoriesFromList(ArrayList<String> list){
        int valid_file_count = 0;
        for (String s : list) {
            if (isPathADirectory(s)) {
                valid_file_count++;
            }
        }
        String[] new_list = new String[valid_file_count];
        int current_index = 0;
        for (String s : list){
            if (isPathADirectory(s)) {
                new_list[current_index] = s;
                current_index++;
            }
        }
        return new_list;
    }

    public static boolean isPathAFile(File the_file){
        return the_file.isFile();
    }
    public static boolean isPathADirectory(File the_file){
        return the_file.isDirectory();
    }


    /**
     * <h2>Description</h2>
     * Returns details about the list of file paths in a long array with 3 entries
     * index = 0 : total size in bytes
     * index = 1 : file count
     * index = 2 : folder count
     * @author  Rafael Andaya Cartagena
     * @version 1.0
     * @param
     * @since 10-29-2022
     */

    public static long[] getDetails(ArrayList<String> paths_list,AtomicBoolean execution_clearance){

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            long total_size_in_bytes = 0;
            long file_count = 0;
            long folder_count = 0;
            for(int index = 0; index<paths_list.size(); index++){
                if(execution_clearance.get()){
                    File current_path = new File(paths_list.get(index));
                    if (RCTfile.isPathAFile(current_path)) {
                            file_count++;
                            total_size_in_bytes += RCTfile.getSize(current_path);
                    }else{
                        try{
                            List<File> file_and_dir = RCTdirectory.listAllFilesAndSubDirectories(current_path);
                            for(int dex = 0; dex < file_and_dir.size(); dex++){
                                if(execution_clearance.get()){
                                    if(RCTfile.isPathAFile(file_and_dir.get(dex))){
                                        file_count++;
                                        total_size_in_bytes += RCTfile.getSize(file_and_dir.get(dex));
                                    }else{
                                        folder_count++;
                                    }
                                }else{
                                    break;
                                }
                            }
                        }catch(IOException |NullPointerException ignored){}

                    }
                }else{
                    break;
                }
            }
            return new long[]{total_size_in_bytes,file_count,folder_count};

        }else{
            long total_size_in_bytes = 0;
            long file_count = 0;
            long folder_count = 0;
            for(int index = 0; index<paths_list.size(); index++){
                if(execution_clearance.get()){
                    File current_path = new File(paths_list.get(index));
                    if (RCTfile.isPathAFile(current_path)) {
                        try{
                            file_count++;
                            total_size_in_bytes += RCTfile.getSize(current_path);
                        } catch (UncheckedIOException | NullPointerException ignored){}
                    }else{
                        try{
                            List<File> file_and_dir = RCTdirectory.listAllFilesAndSubDirectories(current_path);
                            for(int dex = 0; dex < file_and_dir.size(); dex++){
                                if(execution_clearance.get()){
                                    if(RCTfile.isPathAFile(file_and_dir.get(dex))){
                                        file_count++;
                                        total_size_in_bytes += RCTfile.getSize(file_and_dir.get(dex));
                                    }else{
                                        folder_count++;
                                    }
                                }else{
                                    break;
                                }
                            }
                        }catch(IOException | UncheckedIOException |NullPointerException ignored){}

                    }
                }else{
                    break;
                }
            }
            return new long[]{total_size_in_bytes,file_count,folder_count};
        }



    }








}
