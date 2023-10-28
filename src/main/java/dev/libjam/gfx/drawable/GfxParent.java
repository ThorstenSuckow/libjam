package dev.libjam.gfx.drawable;

import java.util.List;

/**
 * A GfxParent is a GfxNode capable of maintaining children.
 */
public interface GfxParent extends GfxNode {

    /**
     * Returns a copy of the list of children maintained by this GfxParent.
     *
     * @return The list of GfxDrawables of this GfxDrawableParent.
     */
    List<GfxNode> getChildren();

    /**
     * Adds the specified node to this GfxParent and makes sure the GfxNode's parent
     * is updated to this GfxParent.
     *
     * @param node the specified GfxNode.
     *
     * @return this GfxParent.
     *
     * @throws IllegalArgumentException if the specified GfxNode is already owned by a parent.
     */
    GfxParent add(GfxNode node) throws IllegalArgumentException;

}
