package dev.libjam.physx;

import dev.libjam.math.Vector2D;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeListener;
import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class World2DImpl extends World2D {

    public World2DImpl() {
        super();
    }


    public World2DImpl(int i, int i1) {
        super(i, i1);
    }

    @Override
    public void updateWorld(long time) {

    }
}

public class World2DTest {


    @SuppressWarnings("checkstyle:MagicNumber")
    @Test
    @DisplayName("World2D")
    void testWorld2D() {

        World2DImpl w = new World2DImpl();
        assertEquals(w, w.getWorld());
        assertEquals(0, w.getWidth());
        assertEquals(0, w.getHeight());
        assertEquals(0, w.getX());
        assertEquals(0, w.getY());
        assertEquals(w.getVelocity(), new Vector2D(0, 0));

        w = new World2DImpl(800, 600);
        assertEquals(w, w.getWorld());
        assertEquals(800, w.getWidth());
        assertEquals(600, w.getHeight());
        assertEquals(0, w.getX());
        assertEquals(0, w.getY());
        assertEquals(w.getVelocity(), new Vector2D(0, 0));
    }


    @Test
    @DisplayName("addObject() / remove object")
    void testAddObject() throws NoSuchFieldException, IllegalAccessException {

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

    @Test
    @DisplayName("equals")
    void testEquals() {

        World2D w1 = new World2D() {
            @Override
            public void updateWorld(long time) {

            }
        };

        World2D w2 = new World2D() {
            @Override
            public void updateWorld(long time) {

            }
        };

        assertEquals(w1, w1);
        assertNotEquals(w1, w2);
    }


    @Test
    @DisplayName("updateObject")
    void testUpdateObject() {

        World2D world = new World2DImpl();

        // mock Object2D
        Object2D obj = mock(Object2D.class);
        world.addObject(obj);

        world.updateObject(1);

        verify(obj, atLeast(1)).updateObject(eq(1L));
    }

}
