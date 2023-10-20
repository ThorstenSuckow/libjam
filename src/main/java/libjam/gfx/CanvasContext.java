package libjam.gfx;


import libjam.math.Rectangle;

/**
 * The CanvasContext provides information about the current Canvas and its metrics.
 * The purpose of this class is to pass information about the current dimensions of
 * a given Canvas to Drawables, providing information about potential rendering bounds.
 *
 */
public final class CanvasContext {


    @SuppressWarnings("checkstyle:JavadocVariable")
    private int width;


    @SuppressWarnings("checkstyle:JavadocVariable")
    private int height;


    @SuppressWarnings("checkstyle:JavadocVariable")
    private Rectangle safeArea;


    /**
     * Createa a new CanvasContext.
     *
     * @param width The pixel-width of the represented Canvas.
     * @param height The pixel-height of the represented Canvas.
     */
    @SuppressWarnings("checkstyle:HiddenField")
    public CanvasContext(final int width, final int height) {
        this.width = width;
        this.height = height;

        this.safeArea = new Rectangle(0, 0, width, height);
    }


    /**
     * @return The pixel-width of the Canvas represented by this Context-object.
     */
    public int getWidth() {
        return width;
    }


    /**
     * @return The pixel-height of the Canvas represented by this Context-object.
     */
    public int getHeight() {
        return height;
    }


    /**
     * Provides information about the area that can be used for underlying Renderers for drawing.
     *
     * @return the Rectangle considered the safeArea for underlying renderers.
     */
    public Rectangle getSafeArea() {
        return safeArea;
    };


    /**
     * Updates this safeArea with the location and dimension found in the specified Rectangle.
     *
     * @param rect the specified Rectangle.
     *
     * @return this CanvasContext.
     */
    public CanvasContext updateSafeArea(final Rectangle rect) {
        safeArea.updateFrom(rect);
        return this;
    }


}
