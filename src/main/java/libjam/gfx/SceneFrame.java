package libjam.gfx;

import libjam.math.Rectangle;


/**
 * A SceneFrame provides metrics for the location and dimension that should be used for observing an area.
 */
public class SceneFrame extends Rectangle {


    /**
     * Creates a new SceneFrame for the specified x/y-coordinates and the specified with and height.
     *
     * @param x The x-coordinate of the scene.
     * @param y The y-coordinate of the scene.
     * @param width The width of the scene.
     * @param height The height of the scene.
     */
    public SceneFrame(
        final double x,
        final double y,
        final double width,
        final double height
    ) {
        super(x, y, width, height);
    }
}
