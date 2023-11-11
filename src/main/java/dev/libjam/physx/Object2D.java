package dev.libjam.physx;

import dev.libjam.math.Vector2D;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;


/**
 * Object2D represents the model of a physical object in a world with two dimensions.
 */
public class Object2D {

    private double x;
    private double y;

    private long created;
    private double width;
    private double height;
    private Vector2D velocity = new Vector2D(0, 0);

    private World2D world;

    private final List<PropertyChangeListener> listeners = new ArrayList<>();

    public Object2D(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.created = System.nanoTime();
    }


    public void setWorld(World2D world) {
        World2D oldWorld = this.world;
        this.world = world;

        if (world == oldWorld) {
            return;
        }

        firePropertyChange("world", oldWorld, world);
    }


    public World2D getWorld() {
        return world;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        double oldX = this.x;
        this.x = x;

        if (oldX == x) {
            return;
        }

        fireObject2DPropertyChange("x", oldX, x);
    }


    public double getY() {
        return y;
    }

    public void setY(double y) {

        double oldY = this.y;
        this.y = y;

        if (oldY == y) {
           return;
        }

        fireObject2DPropertyChange("y", oldY, y);
    }

    public void setVelocity(double x, double y) {

        if (velocity.getX() == x && velocity.getY() == y) {
            return;
        }

        Vector2D oldVelocity = velocity.clone();

        this.velocity.setXY(x, y);
        fireObject2DPropertyChange("velocity", oldVelocity, this.velocity);
    }

    public Vector2D getVelocity() {
        return velocity;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }


    public void addObject2DPropertyChangeListener(
        final PropertyChangeListener listener
    ) {
        listeners.add(listener);
    }

    public void removeObject2DPropertyChangeListener(
        final PropertyChangeListener listener
    ) {
        listeners.remove(listener);
    }


    private void fireObject2DPropertyChange(
        final String property,
        final Object oldValue,
        final Object newValue
    ) {
        for (PropertyChangeListener l : listeners) {
            l.propertyChange(new PropertyChangeEvent(this, property, oldValue, newValue));
        }
    }


    public long getAge(long time) {
        return time - created;
    }

    public long getAge() {
        return System.nanoTime() - created;
    }


    protected void updateObject(long time) {
    }
}
