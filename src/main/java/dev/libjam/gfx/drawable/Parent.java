package dev.libjam.gfx.drawable;

import javafx.scene.canvas.GraphicsContext;

import java.util.List;

public interface Parent {

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
    Parent add(GfxNode node) throws IllegalArgumentException;


    /**
     * Draws the children associated with this GfxDrawable.
     *
     * @param g The GraphicsContext that should be used for rendering.
     * @return this GfxDrawable
     */
    Parent drawChildren(GraphicsContext g);


}
