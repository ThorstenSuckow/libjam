package dev.libjam.physx;

import dev.libjam.math.Vector2D;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;


/**
 * Object2D represents the model of a physical object in a world with two dimensions.
 * An instance of this class provides information about the object's location, dimensions
 * and velocity.
 */
public class Object2D {

    protected double x;

    protected double y;

    private long created;

    private double width;

    private double height;

    protected Vector2D velocity;

    protected World2D world;

    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);


    /**
     * Creates a new Object2D with a Velocity-vector with both components set to 0 and the Object2D's
     * location-coordinates set to 0.
     *
     * @param width The width of this Object2D.
     * @param height The height of this Object2D.
     */
    public Object2D(double width, double height) {
        this.x = 0;
        this.y = 0;
        velocity = new Vector2D(0, 0);
        this.width = width;
        this.height = height;
        created = System.nanoTime();
    }


    /**
     * Returns the time in nano-seconds when this Object2D was created.
     *
     * @return the time in nano-seconds when the Object2D was created.
     */
    public long createdAt() {
        return created;
    }


    /**
     * Returns the age of this Object2D since it was created relative to the specified time.
     *
     * @param time The specified time in nanoseconds
     *
     * @return The age of this Object2D in nanoseconds.
     */
    public long getAge(long time) {
        return time - created;
    }


    /**
     * Returns the age of this Object2D since its creation.
     *
     * @return The age of this Object2D in nanoseconds.
     */
    public long getAge() {
        return System.nanoTime() - created;
    }


    /**
     * Associates this Object2D with a World2D-instance. The specified World2D becomes the owner
     * of this Object2D.
     * Does nothing if the specified World2D is already this Object2D's owner, otherwise fires
     * a PropertyChangeEvent.
     * If null is specified, any existing World2D as the owning world of this object
     * will be removed.
     *
     * @param world The specified World2D owning this Object2D, or null to remove
     *              the current owning World2D.
     */
    public void setWorld(final World2D world) {
        World2D oldWorld = this.world;
        this.world = world;

        if (world == oldWorld) {
            return;
        }

        firePropertyChange("world", oldWorld, world);
    }


    /**
     * Returns the owning World2D instance of this Object2D.
     *
     * @return null if no World2D owns this Objects2D, otherwise the owning instance.
     */
    public World2D getWorld() {
        return world;
    }


    /**
     * Sets the x-/y-coordinates of this Object2D to the specified values.
     *
     * @param x The specified x-value
     * @param y The specified y-value
     */
    public void setXY(double x, double y) {
        setX(x);
        setY(y);
    }

    /**
     * Returns the desired x-coordinate of this Object2D relative to its owning world, if any.
     *
     * @return the desired x-coordinate of this Object2D relative to its owner.
     */
    public double getX() {
        return x;
    }


    /**
     * Sets the x-coordinate of this Objects2D, relative to its owning world.
     * Checks if the value has changed and fires a PropertyChangeEvent
     * accordingly
     *
     * @param x The desired x-coordinate of this Object2D relative to its owning world.
     */
    public void setX(double x) {

        double oldX = this.x;
        this.x = x;

        if (oldX == x) {
            return;
        }

        firePropertyChange("x", oldX, x);
    }


    /**
     * Returns the desired y-coordinate of this Object2D relative to its owning world, if any.
     *
     * @return the desired x-coordinate of this Object2D relative to its owner.
     */
    public double getY() {
        return y;
    }


    /**
     * Sets the desired y-coordinate of this Object2D, relative to its owning world, if any.
     * Checks if the value has changed and fires a PropertyChangeEvent accordingly.
     *
     * @param y The new y-coordinate of this Object2D relative to its owning world, if any.
     */
    public void setY(final double y) {

        double oldY = this.y;
        this.y = y;

        if (oldY == y) {
           return;
        }

        firePropertyChange("y", oldY, y);
    }


    /**
     * Sets the velocity for this Object2D. Fires a PropertyChangeEvent if the velocity has changed.
     *
     * @param x The value of the velocity vectors x-component.
     * @param y The value of the velocity vectors y-component.
     */
    public void setVelocity(final double x, final double y) {

        if (velocity.getX() == x && velocity.getY() == y) {
            return;
        }

        Vector2D oldVelocity = velocity.clone();

        velocity.setXY(x, y);
        firePropertyChange("velocity", oldVelocity, this.velocity);
    }


    /**
     * Returns the velocity of this object.
     *
     * @return The velocity vector for this Object2D. may be null.
     */
    public Vector2D getVelocity() {
        return velocity;
    }


    /**
     * Returns the width of this Object2D.
     *
     * @return the width of this Object2D.
     */
    public double getWidth() {
        return width;
    }


    /**
     * Returns the height of this Object2D.
     *
     * @return the height of this Object2D.
     */
    public double getHeight() {
        return height;
    }


    /**
     * Adds the specified PropertyChangeListener to this Object2D if it was not already registered
     * as a listener.
     *
     * @param listener The specified PropertyChangeListener.
     */
    public void addPropertyChangeListener(
        final PropertyChangeListener listener
    ) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }


    /**
     * Removes the specified PropertyChangeListener from the list of this Object2D's PropertyChangeListeners.
     *
     * @param listener The specified PropertyChangeListener to remove.
     */
    public void removePropertyChangeListener(
        final PropertyChangeListener listener
    ) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }


    /**
     * Wraps the attributes and fires them as a PropertyChangeEvent.
     *
     * @param property The specified attribute that has changed.
     * @param oldValue The old value of the specified property.
     * @param newValue The new value for the specified property.
     */
    private void firePropertyChange(
        final String property,
        final Object oldValue,
        final Object newValue
    ) {
        propertyChangeSupport.firePropertyChange(
                new PropertyChangeEvent(this, property, oldValue, newValue)
        );
    }


    /**
     * Allows this Object2D to be updated with arbitrary data.
     *
     * @param time The point in time where this Object2D should get updated.
     */
    public void updateObject(final long time) {
    }


    /**
     * Returns a String representation of this Object2D.
     *
     * @return A String representation of this Object2D
     */
    public String toString() {
        return "[" + getClass() + "]x: " + getX() + "; y: " + getY() + "; " + getWidth() + "; " + getHeight();
    }
}
