package dev.libjam.physx.event;

import dev.libjam.physx.Object2D;
import dev.libjam.physx.World2D;

/**
 * Class representing an event triggered when an Object2D was removed to
 * a World2D.
 */
public class Object2DRemovedEvent extends World2DEvent {

    private final Object2D object2D;


    /**
     * Constructs a Object2DRemovedEvent.
     *
     * @param source the World2D on which the Event initially occurred
     * @param obj the Object2D that was removed.
     *
     * @throws IllegalArgumentException if source is null
     */
    public Object2DRemovedEvent(final World2D source, final Object2D obj) {
        super(source);
        this.object2D = obj;
    }


    /**
     * Returns the Object2D removed from this event's World2D.
     *
     * @return The Object2D that was removed.
     */
    public Object2D getObject2D() {
        return object2D;
    }


    /**
     * Returns true if the specified Object equals to this Event.
     *
     * @param obj The specified Object that should be checked for equality.
     *
     * @return true if the specified Object is equal to this Event.
     */
    public boolean equals(final Object obj) {

        if (!(obj instanceof Object2DRemovedEvent evt)) {
            return false;
        }

        return evt.getSource() == source && evt.getObject2D() == object2D;
    }
}
