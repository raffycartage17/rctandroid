package com.racartech.library.rctandroid.logging;

import com.racartech.library.rctandroid.file.RCTfile;
import com.racartech.library.rctandroid.location.RCTlatlng;
import com.racartech.library.rctandroid.time.RCTtime;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class RCTloggingLocationData{


    public static String FILE_PATH = null;
    public static AtomicBoolean IS_LOGGING_ENABLED = new AtomicBoolean(false);

    public static void setFilePath(String file_path){
        FILE_PATH = file_path;
        if(!RCTfile.doesFileExist(FILE_PATH)){
           RCTfile.createFile(FILE_PATH);
        }
    }

    public static void setLoggingActive(){
        IS_LOGGING_ENABLED.set(true);
    }
    public static void setLoggingInActive(){
        IS_LOGGING_ENABLED.set(false);
    }

    public static void archiveCurrentRecord(){
        try {
            ArrayList<String> file_contents = RCTfile.readFile_ArrayList(FILE_PATH);
            long log_time_ms = System.currentTimeMillis();
            String time_stamp = RCTtime.getTimeStamp_MMDDYYYY_HHMMSS(log_time_ms);

            String parent_dir = RCTfile.getParentDirectory(FILE_PATH);
            String archived_log_file_path =
                    parent_dir.
                            concat("/").
                            concat("rctandroid_location_log_").
                            concat(time_stamp).
                            concat(".txt");
            RCTfile.createFile(archived_log_file_path);
            RCTfile.overrideFile(archived_log_file_path,file_contents);
        }catch(Exception ignored){}

    }



    public static void add(double latitude, double longitude){
        if(RCTloggingLocationData.IS_LOGGING_ENABLED.get()){
            RCTlatlng new_coords = new RCTlatlng(System.currentTimeMillis(), latitude, longitude);
            addToFile(new_coords.toJSONString());
        }
    }


    public static ArrayList<RCTlatlng> getCoordinatesData(){
        ArrayList<RCTlatlng> coords_data = new ArrayList<>();
        try {
            ArrayList<String> file_contents = readLogFile();
            for(int index = 0; index<file_contents.size(); index++){
                coords_data.add(new RCTlatlng(file_contents.get(index)));
            }
        }catch(Exception ignored){
            return null;
        }
        return coords_data;
    }



    private static void addToFile(String json_string){
        try {
            ArrayList<String> file_contents = RCTfile.readFile_ArrayList(FILE_PATH);
            file_contents.add(json_string);
            RCTfile.overrideFile(FILE_PATH,file_contents);
        }catch (Exception ignored){}
    }
    public static ArrayList<String> readLogFile(){
        try{
            return RCTfile.readFile_ArrayList(FILE_PATH);
        }catch (Exception ignored){
            return null;
        }
    }

    public static void clearLogFile(){
        try {
            RCTfile.clearData(FILE_PATH);
        }catch (Exception ignored){}
    }







}






