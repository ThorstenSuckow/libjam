package dev.libjam.game;

import dev.libjam.gfx.drawable.DefaultGfxNode;
import dev.libjam.gfx.drawable.GfxNode;
import dev.libjam.physx.Object2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;


/**
 * A Sprite is a visual representation of an Object2D.
 * The initial LifecycleState of a Sprite is PREPARING.
 */
@SuppressWarnings("checkstyle:LineLength")
public class Sprite extends DefaultGfxNode {

    @SuppressWarnings("checkstyle:JavadocVariable")
    private Object2D object2D;

    @SuppressWarnings("checkstyle:JavadocVariable")
    private SpriteRenderer renderer;

    @SuppressWarnings("checkstyle:JavadocVariable")
    private WritableImage img;

    @SuppressWarnings("checkstyle:JavadocVariable")
    private LifecycleState state;

    private List<PropertyChangeListener> propertyChangeListener = new ArrayList<>();

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
        state = LifecycleState.PREPARING;
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
            img = this.renderer.render(this);
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
    public LifecycleState getState() {
        return state;
    }



    /**
     * Sets the state of this Sprite to the specified LifecycleState.
     * Fires a PropertyChangeEvent if a new valid state is detected.
     * A state may be reset from EXPIRING to LIVING.
     *
     * @param state The specified LifecycleState.
     *
     * @throws IllegalArgumentException if the ordinal of the new LifecycleState
     * is less than the previous one and the old state is not EXPIRING and new state
     * is not LIVING.
     */
    @SuppressWarnings("checkstyle:HiddenField")
    public void setState(final LifecycleState state) throws IllegalArgumentException {

        if (state.ordinal() < this.state.ordinal() && state != LifecycleState.LIVING
        && this.state != LifecycleState.EXPIRING) {
            throw new IllegalArgumentException("state \"" + state + "\" not allwed");
        }

        if (state.ordinal() == this.state.ordinal()) {
            return;
        }

        this.state = state;

        firePropertyChange("state", this.state, state);
    }


    /**
     * Fires a PropertyChangeEvent based on the specified values.
     *
     *
     * @param propertyName the name of the property that changed.
     * @param oldValue The previous value of the property.
     * @param newValue The new value of the property.
     */
    private void firePropertyChange(
        final String propertyName,
        final Object oldValue,
        final Object newValue
    ) {
        PropertyChangeEvent evt = new PropertyChangeEvent(
                this, propertyName, oldValue, newValue
        );

        for (int i = 0; i < propertyChangeListener.size(); i++) {
            propertyChangeListener.get(i).propertyChange(evt);
        }
    }


    /**
     * Adds the specified PropertyChangeListener to the list of this Sprite's listeners.
     *
     * @param listener The specified PropertyChangeListener to add.
     */
    public void addPropertyChangeListener(final PropertyChangeListener listener) {

        if (!propertyChangeListener.contains(listener)) {
            propertyChangeListener.add(listener);
        }
    }


    /**
     * Removes the specified PropertyChangeListener from this Sprite's
     * list of PropertyChangeListeners.
     *
     * @param listener The specified PropertyChangeListener to remove.
     */
    public void removePropertyChangeListener(final PropertyChangeListener listener) {
        propertyChangeListener.remove(listener);
    }


}
