package com.example.model;

import javax.persistence.*;

/**
 * Created by andrealata on 1/15/17.
 */
@Entity
public class Prediccion {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private int dia;
    private String clima;
    private int perimetro;

    public Prediccion(int dia, String clima, int perimetro) {
        this.dia = dia;
        this.clima = clima;
        this.perimetro = perimetro;
    }

    public Prediccion() {
    }

    public int getDia() {
        return dia;
    }

    public String getClima() {
        return clima;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }
}
