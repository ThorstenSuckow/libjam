package dev.libjam.demo.snowFall;

import dev.libjam.demo.snowFall.physx.SnowFlakeModel;
import dev.libjam.physx.Object2D;

public class SnowCloud extends Object2D {


    /**
     * Creates a new Object2D with a null-vector for velocity and it's location-coordinates set to
     * x=-1, y=-1.
     *
     * @param width  The width of this Object2D.
     * @param height The height of this Object2D.
     */
    public SnowCloud(double width, double height) {
        super(width, height);
    }

    public long getMaxAge() {
        return -1;
    }

    public void updateObject(long time) {

        int size = (int)(Math.random() * 18);

        if ((int)(Math.random() * 100) % 2 == 0) {
            SnowFlakeModel snowFlake = new SnowFlakeModel(
                    size, size
            );
            snowFlake.setVelocity(0, 1);
           getWorld().addObject(snowFlake);
        }

    }


}
