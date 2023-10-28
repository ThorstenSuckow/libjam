package dev.libjam.gfx.drawable;


import java.util.List;

/**
 * A GfxRoot is a GfxDrawable and the root element of a GfxParent/Node hierarchy.
 */
public interface GfxRoot extends GfxDrawable{


    /**
     * Returns a copy of the list of children maintained by this GfxRoot.
     *
     * @return The list of GfxNodes of this GfxRoot.
     */
    List<GfxNode> getChildren();

}
