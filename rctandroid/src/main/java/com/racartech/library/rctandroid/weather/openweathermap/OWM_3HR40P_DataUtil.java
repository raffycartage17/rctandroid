package com.racartech.library.rctandroid.weather.openweathermap;

import com.racartech.library.rctandroid.json.RCTgoogleGSON;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class OWM_3HR40P_DataUtil {

    public static String toJSONString(OWM_3HR40P_Data weather_data_x){
        ArrayList<String> json_contents = new ArrayList<>();

        json_contents.add(String.valueOf(weather_data_x.DATE_MS)); //0
        json_contents.add(String.valueOf(weather_data_x.DT)); //1
        json_contents.add(String.valueOf(weather_data_x.TEMP)); //2
        json_contents.add(String.valueOf(weather_data_x.FEELS_LIKE_TEMP)); //3
        json_contents.add(String.valueOf(weather_data_x.TEMP_MIN)); //4
        json_contents.add(String.valueOf(weather_data_x.TEMP_MAX)); //5
        json_contents.add(String.valueOf(weather_data_x.PRESSURE)); //6
        json_contents.add(String.valueOf(weather_data_x.SEA_LEVEL)); //7
        json_contents.add(String.valueOf(weather_data_x.GROUND_LEVEL)); //8
        json_contents.add(String.valueOf(weather_data_x.HUMIDITY)); //9
        json_contents.add(String.valueOf(weather_data_x.TEMP_KF)); //10
        json_contents.add(String.valueOf(weather_data_x.WEATHER_ID)); //11
        json_contents.add(weather_data_x.WEATHER_MAIN); //12
        json_contents.add(weather_data_x.WEATHER_DESCRIPTION); //13
        json_contents.add(weather_data_x.WEATHER_ICON); //14
        json_contents.add(String.valueOf(weather_data_x.CLOUDS_ALL)); //15
        json_contents.add(String.valueOf(weather_data_x.WIND_SPEED)); //16
        json_contents.add(String.valueOf(weather_data_x.WIND_DEG)); //17
        json_contents.add(String.valueOf(weather_data_x.WIND_GUST)); //18
        json_contents.add(String.valueOf(weather_data_x.VISIBILITY)); //19
        json_contents.add(String.valueOf(weather_data_x.PRECIPITATION_PROBABILITY)); //20
        json_contents.add(String.valueOf(weather_data_x.RAIN_3HR_MM)); //21
        json_contents.add(weather_data_x.SYS_POD);  //22
        json_contents.add(weather_data_x.DT_TEXT);  //23
        json_contents.add(String.valueOf(weather_data_x.LOCATION_LAT)); //24
        json_contents.add(String.valueOf(weather_data_x.LOCATION_LONG)); //25
        return RCTgoogleGSON.convertArrayListToString(json_contents);
    }


    public static String convertArrayToJSONString(ArrayList<OWM_3HR40P_Data> weather_data){
        ArrayList<String> json_contents = new ArrayList<>();
        for(int index = 0; index<weather_data.size(); index++){
            json_contents.add(toJSONString(weather_data.get(index)));
        }
        return RCTgoogleGSON.convertArrayListToString(json_contents);
    }

    public static ArrayList<OWM_3HR40P_Data> convertJSONStringToArray(String weather_datax_array_json_string){
        ArrayList<String> json_contents = RCTgoogleGSON.convertStringToArrayList(weather_datax_array_json_string);
        ArrayList<OWM_3HR40P_Data> weather_data = new ArrayList<>();
        for(int index = 0; index<json_contents.size(); index++){
            weather_data.add(new OWM_3HR40P_Data(json_contents.get(index)));
        }
        return weather_data;
    }





    private static String getURL_40P_3HR(String api_key, double latitude, double longitude) {
        return "https://api.openweathermap.org/data/2.5/forecast?" +
                        "lat=" + latitude +
                        "&lon=" + longitude +
                        "&appid=" + api_key +
                        "&cnt=40" +
                        "&units=metric";
    }

    public static ArrayList<OWM_3HR40P_Data> getWeatherData_40P_3HR(String api_key, double latitude, double longitude) {
        try {
            URL apiURL = new URL(getURL_40P_3HR(api_key,latitude,longitude));
            HttpURLConnection connection = (HttpURLConnection) apiURL.openConnection();
            connection.setRequestMethod("GET");

            InputStream inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }

            JSONObject jsonObject = new JSONObject(stringBuilder.toString());
            JSONArray forecastArray = jsonObject.getJSONArray("list");
            ArrayList<OWM_3HR40P_Data> weather_data = new ArrayList<>();
            for(int index = 0; index<forecastArray.length(); index++){
                weather_data.add(new OWM_3HR40P_Data(forecastArray.getJSONObject(index),latitude,longitude));
            }
            return weather_data;
        }catch(JSONException | IOException ex){
            ex.printStackTrace();
            return null;
        }
    }












}
