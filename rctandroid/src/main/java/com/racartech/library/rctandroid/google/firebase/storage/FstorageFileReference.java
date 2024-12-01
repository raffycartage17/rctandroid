package com.racartech.library.rctandroid.google.firebase.storage;

import com.google.firebase.storage.FirebaseStorage;
import com.racartech.library.rctandroid.file.RCTdirectory;
import com.racartech.library.rctandroid.file.RCTfile;
import com.racartech.library.rctandroid.security.RCThashing;

public class FstorageFileReference {


    protected String PATH;
    protected String LOCAL_CACHED_FILEPATH;
    protected String LOCAL_CACHE_DIR;
    protected long THREAD_WAIT;


    public FstorageFileReference(){
    }

    public FstorageFileReference(
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

    public FstorageFileReference(
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

    protected void init(
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
                this.LOCAL_CACHED_FILEPATH = local_cache_directory.
                        concat("/").
                        concat(getCachedFileName());
                if(pre_cache){
                    preCacheFile(instance, local_cache_directory);
                }
            }catch (Exception ex){
                this.LOCAL_CACHED_FILEPATH = null;
            }
        }
    }

    protected void preCacheFile(FirebaseStorage instance, String local_cache_directory){
        if(!local_cache_directory.isEmpty()){
            byte[] file_data = RCTfirebaseStorage.loadFile(instance,getPATH(),this.THREAD_WAIT);
            RCTfile.saveAsFile(file_data, getLOCAL_CACHED_FILEPATH());
        }
    }


    public void deleteCachedFile(){
        if(RCTfile.doesFileExist(getLOCAL_CACHED_FILEPATH())) {
            RCTfile.deleteFile(getLOCAL_CACHED_FILEPATH());
        }
    }

    public void cacheFile(FirebaseStorage instance){
        if(getLOCAL_CACHE_DIR() != null && getLOCAL_CACHED_FILEPATH() != null){
            if(!RCTdirectory.doesDirectoryExist(getLOCAL_CACHE_DIR())){
                RCTdirectory.createDirectory(getLOCAL_CACHE_DIR());
            }
            byte[] file_data = RCTfirebaseStorage.loadFile(instance,getPATH(),this.THREAD_WAIT);
            RCTfile.saveAsFile(file_data, getLOCAL_CACHED_FILEPATH());
        }
    }

    public void updateLocalFromServer(FirebaseStorage instance){
        cacheFile(instance);
    }

    public void updateServerFromLocal(FirebaseStorage instance){
        RCTfirebaseStorage.uploadFile(
                instance,
                this.LOCAL_CACHED_FILEPATH,
                this.PATH,
                this.THREAD_WAIT);
    }

    public void replaceFile(
            FirebaseStorage instance,
            String replacement_local_filepath
    ){
        RCTfile.deleteFile(this.LOCAL_CACHED_FILEPATH);
        RCTfile.copyFile(replacement_local_filepath,this.LOCAL_CACHED_FILEPATH);
        RCTfirebaseStorage.uploadFile(
                instance,
                this.LOCAL_CACHED_FILEPATH,
                this.PATH,
                this.THREAD_WAIT);
    }

    public String getCachedFileName(){
        try {
            return RCThashing.sha256(getPATH());
        }catch (Exception ex){
            return null;
        }
    }



    public String getDownloadURL(FirebaseStorage instance){
        return RCTfirebaseStorage.getDownloadURL(instance,getPATH(),getTHREAD_WAIT());
    }






    public String getPATH() {
        return PATH;
    }

    public void setPATH(String PATH) {
        this.PATH = PATH;
    }

    public String getLOCAL_CACHED_FILEPATH() {
        return LOCAL_CACHED_FILEPATH;
    }

    public void setLOCAL_CACHED_FILEPATH(String LOCAL_CACHED_FILEPATH) {
        this.LOCAL_CACHED_FILEPATH = LOCAL_CACHED_FILEPATH;
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
