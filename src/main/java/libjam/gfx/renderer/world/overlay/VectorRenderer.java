package libjam.gfx.renderer.world.overlay;

import libjam.gfx.renderer.world.ReferenceRenderingContext;
import libjam.math.Vector;
import libjam.physx.WorldObject;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Renders movement-direction vectors along with information about the individual
 * values of these vectors.
 */
public class VectorRenderer implements OverlayRenderer {


    @Override
    public boolean draw(
            final Graphics g,
            final WorldObject obj,
            final ReferenceRenderingContext referenceRenderingContext
    ) {

        int x = referenceRenderingContext.toX(obj.getX());
        int y = referenceRenderingContext.toY(obj.getY());
        int w = referenceRenderingContext.toWidth(obj.getWidth());
        int h = referenceRenderingContext.toHeight(obj.getHeight());

        int xStart = x + w/2;
        int yStart = y + h/2;
        int vectorLength = 60;
        int labelOffset = 10;

        g.setColor(Color.WHITE);

        String ySpeed =  String.format("%.2f", obj.getYVector().getAt(1));
        String xSpeed =  String.format("%.2f", obj.getXVector().getAt(0));


        // y-Vector
        g.setColor(Color.BLUE);
        g.drawLine(
            xStart,
            yStart,
            xStart,
            yStart + vectorLength
        );

        g.setColor(Color.WHITE);
        g.drawString(
            ySpeed,
            xStart + labelOffset,
            yStart + vectorLength / 2
        );


        g.setColor(Color.WHITE);
        g.drawString(
            xSpeed,
            xStart  + vectorLength / 2,
            yStart - labelOffset
        );

        g.setColor(Color.BLUE);
        g.drawLine(
            xStart,
            yStart,
            xStart + vectorLength,
            yStart
        );


        g.setColor(Color.BLUE);
        g.drawLine(
            xStart,
            yStart,
            xStart + vectorLength,
            yStart
        );

        Vector movementVector = obj.getMovementVector();
        g.setColor(Color.GREEN);
        g.drawLine(
            xStart,
            yStart,
            (int) (xStart + movementVector.getAt(0)),
            (int) (yStart + movementVector.getAt(1))
        );

        return true;
    }
}
