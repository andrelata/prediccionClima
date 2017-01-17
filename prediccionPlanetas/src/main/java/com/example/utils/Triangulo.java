package com.example.utils;

/**
 * Created by andrealata on 1/16/17.
 */
public class Triangulo {

    private double d12;
    private double d23;
    private double d13;

    //Los vertices del triangulo
    private Punto v1;
    private Punto v2;
    private Punto v3;

    public Triangulo(Punto p1, Punto p2, Punto p3) {
        this.v1 = p1;
        this.v2 = p2;
        this.v3 = p3;

        d12 = calcularDistancia(v1,v2);
        d23 = calcularDistancia(v2,v3);
        d13 = calcularDistancia(v1,v3);
    }

    //perimetro de un triangulo suma de la distancia
    public double getPerimetro() {
        return d12 + d23 + d13;
    }

    //Si un punto esta dentro del triangulo
    public boolean include(Punto sol) {
        boolean b1, b2, b3;

        b1 = sign(sol, v1, v2) < 0.0;
        b2 = sign(sol, v2, v3) < 0.0;
        b3 = sign(sol, v3, v1) < 0.0;

        return ((b1 == b2) && (b2 == b3));
    }

    private double sign(Punto p1, Punto p2, Punto p3) {
        return (p1.getX() - p3.getX()) * (p2.getY() - p3.getY()) - (p2.getX() - p3.getX()) * (p1.getY() - p3.getY());
    }

    //d = sqrt((x2-x19)^2+(y2-y1)^2)
    private double calcularDistancia(Punto p1, Punto p2){
        return Math.sqrt(Math.pow((p2.getX() - p1.getX()),2) + Math.pow((p2.getY() - p1.getY()),2));
    }
}
