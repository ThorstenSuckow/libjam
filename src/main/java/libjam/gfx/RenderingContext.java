package libjam.gfx;

/**
 * A RenderingContext provides information about metrics used by a top-level renderer
 * that should be made available to lower-level renderers that are generally not aware
 * of such metrics, such as scaling factors, boundaries and offsets and coordinates.
 */
public interface RenderingContext {

    /**
     * @return the CanvasContext this RenderingContext is part of.
     */
    CanvasContext getCanvasContext();

    /**
     * @return the RenderingDimensionsFrame this Context is coupled with.
     */
    ScaledSceneFrame getScaledSceneFrame();

    /**
     * @return the RenderingDimensionsFrame this Context is coupled with.
     */
    RenderingContext setScaledSceneFrame(ScaledSceneFrame sceneFrame);

    /**
     * Provides information about the scalingFactor this context uses.
     * Underlying renderers can align their scaling to this factor.
     *
     * @return the scalingFactor used by this RenderingContext.
     */
    double getScalingFactor();


    /**
     * Returns the left offset in pixels that is not safe to use by this
     * rendering context.
     *
     * @return the left offset in pixels.
     */
    int getOffsetLeft();


    /**
     * Returns the bottom offset in pixels that is not safe to use by this
     * rendering context.
     *
     * @return the left offset in pixels.
     */
    int getOffsetBottom();


    /**
     * Returns a x-position value in the world translated to a pixel value by this
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
     * @param y An y-value of the world that should be translated to a pixel value.
     *
     * @return the translated pixel value.
     */
    int toY(double y);


    /**
     * Returns a width-value in the world translated to a pixel value by this
     * Context.
     *
     * @param width An width-value of the world that should be translated to a pixel value.
     *
     * @return the translated pixel value.
     */
    int toWidth(double width);


    /**
     * Returns a height-value in the world translated to a pixel value by this
     * Context.
     *
     * @param height An height-value of the world that should be translated to a pixel value.
     *
     * @return the translated pixel value.
     */
    int toHeight(double height);


}
