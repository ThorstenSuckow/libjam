package gfx.bounce;


import gfx.object.Ball;
import math.Vector;
import physx.World;

/**
 * Main class for invoking bounce demo.
 */
final class Main {

    private Main() {

    }


    /**
     * main method.
     *
     * @param args This main method does not require any args.
     */
    public static void main(final String[] args) {

        final int width = 400;
        final int height = 400;


        World world = new World(width, height, 1);

        BounceCanvas bounceCanvas = new BounceCanvas(world);

        new Window(width, 480, bounceCanvas);

        Ball ball = new Ball();
        ball.setVector(Vector.from(0, 0.1));
        world.add(ball, 20, 20);


    }


}
