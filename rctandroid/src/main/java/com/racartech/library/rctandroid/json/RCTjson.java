package com.racartech.library.rctandroid.json;

import com.google.gson.Gson;

import org.json.JSONArray;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import com.google.gson.reflect.TypeToken;
import com.racartech.library.rctandroid.file.RCTfile;

import java.lang.reflect.Type;

public class RCTjson {

    public static String convertArrayListToString(ArrayList<String> arrayList) {
        // Create a Gson object
        Gson gson = new Gson();

        // Convert the ArrayList to JSON
        String json = gson.toJson(arrayList);

        // Return the JSON string
        return json;
    }

    public static ArrayList<String> convertStringToArrayList(String jsonString) {
        // Create a Gson object
        Gson gson = new Gson();

        // Convert the JSON string to ArrayList
        Type type = new TypeToken<ArrayList<String>>(){}.getType();
        ArrayList<String> arrayList = gson.fromJson(jsonString, type);

        // Return the ArrayList
        return arrayList;
    }


    public static JSONArray arrayListToJsonArray_String(ArrayList<String> arrayList) {
        JSONArray jsonArray = new JSONArray();
        for (int index = 0; index < arrayList.size(); index++) {
            String item = arrayList.get(index);
            jsonArray.put(item);
        }
        return jsonArray;
    }

    public static JSONArray arrayListToJsonArray_Int(ArrayList<Integer> array_list) {
        JSONArray jsonArray = new JSONArray();
        for (int index = 0; index < array_list.size(); index++) {
            String item = String.valueOf(array_list.get(index));
            jsonArray.put(item);
        }
        return jsonArray;
    }
    public static JSONArray arrayListToJsonArray_Double(ArrayList<Double> array_list) {
        JSONArray jsonArray = new JSONArray();
        for (int index = 0; index < array_list.size(); index++) {
            String item = String.valueOf(array_list.get(index));
            jsonArray.put(item);
        }
        return jsonArray;
    }
    public static JSONArray arrayListToJsonArray_Float(ArrayList<Float> array_list) {
        JSONArray jsonArray = new JSONArray();
        for (int index = 0; index < array_list.size(); index++) {
            String item = String.valueOf(array_list.get(index));
            jsonArray.put(item);
        }
        return jsonArray;
    }

    public static JSONArray arrayListToJsonArray_Long(ArrayList<Long> array_list) {
        JSONArray jsonArray = new JSONArray();
        for (int index = 0; index < array_list.size(); index++) {
            String item = String.valueOf(array_list.get(index));
            jsonArray.put(item);
        }
        return jsonArray;
    }


    public static boolean saveToFile_0(String file_path, ArrayList<ArrayList<String>> list) {
        if(!RCTfile.doesFileExist(file_path)){
            RCTfile.createFile(file_path);
        }
        // Create a Gson object
        Gson gson = new Gson();

        // Convert the list to JSON
        String json = gson.toJson(list);

        // Save the JSON string to a file
        try {
            FileWriter writer = new FileWriter(file_path);
            writer.write(json);
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean saveToFile_1(String file_path, ArrayList<String> list) {
        if(!RCTfile.doesFileExist(file_path)){
            RCTfile.createFile(file_path);
        }
        // Create a Gson object
        Gson gson = new Gson();

        // Convert the list to JSON
        String json = gson.toJson(list);

        // Save the JSON string to a file
        try {
            FileWriter writer = new FileWriter(file_path);
            writer.write(json);
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }



}
