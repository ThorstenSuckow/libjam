package libjam.physx;


import libjam.math.Rectangle;
import libjam.math.Vector;


public abstract class WorldObject {

    private double x = 0;

    private double y = 0;

    /**
     * The mass of this object, in kg.
     */
    private double mass = 0;

    private Vector yVector = Vector.from(new double[]{0, 0});

    private Vector xVector = Vector.from(new double[]{0, 0});

    public WorldObject(double mass) {
        this.mass = mass;
    }

    public double getMass() {
        return mass;
    }


    /**
     * Returns width of this object in cm.
     *
     * @return the width of this object in cm.
     */
    abstract public double getWidth();

    /**
     * Returns height of this object in cm.
     *
     * @return the height of this object in cm.
     */
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

    /**
     * Returns the current x-coordinate of the object.
     * The x-coordinate is measured from the bottom-left.
     *
     * @return the x-coordinate of the object in meters
     */
    public double getX() {
        return x;
    }

    /**
     * Returns the current y-coordinate of the object.
     * The y-coordinate is measured from the bottom-left.
     *
     * @return the y-coordinate of the object in meters
     */
    public double getY() {
        return y;
    }


    /**
     * Returns the cross-sectional area of this object in cm².
     *
     * @return the cross-sectional area of this object in cm².
     */
    public abstract double getCrossSectionalArea();

    /**
     * Returns a number between 0 and 1 indicating the elasticity of the object.
     * 0 is no elasticity at all, i.e. fully plastic, whereas 1 is fully elastic.
     * Anything between is considered elastic-plastic.
     *
     * @return the elasticity of the object
     */
    public abstract double getElasticity();


    public WorldObject setXVector(final Vector v) {
        xVector = v;
        return this;
    }

    public WorldObject setYVector(final Vector v) {
        yVector = v;
        return this;
    }


    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }


    public Vector getXVector() {
        return xVector;
    }

    public Vector getYVector() {
        return yVector;
    }

    public Vector getMovementVector() {
        return xVector.add(yVector);
    }

}
