package libjam.demo.bounce;


import libjam.gfx.object.Level;
import libjam.physx.World;
import libjam.physx.WorldObject;
import libjam.physx.event.WorldChangeListener;
import libjam.physx.event.WorldObjectEnterEvent;
import libjam.util.Logger;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

final public class BounceCanvas extends Canvas implements Runnable, WorldChangeListener {


    private Graphics gContext;

    private Image buffer;

    private World world;


    /**
     * Create a new raw black canvas.
     *
     * @param world The world that gets rendered in this canvas.
     */
    public BounceCanvas(World world) {
        this.world = world;
        world.addWorldChangeListener(this);
        setBackground(Color.BLACK);
        (new Thread(this)).start();
    }

    protected synchronized void syncWorldStateAndRepaint() {


        updateWorld();
        renderWorld();
        repaint();

    }


    /**
     *
     */
    @Override
    public void run() {

        long start, end;

        double fps = 20;


        while (true) {

            start  = System.currentTimeMillis();

            syncWorldStateAndRepaint();

            try {
                Thread.sleep((int) fps);
            } catch (InterruptedException ignored) {

            }

            end  = System.currentTimeMillis();
            fps = 1000 / (end - start);
          //  Logger.log("" + (int)fps);
        }

    }

    /**
     *
     */
    public void updateWorld() {
        world.syncObjects();
    }

    /**
     *
     */
    public boolean  renderWorld() {

        if (buffer == null) {
            buffer = createImage(this.getWidth(), this.getHeight());

            if (buffer == null) {
                System.err.println("buffer not available");
                return false;
            } else {
                gContext = buffer.getGraphics();
            }
        }

        gContext.setColor(Color.BLACK);
        gContext.fillRect(0, 0, this.getWidth(), this.getHeight());

        ((Level) world).draw(gContext);
        for (WorldObject obj: world.getObjects()) {
            obj.draw(gContext, world.getWorldScale());
        }

        return true;
    }


    /**
     * Paint given the current graphic context.
     *
     * @param g the specified Graphics context
     */
    public void paint(final Graphics g) {

        if (buffer != null) {
            g.drawImage(buffer, 0, 0, null);
        }


       // g.drawString("start: " + start + "; end: " + end + "; fps " + (end / 1000), 10, 10);
    }


    @Override
    public void onWorldObjectEnter(final WorldObjectEnterEvent evt) {
        Logger.log("New obj enters the world " + evt.getSource());

    }
}