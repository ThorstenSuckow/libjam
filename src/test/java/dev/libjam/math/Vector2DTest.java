package dev.libjam.math;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Vector2DTest {


    @SuppressWarnings("checkstyle:MagicNumber")
    @Test
    @DisplayName("Vector2D")
    void testVector2D() {

        Vector2D v = new Vector2D();
        assertEquals(0.0, v.getX());
        assertEquals(0.0, v.getY());

        v = new Vector2D(1, 3);
        assertEquals(1.0, v.getX());
        assertEquals(3.0, v.getY());

        v.setXY(4, 5);
        assertEquals(4.0, v.getX());
        assertEquals(5.0, v.getY());

        v.setX(6);
        v.setY(7);
        assertEquals(6.0, v.getX());
        assertEquals(7.0, v.getY());

        assertEquals(Math.sqrt(85), v.getMagnitude());

        Vector2D v3 = v.add(new Vector2D(1, 2));
        assertNotSame(v, v3);
        assertEquals(7.0, v3.getX());
        assertEquals(9.0, v3.getY());

        Vector2D v4 = v3.clone();
        assertNotSame(v3, v4);

        assertTrue(v4.equals(v3));


    }


}
