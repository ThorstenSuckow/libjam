package libjam.gfx.event;

import java.util.EventObject;

public class ResizeEvent  extends EventObject {


    int oldWidth;

    int oldHeight;

    int newWidth;

    int newHeight;

    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public ResizeEvent(Object source, int oldWidth, int oldHeight, int newWidth, int newHeight) {

        super(source);

        this.oldWidth = oldWidth;
        this.oldHeight = oldHeight;

        this.newHeight = newHeight;
        this.newWidth = newWidth;
    }

    public int getOldHeight() {
        return oldHeight;
    }

    public int getNewHeight() {
        return newHeight;
    }

    public int getOldWidth() {
        return oldWidth;
    }

    public int getNewWidth() {
        return newWidth;
    }
}