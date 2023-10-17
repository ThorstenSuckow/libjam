package libjam.gfx.renderer.offset;

import libjam.gfx.CanvasContext;
import libjam.gfx.Drawable;

public abstract class OffsetRenderer implements Drawable {

    private CanvasContext canvasContext;


    public void setCanvasContext(CanvasContext canvasContext) {
        this.canvasContext = canvasContext;
    }

    public CanvasContext getCanvasContext() {
        return canvasContext;
    }
}
