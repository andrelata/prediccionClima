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

    private double getAngulo(int dia){
        double deg = (sentido * dia * velAngular) % 360;
        return Math.toRadians(deg);
    }

    public Punto getPosicion(int dia){
        //Math.cos espera un nro en rad no en deg
        double x = radio * Math.sin(getAngulo(dia));
        double y = radio * Math.cos(getAngulo(dia));
        return new Punto(x,y);
    }
}
