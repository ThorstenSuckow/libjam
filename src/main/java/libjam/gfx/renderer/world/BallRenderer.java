package libjam.gfx.renderer.world;


import libjam.physx.WorldObject;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Simple Ball renderer.
 */
public final class BallRenderer implements WorldObjectRenderer {


    @Override
    public boolean draw(
            final Graphics g,
            final WorldObject worldObject,
            final ReferenceRenderingContext renderingContext) {

        int x = renderingContext.toX(worldObject.getX());
        int y = renderingContext.toY(worldObject.getY());
        int w = renderingContext.toWidth(worldObject.getWidth());
        int h = renderingContext.toHeight(worldObject.getHeight());


        g.setColor(Color.WHITE);
        g.drawOval(x, y, w, h);

        return true;
    }


}

