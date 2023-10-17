package libjam.gfx;


/**
 * The CanvasContext provides information about the current Canvas and its metrics.
 * The purpose of this class is to pass information about the current dimensions of
 * a given Canvas to Drawables, providing information about potential rendering bounds.
 *
 */
public class CanvasContext {

    /**
     * The width of the Canvas represented by this Context-object.
     */
    private int width;

    /**
     * The height of the Canvas represented by this Context-object.
     */
    private int height;


    /**
     * Createa a new CanvasContext.
     *
     * @param width The pixel-width of the represented Canvas.
     * @param height The pixel-height of the represented Canvas.
     */
    public CanvasContext(final int width, final int height) {
        this.width = width;
        this.height = height;
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
}
