package com.racartech.library.rctandroid.location;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class RCTlocation{


    public static Address getAddress(Context context,double latitude, double longitude){
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if(addresses != null){
                return addresses.get(0);
            }else{
                return null;
            }
        }catch (IOException ex){
            ex.printStackTrace();
            return null;
        }
    }






}
