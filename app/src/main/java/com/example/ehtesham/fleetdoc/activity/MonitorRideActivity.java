package com.example.ehtesham.fleetdoc.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ehtesham.fleetdoc.R;
import com.example.ehtesham.fleetdoc.webservices.ApiClient;
import com.example.ehtesham.fleetdoc.webservices.ApiInterface;
import com.google.gson.Gson;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MonitorRideActivity extends AppCompatActivity {

    Button endRide;
    SensorManager mySensorManager;
    Sensor myProximitySensor,myAccelerometer,myGyroscopeSensor;
    long startTime = System.currentTimeMillis();
    public DataSummary summary = new DataSummary();
    SensorClient gyroClient = new GyroSensorClient(), accleroClient = new AccelerometerSensorClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor_ride);
        endRide = (Button) findViewById(R.id.Book);

        mySensorManager = (SensorManager) getSystemService(
                Context.SENSOR_SERVICE);
        myAccelerometer = mySensorManager.getDefaultSensor(
                Sensor.TYPE_LINEAR_ACCELERATION);

        myGyroscopeSensor = mySensorManager.getDefaultSensor(
                Sensor.TYPE_GYROSCOPE);

        myProximitySensor = mySensorManager.getDefaultSensor(
                Sensor.TYPE_PROXIMITY);


        if (myGyroscopeSensor == null) {
            Toast.makeText(MonitorRideActivity.this,"Gyroscope SensorClient not in Device!",Toast.LENGTH_SHORT).show();
            // ProximitySensor.setText("No Proximity SensorClient!");
        } else {
            mySensorManager.registerListener(proximitySensorEventListener,
                    myGyroscopeSensor,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }



        if (myAccelerometer == null) {
            Toast.makeText(MonitorRideActivity.this,"Accelerometer not in Device!",Toast.LENGTH_SHORT).show();
            // ProximitySensor.setText("No Proximity SensorClient!");
        } else {
            mySensorManager.registerListener(proximitySensorEventListener,
                    myAccelerometer,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }

        if (myProximitySensor == null) {
            Toast.makeText(MonitorRideActivity.this,"ProximitySensor not in Device!",Toast.LENGTH_SHORT).show();
           // ProximitySensor.setText("No Proximity SensorClient!");
        } else {
            mySensorManager.registerListener(proximitySensorEventListener,
                    myProximitySensor,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }


        endRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                Integer ride_id = sharedPref.getInt("ride_id", 0);

                ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
                Call<ResponseBody> call = apiInterface.rideSummary(ride_id, 100, summary.getBrakes(),summary.getBumps(),summary.getTopple());

                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {


                        //Toast.makeText(EditProfile.this, "" + response.code(), Toast.LENGTH_LONG).show();

                        if (response.code() == 200)
                        {

                            Intent intent = new Intent(MonitorRideActivity.this,LastActivity.class);
                            intent.putExtra("brakes",summary.getBrakes());
                            intent.putExtra("bumps",summary.getBumps());
                            intent.putExtra("topple",summary.getTopple());
                            startActivity(intent);
                        } else {
                            Toast.makeText(MonitorRideActivity.this, "Please Check your Internet Connection!" , Toast.LENGTH_LONG).show();

                        }


                        // Log.e("Lets check : ", "onResponse: " + response.body().optString("hi"));
                        //Toast.makeText(EditProfile.this, "" + response.body().toString(), Toast.LENGTH_LONG).show();


                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t)
                    {

                        //Log.d("hi", "" + t);
                        Toast.makeText(MonitorRideActivity.this, "Please Check your Internet Connection" , Toast.LENGTH_LONG).show();
                    }
                });




            }
        });
    }

    SensorEventListener proximitySensorEventListener
            = new SensorEventListener() {
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // TODO Auto-generated method stub
        }
        @Override
        public void onSensorChanged(SensorEvent event) {
            long currentTime = System.currentTimeMillis();
            if(currentTime - startTime < 200) return;

            startTime = currentTime;
            // TODO Auto-generated method stub
            if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
                if (event.values[0] == 0) {
                    ProximitySensorClient.isActive = false;

                } else {
                    ProximitySensorClient.isActive = true;

                }
            }

            if (event.sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION) {
                accleroClient.addData(new AxisData(event.values[0], event.values[1], event.values[2]),summary);
            }

            if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
                gyroClient.addData(new AxisData(event.values[0], event.values[1], event.values[2]),summary);
            }



        }
    };

//    @Override
//    public void onSensorChanged(SensorEvent sensorEvent) {
//
//    }
//
//    @Override
//    public void onAccuracyChanged(SensorClient sensor, int i) {
//
//    }

    //onResume() register the accelerometer for listening the events
    protected void onResume() {
        super.onResume();
        mySensorManager.registerListener(proximitySensorEventListener,
                myProximitySensor,
                SensorManager.SENSOR_DELAY_NORMAL);
         }

//    protected void onPause() {
//        super.onPause();
//        mySensorManager.unregisterListener(MonitorRideActivity.this);
//    }

}
