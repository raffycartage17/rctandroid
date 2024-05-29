package com.racartech.library.rctandroid.weather.openweathermap;

import com.racartech.library.rctandroid.json.RCTjson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

public class OWM_3HR40P_Data {


    public long DATE_MS;
    public long DT;
    public double TEMP;
    public double FEELS_LIKE_TEMP;
    public double TEMP_MIN;
    public double TEMP_MAX;
    public int PRESSURE;
    public int SEA_LEVEL;
    public int GROUND_LEVEL;
    public int HUMIDITY;
    public double TEMP_KF;
    public int WEATHER_ID;
    public String WEATHER_MAIN;
    public String WEATHER_DESCRIPTION;
    public String WEATHER_ICON;
    public int CLOUDS_ALL;
    public double WIND_SPEED;
    public int WIND_DEG;
    public double WIND_GUST;
    public int VISIBILITY;
    public double PRECIPITATION_PROBABILITY;
    public double RAIN_3HR_MM;
    public String SYS_POD;
    public String DT_TEXT;

    public double LOCATION_LAT;
    public double LOCATION_LONG;


    public OWM_3HR40P_Data(JSONObject json_object, double location_latitude, double location_longitude) {

        try{
            DT = Long.parseLong(json_object.get("dt").toString());
            DATE_MS = new Date(DT * 1000L).getTime();
        }catch(JSONException ignored){}

        try{
            JSONObject mainObject = (JSONObject) json_object.get("main");
            TEMP = Double.parseDouble(mainObject.get("temp").toString());
            FEELS_LIKE_TEMP = Double.parseDouble(mainObject.get("feels_like").toString());
            TEMP_MIN = Double.parseDouble(mainObject.get("temp_min").toString());
            TEMP_MAX = Double.parseDouble(mainObject.get("temp_max").toString());
            PRESSURE = Integer.parseInt(mainObject.get("pressure").toString());
            SEA_LEVEL = Integer.parseInt(mainObject.get("sea_level").toString());
            GROUND_LEVEL = Integer.parseInt(mainObject.get("grnd_level").toString());
            HUMIDITY = Integer.parseInt(mainObject.get("humidity").toString());
            TEMP_KF = Double.parseDouble(mainObject.get("temp_kf").toString());
        }catch(JSONException ignored){}

        try{
            JSONArray weatherArray = (JSONArray) json_object.get("weather");
            JSONObject weatherObject = (JSONObject) weatherArray.get(0);
            WEATHER_ID = Integer.parseInt(weatherObject.get("id").toString());
            WEATHER_MAIN = weatherObject.get("main").toString();
            WEATHER_DESCRIPTION = weatherObject.get("description").toString();
            WEATHER_ICON = weatherObject.get("icon").toString();
        }catch(JSONException ignored){}


        try{
            JSONObject cloudsObject = (JSONObject) json_object.get("clouds");
            CLOUDS_ALL = Integer.parseInt(cloudsObject.get("all").toString());
        }catch(JSONException ignored){}


        try{
            JSONObject windObject = (JSONObject) json_object.get("wind");
            WIND_SPEED = Double.parseDouble(windObject.get("speed").toString());
            WIND_DEG = Integer.parseInt(windObject.get("deg").toString());
            WIND_GUST = Double.parseDouble(windObject.get("gust").toString());
        }catch(JSONException ignored){}

        try{
            VISIBILITY = Integer.parseInt(json_object.get("visibility").toString());
            PRECIPITATION_PROBABILITY = Double.parseDouble(json_object.get("pop").toString());
        }catch(JSONException ignored){}

        try{
            JSONObject rainObject = (JSONObject) json_object.get("rain");
            RAIN_3HR_MM = Double.parseDouble(rainObject.get("3h").toString());
        }catch(JSONException ignored){}

        try{
            JSONObject sysObject = (JSONObject) json_object.get("sys");
            SYS_POD = (String) sysObject.get("pod");
        }catch(JSONException ignored){}
        try{
            DT_TEXT = (String) json_object.get("dt_txt");
        }catch(JSONException ignored){}

        this.LOCATION_LAT = location_latitude;
        this.LOCATION_LONG = location_longitude;


    }





    public OWM_3HR40P_Data(String json_string){
        ArrayList<String> json_contents = RCTjson.convertStringToArrayList(json_string);

        this.DATE_MS = Long.parseLong(json_contents.get(0));
        this.DT = Long.parseLong(json_contents.get(1));
        this.TEMP = Double.parseDouble(json_contents.get(2));
        this.FEELS_LIKE_TEMP = Double.parseDouble(json_contents.get(3));
        this.TEMP_MIN = Double.parseDouble(json_contents.get(4));
        this.TEMP_MAX = Double.parseDouble(json_contents.get(5));
        this.PRESSURE = Integer.parseInt(json_contents.get(6));
        this.SEA_LEVEL = Integer.parseInt(json_contents.get(7));
        this.GROUND_LEVEL = Integer.parseInt(json_contents.get(8));
        this.HUMIDITY = Integer.parseInt(json_contents.get(9));
        this.TEMP_KF = Double.parseDouble(json_contents.get(10));
        this.WEATHER_ID = Integer.parseInt(json_contents.get(11));
        this.WEATHER_MAIN = json_contents.get(12);
        this.WEATHER_DESCRIPTION = json_contents.get(13);
        this.WEATHER_ICON = json_contents.get(14);
        this.CLOUDS_ALL = Integer.parseInt(json_contents.get(15));
        this.WIND_SPEED = Double.parseDouble(json_contents.get(16));
        this.WIND_DEG = Integer.parseInt(json_contents.get(17));
        this.WIND_GUST = Double.parseDouble(json_contents.get(18));
        this.VISIBILITY = Integer.parseInt(json_contents.get(19));
        this.PRECIPITATION_PROBABILITY = Double.parseDouble(json_contents.get(20));
        this.RAIN_3HR_MM = Double.parseDouble(json_contents.get(21));
        this.SYS_POD = json_contents.get(22);
        this.DT_TEXT = json_contents.get(23);
        this.LOCATION_LAT = Double.parseDouble(json_contents.get(24));
        this.LOCATION_LONG = Double.parseDouble(json_contents.get(25));
    }

    public OWM_3HR40P_Data(
            long date_ms,
            long dt,
            double temp,
            double feels_like,
            double temp_min,
            double temp_max,
            int pressure,
            int sea_level,
            int ground_level,
            int humidity,
            double temp_kf,
            int weather_id,
            String weather_main,
            String weather_description,
            String weather_icon,
            int clouds_all,
            double wind_speed,
            int wind_deg,
            double wind_gust,
            int visibility,
            double precipitation_probability,
            double rain_3hr_mm,
            String sys_pod,
            String dt_text,
            double location_latitude,
            double location_longitude
    ){
        this.DATE_MS = date_ms;
        this.DT = dt;
        this.TEMP = temp;
        this.FEELS_LIKE_TEMP = feels_like;
        this.TEMP_MIN = temp_min;
        this.TEMP_MAX = temp_max;
        this.PRESSURE = pressure;
        this.SEA_LEVEL = sea_level;
        this.GROUND_LEVEL = ground_level;
        this.HUMIDITY = humidity;
        this.TEMP_KF = temp_kf;
        this.WEATHER_ID = weather_id;
        this.WEATHER_MAIN = weather_main;
        this.WEATHER_DESCRIPTION = weather_description;
        this.WEATHER_ICON = weather_icon;
        this.CLOUDS_ALL = clouds_all;
        this.WIND_SPEED = wind_speed;
        this.WIND_DEG = wind_deg;
        this.WIND_GUST = wind_gust;
        this.VISIBILITY = visibility;
        this.PRECIPITATION_PROBABILITY = precipitation_probability;
        this.RAIN_3HR_MM = rain_3hr_mm;
        this.SYS_POD = sys_pod;
        this.DT_TEXT = dt_text;
        this.LOCATION_LAT = location_latitude;
        this.LOCATION_LONG = location_longitude;

    }




}



