package libjam.physx;

import libjam.math.Vector;
import libjam.physx.event.WorldChangeListener;
import libjam.physx.event.WorldEvent;
import libjam.physx.event.WorldObjectEnterEvent;

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
    public World(final int width, final int height, double worldScale) {
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

    public double getWorldScale() {
        return worldScale;
    }

    public double startY = 200;

    public double frac = 1;


    /**
     *
     */
    public synchronized void syncObjects() {

        if (startedAt == 0) {
            startedAt = System.currentTimeMillis();
        }

        long now = System.currentTimeMillis();

        double secondsSinceFirstImpulse = (now - startedAt) / 1000d;

        double yDistance = startY;

        double yVelocity = 0;

        Vector vY;




        for (WorldObject obj: worldObjects) {

            vY = obj.getYVector();

            // update velocity according to v = v_0 + g*t (v_0 = initial speed, g = gravity, t = fall time)


            if (vY.getAt(1) > 0) {
              //  yVelocity = Gravity.getVelocityAtTime(secondsSinceFirstImpulse);
                //yDistance = startY + Gravity.getDistanceTravelled(yVelocity);
               // vY.setAt(1, yVelocity);
            } else if (vY.getAt(1) < 0) {
              //  yDistance = obj.getY() + vY.getAt(1);
              //  vY.setAt(1,  vY.getAt(1) - frac);


               // vY.setAt(1, -1-Gravity.getVelocityAtTime(secondsSinceFirstImpulse));

               // System.out.println(frac + Gravity.getVelocityAtTime(secondsSinceFirstImpulse));
            //    vY.setAt(1, frac + Gravity.getVelocityAtTime(secondsSinceFirstImpulse));
          //      yDistance = getHeight() +
            //            Math.min(
              //                  -1, (
                //        frac * secondsSinceFirstImpulse + Gravity.getDistanceAtTime(secondsSinceFirstImpulse)
                //));// Gravity.getDistanceTravelled(vY.getAt(1));

            }



            // Logger.log("Factor: " + factor + "; Time (seconds):" + (((fallingSince))) + "; velocity: " + vY.getAt(1) + "; meters travelled: " + (yDistance / worldScale) + "; pixels travelled: " + (yDistance));

            if (yDistance + obj.getHeight() >= getHeight() && vY.getAt(1) > 0) {

                obj.setY(getHeight() - obj.getHeight());
                obj.setXVector(Vector.from(0, 0));
                vY.setAt(0, 0);
                frac = -yVelocity * obj.getElasticity();
                vY.setAt(1, -yVelocity * obj.getElasticity());
                startedAt = 0;
                lastUpdate = 0;

            } else if (yDistance != 0) {

                obj.setY(yDistance);
                lastUpdate = System.currentTimeMillis();
            }


        }


    }


}
