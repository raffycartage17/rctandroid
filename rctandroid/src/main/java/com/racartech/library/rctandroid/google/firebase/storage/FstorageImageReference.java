package com.racartech.library.rctandroid.google.firebase.storage;

import android.graphics.Bitmap;

import com.google.firebase.storage.FirebaseStorage;
import com.racartech.library.rctandroid.file.RCTfile;
import com.racartech.library.rctandroid.media.RCTbitmap;

public class FstorageImageReference extends FstorageFileReference{


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


    public Bitmap getBitmap(FirebaseStorage instance){
        boolean load_from_local = true;

        if(getLOCAL_CACHED_FILEPATH() == null){
            load_from_local = false;
        }else{
            if(!RCTfile.doesFileExist(getLOCAL_CACHED_FILEPATH())){
                load_from_local = false;
            }
        }

        if(load_from_local){
            try {
                return RCTbitmap.getBitmapForFile(getLOCAL_CACHED_FILEPATH());
            }catch (Exception ex){
                byte[] byte_data = RCTfirebaseStorage.loadFile(instance,getPATH(),getTHREAD_WAIT());
                return RCTbitmap.getBitmap(byte_data);
            }
        }else{
            byte[] byte_data = RCTfirebaseStorage.loadFile(instance,getPATH(),getTHREAD_WAIT());
            return RCTbitmap.getBitmap(byte_data);
        }
    }



}
