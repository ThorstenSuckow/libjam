package dev.libjam.gfx.drawable;

import java.util.List;

/**
 * A GfxDrawableParent is a GfxDrawable capable of maintaining child nodes.
 */
public interface GfxDrawableParent extends GfxDrawableNode {

    /**
     * @return The list of GfxDrawables of this GfxDrawableParent.
     */
    List<GfxDrawableNode> getChildren();

}
