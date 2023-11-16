package dev.libjam.game;

import dev.libjam.gfx.drawable.GfxNode;
import dev.libjam.physx.Object2D;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;


/**
 * A Sprite is a visual representation of an Object2D.
 * The initial LifecycleState of a Sprite is PREPARING.
 */
@SuppressWarnings("checkstyle:LineLength")
public class Sprite extends GfxNode {

    @SuppressWarnings("checkstyle:JavadocVariable")
    private Object2D object2D;

    @SuppressWarnings("checkstyle:JavadocVariable")
    private SpriteRenderer renderer;

    @SuppressWarnings("checkstyle:JavadocVariable")
    private WritableImage img;

    @SuppressWarnings("checkstyle:JavadocVariable")
    private ReadOnlyObjectWrapper<LifecycleState> lifecycleState;


    /**
     * Creates a new Sprite representing the specified Object2D.
     *
     * @param object2D the specified Object2D this Sprite should represent.
     */
    @SuppressWarnings("checkstyle:HiddenField")
    public Sprite(final Object2D object2D) {
        this(object2D, null);
    }


    /**
     * Creates a new Sprite representing the specified Object2D using
     * the specified renderer
     *
     * @param object2D The specified Object2D
     * @param renderer The specified renderer.
     */
    @SuppressWarnings("checkstyle:HiddenField")
    public Sprite(
            final Object2D object2D,
            final SpriteRenderer renderer
    ) {
        this.object2D = object2D;
        this.renderer = renderer;
        lifecycleState = new ReadOnlyObjectWrapper<>(this, "lifecycleState", LifecycleState.PREPARING);
    }


    /**
     * Returns the Object2D this Sprite represents.
     *
     * @return the Object2D this Sprite represents.
     */
    public Object2D getObject2D() {
        return object2D;
    }


    /**
     * Returns the SpriteRenderer for this Sprite.
     *
     * @return the SpriteRender used for this Sprite. null, if none was registered
     */
    public SpriteRenderer getRenderer() {
        return renderer;
    }


    /**
     * Returns the max age of this Sprite.
     *
     * @return -1 if there is no max-age, otherwise the max-age in
     * nanoseconds.
     */
    public long getMaxAge() {
        return -1;
    }


    /**
     * Requests an image from this Sprite's renderer for drawing
     * using the specified GraphicsContext.
     *
     * @param g The GraphicsContext that should be used for rendering.
     *
     * @return this Sprite as a GfxNode
     */
    @Override
    protected GfxNode drawNode(final GraphicsContext g) {

        if (renderer != null) {
            img = renderer.render(this);
        }

        if (img != null) {
            g.drawImage(img, getX(), getY());
        }

        return this;
    }


    /**
     * Returns the current LifecycleState of this Sprite.
     *
     * @return the LifecycleState of this Sprite.
     */
    public LifecycleState getLifecycleState() {
        return lifecycleState.get();
    }


    /**
     * Returns the ReadOnlyObjectProperty that is representing the LifecycleState of this Sprite.
     *
     * @return the ReadOnlyObjectProperty representing this Sprite's LifecycleState.
     */
    public ReadOnlyObjectProperty<LifecycleState> lifecycleStateProperty() {
        return lifecycleState.getReadOnlyProperty();
    }


    /**
     * Sets the lifecycleState of this Sprite to the specified LifecycleState.
     *
     * @param state The specified LifecycleState.
     *
     * @throws IllegalArgumentException if the ordinal of the new LifecycleState
     * is less than the previous one, and the old state is not "EXPIRING" and the new state
     * is not "LIVING".
     */
    public void setLifecycleState(final LifecycleState state) throws IllegalArgumentException {

        LifecycleState currentState = lifecycleState.get();

        if (state.ordinal() < currentState.ordinal() && state != LifecycleState.LIVING
        && currentState != LifecycleState.EXPIRING) {
            throw new IllegalArgumentException("state \"" + state + "\" not allowed");
        }

        if (state.ordinal() == currentState.ordinal()) {
            return;
        }

        lifecycleState.set(state);
    }

}
