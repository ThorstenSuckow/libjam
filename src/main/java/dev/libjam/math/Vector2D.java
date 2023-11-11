package dev.libjam.math;

/**
 * Represents a vector in a two-dimensional Euclidean space.
 */
public class Vector2D implements Cloneable {

    @SuppressWarnings("checkstyle:JavadocVariable")
    private double x;

    @SuppressWarnings("checkstyle:JavadocVariable")
    private double y;


    /**
     * Creates a new Vector2D with the x-component and the y-component set
     * to 0.
     */
    public Vector2D () {
        this(0, 0);
    }


    /**
     * Creates a new Vector2D with the x- and y-component set to the specified
     * values.
     *
     * @param x The specified x-value.
     * @param y The specified y-value.
     */
    public Vector2D (final double x, final double y) {
        setXY(x, y);
    }


    /**
     * Returns the value of the x-component of this Vector2D.
     *
     * @return the value of this Vector2D's x-component.
     */
    public double getX() {
        return x;
    }


    /**
     * Returns the value of the y-component of this Vector2D.
     *
     * @return the value of this Vector2D's y-component.
     */
    public double getY() {
        return y;
    }

    /**
     * Sets the x-component of this Vector2D to the specified value.
     *
     * @param x The specified x-value.
     */
    public void setX(final double x) {
        this.x = x;
    }


    /**
     * Sets the y-component of this Vector2D to the specified value.
     *
     * @param y The specified y-value.
     */
    public void setY(final double y) {
        this.y = y;
    }


    /**
     * Sets both x- and y-components of this Vector2D two the specified values.
     *
     * @param x The specified x-value.
     * @param y The specified y-value.
     */
    public void setXY(final double x, final double y) {
        this.x = x;
        this.y = y;
    }


    /**
     * Returns the magnitude of this vector.
     *
     * @return the magnitude of this vector.
     */
    public double getMagnitude() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }


    /**
     * Returns the sum of this vector and the specified vector.
     *
     * @param v the specified Vector2D.
     *
     * @return a new Vector2d
     */
    public Vector2D add(final Vector2D v) {
        return new Vector2D(
            x + v.x,
            y + v.y
        );
    }


    @Override
    public Vector2D clone() {
        return new Vector2D(x, y);
    }


    /**
     * Returns true if the specified Vector2D's components are equal
     * to this Vector2Ds components.
     *
     * @param v the specified Vector
     *
     * @return true if the specified Vector2D is considered to be equal to this Vector2D.
     */
    public boolean equals(Vector2D v) {
        if (v == null) {
            return false;
        }

        return x == v.getX() && y == v.getY();
    }

}
