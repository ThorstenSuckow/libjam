package dev.libjam.physx.event;

import dev.libjam.physx.World2D;

import java.util.EventObject;

/**
 * Abstract base class for World2DEvents.
 */
public abstract class World2DEvent extends EventObject {


    /**
     * Constructs a prototypical World2DEvent.
     *
     * @param source the World2D on which the Event initially occurred
     *
     * @throws IllegalArgumentException if source is null
     */
    public World2DEvent(final World2D source) {
        super(source);
    }



}
