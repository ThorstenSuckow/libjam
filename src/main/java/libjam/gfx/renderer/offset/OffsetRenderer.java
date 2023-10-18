package libjam.gfx.renderer.offset;

import libjam.gfx.Drawable;


/**
 * Frames a canvas with offsets in which additional data can be rendered.
 */
public abstract class OffsetRenderer implements Drawable {

    /**
     * @return The height (px) of the bottom area that should be reserved for
     * this renderer.
     */
    public int getOffsetBottom() {
        return 0;
    }

    /**
     * @return The height (px) of the left area that should be reserved for
     * this renderer.
     */
    public int getOffsetLeft() {
        return 0;
    }


    /**
     * @return The height (px) of the top area that should be reserved for
     * this renderer.
     */
    public int getOffsetTop() {
        return 0;
    }


    /**
     * @return The height (px) of the right area that should be reserved for
     * this renderer.
     */
    public int getOffsetRight() {
        return 0;
    }

}
