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
 * Mediates information between physical objects and their representation as Sprites.
 */
public class SpriteManager  implements World2DEventListener {

    private List<PropertyChangeListener> propertyChangeListener = new ArrayList<>();

    private HashMap<Object2D, Sprite> objToSprite = new HashMap<>();

    private SpriteLayer spriteLayer;

    private SpriteRenderer renderer;


    /**
     *
     * @param world
     * @param rootSpriteLayer
     * @param renderer
     */
    public SpriteManager(final World2D world, final SpriteLayer rootSpriteLayer, SpriteRenderer renderer) {
        this.spriteLayer = rootSpriteLayer;
        this.renderer = renderer;
        world.addWorld2DEventListener(this);
    }

    /**
     * Translates the position of the specified Object2D to coordinates used by the specified Sprite.
     *
     * @param object2D
     * @param sprite
     */
    public void translateObject2DToSprite(Sprite sprite) {
        Object2D obj = sprite.getObject2D();
        sprite.setWidth(obj.getWidth());
        sprite.setHeight(obj.getHeight());
        sprite.setX(obj.getX());
        sprite.setY(obj.getY());
    }

    @Override
    public void object2DAdded(Object2DAddedEvent evt) {
        Object2D obj = evt.getObject2D();
        Sprite sp = new Sprite(obj, renderer);
        translateObject2DToSprite(sp);
        registerSprite(sp);
        spriteLayer.add(sp);
    }

    @Override
    public void object2DRemoved(Object2DRemovedEvent evt) {
        unregisterSprite(objToSprite.get((Object2D)evt.getObject2D()));
    }

    public void unregisterSprite(Sprite sp) {
        sp.getObject2D().removePropertyChangeListener(this::object2DPropertyChange);
    }

    public void registerSprite(Sprite sp) {
        sp.getObject2D().addPropertyChangeListener(this::object2DPropertyChange);
        sp.lifecycleStateProperty().addListener(this::spriteLifecycleChange);

        objToSprite.put(sp.getObject2D(), sp);
    }

    private void spriteLifecycleChange(final Observable observable, Object oldValue, Object newValue) {
        ReadOnlyObjectProperty<LifecycleState> lifecycleState = (ReadOnlyObjectProperty<LifecycleState>) observable;

        Sprite sprite = (Sprite)lifecycleState.getBean();

        if (newValue == LifecycleState.VOID) {
            sprite.getObject2D().removePropertyChangeListener(this::object2DPropertyChange);
        }
    }


    private void object2DPropertyChange(PropertyChangeEvent evt) {

        Sprite sp = objToSprite.get((Object2D)evt.getSource());

        if (evt.getPropertyName() == "y" || evt.getPropertyName() == "x") {
            translateObject2DToSprite(sp);
        }

    }

}
