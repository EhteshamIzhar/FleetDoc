package com.example.ehtesham.fleetdoc.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ehtesham.fleetdoc.R;
import com.example.ehtesham.fleetdoc.model.RideDetails;
import com.example.ehtesham.fleetdoc.webservices.ApiClient;
import com.example.ehtesham.fleetdoc.webservices.ApiInterface;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StartRideActivity extends AppCompatActivity {
    Button btnStart;
   RideDetails rideDetails;
    TextView rideMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_ride);
        rideMsg = (TextView) findViewById(R.id.rideMsg);

        fetchRideDetails();




        if(rideDetails != null)
        {rideMsg.setText("Your Booking id is : " + rideDetails.getRideId());}//rideDetails.getRideId()
        btnStart = (Button) findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartRideActivity.this,MonitorRideActivity.class);
                startActivity(intent);
            }
        });
    }

    private void fetchRideDetails(){

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<RideDetails> call = apiInterface.getRideDetails();

        call.enqueue(new Callback<RideDetails>() {
            @Override
            public void onResponse(Call<RideDetails> call, Response<RideDetails> response) {

                Log.v("shvashis code","" + response.code());
                rideDetails = response.body();
                rideMsg.setText("Your Booking id is : " + rideDetails.getRideId());

                SharedPreferences sharedPreferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("ride_id", rideDetails.getRideId());
                editor.apply();

               // Toast.makeText(StartRideActivity.this,""+response.code() + "\n\n"+response.body().getRideId(),Toast.LENGTH_SHORT).show();

                if (response.code() != 200)
                    Toast.makeText(StartRideActivity.this, "Please check internet connection \n\n " + response.body(), Toast.LENGTH_SHORT).show();

                // else
                //  Toast.makeText(selectLocationActivity.this,""+response.code(),Toast.LENGTH_SHORT).show();
                if (rideDetails == null)
                    Toast.makeText(StartRideActivity.this, "Sorry, No rides found!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<RideDetails> call, Throwable t) {
                Toast.makeText(StartRideActivity.this, "Please check your Internet Connection!", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
