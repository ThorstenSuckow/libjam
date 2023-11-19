package dev.libjam.physx;


import dev.libjam.math.Vector2D;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class TestWorld2D extends World2D {

    @Override
    public void updateWorld(long time) {
        // intentionally left emoty
    }
}

class TestPropertyChangeListener implements PropertyChangeListener {

    List<PropertyChangeEvent> list = new ArrayList<PropertyChangeEvent>();

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        list.add(evt);
    }

    private boolean equals(Object sourceValue, Object target) {

        if (sourceValue instanceof Vector2D) {
            return ((Vector2D)sourceValue).equals(target);
        }

        if (sourceValue instanceof World2D) {
            return ((World2D)sourceValue).equals(target);
        }

        if (sourceValue instanceof Double) {
            return ((Double)sourceValue).equals(target);
        }

        throw new IllegalArgumentException();
    }

    public boolean eventMatches(
            final int index,
            final String propertyName,
            final Object2D source,
            final Object oldValue,
            final Object newValue
    ) {

        PropertyChangeEvent evt = list.get(index);

        if (!propertyName.equals(evt.getPropertyName())) {
            fail("[fail(" + index + ")] property: " +  evt.getPropertyName() + " (" + propertyName + ")");
            return false;
        }
        if (evt.getSource() != source) {
            fail("[fail(" + index + ")] source: " +  evt.getSource() + " (" + source + ")");
            return false;
        }

        if (evt.getOldValue() == null && oldValue != null) {
            fail("[fail(" + index + ")] oldValue: " +  evt.getOldValue() + " (" + oldValue + ")");
            return false;
        } else if (evt.getOldValue() != null && !equals(oldValue, evt.getOldValue())) {
            fail("[fail(" + index + ")] oldValue: " +  evt.getOldValue() + " (" + oldValue + ")");
            return false;
        }

        if (!equals(newValue, evt.getNewValue())) {
            fail("[fail] newValue: " +  evt.getNewValue() + " (" + newValue + ")");
            return false;
        }
        return true;
    }
}

public class Object2DTest {


    @SuppressWarnings("checkstyle:MagicNumber")
    @Test
    @DisplayName("Object2D")
    void testObject2D() {

        Object2D o = new Object2D(4, 5);
        assertEquals(4.0, o.getWidth());
        assertEquals(5.0, o.getHeight());

        // no functionality expected here other than public method exists
        o.updateObject(1);

        assertNotNull(o.getVelocity());
        assertEquals(0, o.getX());
        assertEquals(0, o.getY());
        assertTrue(o.getAge() > 0);

        assertTrue(o.getAge(System.nanoTime()) > 0);

        o.setX(100);
        assertEquals(100, o.getX());
        o.setY(100);
        assertEquals(100, o.getY());

        o.setVelocity(1, 1);
        assertEquals(new Vector2D(1, 1), o.getVelocity());
    }


    @Test
    @DisplayName("Object2D with owning World2D")
    void testObject2DwithOwningWorld2D() {

        Object2D o = new Object2D(0, 0);

        o.setWorld(new TestWorld2D());

        assertNotNull(o.getVelocity());
        assertEquals(0, o.getX());
        assertEquals(0, o.getY());

        o.setX(100);
        assertEquals(100, o.getX());
        o.setY(200);
        assertEquals(200, o.getY());

        o.setXY(300, 400);
        assertEquals(300, o.getX());
        assertEquals(400, o.getY());

        o.setVelocity(1, 2);
        assertTrue(o.getVelocity().equals(new Vector2D(1, 2)));

        o.setWorld(new TestWorld2D());
        assertTrue(o.getVelocity().equals(new Vector2D(1, 2)));
        assertEquals(300, o.getX());
        assertEquals(400, o.getY());

        o.setWorld(null);
        assertNotNull(o.getVelocity());
        assertEquals(300, o.getX());
        assertEquals(400, o.getY());
    }


    @Test
    @DisplayName("PropertyChange")
    void testPropertyChange() {

        Object2D o = new Object2D(0, 0);

        TestPropertyChangeListener p = new TestPropertyChangeListener();

        o.addPropertyChangeListener(p);

        World2D w = new TestWorld2D();
        o.setWorld(w);
        assertTrue(p.eventMatches(0, "world", o, null, w));

        o.setXY(1, 2);
        assertTrue(p.eventMatches(1, "x", o, 0d, 1d));
        assertTrue(p.eventMatches(2, "y", o, 0d, 2d));

        Vector2D oldV = o.getVelocity().clone();
        o.setVelocity(4, 5);

        assertTrue(p.eventMatches(3, "velocity", o, oldV, new Vector2D(4, 5)));

    }

    @Test
    @DisplayName("createdAt")
    void testCreatedAt() {

        Object2D o = new Object2D(0 ,0);

        assertTrue(o.createdAt() <= System.nanoTime());
    }

}


