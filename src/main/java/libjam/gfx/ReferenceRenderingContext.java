package libjam.gfx;

import libjam.util.Unit;

public class ReferenceRenderingContext implements RenderingContext{

    private ScaledSceneFrame sceneFrame;

    private CanvasContext canvasContext;

    private int offsetLeft;

    private int offsetBottom;

    public ReferenceRenderingContext(final ScaledSceneFrame sceneFrame) {
        this.sceneFrame = sceneFrame;
    }

    public void setCanvasContext(CanvasContext canvasContext) {
        this.canvasContext = canvasContext;
    }

    @Override
    public CanvasContext getCanvasContext() {
        return canvasContext;
    }

    @Override
    public ScaledSceneFrame getScaledSceneFrame() {
        return sceneFrame;
    }

    @Override
    public ReferenceRenderingContext setScaledSceneFrame(ScaledSceneFrame sceneFrame) {
        this.sceneFrame = sceneFrame;
        return this;
    }


    @Override
    public double getScalingFactor() {
        return getScaledSceneFrame().getScalingFactor();
    }


    public void setOffsetLeft(int offsetLeft) {
        this.offsetLeft = offsetLeft;
    }

    @Override

    public int getOffsetLeft() {
        return offsetLeft;
    }

    public void setOffsetBottom(int offsetBottom) {
        this.offsetBottom = offsetBottom;
    }

    @Override

    public int getOffsetBottom() {
        return offsetBottom;
    }

    @Override
    public int toX(double x) {

        int zero = getOffsetLeft() - (int) sceneFrame.getX();

        return zero + (int) Unit.toPixel(x, getScalingFactor());

    }

    @Override
    public int toY(double y) {

        int zero = getCanvasContext().getHeight() - getOffsetBottom();

        return zero - (int) Unit.toPixel(y, getScalingFactor()) + (int) sceneFrame.getY();
    }

    @Override
    public int toWidth(double width) {
        return Unit.toPixel(width, getScalingFactor());
    }


    @Override
    public int toHeight(double height) {
        return Unit.toPixel(height, getScalingFactor());
    }

}
