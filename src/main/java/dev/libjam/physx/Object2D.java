package dev.libjam.physx;

import dev.libjam.math.Vector2D;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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

    private final List<PropertyChangeListener> listeners = new ArrayList<>();


    /**
     * Creates a new Object2D with a null-vector for velocity and it's location-coordinates set to
     * x=-1, y=-1.
     *
     * @param width The width of this Object2D.
     * @param height The height of this Object2D.
     */
    public Object2D(double width, double height) {
        this.x = -1;
        this.y = -1;
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
     * Associates this Object2D with a World2D-instance. The specified World2D becomes the owner of this Object2D.
     * Does nothing if the specified World2D is already this Object2D's owner, otherwise fires a PropertyChangeEvent.
     * If no velocity exists for this Object2D, it will be created here.
     * If null is specified, any existing World2D as the owning world of this object
     * will be removed.
     *
     * @param world The specified World2D owning this Object2D, or null to remove
     *              the current owning World2D.
     */
    public void setWorld(World2D world) {
        World2D oldWorld = this.world;
        this.world = world;

        if (world != null) {
            x = y = -1;
            if (world != oldWorld) {
                velocity = new Vector2D(0, 0);
            }
        } else {
            velocity = null;
        }

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
     * Does nothing if this object has no owning World2D.
     *
     * @param x The specified x-value
     * @param y The specified y-value
     */
    public void setXY(double x, double y) {

        if (world == null) {
            return;
        }

        setX(x);
        setY(y);
    }

    /**
     * Returns the x-coordinate of this Object2D relative to its owning world.
     * Negative values indicate that this Object2D location is out of the bounds of its
     * owning world, or has no owning World2D at all. This method will always return -1 if this Object2 has no owner.
     *
     * @return the x-coordinate of this Object2D relative to its owner, -1 if no owning World2D exists.
     */
    public double getX() {
        return world != null ? x : -1;
    }


    /**
     * Sets the x-coordinate of this Objects2D, relative to its owning world. Will do nothing if this
     * Object2D has no owning World2D, otherwise checks if the value has changed and fire a PropertyChangeEvent
     * accordingly
     *
     * @param x The new x-coordinate of this Object2D relative to its owning world
     */
    public void setX(double x) {

        if (world == null) {
            return;
        }

        double oldX = this.x;
        this.x = x;

        if (oldX == x) {
            return;
        }

        firePropertyChange("x", oldX, x);
    }


    /**
     * Returns the y-coordinate of this Object2D relative to its owning world.
     * Negative values indicate that this Object2D location is out of the bounds of its
     * owning world, or has no owning World2D at all. This method will always return -1 if this Object2 has no owner.
     *
     * @return the x-coordinate of this Object2D relative to its owner, -1 if no owning World2D exists.
     */
    public double getY() {
        return world != null ? y : -1;
    }


    /**
     * Sets the y-coordinate of this Object2D, relative to its owning world. Will do nothing if this
     * Object2D has no owning World2D, otherwise checks if the value has changed and fire a PropertyChangeEvent
     * accordingly.
     *
     * @param y The new y-coordinate of this Object2D relative to its owning world
     */
    public void setY(double y) {

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
    public void setVelocity(double x, double y) {

        if (world == null) {
            return;
        }

        if (velocity.getX() == x && velocity.getY() == y) {
            return;
        }

        Vector2D oldVelocity = velocity.clone();

        velocity.setXY(x, y);
        firePropertyChange("velocity", oldVelocity, this.velocity);
    }


    /**
     * Returns the velocity of this object. Returns null if this Object2D has no owning World2D.
     *
     * @return The velocity vector for this Object2D, otherwise null if this Object2D has no owning World2D.
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
        if (listeners.contains(listener)) {
            return;
        }
        listeners.add(listener);
    }


    /**
     * Removes the specified PropertyChangeListener from the list of this Object2D's PropertyChangeListeners.
     *
     * @param listener The specified PropertyChangeListener to remove.
     */
    public void removePropertyChangeListener(
        final PropertyChangeListener listener
    ) {
        listeners.remove(listener);
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
        for (int i = 0; i < listeners.size(); i++) {
            listeners.get(i).propertyChange(new PropertyChangeEvent(this, property, oldValue, newValue));
        }
    }


    /**
     * Allows this Object2D to be updated with arbitrary data.
     *
     * @param time The point in time where this Object2D should get updated.
     */
    public void updateObject(long time) {
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
