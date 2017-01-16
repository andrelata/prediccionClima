package com.example.utils;

/**
 * Created by andrealata on 1/16/17.
 */
public enum TipoPeriodo {

    SEQUIA("sequia"), LLUVIA("lluvia"), OPTIMO("optimo"), INDEFINIDO("indefinido");

    private String descripcion;

    TipoPeriodo(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public static boolean contains(String tipo){
        if(tipo.equalsIgnoreCase(SEQUIA.descripcion) ||
                tipo.equalsIgnoreCase(LLUVIA.descripcion) ||
                tipo.equalsIgnoreCase(OPTIMO.descripcion)){
            return true;
        }
        return false;
    }

}
