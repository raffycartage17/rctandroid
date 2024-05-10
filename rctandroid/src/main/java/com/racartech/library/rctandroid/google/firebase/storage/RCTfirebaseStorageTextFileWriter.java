package com.racartech.library.rctandroid.google.firebase.storage;

import android.content.Context;

import com.google.firebase.storage.FirebaseStorage;
import com.racartech.library.rctandroid.RCTlibraryConstants;
import com.racartech.library.rctandroid.datatypes.RCTstring;
import com.racartech.library.rctandroid.file.RCTdirectory;
import com.racartech.library.rctandroid.file.RCTfile;
import com.racartech.library.rctandroid.net.RCTinternet;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class RCTfirebaseStorageTextFileWriter {
    FirebaseStorage INSTANCE;
    Context CONTEXT;
    String FSTORAGE_DIRECTORY = null;
    String FSTORAGE_FILENAME = null;
    String SESSION_FILE = null;
    String LOCAL_DIRECTORY = null;
    long THREAD_WAIT;


    int BUFFER_SIZE = 0;
    int CONNECTION_TIMEOUT = 0;
    int READ_TIMEOUT = 0;


    public RCTfirebaseStorageTextFileWriter(
            FirebaseStorage storage_instance,
            Context context,
            String fstorage_directory,
            String fstorage_file_name,
            long thread_wait,
            int buffer_size,
            int connection_timeout,
            int read_timeout
    ){
        this.INSTANCE = storage_instance;
        this.CONTEXT = context;
        this.FSTORAGE_DIRECTORY = fstorage_directory;
        this.FSTORAGE_FILENAME = fstorage_file_name;
        this.THREAD_WAIT = thread_wait;

        this.BUFFER_SIZE = buffer_size;
        this.CONNECTION_TIMEOUT = connection_timeout;
        this.READ_TIMEOUT = read_timeout;

        this.LOCAL_DIRECTORY =
                RCTfile.getDir_IntAppFiles(context).
                        concat("/").
                        concat(RCTlibraryConstants.LIBRARY_LOCAL_DIRECTORY);

        this.SESSION_FILE =
                this.LOCAL_DIRECTORY.
                        concat("/").
                        concat(RCTstring.randomString(64, 64));

    }





    public synchronized void initialize(){
        try {
            if(!RCTfile.doesDirectoryExist(this.LOCAL_DIRECTORY)){
                RCTdirectory.createDirectory(this.LOCAL_DIRECTORY);
            }
            if(RCTfile.doesFileExist(SESSION_FILE)){
                RCTfile.delete_File(SESSION_FILE);
            }

            String fstorage_file_url;

            if(this.FSTORAGE_DIRECTORY != null) {
                fstorage_file_url = RCTfirebaseStorage.getDownloadURL(
                        this.INSTANCE,
                        FSTORAGE_DIRECTORY,
                        FSTORAGE_FILENAME,
                        100
                );
            }else{
                fstorage_file_url = RCTfirebaseStorage.getDownloadURL(
                        this.INSTANCE,
                        FSTORAGE_FILENAME,
                        100
                );
            }

            System.out.println(fstorage_file_url);
            if(fstorage_file_url != null){
                RCTinternet.downloadFile(
                        fstorage_file_url,
                        this.SESSION_FILE,
                        this.BUFFER_SIZE,
                        this.CONNECTION_TIMEOUT,
                        this.READ_TIMEOUT
                );
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public synchronized void initialize(AtomicBoolean finished_boolean){
        try {
            if(!RCTfile.doesDirectoryExist(this.LOCAL_DIRECTORY)){
                RCTdirectory.createDirectory(this.LOCAL_DIRECTORY);
            }
            if(RCTfile.doesFileExist(SESSION_FILE)){
                RCTfile.delete_File(SESSION_FILE);
            }

            String fstorage_file_url;

            if(this.FSTORAGE_DIRECTORY != null) {
                fstorage_file_url = RCTfirebaseStorage.getDownloadURL(
                        this.INSTANCE,
                        FSTORAGE_DIRECTORY,
                        FSTORAGE_FILENAME,
                        100
                );
            }else{
                fstorage_file_url = RCTfirebaseStorage.getDownloadURL(
                        this.INSTANCE,
                        FSTORAGE_FILENAME,
                        100
                );
            }

            System.out.println(fstorage_file_url);
            if(fstorage_file_url != null){
                RCTinternet.downloadFile(
                        fstorage_file_url,
                        this.SESSION_FILE,
                        this.BUFFER_SIZE,
                        this.CONNECTION_TIMEOUT,
                        this.READ_TIMEOUT
                );
            }
            finished_boolean.set(true);
        }catch (Exception ex){
            ex.printStackTrace();
            finished_boolean.set(true);
        }
    }






    public String getLocalFilePath(){
        return this.SESSION_FILE;
    }

    public File getLocalFilePath_FILE(){
        return new File(this.SESSION_FILE);
    }

    public synchronized ArrayList<String> getFileContents(){
        ArrayList<String> file_contents = null;
        try {
            file_contents = RCTfile.readFile_ArrayList(this.SESSION_FILE);
        }catch (Exception ignored){}
        return file_contents;
    }

    public synchronized boolean override(ArrayList<String> file_contents){
        try {
            RCTfile.overrideFile(this.SESSION_FILE, file_contents);
            return true;
        }catch(Exception ignored){
            return false;
        }
    }

    public synchronized boolean save(){
        String fstorage_url = null;
        ArrayList<String> file_contents = getFileContents();

        if(FSTORAGE_DIRECTORY != null) {
            fstorage_url = RCTfirebaseStorage.createOverrideFile_GetURL(
                    this.INSTANCE,
                    FSTORAGE_DIRECTORY,
                    FSTORAGE_FILENAME,
                    file_contents,
                    this.THREAD_WAIT);
        }else{
            fstorage_url = RCTfirebaseStorage.createOverrideFile_GetURL(
                    this.INSTANCE,
                    FSTORAGE_FILENAME,
                    file_contents,
                    this.THREAD_WAIT);
        }
        return fstorage_url != null;
    }

    public synchronized boolean overrideSave(ArrayList<String> file_contents){
        boolean override_success = override(file_contents);
        if(override_success){
            return save();
        }else{
            return false;
        }
    }


    public synchronized void close(boolean save){
        if(save){
            save();
        }
        RCTfile.delete_File(this.SESSION_FILE);
        this.INSTANCE = null;
        this.CONTEXT = null;
        this.FSTORAGE_DIRECTORY = null;
        this.FSTORAGE_FILENAME = null;
        this.SESSION_FILE = null;
        this.LOCAL_DIRECTORY = null;
    }


    //Returns previous value
    public synchronized String set(int index, String new_value){
        ArrayList<String> file_contents = getFileContents();
        if(index >= 0 && index<file_contents.size()){
            String previous_content = file_contents.set(index,new_value);
            override(file_contents);
            return previous_content;
        }else{
            return null;
        }
    }


    //Returns previous value
    public synchronized String get(int index){
        ArrayList<String> file_contents = getFileContents();
        if(index >= 0 && index<file_contents.size()){
            return file_contents.get(index);
        }else{
            return null;
        }
    }










}
