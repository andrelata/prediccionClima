package com.example.utils;

/**
 * Created by andrealata on 1/16/17.
 */
public class Recta {

    //y = ax + b
    private double a;
    private double b;

    public Recta(Punto p1, Punto p2) {
        //a = (y2 - y1) / (x2 - x1)
        a = (p2.getY() - p1.getY())/(p2.getX() - p1.getX());
        //b = y1 - a * x1
        b = p1.getY() - a * p1.getX();
    }

    public boolean include(Punto p){
        double y = a * p.getX() + b;
        if( y == p.getY()){
            return true;
        }
        return false;
    }

}
