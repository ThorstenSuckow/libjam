package dev.libjam.demo.snowFall.physx;

import dev.libjam.physx.Object2D;

public class SnowFlakeModel extends Object2D {
    /**
     * Creates a new Object2D with a null-vector for velocity and it's location-coordinates set to
     * x=-1, y=-1.
     *
     * @param width  The width of this Object2D.
     * @param height The height of this Object2D.
     */
    public SnowFlakeModel(double width, double height) {
        super(width, height);
    }
    /*public SnowFlakeModel(double x, double y, double width, double height) {
        super(x, y, width, height);
    }*/

    public long getMaxAge() {
        return 12_000_000_000L;
    }

}
