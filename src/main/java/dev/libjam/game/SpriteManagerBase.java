package dev.libjam.game;

import dev.libjam.physx.Object2D;
import dev.libjam.physx.World2D;
import dev.libjam.physx.event.Object2DAddedEvent;
import dev.libjam.physx.event.Object2DRemovedEvent;
import dev.libjam.physx.event.World2DEventListener;
import javafx.beans.Observable;
import javafx.beans.property.ReadOnlyObjectProperty;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Base class for SpriteManagers.
 */
public abstract class SpriteManagerBase implements World2DEventListener {

    @SuppressWarnings("checkstyle:JavadocVariable")
    private final List<PropertyChangeListener> propertyChangeListener = new ArrayList<>();

    @SuppressWarnings("checkstyle:JavadocVariable")
    private final HashMap<Object2D, Sprite> objToSprite = new HashMap<>();

    @SuppressWarnings("checkstyle:JavadocVariable")
    private final SpriteLayer spriteLayer;


    public SpriteManagerBase(final World2D world, final SpriteLayer layer) {
        this.spriteLayer = layer;
        world.addWorld2DEventListener(this);
    }

    /**
     * Creates a Sprite for the specified object2D and returns the sprite by also
     * registering the specified Object2D with this SpriteManager. If the specified
     * Object2D is already managed by this SpriteManager, the owning Worite will be returned.
     *
     * @param object2D The specified object2D for which the Sprite should get created.
     *
     * @return Sprite the created Sprite.
     */
    protected Sprite createSprite(Object2D object2D) {
        Sprite sprite = getSprite(object2D);
        if (sprite == null) {
            sprite = new Sprite(object2D);
            register(object2D, sprite);
        }
        return sprite;
    }


    /**
     * Returns the Sprite for the specified object2D managed by this instance.
     * Returns null if the specified Object2D is not managed by this
     * SpriteManager.
     *
     * @param object2D The specified object2D for which the Sprite should be returned-
     *
     * @return the Sprite associated with the Object2D, or null if no such Sprite exists.
     */
    public Sprite getSprite(final Object2D object2D) {
        return objToSprite.get(object2D);
    }

    /**
     * Syncs the state of the Object2D with the state of the specified Sprite.
     * State can be any information provided by the Object2D, such as positional
     * information.
     *
     * @param object2D The specified Objects2D whose state must be reflected with the specified Sprite
     * @param sprite The specified sprite whose state needs to by synced.
     *
     * @throws IllegalArgumentException if the specified object2D is not associated with the
     * specified sprite.
     */
    public abstract void sync(final Object2D object2D, final Sprite sprite) throws IllegalArgumentException;


    /**
     * Registers the specified Sprite to make sure changes made to  Object2D-model are synced by this SpriteManager.
     *
     * @param sprite The specified Sprite to register.
     */
    protected void register(final Object2D object2D, final Sprite sprite) throws IllegalArgumentException {
        if (sprite.getObject2D() != object2D) {
            throw new IllegalArgumentException();
        }
        object2D.addPropertyChangeListener(this::object2DPropertyChange);
        sprite.lifecycleStateProperty().addListener(this::spriteLifecycleChange);

        objToSprite.put(sprite.getObject2D(), sprite);
    }


    /**
     * Unregisters the specified Sprite. Any changes to its Object2D-model are
     * not synced by this SpriteManager anymore.
     *
     * @param sp The specified Sprite to unregister.
     */
    protected void unregister(final Object2D object2D, final Sprite sprite) throws IllegalArgumentException {
        if (sprite.getObject2D() != object2D) {
            throw new IllegalArgumentException();
        }
        sp.getObject2D().removePropertyChangeListener(this::object2DPropertyChange);
        sp.lifecycleStateProperty().removeListener(this::spriteLifecycleChange);
        objToSprite.remove(sp.getObject2D());
    }


    @Override
    public void object2DAdded(final Object2DAddedEvent evt) {
        Object2D obj = evt.getObject2D();
        Sprite sp = createSprite(obj);
        sync(obj, sp);
        register(sp);
        spriteLayer.add(sp);
    }


    @Override
    public void object2DRemoved(final Object2DRemovedEvent evt) {
        unregister(objToSprite.get((Object2D) evt.getObject2D()));
    }


    private void spriteLifecycleChange(final Observable observable, final Object oldValue, final Object newValue) {

        if (newValue == LifecycleState.VOID) {
            ReadOnlyObjectProperty<LifecycleState> lifecycleState = (ReadOnlyObjectProperty<LifecycleState>) observable;
            Sprite sprite = (Sprite) lifecycleState.getBean();

            sprite.getObject2D().removePropertyChangeListener(this::object2DPropertyChange);
        }
    }


    protected void object2DPropertyChange(final PropertyChangeEvent evt) {

        if (evt.getPropertyName() == "y" || evt.getPropertyName() == "x") {
            Object2D object2D = (Object2D) evt.getSource();
            Sprite sp = objToSprite.get(object2D);
            translate(object2D, sp);
        }

    }

}
