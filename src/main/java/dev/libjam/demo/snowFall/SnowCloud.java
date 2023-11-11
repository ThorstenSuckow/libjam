package dev.libjam.demo.snowFall;

import dev.libjam.demo.snowFall.physx.SnowFlakeModel;
import dev.libjam.physx.Object2D;

public class SnowCloud extends Object2D {
    public SnowCloud(double x, double y, double width, double height) {
        super(x, y, width, height);
    }


    public long getMaxAge() {
        return -1;
    }

    public void updateObject(long time) {

        int size = (int)(Math.random() * 18);

        if ((int)(Math.random() * 100) % 2 == 0) {
            SnowFlakeModel snowFlake = new SnowFlakeModel(Math.random() * getWidth(), -size, size, size);
            snowFlake.setVector(0, 1);
           getWorld().addObject(snowFlake);
        }

    }


}
