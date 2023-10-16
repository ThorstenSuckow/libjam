package libjam.gfx;

import java.awt.geom.Rectangle2D;

public class ReferenceRenderingContext {

    private Rectangle2D.Double observedFrame;


    private double scale;

    private CanvasContext canvasContext;

    public ReferenceRenderingContext(Rectangle2D.Double observedFrame, double scale) {
        this.observedFrame = observedFrame;
        this.scale = scale;
    }

    public void setCanvasContext(CanvasContext canvasContext) {
        this.canvasContext = canvasContext;
    }

    public CanvasContext getCanvasContext() {
        return canvasContext;
    }

    public Rectangle2D.Double getObservedFrame() {
        return observedFrame;
    }

    public double getScale() {
        return scale;
    }
}
