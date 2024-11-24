package com.racartech.library.rctandroid.google.maps.distancematrix;

import android.content.Context;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.model.DistanceMatrix;

import java.util.ArrayList;

public class DistanceMatrixResults {


    public ArrayList<DistanceMatrixResult> DISTANCE_MATRIX_RESULT = new ArrayList<>();


    public DistanceMatrixResults(
            ArrayList<LatLng> origins,
            ArrayList<LatLng> destinations,
            DistanceMatrix result){

        for (int i = 0; i < result.rows.length; i++) {
            for (int j = 0; j < result.rows[i].elements.length; j++) {
//                System.out.println("From: " + origins.get(i) + " To: " + destinations.get(j));
//                System.out.println("Distance            : " + result.rows[i].elements[j].distance.inMeters);
//                System.out.println("Duration            : " + result.rows[i].elements[j].duration.inSeconds);
                DISTANCE_MATRIX_RESULT.add(new DistanceMatrixResult(
                        origins.get(i),
                        destinations.get(j),
                        result.rows[i].elements[j]
                ));
            }
        }
    }

    public void print(Context context){
        System.out.println("------------------------------------------------------");
        for(
                int index = 0; index<this.DISTANCE_MATRIX_RESULT.size(); index++
        ){

            DistanceMatrixResult current = DISTANCE_MATRIX_RESULT.get(index);
            System.out.println("Origin      : ".concat(String.valueOf(current.origin)));
            System.out.println("Destination : ".concat(String.valueOf(current.destination)));
            System.out.println("Distance (M): ".concat(String.valueOf(current.distance.inMeters)));
            System.out.println("Duration (S): ".concat(String.valueOf(current.duration.inSeconds)));
            System.out.println("------------------------------------------------------");

        }
    }


}
