package com.example.service;

import com.example.model.Planeta;
import com.example.model.Prediccion;
import com.example.repositories.PrediccionRepo;
import com.example.utils.Punto;
import com.example.utils.Recta;
import com.example.utils.TipoPeriodo;
import com.example.utils.Triangulo;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by andrealata on 1/16/17.
 */
public class PrediccionClimaService {

    private PrediccionRepo repository;

    private Planeta ferengi;
    private Planeta betasoide;
    private Planeta vulcano;
    private Punto sol = new Punto(0,0);

    //El ciclo dura 360 dias, despues las predicciones se repiten
    private final int ciclo = 360;

    public PrediccionClimaService(Planeta ferengi, Planeta betasoide, Planeta vulcano, PrediccionRepo repository) {
        this.ferengi = ferengi;
        this.betasoide = betasoide;
        this.vulcano = vulcano;
        this.repository = repository;

        repository.deleteAll();
        initPrediccion();
    }

    private void initPrediccion() {
        for(int i = 0; i < ciclo; i++) {
            repository.save(getClima(i));
        }
    }

    public Prediccion getClima(int dia){
        Punto p1 = ferengi.getPosicion(dia);
        Punto p2 = betasoide.getPosicion(dia);
        Punto p3 = vulcano.getPosicion(dia);

        Recta recta = new Recta(p1, p2);
        if(recta.include(p3)){
            //los 3 planetas estan alineados
            if(recta.include(sol)){
                //Estan alineados respecto al sol
                return new Prediccion(dia, TipoPeriodo.SEQUIA.getDescripcion(), 0);
            }
            return new Prediccion(dia, TipoPeriodo.OPTIMO.getDescripcion(), 0);
        }

        Triangulo triangulo = new Triangulo(p1, p2, p3);
        if(triangulo.include(sol)){
            //si el sol esta dentro del triangulo
            return new Prediccion(dia, TipoPeriodo.LLUVIA.getDescripcion(), triangulo.getPerimetro());
        }

        return new Prediccion(dia, TipoPeriodo.INDEFINIDO.getDescripcion(), 0); //Si el triangulo no incluye al sol y no es un recta
    }


}
