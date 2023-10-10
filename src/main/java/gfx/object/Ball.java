package gfx.object;


import physx.WorldObject;

import java.awt.Color;
import java.awt.Graphics;

public class Ball extends WorldObject {

    /**
     * Draws the ball given the graphic context.
     *
     * @param g Current graphic context.
     */
    public void draw(final Graphics g) {

        g.setColor(Color.WHITE);
        g.drawOval((int) getX(), (int) getY(), (int) getWidth(), (int) getHeight());
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
