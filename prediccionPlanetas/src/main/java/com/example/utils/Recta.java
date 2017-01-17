package com.example.utils;

/**
 * Created by andrealata on 1/16/17.
 */
public class Recta {

    //y = ax + b
    private double a;
    private double b;

    private double e = 0.01;

    public Recta(Punto p1, Punto p2) {
        //a = (y2 - y1) / (x2 - x1)
        a = (p2.getY() - p1.getY())/(p2.getX() - p1.getX());
        //b = y1 - a * x1
        b = p1.getY() - a * p1.getX();
    }

    public boolean include(Punto p){
        double y = a * p.getX() + b;
        //si esta cerca de la recta con un margen de error menor a e
        if(Math.abs(y - p.getY()) < e){
            return true;
        }
        return false;
    }

}
