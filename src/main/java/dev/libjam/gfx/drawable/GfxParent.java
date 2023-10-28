package dev.libjam.gfx.drawable;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;

/**
 * A GfxParent is a GfxNode capable of maintaining children.
 */
public abstract class GfxParent extends GfxNode implements GfxDrawable, Parent {

    @SuppressWarnings("checkstyle:JavadocVariable")
    protected List<GfxNode> children = new ArrayList<GfxNode>();

    /**
     * Creates a new GfxDrawable with its height, width, x- and y-coordinate set to 0.
     */
    public GfxParent() {
        this(0, 0, 0, 0);
    }

    /**
     * Creates a new GfxDrawable with its width and height set to the specified values,
     * the x- and y-coordinate set to 0.
     *
     * @param width the specified width
     * @param height the specified height
     */
    public GfxParent(final int width, final int height) {
        this(0, 0, width, height);
    }

    /**
     * Creates a new GfxDrawable with its width, height, x- and y-coordinate
     * set to the specified values.
     *
     * @param width the specified width
     * @param height the specified height
     * @param x the specified x-coordinate
     * @param y the specified y-coordinate
     */
    public GfxParent(final double x, final double y, final double width, final double height) {
        this.width = new SimpleDoubleProperty(width);
        this.height = new SimpleDoubleProperty(height);

        this.x = new SimpleDoubleProperty(x);
        this.y = new SimpleDoubleProperty(y);

        visible = new SimpleBooleanProperty();
    }

    @Override
    public void draw(final GraphicsContext g) {

        if (!this.isVisible()) {
            return;
        }

        drawNode(g);
        drawChildren(g);
    }

    /**
     * Draws the children associated with this GfxDrawable.
     *
     * @param g The GraphicsContext that should be used for rendering.
     * @return this GfxDrawable
     */
    public GfxParent drawChildren(final GraphicsContext g) {
        for (GfxNode child: children) {
            child.draw(g);
        }
        return this;
    }

    /**
     * Returns a copy of the list of children maintained by this GfxParent.
     *
     * @return The list of GfxDrawables of this GfxDrawableParent.
     */
    public List<GfxNode> getChildren() {
        return new ArrayList<GfxNode>(children);
    }


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
    public GfxParent add(final GfxNode node) throws IllegalArgumentException {
        if (node.getParent() != null) {
            throw new IllegalArgumentException();
        }

        node.setParent(this);
        children.add(node);
        return this;
    }


}
