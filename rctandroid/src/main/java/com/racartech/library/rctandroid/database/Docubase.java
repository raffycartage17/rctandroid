package com.racartech.library.rctandroid.database;


import android.content.Context;

import com.racartech.library.rctandroid.array.RCTarray;
import com.racartech.library.rctandroid.file.RCTdirectory;
import com.racartech.library.rctandroid.file.RCTfile;

import org.json.JSONObject;

import java.util.ArrayList;

public class Docubase {

    private static Docubase instance;
    private final String ROOT_DIR;

    private Docubase(Context context) {
        ROOT_DIR = RCTfile.getDir_IntAppFiles(context).concat("/rctandroid-docubase");
        if(RCTdirectory.doesDirectoryExist(this.ROOT_DIR)){
            RCTfile.createDirectory(this.ROOT_DIR);
        }
    }
    public static synchronized Docubase getInstance(Context context) {
        if (instance == null) {
            instance = new Docubase(context.getApplicationContext());
        }
        return instance;
    }


    public synchronized boolean createDocument(String directory, String document){
        String fixed_dir = removeForwardSlash(directory);
        String absolute_dir = this.ROOT_DIR.concat("/").concat(fixed_dir);
        if(!RCTdirectory.doesDirectoryExist(absolute_dir)){
            createDirectory(directory);
        }
        String document_filepath = getDocumentAbsolutePath(directory,document);

        if(!RCTfile.doesFileExist(document_filepath)){
            RCTfile.createFile(document_filepath);
            return true;
        }else{
            return false;
        }
    }






    private synchronized void createDirectory(String directory){
        ArrayList<String> directories = getDirectories(directory);
        RCTdirectory.createDirectory(directories);
    }

    private String removeForwardSlash(String path) {
        return path.replaceAll("^/+","");
    }


    private ArrayList<String> getDirectories(String directory){
        String[] parts = directory.split("/");
        ArrayList<String> directories = new ArrayList<>();
        for(int index = 0; index< parts.length; index++){
            directories.add(concatenateDir(parts,index));
        }
        return directories;
    }
    private String concatenateDir(String[] parts, int index){
        String concat_dir = "";
        for(int dex = 0; dex<=index; dex++){
            if(dex < index){
                concat_dir = concat_dir.concat(parts[dex]).concat("/");
            }else{
                concat_dir = concat_dir.concat(parts[dex]);
            }
        }
        return this.ROOT_DIR.concat("/").concat(concat_dir);
    }

    private String getDirectoryAbsolutePath(String directory){
        return this.ROOT_DIR.concat("/").concat(directory);
    }

    private String getDocumentAbsolutePath(String directory, String document){
        return
                this.ROOT_DIR.
                    concat("/").
                    concat(directory).
                    concat("/").
                    concat(document).
                    concat(".json");
    }



    ////////////////////////////////

//    public String createField(String field_name, String field_value){
//
//    }

    ////////////////////////////////

    public synchronized JSONObject getDocumentData(String directory, String filename){
        String document_absolute_path = getDocumentAbsolutePath(directory,filename);
        try {
            ArrayList<String> file_contents = RCTfile.readFile_ArrayList(document_absolute_path);
            String json_string = RCTarray.concatArrayListStringToString(file_contents);
            return new JSONObject(json_string);
        }catch (Exception ignored){
            return new JSONObject();
        }
    }







}
