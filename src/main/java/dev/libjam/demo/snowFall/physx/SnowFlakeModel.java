package dev.libjam.demo.snowFall.physx;

import dev.libjam.physx.Object2D;

public class SnowFlakeModel extends Object2D {
    public SnowFlakeModel(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    public long getMaxAge() {
        return 12_000_000_000L;
    }

}
