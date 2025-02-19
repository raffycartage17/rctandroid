package com.racartech.library.rctandroid.filebase;

import android.content.Context;

import com.racartech.library.rctandroid.file.RCTdirectory;
import com.racartech.library.rctandroid.file.RCTfile;
import com.racartech.library.rctandroid.math.RCTdataSizeConverter;

public class RByteBase {
    private String DEFAULT_TABLE_NAME = "data";
    private String DB_ROOT_DIR;
    private String DB_DIR; //For single db mode
    private String DB_PATH;
    private long MAX_FILE_SIZE = (long) RCTdataSizeConverter.megabytesToBytes(100);

    public RByteBase(Context context){
        DB_ROOT_DIR = RCTfile.getDir_IntAppFiles(context).concat("/rcache");
        DB_DIR = DB_ROOT_DIR.concat("/data_cache/");
        DB_PATH = getTargetFile();



        if(!RCTdirectory.doesDirectoryExist(DB_ROOT_DIR)){
            RCTdirectory.createDirectory(DB_ROOT_DIR);
        }

        if(!RCTdirectory.doesDirectoryExist(DB_DIR)){
            RCTdirectory.createDirectory(DB_DIR);
        }

        if(!RCTfile.doesFileExist(DB_PATH)){
            RCTfile.createFile(DB_PATH);
        }

    }


    private String getTargetFile(){
        String[] cache_files = RCTdirectory.getAllFiles(DB_DIR,true);

        if(cache_files != null) {
            if (cache_files.length > 0) {
                for (int index = 0; index < cache_files.length; index++) {
                    String current_file = cache_files[index];
                    String filename = RCTfile.getFileName(current_file);
                    long current_file_size = RCTfile.getSize(current_file);
                    if (current_file_size <= MAX_FILE_SIZE) {
                        return current_file;
                    } else {
                        int new_file_number = Integer.parseInt(filename) + 1;
                        return createCacheFile(new_file_number);
                    }
                }
            } else {
                return createCacheFile(0);
            }
        }
        return null;
    }

    private String createCacheFile(int file_number){
        String file_path = DB_DIR.concat(String.valueOf(file_number));
        RCTfile.createFile(file_path);
        return file_path;
    }


//    private String getTargetFile(){
//        String[] cache_files = RCTdirectory.getAllFiles(this.DB_DIR, true);
//        String target_file = null;
//        if(cache_files.length > 0){
//            for(int index = 0; index<cache_files.length; index++){
//
//                String current_file = cache_files[index];
//                long file_size = RCTfile.getSize(current_file);
//                if(file_size <= MAX_FILE_SIZE){
//                    return current_file;
//                }else{
//
//                }
//            }
//        }else{
//            target_file = this.DB_DIR.concat("/").concat("0").concat(DEFAULT_DB_FILE_EXT);
//            RCTfile.createFile(this.DB_DIR.concat("/").concat(DEFAULT_DB_FILE_NAME).concat("1").concat(DEFAULT_DB_FILE_EXT));
//            return target_file;
//        }
//    }






}
