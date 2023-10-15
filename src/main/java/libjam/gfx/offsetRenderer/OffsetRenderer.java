package libjam.gfx.offsetRenderer;

import libjam.gfx.CanvasContext;
import libjam.gfx.Drawable;

public abstract class OffsetRenderer implements Drawable {

    public final static int LEFT = 0;

    public final static int BOTTOM = 1;


    private final int offset;
    private final int position;

    private CanvasContext canvasContext;

    /**
     *
     * @param offset offset-size in pixels
     * @param position height of bottom offsets
     */
    public OffsetRenderer(int offset, int position) {
        this.offset = offset;
        this.position = position;
    }


    public int getPosition() {
        return position;
    }

    public int getOffset() {
        return offset;
    }

    public void setCanvasContext(CanvasContext canvasContext) {
        this.canvasContext = canvasContext;
    }

    public CanvasContext getCanvasContext() {
        return canvasContext;
    }
}
