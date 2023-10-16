package libjam.gfx.renderer.object;


import libjam.gfx.ReferenceRenderingContext;
import libjam.gfx.renderer.ObjectRenderer;
import libjam.physx.WorldObject;

import java.awt.Color;
import java.awt.Graphics;

public class BallRenderer implements ObjectRenderer {


    public WorldObject draw(
            final Graphics g,
            final WorldObject worldObject,
            final ReferenceRenderingContext referenceRenderingContext) {

        ReferenceRenderingContext context = referenceRenderingContext;

        int x = context.toX(worldObject.getX());
        int y = context.toY(worldObject.getY());
        int w = context.toWidth(worldObject.getWidth());
        int h = context.toHeight(worldObject.getHeight());


        g.setColor(Color.WHITE);
        g.drawOval(x, y, w, h);

        return worldObject;
    }


}

