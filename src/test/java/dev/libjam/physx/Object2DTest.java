package dev.libjam.math;


import dev.libjam.physx.Object2D;
import dev.libjam.physx.World2D;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class World2DTest extends World2D {

    @Override
    public void updateWorld(long time) {
        // intentionally left emoty
    }
}

public class Object2DTest {


    @SuppressWarnings("checkstyle:MagicNumber")
    @Test
    @DisplayName("Object2D")
    void Object2D() {

        Object2D o = new Object2D(4, 5);
        assertEquals(4.0, o.getWidth());
        assertEquals(5.0, o.getHeight());


        assertNull(o.getVelocity());
        assertEquals(-1, o.getX());
        assertEquals(-1, o.getY());
        assertTrue(o.getAge() > 0);

        assertTrue(o.getAge(System.nanoTime()) > 0);

        o.setX(100);
        assertEquals(-1, o.getX());
        o.setY(100);
        assertEquals(-1, o.getY());

        o.setVelocity(1, 1);
        assertNull(o.getVelocity());

    }


    @Test
    @DisplayName("Object2D with owning World2D")
    void Object2DwithOwningWorld2D() {

        Object2D o = new Object2D(0, 0);

        o.setWorld(new World2DTest());

        assertNotNull(o.getVelocity());
        assertEquals(-1, o.getX());
        assertEquals(-1, o.getY());

        o.setX(100);
        assertEquals(100, o.getX());
        o.setY(200);
        assertEquals(200, o.getY());

        o.setVelocity(1, 2);
        assertTrue(o.getVelocity().equals(new Vector2D(1, 2)));

        o.setWorld(new World2DTest());
        assertTrue(o.getVelocity().equals(new Vector2D(0, 0)));
        assertEquals(-1, o.getX());
        assertEquals(-1, o.getY());

        o.setWorld(null);
        assertNull(o.getVelocity());
        assertEquals(-1, o.getX());
        assertEquals(-1, o.getY());

    }

}
