package physx;

import math.Vector;
import physx.event.WorldChangeListener;
import physx.event.WorldEvent;
import physx.event.WorldObjectEnterEvent;
import util.Logger;

import java.util.ArrayList;
import java.util.List;

public class World {


    protected long lastUpdate;

    protected int width;

    protected int height;

    /**
     * Provides information about the scale of this world, measured in pixels.
     * 100 is calculated as 100 pixel = 1 m = 100 cm.
     */
    private double worldScale = 1;

    /**
     * The list of worldObjects in this world.
     */
    private final List<WorldObject> worldObjects = new ArrayList<>();

    /**
     * Listeners observing this world.
     */
    private final List<WorldChangeListener> worldChangeListeners = new ArrayList<>();

    /**
     * Creates a new instance of this world.
     *
     * @param width The width for this world.
     * @param height The height for this world.
     */
    public World(final int width, final int height, int worldScale) {
        this.worldScale = worldScale;
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }



    public List<WorldObject> getObjects() {
        return worldObjects;
    }


    /**
     * Adds a new object to this world.
     *
     * @param worldObject The object to add.
     * @param x The x pos of the object.
     * @param y The y position of this object.
     *
     * @return this
     */
    public World add(final WorldObject worldObject, final int x, final int y) {
        worldObject.setXY(x, y);
        worldObjects.add(worldObject);
        
        this.fireEvent(new WorldObjectEnterEvent(worldObject));

        return this;
    }


    /**
     * Adds a new listener for WorldEvents to this instance.
     *
     * @param listener
     * @return
     */
    public World addWorldChangeListener(final WorldChangeListener listener) {

        if (worldChangeListeners.contains(listener)) {
            return this;
        }

        worldChangeListeners.add(listener);
        return this;
    }

    private void fireEvent(final WorldEvent evt) {

        for (WorldChangeListener listener: worldChangeListeners) {
            listener.onWorldObjectEnter((WorldObjectEnterEvent) evt);
        }
    }

    private long startedAt = 0;

    /**
     *
     */
    public synchronized void syncObjects() {

        long now =  System.currentTimeMillis();

        long diffToPreviousInvocation = lastUpdate == 0 ? 0 : now - lastUpdate;

        // in m/s²
        double acceleration = 9.81 * worldScale;

        double factor = (diffToPreviousInvocation/1000d);

        Logger.log("" + factor);

        double yDistance = 0;

        double yVelocity;
        double fallingSince;

        for (WorldObject obj: worldObjects) {

            Vector v = obj.getVector();

            if (v.equals(Vector.NULL_VECTOR)) {
                return;
            }

            // update velocity according to v = v_0 + g*t (v_0 = initial speed, g = gravity, t = fall time)
            yVelocity = v.getAt(1) + (factor) * acceleration;
            v.setAt(1, yVelocity);

            fallingSince = yVelocity / acceleration;

            yDistance = (acceleration * (fallingSince * fallingSince)) / 2d;

            Logger.log("Factor: " + factor + "; Time (seconds):" + (((fallingSince))) + "; velocity: " + v.getAt(1) + "; meters travelled: " + (yDistance / worldScale) + "; pixels travelled: " + (yDistance));

            if (obj.getY() + yDistance >= getHeight()) {

                obj.setY(getHeight() - obj.getHeight());
                obj.setVector(Vector.from(0, 0));
            } else {
                obj.setY(obj.getY() + yDistance);
            }


        }

        lastUpdate = System.currentTimeMillis();
    }


}
