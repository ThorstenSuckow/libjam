package libjam.gfx;

import libjam.util.Logger;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

public class GfxCanvas extends Canvas implements Runnable {



    private Graphics gContext;

    private Image buffer;


    /**
     *
     */
    private List<Drawable> drawableList = new ArrayList<>();


    public GfxCanvas(int width, int height) {
        setSize(width, height);
    }



    public GfxCanvas addDrawable(Drawable drawable) {
        drawable.setCanvasContext(new CanvasContext(getWidth(), getHeight()));
        drawableList.add(drawable);

        return this;
    }

    public boolean updateDrawables() {
        if (buffer == null) {
            buffer = createImage(this.getWidth(), this.getHeight());

            if (buffer == null) {
                Logger.log("buffer not available");
                return false;
            } else {
                gContext = buffer.getGraphics();
            }
        }

        gContext.setColor(Color.BLACK);
        gContext.fillRect(0, 0, this.getWidth(), this.getHeight());




        for (Drawable drawable: drawableList) {
            drawable.draw(gContext);
        }

        return true;
    }

    public void updateAndRepaint() {

        updateDrawables();
        repaint();
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
    public void run() {
        long start, end;

        double fps = 20;


        while (true) {

            start  = System.currentTimeMillis();

            updateAndRepaint();

            try {
                Thread.sleep((int) fps);
            } catch (InterruptedException ignored) {

            }

            //end  = System.currentTimeMillis();
        }
    }
}
