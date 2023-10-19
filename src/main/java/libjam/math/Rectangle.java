package libjam.math;


/**
 * Like the java.awt.Rectangle, a rectangle specifies an area in a coordinate space, but this implementation
 * is defined by the rectangle's <u>bottom-left point (x, y)</u> (instead of java.awt.Rectangle's top-left point) in
 * the coordinate space, its width, and its height.
 */
public class Rectangle {

    @SuppressWarnings("checkstyle:JavadocVariable")
    private double x;

    @SuppressWarnings("checkstyle:JavadocVariable")
    private double y;

    @SuppressWarnings("checkstyle:JavadocVariable")
    private double width;

    @SuppressWarnings("checkstyle:JavadocVariable")
    private double height;


    /**
     * Creates a new Rectangle based on the (x, y) coordinates and the width and height.
     *
     * @param x The x-coordinate in the coordinate space.
     * @param y The y-coordinate in the coordinate space.
     * @param width The width of the Rectangle.
     * @param height The height of the Rectangle.
     */
    @SuppressWarnings("checkstyle:HiddenField")
    public Rectangle(final double x, final double y, final double width, final double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }


    /**
     * @return This Rectangle's height.
     */
    public double getHeight() {
        return height;
    }


    /**
     * @return This Rectangle's width.
     */
    public double getWidth() {
        return width;
    }


    /**
     * @return This Rectangle's x-coordinate.
     */
    public double getX() {
        return x;
    }


    /**
     * @return This Rectangle's y-coordinate.
     */
    public double getY() {
        return y;

    }


    /**
     * Sets the x-coordinate of this Rectangle.
     *
     * @param x the new x-coordinate for this Rectangle.
     *
     * @return this Rectangle.
     */
    @SuppressWarnings("checkstyle:HiddenField")
    public Rectangle setX(final double x) {
        this.x = x;
        return this;
    }


    /**
     * Sets the y-coordinate of this Rectangle.
     *
     * @param y the new y-coordinate for this Rectangle.
     *
     * @return this Rectangle.
     */
    @SuppressWarnings("checkstyle:HiddenField")
    public Rectangle setY(final double y) {
        this.y = y;
        return this;
    }


    /**
     * Sets the height of this Rectangle.
     *
     * @param height the new height for this Rectangle.
     *
     * @return this Rectangle.
     */
    @SuppressWarnings("checkstyle:HiddenField")
    public Rectangle setHeight(final double height) {
        this.height = height;
        return this;
    }


    /**
     * Sets the width of this Rectangle.
     *
     * @param width the new width for this Rectangle.
     *
     * @return this Rectangle.
     */
    @SuppressWarnings("checkstyle:HiddenField")
    public Rectangle setWidth(final double width) {
        this.width = width;
        return this;
    }


    /**
     * Returns true if this Rectangle entirely contains the specified Rectangle.
     *
     * @param rect the specified Rectangle
     *
     * @return true if this Rectangle contains the specified Rectangle, otherwise false.
     */
    public boolean contains(final Rectangle rect) {
        return rect.x >= x
            && rect.y >= y
            && rect.x + rect.width <= x + width
            && rect.y + rect.height <= y + height;
    }


    /**
     * Returns true if this Rectangle and the specified Rectangle intersect.
     *
     * @param rect the specified Rectangle
     *
     * @return true if this Rectangle and the specified Rectangle intersect, otherwise false.
     */
    public boolean intersects(final Rectangle rect) {

        double ry = rect.y;
        double rx = rect.x;
        double rw = rect.width;
        double rh = rect.height;

        if (rw <= 0 || rh <= 0 || width <= 0 || height <= 0) {
            return false;
        }

        return (y + height > ry && y + height < ry + rh)
            || (y > ry && y < ry + rh)
                || (x + width > rx && x + width < rx + rw)
            || (x > rx && x < rx + rw);
    }


}
