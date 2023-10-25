package dev.libjam.gfx.drawable;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;


@SuppressWarnings("checkstyle:RegexpSingleline")
public abstract class DrawableBase implements GfxDrawable {


    @SuppressWarnings("checkstyle:JavadocVariable")
    protected DoubleProperty width;

    @SuppressWarnings("checkstyle:JavadocVariable")
    protected DoubleProperty height;

    @SuppressWarnings("checkstyle:JavadocVariable")
    protected DoubleProperty x;

    @SuppressWarnings("checkstyle:JavadocVariable")
    protected DoubleProperty y;

    @SuppressWarnings("checkstyle:JavadocVariable")
    protected BooleanProperty visible;

    /**
     * Creates a new GfxDrawable with its height, width, x- and y-coordinate set to 0.
     */
    public DrawableBase() {
        this(0, 0, 0, 0);
    }


    /**
     * Creates a new GfxDrawable with its width and height set to the specified values,
     * the x- and y-coordinate set to 0.
     *
     * @param width the specified width
     * @param height the specified height
     */
    public DrawableBase(final int width, final int height) {
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
    public DrawableBase(final double x, final double y, final double width, final double height) {
        this.width = new SimpleDoubleProperty(width);
        this.height = new SimpleDoubleProperty(height);

        this.x = new SimpleDoubleProperty(x);
        this.y = new SimpleDoubleProperty(y);

        visible = new SimpleBooleanProperty();
    }


    @Override
    public GfxDrawable setVisible(boolean visible) {
        this.visible.set(visible);
        return this;
    }

    @Override
    public boolean isVisible() {
        return visible.get();
    }

    @Override
    public GfxDrawable setWidth(double width) {
        this.width.set(width);
        return this;
    }

    @Override
    public GfxDrawable setHeight(double height) {
        this.height.set(height);
        return this;
    }

    @Override
    public GfxDrawable setX(double x) {
        this.x.set(x);
        return this;
    }

    @Override
    public GfxDrawable setY(double y) {
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
}
