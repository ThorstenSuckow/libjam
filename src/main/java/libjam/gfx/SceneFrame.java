package libjam.gfx;

import libjam.math.Rectangle;


/**
 * A SceneFrame provides information about the cutout of a scene that should be considered when rendering this scene.
 * It provides x/y-coordinates and width/height-values for describing the regions of a scene that
 * should be considered for rendering.
 *
 * <pre>
 *     +-----------------------------+
 *     |     +----------------+      |
 *     |     |                |      |
 *     |     |                |      |
 *     |     +--SceneFrame----+      |
 *     |                             |
 *     +----------Scene--------------+
 * </pre>
 *
 *
 */
public class SceneFrame extends Rectangle {


    public SceneFrame(double x, double y, double width, double height) {
        super(x, y, width, height);
    }
}
