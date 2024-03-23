package com.racartech.app.rctandroidlts.maps;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.racartech.app.rctandroidlts.R;
import com.racartech.library.rctandroid.google.maps.RCTgoogleMapsDropPin;

public class MapsTestActivity extends AppCompatActivity implements RCTgoogleMapsDropPin.OnPinDropListener {

    private FrameLayout mapContainer;
    private RCTgoogleMapsDropPin customMapView;
    private Button addButton;

    TextView long_value, lat_value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maps_test_activity_layout);

        mapContainer = findViewById(R.id.map_container);
        addButton = findViewById(R.id.add_button);
        long_value = findViewById(R.id.mtcl_long_value);
        lat_value = findViewById(R.id.mtcl_lat_value);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMapView();
            }
        });
    }

    private void addMapView() {
        customMapView = new RCTgoogleMapsDropPin(this);
        customMapView.setOnPinDropListener(this); // Set listener
        mapContainer.addView(customMapView);
    }

    // Implement the method from OnPinDropListener interface
    @Override
    public void onPinDrop(double latitude, double longitude) {
        lat_value.setText(String.valueOf(latitude));
        long_value.setText(String.valueOf(longitude));
    }
}
