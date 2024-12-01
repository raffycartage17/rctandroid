package com.racartech.library.rctandroid.google.firebase.storage;

import android.graphics.Bitmap;

import com.google.firebase.storage.FirebaseStorage;
import com.racartech.library.rctandroid.file.RCTdirectory;
import com.racartech.library.rctandroid.file.RCTfile;
import com.racartech.library.rctandroid.media.RCTbitmap;
import com.racartech.library.rctandroid.security.RCThashing;

public class FstorageImageReference {


    private String PATH;
    private String LOCAL_FILE_PATH;
    private String LOCAL_CACHE_DIR;
    private long THREAD_WAIT;

    public FstorageImageReference(
            FirebaseStorage instance,
            String local_cache_directory,
            String storage_file_path,
            boolean pre_cache,
            long thread_wait
    ){
        init(
                instance,
                local_cache_directory,
                storage_file_path,
                pre_cache,
                thread_wait
        );
    }

    public FstorageImageReference(
            FirebaseStorage instance,
            String local_cache_directory,
            String directory,
            String file_name,
            boolean pre_cache,
            long thread_wait
    ){
       init(
               instance,
               local_cache_directory,
               directory.concat("/").concat(file_name),
               pre_cache,
               thread_wait
       );
    }

    private void init(
            FirebaseStorage instance,
            String local_cache_directory,
            String storage_filepath,
            boolean pre_cache,
            long thread_wait
    ){
        this.PATH = storage_filepath;
        this.LOCAL_CACHE_DIR = local_cache_directory;
        this.THREAD_WAIT = thread_wait;

        if(local_cache_directory != null){
            try {
                this.LOCAL_FILE_PATH = local_cache_directory.
                        concat("/").
                        concat(RCThashing.sha256(storage_filepath));
                if(pre_cache){
                    preCacheFile(instance, local_cache_directory);
                }
            }catch (Exception ex){
                this.LOCAL_FILE_PATH = null;
            }
        }
    }

    private void preCacheFile(FirebaseStorage instance, String local_cache_directory){
        if(!local_cache_directory.isEmpty()){
            byte[] file_data = RCTfirebaseStorage.loadFile(instance,getPATH(),this.THREAD_WAIT);
            RCTfile.saveAsFile(file_data,getLOCAL_FILE_PATH());
        }
    }


    public void deleteCachedFile(){
        if(RCTfile.doesFileExist(getLOCAL_FILE_PATH())) {
            RCTfile.deleteFile(getLOCAL_FILE_PATH());
        }
    }

    public void cacheFile(FirebaseStorage instance){
        if(getLOCAL_CACHE_DIR() != null && getLOCAL_FILE_PATH() != null){
            if(!RCTdirectory.doesDirectoryExist(getLOCAL_CACHE_DIR())){
                RCTdirectory.createDirectory(getLOCAL_CACHE_DIR());
            }
            byte[] file_data = RCTfirebaseStorage.loadFile(instance,getPATH(),this.THREAD_WAIT);
            RCTfile.saveAsFile(file_data,getLOCAL_FILE_PATH());
        }
    }

    public String getDownloadURL(FirebaseStorage instance){
        return RCTfirebaseStorage.getDownloadURL(instance,getPATH(),getTHREAD_WAIT());
    }



    public Bitmap getBitmap(FirebaseStorage instance){
        boolean load_from_local = true;

        if(getLOCAL_FILE_PATH() == null){
            load_from_local = false;
        }else{
            if(!RCTfile.doesFileExist(getLOCAL_FILE_PATH())){
                load_from_local = false;
            }
        }

        if(load_from_local){
            try {
                System.out.println(">>>>>>>>>> Bitmap from Local");
                return RCTbitmap.getBitmapForFile(getLOCAL_FILE_PATH());
            }catch (Exception ex){
                System.out.println(">>>>>>>>>> Bitmap from Server (From Exception)");
                byte[] byte_data = RCTfirebaseStorage.loadFile(instance,getPATH(),this.THREAD_WAIT);
                return RCTbitmap.getBitmap(byte_data);
            }
        }else{
            System.out.println(">>>>>>>>>> Bitmap from Server");
            byte[] byte_data = RCTfirebaseStorage.loadFile(instance,getPATH(),this.THREAD_WAIT);
            return RCTbitmap.getBitmap(byte_data);
        }
    }


    public String getPATH() {
        return PATH;
    }

    public void setPATH(String PATH) {
        this.PATH = PATH;
    }

    public String getLOCAL_FILE_PATH() {
        return LOCAL_FILE_PATH;
    }

    public void setLOCAL_FILE_PATH(String LOCAL_FILE_PATH) {
        this.LOCAL_FILE_PATH = LOCAL_FILE_PATH;
    }

    public String getLOCAL_CACHE_DIR() {
        return LOCAL_CACHE_DIR;
    }

    public void setLOCAL_CACHE_DIR(String LOCAL_CACHE_DIR) {
        this.LOCAL_CACHE_DIR = LOCAL_CACHE_DIR;
    }

    public long getTHREAD_WAIT() {
        return this.THREAD_WAIT;
    }

    public void setTHREAD_WAIT(long thread_wait) {
        this.THREAD_WAIT = thread_wait;
    }
}
