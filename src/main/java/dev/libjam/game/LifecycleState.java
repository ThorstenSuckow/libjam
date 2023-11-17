package dev.libjam.game;


/**
 * LifecycleState represents the current state of an Object
 * to a given point in time.
 */
public enum LifecycleState {

    /**
     * The object is currently being prepared. This state
     * indicates that the object is not yet ready for rendering,
     * and was not rendered.
     */
    PREPARING,

    /**
     * This state indicates that an object is spawning. This means it
     * can be rendered or has already been rendered.
     */
    SPAWNING,

    /**
     * This state indicates than an object is neither SPAWNING nor
     * EXPIRING, i.e., it's rendered.
     */
    LIVING,

    /**
     * EXPIRING indicates that an object is about to be removed. This
     * state can be vetoed before the object's state is VOID.
     */
    EXPIRING,

    /**
     * The object is removed and cannot be reconstructed for rendering,
     * ready to be picked up by teh GC.
     */
    VOID

}
