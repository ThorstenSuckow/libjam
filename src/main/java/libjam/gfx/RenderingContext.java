package libjam.gfx;

/**
 * A RenderingContext provides information about metrics used by a top-level renderer
 * that should be made available to lower-level renderers, including scaling factors,
 * boundaries, offsets and coordinates.
 */
public interface RenderingContext {

    /**
     * @return the CanvasContext this RenderingContext is part of.
     */
    CanvasContext getCanvasContext();


    /**
     * @return the ScaledSceneFrame this RenderingContext uses.
     */
    ScaledSceneFrame getScaledSceneFrame();


    /**
     * Sets this ScaledSceneFrame of this RenderingContext to the specified ScaledSceneFrame.
     *
     * @param sceneFrame the specified ScaledSceneFrame
     *
     * @return this RenderingContext
     */
    RenderingContext setScaledSceneFrame(final ScaledSceneFrame sceneFrame);


    /**
     * @return the scalingFactor available with this RenderingContext.
     */
    double getScalingFactor();


    /**
     * Returns an x-position value in the world translated to a pixel value by this
     * Context.
     *
     * @param x An x-value of the world that should be translated to a pixel value.
     *
     * @return the translated pixel value.
     */
    int toX(double x);


    /**
     * Returns a y-position value in the world translated to a pixel value by this
     * Context.
     *
     * @param y A y-value of the world that should be translated to a pixel value.
     *
     * @return the translated pixel value.
     */
    int toY(double y);


    /**
     * Returns a width-value in the world translated to a pixel value by this
     * Context.
     *
     * @param width A width-value of the world that should be translated to a pixel value.
     *
     * @return the translated pixel value.
     */
    int toWidth(double width);


    /**
     * Returns a height-value in the world translated to a pixel value by this
     * Context.
     *
     * @param height A height-value of the world that should be translated to a pixel value.
     *
     * @return the translated pixel value.
     */
    int toHeight(double height);


}
