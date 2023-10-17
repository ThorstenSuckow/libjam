package libjam.gfx.event;

import java.util.EventObject;

public class CameraEvent  extends EventObject {

    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public CameraEvent(Object source) {
        super(source);
    }
}