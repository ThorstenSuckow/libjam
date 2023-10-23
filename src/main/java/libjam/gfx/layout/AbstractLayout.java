package libjam.gfx.layout;


import libjam.gfx.canvas.GfxCanvas;
import libjam.gfx.event.ResizeListener;

/**
 * Abstract base Layout for GfxCanvas.
 * Will register itself as a ResizeListener for the owning GfxCanvas.
 */
public abstract class AbstractLayout implements GfxLayout, ResizeListener {


    @SuppressWarnings("checkstyle:JavadocVariable")
    protected GfxCanvas owner;


    @Override
    public boolean setOwner(final GfxCanvas canvas) {
        if (this.owner != null) {
            return false;
        }

        this.owner = canvas;
        this.owner.addResizeListener(this);

        return true;
    }


    @Override
    public GfxCanvas getOwner() {
        return owner;
    }

}
