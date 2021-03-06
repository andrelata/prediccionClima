package com.example.web;

import com.example.model.Periodo;
import com.example.model.PicoLluvia;
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
            Prediccion p = repository.findByDia(d % ciclo);
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
     * @return la cantidad de dias de cierto clima dentro de los años.
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
     * Ej: http://localhost:8080/periodos?años=1 o http://localhost:8080/periodos
     * @param anio
     * @return lista de los periodos de clima y la cantidad de dias que se repiten en n años.
     * Por default el calculo se hace por 10 años.
     */
    @RequestMapping("/periodos")
    public List<Periodo> periodos(@RequestParam(value="años", defaultValue=años) String anio){
        List<Prediccion> dias = (List<Prediccion>) repository.findAll(); //un ciclo
        if(dias.size() > 0){
            try{
                int d = Integer.valueOf(anio).intValue() * diasXanio;
                int cantCiclos = getCiclos(d);
                int diasExtra = getDiasExtra(d, cantCiclos);
                Map<String, Long> map = contarPeriodos(dias);
                List<Periodo> periodos = mapearPeriodos(map, cantCiclos);
                if(diasExtra > 0){
                    dias = repository.findByDiaLessThan(diasExtra);
                    map = contarPeriodos(dias);
                    periodos = mapearPeriodos(map, cantCiclos, periodos);
                }
                return periodos;
            }catch (Exception e){
                throw new RuntimeException("El parametro que estas pasando como año no es valido.");
            }
        }
        throw new RuntimeException("Inicialice la bd");
    }

    /**
     * http://localhost:8080/picoLluvia
     * @param anio
     * @return lista de los dias con pico de lluvia dentro de n años. Por default 10 años
     */
    @RequestMapping("/picoLluvia")
    public List<PicoLluvia> picoLluvia(@RequestParam(value="años", defaultValue=años) String anio){
        Prediccion prediccion = repository.findFirstByOrderByPerimetroDesc();
        List<Prediccion> predicciones = repository.findByPerimetro(prediccion.getPerimetro());
        List<PicoLluvia> picos = new ArrayList<>();
        try{
            int d = Integer.valueOf(anio).intValue() * diasXanio;
            int cantCiclos = getCiclos(d);
            for(Prediccion p: predicciones){
                picos.add(new PicoLluvia(p.getDia(), p.getPerimetro()));
                while(cantCiclos > 1){
                    picos.add(new PicoLluvia(p.getDia() * cantCiclos, p.getPerimetro()));
                    cantCiclos--;
                }
                cantCiclos = getCiclos(d);
            }
            return picos;
        }catch (Exception e){
            //TODO podria armar una RuntimeException que sea año invalido
            throw new RuntimeException("El parametro que estas pasando como año no es valido.");
        }
    }

    private List<Periodo> mapearPeriodos(Map<String, Long> periodos, int ciclos) {
        List<Periodo> resp = new ArrayList<Periodo>();
        mapearPeriodos(periodos, ciclos, resp);
        return resp;
    }

    private List<Periodo> mapearPeriodos(Map<String, Long> map, int ciclos, List<Periodo> periodos ) {
        for(Map.Entry<String, Long> periodo: map.entrySet()){
            //TODO mejorar este codigo
            Periodo p = new Periodo(periodo.getKey(), periodo.getValue().intValue());
            if(periodos.contains(p)){
                int i = periodos.indexOf(p);
                periodos.get(i).addCantidad(p.getCantidad());
            }else{
                periodos.add(new Periodo(periodo.getKey(), periodo.getValue().intValue() * ciclos));
            }
        }
        return periodos;
    }

    private Map<String, Long> contarPeriodos(List<Prediccion> periodos){
        Map<String, Long> result =
                periodos.stream().collect(Collectors.groupingBy(Prediccion::getClima, Collectors.counting()));
        return result;
    }

    private int getCiclos(int dias){
        return dias/ciclo;
    }

    private int getDiasExtra(int dias, int cantCiclos){
        return dias - (ciclo * cantCiclos);
    }

}

