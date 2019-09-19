package com.example.ehtesham.fleetdoc.activity;

import android.util.Log;

import java.util.List;

/**
 * Created by ehtesham on 3/8/19.
 */

public class GyroSensorClient extends SensorClientImp<AxisData> {

    public float thres=6;
    @Override
    public void doCalculation(DataSummary summary) {
        List<AxisData> data = this.getSensorData();
        int count=0;
        float msfx=-1f,msfy=-1f,msfz=-1f;
        for(int i=0;i<data.size();i+=5){

            for(int j=i;j<i+5;j++){
                if(j>=data.size() || i >=data.size())
                    break;
                msfx = Math.max(msfx, Math.abs(data.get(j).getX()));
                msfy = Math.max(msfx, Math.abs(data.get(j).getY()));
                msfz = Math.max(msfx, Math.abs(data.get(j).getZ()));
            }
            if(msfx > thres || msfy > thres || msfz > thres)
                count+=1;
        }
        summary.setTopple(summary.getTopple()+count);
        return;
    }

}
