package com.example.ehtesham.fleetdoc.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ehtesham on 3/8/19.
 */

public class RideDetails {

    @SerializedName("ride_id")
    @Expose
    private Integer rideId;

    public Integer getRideId() {
        return rideId;
    }

    public void setRideId(Integer rideId) {
        this.rideId = rideId;
    }

}

