package dev.libjam.gfx.drawable;

import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.ReadOnlyListWrapper;
import javafx.collections.FXCollections;
import javafx.scene.canvas.GraphicsContext;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.util.ArrayList;
import java.util.List;

/**
 * A GfxParent is a GfxNode capable of maintaining children.
 */
public abstract class GfxParent extends GfxNode implements GfxDrawable, Parent {

    @SuppressWarnings("checkstyle:JavadocVariable")
    protected ReadOnlyListWrapper<GfxNode> children = new ReadOnlyListWrapper<>(FXCollections.observableArrayList());


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
        super(x, y, width, height);
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

        int len = children.size();
        for (int i = 0; i < len; i++) {
            children.get(i).draw(g);
        }

        return this;
    }

    /**
     * Returns the ReadOnlyListProperty of this children.
     *
     * @return The list of GfxNodes maintained by this GfxParent.
     */
    public ReadOnlyListProperty<GfxNode> getChildren() {
        return children.getReadOnlyProperty();
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
        node.setVisible(isVisible());
        children.add(node);
        return this;
    }


}
