package dev.libjam.physx.event;

/**
 * Observer-interface for World2DEvents.
 */
public interface World2DEventListener {

    /**
     * Gets called from implementing classed when the specified Object2DAddedEvent
     * occured.
     *
     * @param evt The specified Object2DAddedEvent.
     */
    void object2DAdded(Object2DAddedEvent evt);


    /**
     * Gets called from implementing classed when the specified Object2DRemovedEvent
     * occured.
     *
     * @param evt The specified Object2DRemovedEvent.
     */
    void object2DRemoved(Object2DRemovedEvent evt);


}
