package com.example.web;

import com.example.model.Periodo;
import com.example.model.Prediccion;
import com.example.repositories.PrediccionRepo;
import com.example.utils.TipoPeriodo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by andrealata on 1/15/17.
 */
@RestController
public class PrediccionController {

    @Autowired
    private PrediccionRepo repository;
    private final String años  = "10";

    //El ciclo dura 360 dias, despues las predicciones se repiten
    private final int ciclo = 360;
    private final int diasXanio = 365;

    /**
     *
     * EJ: http://localhost:8080/clima?dia=20
     * @param dia
     * @return la condición climática del día consultado.
     */
    @RequestMapping("/clima")
    public Prediccion clima(@RequestParam(value="dia") String dia) {
        try{
            int d = Integer.valueOf(dia).intValue();
            Prediccion p = repository.findByDia(d % ciclo).get(0);
            p.setDia(d);
            return p;
        }catch (Exception e){
            throw new RuntimeException("El parametro que estas pasando como dia no es valido.");
        }

    }

    /**
     * Ej: http://localhost:8080/periodo?clima=lluvia&&años=1 o http://localhost:8080/periodo?clima=Optimo
     * @param clima
     * @param anio
     * @return la cantidad de dias dentro de los años que el clima va ser de un tipo.
     * tipo de clima: lluvia, sequia, optimo
     * por default el calculo se hace por 10 años.
     */
    @RequestMapping("/periodo")
    public Periodo periodo(@RequestParam(value="clima") String clima, @RequestParam(value="años", defaultValue=años) String anio){
        //valida que el clima sea una opcion valida
        if(!TipoPeriodo.contains(clima)){
            throw new RuntimeException("El parametro que estas pasando como clima no es valido.");
        }
        try {
            List<Prediccion> dias = repository.findByClima(clima); //un ciclo
            int cantidad = 0;
            if(dias.size() > 0){
                int d = Integer.valueOf(anio).intValue() * diasXanio;
                int cantCiclos = getCiclos(d);
                int diasExtra = getDiasExtra(d, cantCiclos);
                cantidad = dias.size() * cantCiclos;
                if(diasExtra > 0){
                    dias = repository.findByDiaLessThanAndClima(diasExtra, clima);
                    cantidad += dias.size();
                }
            }
            return new Periodo(clima, cantidad);
        }catch (Exception e){
            throw new RuntimeException("El parametro que estas pasando como año no es valido.");
        }


    }

    /**
     * Ej: http://localhost:8080/periodos
     * @param anio
     * @return lista de los periodos de clima y la cantidad de dias que se repiten en n años.
     * Por default el calculo se hace por 10 años.
     */
    @RequestMapping("/periodos")
    public List<Periodo> periodos(@RequestParam(value="años", defaultValue=años) String anio){
        //TODO completar el metodo para que agarre los dias q corresponde
        List<Prediccion> dias = (List<Prediccion>) repository.findAll();
        return contarPeriodos(dias);
    }

    //TODO podria agregar un metodo que devuelva el pico máximo de lluvia en n años, default 10 años

    private List<Periodo> mapearPeriodos(Map<String, Long> periodos) {
        List<Periodo> resp = new ArrayList<Periodo>();
        for(Map.Entry<String, Long> periodo: periodos.entrySet()){
            resp.add(new Periodo(periodo.getKey(), periodo.getValue().intValue()));
        }
        return resp;
    }

    private List<Periodo> contarPeriodos(List<Prediccion> periodos){
        Map<String, Long> result =
                periodos.stream().collect(Collectors.groupingBy(Prediccion::getClima, Collectors.counting()));
        return mapearPeriodos(result);
    }

    private int getCiclos(int dias){
        return dias/ciclo;
    }

    private int getDiasExtra(int dias, int cantCiclos){
        return dias - (ciclo * cantCiclos);
    }

}

