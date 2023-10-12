package libjam.math;

public class Gravity {

    public static final double G_EARTH = 9.81;




    public static double getDistanceTravelled(double velocity) {
        return (Math.pow(velocity, 2)) / (2 * G_EARTH);
    }

    public static double getVelocityAtTime(double time) {
        return G_EARTH * time;
    }

    public static double getDistanceAtTime(double time) {
        return G_EARTH * (Math.pow(time, 2)) / 2d;
    }


    /**
     * Returns the distance travelled of the object for which terminalVelocity applies after "duration" seconds
     * of free fall.
     *
     * @param duration fall time in seconds
     * @param terminalVelocity the terminalVelocity computed for the object (m/s)
     * @param gravity Gravitational force (m / s²)
     *
     * @return vertical position (m)
     */
    public static double computeVerticalDistanceTravelled(
        double duration,
        double terminalVelocity,
        double gravity
    ) {

        return (Math.pow(terminalVelocity, 2) / gravity)
                * Math.log(Math.cosh((gravity * duration) / terminalVelocity));
    }


    /**
     * Returns the velocity of an object for which terminalVelocity applies after "duration" seconds
     * of free fall.
     *
     * @param duration fall time in seconds
     * @param terminalVelocity the terminalVelocity computed for the object (m/s)
     * @param gravity Gravitational force (m / s²)
     *
     * @return speed (m/s)
     */
    public static double computeVelocityForDuration(
            double duration,
            double terminalVelocity,
            double gravity
    )
    {
        return terminalVelocity * Math.tanh(
                (gravity * duration) / terminalVelocity
        );
    }


    /**
     * Returns the terminal speed in (m/s) of a body with the mass "mass" in a medium with the density "density",
     * with the gravity-constant "G".
     *
     * @param mass mass of the body falling (kg)
     * @param gravity Gravitational force (m / s²)
     * @param density density of the medium the body travels through (kg / m³)
     * @param dragForce the drag coefficient for the body (@see https://www.engineeringtoolbox.com/drag-coefficient-d_627.html)
     * @param crossSectionalArea the cross-sectional area of the body (in m²)
     *
     * @return terminal speed in (m/s)
     */
     public static double computeTerminalSpeed(
        double mass,
        double gravity,
        double density,
        double dragForce,
        double crossSectionalArea
     ) {
        return Math.sqrt(
            (2 * mass * gravity) /
            (density * dragForce  * crossSectionalArea)
        );
    }



}
