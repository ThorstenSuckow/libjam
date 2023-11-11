package dev.libjam.math;

public class Bounds2D {

    Point2D p;

    double width;

    double height;

    public Bounds2D(Point2D p, double width, double height) {
        this.p = p;
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getX() {
        return p.getX();
    }

    public double getY() {
        return p.getY();
    }
}
