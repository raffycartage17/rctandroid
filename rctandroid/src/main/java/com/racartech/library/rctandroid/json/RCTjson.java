package com.racartech.library.rctandroid.json;

import org.json.JSONArray;

import java.util.ArrayList;

public class RCTjson {


    public static JSONArray arrayListToJsonArray(ArrayList<String> arrayList) {
        JSONArray jsonArray = new JSONArray();
        for (String item : arrayList) {
            jsonArray.put(item);
        }
        return jsonArray;
    }


}
