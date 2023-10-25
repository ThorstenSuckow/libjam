package dev.libjam.gfx.drawable;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

;

@SuppressWarnings("checkstyle:RegexpSingleline")
public class DefaultDrawable extends DrawableBase {


    @SuppressWarnings("checkstyle:JavadocVariable")
    private static final Color BG_COLOR = Color.BLACK;

    @SuppressWarnings("checkstyle:JavadocVariable")
    private static final Color BORDER_COLOR = Color.CYAN;

    @SuppressWarnings("checkstyle:JavadocVariable")
    private Color bgColor;

    @SuppressWarnings("checkstyle:JavadocVariable")
    private Color borderColor;


    /**
     * Creates a new GfxDrawable with its height, width, x- and y-coordinate set to 0.
     */
    public DefaultDrawable() {
        this(0, 0,0,0);
    }


    /**
     * Creates a new GfxDrawable with its width and height set to the specified values,
     * the x- and y-coordinate set to 0.
     *
     * @param width the specified width
     * @param height the specified height
     */
    public DefaultDrawable(final double width, final double height) {
        this(0,0, width, height);
    }


    /**
     * Creates a new GfxDrawable with its width, height, x- and y-coordinate
     * set to the specified values.
     *
     * @param width the specified width
     * @param height the specified height
     * @param x the specified x-coordinate
     * @param y the specified y-coordinate
     */
    public DefaultDrawable(final double x, final double y, final double width, final double height) {
        this(x, y, width, height, null);
    }

    /**
     * Creates a new GfxDrawable with its width, height, x- and y-coordinate
     * set to the specified values.
     *
     * @param width the specified width
     * @param height the specified height
     * @param x the specified x-coordinate
     * @param y the specified y-coordinate
     */
    @SuppressWarnings("checkstyle:FinalParameters")
    public DefaultDrawable(final double x, final double y, final double width, final double height, Color borderColor) {

        super(x, y, width, height);

        this.borderColor = borderColor;
    }

    public GfxDrawable setBgColor (final Color bgColor) {
        this.bgColor = bgColor;
        return this;
    }

    public GfxDrawable setBorderColor (final Color borderColor) {
        this.borderColor = borderColor;
        return this;
    }


    @Override
    public void draw(final GraphicsContext g) {

        if (!this.isVisible()) {
            return;
        }

        if (bgColor != null) {
            g.setFill(bgColor);
            g.fillRect(
                xProperty().get(),
                yProperty().get(),
                widthProperty().get(),
                heightProperty().get()
            );
        }

        if (borderColor != null) {
            g.setStroke(borderColor);
            g.strokeRect(
                xProperty().get(),
                yProperty().get(),
                widthProperty().get(),
                heightProperty().get()
            );
        }

    }


    @Override
    public String toString() {
        return "Drawable["
            + "x:" + x
            + "; y:" + y
            + "; width:" + width
            + "; height: " + height
            + "]";
    }
}
