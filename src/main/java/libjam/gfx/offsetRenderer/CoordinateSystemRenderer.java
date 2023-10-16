package libjam.gfx.offsetRenderer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public  class CoordinateSystemRenderer extends OffsetRenderer {

    private final int offsetBottom;

    private final int offsetLeft;

    Font font = new Font("Courier New", Font.PLAIN, 8);
    private int startX;

    private int endX;

    private int startY;

    private int endY;

    /**
     * @param offsetBottom
     * @param offsetLeft
     */
    public CoordinateSystemRenderer(int offsetBottom, int offsetLeft) {

        this.offsetBottom = offsetBottom;
        this.offsetLeft = offsetLeft;
    }

    public void setRangeX(int startX, int endX) {
        this.startX = startX;
        this.endX = endX;
    }

    public int getOffsetBottom() {
        return offsetBottom;
    }

    public int getOffsetLeft() {
        return offsetLeft;
    }

    public int getStartX() {
        return startX;
    }

    public int getEndX() {
        return endX;
    }

    public void setRangeY(int startY, int endY) {
        this.startY = startY;
        this.endY = endY;
    }


    public int getStartY() {
        return startY;
    }

    public int getEndY() {
        return endY;
    }

    @Override
    public void draw(Graphics g) {
        drawX(g);
        drawY(g);
    }


    public void drawX(final Graphics g) {

        int width = getCanvasContext().getWidth();
        int offsetLeft = getOffsetLeft();
        int offsetBottom = getOffsetBottom();


        int height = getCanvasContext().getHeight() - offsetBottom;

        g.setColor(Color.CYAN);

        g.setColor(Color.cyan);

        int zero = offsetLeft;

        // x
        g.drawLine(0, height, width, height);

        for (int i = 0, len = getEndX() ; i <= len; i += 5) {

            int x1 = zero + i;
            int y1 = height + (i % 10 == 0 ? 4 : 2);
            int x2 = x1;
            int y2 = height;


            g.drawLine(x1, y1, x2, y2);

            if (i % 10 == 0 && i != 0) {
                g.setFont(font);

                Graphics2D g2d = (Graphics2D) g;
                AffineTransform defaultAt = g2d.getTransform();

                // rotates 90 degree counterclockwise
                AffineTransform at = new AffineTransform();
                at.rotate( -Math.PI / 2);
                g2d.setTransform(at);
                g2d.drawString((i + getStartX()) + "",- height - 24, x1 + 3);
                // reset
                g2d.setTransform(defaultAt);
            }
        }

    }


    public void drawY(final Graphics g) {

        int height = getCanvasContext().getHeight();
        int offsetBottom = getOffsetBottom();
        int offsetLeft = getOffsetLeft();

        g.setColor(Color.CYAN);

        int zero = height - offsetBottom;

        // y
        g.drawLine(offsetLeft,  0, offsetLeft, height);

        for (int i = 0, len = getEndY(); i <= len; i += 5) {

            int x1 = offsetLeft - (i % 10 == 0 ? 4 : 2);
            int y1 = zero - i;

            int x2 = getOffsetLeft();
            int y2 = y1;

            g.drawLine(x1, y1, x2, y2);

            if (i % 10 == 0 && i != 0) {
                g.setFont(font);
                g.drawString((getStartY() + i)+ "", offsetLeft - 24, y1 + 3);
            }


        }
    }



}
