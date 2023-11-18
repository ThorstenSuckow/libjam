package dev.libjam.physx;

import dev.libjam.math.Vector2D;
import dev.libjam.physx.event.Object2DAddedEvent;
import dev.libjam.physx.event.Object2DRemovedEvent;
import dev.libjam.physx.event.World2DEvent;
import dev.libjam.physx.event.World2DEventListener;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;


/**
 * Represents an abstract world that can contain other objects under the
 * influence of this worlds physics.
 * A World2D's owning world is always the World2D itself if no other
 * owning World2D is specified.
 */
public abstract class World2D extends Object2D implements PropertyChangeListener {

    protected List<Object2D> objects = new ArrayList<Object2D>();

    protected List<World2DEventListener> world2DEventListeners = new ArrayList<World2DEventListener>();


    /**
     * Creates a new World2D with its World2D's coordinates to 0, and
     * its velocity to 0.
     *
     */
    public World2D () {
        this(0, 0);
    }


    /**
     * Creates a new World2D with the specified width and height.
     *
     * @param width the specified width of this World2D.
     * @param height the specified height of this World2D.
     */
    public World2D(int width, int height) {
        super(width, height);
        world = this;
        this.x = 0;
        this.y = 0;
        this.velocity = new Vector2D(0, 0);
    }


    /**
     * Adds the specified Object2D to this World2D and registers
     * this World2D as its PropertyChangeListener. Ths World2D will be
     * set as the owning world of the specified Object2D.
     *
     * @param obj The specified Object2D
     */
    public void addObject(final Object2D obj) {
        if (!objects.contains(obj)) {
            objects.add(obj);
            obj.addPropertyChangeListener(this);
            obj.setWorld(this);
            fireEvent(new Object2DAddedEvent(this, obj));
        }
    }


    /**
     * Removes the specified Object2D from this World2D, also
     * removing this World2D's as the Object2D's PropertyChangeListener.
     *
     * @param obj The specified Object2D to remove.
     */
    void removeObject(final Object2D obj) {
        if (objects.contains(obj)) {
            objects.remove(obj);
            obj.removePropertyChangeListener(this);
            fireEvent(new Object2DRemovedEvent(this, obj));
        }
    }


    /**
     * Updates this world along with its child objects in regard to arbitrary data or behavior.
     *
     * @param time The specified point in time (in nanoseconds)
     *            at which this World2D should be updated.
     */
    public void updateObject(long time) {

        int len = objects.size();
        for (int i = 0; i < len; i++) {
            objects.get(i).updateObject(time);
        }
    }


    /**
     * Updates this world along with its child objects.
     *
     * @param time The specified point in time (in nanoseconds)
     *            at which this World2D should be updated.
     */
    public abstract void updateWorld(long time);


    /**
     * Observer for PropertyChangeEvents this World2D is observing.
     *
     * @param evt A PropertyChangeEvent object describing the event source
     *          and the property that has changed.
     */
    public void propertyChange(final PropertyChangeEvent evt) {

        Object2D obj = (Object2D)evt.getSource();
        if (evt.getPropertyName().equals("world")) {
            World2D oldWorld = (World2D) evt.getOldValue();
            if (oldWorld == this) {
                removeObject(obj);
            }
        }
    }


    /**
     * Checks if the specified world is equal to this World2D.
     *
     * @param w The specified world.
     *
     * @return true if the specified world is considered to be equal to this world.
     */
    public boolean equals(final Object w) {
        if (!(w instanceof World2D t)) {
            return false;
        }
        return this == w;
    }


    /**
     * Fires the specified World2DEvent.
     *
     * @param evt The specified World2DEvent.
     */
    private void fireEvent(final World2DEvent evt) {
        int len = world2DEventListeners.size();
        if (evt instanceof Object2DAddedEvent addEvt) {
            for (int i = 0; i < len; i++) {
                world2DEventListeners.get(i).object2DAdded(addEvt);
            }
        } else if (evt instanceof Object2DRemovedEvent removedEvt) {
            for (int i = 0; i < len; i++) {
                world2DEventListeners.get(i).object2DRemoved(removedEvt);
            }

        }
    }


    /**
     * Adds the specified World2DEventListener to this World2D.
     *
     * @param wel the specified World2DEventListener
     */
    public void addWorld2DEventListener(final World2DEventListener wel) {
        world2DEventListeners.add(wel);
    }


    /**
     * Removes the specified Worl2DEventListener from this World2D.
     *
     * @param wel The specified World2DEventListener to remove from this world.
     */
    public void removeWorld2DEventListener(final World2DEventListener wel) {
        world2DEventListeners.remove(wel);
    }


}
