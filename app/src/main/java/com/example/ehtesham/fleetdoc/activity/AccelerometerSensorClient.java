package com.example.ehtesham.fleetdoc.activity;

import android.util.Log;

import java.util.List;

/**
 * Created by ehtesham on 3/8/19.
 */

public class AccelerometerSensorClient extends SensorClientImp<AxisData> {
    public static float bumpThreshold=5;
    public static float brakeThreshold=2;
    @Override
    public void doCalculation(DataSummary summary) {
        List<AxisData> data = this.getSensorData();

        int bumpCount = checkBump(data);
        int brakeCount = checkBrake(data);
        summary.setBrakes(summary.getBrakes()+brakeCount);
        summary.setBumps(summary.getBumps()+bumpCount);
        this.getSensorData().removeAll(data);
    }

    private int checkBump(List<AxisData> data){
        int count=0;
        for(int i=1;i<data.size();i++){
            Log.v("bumpSignificancd", ""+significantChange(data.get(i), data.get(i - 1)));
            if(significantChange(data.get(i),data.get(i - 1))) {
                count += 1;
                i+=1;
            }
        }
        return count;
    }

    private int checkBrake(List<AxisData> data){
        for(AxisData d:data){
            Log.v("mag", ""+getMagnitude(d.getX(), d.getY(), 0));
            if (getMagnitude(d.getX(), d.getY(), 0) >= brakeThreshold) {
                return 1;
            }
        }
        return 0;
    }
    public static float getMagnitude(float a, float b, float c){
        return (float) Math.sqrt(a * a + b * b + c * c);
    }

    public boolean significantChange(AxisData a,AxisData b){
        Log.v("axisValue", "" + a.getZ() + " , " + b.getZ());
        if((a.getZ()*b.getZ() < 0) && (Math.abs(a.getZ() - b.getZ()) > bumpThreshold)){
            Log.v("InLoop", "" + a.getZ() + " , " + b.getZ());
            return true;
       }
        return false;
    }
}
