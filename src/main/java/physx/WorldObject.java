package physx;


import math.Vector;

import java.awt.Graphics;


public abstract class WorldObject {

    private double x = 0;

    private double y = 0;

    private Vector vector = new Vector(new double[]{0, 0});

    abstract public double getWidth();

    abstract public double getHeight();

    public WorldObject setXY(final double x, final double y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public WorldObject setX(final double x) {
        this.x = x;
        return this;
    }

    public WorldObject setY(final double y) {
        this.y = y;
        return this;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public abstract void draw(Graphics g);

    public WorldObject setVector(final Vector v) {
        vector = v;
        return this;
    }


    public Vector getVector() {
        return vector;
    }


}
