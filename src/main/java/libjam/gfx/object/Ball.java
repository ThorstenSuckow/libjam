package libjam.gfx.object;


import libjam.math.Vector;
import libjam.physx.WorldObject;

import java.awt.Color;
import java.awt.Graphics;

public class Ball extends WorldObject {

    public Ball(double mass) {
        super(mass);
    }

    /**
     * Draws the ball given the graphic context.
     *
     * @param g Current graphic context.
     */
    public void draw(final Graphics g, double worldScale) {

        double x = getX();
        double y = getY();
        double w = getWidth();
        double h = getHeight();

        int xStart =   (int) (x + w / 2);
        int yStart =  (int) (y + h / 2);
        int vectorLength = 60;
        int labelOffset = 10;

        g.setColor(Color.WHITE);
        g.drawOval((int) x, (int) y, (int) w, (int) h);


        String ySpeed =  String.format("%.2f", getYVector().getAt(1));
        String xSpeed =  String.format("%.2f", getXVector().getAt(0));


        // y-Vector
        g.setColor(Color.BLUE);
        g.drawLine (
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
        g.drawLine (
                xStart,
                yStart,
                xStart + vectorLength,
                yStart
        );


        g.setColor(Color.BLUE);
        g.drawLine (
                xStart,
                yStart,
                xStart + vectorLength,
                yStart
        );

        Vector movementVector = getMovementVector();
        g.setColor(Color.GREEN);
        g.drawLine (
                xStart,
                yStart,
                (int) (xStart + movementVector.getAt(0)),
                (int) (yStart + movementVector.getAt(1))
        );


    }

    @Override
    public double getCrossSectionalArea() {
        return Math.PI * Math.pow(getWidth() / 2, 2);
    }

    public double getElasticity() {
        return 0.5;
    }

    /**
     * Returns the height for this object.
     *
     * @return the width
     */
    @Override
    public double getWidth() {
        return 10;
    }

    /**
     * Returns the height for this object.
     *
     * @return the height
     */
    @Override
    public double getHeight() {
        return 10;
    }

}
