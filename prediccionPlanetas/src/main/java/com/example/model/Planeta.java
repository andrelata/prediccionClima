package com.example.model;

import com.example.utils.Punto;

/**
 * Created by andrealata on 1/16/17.
 */
public class Planeta {

   // private String nombre;
    private int velAngular;
    private int sentido;
    private int radio;

    public Planeta(/*String nombre,*/ int velAngular, int sentido, int radio) {
      //  this.nombre = nombre;
        this.velAngular = velAngular;
        this.sentido = sentido;
        this.radio = radio;
    }

    private float getAngulo(int dia){
        return (sentido * dia * velAngular) % 360;
    }

    public Punto getPosicion(int dia){
        double x = radio * Math.cos(getAngulo(dia));
        double y = radio * Math.sin(getAngulo(dia));
        return new Punto(x,y);
    }

}