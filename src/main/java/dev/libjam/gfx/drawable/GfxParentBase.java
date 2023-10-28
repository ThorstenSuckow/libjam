package dev.libjam.gfx.drawable;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;


@SuppressWarnings("checkstyle:RegexpSingleline")
public abstract class GfxParentBase extends GfxNodeBase implements GfxParent  {

    @SuppressWarnings("checkstyle:JavadocVariable")
    protected List<GfxNode> children = new ArrayList<GfxNode>();

    /**
     * Creates a new GfxDrawable with its height, width, x- and y-coordinate set to 0.
     */
    public GfxParentBase() {
        this(0, 0, 0, 0);
    }

    /**
     * Creates a new GfxDrawable with its width and height set to the specified values,
     * the x- and y-coordinate set to 0.
     *
     * @param width the specified width
     * @param height the specified height
     */
    public GfxParentBase(final int width, final int height) {
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
    public GfxParentBase(final double x, final double y, final double width, final double height) {
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
    public GfxParent drawChildren(GraphicsContext g) {
        for (GfxNode child: children) {
            child.draw(g);
        }
        return this;
    }

    @Override
    public List<GfxNode> getChildren() {
        return new ArrayList<GfxNode>(children);
    }

}
