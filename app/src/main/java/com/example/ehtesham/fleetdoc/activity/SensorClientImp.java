package com.example.ehtesham.fleetdoc.activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ehtesham on 3/8/19.
 */

public class SensorClientImp<T> implements SensorClient<T> {
    private List<T> sensorData;

    public SensorClientImp() {
        this.sensorData = new ArrayList<T>();
    }

    @Override

    public List<T> getSensorData() {
        return sensorData;
    }

    @Override
    public void doCalculation(DataSummary summary) {

    }

    @Override
    public void addData(T data,DataSummary summary) {
        sensorData.add(data);
        if(sensorData.size() >=10){
            this.doCalculation(summary);
        }
    }
}
