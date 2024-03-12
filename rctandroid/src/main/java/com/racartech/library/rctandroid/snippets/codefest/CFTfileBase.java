package com.racartech.library.rctandroid.snippets.codefest;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class CFTfileBase{

    public static String SIMPLE_FILE_DB_PATH = "";
    public static void initialize(Context app_context){
        SIMPLE_FILE_DB_PATH = getDir_IntAppFiles(app_context).concat("/codefest.txt");

        if(!doesFileExist(SIMPLE_FILE_DB_PATH)){
            createFile(SIMPLE_FILE_DB_PATH);
        }

    }


    public static boolean saveDouble(String name, Double value) throws IOException{
        if(!doExist(name)){
            String line_content = name.concat(",").concat(String.valueOf(value));
            ArrayList<String> file_content = readFile_ArrayList(SIMPLE_FILE_DB_PATH);
            file_content.add(line_content);
            overrideFile(SIMPLE_FILE_DB_PATH, file_content);
            return true;
        }else{
            return false;
        }
    }

    public static boolean saveInteger(String name, int value) throws IOException{
        if(!doExist(name)){
            String line_content = name.concat(",").concat(String.valueOf(value));
            ArrayList<String> file_content = readFile_ArrayList(SIMPLE_FILE_DB_PATH);
            file_content.add(line_content);
            overrideFile(SIMPLE_FILE_DB_PATH, file_content);
            return true;
        }else{
            return false;
        }
    }

    public static boolean saveLong(String name, long value) throws IOException{
        if(!doExist(name)){
            String line_content = name.concat(",").concat(String.valueOf(value));
            ArrayList<String> file_content = readFile_ArrayList(SIMPLE_FILE_DB_PATH);
            file_content.add(line_content);
            overrideFile(SIMPLE_FILE_DB_PATH, file_content);
            return true;
        }else{
            return false;
        }
    }

    public static boolean saveFloat(String name, float value) throws IOException{
        if(!doExist(name)){
            String line_content = name.concat(",").concat(String.valueOf(value));
            ArrayList<String> file_content = readFile_ArrayList(SIMPLE_FILE_DB_PATH);
            file_content.add(line_content);
            overrideFile(SIMPLE_FILE_DB_PATH, file_content);
            return true;
        }else{
            return false;
        }
    }

    public static boolean saveBoolean(String name, boolean value) throws IOException{
        if(!doExist(name)){
            String line_content = name.concat(",").concat(String.valueOf(value));
            ArrayList<String> file_content = readFile_ArrayList(SIMPLE_FILE_DB_PATH);
            file_content.add(line_content);
            overrideFile(SIMPLE_FILE_DB_PATH, file_content);
            return true;
        }else{
            return false;
        }
    }



    public static boolean doExist(String name) throws IOException{
        boolean do_exist = false;
        ArrayList<String> file_contents = readFile_ArrayList(SIMPLE_FILE_DB_PATH);
        for(int index = 0; index<file_contents.size(); index++){
            String line_value = file_contents.get(index);
            String[] line_content = line_value.split(",");
            String variable_name = line_content[0];
            if(variable_name.equals(name)){
                do_exist = true;
            }
        }
        return do_exist;
    }






    public static Double readDouble(String name) throws IOException{
        ArrayList<String> file_contents = readFile_ArrayList(SIMPLE_FILE_DB_PATH);
        for(int index = 0; index<file_contents.size(); index++){
            String line_value = file_contents.get(index);
            String[] line_content = line_value.split(",");
            String variable_name = line_content[0];
            if(variable_name.equals(name)){
                return Double.parseDouble(line_content[1]);
            }
        }
        return -99999999999999.0;
    }


    public static Integer readInteger(String name) throws IOException{
        ArrayList<String> file_contents = readFile_ArrayList(SIMPLE_FILE_DB_PATH);
        for(int index = 0; index<file_contents.size(); index++){
            String line_value = file_contents.get(index);
            String[] line_content = line_value.split(",");
            String variable_name = line_content[0];
            if(variable_name.equals(name)){
                return Integer.parseInt(line_content[1]);
            }
        }
        return -999999999;
    }


    public static Long readLong(String name) throws IOException{
        ArrayList<String> file_contents = readFile_ArrayList(SIMPLE_FILE_DB_PATH);
        for(int index = 0; index<file_contents.size(); index++){
            String line_value = file_contents.get(index);
            String[] line_content = line_value.split(",");
            String variable_name = line_content[0];
            if(variable_name.equals(name)){
                return Long.parseLong(line_content[1]);
            }
        }
        return -99999999999999L;
    }


    public static Float readFloat(String name) throws IOException{
        ArrayList<String> file_contents = readFile_ArrayList(SIMPLE_FILE_DB_PATH);
        for(int index = 0; index<file_contents.size(); index++){
            String line_value = file_contents.get(index);
            String[] line_content = line_value.split(",");
            String variable_name = line_content[0];
            if(variable_name.equals(name)){
                return Float.parseFloat(line_content[1]);
            }
        }
        return -999999999999.0F;
    }

    public static Boolean readBoolean(String name, boolean default_value) throws IOException{
        ArrayList<String> file_contents = readFile_ArrayList(SIMPLE_FILE_DB_PATH);
        for(int index = 0; index<file_contents.size(); index++){
            String line_value = file_contents.get(index);
            String[] line_content = line_value.split(",");
            String variable_name = line_content[0];
            if(variable_name.equals(name)){
                return Boolean.parseBoolean(line_content[1]);
            }
        }
        return default_value;
    }










    ////////////////////////////////////////////////////////////////////



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



    public static String getDir_IntAppFiles(Context app_context){
        return app_context.getFilesDir().getAbsolutePath();
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

    public static boolean doesFileExist(String file_path){
        return new File(file_path).exists();
    }

    public static boolean doesDirectoryExist(String directory){
        return new File(directory).exists();
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





}
