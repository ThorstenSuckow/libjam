package dev.libjam.math;

/**
 * Represents a vector in a two-dimensional Euclidean space.
 */
public class Vector2D {

    double x;

    double y;

    public Vector2D (double x, double y) {
        this.x = 0;
        this.y = 0;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(final double x) {
        this.x = x;
    }

    public void setY(final double y) {
        this.y = y;
    }

    public void setXY(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the magnitude of this vector.
     *
     * @return
     */
    public double getMagnitude() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }


    /**
     * Returns the sum of this vector and the specified vector.
     * @return
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
}
