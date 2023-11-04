package dev.libjam.gfx.drawable;


/**
 * A GfxRoot is a GfxDrawable and the root element of a GfxParent/Node hierarchy.
 */
public interface GfxRoot extends Parent {


    /**
     * Requests to draw this GfxRoot along with its children. It is up to the
     * implementing API to decide which GraphicContext to use.
     */
    void draw();

    /**
     *@return the width of this GfxRoot.
     */
    double getWidth();

    /**
     * @return The height of this GfxRoot.
     */
    double getHeight();

}
