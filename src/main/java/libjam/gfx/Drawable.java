package libjam.gfx;

import java.awt.Graphics;

public interface Drawable {


    /**
     * Draw given the specified Graphics-context.
     *
     * @param g The Graphics-context.
     */
    void draw(Graphics g);


    /**
     * Sets the CanvasContext for this Drawable. If the drawable maintains
     * drawable objects, the new CanvasContext should be forwarded so they
     * can update themselves with the new metrics provided accordingly.
     *
     * @param canvasContext The new CanvasContext to consider by this Drawable.
     */
    void setCanvasContext(CanvasContext canvasContext);
}
