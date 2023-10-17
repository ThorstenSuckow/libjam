package libjam.physx.physics;


import libjam.math.Rectangle;
import libjam.physx.World;
import libjam.physx.WorldObject;
import libjam.util.Logger;

/**
 * A FrameOfReference serves as an observator for a libjam.physx.World
 *
 * @see <a href="https://en.wikipedia.org/wiki/Frame_of_reference">https://en.wikipedia.org/wiki/Frame_of_reference</a>
 */
public class FrameOfReference {

    /**
     * The dimensions of the observed frame, in meters
     */
    private Rectangle observedFrame;


    /**
     * The observed world.
     */
    private World world;


    /**
     * @param observedFrame The initial dimensions of the frame to observe, units in meters
     */
    public FrameOfReference(final Rectangle observedFrame) {
        this.observedFrame = observedFrame;
    }


    /**
     * Commands this FrameofReference to observe the specified World.
     *
     * @param world the world to observe
     * @return
     */
    public FrameOfReference observe(final World world) {
        this.world = world;

        if (observedFrame.getWidth() > world.getWidth()) {
            setObservedWidth(world.getWidth());
        } else {
            setObservedWidth(Math.min(world.getWidth(), observedFrame.getWidth()));
        }

        Logger.log("World-width: " + world.getWidth() + "; frame-width: " + observedFrame.getWidth());

        if (observedFrame.getHeight() > world.getHeight()) {
            setObservedHeight(world.getHeight());
        } else {
            setObservedHeight(Math.min(world.getHeight(), observedFrame.getHeight()));
        }

        Logger.log("World-height: " + world.getHeight() + "; frame-height: " + observedFrame.getHeight());

        if (getObservedX() >= world.getWidth()) {
            throw new RuntimeException("observed X exceeds world's width");
        }

        return this;
    }


    /**
     * Returns the observed world.
     * @return
     */
    public World getObservedWorld() {
        return world;
    }


    /**
     * Returns the x-coordinate of the observed frame in meters.
     * @return
     */
    public double getObservedX() {
        return observedFrame.getX();
    }


    /**
     * Returns the y-coordinate of the observed frame in meters.
     * @return
     */
    public double getObservedY() {
        return observedFrame.getY();
    }


    /**
     * Returns the height of the observed frame in meters.
     * @return
     */
    public double getObservedHeight() {
        return observedFrame.getHeight();
    }


    /**
     * Returns the width of the frame in meters.
     * @return
     */
    public double getObservedWidth() {
        return observedFrame.getWidth();
    }


    /**
     * Returns the observed frame as a Rectangle.
     *
     * @return The observed frame as a Rectangle, its units in meters.
     */
    public Rectangle getObservedFrame() {
        return observedFrame;
    }


    /**
     * Sets the observed height.
     *
     * @param newHeight in meters
     */
    public void setObservedHeight(double newHeight) {
        this.observedFrame.setHeight(newHeight);
    }


    /**
     * Sets the observed width.
     *
     * @param newWidth in meters
     */
    public void setObservedWidth(double newWidth) {

        this.observedFrame.setWidth(newWidth);
    }


    /**
     * Sets the observed width.
     *
     * @param newX in meters
     */
    public void setObservedX(double newX) {
        this.observedFrame.setX(newX);
    }


    /**
     * Sets the observed width.
     *
     * @param newY in meters
     */
    public void setObservedY(double newY) {
        this.observedFrame.setY(newY);
    }


    /**
     * Returns true if this FrameOrReference is able to observe the specified
     * WorldObject, i.e. if the obj is within the observedFrame with any of its
     * area or coordinates.
     *
     * @param obj The obj that should be checked for observation.
     *
     * @return True if this FrameOfReference can observe the specified WorldObject,
     * otherwise false.
     */
    public boolean canObserve(final WorldObject obj) {
        Rectangle rect = obj.getRectangle();
        return observedFrame.contains(rect) || observedFrame.intersects(rect);
    }

}
