package com.racartech.library.rctandroid.json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class RCTjson {



    public static HashMap<String, String> convertToHashmap(JSONObject json_object) {
        HashMap<String, String> map = new HashMap<>();
        Iterator<String> keys = json_object.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            try {
                map.put(key, json_object.getString(key));
            } catch (JSONException ignored){}
        }
        return map;
    }

    public static JSONObject convertToJson(HashMap<String, String> map) {
        return new JSONObject(map);
    }




    public static String arrayListStringToJSONString(ArrayList<String> array_list) {
        JSONArray json_array = new JSONArray();
        for (int index = 0; index < array_list.size(); index++) {
            String item = array_list.get(index);
            json_array.put(item);
        }
        return json_array.toString();
    }

    public static ArrayList<String> jsonStringToArrayListString(String json_string) {
        ArrayList<String> array_list = new ArrayList<>();
        try {
            JSONArray json_array = new JSONArray(json_string);
            for (int index = 0; index < json_array.length(); index++) {
                array_list.add(json_array.getString(index));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return array_list;
    }

    public static String arrayListDoubleToJSONString(ArrayList<Double> array_list) {
        JSONArray json_array = new JSONArray();
        for (int index = 0; index < array_list.size(); index++) {
            Double item = array_list.get(index);
            json_array.put(item);
        }
        return json_array.toString();
    }


    public static ArrayList<Double> jsonStringToArrayListDouble(String json_string) {
        ArrayList<Double> array_list = new ArrayList<>();
        try {
            JSONArray json_array = new JSONArray(json_string);
            for (int index = 0; index < json_array.length(); index++) {
                array_list.add(json_array.getDouble(index));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return array_list;
    }

    public static String arrayListLongToJSONString(ArrayList<Long> array_list) {
        JSONArray json_array = new JSONArray();
        for (int index = 0; index < array_list.size(); index++) {
            Long item = array_list.get(index);
            json_array.put(item);
        }
        return json_array.toString();
    }

    public static ArrayList<Long> jsonStringToArrayListLong(String json_string) {
        ArrayList<Long> array_list = new ArrayList<>();
        try {
            JSONArray json_array = new JSONArray(json_string);
            for (int index = 0; index < json_array.length(); index++) {
                array_list.add(json_array.getLong(index));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return array_list;
    }

    public static String arrayListIntegerToJSONString(ArrayList<Integer> array_list) {
        JSONArray json_array = new JSONArray();
        for (int index = 0; index < array_list.size(); index++) {
            Integer item = array_list.get(index);
            json_array.put(item);
        }
        return json_array.toString();
    }


    public static ArrayList<Integer> jsonStringToArrayListInteger(String json_string) {
        ArrayList<Integer> array_list = new ArrayList<>();
        try {
            JSONArray json_array = new JSONArray(json_string);
            for (int index = 0; index < json_array.length(); index++) {
                array_list.add(json_array.getInt(index));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return array_list;
    }

    public static String arrayListBooleanToJSONString(ArrayList<Boolean> array_list) {
        JSONArray json_array = new JSONArray();
        for (int index = 0; index < array_list.size(); index++) {
            Boolean item = array_list.get(index);
            json_array.put(item);
        }
        return json_array.toString();
    }

    public static ArrayList<Boolean> jsonStringToArrayListBoolean(String json_string) {
        ArrayList<Boolean> array_list = new ArrayList<>();
        try {
            JSONArray json_array = new JSONArray(json_string);
            for (int index = 0; index < json_array.length(); index++) {
                array_list.add(json_array.getBoolean(index));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return array_list;
    }




    ///////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////


    public static ArrayList<String> jsonArray_To_ArrayListString(JSONArray json_array) throws JSONException{
        ArrayList<String> the_arraylist = new ArrayList<>();
        for (int i = 0; i < json_array.length(); i++) {
            the_arraylist.add(json_array.getString(i));
        }
        return the_arraylist;
    }

    public static ArrayList<Integer> jsonArray_To_ArrayListInteger(JSONArray json_array) throws JSONException{
        ArrayList<Integer> the_arraylist = new ArrayList<>();
        for (int i = 0; i < json_array.length(); i++) {
            the_arraylist.add(json_array.getInt(i));
        }
        return the_arraylist;
    }

    public static ArrayList<Double> jsonArray_To_ArrayListDouble(JSONArray json_array) throws JSONException{
        ArrayList<Double> the_arraylist = new ArrayList<>();
        for (int i = 0; i < json_array.length(); i++) {
            the_arraylist.add(json_array.getDouble(i));
        }
        return the_arraylist;
    }

    public static ArrayList<Long> jsonArray_To_ArrayListLong(JSONArray json_array) throws JSONException{
        ArrayList<Long> the_arraylist = new ArrayList<>();
        for (int i = 0; i < json_array.length(); i++) {
            the_arraylist.add(json_array.getLong(i));
        }
        return the_arraylist;
    }

    public static ArrayList<Boolean> jsonArray_To_ArrayListBoolean(JSONArray json_array) throws JSONException{
        ArrayList<Boolean> the_arraylist = new ArrayList<>();
        for (int i = 0; i < json_array.length(); i++) {
            the_arraylist.add(json_array.getBoolean(i));
        }
        return the_arraylist;
    }


    public static JSONArray arrayListStringToJSONArray(ArrayList<String> array_list){
        JSONArray json_array = new JSONArray();
        for (int index = 0; index < array_list.size(); index++) {
            String mate = array_list.get(index);
            json_array.put(mate);
        }
        return json_array;
    }

    public static JSONArray arrayListIntegerToJSONArray(ArrayList<Integer> array_list){
        JSONArray json_array = new JSONArray();
        for (int index = 0; index < array_list.size(); index++) {
            int mate = array_list.get(index);
            json_array.put(mate);
        }
        return json_array;
    }

    public static JSONArray arrayListLongToJSONArray(ArrayList<Long> array_list){
        JSONArray json_array = new JSONArray();
        for (int index = 0; index < array_list.size(); index++) {
            Long mate = array_list.get(index);
            json_array.put(mate);
        }
        return json_array;
    }

    public static JSONArray arrayListDoubleToJSONArray(ArrayList<Double> array_list){
        JSONArray json_array = new JSONArray();
        for (int index = 0; index < array_list.size(); index++) {
            Double mate = array_list.get(index);
            json_array.put(mate);
        }
        return json_array;
    }

    public static JSONArray arrayListBooleanToJSONArray(ArrayList<Boolean> array_list){
        JSONArray json_array = new JSONArray();
        for (int index = 0; index < array_list.size(); index++) {
            boolean mate = array_list.get(index);
            json_array.put(mate);
        }
        return json_array;
    }


    /*


    public static String convertToJSONString(
            long chat_id,
            String username,
            ArrayList<String> chat_mates,
            ArrayList<Long> chat_mates_id){

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("chat_id", chat_id);
            jsonObject.put("username", username);

            JSONArray matesArray = arrayListStringToJSONArray(chat_mates);
            jsonObject.put("chat_mates", matesArray);

            JSONArray matesIdArray = arrayListLongToJSONArray(chat_mates_id);
            jsonObject.put("chat_mates_id", matesIdArray);

        }catch(JSONException ignored) {}

        return jsonObject.toString();
    }

    public static void jsonStringToData(String json_string){

        try {
            JSONObject jsonObject = new JSONObject(json_string);
            long chat_id = jsonObject.getLong("chat_id");
            String username = jsonObject.getString("username");

            JSONArray matesArray = jsonObject.getJSONArray("chat_mates");
            JSONArray matesIdArray = jsonObject.getJSONArray("chat_mates_id");

            ArrayList<String> chat_mates = jsonArray_To_ArrayListString(matesArray);
            ArrayList<Long> chat_mates_id = jsonArray_To_ArrayListLong(matesIdArray);


        }catch(JSONException ignored){}
    }

     */


}
