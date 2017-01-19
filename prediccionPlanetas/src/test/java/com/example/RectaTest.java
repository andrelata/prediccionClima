package com.example;

import com.example.utils.Punto;
import com.example.utils.Recta;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by andrealata on 1/16/17.
 */
public class RectaTest {

    @Test
    public void testInclude() throws Exception {
        Punto p1 = new Punto(1, 2);
        Punto p2 = new Punto(5, 6);

        Recta linea = new Recta(p1, p2);

        Punto p = new Punto(0, 0);
        assertFalse(linea.include(p));

        p = new Punto(1,2);
        assertTrue(linea.include(p));

        //recta vertical
        p1 = new Punto(0, 2);
        p2 = new Punto(0, 6);

        linea = new Recta(p1, p2);

        p = new Punto(0,0);
        assertTrue(linea.include(p));

        p = new Punto(1,1);
        assertFalse(linea.include(p));

        //recta horizontal
        p1 = new Punto(-1, 4);
        p2 = new Punto(3, 4);

        linea = new Recta(p1, p2);

        p = new Punto(2,4);
        assertTrue(linea.include(p));

        p = new Punto(-1,5);
        assertFalse(linea.include(p));

    }
}
