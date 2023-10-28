package dev.libjam.gfx.drawable;

/**
 * Contract for a GfxNode.
 * A GfxNode represents a GfxDrawable that is related to an ancestor, its GfxParent.
 */
public interface GfxNode extends GfxDrawable {


    /**
     * @return The parent of this node.
     */
    GfxParent getParent();

    /**
     * Sets the parent of this GfxDrawableNode to the specified parent.
     *
     * @param parent The specified parent.
     *
     * @return this GfxDrawableNode.
     */
    GfxNode setParent(GfxParent parent);


}
