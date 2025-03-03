package com.racartech.library.rctandroid.filebase;

import android.content.Context;

import com.racartech.library.rctandroid.file.RCTdirectory;
import com.racartech.library.rctandroid.file.RCTfile;

import java.util.ArrayList;

public class DocumentBase{

    private Context context;
    private String root_directory;

    public DocumentBase(Context context){
        this.context = context;
        this.root_directory = RCTfile.getDir_IntAppFiles(context).concat("/document_base");

        initialize();
    }

    private void initialize(){
        if(!RCTdirectory.doesDirectoryExist(root_directory)){
            RCTdirectory.createDirectory(root_directory);
        }
    }

    public boolean createCollection(String collection_path) {
        String col_path = collection_path;
        col_path = removeDirectoryIdentifierFromBeginning(col_path, '/');
        String new_collection_path = getCollectionAbsolutePath(col_path);
        if(!RCTdirectory.doesDirectoryExist(new_collection_path)){
            RCTdirectory.createPathSectionBySection(new_collection_path, false);
            return true;
        }else{
            return false;
        }
    }

    public boolean deleteCollection(String collection_path){
        String absolute_collection_path = getCollectionAbsolutePath(collection_path);
        if(RCTfile.doesDirectoryExist(absolute_collection_path)){
            RCTdirectory.deleteDirectory(absolute_collection_path);
            return true;
        }else{
            return false;
        }
    }

    public ArrayList<String> getDocuments(String collection_path){
        String absolute_path = getCollectionAbsolutePath(collection_path);
        if(RCTdirectory.doesDirectoryExist(absolute_path)) {
            try {
                ArrayList<String> documents = new ArrayList<>();
                ArrayList<String> file_list = RCTdirectory.getAllFiles_ArrayList(absolute_path, false);
                documents.addAll(file_list);

                return documents;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                return null;
            }
        }else{
            return null;
        }
    }




    private String getCollectionAbsolutePath(String collection_path){
        return this.root_directory.concat("/").concat(collection_path);
    }



    private char getChar(String text, int index){
        char[] char_array = text.toCharArray();
        return char_array[index];
    }

    private String removeDirectoryIdentifierFromBeginning(String text, char remove_char){
        String the_text = text;
        while(getChar(the_text,0) == remove_char){
            the_text = the_text.substring(1);
        }
        return the_text;
    }




}
