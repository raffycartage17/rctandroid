package com.racartech.library.rctandroid.google.maps;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.DirectionsApi;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.LatLng;
import com.google.maps.model.TravelMode;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class RCTgoogleMapsDirectionsData {

    public final LatLng ORIGIN;
    public final LatLng DESTINATION;
    public final TravelMode TRAVEL_MODE;
    public final ArrayList<DirectionsApi.RouteRestriction> ROUTE_RESTRICTIONS;
    public final Instant DEPARTURE_TIME;
    public final DirectionsRoute DIRECTION_ROUTE;


    public RCTgoogleMapsDirectionsData(String api_key,
                                       com.google.maps.model.LatLng origin,
                                       com.google.maps.model.LatLng destination,
                                       TravelMode travel_mode,
                                       ArrayList<DirectionsApi.RouteRestriction> route_restriction,
                                       Instant departure_time){

        this.ORIGIN = origin;
        this.DESTINATION = destination;
        this.TRAVEL_MODE = travel_mode;
        this.ROUTE_RESTRICTIONS = route_restriction;
        this.DEPARTURE_TIME = departure_time;

        this.DIRECTION_ROUTE = RCTgoogleMapsUtil.getDirectionRoute(
                api_key,
                this.ORIGIN,
                this.DESTINATION,
                this.TRAVEL_MODE,
                this.ROUTE_RESTRICTIONS,
                this.DEPARTURE_TIME
        );


    }


    public RCTgoogleMapsDirectionsData(String api_key,
                                       com.google.maps.model.LatLng origin,
                                       com.google.maps.model.LatLng destination,
                                       TravelMode travel_mode){

        this.ORIGIN = origin;
        this.DESTINATION = destination;
        this.TRAVEL_MODE = travel_mode;
        this.ROUTE_RESTRICTIONS = new ArrayList<>();
        this.DEPARTURE_TIME = Instant.now();

        this.DIRECTION_ROUTE = RCTgoogleMapsUtil.getDirectionRoute(
                api_key,
                this.ORIGIN,
                this.DESTINATION,
                this.TRAVEL_MODE,
                this.ROUTE_RESTRICTIONS,
                this.DEPARTURE_TIME
        );

    }


    public double getDrivingDistance_M(){
        return RCTgoogleMapsUtil.getDrivingDistance_M(this.DIRECTION_ROUTE);
    }
    public long getDrivingTime_Seconds(){
        return RCTgoogleMapsUtil.getDrivingTime_Seconds(this.DIRECTION_ROUTE);
    }
    public List<com.google.android.gms.maps.model.LatLng> getDirectionRoutePaths(){
        return RCTgoogleMapsUtil.getDirectionRoutePaths(this.DIRECTION_ROUTE);
    }
    public PolylineOptions getDirectionRoutePolylineOptions(){
        return RCTgoogleMapsUtil.getDirectionRoutePolylineOptions(this.DIRECTION_ROUTE);
    }






}
