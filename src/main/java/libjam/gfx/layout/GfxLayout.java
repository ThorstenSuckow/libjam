package libjam.gfx.layout;


import libjam.gfx.canvas.GfxCanvas;

/**
 * GfxLayout provides a contract for layout-managers for GfxCanvas.
 */
public interface GfxLayout {


    /**
     * Updates element-position and -sizing of the owning GfxCanvas according to the available constraints.
     */
    void updateLayout();

    /**
     * Sets the onwer of this GfxLayout to the specified GfxCanvas.
     *
     * @param canvas the specified GfxCanvas
     */
    boolean setOwner(GfxCanvas canvas);

    /**
     * @return the owning GfxCanvas.
     */
    GfxCanvas getOwner();

}
