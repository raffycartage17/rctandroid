package com.racartech.library.rctandroid.google.maps.distancematrix;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.model.Distance;
import com.google.maps.model.DistanceMatrixElement;
import com.google.maps.model.Duration;
import com.google.maps.model.Fare;

public class DistanceMatrixResult{

    public LatLng origin;
    public LatLng destination;
    public Duration duration;
    public Duration durationInTraffic;
    public Distance distance;
    public Fare fare;




    public DistanceMatrixResult(
            LatLng origin,
            LatLng destination,
            DistanceMatrixElement distanceMatrixElement
    ){
        this.origin = origin;
        this.destination = destination;
        this.duration = distanceMatrixElement.duration;
        this.durationInTraffic = distanceMatrixElement.durationInTraffic;
        this.distance = distanceMatrixElement.distance;
        this.fare = distanceMatrixElement.fare;
    }

}
