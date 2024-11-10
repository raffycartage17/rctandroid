package com.racartech.library.rctandroid.google.firebase.storage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.racartech.library.rctandroid.file.RCTdirectory;
import com.racartech.library.rctandroid.file.RCTfile;
import com.racartech.library.rctandroid.google.firebase.firestore.RCTfirebaseFirestore;
import com.racartech.library.rctandroid.media.RCTbitmap;
import com.racartech.library.rctandroid.net.RCTinternet;
import com.racartech.library.rctandroid.security.RCThashing;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class RCTfirebaseStorage {


    private final static String NULL_RESULT_IDENTIFIER = "NULL";

    public static AtomicReference<String> DEFAULT_CACHE_DIRECTORY = new AtomicReference<>(null);


    public static String initializeCachingSystem(Context context){
        String root_dir = RCTfile.getDir_IntAppFiles(context).concat("/");
        String parent_dir = "rctjava_firebase_storage_cache";
        String final_dir = root_dir.concat(parent_dir);
        DEFAULT_CACHE_DIRECTORY.set(final_dir);
        RCTdirectory.createDirectory(final_dir);
        return final_dir;
    }




    public static void renameFile_WaitProgress(
            FirebaseStorage instance,
            String directory,
            String old_file_name,
            String new_file_name,
            long thread_wait
    ){
        boolean return_boolean = false;
        AtomicBoolean finished_boolean = new AtomicBoolean(false);

        if(directory == null){
            directory = "";
        }


        RCTfirebaseStorageUtil.renameFile_WaitProgress(
                instance,
                finished_boolean,
                directory,
                old_file_name,
                new_file_name
        );

        while(!return_boolean){
            if(!finished_boolean.get()){
                try {
                    Thread.sleep(thread_wait);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }else{
                return_boolean = true;
            }
        }
    }

    public static void renameFile(
            FirebaseStorage instance,
            String directory,
            String old_file_name,
            String new_file_name
    ){

        if(directory == null){
            directory = "";
        }


        RCTfirebaseStorageUtil.renameFile(
                instance,
                directory,
                old_file_name,
                new_file_name
        );


    }





    public static String cacheFileDefault(Context context, String download_url){
        try {
            String parent_dir = null;
            if(DEFAULT_CACHE_DIRECTORY.get() != null){
                parent_dir = DEFAULT_CACHE_DIRECTORY.get();
            }else{
                parent_dir = initializeCachingSystem(context);
            }
            String hashname = RCThashing.sha256(download_url);
            String save_file_path = parent_dir.concat("/").concat(hashname);

            if(!RCTfile.doesFileExist(save_file_path)){
                RCTinternet.downloadFile(download_url,save_file_path);
            }
            return save_file_path;
        }catch (Exception ignored){
            return null;
        }
    }

    public static String cacheFileDefault(
            Context context,
            FirebaseStorage storage_instance,
            String firebase_storage_parent_dir,
            String firebase_storage_filename,
            long thread_wait
    ){



        try {

            String download_url = getDownloadURL(
                    storage_instance,
                    firebase_storage_parent_dir,
                    firebase_storage_filename,
                    thread_wait
            );

            String parent_dir = null;
            if(DEFAULT_CACHE_DIRECTORY.get() != null){
                parent_dir = DEFAULT_CACHE_DIRECTORY.get();
            }else{
                parent_dir = initializeCachingSystem(context);
            }
            String hashname = RCThashing.sha256(download_url);
            String save_file_path = parent_dir.concat("/").concat(hashname);

            if(!RCTfile.doesFileExist(save_file_path)){
                RCTinternet.downloadFile(download_url,save_file_path);
            }
            return save_file_path;
        }catch (Exception ignored){
            return null;
        }
    }


    public static String getCachedFilePathDefault(
            Context context,
            FirebaseStorage storage_instance,
            String firebase_storage_parent_dir,
            String firebase_storage_filename,
            long thread_wait
    ){


        try {

            String download_url = getDownloadURL(
                    storage_instance,
                    firebase_storage_parent_dir,
                    firebase_storage_filename,
                    thread_wait
            );

            String parent_dir = null;
            if(DEFAULT_CACHE_DIRECTORY.get() != null){
                parent_dir = DEFAULT_CACHE_DIRECTORY.get();
            }else{
                parent_dir = initializeCachingSystem(context);
            }
            String hashname = RCThashing.sha256(download_url);
            String save_file_path = parent_dir.concat("/").concat(hashname);

            return save_file_path;
        }catch (Exception ignored){
            return null;
        }
    }








    public static String cacheFileDefault(
            Context context,
            String download_url,
            String file_extension){

        try {
            String parent_dir = null;
            if(DEFAULT_CACHE_DIRECTORY.get() != null){
                parent_dir = DEFAULT_CACHE_DIRECTORY.get();
            }else{
                parent_dir = initializeCachingSystem(context);
            }


            file_extension = file_extension.replace(".", "");

            String hashname = RCThashing.sha256(download_url);
            String save_file_path = parent_dir.
                    concat("/").
                    concat(hashname).
                    concat(".").
                    concat(file_extension);
            if(!RCTfile.doesFileExist(save_file_path)){
                RCTinternet.downloadFile(download_url,save_file_path);
            }
            return save_file_path;
        }catch (Exception ignored){
            return null;
        }
    }


    public static String cacheFileDefault(
            Context context,
            FirebaseStorage storage_instance,
            String firebase_storage_parent_dir,
            String firebase_storage_filename,
            String file_extension,
            long thread_wait){

        try {

            String download_url = getDownloadURL(
                    storage_instance,
                    firebase_storage_parent_dir,
                    firebase_storage_filename,
                    thread_wait
            );

            String parent_dir = null;
            if(DEFAULT_CACHE_DIRECTORY.get() != null){
                parent_dir = DEFAULT_CACHE_DIRECTORY.get();
            }else{
                parent_dir = initializeCachingSystem(context);
            }


            file_extension = file_extension.replace(".", "");

            String hashname = RCThashing.sha256(download_url);
            String save_file_path = parent_dir.
                    concat("/").
                    concat(hashname).
                    concat(".").
                    concat(file_extension);
            if(!RCTfile.doesFileExist(save_file_path)){
                RCTinternet.downloadFile(download_url,save_file_path);
            }
            return save_file_path;
        }catch (Exception ignored){
            return null;
        }
    }


    public static String getCachedFilePathDefault(
            Context context,
            FirebaseStorage storage_instance,
            String firebase_storage_parent_dir,
            String firebase_storage_filename,
            String file_extension,
            long thread_wait){

        try {

            String download_url = getDownloadURL(
                    storage_instance,
                    firebase_storage_parent_dir,
                    firebase_storage_filename,
                    thread_wait
            );

            String parent_dir = null;
            if(DEFAULT_CACHE_DIRECTORY.get() != null){
                parent_dir = DEFAULT_CACHE_DIRECTORY.get();
            }else{
                parent_dir = initializeCachingSystem(context);
            }


            file_extension = file_extension.replace(".", "");

            String hashname = RCThashing.sha256(download_url);
            String save_file_path = parent_dir.
                    concat("/").
                    concat(hashname).
                    concat(".").
                    concat(file_extension);
            return save_file_path;
        }catch (Exception ignored){
            return null;
        }
    }

    public static String getCachedFilePathDefault(Context context, String download_url){
        try {
            String parent_dir = null;
            if(DEFAULT_CACHE_DIRECTORY.get() != null){
                parent_dir = DEFAULT_CACHE_DIRECTORY.get();
            }else{
                parent_dir = initializeCachingSystem(context);
            }
            String hashname = RCThashing.sha256(download_url);
            String save_file_path = parent_dir.concat("/").concat(hashname);

            return save_file_path;
        }catch (Exception ignored){
            return null;
        }
    }

    public static String getCachedFilePathDefault(
            Context context,
            String download_url,
            String file_extension){

        try {
            String parent_dir = null;
            if(DEFAULT_CACHE_DIRECTORY.get() != null){
                parent_dir = DEFAULT_CACHE_DIRECTORY.get();
            }else{
                parent_dir = initializeCachingSystem(context);
            }


            file_extension = file_extension.replace(".", "");

            String hashname = RCThashing.sha256(download_url);
            String save_file_path = parent_dir.
                    concat("/").
                    concat(hashname).
                    concat(".").
                    concat(file_extension);

            return save_file_path;
        }catch (Exception ignored){
            return null;
        }
    }






    public static Bitmap getBitmapForFileURL(String download_url){
        return RCTbitmap.getBitmapForURL(download_url);
    }

    public static Bitmap getBitmapForFile(
            FirebaseStorage storage_instance,
            String file_name,
            long thread_wait
    ){
        try {
            String download_url = getDownloadURL(storage_instance, file_name, thread_wait);
            return RCTbitmap.getBitmapForURL(download_url);
        }catch (Exception ignored){
            return null;
        }
    }

    public static Bitmap getBitmapForFile(
            FirebaseStorage storage_instance,
            String directory,
            String file_name,
            long thread_wait
    ){
        try {
            String download_url = getDownloadURL(
                    storage_instance,
                    directory,
                    file_name,
                    thread_wait
            );
            return RCTbitmap.getBitmapForURL(download_url);
        }catch(Exception ignored){
            return null;
        }
    }


    /**
     * <h2>Description</h2>
     * Create a file in the root directory of your Firebase Storage
     * This class wont delete and recreate the file it already existed but instead override it.
     * <br>
     * Always call this method on a worker class
     * @author Rafael Andaya Cartagena
     * @return String - download url of the created file
     * @param thread_wait length of time that the thread will wait to query the generated url in millisecond.
     */
    public static String createOverrideFile_GetURL(
            FirebaseStorage instance,
            String file_name,
            ArrayList<String> file_contents,
            long thread_wait){

        boolean return_boolean = false;
        AtomicBoolean finished_boolean = new AtomicBoolean(false);
        AtomicReference<String> atomic_string = new AtomicReference<>(null);
        RCTfirebaseStorageUtil.createOverrideFile_GetURL_X0001(
                instance,
                file_name,
                file_contents,
                finished_boolean,
                atomic_string);
        while(!return_boolean){
            if(!finished_boolean.get() && atomic_string.get() == null){
                try {
                    Thread.sleep(thread_wait);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }else{
                return_boolean = true;
            }
        }
        if(atomic_string.get().equalsIgnoreCase(NULL_RESULT_IDENTIFIER)) {
            return null;
        }else{
            return atomic_string.get();
        }

    }

    /**
     * <h2>Description</h2>
     * Create a file in the root directory of your Firebase Storage
     * This class wont delete and recreate the file it already existed but instead override it.
     * <br>
     * Always call this method on a worker class
     * @author Rafael Andaya Cartagena
     */
    public static void createOverrideFile(FirebaseStorage instance,String file_name, ArrayList<String> file_contents){
        RCTfirebaseStorageUtil.createWrite_File(
                instance,
                file_name,
                file_contents);
    }

    /**
     * <h2>Description</h2>
     * Create a file in the desired directory of your Firebase Storage
     *
     * This class wont delete and recreate the file it already existed but instead override it.
     * <br>
     * Sample Directory : "test/a", this will create the file in "storage root/test/a" directory
     * <br>
     * Always call this method on a worker class
     * @author Rafael Andaya Cartagena
     * @return String - download url of the created file
     * @param thread_wait length of time that the thread will wait to query the generated url in millisecond.
     */
    public static String createOverrideFile_GetURL(
            FirebaseStorage instance,
            String directory,
            String file_name,
            ArrayList<String> file_contents,
            long thread_wait){

        boolean return_boolean = false;
        AtomicBoolean finished_boolean = new AtomicBoolean(false);
        AtomicReference<String> atomic_string = new AtomicReference<>(null);
        RCTfirebaseStorageUtil.createWrite_File_X0002(instance,directory,file_name,file_contents,finished_boolean,atomic_string);
        while(!return_boolean){
            if(!finished_boolean.get() && atomic_string.get() == null){
                try {
                    Thread.sleep(thread_wait);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }else{
                return_boolean = true;
            }
        }
        if(atomic_string.get().equalsIgnoreCase(NULL_RESULT_IDENTIFIER)) {
            return null;
        }else{
            return atomic_string.get();
        }

    }

    /**
     * <h2>Description</h2>
     * Create a file in the desired directory of your Firebase Storage
     * This class wont delete and recreate the file it already existed but instead override it.
     * <br>
     * Sample Directory : "test/a", this will create the file in "storage root/test/a" directory
     * <br>
     * Always call this method on a worker class
     * @author Rafael Andaya Cartagena
     */
    public static void createOverrideFile(FirebaseStorage instance,String directory, String file_name, ArrayList<String> file_contents){
        RCTfirebaseStorageUtil.createWrite_File(instance,directory,file_name,file_contents);
    }


    /**
     * <h2>Description</h2>
     * Create a file in the root directory of your Firebase Storage
     * This class wont delete and recreate the file it already existed but instead override it.
     * <br>
     * Always call this method on a worker class
     * @author Rafael Andaya Cartagena
     * @return String - download url of the created file
     * @param thread_wait length of time that the thread will wait to query the generated url in millisecond.
     */
    public static String createEmptyFile_GetURL(FirebaseStorage instance,String file_name, long thread_wait){

        boolean return_boolean = false;
        AtomicBoolean finished_boolean = new AtomicBoolean(false);
        AtomicReference<String> atomic_string = new AtomicReference<>(null);
        RCTfirebaseStorageUtil.createEmptyFile_X0005(instance,file_name,finished_boolean,atomic_string);

        while(!return_boolean){
            if(!finished_boolean.get() && atomic_string.get() == null){
                try {
                    Thread.sleep(thread_wait);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }else{
                return_boolean = true;
            }
        }
        if(atomic_string.get().equalsIgnoreCase(NULL_RESULT_IDENTIFIER)) {
            return null;
        }else{
            return atomic_string.get();
        }


    }

    /**
     * <h2>Description</h2>
     * Create a file in the root directory of your Firebase Storage
     * This class wont delete and recreate the file it already existed but instead override it.
     * <br>
     * Always call this method on a worker thread
     * @author Rafael Andaya Cartagena
     */
    public static void createEmptyFile(FirebaseStorage instance, String file_name){
        RCTfirebaseStorageUtil.createEmptyFile(instance,file_name);
    }

    /**
     * <h2>Description</h2>
     * Create a file in the root directory of your Firebase Storage
     * This class wont delete and recreate the file it already existed but instead override it.
     * <br>
     * Always call this method on a worker thread
     * @author Rafael Andaya Cartagena
     */
    public static String createEmptyFile_GetURL(FirebaseStorage instance, String directory, String file_name, long thread_wait){
        boolean return_boolean = false;
        AtomicBoolean finished_boolean = new AtomicBoolean(false);
        AtomicReference<String> atomic_string = new AtomicReference<>(null);
        RCTfirebaseStorageUtil.createEmptyFile_X0004(instance,directory,file_name,finished_boolean,atomic_string);

        while(!return_boolean){
            if(!finished_boolean.get() && atomic_string.get() == null){
                try {
                    Thread.sleep(thread_wait);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }else{
                return_boolean = true;
            }
        }
        if(atomic_string.get().equalsIgnoreCase(NULL_RESULT_IDENTIFIER)) {
            return null;
        }else{
            return atomic_string.get();
        }
    }

    /**
     * <h2>Description</h2>
     * Create a file in the specified directory relative to the root
     * This class wont delete and recreate the file it already existed but instead override it.
     * <br>
     * Always call this method on a worker thread
     * @author Rafael Andaya Cartagena
     */
    public static void createEmptyFile(FirebaseStorage instance, String directory, String file_name){
        RCTfirebaseStorageUtil.createEmptyFile(instance,directory,file_name);
    }

    /**
     * <h2>Description</h2>
     * Create the files in the specified directory relative to the root
     * This class wont delete and recreate the file it already existed but instead override it.
     * <br>
     * Always call this method on a worker thread
     * @author Rafael Andaya Cartagena
     */
    public static void createEmptyFiles(FirebaseStorage instance,String directory, ArrayList<String> file_names){
        RCTfirebaseStorageUtil.createEmptyFiles(instance,directory,file_names);
    }


    public static void createDirectory(FirebaseStorage instance,String directory, String place_holder_filename){
        RCTfirebaseStorageUtil.createDirectory(instance,directory,place_holder_filename);
    }

    /**
     * <h2>Description</h2>
     * <br>
     * Sample Directory : "test/a", this code will look for the file that is inside "storage_root/test/a"
     * <br>
     * Always call this method on a worker thread
     * @author Rafael Andaya Cartagena
     * @return String - the download url of the target file
     */
    public static String getDownloadURL(FirebaseStorage instance,String directory, String file_name, long thread_wait){
        boolean return_boolean = false;
        AtomicBoolean finished_boolean = new AtomicBoolean(false);
        AtomicReference<String> atomic_string = new AtomicReference<>(null);
        RCTfirebaseStorageUtil.getDownloadURL_X0003(instance,directory,file_name,finished_boolean,atomic_string);

        while(!return_boolean){
            if(!finished_boolean.get() && atomic_string.get() == null){
                try {
                    Thread.sleep(thread_wait);
                } catch (Exception e){}
            }else{
                return_boolean = true;
            }
        }
        if(atomic_string.get().equalsIgnoreCase(NULL_RESULT_IDENTIFIER)) {
            return null;
        }else{
            return atomic_string.get();
        }

    }

    /**
     * <h2>Description</h2>
     * Always call this method on a worker thread
     * @author Rafael Andaya Cartagena
     * @return String - the download url of the target file
     */
    public static String getDownloadURL(FirebaseStorage instance,String file_name, long thread_wait){
        boolean return_boolean = false;
        AtomicBoolean finished_boolean = new AtomicBoolean(false);
        AtomicReference<String> atomic_string = new AtomicReference<>(null);
        RCTfirebaseStorageUtil.getDownloadURL_X0006(instance,file_name,finished_boolean,atomic_string);

        while(!return_boolean){
            if(!finished_boolean.get() && atomic_string.get() == null){
                try {
                    Thread.sleep(thread_wait);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }else{
                return_boolean = true;
            }
        }
        if(atomic_string.get().equalsIgnoreCase(NULL_RESULT_IDENTIFIER)) {
            return null;
        }else{
            return atomic_string.get();
        }


    }


    /**
     * <h2>Description</h2>
     * Create the files in the specified directory relative to the root, this method will wait for the progress before stopping.
     * This class wont delete and recreate the file it already existed but instead override it.
     * <br>
     * Always call this method on a worker thread
     * @author Rafael Andaya Cartagena
     */
    public static boolean createEmptyFiles_WaitProgress(FirebaseStorage instance,String directory_path, ArrayList<String> file_names, long thread_wait){


        boolean return_boolean = false;

        final ArrayList<String> cloned_file_names = new ArrayList<>(file_names);
        AtomicReference<ArrayList<Boolean>> atomic_progress = new AtomicReference<>(new ArrayList<>());

        RCTfirebaseStorageUtil.createEmptyFiles_WaitProgress(instance,directory_path,cloned_file_names,atomic_progress);


        while(!return_boolean){
            if(cloned_file_names.size() != atomic_progress.get().size()){
                try {
                    Thread.sleep(thread_wait);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }else{
                return_boolean = true;
            }
        }

        return return_boolean;



    }





    public static void deleteFile(FirebaseStorage instance,String filename){
        RCTfirebaseStorageUtil.deleteFile(instance,filename);
    }

    public static void deleteFile(FirebaseStorage instance,ArrayList<String> filenames){
        RCTfirebaseStorageUtil.deleteFile(instance,filenames);
    }

    public static void deleteFile(FirebaseStorage instance, String directory, String filename){
        RCTfirebaseStorageUtil.deleteFile(instance,directory,filename);
    }

    public static void deleteFile(FirebaseStorage instance,String directory, ArrayList<String> filenames){
        RCTfirebaseStorageUtil.deleteFile(instance,directory,filenames);
    }

    public static void clearDirectory_ImmediateFile(FirebaseStorage instance,String directory){
        RCTfirebaseStorageUtil.clearDirectory_ImmediateFiles(instance,directory);
    }




    public static boolean deleteFile_WaitProgress(FirebaseStorage instance,String filename, long thread_wait){
        //Always call this method on a worker thread
        try {
            AtomicBoolean is_finished = new AtomicBoolean(false);
            RCTfirebaseStorageUtil.deleteFile_WaitProgress(instance,is_finished, filename);
            while (!is_finished.get()) {
                Thread.sleep(thread_wait);
            }
            return is_finished.get();
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean deleteFile_WaitProgress(FirebaseStorage instance, ArrayList<String> filenames, long thread_wait){
        //Always call this method on a worker thread
        try {
            AtomicBoolean is_finished = new AtomicBoolean(false);
            RCTfirebaseStorageUtil.deleteFile_WaitProgress(instance,is_finished, filenames);
            while (!is_finished.get()) {
                Thread.sleep(thread_wait);
            }
            return is_finished.get();
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean deleteFile_WaitProgress(FirebaseStorage instance,String directory, String filename, long thread_wait){
        //Always call this method on a worker thread
        try {
            AtomicBoolean is_finished = new AtomicBoolean(false);
            RCTfirebaseStorageUtil.deleteFile_WaitProgress(instance,is_finished, directory, filename);
            while (!is_finished.get()) {
                Thread.sleep(thread_wait);
            }
            return is_finished.get();
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean deleteFile_WaitProgress(FirebaseStorage instance,String directory, ArrayList<String> filenames, long thread_wait){
        //Always call this method on a worker thread
        try {
            AtomicBoolean is_finished = new AtomicBoolean(false);
            RCTfirebaseStorageUtil.deleteFile_WaitProgress(instance,is_finished, directory, filenames);
            while (!is_finished.get()) {
                Thread.sleep(thread_wait);
            }
            return is_finished.get();
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean clearDirectory_ImmediateFile_WaitProgress(FirebaseStorage instance,String directory, long thread_wait){
        //Always call this method on a worker thread
        try {
            AtomicBoolean is_finished = new AtomicBoolean(false);
            boolean finish_boolean = false;
            RCTfirebaseStorageUtil.clearDirectory_ImmediateFiles_WaitProgress(instance,is_finished, directory);
            while (!finish_boolean) {
                if(!is_finished.get()){
                    Thread.sleep(thread_wait);
                }else{
                    finish_boolean = true;
                }
            }
            return is_finished.get();
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }


    public static String uploadFile(
            FirebaseStorage instance,
            String local_file_path,
            String uploaded_file_name,
            long thread_wait
    ){
        return uploadFile(
                instance,
                local_file_path,
                null,
                uploaded_file_name,
                thread_wait
        );
    }

    public static ArrayList<String> uploadFiles(
            FirebaseStorage instance,
            ArrayList<String> local_file_paths,
            ArrayList<String> uploaded_file_names,
            long thread_wait
    ){
        return uploadFiles(
                instance,
                local_file_paths,
                null,
                uploaded_file_names,
                100
        );
    }




    public static String uploadFile(
            FirebaseStorage instance,
            String local_file_path,
            String firebase_directory_path,
            String uploaded_file_name,
            long thread_wait
    ){
        boolean return_boolean = false;
        AtomicBoolean finished_boolean = new AtomicBoolean(false);
        AtomicReference<String> download_url = new AtomicReference<>(null);

        if(firebase_directory_path == null){
            firebase_directory_path = "";
        }


        RCTfirebaseStorageUtil.uploadFile(
                instance,
                local_file_path,
                firebase_directory_path,
                uploaded_file_name,
                finished_boolean,
                download_url
        );
        while(!return_boolean){
            if(!finished_boolean.get()){
                try {
                    Thread.sleep(thread_wait);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }else{
                return_boolean = true;
            }
        }
        if(download_url.get().equalsIgnoreCase(NULL_RESULT_IDENTIFIER)) {
            return null;
        }else{
            return download_url.get();
        }
    }


    public static ArrayList<String> uploadFiles(
            FirebaseStorage instance,
            ArrayList<String> local_file_path,
            String firebase_directory_path,
            ArrayList<String> uploaded_file_name,
            long thread_wait
    ){
        boolean return_boolean = false;

        if(firebase_directory_path == null){
            firebase_directory_path = "";
        }


        AtomicReference<ArrayList<String>> atomic_list = new AtomicReference<>(new ArrayList<>());
        RCTfirebaseStorageUtil.uploadMultipleFile(
                instance,
                local_file_path,
                firebase_directory_path,
                uploaded_file_name,
                atomic_list
        );
        while(!return_boolean){
            if(atomic_list.get().size() != local_file_path.size()){
                try {
                    Thread.sleep(thread_wait);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }else{
                return_boolean = true;
            }
        }

        ArrayList<String> processed_atomic_list = new ArrayList<>();
        for(int index = 0; index<atomic_list.get().size(); index++){
            if(atomic_list.get().get(index).equalsIgnoreCase(NULL_RESULT_IDENTIFIER)){
                processed_atomic_list.add(null);
            }else{
                processed_atomic_list.add(atomic_list.get().get(index));
            }
        }

        return processed_atomic_list;
    }
    



































    ////////////////////////Utilities//////////////////////




}


 class RCTfirebaseStorageUtil{

    private final static String NULL_RESULT_IDENTIFIER = "NULL";



    public static void createOverrideFile_GetURL_X0001(
            FirebaseStorage instance,
            String fileName,
            ArrayList<String> file_contents,
            AtomicBoolean finished_boolean,
            AtomicReference<String> atomic_string) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                // Initialize Firebase Storage
                StorageReference storageRef = instance.getReference();

                // Concatenate the ArrayList into a single string with newline characters
                StringBuilder fileContentBuilder = new StringBuilder();
                for (int i = 0; i < file_contents.size(); i++) {
                    String line = file_contents.get(i);
                    fileContentBuilder.append(line).append("\n");
                }
                String fileContent = fileContentBuilder.toString().trim(); // Remove the last newline character

                // Create a reference to the file you want to create
                StorageReference fileRef = storageRef.child(fileName);

                byte[] data = fileContent.getBytes();

                fileRef.putBytes(data)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                // File upload successful
                                // Get the download URL of the uploaded file
                                Task<Uri> downloadUrlTask = taskSnapshot.getStorage().getDownloadUrl();
                                downloadUrlTask.addOnCompleteListener(new OnCompleteListener<Uri>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Uri> task) {
                                        if (task.isSuccessful()) {
                                            // Get the URL of the uploaded file
                                            Uri downloadUrl = task.getResult();
                                            String downloadUrlString = downloadUrl.toString();
                                            atomic_string.set(downloadUrlString);
                                            finished_boolean.set(true);
                                        } else {
                                            atomic_string.set(NULL_RESULT_IDENTIFIER);
                                            finished_boolean.set(true);
                                        }
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        atomic_string.set(NULL_RESULT_IDENTIFIER);
                                        finished_boolean.set(true);
                                    }
                                }).addOnCanceledListener(new OnCanceledListener() {
                                    @Override
                                    public void onCanceled() {
                                        atomic_string.set(NULL_RESULT_IDENTIFIER);
                                        finished_boolean.set(true);
                                    }
                                });
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Handle the error
                                atomic_string.set(NULL_RESULT_IDENTIFIER);
                                finished_boolean.set(true);
                            }
                        }).addOnCanceledListener(new OnCanceledListener() {
                            @Override
                            public void onCanceled() {
                                atomic_string.set(NULL_RESULT_IDENTIFIER);
                                finished_boolean.set(true);
                            }
                        });
            }
        }).start();


    }


    public static void createWrite_File(
            FirebaseStorage instance,
            String fileName,
            ArrayList<String> file_contents) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                // Initialize Firebase Storage
                StorageReference storageRef = instance.getReference();

                // Concatenate the ArrayList into a single string with newline characters
                StringBuilder fileContentBuilder = new StringBuilder();
                for (int i = 0; i < file_contents.size(); i++) {
                    String line = file_contents.get(i);
                    fileContentBuilder.append(line).append("\n");
                }
                String fileContent = fileContentBuilder.toString().trim(); // Remove the last newline character

                // Create a reference to the file you want to create
                StorageReference fileRef = storageRef.child(fileName);

                byte[] data = fileContent.getBytes();

                fileRef.putBytes(data)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                // File upload successful
                                // Get the download URL of the uploaded file
                                Task<Uri> downloadUrlTask = taskSnapshot.getStorage().getDownloadUrl();
                                downloadUrlTask.addOnCompleteListener(new OnCompleteListener<Uri>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Uri> task) {
                                    }
                                });
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Handle the error
                            }
                        });

            }
        }).start();



    }


    public static void createWrite_File_X0002(
            FirebaseStorage instance,
            String folder_path,
            String file_name,
            ArrayList<String> file_contents,
            AtomicBoolean finished_boolean,
            AtomicReference<String> atomic_string) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                //This code wont recreate the file if it already existed
                String[] folder_path_branches = folder_path.split("/");

                StorageReference storageRef = instance.getReference();

                // Concatenate the ArrayList into a single string with newline characters
                StringBuilder fileContentBuilder = new StringBuilder();
                for (int i = 0; i < file_contents.size(); i++) {
                    String line = file_contents.get(i);
                    fileContentBuilder.append(line).append("\n");
                }
                String fileContent = fileContentBuilder.toString().trim(); // Remove the last newline character

                // Define the name of the text file you want to create
                String fileName = file_name;

                // Get the root folder reference
                StorageReference folderRef = storageRef;

                // Create nested directories if they exist in the folder_path_branches array
                for (String folderName : folder_path_branches) {
                    if (!folderName.isEmpty()) {
                        // Create a reference to the next level folder
                        folderRef = folderRef.child(folderName);
                    }
                }

                // Create a reference to the file inside the final folder
                StorageReference fileRef = folderRef.child(fileName);

                byte[] data = fileContent.getBytes();

                fileRef.putBytes(data)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                // File upload successful
                                // Get the download URL of the uploaded file
                                Task<Uri> downloadUrlTask = taskSnapshot.getStorage().getDownloadUrl();
                                downloadUrlTask.addOnCompleteListener(new OnCompleteListener<Uri>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Uri> task) {
                                        if (task.isSuccessful()) {
                                            // Get the URL of the uploaded file
                                            Uri downloadUrl = task.getResult();
                                            String download_url_string = downloadUrl.toString();
                                            atomic_string.set(download_url_string);
                                            finished_boolean.set(true);
                                        } else {
                                            atomic_string.set(NULL_RESULT_IDENTIFIER);
                                            finished_boolean.set(true);
                                        }
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        atomic_string.set(NULL_RESULT_IDENTIFIER);
                                        finished_boolean.set(true);
                                    }
                                }).addOnCanceledListener(new OnCanceledListener() {
                                    @Override
                                    public void onCanceled() {
                                        atomic_string.set(NULL_RESULT_IDENTIFIER);
                                        finished_boolean.set(true);
                                    }
                                });
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Handle the error
                                atomic_string.set(NULL_RESULT_IDENTIFIER);
                                finished_boolean.set(true);
                            }
                        }).addOnCanceledListener(new OnCanceledListener() {
                            @Override
                            public void onCanceled() {
                                atomic_string.set(NULL_RESULT_IDENTIFIER);
                                finished_boolean.set(true);
                            }
                        });

            }
        }).start();




    }



    public static void createWrite_File(FirebaseStorage instance,String folder_path, String file_name, ArrayList<String> file_contents) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                //This code wont recreate the file if it already existed
                String[] folder_path_branches = folder_path.split("/");

                // Initialize Firebase Storage
                StorageReference storageRef = instance.getReference();

                // Concatenate the ArrayList into a single string with newline characters
                StringBuilder fileContentBuilder = new StringBuilder();
                for (int i = 0; i < file_contents.size(); i++) {
                    String line = file_contents.get(i);
                    fileContentBuilder.append(line).append("\n");
                }
                String fileContent = fileContentBuilder.toString().trim(); // Remove the last newline character

                // Define the name of the text file you want to create
                String fileName = file_name;

                // Get the root folder reference
                StorageReference folderRef = storageRef;

                // Create nested directories if they exist in the folder_path_branches array
                for (String folderName : folder_path_branches) {
                    if (!folderName.isEmpty()) {
                        // Create a reference to the next level folder
                        folderRef = folderRef.child(folderName);
                    }
                }

                // Create a reference to the file inside the final folder
                StorageReference fileRef = folderRef.child(fileName);

                byte[] data = fileContent.getBytes();

                fileRef.putBytes(data)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                // File upload successful
                                // Get the download URL of the uploaded file
                                Task<Uri> downloadUrlTask = taskSnapshot.getStorage().getDownloadUrl();
                                downloadUrlTask.addOnCompleteListener(new OnCompleteListener<Uri>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Uri> task){
                                    }
                                });
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Handle the error
                            }
                        });

            }
        }).start();



    }

    public static void getDownloadURL_X0003(
            FirebaseStorage instance,
            String folder_path,
            String file_name,
            AtomicBoolean finished_boolean,
            AtomicReference<String> atomic_string) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                String[] folder_path_branches = (folder_path != null) ? folder_path.split("/") : new String[0];

                // Initialize Firebase Storage
                StorageReference storageRef = instance.getReference();

                // Get the root folder reference
                StorageReference folderRef = storageRef;

                // Create nested directories if they exist in the folder_path_branches array
                for (String folderName : folder_path_branches) {
                    if (!folderName.isEmpty()) {
                        // Create a reference to the next level folder
                        folderRef = folderRef.child(folderName);
                    }
                }

                // Create a reference to the file inside the final folder
                StorageReference fileRef = folderRef.child(file_name);

                // Get the download URL of the file
                fileRef.getDownloadUrl()
                        .addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri downloadUrl) {
                                // Get the URL of the file
                                String download_url_string = downloadUrl.toString();
                                atomic_string.set(download_url_string);
                                finished_boolean.set(true);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                atomic_string.set(NULL_RESULT_IDENTIFIER);
                                finished_boolean.set(true);
                            }
                        }).addOnCanceledListener(new OnCanceledListener() {
                            @Override
                            public void onCanceled() {
                                atomic_string.set(NULL_RESULT_IDENTIFIER);
                                finished_boolean.set(true);
                            }
                        });

            }
        }).start();


    }

    public static void getDownloadURL_X0006(
            FirebaseStorage instance,
            String file_name,
            AtomicBoolean finished_boolean,
            AtomicReference<String> atomic_string) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                // Initialize Firebase Storage
                StorageReference storageRef = instance.getReference();

                // Create a reference to the file in the root directory
                StorageReference fileRef = storageRef.child(file_name);

                // Get the download URL of the file
                fileRef.getDownloadUrl()
                        .addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri downloadUrl) {
                                // Get the URL of the file
                                String download_url_string = downloadUrl.toString();
                                atomic_string.set(download_url_string);
                                finished_boolean.set(true);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                atomic_string.set(NULL_RESULT_IDENTIFIER);
                                finished_boolean.set(true);
                            }
                        }).addOnCanceledListener(new OnCanceledListener() {
                            @Override
                            public void onCanceled() {
                                atomic_string.set(NULL_RESULT_IDENTIFIER);
                                finished_boolean.set(true);
                            }
                        });
            }
        }).start();


    }




     public static void createDirectory(FirebaseStorage instance, String folder_path, String place_holder_file_name) {
         String[] folder_path_branches = folder_path.split("/");
         StorageReference folderRef = instance.getReference();

         // Create nested directories if they exist in the folder_path_branches array
         for (String folderName : folder_path_branches) {
             if (!folderName.isEmpty()) {
                 // Create a reference to the next level folder
                 folderRef = folderRef.child(folderName);
             }
         }

         // Create a placeholder file to ensure the directory is created in Firebase Storage
         StorageReference placeholderFileRef = folderRef.child(place_holder_file_name);
         placeholderFileRef.putBytes(new byte[]{});
     }




     public static void createEmptyFile_X0004(
            FirebaseStorage instance,
            String folder_path,
            String file_name,
            AtomicBoolean finished_boolean,
            AtomicReference<String> atomic_string) {


        new Thread(new Runnable() {
            @Override
            public void run() {

                StorageReference storageRef = instance.getReference();

                // Get the root folder reference
                StorageReference folderRef = storageRef;

                // Create nested directories if they exist in the folder_path array
                String[] folder_path_branches = folder_path.split("/");
                for (String folderName : folder_path_branches) {
                    if (!folderName.isEmpty()) {
                        // Create a reference to the next level folder
                        folderRef = folderRef.child(folderName);
                    }
                }

                // Define the name of the text file you want to create
                String fileName = file_name;

                // Create a reference to the file inside the final folder
                StorageReference fileRef = folderRef.child(fileName);

                // Create an empty file (without contents)
                byte[] emptyData = new byte[0];

                fileRef.putBytes(emptyData)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                // File upload successful
                                // Get the download URL of the uploaded file
                                Task<Uri> downloadUrlTask = taskSnapshot.getStorage().getDownloadUrl();
                                downloadUrlTask.addOnCompleteListener(new OnCompleteListener<Uri>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Uri> task) {
                                        if (task.isSuccessful()) {
                                            // Get the URL of the uploaded file
                                            Uri downloadUrl = task.getResult();
                                            String download_url_string = downloadUrl.toString();
                                            atomic_string.set(download_url_string);
                                            finished_boolean.set(true);
                                        } else {
                                            atomic_string.set(NULL_RESULT_IDENTIFIER);
                                            finished_boolean.set(true);
                                        }
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        atomic_string.set(NULL_RESULT_IDENTIFIER);
                                        finished_boolean.set(true);
                                    }
                                }).addOnCanceledListener(new OnCanceledListener() {
                                    @Override
                                    public void onCanceled() {
                                        atomic_string.set(NULL_RESULT_IDENTIFIER);
                                        finished_boolean.set(true);
                                    }
                                });
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Handle the error
                                atomic_string.set(NULL_RESULT_IDENTIFIER);
                                finished_boolean.set(true);
                            }
                        }).addOnCanceledListener(new OnCanceledListener() {
                            @Override
                            public void onCanceled() {
                                atomic_string.set(NULL_RESULT_IDENTIFIER);
                                finished_boolean.set(true);
                            }
                        });

            }
        }).start();



    }

    public static void createEmptyFile(FirebaseStorage instance, String folder_path, String file_name) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                StorageReference storageRef = instance.getReference();

                // Get the root folder reference
                StorageReference folderRef = storageRef;

                // Create nested directories if they exist in the folder_path array
                String[] folder_path_branches = folder_path.split("/");
                for (String folderName : folder_path_branches) {
                    if (!folderName.isEmpty()) {
                        // Create a reference to the next level folder
                        folderRef = folderRef.child(folderName);
                    }
                }

                // Define the name of the text file you want to create
                String fileName = file_name;

                // Create a reference to the file inside the final folder
                StorageReference fileRef = folderRef.child(fileName);

                // Create an empty file (without contents)
                byte[] emptyData = new byte[0];

                fileRef.putBytes(emptyData)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                // File upload successful
                                // Get the download URL of the uploaded file
                                Task<Uri> downloadUrlTask = taskSnapshot.getStorage().getDownloadUrl();
                                downloadUrlTask.addOnCompleteListener(new OnCompleteListener<Uri>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Uri> task) {
                                    }
                                });
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Handle the error
                            }
                        });

            }
        }).start();


    }


    public static void createEmptyFile_X0005(
            FirebaseStorage instance,
            String fileName,
            AtomicBoolean finished_boolean,
            AtomicReference<String> atomic_string) {


        new Thread(new Runnable() {
            @Override
            public void run() {

                StorageReference storageRef = instance.getReference();

                // Create a reference to the file you want to create
                final StorageReference fileRef = storageRef.child(fileName);

                // Check if the file already exists
                fileRef.getMetadata().addOnSuccessListener(new OnSuccessListener<StorageMetadata>() {
                    @Override
                    public void onSuccess(StorageMetadata storageMetadata) {
                        // File already exists, skip the upload process
                        // Get the download URL of the existing file
                        Task<Uri> downloadUrlTask = fileRef.getDownloadUrl();
                        downloadUrlTask.addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                if (task.isSuccessful()) {
                                    // Get the URL of the existing file
                                    Uri downloadUrl = task.getResult();
                                    String downloadUrlString = downloadUrl.toString();
                                    atomic_string.set(downloadUrlString);
                                    finished_boolean.set(true);
                                } else {
                                    atomic_string.set(NULL_RESULT_IDENTIFIER);
                                    finished_boolean.set(true);
                                    // Handle the error
                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                atomic_string.set(NULL_RESULT_IDENTIFIER);
                                finished_boolean.set(true);
                            }
                        }).addOnCanceledListener(new OnCanceledListener() {
                            @Override
                            public void onCanceled() {
                                atomic_string.set(NULL_RESULT_IDENTIFIER);
                                finished_boolean.set(true);
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // File does not exist or some other error occurred while checking the metadata
                        // Proceed with uploading the empty byte array
                        byte[] emptyData = new byte[0];
                        fileRef.putBytes(emptyData)
                                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                        // File upload successful
                                        // Get the download URL of the uploaded file
                                        Task<Uri> downloadUrlTask = taskSnapshot.getStorage().getDownloadUrl();
                                        downloadUrlTask.addOnCompleteListener(new OnCompleteListener<Uri>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Uri> task) {
                                                if (task.isSuccessful()) {
                                                    // Get the URL of the uploaded file
                                                    Uri downloadUrl = task.getResult();
                                                    String downloadUrlString = downloadUrl.toString();
                                                    atomic_string.set(downloadUrlString);
                                                    finished_boolean.set(true);
                                                } else {
                                                    atomic_string.set(NULL_RESULT_IDENTIFIER);
                                                    finished_boolean.set(true);
                                                    // Handle the error
                                                }
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                atomic_string.set(NULL_RESULT_IDENTIFIER);
                                                finished_boolean.set(true);
                                            }
                                        });
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        // Handle the error
                                        atomic_string.set(NULL_RESULT_IDENTIFIER);
                                        finished_boolean.set(true);
                                    }
                                }).addOnCanceledListener(new OnCanceledListener() {
                                    @Override
                                    public void onCanceled() {
                                        atomic_string.set(NULL_RESULT_IDENTIFIER);
                                        finished_boolean.set(true);
                                    }
                                });
                    }
                }).addOnCanceledListener(new OnCanceledListener() {
                    @Override
                    public void onCanceled() {
                        atomic_string.set(NULL_RESULT_IDENTIFIER);
                        finished_boolean.set(true);
                    }
                });

            }
        }).start();



    }


    public static void createEmptyFile(FirebaseStorage instance, String fileName) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                StorageReference storageRef = instance.getReference();

                // Create a reference to the file you want to create
                final StorageReference fileRef = storageRef.child(fileName);

                // Check if the file already exists
                fileRef.getMetadata().addOnSuccessListener(new OnSuccessListener<StorageMetadata>() {
                    @Override
                    public void onSuccess(StorageMetadata storageMetadata) {
                        // File already exists, do nothing
                        // You may add additional logic here if needed
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // File does not exist or some other error occurred while checking the metadata
                        // Proceed with uploading the empty byte array
                        byte[] emptyData = new byte[0];
                        fileRef.putBytes(emptyData)
                                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                        // File upload successful
                                        // Get the download URL of the uploaded file
                                        Task<Uri> downloadUrlTask = taskSnapshot.getStorage().getDownloadUrl();
                                        downloadUrlTask.addOnCompleteListener(new OnCompleteListener<Uri>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Uri> task) {
                                                // Handle the completion if needed
                                            }
                                        });
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        // Handle the error
                                    }
                                });
                    }
                });

            }
        }).start();

    }




    public static void createEmptyFiles(FirebaseStorage instance,String folder_path, ArrayList<String> file_names) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                StorageReference storageRef = instance.getReference();

                // Get the root folder reference
                StorageReference folderRef = storageRef;

                // Create nested directories if they exist in the folder_path array
                String[] folder_path_branches = folder_path.split("/");
                for (String folderName : folder_path_branches) {
                    if (!folderName.isEmpty()) {
                        // Create a reference to the next level folder
                        folderRef = folderRef.child(folderName);
                    }
                }

                // Create an empty file (without contents)
                byte[] emptyData = new byte[0];

                // Loop through the list of file names and upload each file
                for (String file_name : file_names) {
                    // Define the name of the text file you want to create
                    String fileName = file_name;

                    // Create a reference to the file inside the final folder
                    final StorageReference fileRef = folderRef.child(fileName);

                    // Check if the file already exists
                    fileRef.getMetadata().addOnSuccessListener(new OnSuccessListener<StorageMetadata>() {
                        @Override
                        public void onSuccess(StorageMetadata storageMetadata) {
                            // File already exists, skip the upload process for this file
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // File does not exist or some other error occurred while checking the metadata
                            // Proceed with uploading the empty byte array
                            fileRef.putBytes(emptyData)
                                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                            // File upload successful
                                            // Get the download URL of the uploaded file
                                            Task<Uri> downloadUrlTask = taskSnapshot.getStorage().getDownloadUrl();
                                            downloadUrlTask.addOnCompleteListener(new OnCompleteListener<Uri>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Uri> task) {
                                                    // File upload is complete for this file, you can add any further handling if needed.
                                                }
                                            });
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            // Handle the error
                                        }
                                    });
                        }
                    });
                }

            }
        }).start();



    }


    public static void createEmptyFiles_WaitProgress(
            FirebaseStorage instance,
            String folder_path,
            ArrayList<String> file_names,
            AtomicReference<ArrayList<Boolean>> atomic_progress) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                // Initialize Firebase Storage
                StorageReference storageRef = instance.getReference();
                // Get the root folder reference
                StorageReference folderRef = storageRef;

                // Create nested directories if they exist in the folder_path array
                String[] folder_path_branches = folder_path.split("/");
                for (String folderName : folder_path_branches) {
                    if (!folderName.isEmpty()) {
                        // Create a reference to the next level folder
                        folderRef = folderRef.child(folderName);
                    }
                }

                // Create an empty file (without contents)
                byte[] emptyData = new byte[0];

                // Loop through the list of file names and upload each file
                for (int index = 0; index < file_names.size(); index++) {
                    String file_name = file_names.get(index);
                    // Define the name of the text file you want to create
                    String fileName = file_name;

                    // Create a reference to the file inside the final folder
                    final StorageReference fileRef = folderRef.child(fileName);

                    // Check if the file already exists
                    int finalIndex = index;
                    fileRef.getMetadata().addOnSuccessListener(new OnSuccessListener<StorageMetadata>() {
                        @Override
                        public void onSuccess(StorageMetadata storageMetadata) {
                            atomic_progress.get().add(true);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // File does not exist or some other error occurred while checking the metadata
                            // Proceed with uploading the empty byte array
                            fileRef.putBytes(emptyData)
                                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                            // File upload successful
                                            // Get the download URL of the uploaded file
                                            Task<Uri> downloadUrlTask = taskSnapshot.getStorage().getDownloadUrl();
                                            downloadUrlTask.addOnCompleteListener(new OnCompleteListener<Uri>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Uri> task) {
                                                    // File upload is complete for this file, you can add any further handling if needed.
                                                    atomic_progress.get().add(true);
                                                }
                                            });
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            // Handle the error
                                            atomic_progress.get().add(true);
                                        }
                                    }).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                            atomic_progress.get().add(true);
                                        }
                                    }).addOnCanceledListener(new OnCanceledListener() {
                                        @Override
                                        public void onCanceled() {
                                            atomic_progress.get().add(true);
                                        }
                                    });
                        }
                    });
                }

            }
        }).start();

    }


    public static void deleteFile(FirebaseStorage instance, String directory, String filename) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Initialize Firebase Storage
                StorageReference storageRef = instance.getReference();

                // Create a reference to the file you want to delete
                StorageReference fileToDeleteRef = storageRef.child(directory + "/" + filename);

                // Delete the file
                fileToDeleteRef.delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                // File deleted successfully
                                // You can perform any additional operations here if needed
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Handle the error
                            }
                        });
            }
        }).start();


    }

    public static void deleteFile(FirebaseStorage instance,String filename) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Initialize Firebase Storage
                StorageReference storageRef = instance.getReference();

                // Create a reference to the file you want to delete (located in the root directory)
                StorageReference fileToDeleteRef = storageRef.child(filename);

                // Delete the file
                fileToDeleteRef.delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                // File deleted successfully
                                // You can perform any additional operations here if needed
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Handle the error
                            }
                        });
            }
        }).start();

    }

    public static void deleteFile(FirebaseStorage instance,ArrayList<String> filenames) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                // Initialize Firebase Storage
                StorageReference storageRef = instance.getReference();

                for (String filename : filenames) {
                    // Create a reference to the file you want to delete (located in the root directory)
                    StorageReference fileToDeleteRef = storageRef.child(filename);

                    // Delete the file
                    fileToDeleteRef.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(Task<Void> task) {
                            if (task.isSuccessful()) {
                                // File deleted successfully
                                // You can perform any additional operations here if needed
                            } else {
                                // Handle the error
                            }
                        }
                    });
                }

            }
        }).start();


    }

    public static void deleteFile(FirebaseStorage instance,String directory, ArrayList<String> filenames) {


        new Thread(new Runnable() {
            @Override
            public void run() {
                // Initialize Firebase Storage
                StorageReference storageRef = instance.getReference();

                // Loop through the filenames and delete each file
                for (String filename : filenames) {
                    // Create a reference to the file you want to delete
                    StorageReference fileToDeleteRef = storageRef.child(directory + "/" + filename);

                    // Delete the file
                    fileToDeleteRef.delete()
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    // File deleted successfully
                                    // You can perform any additional operations here if needed
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    // Handle the error
                                }
                            });
                }
            }
        }).start();

    }






    public static void clearDirectory_ImmediateFiles(FirebaseStorage instance,String directory) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                // Initialize Firebase Storage
                StorageReference storageRef = instance.getReference();

                // Create a reference to the directory you want to delete
                StorageReference directoryRef = storageRef.child(directory);

                // List all items (files and subdirectories) within the directory
                directoryRef.listAll()
                        .addOnCompleteListener(new OnCompleteListener<ListResult>() {
                            @Override
                            public void onComplete(@NonNull Task<ListResult> task) {
                                if (task.isSuccessful()) {
                                    // Delete each item (file or subdirectory) in the directory
                                    for (StorageReference item : task.getResult().getItems()) {
                                        item.delete()
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {
                                                        // Item (file or subdirectory) deleted successfully
                                                        // You can perform any additional operations here if needed
                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        // Handle the error
                                                    }
                                                });
                                    }
                                } else {
                                    // Handle the error
                                }
                            }
                        });
            }
        }).start();

    }





    public static void deleteFile_WaitProgress(FirebaseStorage instance,AtomicBoolean is_finished, String directory, String filename) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                // Initialize Firebase Storage
                StorageReference storageRef = instance.getReference();

                // Create a reference to the file you want to delete
                StorageReference fileToDeleteRef = storageRef.child(directory + "/" + filename);

                // Delete the file
                fileToDeleteRef.delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                // File deleted successfully
                                // You can perform any additional operations here if needed
                                is_finished.set(true);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Handle the error
                                is_finished.set(true);
                            }
                        }).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                is_finished.set(true);
                            }
                        }).addOnCanceledListener(new OnCanceledListener() {
                            @Override
                            public void onCanceled() {
                                is_finished.set(true);
                            }
                        });
            }
        }).start();


    }

    public static void deleteFile_WaitProgress(FirebaseStorage instance, AtomicBoolean is_finished, String filename) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                // Initialize Firebase Storage
                StorageReference storageRef = instance.getReference();

                // Create a reference to the file you want to delete (located in the root directory)
                StorageReference fileToDeleteRef = storageRef.child(filename);

                // Delete the file
                fileToDeleteRef.delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                // File deleted successfully
                                // You can perform any additional operations here if needed
                                is_finished.set(true);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Handle the error
                                is_finished.set(true);
                            }
                        }).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                is_finished.set(true);
                            }
                        }).addOnCanceledListener(new OnCanceledListener() {
                            @Override
                            public void onCanceled() {
                                is_finished.set(true);
                            }
                        });
            }
        }).start();


    }

    public static void deleteFile_WaitProgress(FirebaseStorage instance, AtomicBoolean is_finished, ArrayList<String> filenames) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                // Initialize Firebase Storage
                StorageReference storageRef = instance.getReference();

                for (int index = 0; index < filenames.size(); index++) {
                    String filename = filenames.get(index);
                    // Create a reference to the file you want to delete (located in the root directory)
                    StorageReference fileToDeleteRef = storageRef.child(filename);

                    // Delete the file
                    int finalIndex = index;
                    fileToDeleteRef.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(Task<Void> task) {
                            if (task.isSuccessful()) {
                                // File deleted successfully
                                // You can perform any additional operations here if needed
                            } else {
                                // Handle the error
                            }

                            if(finalIndex == (filenames.size()-1)){
                                is_finished.set(true);
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            if(finalIndex == (filenames.size()-1)){
                                is_finished.set(true);
                            }
                        }
                    }).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            if(finalIndex == (filenames.size()-1)){
                                is_finished.set(true);
                            }
                        }
                    }).addOnCanceledListener(new OnCanceledListener() {
                        @Override
                        public void onCanceled() {
                            is_finished.set(true);
                        }
                    });
                }
            }
        }).start();


    }

    public static void deleteFile_WaitProgress(FirebaseStorage instance,AtomicBoolean is_finished, String directory, ArrayList<String> filenames) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                // Initialize Firebase Storage
                StorageReference storageRef = instance.getReference();

                // Loop through the filenames and delete each file
                for (int index = 0; index < filenames.size(); index++) {
                    String filename = filenames.get(index);
                    // Create a reference to the file you want to delete
                    StorageReference fileToDeleteRef = storageRef.child(directory + "/" + filename);

                    // Delete the file
                    int finalIndex = index;
                    fileToDeleteRef.delete()
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    // File deleted successfully
                                    // You can perform any additional operations here if needed
                                    if(finalIndex == (filenames.size()-1)){
                                        is_finished.set(true);
                                    }
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    // Handle the error
                                    if(finalIndex == (filenames.size()-1)){
                                        is_finished.set(true);
                                    }
                                }
                            }).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(finalIndex == (filenames.size()-1)){
                                        is_finished.set(true);
                                    }
                                }
                            }).addOnCanceledListener(new OnCanceledListener() {
                                @Override
                                public void onCanceled() {
                                    if(finalIndex == (filenames.size()-1)){
                                        is_finished.set(true);
                                    }
                                }
                            });
                }

            }
        }).start();

    }






    public static void clearDirectory_ImmediateFiles_WaitProgress(FirebaseStorage instance,AtomicBoolean is_finished, String directory) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                // Initialize Firebase Storage
                StorageReference storageRef = instance.getReference();

                // Create a reference to the directory you want to delete
                StorageReference directoryRef = storageRef.child(directory);

                // List all items (files and subdirectories) within the directory
                directoryRef.listAll()
                        .addOnCompleteListener(new OnCompleteListener<ListResult>() {
                            @Override
                            public void onComplete(@NonNull Task<ListResult> task) {
                                if (task.isSuccessful()) {
                                    // Delete each item (file or subdirectory) in the directory
                                    List<StorageReference> items = task.getResult().getItems();
                                    for (int index = 0; index < items.size(); index++) {
                                        int finalIndex = index;
                                        StorageReference item = items.get(index);
                                        item.delete()
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {
                                                        // Item (file or subdirectory) deleted successfully
                                                        // You can perform any additional operations here if needed
                                                        if(finalIndex == (items.size()-1)){
                                                            is_finished.set(true);
                                                        }
                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        // Handle the error
                                                        if(finalIndex == (items.size()-1)){
                                                            is_finished.set(true);
                                                        }
                                                    }
                                                }).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if(finalIndex == (items.size()-1)){
                                                            is_finished.set(true);
                                                        }
                                                    }
                                                }).addOnCanceledListener(new OnCanceledListener() {
                                                    @Override
                                                    public void onCanceled() {
                                                        if(finalIndex == (items.size()-1)){
                                                            is_finished.set(true);
                                                        }
                                                    }
                                                });
                                    }
                                } else {
                                    // Handle the error
                                    is_finished.set(true);
                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                is_finished.set(true);
                            }
                        }).addOnSuccessListener(new OnSuccessListener<ListResult>() {
                            @Override
                            public void onSuccess(ListResult listResult) {
                                is_finished.set(true);
                            }
                        }).addOnCanceledListener(new OnCanceledListener() {
                            @Override
                            public void onCanceled() {
                                is_finished.set(true);
                            }
                        });

            }
        }).start();


    }


    public static void uploadFile(
            FirebaseStorage instance,
            String local_file_path,
            String firebase_directory_path,
            String uploaded_file_name,
            AtomicBoolean finished_boolean,
            AtomicReference<String> atomic_string) {


        new Thread(new Runnable() {
            @Override
            public void run() {

                // Initialize Firebase Storage
                StorageReference storageRef = instance.getReference();

                // Read the content of the local file into a byte array
                File localFile = new File(local_file_path);
                byte[] data;
                try {
                    data = Files.readAllBytes(localFile.toPath());
                } catch (IOException e) {
                    // Handle the error
                    atomic_string.set(NULL_RESULT_IDENTIFIER);
                    finished_boolean.set(true);
                    return;
                }

                // Get the root folder reference
                StorageReference folderRef = storageRef;

                // Create nested directories if they exist in the firebase_directory_path
                String[] folder_path_branches = firebase_directory_path.split("/");
                for (String folderName : folder_path_branches) {
                    if (!folderName.isEmpty()) {
                        // Create a reference to the next level folder
                        folderRef = folderRef.child(folderName);
                    }
                }

                // Create a reference to the file inside the final folder
                StorageReference fileRef = folderRef.child(uploaded_file_name);

                // Upload the file to Firebase Storage
                fileRef.putBytes(data)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                // File upload successful
                                // Get the download URL of the uploaded file
                                Task<Uri> downloadUrlTask = taskSnapshot.getStorage().getDownloadUrl();
                                downloadUrlTask.addOnCompleteListener(new OnCompleteListener<Uri>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Uri> task) {
                                        if (task.isSuccessful()) {
                                            // Get the URL of the uploaded file
                                            Uri downloadUrl = task.getResult();
                                            String download_url_string = downloadUrl.toString();
                                            atomic_string.set(download_url_string);
                                            finished_boolean.set(true);
                                        } else {
                                            atomic_string.set(NULL_RESULT_IDENTIFIER);
                                            finished_boolean.set(true);
                                        }
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        atomic_string.set(NULL_RESULT_IDENTIFIER);
                                        finished_boolean.set(true);
                                    }
                                }).addOnCanceledListener(new OnCanceledListener() {
                                    @Override
                                    public void onCanceled() {
                                        atomic_string.set(NULL_RESULT_IDENTIFIER);
                                        finished_boolean.set(true);
                                    }
                                });
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Handle the error
                                atomic_string.set(NULL_RESULT_IDENTIFIER);
                                finished_boolean.set(true);
                            }
                        }).addOnCanceledListener(new OnCanceledListener() {
                            @Override
                            public void onCanceled() {
                                atomic_string.set(NULL_RESULT_IDENTIFIER);
                                finished_boolean.set(true);
                            }
                        });

            }
        }).start();


    }


     public static void uploadMultipleFile(
             FirebaseStorage instance,
             ArrayList<String> local_file_paths,
             String firebase_directory_path,
             ArrayList<String> uploaded_file_names,
             AtomicReference<ArrayList<String>> atomic_list) {





         new Thread(new Runnable() {
             @Override
             public void run() {


                 // Initialize Firebase Storage
                 StorageReference storageRef = instance.getReference();


                 for(int index = 0; index<local_file_paths.size(); index++){

                     // Read the content of the local file into a byte array
                     File localFile = new File(local_file_paths.get(index));
                     byte[] data;
                     try {
                         data = Files.readAllBytes(localFile.toPath());
                     } catch (IOException e) {
                         // Handle the error
                         atomic_list.get().add(NULL_RESULT_IDENTIFIER);
                         return;
                     }

                     // Get the root folder reference
                     StorageReference folderRef = storageRef;

                     // Create nested directories if they exist in the firebase_directory_path
                     String[] folder_path_branches = firebase_directory_path.split("/");
                     for (String folderName : folder_path_branches) {
                         if (!folderName.isEmpty()) {
                             // Create a reference to the next level folder
                             folderRef = folderRef.child(folderName);
                         }
                     }

                     // Create a reference to the file inside the final folder
                     StorageReference fileRef = folderRef.child(uploaded_file_names.get(index));

                     // Upload the file to Firebase Storage
                     fileRef.putBytes(data)
                             .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                 @Override
                                 public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                     // File upload successful
                                     // Get the download URL of the uploaded file
                                     Task<Uri> downloadUrlTask = taskSnapshot.getStorage().getDownloadUrl();
                                     downloadUrlTask.addOnCompleteListener(new OnCompleteListener<Uri>() {
                                         @Override
                                         public void onComplete(@NonNull Task<Uri> task) {
                                             if (task.isSuccessful()) {
                                                 // Get the URL of the uploaded file
                                                 Uri downloadUrl = task.getResult();
                                                 String download_url_string = downloadUrl.toString();
                                                 atomic_list.get().add(download_url_string);
                                             } else {
                                                 atomic_list.get().add(NULL_RESULT_IDENTIFIER);
                                             }
                                         }
                                     }).addOnFailureListener(new OnFailureListener() {
                                         @Override
                                         public void onFailure(@NonNull Exception e) {
                                             atomic_list.get().add(NULL_RESULT_IDENTIFIER);
                                         }
                                     }).addOnCanceledListener(new OnCanceledListener() {
                                         @Override
                                         public void onCanceled() {
                                             atomic_list.get().add(NULL_RESULT_IDENTIFIER);
                                         }
                                     });
                                 }
                             })
                             .addOnFailureListener(new OnFailureListener() {
                                 @Override
                                 public void onFailure(@NonNull Exception e) {
                                     // Handle the error
                                     atomic_list.get().add(NULL_RESULT_IDENTIFIER);
                                 }
                             }).addOnCanceledListener(new OnCanceledListener() {
                                 @Override
                                 public void onCanceled() {
                                     atomic_list.get().add(NULL_RESULT_IDENTIFIER);
                                 }
                             });
                 }

             }
         }).start();


     }



     public static void renameFile_WaitProgress(
             FirebaseStorage instance,
             AtomicBoolean is_finished,
             String directory,
             String old_file_name,
             String new_file_name) {

         new Thread(new Runnable() {
             @Override
             public void run() {
                 // Initialize Firebase Storage
                 StorageReference storageRef = instance.getReference();

                 // Create the full paths for the old and new file locations within the specified directory
                 StorageReference oldFileRef = storageRef.child(directory + "/" + old_file_name);
                 StorageReference newFileRef = storageRef.child(directory + "/" + new_file_name);

                 // Start the renaming process by copying the file
                 oldFileRef.getBytes(Long.MAX_VALUE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                     @Override
                     public void onSuccess(byte[] bytes) {
                         // Upload the file to the new location
                         newFileRef.putBytes(bytes).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                             @Override
                             public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                 // After copying to the new location, delete the old file
                                 oldFileRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                     @Override
                                     public void onSuccess(Void aVoid) {
                                         // Renaming (copy + delete) succeeded
                                         is_finished.set(true);
                                     }
                                 }).addOnFailureListener(new OnFailureListener() {
                                     @Override
                                     public void onFailure(@NonNull Exception e) {
                                         // Failed to delete the old file
                                         is_finished.set(true);
                                     }
                                 });
                             }
                         }).addOnFailureListener(new OnFailureListener() {
                             @Override
                             public void onFailure(@NonNull Exception e) {
                                 // Failed to upload the file to the new location
                                 is_finished.set(true);
                             }
                         });
                     }
                 }).addOnFailureListener(new OnFailureListener() {
                     @Override
                     public void onFailure(@NonNull Exception e) {
                         // Failed to download the old file
                         is_finished.set(true);
                     }
                 }).addOnCanceledListener(new OnCanceledListener() {
                     @Override
                     public void onCanceled() {
                         is_finished.set(true);
                     }
                 });
             }
         }).start();
     }


     public static void renameFile(
             FirebaseStorage instance,
             String directory,
             String old_file_name,
             String new_file_name) {

         new Thread(new Runnable() {
             @Override
             public void run() {
                 // Initialize Firebase Storage
                 StorageReference storageRef = instance.getReference();

                 // Create the full paths for the old and new file locations within the specified directory
                 StorageReference oldFileRef = storageRef.child(directory + "/" + old_file_name);
                 StorageReference newFileRef = storageRef.child(directory + "/" + new_file_name);

                 // Start the renaming process by copying the file
                 oldFileRef.getBytes(Long.MAX_VALUE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                     @Override
                     public void onSuccess(byte[] bytes) {
                         // Upload the file to the new location
                         newFileRef.putBytes(bytes).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                             @Override
                             public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                 // After copying to the new location, delete the old file
                                 oldFileRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                     @Override
                                     public void onSuccess(Void aVoid) {
                                         // Renaming (copy + delete) succeeded
                                     }
                                 }).addOnFailureListener(new OnFailureListener() {
                                     @Override
                                     public void onFailure(@NonNull Exception e) {
                                         // Failed to delete the old file
                                     }
                                 });
                             }
                         }).addOnFailureListener(new OnFailureListener() {
                             @Override
                             public void onFailure(@NonNull Exception e) {
                                 // Failed to upload the file to the new location
                             }
                         });
                     }
                 }).addOnFailureListener(new OnFailureListener() {
                     @Override
                     public void onFailure(@NonNull Exception e) {
                         // Failed to download the old file
                     }
                 }).addOnCanceledListener(new OnCanceledListener() {
                     @Override
                     public void onCanceled() {
                     }
                 });
             }
         }).start();
     }





    /*


    boolean return_boolean = false;
        AtomicBoolean finished_boolean = new AtomicBoolean(false);
        AtomicReference<String> atomic_string = new AtomicReference<>(null);
        RCTfirebaseStorageUtil.createOverrideFile_GetURL_X0001(
                instance,
                file_name,
                file_contents,
                finished_boolean,
                atomic_string);
        while(!return_boolean){
            if(!finished_boolean.get() && atomic_string.get() == null){
                try {
                    Thread.sleep(thread_wait);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }else{
                return_boolean = true;
            }
        }
        return atomic_string.get();


     */




}
