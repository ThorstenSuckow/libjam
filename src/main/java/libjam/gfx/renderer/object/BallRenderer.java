package libjam.gfx.renderer.object;


import libjam.gfx.ReferenceRenderingContext;
import libjam.gfx.renderer.ObjectRenderer;
import libjam.physx.WorldObject;

import java.awt.Color;
import java.awt.Graphics;

public class BallRenderer implements ObjectRenderer {


    public WorldObject draw(final Graphics g, final WorldObject worldObject, ReferenceRenderingContext referenceRenderingContext) {

        double x = worldObject.getX();
        double y = worldObject.getY();
        double w = worldObject.getWidth();
        double h = worldObject.getHeight();

        g.setColor(Color.WHITE);
        g.drawOval((int) x, (int) y, (int) w, (int) h);

        return worldObject;
    }


}

