package com.example.model;

/**
 * Created by andrealata on 1/16/17.
 */
public class PicoLluvia {

    private int dia;
    private double perimetro;

    public PicoLluvia(int dia, double perimetro) {
        this.dia = dia;
        this.perimetro = perimetro;
    }

    public double getPerimetro() {
        return perimetro;
    }

    public int getDia() {
        return dia;
    }
}
