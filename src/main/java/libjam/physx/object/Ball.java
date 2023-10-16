package libjam.physx.object;


import libjam.physx.WorldObject;

public class Ball extends WorldObject {

    public Ball(double mass) {
        super(mass);
    }

    @Override
    public double getCrossSectionalArea() {
        return Math.PI * Math.pow(getWidth() / 2, 2);
    }

    public double getElasticity() {
        return 0.5;
    }

    /**
     * Returns the height for this object.
     *
     * @return the width
     */
    @Override
    public double getWidth() {
        return 0.1;
    }

    /**
     * Returns the height for this object.
     *
     * @return the height
     */
    @Override
    public double getHeight() {
        return 0.1;
    }

}
