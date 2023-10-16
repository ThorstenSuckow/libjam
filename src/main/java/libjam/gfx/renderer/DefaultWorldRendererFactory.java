package libjam.gfx.renderer;


import libjam.gfx.renderer.object.BallRenderer;
import libjam.physx.WorldObject;
import libjam.physx.object.Ball;

public class DefaultWorldRendererFactory implements WorldRendererFactory {

    private BallRenderer ballRenderer;


    public ObjectRenderer getRenderer(WorldObject worldObj) {

        if (worldObj instanceof Ball) {
            return getBallRenderer();
        }


        throw new RuntimeException("No render for " + worldObj + " found.");
    }


    private BallRenderer getBallRenderer() {

        if (ballRenderer != null) {
            return ballRenderer;
        }

        ballRenderer = new BallRenderer();

        return ballRenderer;
    }

}
