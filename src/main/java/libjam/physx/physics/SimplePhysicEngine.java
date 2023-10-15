package libjam.physx.physics;

import libjam.physic.Gravity;

public class SimplePhysicEngine implements PhysicEngine {

    /**
     * GravityModel used with this engine-instance
     */
    private final Gravity gravityModel;


    /**
     * Creates a new SimplePhysics-engine with the specified gravityModel.
     *
     * @param gravityModel
     */
    public SimplePhysicEngine(final Gravity gravityModel) {
        this.gravityModel = gravityModel;
    }

    @Override
    public double getVerticalDistanceAtTime(double time, double verticalVelocity, double initialHeight) {
        return gravityModel.getVerticalDistanceAtTime(time, verticalVelocity, initialHeight);
    }

    @Override
    public double getVerticalVelocityAtTime(double time, double alpha, double velocity) {
        return gravityModel.getVerticalVelocityAtTime(time, alpha, velocity);
    }
}
