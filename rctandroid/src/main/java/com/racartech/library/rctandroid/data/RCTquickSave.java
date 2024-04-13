package com.racartech.library.rctandroid.data;

import com.racartech.library.rctandroid.file.RCTfile;
import com.racartech.library.rctandroid.json.RCTjson;

import java.io.IOException;
import java.util.ArrayList;

public class RCTquickSave{

    public String SAVE_FILE_PATH = null;
    public RCTquickSave(String save_file_path){
        this.SAVE_FILE_PATH = save_file_path;
        if(!RCTfile.doesFileExist(this.SAVE_FILE_PATH)){
            RCTfile.createFile(this.SAVE_FILE_PATH);
        }
    }

    public void addString(String name, String value){
        ArrayList<String> json_contents = new ArrayList<>();
        json_contents.add(name);
        json_contents.add(value);
        String json_string = RCTjson.convertArrayListToString(json_contents);

        ArrayList<String> file_contents = getFileContents();
        int name_position = getPosition(file_contents, name);
        if(name_position >= 0){
            file_contents.set(name_position, json_string);
        }else{
            file_contents.add(json_string);
        }
        saveFileContents(file_contents);
    }

    public void addInteger(String name, int value){
        ArrayList<String> json_contents = new ArrayList<>();
        json_contents.add(name);
        json_contents.add(String.valueOf(value));
        String json_string = RCTjson.convertArrayListToString(json_contents);

        ArrayList<String> file_contents = getFileContents();
        int name_position = getPosition(file_contents, name);
        if(name_position >= 0){
            file_contents.set(name_position, json_string);
        }else{
            file_contents.add(json_string);
        }
        saveFileContents(file_contents);
    }

    public void addDouble(String name, double value){
        ArrayList<String> json_contents = new ArrayList<>();
        json_contents.add(name);
        json_contents.add(String.valueOf(value));
        String json_string = RCTjson.convertArrayListToString(json_contents);

        ArrayList<String> file_contents = getFileContents();
        int name_position = getPosition(file_contents, name);
        if(name_position >= 0){
            file_contents.set(name_position, json_string);
        }else{
            file_contents.add(json_string);
        }
        saveFileContents(file_contents);
    }

    public void addFloat(String name, float value){
        ArrayList<String> json_contents = new ArrayList<>();
        json_contents.add(name);
        json_contents.add(String.valueOf(value));
        String json_string = RCTjson.convertArrayListToString(json_contents);

        ArrayList<String> file_contents = getFileContents();
        int name_position = getPosition(file_contents, name);
        if(name_position >= 0){
            file_contents.set(name_position, json_string);
        }else{
            file_contents.add(json_string);
        }
        saveFileContents(file_contents);
    }

    public void addShort(String name, short value){
        ArrayList<String> json_contents = new ArrayList<>();
        json_contents.add(name);
        json_contents.add(String.valueOf(value));
        String json_string = RCTjson.convertArrayListToString(json_contents);

        ArrayList<String> file_contents = getFileContents();
        int name_position = getPosition(file_contents, name);
        if(name_position >= 0){
            file_contents.set(name_position, json_string);
        }else{
            file_contents.add(json_string);
        }
        saveFileContents(file_contents);
    }

    public void addLong(String name, long value){
        ArrayList<String> json_contents = new ArrayList<>();
        json_contents.add(name);
        json_contents.add(String.valueOf(value));
        String json_string = RCTjson.convertArrayListToString(json_contents);

        ArrayList<String> file_contents = getFileContents();
        int name_position = getPosition(file_contents, name);
        if(name_position >= 0){
            file_contents.set(name_position, json_string);
        }else{
            file_contents.add(json_string);
        }
        saveFileContents(file_contents);
    }

    public void addByte(String name, byte value){
        ArrayList<String> json_contents = new ArrayList<>();
        json_contents.add(name);
        json_contents.add(String.valueOf(value));
        String json_string = RCTjson.convertArrayListToString(json_contents);

        ArrayList<String> file_contents = getFileContents();
        int name_position = getPosition(file_contents, name);
        if(name_position >= 0){
            file_contents.set(name_position, json_string);
        }else{
            file_contents.add(json_string);
        }
        saveFileContents(file_contents);
    }

    public void addCharacter(String name, char value){
        ArrayList<String> json_contents = new ArrayList<>();
        json_contents.add(name);
        json_contents.add(String.valueOf(value));
        String json_string = RCTjson.convertArrayListToString(json_contents);

        ArrayList<String> file_contents = getFileContents();
        int name_position = getPosition(file_contents, name);
        if(name_position >= 0){
            file_contents.set(name_position, json_string);
        }else{
            file_contents.add(json_string);
        }
        saveFileContents(file_contents);
    }

    public void addBoolean(String name, boolean value){
        ArrayList<String> json_contents = new ArrayList<>();
        json_contents.add(name);
        json_contents.add(String.valueOf(value));
        String json_string = RCTjson.convertArrayListToString(json_contents);

        ArrayList<String> file_contents = getFileContents();
        int name_position = getPosition(file_contents, name);
        if(name_position >= 0){
            file_contents.set(name_position, json_string);
        }else{
            file_contents.add(json_string);
        }
        saveFileContents(file_contents);
    }

    public String getString(String name){
        ArrayList<String> file_contents = getFileContents();
        int name_position = getPosition(file_contents, name);
        if(name_position >= 0){
            ArrayList<String> json_contents = RCTjson.convertStringToArrayList(file_contents.get(name_position));
            return json_contents.get(1);
        }else{
            return null;
        }
    }

    public int getInteger(String name, int default_value){
        ArrayList<String> file_contents = getFileContents();
        int name_position = getPosition(file_contents, name);
        if(name_position >= 0){
            ArrayList<String> json_contents = RCTjson.convertStringToArrayList(file_contents.get(name_position));
            return Integer.parseInt(json_contents.get(1));
        }else{
            return default_value;
        }
    }

    public double getDouble(String name, double default_value){
        ArrayList<String> file_contents = getFileContents();
        int name_position = getPosition(file_contents, name);
        if(name_position >= 0){
            ArrayList<String> json_contents = RCTjson.convertStringToArrayList(file_contents.get(name_position));
            return Double.parseDouble(json_contents.get(1));
        }else{
            return default_value;
        }
    }

    public double getFloat(String name, float default_value){
        ArrayList<String> file_contents = getFileContents();
        int name_position = getPosition(file_contents, name);
        if(name_position >= 0){
            ArrayList<String> json_contents = RCTjson.convertStringToArrayList(file_contents.get(name_position));
            return Float.parseFloat(json_contents.get(1));
        }else{
            return default_value;
        }
    }

    public long getLong(String name, long default_value){
        ArrayList<String> file_contents = getFileContents();
        int name_position = getPosition(file_contents, name);
        if(name_position >= 0){
            ArrayList<String> json_contents = RCTjson.convertStringToArrayList(file_contents.get(name_position));
            return Long.parseLong(json_contents.get(1));
        }else{
            return default_value;
        }
    }

    public short getShort(String name, short default_value){
        ArrayList<String> file_contents = getFileContents();
        int name_position = getPosition(file_contents, name);
        if(name_position >= 0){
            ArrayList<String> json_contents = RCTjson.convertStringToArrayList(file_contents.get(name_position));
            return Short.parseShort(json_contents.get(1));
        }else{
            return default_value;
        }
    }

    public short getByte(String name, byte default_value){
        ArrayList<String> file_contents = getFileContents();
        int name_position = getPosition(file_contents, name);
        if(name_position >= 0){
            ArrayList<String> json_contents = RCTjson.convertStringToArrayList(file_contents.get(name_position));
            return Byte.parseByte(json_contents.get(1));
        }else{
            return default_value;
        }
    }

    public boolean getBoolean(String name, boolean default_value){
        ArrayList<String> file_contents = getFileContents();
        int name_position = getPosition(file_contents, name);
        if(name_position >= 0){
            ArrayList<String> json_contents = RCTjson.convertStringToArrayList(file_contents.get(name_position));
            return Boolean.parseBoolean(json_contents.get(1));
        }else{
            return default_value;
        }
    }

    public char getCharacter(String name, char default_value){
        ArrayList<String> file_contents = getFileContents();
        int name_position = getPosition(file_contents, name);
        if(name_position >= 0){
            ArrayList<String> json_contents = RCTjson.convertStringToArrayList(file_contents.get(name_position));
            return json_contents.get(1).toCharArray()[0];
        }else{
            return default_value;
        }
    }



    public void delete(String name){
        ArrayList<String> file_contents = new ArrayList<>();
        int name_position = getPosition(file_contents, name);
        if(name_position >= 0){
            file_contents.remove(name_position);
        }
        saveFileContents(file_contents);
    }




    private void saveFileContents(ArrayList<String> file_contents){
        try {
            RCTfile.overrideFile(this.SAVE_FILE_PATH, file_contents);
        } catch (IOException ignored) {
        }
    }


    public ArrayList<String> getFileContents(){
        try {
            return RCTfile.readFile_ArrayList(this.SAVE_FILE_PATH);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }


    public int getPosition(String name){
        int position = -1;
        try {
            ArrayList<String> file_contents = RCTfile.readFile_ArrayList(this.SAVE_FILE_PATH);
            for (int index = 0; index <file_contents.size(); index++) {
                ArrayList<String> json_contents = RCTjson.convertStringToArrayList(file_contents.get(index));
                String current_name = json_contents.get(0);
                if(current_name.equals(name)){
                    position = index;
                    break;
                }
            }
        }catch (IOException ignored){}
        return position;
    }

    public int getPosition(ArrayList<String> file_contents, String name){
        int position = -1;
        for (int index = 0; index <file_contents.size(); index++) {
            ArrayList<String> json_contents = RCTjson.convertStringToArrayList(file_contents.get(index));
            String current_name = json_contents.get(0);
            if(current_name.equals(name)){
                position = index;
                break;
            }
        }
        return position;
    }

    public boolean doDataExist(String name){
        return (getPosition(name) >= 0);
    }



}
