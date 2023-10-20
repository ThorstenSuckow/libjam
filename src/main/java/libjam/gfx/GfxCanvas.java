package libjam.gfx;

import libjam.math.Rectangle;

import java.awt.Graphics;


public interface GfxCanvas {


    /**
     * @return the most recent CanvasContext representing this GfxCanvas.
     */
    CanvasContext getCanvasContext();


    /**
     * Instructs this GfxCanvas to refresh its metrics.
     * The associated CanvasContext should be updated if any requirements to do so are met, e.g., the implementing
     * component was resized.
     */
    void refresh();


    /**
     * Paints given the current graphic context.
     *
     * @param g the specified Graphics context
     */
    void paint(Graphics g);


    /**
     * @return The SafeArea of this GfxCanvas.
     */
    Rectangle getSafeArea();


    /**
     * Adds a new Drawable to this GfxCanvas.
     * @param drawable
     *
     * @return
     */
    GfxCanvas addDrawable(Drawable drawable);



}
