package libjam.physx.physics;

/**
 * A contract for physic-engines providing calculations against a Euclidean plane.
 * All units are treated as "real-world" units (e.g. meters, seconds) and should be
 * translated to cartesian coordinates the implementing API requires for rendering.
 */
interface PhysicEngine {

    /**
     * Returns the distance an object has vertically traveled, considering an verticalVelocity
     * and an initialHeight where travel started. Both verticalVelocity and teh return value may be
     * negative, indicating movement-directions towards negative y, positive against positive y.
     *
     * @param time The time for which the distance should be computed, in seconds
     * @param verticalVelocity an initial verticalVelocity that should be taken into account
     * @param initialHeight an initial height that should be considerd for computing the velocity
     * @return
     */
    double getVerticalDistanceAtTime(double time, double verticalVelocity, double initialHeight);


    /**
     * Returns the vertical velocity of an object at time "time" (in seconds), considering
     * an initial velocity (m/s) and a launch angle alpha (deg). The sign for the velocity parameter
     * and the return value of this method provide information about the movement direction:
     * Given an Euclidean plane with x/y coordinates, negative numbers indicate
     * a movement-direction against negative y, positive against positive y.
     *
     * @param time in s
     * @param alpha in deg
     * @param velocity the sum of the horizontal speed vector and the vertical speed vector.
     *
     * @return The vertical speed.
     */
    double getVerticalVelocityAtTime(double time, double alpha, double velocity);

}
