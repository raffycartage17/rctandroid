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



    //This is a test comment, dont mind this
    public static String TEST_STRING = "Hello Dont Mind this Variable";

    public static String convertArrayListToString_Integer(ArrayList<Integer> arrayList) {
        // Create a Gson object
        Gson gson = new Gson();

        // Convert the ArrayList to JSON
        String json = gson.toJson(arrayList);

        // Return the JSON string
        return json;
    }

    public static String convertArrayListToString_Double(ArrayList<Double> arrayList) {
        // Create a Gson object
        Gson gson = new Gson();

        // Convert the ArrayList to JSON
        String json = gson.toJson(arrayList);

        // Return the JSON string
        return json;
    }

    public static String convertArrayListToString_Long(ArrayList<Long> arrayList) {
        // Create a Gson object
        Gson gson = new Gson();

        // Convert the ArrayList to JSON
        String json = gson.toJson(arrayList);

        // Return the JSON string
        return json;
    }

    public static String convertArrayListToString_Boolean(ArrayList<Boolean> arrayList) {
        // Create a Gson object
        Gson gson = new Gson();

        // Convert the ArrayList to JSON
        String json = gson.toJson(arrayList);

        // Return the JSON string
        return json;
    }

    public static String convertArrayListToString_Float(ArrayList<Float> arrayList) {
        // Create a Gson object
        Gson gson = new Gson();

        // Convert the ArrayList to JSON
        String json = gson.toJson(arrayList);

        // Return the JSON string
        return json;
    }
    public static String convertArrayListToString_Byte(ArrayList<Byte> arrayList) {
        // Create a Gson object
        Gson gson = new Gson();

        // Convert the ArrayList to JSON
        String json = gson.toJson(arrayList);

        // Return the JSON string
        return json;
    }

    public static String convertArrayListToString_Char(ArrayList<Character> arrayList) {
        // Create a Gson object
        Gson gson = new Gson();

        // Convert the ArrayList to JSON
        String json = gson.toJson(arrayList);

        // Return the JSON string
        return json;
    }

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

    public static ArrayList<Integer> convertStringToArrayListInteger(String jsonString) {
        // Create a Gson object
        Gson gson = new Gson();

        // Convert the JSON string to ArrayList
        Type type = new TypeToken<ArrayList<Integer>>(){}.getType();
        ArrayList<Integer> arrayList = gson.fromJson(jsonString, type);

        // Return the ArrayList
        return arrayList;
    }

    public static ArrayList<Double> convertStringToArrayListDouble(String jsonString) {
        // Create a Gson object
        Gson gson = new Gson();

        // Convert the JSON string to ArrayList
        Type type = new TypeToken<ArrayList<Double>>(){}.getType();
        ArrayList<Double> arrayList = gson.fromJson(jsonString, type);

        // Return the ArrayList
        return arrayList;
    }

    public static ArrayList<Float> convertStringToArrayListFloat(String jsonString) {
        // Create a Gson object
        Gson gson = new Gson();

        // Convert the JSON string to ArrayList
        Type type = new TypeToken<ArrayList<Float>>(){}.getType();
        ArrayList<Float> arrayList = gson.fromJson(jsonString, type);

        // Return the ArrayList
        return arrayList;
    }

    public static ArrayList<Long> convertStringToArrayListLong(String jsonString) {
        // Create a Gson object
        Gson gson = new Gson();

        // Convert the JSON string to ArrayList
        Type type = new TypeToken<ArrayList<Long>>(){}.getType();
        ArrayList<Long> arrayList = gson.fromJson(jsonString, type);

        // Return the ArrayList
        return arrayList;
    }

    public static ArrayList<Byte> convertStringToArrayListByte(String jsonString) {
        // Create a Gson object
        Gson gson = new Gson();

        // Convert the JSON string to ArrayList
        Type type = new TypeToken<ArrayList<Byte>>(){}.getType();
        ArrayList<Byte> arrayList = gson.fromJson(jsonString, type);

        // Return the ArrayList
        return arrayList;
    }

    public static ArrayList<Boolean> convertStringToArrayListBoolean(String jsonString) {
        // Create a Gson object
        Gson gson = new Gson();

        // Convert the JSON string to ArrayList
        Type type = new TypeToken<ArrayList<Boolean>>(){}.getType();
        ArrayList<Boolean> arrayList = gson.fromJson(jsonString, type);

        // Return the ArrayList
        return arrayList;
    }

    public static ArrayList<Character> convertStringToArrayListCharacter(String jsonString) {
        // Create a Gson object
        Gson gson = new Gson();

        // Convert the JSON string to ArrayList
        Type type = new TypeToken<ArrayList<Character>>(){}.getType();
        ArrayList<Character> arrayList = gson.fromJson(jsonString, type);

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
