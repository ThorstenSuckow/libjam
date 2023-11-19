package dev.libjam.demo.snowFall;

import dev.libjam.demo.snowFall.physx.SnowFlakeModel;
import dev.libjam.physx.Object2D;

public class SnowCloud extends Object2D {



    /**
     * Creates a new Object2D with a null-vector for velocity and it's location-coordinates set to
     * x=-1, y=-1.
     *
     * @param width     The width of this Object2D.
     * @param height    The height of this Object2D.
     * @param gfxParent
     */
    public SnowCloud(double width, double height) {
        super(width, height);
    }


    public void updateObject(long time) {

        double size = (Math.random() * 10);

        if ((int)(Math.random() * 100) % 2 == 0) {

            SnowFlakeModel snowFlakeModel = new SnowFlakeModel(size, size);

            snowFlakeModel.setVelocity(0, 1);

            snowFlakeModel.setXY(Math.random() * getWidth(), 0);

            getWorld().addObject(snowFlakeModel);
        }

    }


}
