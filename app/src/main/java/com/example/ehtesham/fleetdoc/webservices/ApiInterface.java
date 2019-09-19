package com.example.ehtesham.fleetdoc.webservices;

import com.example.ehtesham.fleetdoc.model.RideDetails;

import org.json.JSONObject;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by ehtesham on 3/8/19.
 */

public interface ApiInterface {
    //@GET("api/restaurant/")
    @GET("create_ride")
    Call<RideDetails> getRideDetails();

//    @GET("/todos/1")
//    Call<ResponseBody> getRideDetails();



    @FormUrlEncoded
    @POST("/update_ride")
    Call<ResponseBody> rideSummary(@Field("ride_id") Integer rideId, @Field("d") Integer d , @Field("b") Integer b, @Field("bumps") Integer bumps, @Field("rg") Integer rg);

}
