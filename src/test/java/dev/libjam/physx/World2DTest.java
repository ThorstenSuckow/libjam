package dev.libjam.physx;

import dev.libjam.math.Vector2D;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.beans.PropertyChangeListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.*;
import java.util.List;

class World2DImpl extends World2D {
    @Override
    public void updateWorld(long time) {

    }
}

public class World2DTest {


    @SuppressWarnings("checkstyle:MagicNumber")
    @Test
    @DisplayName("World2D")
    void World2D() {

        World2DImpl w = new World2DImpl();

        assertEquals(w, w.getWorld());

        assertEquals(0, w.getX());
        assertEquals(0, w.getY());

        assertEquals(w.getVelocity(), new Vector2D(0, 0));
    }


    @Test
    @DisplayName("addObject() / remove object")
    void addObject() throws NoSuchFieldException, IllegalAccessException {

        World2DImpl w = new World2DImpl();

        Object2D obj = new Object2D(0, 0);

        // World2D PropertyChangeListener
        Field listeners = Object2D.class.getDeclaredField("listeners");
        listeners.setAccessible(true);
        List<PropertyChangeListener> propertyChangeListener = (List<PropertyChangeListener>) listeners.get(obj);

        assertFalse(propertyChangeListener.contains(w));

        w.addObject(obj);

        assertTrue(propertyChangeListener.contains(w));

        Field objects = World2D.class.getDeclaredField("objects");
        objects.setAccessible(true);
        List<Object2D> objectList = (List<Object2D>) objects.get(w);

        assertTrue(objectList.contains(obj));
        assertEquals(w, obj.getWorld());

        obj.setWorld(null);
        assertFalse(objectList.contains(obj));


        assertFalse(propertyChangeListener.contains(w));

        assertNull(obj.getWorld());

    }

}
