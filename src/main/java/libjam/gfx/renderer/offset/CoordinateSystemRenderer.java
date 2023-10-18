package libjam.gfx.renderer.offset;

import libjam.gfx.CanvasContext;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;


/**
 * Draws a coordinate system with the origin in the lower left bottom of the canvas.
 *
 */
public  class CoordinateSystemRenderer extends OffsetRenderer {

    @SuppressWarnings("checkstyle:JavadocVariable")
    private final int offsetBottom;

    @SuppressWarnings("checkstyle:JavadocVariable")
    private final int offsetLeft;

    @SuppressWarnings("checkstyle:JavadocVariable")
    private int startX;

    @SuppressWarnings("checkstyle:JavadocVariable")
    private int endX;

    @SuppressWarnings("checkstyle:JavadocVariable")
    private int startY;

    @SuppressWarnings("checkstyle:JavadocVariable")
    private int endY;

    /**
     * value for the "ticks", i.e. after how many pixels a new marker should be rendered.
     */
    private final int ticks = 5;

    /**
     * Number indicating the marker that gets highlighted after how many pixels.
     */
    private final int highlightEach = ticks + 5;


    /**
     * Creates a new CoordinateSystemRenderer.
     * @param offsetBottom The height (px) of the bottom area that should be used for rendering the x-axis.
     * @param offsetLeft The width (px) of the left area that should be used for rendering the y-axis.
     */
    @SuppressWarnings("checkstyle:HiddenField")
    public CoordinateSystemRenderer(final int offsetBottom, final int offsetLeft) {
        this.offsetBottom = offsetBottom;
        this.offsetLeft = offsetLeft;
    }


    /**
     * Sets the range of the values to render on the x-axis.
     *
     * @param startX The first value for the x-axis
     * @param endX The last value of the x-axis
     */
    @SuppressWarnings("checkstyle:HiddenField")
    public void setRangeX(final int startX, final int endX) {
        this.startX = startX;
        this.endX = endX;
    }


    /**
     * Sets the range of the values to render on the y-axis.
     *
     * @param startY The first value for the y-axis
     * @param endY The last value of the y-axis
     */
    @SuppressWarnings("checkstyle:HiddenField")
    public void setRangeY(final int startY, final int endY) {
        this.startY = startY;
        this.endY = endY;
    }


    /**
     * @return the first value of the x-axis.
     */
    public int getStartX() {
        return startX;
    }


    /**
     * @return the last value of the x-axis.
     */
    public int getEndX() {
        return endX;
    }


    /**
     * @return the first value of the y-axis.
     */
    public int getStartY() {
        return startY;
    }


    /**
     * @return the last value of the y-axis.
     */
    public int getEndY() {
        return endY;
    }


    /**
     * Renders the bottom offset, i.e., the x-axis.
     *
     * @param g The Graphics-context
     * @param canvasContext The CanvasContext.
     *
     * @see #draw
     */
    @SuppressWarnings("checkstyle:MagicNumber")
    private void drawX(final Graphics g, final CanvasContext canvasContext) {

        int width = canvasContext.getWidth();

        int height = canvasContext.getHeight() - offsetBottom;

        g.setColor(Color.CYAN);

        g.setColor(Color.cyan);

        int zero = offsetLeft;

        Font font = getFont();
        int[] fontDim = getFontDimension();

        // x
        g.drawLine(0, height, width, height);


        for (int i = 0, len = getEndX() ; i <= len; i += ticks) {

            int x1 = zero + i;
            int y1 = height + (i % highlightEach == 0 ? 4 : 2);
            int x2 = x1;
            int y2 = height;


            g.drawLine(x1, y1, x2, y2);

            if (i % highlightEach == 0 && i != 0) {
                g.setFont(font);

                Graphics2D g2d = (Graphics2D) g;
                AffineTransform defaultAt = g2d.getTransform();

                // rotates 90 degree counterclockwise
                AffineTransform at = new AffineTransform();
                at.rotate( -Math.PI / 2);
                g2d.setTransform(at);
                g2d.drawString(
                    (i + getStartX()) + "",
                    -height - fontDim[1],
                    x1 + fontDim[0] / 2
                );
                // reset
                g2d.setTransform(defaultAt);
            }
        }

    }


    /**
     * Renders the left offset, i.e. the y-axis.
     *
     * @param g The Graphics-context
     * @param canvasContext The CanvasContex.
     *
     * @see #draw
     */
    private void drawY(final Graphics g, final CanvasContext canvasContext) {

        int height = canvasContext.getHeight();

        g.setColor(Color.CYAN);

        int zero = height - offsetBottom;

        Font font = getFont();
        int[] fontDim = getFontDimension();

        // y
        g.drawLine(offsetLeft,  0, offsetLeft, height);

        for (int i = 0, len = getEndY(); i <= len; i += ticks) {

            int x1 = offsetLeft - (i % highlightEach == 0 ? 4 : 2);
            int y1 = zero - i;

            int x2 = getOffsetLeft();
            int y2 = y1;

            g.drawLine(x1, y1, x2, y2);

            if (i % highlightEach == 0 && i != 0) {
                g.setFont(font);
                g.drawString(
                (getStartY() + i)+ "",
                offsetLeft - fontDim[0],
                y1 + fontDim[1] / 2
                );
            }


        }
    }


    /**
     * Draws the coordinate system. Axis will be drawn for all offsets > 0.
     *
     * @param g The Graphics-context.
     * @param canvasContext The new CanvasContext to consider by this Drawable.
     */
    @Override
    public void draw(final Graphics g, final CanvasContext canvasContext) {

        if (offsetBottom > 0) {
            drawX(g, canvasContext);
        }

        if (offsetLeft > 0) {
            drawY(g, canvasContext);
        }
    }


    @SuppressWarnings("checkstyle:DesignForExtension")
    @Override
    public int getOffsetBottom() {
        return offsetBottom;
    }


    @SuppressWarnings("checkstyle:DesignForExtension")
    @Override
    public int getOffsetLeft() {
        return offsetLeft;
    }


    /**
     * @return The font used fot this coordinate system.
     */
    private Font getFont() {
        return new Font("Courier New", Font.PLAIN, 8);
    }


    /**
     * @todo compute proper metrics
     *
     * @return 2-dim int-array, where the first index is the max-width of a string
     * rendered with this font, the second is the height
     */
    @SuppressWarnings("checkstyle:MagicNumber")
    private int[] getFontDimension() {
        return new int[]{6, 24};
    }
}
