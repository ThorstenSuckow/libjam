package dev.libjam.demo.snowFall;

import dev.libjam.physx.Object2D;
import dev.libjam.physx.World2D;

public class SnowWorld extends World2D {

    long lastUpdate;


    public SnowWorld(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    @Override
    public void updateWorld(long time) {

        super.updateWorld(time);
        for (Object2D obj:getObjects()) {


            if (obj.getY() > (getHeight()- 40)  - obj.getHeight()) {
                obj.setVelocity(0, 0);
                obj.setY((getHeight() - 40) - obj.getHeight());
            }

            updatePosition(obj, time);


            randomizeMovement(obj, time);


            lastUpdate = time;
        }
    }

    private void randomizeMovement(Object2D obj, long time) {


        /*long random = (long)((obj.getAge() / 1_000_000_000d) * 10) % 2;

        if (random == 0) {
            obj.setVector(-0.05, 1);
        } else {
            obj.setVector(0.05, 1);
        }*/

    }

    private void updatePosition(Object2D obj, long time) {
        double x = obj.getVelocity().getX();
        double y = obj.getVelocity().getY();

        obj.setX((obj.getX() + x) % getWidth());
        obj.setY((obj.getY() + y));
    }
}
