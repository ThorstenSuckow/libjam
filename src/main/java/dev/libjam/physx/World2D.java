package dev.libjam.physx;


import dev.libjam.game.event.ObjectLifecycleListener;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;



public abstract class World2D extends Object2D implements PropertyChangeListener {

    List<Object2D> objects = new ArrayList<Object2D>();

    private final List<ObjectLifecycleListener> objectLifecycleListeners = new ArrayList<>();


    public World2D(double x, double y, double width, double height) {
        super(x, y, width, height);

    }

    void removeObject(final Object2D obj) {
        if (objects.contains(obj)) {
            objects.remove(obj);
            obj.removeObject2DPropertyChangeListener(this);
        }

    }

    public void addObject(final Object2D obj) {
        if (!objects.contains(obj)) {
            objects.add(obj);
            obj.addObject2DPropertyChangeListener(this);
            obj.setWorld(this);
        }
    }

    /**
     * should return copy
     * @return
     */
    public List<Object2D> getObjects() {
        return objects;
    }

    public void updateObject(long time) {
        List<Object2D> objects = getObjects();

        for (int i = 0; i < getObjects().size(); i++) {
            objects.get(i).updateObject(time);
        }
    }

    public void updateWorld(long time) {

        Object2D obj;
       /* for (int i = 0; i < objects.size(); i++) {
            obj = objects.get(i);
            if (obj.isExpired(time)) {
                getObjects().remove(obj);
                this.fireObjectLifecycleEvent(obj, LifecycleType.EXPIRED);

            }
        }*/
    }

    public void object2DPropertyChange(final PropertyChangeEvent evt) {

        Object2D obj = (Object2D)evt.getSource();

        if (evt.getPropertyName().equals("world")) {
            World2D oldWorld = (World2D) evt.getOldValue();
            if (oldWorld == this) {
                removeObject(obj);
            }
        }

    }


}
