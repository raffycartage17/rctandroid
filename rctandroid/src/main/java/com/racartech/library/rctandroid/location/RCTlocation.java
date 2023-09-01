package com.racartech.library.rctandroid.location;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class RCTlocation{


    public final static int LATITUDE_INDEX = 0;
    public final static int LONGITUDE_INDEX = 1;
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

    public static double[] getDefaultLocationCoordinates() {
        return new double[]{14.5995, 120.9842};
    }


    public static boolean isSameLocality(Address address_one, Address address_two){
        String address_one_country_name = address_one.getCountryName();
        String address_two_country_name = address_two.getCountryName();

        String address_one_admin_area = address_one.getAdminArea();
        String address_two_admin_area = address_two.getAdminArea();

        String address_one_sub_admin_area = address_one.getSubAdminArea();
        String address_two_sub_admin_area = address_two.getSubAdminArea();

        String address_one_locality = address_one.getLocality();
        String address_two_locality = address_two.getLocality();

        boolean pass_one = true;
        boolean pass_two = true;
        boolean pass_three = true;
        boolean pass_four = true;
        try{
            if(!address_one_country_name.equals(address_two_country_name)){
                pass_one = false;
            }
        }catch (NullPointerException ignored){}

        try{
            if(!address_one_admin_area.equals(address_two_admin_area)){
                pass_two = false;
            }
        }catch (NullPointerException ignored){}

        try{
            if(!address_one_sub_admin_area.equals(address_two_sub_admin_area)){
                pass_three = false;
            }
        }catch (NullPointerException ignored){}

        try{
            if(!address_one_locality.equals(address_two_locality)){
                pass_four = false;
            }
        }catch (NullPointerException ignored){
            pass_four = false;
        }
        return (pass_one && pass_two && pass_three && pass_four);
    }


    public static boolean isSameLocality(
            Context context,
            double address_one_latitude,
            double address_one_longitude,
            double address_two_latitude,
            double address_two_longitude){

        Address address_one = getAddress(
                context,
                address_one_latitude,
                address_one_longitude);

        Address address_two = getAddress(
                context,
                address_two_latitude,
                address_two_longitude);

        String address_one_country_name = address_one.getCountryName();
        String address_two_country_name = address_two.getCountryName();

        String address_one_admin_area = address_one.getAdminArea();
        String address_two_admin_area = address_two.getAdminArea();

        String address_one_sub_admin_area = address_one.getSubAdminArea();
        String address_two_sub_admin_area = address_two.getSubAdminArea();

        String address_one_locality = address_one.getLocality();
        String address_two_locality = address_two.getLocality();

        boolean pass_one = true;
        boolean pass_two = true;
        boolean pass_three = true;
        boolean pass_four = true;
        try{
            if(!address_one_country_name.equals(address_two_country_name)){
                pass_one = false;
            }
        }catch (NullPointerException ignored){}

        try{
            if(!address_one_admin_area.equals(address_two_admin_area)){
                pass_two = false;
            }
        }catch (NullPointerException ignored){}

        try{
            if(!address_one_sub_admin_area.equals(address_two_sub_admin_area)){
                pass_three = false;
            }
        }catch (NullPointerException ignored){}

        try{
            if(!address_one_locality.equals(address_two_locality)){
                pass_four = false;
            }
        }catch (NullPointerException ignored){
            pass_four = false;
        }
        return (pass_one && pass_two && pass_three && pass_four);
    }


    public static boolean isLocationEnabled(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (locationManager != null) {
            return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                    || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        }
        return false;
    }






}
