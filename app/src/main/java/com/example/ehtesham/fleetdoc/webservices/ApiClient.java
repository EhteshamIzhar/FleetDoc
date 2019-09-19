package com.example.ehtesham.fleetdoc.webservices;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ehtesham on 3/8/19.
 */


public class ApiClient {
    //    private static final String BASE_URL = "http://f9a3eb81.ngrok.io/";
//
   // private static final  String BASE_URL = "https://jsonplaceholder.typicode.com/";

    private static final  String BASE_URL = "https://79a353bb.ngrok.io";


    private static Retrofit retrofit = null, ret = null;
    static Gson gson = new GsonBuilder()
            .setLenient()
            .create();


    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }


}

