package com.racartech.app.rctandroidlts.api;

import com.google.firebase.firestore.FirebaseFirestore;
import com.racartech.library.rctandroid.google.firebase.firestore.RCTfirebaseFirestore;

public class ApiKeyManager {

    public static String getGoogleApiKey(FirebaseFirestore instance){
        String collection = "key";
        String document = "keys";
        String field_name = "google_api";
        return RCTfirebaseFirestore.readField(
                collection,
                document,
                field_name,
                100,
                instance);
    }

    public static String getOpenWeatherMapApiKey(FirebaseFirestore instance){
        String collection = "key";
        String document = "keys";
        String field_name = "open_weather_map";
        return RCTfirebaseFirestore.readField(
                collection,
                document,
                field_name,
                100,
                instance);
    }

}
