package com.tan.thread;

/**
 * @author Tan.cs
 * @version 1.0
 * @description
 * @date 2021/6/30 11:14
 **/
public class Location {

    private final double x;
    private final double y;


    public Location(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
