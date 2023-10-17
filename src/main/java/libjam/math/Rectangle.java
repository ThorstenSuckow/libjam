package libjam.math;


/**
 * A rectangle specifies an area in a coordinate space that is defined by the rectangle's
 * bottom-left point (x, y) in the coordinate space, its width, and its height. The difference to
 * the java.awt.Rectangle-implementation is that the origin is bottom left insteadt of top-left.
 */
public class Rectangle {

    /**
     * The x-coordinate of the lower left corner
     */
    private double x;

    /**
     * The y-coordinate of the lower left corner
     */
    private double y;

    private double width;

    private double height;


    public Rectangle(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Rectangle setLocation(double x, double y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public Rectangle setDimension(double width, double height) {
        this.width = width;
        this.height = height;
        return this;
    }


    public double getHeight() {
        return height;
    }


    public double getWidth() {
        return width;
    }


    public double getX() {
        return x;
    }


    public double getY() {
        return y;

    }


    public Rectangle setX(double x) {
        this.x = x;
        return this;
    }


    public Rectangle setY(double y) {
        this.y = y;
        return this;
    }


    public Rectangle setHeight(double height) {
        this.height = height;
        return this;
    }

    public Rectangle setWidth(double width) {
        this.width = width;
        return this;
    }


    /**
     *
     * @param x the x-xoordinate of the lower left corner of the object
     * @param y the y-coordinate of the lower left corner of the object
     * @param width the width of the object
     * @param height the height of the object
     * @return
     */
    public boolean contains(final  Rectangle rect) {

        double camX_left = getX();
        double camX_right = getX() + getWidth();
        double camY_bottom = getY();
        double camY_top = getY() + getHeight();

        double x_left = rect.getX();
        double y_bottom = rect.getY();
        double x_right = x_left + rect.getWidth();
        double y_top = y_bottom + rect.getHeight();


        // contains
        if (x_left >= camX_left &&
            y_bottom >= camY_bottom &&
            (x_right) <= camX_right &&
            (y_top) <= camY_top) {
            return true;
        }
        return false;
    }

    public boolean intersects(final Rectangle rect) {

        double camX_left = getX();
        double camX_right = getX() + getWidth();
        double camY_bottom = getY();
        double camY_top = getY() + getHeight();

        double x_left = rect.getX();
        double y_bottom = rect.getY();
        double x_right = x_left + rect.getWidth();
        double y_top = y_bottom + rect.getHeight();

        if (x_right > camX_left &&
                y_top > camY_bottom &&
                x_left < camX_right &&
                camY_bottom < camY_top) {
            return true;
        }

        return false;

    }


}
