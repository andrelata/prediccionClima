package com.example;

import com.example.model.Prediccion;
import com.example.repositories.PrediccionRepo;
import com.example.utils.TipoPeriodo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by andrealata on 1/18/17.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class PrediccionRepoTest {

    @Autowired
    private PrediccionRepo repository;

    @Before
    public void initRepo(){
        this.repository.deleteAll();
        this.repository.save(new Prediccion(1, TipoPeriodo.SEQUIA.getDescripcion(), 0));
        this.repository.save(new Prediccion(2, TipoPeriodo.LLUVIA.getDescripcion(), 20));
        this.repository.save(new Prediccion(3, TipoPeriodo.OPTIMO.getDescripcion(), 0));
        this.repository.save(new Prediccion(4, TipoPeriodo.LLUVIA.getDescripcion(), 10));
        this.repository.save(new Prediccion(5, TipoPeriodo.OPTIMO.getDescripcion(), 0));
    }

    @Test
    public void testFindByDia() throws Exception{
        Prediccion p = repository.findByDia(1);
        assertEquals(TipoPeriodo.SEQUIA.getDescripcion(), p.getClima());
    }

    @Test
    public void testFindByClima() throws Exception{
        List<Prediccion> p = repository.findByClima(TipoPeriodo.LLUVIA.getDescripcion());
        assertEquals(2, p.size());
        assertTrue(p.get(0).getDia() == 2);
    }

    @Test
    public void testFindByDiaLessThan() throws Exception {
        List<Prediccion> p = repository.findByDiaLessThan(3);
        assertEquals(2, p.size());
    }

    @Test
    public void testFindByDiaLessThanAndClima() throws Exception {
        List<Prediccion> list = repository.findByDiaLessThanAndClima(2, TipoPeriodo.OPTIMO.getDescripcion());
        assertTrue(list.isEmpty());
        list = repository.findByDiaLessThanAndClima(4, TipoPeriodo.SEQUIA.getDescripcion());
        assertEquals(1, list.size());
        assertTrue(list.get(0).getDia() == 1);
    }

    @Test
    public void testFindFirstByOrderByPerimetroDesc() throws Exception {
        Prediccion p = repository.findFirstByOrderByPerimetroDesc();
        assertEquals(20, p.getPerimetro(), 0.01);
    }

    @Test
    public void testFindByPerimetro() throws Exception {
        List<Prediccion> p = repository.findByPerimetro(20.0);
        assertEquals(1, p.size());
    }
}
