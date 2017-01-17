package com.example.utils;

/**
 * Created by andrealata on 1/16/17.
 */
public class Triangulo {

    private double lado1;
    private double lado2;
    private double lado3;

    //Los vertices del triangulo
    private Punto v1;
    private Punto v2;
    private Punto v3;

    public Triangulo(Punto p1, Punto p2, Punto p3) {
        this.v1 = p1;
        this.v2 = p2;
        this.v3 = p3;

        armarTriangulo();
    }

    private void armarTriangulo() {
        //TODO calcular los 3 lados del triangulo
    }

    //perimetro de un triangulo = lado1 + lado2 + lado3
    public double getPerimetro() {
        return lado1 + lado2 + lado3;
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
}
