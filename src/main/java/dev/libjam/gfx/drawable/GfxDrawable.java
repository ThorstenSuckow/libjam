package dev.libjam.gfx.drawable;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.scene.canvas.GraphicsContext;

/**
 * Contract for a GfxDrawable.
 * A GfxDrawable represents a drawable figure that should be added to an existing GraphicsContext.
 * The purpose of this interface is to provide a contract for representatives of drawable elements
 * that do not require the API of a javafx.scene.Node.
 */
public interface GfxDrawable {

    /**
     * Draws this GfxDrawable given the specified Graphics-context.
     * If this GfxDrawable contains any children, their draw()-method
     * should be invoked if applicable.
     * @param g The Graphics-context.
     */
    void draw(GraphicsContext g);


    /**
     * The returned BooleanProperty specifies if this GfxDrawable should be included in the next rendering
     * pass, if this value equals to "true".
     *
     * @return the BooleanProperty whose value repesents the visible state of this GfxDrawable.
     *
     * @see #isVisible()
     * @see #setVisible()
     */
    BooleanProperty visibleProperty();


    /**
     * Requests this drawable to be included in the next rendering pass if visible is true,
     * otherwise requests the next rendering pass to skip this Drawable.
     *
     * @param visible true to render this Drawable
     *
     * @return this GfxDrawable
     */
    GfxDrawable setVisible(boolean visible);


    /**
     * The returned value specifies if this GfxDrawable should be included in the next rendering pass,
     * if this value equals to "true".
     *
     * @return the boolean value repesenting the visible state of this GfxDrawable.
     *
     * @see #setVisible()
     * @see #visibleProperty()
     */
    boolean isVisible();


    /**
     *
     * @return
     *
     * @see #setWidth()
     * @see #getWidth()
     */
    DoubleProperty widthProperty();

    /**
     * Sets the width of this Drawable to the specified width.
     * If this Drawable is associated with any Layout, the layout
     * should be updated accordingly.
     * Changing this value should fire a ResizeEvent to propagate changes
     * to any interested client.
     *
     * @param width the specified width for this GfxCanvas.
     *
     * @see #widthProperty()
     * @see #getWidth()
     */
    GfxDrawable setWidth(double width);

    /**
     * @return the DoubleProperty wrapping the width of this GfxDrawable.
     */
    double getWidth();


    /**
     *
     * @return
     *
     * @see #setHeight()
     * @see #getHeight()
     */
    DoubleProperty heightProperty();


    /**
     * Sets the height of this Drawable to the specified height.
     * If this Drawable is associated with any Layout, the layout
     * should be updated accordingly.
     *
     * @param height the specified height for this GfxCanvas.
     */
    GfxDrawable setHeight(double height);

    /**
     * @return the DoubleProperty wrapping the height of this GfxDrawable.
     */
    double getHeight();


    /**
     *
     * @return
     *
     * @see #setX()
     * @see #getX()
     */
    DoubleProperty xProperty();


    /**
     * Sets the x-coordinate of this Drawable to the specified value.
     *
     * @param x the specified y-coordinate.
     */
    GfxDrawable setX(double x);

    /**
     * @return the DoubleProperty wrapping the x-coordinate of this GfxDrawable.
     */
    double getX();

    /**
     *
     * @return
     *
     * @see #getY()
     * @see #setY()
     */
    DoubleProperty yProperty();


    /**
     * Sets the y-coordinate of this Drawable to the specified value.
     *
     * @param y the specified y-coordinate.
     */
    GfxDrawable setY(double y);




    /**
     * @return the DoubleProperty wrapping the y-coordinate of this GfxDrawable.
     */
    double getY();





}
