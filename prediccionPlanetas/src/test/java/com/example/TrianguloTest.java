package com.example;

import com.example.utils.Punto;
import com.example.utils.Triangulo;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by andrealata on 1/16/17.
 */
public class TrianguloTest {

    @Test
    public void testInclude() throws Exception {
        Punto v1 = new Punto(1, 1);
        Punto v2 = new Punto(-1, 0);
        Punto v3 = new Punto(1, -1);

        Triangulo t = new Triangulo(v1, v2, v3);

        Punto p = new Punto(0,0);
        assertTrue(t.include(p));

        p = new Punto(2,2);
        assertFalse(t.include(p));

        p = new Punto(1,0);
        assertTrue(t.include(p));
    }

}
