package com.racartech.library.rctandroid.location;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.Priority;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;

public class RCTlocationData{

    public static final int MODE_CURRENT = 0;
    public static final int MODE_LAST_KNOWN = 1;

    private Location LOCATION = null;
    private Address ADDRESS = null;
    private ArrayList<Address> LIKELY_ADDRESSES = new ArrayList<>();



    public RCTlocationData(Context context, double lat, double lng){
        this.ADDRESS = RCTlocation.getAddress(context,lat,lng);
    }

    public RCTlocationData(Address address){
        this.ADDRESS = address;
    }

    public RCTlocationData(Context context, int mode, long thread_wait) {

        boolean progress_boolean = false;
        AtomicBoolean finished_boolean = new AtomicBoolean(false);


        new Thread(new Runnable() {
            @Override
            public void run() {
                FusedLocationProviderClient fusedLocationProviderClient;
                fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);

                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                }

                if(mode == MODE_LAST_KNOWN){
                    fusedLocationProviderClient.getLastLocation()
                            .addOnSuccessListener(new OnSuccessListener<Location>() {
                                @Override
                                public void onSuccess(Location location) {
                                    if (location != null) {
                                        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
                                        List<Address> addresses = null;
                                        try {
                                            addresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
                                            if(addresses != null) {
                                                RCTlocationData.this.LOCATION = location;
                                                RCTlocationData.this.ADDRESS = addresses.get(0);
                                                RCTlocationData.this.LIKELY_ADDRESSES.clear();
                                                RCTlocationData.this.LIKELY_ADDRESSES.addAll(addresses);
                                                finished_boolean.set(true);
                                            }
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    finished_boolean.set(true);
                                }
                            }).addOnCanceledListener(new OnCanceledListener() {
                                @Override
                                public void onCanceled() {
                                    finished_boolean.set(true);
                                }
                            });
                }

                if(mode == MODE_CURRENT){
                    fusedLocationProviderClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY,null).addOnSuccessListener(new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if (location != null) {
                                Geocoder geocoder = new Geocoder(context, Locale.getDefault());
                                List<Address> addresses = null;
                                try {
                                    addresses = geocoder.getFromLocation(
                                                    location.getLatitude(),
                                                    location.getLongitude(),
                                                    1
                                                );
                                    if(addresses != null) {
                                        RCTlocationData.this.LOCATION = location;
                                        RCTlocationData.this.ADDRESS = addresses.get(0);
                                        RCTlocationData.this.LIKELY_ADDRESSES.clear();
                                        RCTlocationData.this.LIKELY_ADDRESSES.addAll(addresses);
                                        finished_boolean.set(true);
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            finished_boolean.set(true);
                        }
                    }).addOnCanceledListener(new OnCanceledListener() {
                        @Override
                        public void onCanceled() {
                            finished_boolean.set(true);
                        }
                    });
                }
            }
        }).start();

        while(!progress_boolean){
            if(!finished_boolean.get()){
                try {
                    Thread.sleep(thread_wait);
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }else{
                progress_boolean = true;
            }
        }



    }

    public Address getAddress(){
        return this.ADDRESS;
    }

    public Address setAddress(Address new_address){
        Address old_address = this.ADDRESS;
        this.ADDRESS = new_address;
        return old_address;
    }



}



