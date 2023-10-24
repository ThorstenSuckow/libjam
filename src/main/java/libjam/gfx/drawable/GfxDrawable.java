package libjam.gfx.drawable;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.scene.canvas.GraphicsContext;
import libjam.gfx.event.ResizeListener;

/**
 * Contract for a GfxDrawable.
 * A GfxDrawable represents a drawable figure that should be added to an existing GraphicsContext.
 * The purpose of this interface is to provide a contract for representatives of drawable elements
 * that do not require the API of a javafx.scene.Node.
 */
public interface GfxDrawable {

    /**
     * Requests this drawable to be included in the next rendering pass.
     *
     * @return this GfxDrawable
     */
    GfxDrawable show();

    /**
     * Makes sure this GfxDrawable is removed from the Image during the next rendering pass.
     *
     * @return this GfxDrawable
     */
    GfxDrawable hide();

    /**
     * The returned BooleanProperty specifies if this GfxDrawable should be included in the next rendering pass,
     * if this value equals to "true".
     *
     * @return the BooleanProperty repesenting the visible state of this GfxDrawable.
     */
    BooleanProperty isVisible();


    /**
     * Adds the specified ResizeListener to this GfxDrawable.
     *
     * @param listener The specified ResizeListener.
     *
     * @return this GfxDrawable.
     */
    GfxDrawable addResizeListener(ResizeListener listener);


    /**
     * Sets the width of this Drawable to the specified width.
     *
     * @param width the specified width for this GfxCanvas.
     */
    GfxDrawable setWidth(double width);


    /**
     * Sets the height of this Drawable to the specified height.
     *
     * @param height the specified height for this GfxCanvas.
     */
    GfxDrawable setHeight(double height);


    /**
     * Sets the x-coordinate of this Drawable to the specified value.
     *
     * @param x the specified y-coordinate.
     */
    GfxDrawable setX(double x);


    /**
     * Sets the y-coordinate of this Drawable to the specified value.
     *
     * @param y the specified y-coordinate.
     */
    GfxDrawable setY(double y);


    /**
     * @return the DoubleProperty wrapping the height of this GfxDrawable.
     */
    DoubleProperty getHeight();


    /**
     * @return the DoubleProperty wrapping the width of this GfxDrawable.
     */
    DoubleProperty getWidth();


    /**
     * @return the DoubleProperty wrapping the x-coordinate of this GfxDrawable.
     */
    DoubleProperty getX();


    /**
     * @return the DoubleProperty wrapping the y-coordinate of this GfxDrawable.
     */
    DoubleProperty getY();


    /**
     * Draws this GfxDrawable given the specified Graphics-context.
     *
     * @param g The Graphics-context.
     */
    void draw(GraphicsContext g);


    /**
     * Sets this drawables width and height to the specified values.
     *
     * @param width the specified width.
     * @param height the specified height.
     *
     * @return this GfxDrawable.
     */
    GfxDrawable setSize(double width, double height);


    /**
     * Sets the x- and y-coordinate of this GfxDrawable to the specified values.
     *
     * @param x the specified x-coordinate.
     * @param y the specified y-coordinate.
     *
     * @return this GfxDrawable.
     */
    GfxDrawable setLocation(double x, double y);


    /**
     * Updates the x- and y-coordinate of this GfxDrawable to the specified values, along with the width and
     * height.
     *
     * @param x the specified x-coordinate
     * @param y the specified y-coordinate
     * @param width the specified width
     * @param height the specified height
     *
     * @return this GfxDrawable
     */
    GfxDrawable setRect(double x, double y, double width, double height);
}
