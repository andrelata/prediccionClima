package com.example;

import com.example.model.Planeta;
import com.example.model.Prediccion;
import com.example.repositories.PrediccionRepo;
import com.example.service.PrediccionClimaService;
import com.example.utils.TipoPeriodo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by andrealata on 1/18/17.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class PrediccionClimaServiceTest {

    @Autowired
    private PrediccionRepo repository;

    @Test
    public void testGetClima() throws Exception{
        Planeta ferengi = new Planeta(1, 1, 500);
        Planeta betasoide = new Planeta(3, 1, 2000);;
        Planeta vulcano = new Planeta(5, -1, 1000);;

        PrediccionClimaService service = new PrediccionClimaService(ferengi, betasoide, vulcano, repository);

        Prediccion p = service.getClima(90);
        assertEquals(TipoPeriodo.SEQUIA.getDescripcion(), p.getClima());

        p = service.getClima(0);
        assertEquals(TipoPeriodo.SEQUIA.getDescripcion(), p.getClima());

        p = service.getClima(70);
        assertEquals(TipoPeriodo.LLUVIA.getDescripcion(), p.getClima());

        p = service.getClima(60);
        assertEquals(TipoPeriodo.INDEFINIDO.getDescripcion(), p.getClima());
    }
}

