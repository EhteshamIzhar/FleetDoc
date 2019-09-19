package com.example.ehtesham.fleetdoc.activity;

/**
 * Created by ehtesham on 3/8/19.
 */

public class AxisData {
    float x,y, z;

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public AxisData(float x, float y, float z) {

        this.x = x;
        this.y = y;
        this.z = z;
    }
}
