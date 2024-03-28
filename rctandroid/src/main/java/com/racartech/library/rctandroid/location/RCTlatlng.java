package com.racartech.library.rctandroid.location;

import android.content.Context;
import android.location.Address;

import org.json.JSONException;
import org.json.JSONObject;

public class RCTlatlng {


    public long LOG_MS;
    public double LATITUDE;
    public double LONGITUDE;


    public RCTlatlng(String json_string){
        try {
            toLatLng(json_string);
        }catch(Exception ignored){
            this.LOG_MS = -1000;
            this.LATITUDE = -1000;
            this.LONGITUDE = -1000;
        }
    }

    public RCTlatlng(long log_millis,double latitude, double longitude){
        this.LOG_MS = log_millis;
        this.LATITUDE = latitude;
        this.LONGITUDE = longitude;
    }


    public String toJSONString(){
        try {
            return getJSONString(this.LOG_MS,this.LATITUDE, this.LONGITUDE);
        }catch(Exception ignored){
            return null;
        }
    }

    public Address getAddress(Context context){
        return RCTlocation.getAddress(context,this.LATITUDE, this.LONGITUDE);
    }




    private String getJSONString(long log_ms, double latitude, double longitude) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("log_ms",log_ms);
        jsonObject.put("latitude", latitude);
        jsonObject.put("longitude", longitude);
        return jsonObject.toString();
    }

    private void toLatLng(String json_string) throws JSONException {
        JSONObject jsonObject = new JSONObject(json_string);
        this.LOG_MS = jsonObject.getLong("log_ms");
        this.LATITUDE = jsonObject.getDouble("latitude");
        this.LONGITUDE = jsonObject.getDouble("longitude");
    }





}
