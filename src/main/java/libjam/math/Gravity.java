package libjam.math;

public class Gravity {

    public static final double G_EARTH = 9.81;


    /**
     * Returns the vertical distance of an object based on the parameters at a given point in time.
     *
     * @param time in seconds
     * @param verticalVelocity The absolute value of the vertical movement vector. Positive value means an object
     *                        moves upward, begative means the object travels downwards.
     * @param height The height as the reference for computing the vertical distance.
     *
     * @return Returns the current vertical distance at this point in time, considering height.
     * The value may be < 0. Calling methods have to take measures if the returned value does not fit into the
     * frame of reference.
     */
    public static double getVerticalDistanceAtTime(double time, double verticalVelocity, double height) {
        return height + (verticalVelocity * time) - (G_EARTH * Math.pow(time, 2) / 2d);
    }


    /**
     * Returns the vertical velocity of an object at the specified time relative to the velocity
     * and the initial launch angle alpha.
     *
     * @param time in seconds
     * @param alpha The initial launch angle of the object, relative to a perfect flat ground with 0 degrees..
     * @param velocity The movement vector with V = V_x / cos(alpha) and V = V_y / sin(alpha)
     * @return
     */
    public static double getVerticalVelocityAtTime(double time, double alpha, double velocity) {

        alpha = alpha % 360;

        boolean upwards = (alpha >= 0 && alpha <= 180);

        if (upwards && velocity < 0) {
            throw new IllegalArgumentException(
                    "With alpha=" + alpha + ", the initialVelocity must be positive (travelling upwards with positive velocity)."
            );
        }

        if (!upwards && velocity > 0) {
            throw new IllegalArgumentException(
                    "With alpha=" + alpha + ", the initialVelocity must be negative (travelling downwards with negative velocity)."
            );
        }

        double v = G_EARTH * time;

        return velocity * Math.sin(Math.toRadians(alpha)) + (upwards ? -v : v);
    }




}
