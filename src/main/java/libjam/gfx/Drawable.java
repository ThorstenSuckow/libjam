package libjam.gfx;

import java.awt.Graphics;

/**
 * Contract for a drawable object.
 */
public interface Drawable {


    /**
     * Draw given the specified Graphics-context.
     *
     * @param g The Graphics-context.
     * @param canvasContext The new CanvasContext to consider by this Drawable.
     */
    void draw(Graphics g, CanvasContext canvasContext);

}
