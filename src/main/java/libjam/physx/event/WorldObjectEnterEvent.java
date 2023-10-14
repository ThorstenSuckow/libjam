package libjam.physx.event;


import libjam.physx.WorldObject;

/**
 * Event to be fired when a new Object gets added to the World.
 */
public class WorldObjectEnterEvent extends WorldEvent {


    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public WorldObjectEnterEvent(WorldObject source) {
        super(source);
    }
}
