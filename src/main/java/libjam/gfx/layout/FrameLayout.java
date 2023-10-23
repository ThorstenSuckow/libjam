package libjam.gfx.layout;


import libjam.gfx.drawable.GfxDrawable;
import libjam.gfx.event.ResizeEvent;

public class FrameLayout extends AbstractLayout {

    /**
     * "Top"-Position indicator for the frame.
     */
    public static final int TOP = 0;

    /**
     * "Top"-Position indicator for the frame.
     */
    public static final int RIGHT = 1;

    /**
     * "Bottom"-Position indicator for the frame.
     */
    public static final int BOTTOM = 2;

    /**
     * "Left"-Position indicator for the frame.
     */
    public static final int LEFT = 3;

    /**
     * "Center"-Position indicator for the frame.
     */
    public static final int CENTER = 4;


    @SuppressWarnings("checkstyle:JavadocVariable")
    private int oldHeight;

    @SuppressWarnings("checkstyle:JavadocVariable")
    private int oldWidth;


    /**
     * Overrides sizing of the specified element of the owning GfxCanvas based on its position
     * to fit in the bounds specified by the owning GfxCanvas.
     *
     * @param drawable the specified drawable.
     * @param position the position of the specified GfxDrawable.
     */
    private void applyDimensions(final GfxDrawable drawable, final int position) {
        if (position == TOP || position == BOTTOM) {
            drawable.setWidth(owner.getWidth());
            drawable.setX(owner.getX());

            if (position == BOTTOM) {
                drawable.setY(owner.getY() + owner.getHeight() - drawable.getHeight());
            } else {
                drawable.setY(owner.getY());
            }

            System.out.println(drawable);
        }

        if (position == LEFT || position == RIGHT) {
            drawable.setHeight(owner.getHeight());
            drawable.setY(owner.getX());

            if (position == RIGHT) {
                drawable.setX(owner.getX() + owner.getWidth() - drawable.getWidth());
            }
        }


    }

    @Override
    public void onResize(final ResizeEvent e) {
        oldWidth = e.getOldWidth();
        oldHeight = e.getOldHeight();
        updateLayout();
    }


    @Override
    public synchronized void updateLayout() {

        double heightFactor =  oldHeight != 0 ? ((double)  owner.getHeight() / oldHeight) : 1;
        double widthFactor  =  oldWidth != 0 ? ((double) owner.getWidth() / oldWidth) : 1;

        for (GfxDrawable drawable: owner.getDrawables()) {
            if (drawable == null) {
                continue;
            }

            drawable.setHeight((int)(drawable.getHeight() * heightFactor));
            drawable.setWidth((int)(drawable.getWidth() * widthFactor));
        }
    }

}
