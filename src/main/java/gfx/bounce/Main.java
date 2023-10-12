package gfx.bounce;


import gfx.object.Ball;
import gfx.object.Level;
import libjam.math.Vector;
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


        World world = new Level(width, height, 100);

        BounceCanvas bounceCanvas = new BounceCanvas(world);

        new Window(width, 480, bounceCanvas);

        Ball ball = new Ball(10);
        ball.setXVector(Vector.from(0, 0));
        ball.setYVector(Vector.from(0, 1));
        world.add(ball, 20, 20);


    }


}
