package dev.libjam.gfx.drawable;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.canvas.GraphicsContext;

/**
 * Contract for a GfxNode.
 * A GfxNode represents a GfxDrawable that is related to an ancestor, its GfxParent.
 */
public abstract class GfxNode implements GfxDrawable {

    @SuppressWarnings("checkstyle:JavadocVariable")
    protected DoubleProperty width = new SimpleDoubleProperty();

    @SuppressWarnings("checkstyle:JavadocVariable")
    protected DoubleProperty height = new SimpleDoubleProperty();

    @SuppressWarnings("checkstyle:JavadocVariable")
    protected DoubleProperty x = new SimpleDoubleProperty();

    @SuppressWarnings("checkstyle:JavadocVariable")
    protected DoubleProperty y = new SimpleDoubleProperty();

    @SuppressWarnings("checkstyle:JavadocVariable")
    protected BooleanProperty visible = new SimpleBooleanProperty(true);

    @SuppressWarnings("checkstyle:JavadocVariable")
    protected Parent parent;



    /**
     * Creates a new GfxDrawable with its height, width, x- and y-coordinate set to 0.
     */
    public GfxNode() {
        this(0, 0, 0, 0);
    }


    /**
     * Creates a new GfxDrawable with its width and height set to the specified values,
     * the x- and y-coordinate set to 0.
     *
     * @param width the specified width
     * @param height the specified height
     */
    public GfxNode(final int width, final int height) {
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
    @SuppressWarnings("checkstyle:HiddenField")
    public GfxNode(final double x, final double y, final double width, final double height) {
        this.width.set(width);
        this.height.set(height);

        this.x.set(x);
        this.y.set(y);
    }

    @Override
    public void draw(final GraphicsContext g) {

        if (!this.isVisible()) {
            return;
        }

        drawNode(g);
    }

    /**
     * Draws this GfxDrawable.
     *
     * @param g The GraphicsContext that should be used for rendering.
     * @return this GfxNode
     */
    protected abstract GfxNode drawNode(GraphicsContext g);


    @Override
    public GfxNode setVisible(boolean visible) {
        this.visible.set(visible);
        return this;
    }

    @Override
    public boolean isVisible() {
        return visible.get();
    }

    @Override
    public GfxNode setWidth(double width) {
        this.width.set(width);
        return this;
    }

    @Override
    public GfxNode setHeight(double height) {
        this.height.set(height);
        return this;
    }

    @Override
    public GfxNode setX(double x) {
        this.x.set(x);
        return this;
    }

    @Override
    public GfxNode setY(double y) {
        this.y.set(y);
        return this;
    }

    @Override
    public double getHeight() {
        return height.get();
    }

    @Override
    public double getWidth() {
        return width.get();
    }

    @Override
    public double getX() {
        return x.get();
    }

    @Override
    public double getY() {
        return y.get();
    }


    @Override
    public DoubleProperty heightProperty() {
        return height;
    }

    @Override
    public DoubleProperty widthProperty() {
        return width;
    }

    @Override
    public BooleanProperty visibleProperty() {
        return visible;
    }

    @Override
    public DoubleProperty xProperty() {
        return x;
    }

    @Override
    public DoubleProperty yProperty() {
        return y;
    }

    /**
     * @return The parent of this node.
     */
    public Parent getParent() {
        return parent;
    }

    /**
     * Sets the parent of this GfxDrawableNode to the specified parent.
     *
     * @param parent The specified parent.
     *
     * @return this GfxDrawableNode.
     */
    public GfxNode setParent(final Parent parent) {
        this.parent = parent;
        return this;
    }

    @Override
    public String toString() {
        return this.getClass().getName() + "@["
            + "x:" + x
            + "; y:" + y
            + "; width:" + width
            + "; height: " + height
            + "]";
    }
}
