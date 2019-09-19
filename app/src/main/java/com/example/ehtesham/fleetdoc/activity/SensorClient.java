package com.example.ehtesham.fleetdoc.activity;

import java.util.List;

/**
 * Created by ehtesham on 3/8/19.
 */

public interface SensorClient<T> {

    List<T> getSensorData();
    public void doCalculation(DataSummary summary);
    public void addData(T data,DataSummary summary);
}
