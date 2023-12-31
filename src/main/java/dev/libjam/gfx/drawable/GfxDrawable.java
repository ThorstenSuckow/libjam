package dev.libjam.gfx.drawable;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.scene.canvas.GraphicsContext;

public interface GfxDrawable {


    /**
     * Draws this GfxDrawable and its children given the specified Graphics-context.
     *
     * @param g The Graphics-context.
     */
    void draw(GraphicsContext g);

    /**
     * The returned BooleanProperty represents the boolean value indicating whether this GfxDrawable should be included
     * in the next rendering pass.
     *
     * @return a BooleanProperty with its value set to "true" to render this GfxDrawable, otherwise "false"
     *
     * @see #isVisible()
     * @see #setVisible(boolean)
     */
    BooleanProperty visibleProperty();

    /**
     * Requests this GfxDrawable to be included in the next rendering pass if visible is true,
     * otherwise requests the next rendering pass to skip this GfxDrawable.
     *
     * @param visible true to render this Drawable
     *
     * @return this GfxDrawable
     */
    GfxNode setVisible(boolean visible);

    /**
     * The value of the #visibleProperty().
     *
     * @return the boolean value representing the visible state of this GfxDrawable.
     *
     * @see #setVisible(boolean)
     * @see #visibleProperty()
     */
    boolean isVisible();

    /**
     * @return The width of this GfxDrawable.
     *
     * @see #setWidth(double)
     * @see #getWidth()
     */
    DoubleProperty widthProperty();

    /**
     * Sets the value of the #widthProperty() to the specified value.
     *
     * @param width the specified width for this GfxDrawable.
     *
     * @see #widthProperty()
     * @see #getWidth()
     */
    GfxNode setWidth(double width);

    /**
     * @return the value of #widthProperty().
     */
    double getWidth();

    /**
     * @return The height of this GfxDrawable.
     *
     * @see #setHeight(double)
     * @see #getHeight()
     */
    DoubleProperty heightProperty();

    /**
     * Sets the value of the #heightProperty() to the specified value.
     *
     * @param height the specified height for this GfxCanvas.
     */
    GfxNode setHeight(double height);

    /**
     * @return the value of #heightProperty().
     */
    double getHeight();

    /**
     * @return The x-coordinate of this GfxDrawable.
     *
     * @see #setX(double)
     * @see #getX()
     */
    DoubleProperty xProperty();

    /**
     * Sets the value of the #xProperty() to the specified value.
     *
     * @param x the specified x-coordinate for this GfxCanvas.
     */
    GfxNode setX(double x);

    /**
     * @return the value of #xProperty().
     */
    double getX();

    /**
     * @return The y-coordinate of this GfxDrawable.
     *
     * @see #setY(double)
     * @see #getY()
     */
    DoubleProperty yProperty();

    /**
     * Sets the value of the #yProperty() to the specified value.
     *
     * @param y the specified y-coordinate for this GfxCanvas.
     */
    GfxNode setY(double y);

    /**
     * @return the value of #yProperty().
     */
    double getY();

}
