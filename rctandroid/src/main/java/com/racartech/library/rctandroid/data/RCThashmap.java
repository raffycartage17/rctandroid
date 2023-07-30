package com.racartech.library.rctandroid.data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class RCThashmap{
    public static void serialize_STR_STR(HashMap<String, String> hash_map, String file_path){
        try {
            FileOutputStream myFileOutStream = new FileOutputStream(file_path);
            ObjectOutputStream myObjectOutStream = new ObjectOutputStream(myFileOutStream);
            myObjectOutStream.writeObject(hash_map);
            myObjectOutStream.close();
            myFileOutStream.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public static void serialize_STR_INT(HashMap<String, Integer> hash_map, String file_path){
        try {
            FileOutputStream myFileOutStream = new FileOutputStream(file_path);
            ObjectOutputStream myObjectOutStream = new ObjectOutputStream(myFileOutStream);
            myObjectOutStream.writeObject(hash_map);
            myObjectOutStream.close();
            myFileOutStream.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public static void serialize_INT_STR(HashMap<Integer, String> hash_map, String file_path){
        try {
            FileOutputStream myFileOutStream = new FileOutputStream(file_path);
            ObjectOutputStream myObjectOutStream = new ObjectOutputStream(myFileOutStream);
            myObjectOutStream.writeObject(hash_map);
            myObjectOutStream.close();
            myFileOutStream.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }



    public static HashMap<String, String> deserialize_STR_STR(String file_path){
        HashMap<String, String> newHashMap = null;

        try {
            FileInputStream fileInput = new FileInputStream(file_path);
            ObjectInputStream objectInput = new ObjectInputStream(fileInput);
            newHashMap = (HashMap)objectInput.readObject();
            objectInput.close();
            fileInput.close();
        }catch (IOException | ClassNotFoundException obj1){
            obj1.printStackTrace();
        }
        return  newHashMap;
    }

    public static HashMap<String, Integer> deserialize_STR_INT(String file_path){
        HashMap<String, Integer> newHashMap = null;

        try {
            FileInputStream fileInput = new FileInputStream(file_path);
            ObjectInputStream objectInput = new ObjectInputStream(fileInput);
            newHashMap = (HashMap)objectInput.readObject();
            objectInput.close();
            fileInput.close();
        }catch (IOException | ClassNotFoundException obj1){
            obj1.printStackTrace();
        }
        return  newHashMap;
    }

    public static HashMap<Integer, String> deserialize_INT_STR(String file_path){
        HashMap<Integer, String> newHashMap = null;

        try {
            FileInputStream fileInput = new FileInputStream(file_path);
            ObjectInputStream objectInput = new ObjectInputStream(fileInput);
            newHashMap = (HashMap)objectInput.readObject();
            objectInput.close();
            fileInput.close();
        }catch (IOException | ClassNotFoundException obj1){
            obj1.printStackTrace();
        }
        return  newHashMap;
    }
}
