package com.example.ehtesham.fleetdoc.activity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ehtesham.fleetdoc.R;

public class SummaryActivity extends AppCompatActivity {
    SensorManager mySensorManager;
    Sensor myProximitySensor,myAccelerometer,myGyroscopeSensor;
    long startTime = System.currentTimeMillis();
    TextView x,y,z;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        x = (TextView) findViewById(R.id.textView);
        y = (TextView) findViewById(R.id.textView2);
        z = (TextView) findViewById(R.id.textView3);

        mySensorManager = (SensorManager) getSystemService(
                Context.SENSOR_SERVICE);
        myAccelerometer = mySensorManager.getDefaultSensor(
                Sensor.TYPE_LINEAR_ACCELERATION);

        if (myAccelerometer == null) {
            Toast.makeText(SummaryActivity.this,"Accelerometer not in Device!",Toast.LENGTH_SHORT).show();
            // ProximitySensor.setText("No Proximity SensorClient!");
        } else {
            mySensorManager.registerListener(proximitySensorEventListener,
                    myAccelerometer,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
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

            if (event.sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION) {
                x.setText(""+event.values[0]);
                y.setText(""+event.values[1]);
                z.setText(""+event.values[2]);
            }





        }
    };
}
