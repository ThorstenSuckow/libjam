package dev.libjam.game2D;

import dev.libjam.gfx.drawable.GfxNode;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;


/**
 * A Sprite is a visual representation of an GameObject.
 * The initial LifecycleState of a Sprite is PREPARING.
 */
@SuppressWarnings("checkstyle:LineLength")
public class Sprite extends GfxNode {

    @SuppressWarnings("checkstyle:JavadocVariable")
    private GameObject gameObject;

    @SuppressWarnings("checkstyle:JavadocVariable")
    private SpriteRenderer renderer;

    @SuppressWarnings("checkstyle:JavadocVariable")
    private WritableImage img;

    @SuppressWarnings("checkstyle:JavadocVariable")
    private ReadOnlyObjectWrapper<LifecycleState> lifecycleState;

    /**
     * Package private constructor for testing.
     */
    Sprite() {
        super();
    };

    /**
     * Creates a new Sprite representing the specified GameObject.
     *
     * @param gameObject the specified GameObject this Sprite should represent.
     */
    @SuppressWarnings("checkstyle:HiddenField")
    public Sprite(final GameObject gameObject) {
        this(gameObject, null);
    }


    /**
     * Creates a new Sprite representing the specified GameObject using
     * the specified renderer
     *
     * @param gameObject The specified GameObject
     * @param renderer The specified renderer.
     */
    @SuppressWarnings("checkstyle:HiddenField")
    public Sprite(
            final GameObject gameObject,
            final SpriteRenderer renderer
    ) {
        this.setGameObject(gameObject);
        this.renderer = renderer;
        lifecycleState = new ReadOnlyObjectWrapper<>(this, "lifecycleState", LifecycleState.PREPARING);
    }

    /**
     * Sets the GameObject of this Sprite to the specified instance.
     *
     * @param gameObject the specified GameObject.
     *
     * @return the GameObject this Sprite represents.
     *
     * @throws IllegalArgumentException if the gameObject's Sprite has already been set and does not equal to
     * this object
     * @throws IllegalStateException or if this Sprite already has a GameObject and the specified GameObject
     * does not reference the same object.
     *
     */
    void setGameObject(final GameObject gameObject) throws IllegalArgumentException, IllegalStateException {

        if (this.gameObject != null && this.gameObject != gameObject) {
            throw new IllegalStateException();
        }

        if (gameObject.getSprite() != null && gameObject.getSprite() != this) {
            throw new IllegalArgumentException();
        }

        this.gameObject = gameObject;
        if (gameObject.getSprite() == null) {
            this.gameObject.setSprite(this);
        }
    }

    /**
     * Returns the GameObject this Sprite represents.
     *
     * @return the GameObject this Sprite represents.
     */
    public GameObject getGameObject() {
        return gameObject;
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
     * Returns the WritableImage used by this Sprite. If this Sprite
     * does not use a WritableImage, this method returns null.
     *
     * @return The WritableImage used by this Sprite, otherwise null if no such
     * object exists.
     */
    public WritableImage getImage() {
        return img;
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
