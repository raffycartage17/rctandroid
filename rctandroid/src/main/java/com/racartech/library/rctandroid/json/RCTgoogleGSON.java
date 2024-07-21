package com.racartech.library.rctandroid.json;

import com.google.gson.Gson;

import org.json.JSONArray;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.racartech.library.rctandroid.file.RCTfile;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class RCTgoogleGSON {



    /**
     * Converts a JSON string to a Map.
     *
     * @param jsonString the JSON string to convert
     * @return a Map containing the data from the JSON string
     */
    public static Map<String, Object> jsonStringToMap(String jsonString) {
        Type type = new TypeToken<Map<String, Object>>() {}.getType();
        return new GsonBuilder().serializeNulls().create().fromJson(jsonString, type);
    }

    /**
     * Converts a JSON string to a Map.
     *
     * @param jsonString the JSON string to convert
     * @return a Map containing the data from the JSON string
     */
    public static HashMap<String, Object> jsonStringToHashMap(String jsonString) {
        Type type = new TypeToken<Map<String, Object>>() {}.getType();
        Map<String, Object> tempMap = new GsonBuilder().serializeNulls().create().fromJson(jsonString, type);
        return new HashMap<>(tempMap);
    }

    /**
     * Converts a Map to a JSON string.
     *
     * @param map the Map to convert
     * @return a JSON string representing the data in the Map
     */
    public static String mapToJsonString(Map<String, Object> map) {
        return new Gson().toJson(map);
    }

    /**
     * Converts a Map to a JSON string.
     *
     * @param map the Map to convert
     * @return a JSON string representing the data in the Map
     */
    public static String mapToJsonString(HashMap<String, Object> map) {
        return new Gson().toJson(map);
    }



    public static ArrayList<String> getKeySet_JsonObject(String json_object_string){
        JsonElement jsonElement = new Gson().fromJson(json_object_string, JsonElement.class);
        JsonObject response_object = jsonElement.getAsJsonObject();
        return new ArrayList<>(response_object.keySet());
    }
    public static JsonObject toJsonObject(String json_object_string){
        JsonElement jsonElement = new Gson().fromJson(json_object_string, JsonElement.class);
        return jsonElement.getAsJsonObject();
    }

    public static JsonElement toJsonElement(String json_object_string){
        return new Gson().fromJson(json_object_string, JsonElement.class);
    }


    public static JsonObject stringToJsonObject(String json_object_string){
        return new Gson().fromJson(json_object_string, JsonElement.class).getAsJsonObject();
    }

    public static JsonObject[] jsonArrayToJsonObjectArray(String json_array_string){
        JsonArray json_array = JsonParser.parseString(json_array_string).getAsJsonArray();
        JsonObject[] json_object_array = new JsonObject[json_array.size()];
        for (int i = 0; i < json_array.size(); i++) {
            json_object_array[i] = json_array.get(i).getAsJsonObject();
        }
        return json_object_array;
    }

    public static JsonArray stringToJsonArray(String json_array_string){
        return JsonParser.parseString(json_array_string).getAsJsonArray();
    }


    public static String jsonObjectToString(JsonObject jsonObject) {
        return new Gson().toJson(jsonObject);
    }

    public static String jsonArrayToString(JsonArray jsonArray) {
        return jsonArray.toString();
    }



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

    public static String convertArrayListToString_Short(ArrayList<Short> arrayList) {
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
        Gson gson = new Gson();
        String json = gson.toJson(arrayList);
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

    public static ArrayList<Short> convertStringToArrayListShort(String jsonString) {
        // Create a Gson object
        Gson gson = new Gson();

        // Convert the JSON string to ArrayList
        Type type = new TypeToken<ArrayList<Short>>(){}.getType();
        ArrayList<Short> arrayList = gson.fromJson(jsonString, type);

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



    public static ArrayList<ArrayList<String>> convertString_ArrayList2DString(String json_string){
        ArrayList<String> main_body = convertStringToArrayList(json_string);

        ArrayList<ArrayList<String>> all_list = new ArrayList<>();
        for(int index = 0; index<main_body.size(); index++){
            all_list.add(convertStringToArrayList(main_body.get(index)));
        }
        return all_list;
    }

    public static ArrayList<ArrayList<Integer>> convertString_ArrayList2DInteger(String json_string){
        ArrayList<String> main_body = convertStringToArrayList(json_string);

        ArrayList<ArrayList<Integer>> all_list = new ArrayList<>();
        for(int index = 0; index<main_body.size(); index++){
            all_list.add(convertStringToArrayListInteger(main_body.get(index)));
        }
        return all_list;
    }

    public static ArrayList<ArrayList<Double>> convertString_ArrayList2DDouble(String json_string){
        ArrayList<String> main_body = convertStringToArrayList(json_string);

        ArrayList<ArrayList<Double>> all_list = new ArrayList<>();
        for(int index = 0; index<main_body.size(); index++){
            all_list.add(convertStringToArrayListDouble(main_body.get(index)));
        }
        return all_list;
    }

    public static ArrayList<ArrayList<Float>> convertString_ArrayList2DFloat(String json_string) {
        ArrayList<String> main_body = convertStringToArrayList(json_string);

        ArrayList<ArrayList<Float>> all_list = new ArrayList<>();
        for (int index = 0; index < main_body.size(); index++) {
            all_list.add(convertStringToArrayListFloat(main_body.get(index)));
        }
        return all_list;
    }

    public static ArrayList<ArrayList<Long>> convertString_ArrayList2DLong(String json_string) {
        ArrayList<String> main_body = convertStringToArrayList(json_string);

        ArrayList<ArrayList<Long>> all_list = new ArrayList<>();
        for (int index = 0; index < main_body.size(); index++) {
            all_list.add(convertStringToArrayListLong(main_body.get(index)));
        }
        return all_list;
    }

    public static ArrayList<ArrayList<Short>> convertString_ArrayList2DShort(String json_string) {
        ArrayList<String> main_body = convertStringToArrayList(json_string);

        ArrayList<ArrayList<Short>> all_list = new ArrayList<>();
        for (int index = 0; index < main_body.size(); index++) {
            all_list.add(convertStringToArrayListShort(main_body.get(index)));
        }
        return all_list;
    }

    public static ArrayList<ArrayList<Byte>> convertString_ArrayList2DByte(String json_string) {
        ArrayList<String> main_body = convertStringToArrayList(json_string);

        ArrayList<ArrayList<Byte>> all_list = new ArrayList<>();
        for (int index = 0; index < main_body.size(); index++) {
            all_list.add(convertStringToArrayListByte(main_body.get(index)));
        }
        return all_list;
    }

    public static ArrayList<ArrayList<Character>> convertString_ArrayList2DCharacter(String json_string) {
        ArrayList<String> main_body = convertStringToArrayList(json_string);

        ArrayList<ArrayList<Character>> all_list = new ArrayList<>();
        for (int index = 0; index < main_body.size(); index++) {
            all_list.add(convertStringToArrayListCharacter(main_body.get(index)));
        }
        return all_list;
    }

    public static ArrayList<ArrayList<Boolean>> convertString_ArrayList2DBoolean(String json_string) {
        ArrayList<String> main_body = convertStringToArrayList(json_string);
        ArrayList<ArrayList<Boolean>> all_list = new ArrayList<>();
        for (int index = 0; index < main_body.size(); index++) {
            all_list.add(convertStringToArrayListBoolean(main_body.get(index)));
        }
        return all_list;
    }


    public static String convertArrayList2DString_ToString(ArrayList<ArrayList<String>> array_list){
        ArrayList<String> main_body = new ArrayList<>();
        for(int index = 0; index<array_list.size(); index++){
            main_body.add(convertArrayListToString(array_list.get(index)));
        }
        return convertArrayListToString(main_body);
    }

    public static String convertArrayList2Integer_ToString(ArrayList<ArrayList<Integer>> array_list){
        ArrayList<String> main_body = new ArrayList<>();
        for(int index = 0; index<array_list.size(); index++){
            main_body.add(convertArrayListToString_Integer(array_list.get(index)));
        }
        return convertArrayListToString(main_body);
    }

    public static String convertArrayList2DDouble_ToString(ArrayList<ArrayList<Double>> array_list){
        ArrayList<String> main_body = new ArrayList<>();
        for(int index = 0; index<array_list.size(); index++){
            main_body.add(convertArrayListToString_Double(array_list.get(index)));
        }
        return convertArrayListToString(main_body);
    }

    public static String convertArrayList2DFloatToString(ArrayList<ArrayList<Float>> array_list) {
        ArrayList<String> main_body = new ArrayList<>();
        for (int index = 0; index < array_list.size(); index++) {
            main_body.add(convertArrayListToString_Float(array_list.get(index)));
        }
        return convertArrayListToString(main_body);
    }

    public static String convertArrayList2DLongToString(ArrayList<ArrayList<Long>> array_list) {
        ArrayList<String> main_body = new ArrayList<>();
        for (int index = 0; index < array_list.size(); index++) {
            main_body.add(convertArrayListToString_Long(array_list.get(index)));
        }
        return convertArrayListToString(main_body);
    }

    public static String convertArrayList2DShortToString(ArrayList<ArrayList<Short>> array_list) {
        ArrayList<String> main_body = new ArrayList<>();
        for (int index = 0; index < array_list.size(); index++) {
            main_body.add(convertArrayListToString_Short(array_list.get(index)));
        }
        return convertArrayListToString(main_body);
    }

    public static String convertArrayList2DByteToString(ArrayList<ArrayList<Byte>> array_list) {
        ArrayList<String> main_body = new ArrayList<>();
        for (int index = 0; index < array_list.size(); index++) {
            main_body.add(convertArrayListToString_Byte(array_list.get(index)));
        }
        return convertArrayListToString(main_body);
    }

    public static String convertArrayList2DCharacterToString(ArrayList<ArrayList<Character>> array_list) {
        ArrayList<String> main_body = new ArrayList<>();
        for (int index = 0; index < array_list.size(); index++) {
            main_body.add(convertArrayListToString_Char(array_list.get(index)));
        }
        return convertArrayListToString(main_body);
    }

    public static String convertArrayList2DBooleanToString(ArrayList<ArrayList<Boolean>> array_list) {
        ArrayList<String> main_body = new ArrayList<>();
        for (int index = 0; index < array_list.size(); index++) {
            main_body.add(convertArrayListToString_Boolean(array_list.get(index)));
        }
        return convertArrayListToString(main_body);
    }



}
