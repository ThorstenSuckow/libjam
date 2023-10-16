package libjam.gfx;

import libjam.util.Unit;

import java.awt.geom.Rectangle2D;

public class ReferenceRenderingContext {

    private Rectangle2D.Double observedFrame;


    private double scale;

    private CanvasContext canvasContext;

    private int offsetLeft;

    private int offsetBottom;

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


    public void setOffsetLeft(int offsetLeft) {
        this.offsetLeft = offsetLeft;
    }

    public int getOffsetLeft() {
        return offsetLeft;
    }

    public void setOffsetBottom(int offsetBottom) {
        this.offsetBottom = offsetBottom;
    }

    public int getOffsetBottom() {
        return offsetBottom;
    }

    public int toX(double worldX) {

        int zero = getOffsetLeft() - Unit.toPixel(observedFrame.getX(), scale);

        return zero + (int) Unit.toPixel(worldX, scale);

    }

    public int toY(double worldY) {

        int zero = getCanvasContext().getHeight() - getOffsetBottom();

        return zero - (int) Unit.toPixel(worldY, scale) + Unit.toPixel(observedFrame.getY(), scale);
    }

    public int toWidth(double worldWidth) {
        return Unit.toPixel(worldWidth, scale);
    }

    public int toHeight(double worldHeight) {
        return Unit.toPixel(worldHeight, scale);
    }

}
