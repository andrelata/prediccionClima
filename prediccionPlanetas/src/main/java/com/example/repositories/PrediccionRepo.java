package com.example.repositories;

import com.example.model.Prediccion;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by andrealata on 1/15/17.
 */
public interface PrediccionRepo extends PagingAndSortingRepository<Prediccion,Long> {

    /**
     * @param dia
     * @return Devuelve la prediccion de ese dia
     */
    public Prediccion findByDia(int dia);

    /**
     * @param clima
     * @return Devuelve las predicciones que tienen el mismo clima
     */
    public List<Prediccion> findByClima(String clima);

    /**
     * @param dia de dias que devuelve
     * @return Devuelve la lista de predicciones menor a dia where x.dia < ?1
     */
    public List<Prediccion> findByDiaLessThan(int dia);

    /**
     *
     * @param dia
     * @param clima
     * @return Devuelve la lista de predicciones menor a dia where x.dia < ?1 y con cierto clima
     */
    public List<Prediccion> findByDiaLessThanAndClima(int dia, String clima);


    /**
     *
     * @return devuelve la prediccion con mayor perimetro
     */
    public Prediccion findFirstByOrderByPerimetroDesc();

    /**
     *
     * @param perimetro
     * @return devuelve la lista de predicciones con cierto perimetro
     */
    public List<Prediccion> findByPerimetro(double perimetro);
}

