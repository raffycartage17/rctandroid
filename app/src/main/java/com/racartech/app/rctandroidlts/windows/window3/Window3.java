package com.racartech.app.rctandroidlts.windows.window3;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.racartech.app.rctandroidlts.FirebaseSingleton;
import com.racartech.app.rctandroidlts.R;
import com.racartech.app.rctandroidlts.api.ApiKeyManager;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Window3 extends AppCompatActivity {

    Button f1, f2, f3;
    FirebaseSingleton firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window3);

        new Thread(new Runnable() {
            @Override
            public void run() {
                FirebaseSingleton firebase = FirebaseSingleton.getInstance();
                // Your Google Maps API Key
                //String apiKey = ApiKeyManager.getGoogleApiKey(firebase.getFirestore());

                String apiKey = "AIzaSyAcsGFBAasyrN6AYrGv8sK21eTtV11WJ0Y";

                System.out.println("API KEY : "+apiKey);

                // Create the HTTP client
                OkHttpClient client = new OkHttpClient();

                // Build the request
                String jsonRequest = "{\n" +
                        "    \"origin\": {\"location\": {\"latLng\": {\n" +
                        "        \"latitude\": 40.712776,\n" +
                        "        \"longitude\": -74.005974\n" +
                        "    }}},\n" +
                        "    \"destination\": {\"location\": {\"latLng\": {\n" +
                        "        \"latitude\": 34.052235,\n" +
                        "        \"longitude\": -118.243683\n" +
                        "    }}},\n" +
                        "    \"travelMode\": \"DRIVE\"\n" +
                        "}";

                try {

                    Request request = new Request.Builder()
                            .url("https://routes.googleapis.com/directions/v2:computeRoutes?key=" + apiKey)
                            .post(RequestBody.create(jsonRequest, MediaType.get("application/json")))
                            .build();

                    System.out.println("Request String : ".concat(request.toString()));

                    // Execute the request
                    try (Response response = client.newCall(request).execute()) {
                        if (response.isSuccessful() && response.body() != null) {
                            System.out.println("--------------------------------------------");
                            System.out.println("Response: " + response.body().string());
                            System.out.println("--------------------------------------------");
                        } else {
                            System.out.println("--------------------------------------------");
                            System.out.println("Request failed: " + response.code() + " " + response.message());
                            System.out.println("--------------------------------------------");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }catch (Exception exx){
                    exx.printStackTrace();
                }




            }
        }).start();


    }

}









