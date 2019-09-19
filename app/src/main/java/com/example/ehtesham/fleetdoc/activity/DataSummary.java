package com.example.ehtesham.fleetdoc.activity;

/**
 * Created by ehtesham on 3/8/19.
 */

public class DataSummary {
    int brakes,bumps,topple;

    public int getBrakes() {
        return brakes;
    }

    public void setBrakes(int brakes) {
        this.brakes = brakes;
    }

    public int getBumps() {
        return bumps;
    }

    public void setBumps(int bumps) {
        this.bumps = bumps;
    }

    public int getTopple() {
        return topple;
    }

    public void setTopple(int topple) {
        this.topple = topple;
    }

    public DataSummary() {
        this.brakes=0;

        this.bumps=0;
        this.topple=0;

    }
}
