package libjam.gfx.object;

import libjam.physx.World;

import java.awt.Color;
import java.awt.Graphics;

public class Level extends World {


    /**
     * Creates a new instance of this world.
     *
     * @param width      The width for this world.
     * @param height     The height for this world.
     * @param worldScale
     */
    public Level(int width, int height) {
        super(width, height);
    }

    public void draw(final Graphics g) {

        g.setColor(Color.WHITE);
        g.drawLine(0, getHeight() , getWidth(), getHeight() );
    }



}
