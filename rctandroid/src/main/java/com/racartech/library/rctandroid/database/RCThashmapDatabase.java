package com.racartech.library.rctandroid.database;

import android.os.Build;

import com.racartech.library.rctandroid.array.RCTarray;
import com.racartech.library.rctandroid.data.RCThashmap;
import com.racartech.library.rctandroid.file.RCTfile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class RCThashmapDatabase extends RCThashmap {
    private String file_path;
    private HashMap<String,String> the_data;

    public RCThashmapDatabase(String file_path){
        this.the_data = deserialize_STR_STR(file_path);
        this.file_path = file_path;
    }


    public void save(){
        serialize_STR_STR(this.the_data,this.file_path);
    }

    public HashMap<String,String> getMap(){
        return the_data;
    }

    public void add(String key, String value){
        this.the_data.put(key,value);
    }
    public void addAll(String[] key, String[] value){
        int target_length = 0;
        if(key.length > value.length){
            target_length = value.length;
        }else{
            target_length = key.length;
        }
        for(int index = 0; index<target_length; index++){
            this.the_data.put(key[index],value[index]);
        }
    }
    public void delete(String key){
        the_data.remove(key);
    }
    public void delete(String key, String value) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            Iterator<Map.Entry<String, String>> iterator = the_data.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                if (entry.getKey().equals(key) && entry.getValue().equals(value)) {
                    iterator.remove();
                }
            }
        } else {
            the_data.remove(key, value);
        }
    }

    public void deleteAll(String[] keys){
        for(int index = 0; index< keys.length; index++){
            the_data.remove(keys[index]);
        }
    }
    public void deleteAll(String[] keys,String[] values){
        int max_length = -1;
        if(keys.length< values.length){
            max_length = keys.length;
        }else{
            max_length = values.length;
        }
        for(int index = 0; index< max_length; index++){
            the_data.remove(keys[index]);
        }
    }
    public void replaceValue(String key, String value) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            Iterator<Map.Entry<String, String>> iterator = the_data.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                if (entry.getKey().equals(key)) {
                    entry.setValue(value);
                    break;
                }
            }
        } else {
            the_data.replace(key, value);
        }
    }

    public void replaceValue(String key, String old_value, String new_value) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            Iterator<Map.Entry<String, String>> iterator = the_data.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                if (entry.getKey().equals(key) && entry.getValue().equals(old_value)) {
                    entry.setValue(new_value);
                    break;
                }
            }
        } else {
            the_data.replace(key, old_value, new_value);
        }
    }

    public boolean renameKey(String key,String new_name){
        if(the_data.containsKey(key)){
            String temp_value = the_data.get(key);
            the_data.remove(key);
            the_data.put(new_name,temp_value);
            return true;
        }else{
            return false;
        }
    }
    public void renameKey(String[] keys,String[] new_names){
        int max_length = -1;
        if(keys.length< new_names.length){
            max_length = keys.length;
        }else{
            max_length = new_names.length;
        }
        for(int index = 0; index<max_length; index++){
            if(the_data.containsKey(keys[index])){
                String temp_value = the_data.get(keys[index]);
                the_data.remove(keys[index]);
                the_data.put(new_names[index],temp_value);
            }
        }
    }

    public String getValue(String key){
        return the_data.get(key);
    }
    public String[] getValue(String[] keys){
        String[] values = new String[keys.length];
        for(int index = 0; index< keys.length; index++){
            values[index] = the_data.get(keys[index]);
        }
        return values;
    }




}
