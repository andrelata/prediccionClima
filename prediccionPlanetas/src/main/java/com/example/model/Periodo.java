package com.example.model;

/**
 * Created by andrealata on 1/16/17.
 */
public class Periodo {

    private String clima;
    private int cantidad;

    public Periodo(String periodo, int cantidad) {
        this.clima = periodo;
        this.cantidad = cantidad;
    }

    public String getPeriodo() {
        return clima;
    }

    public int getCantidad() {
        return cantidad;
    }


}

