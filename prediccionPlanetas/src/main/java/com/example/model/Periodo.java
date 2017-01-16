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


    public void addCantidad(int value) {
        cantidad += value;
    }

    @Override
    public boolean equals(Object obj) {
        return this.clima.equalsIgnoreCase(((Periodo)obj).getPeriodo());
    }
}

