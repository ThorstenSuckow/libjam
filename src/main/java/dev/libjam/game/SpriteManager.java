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

public class SpriteManager  implements World2DEventListener {

    List<PropertyChangeListener> propertyChangeListener = new ArrayList<>();

    HashMap<Object2D, Sprite> objToSprite = new HashMap<>();

    SpriteLayer spriteLayer;

    SpriteRenderer renderer;

    public SpriteManager(final World2D world, final SpriteLayer rootSpriteLayer, SpriteRenderer renderer) {
        this.spriteLayer = rootSpriteLayer;
        this.renderer = renderer;
        world.addWorld2DEventListener(this);
    }


    @Override
    public void object2DAdded(Object2DAddedEvent evt) {
        Sprite sp = new Sprite(evt.getObject2D(), renderer);
        spriteLayer.add(sp);
        registerSprite(sp);
    }

    @Override
    public void object2DRemoved(Object2DRemovedEvent evt) {

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
        // System.out.println(evt);
        if (evt.getPropertyName() == "y") {
            double y = (Double)evt.getNewValue();
            sp.setY(y);
        }

        if (evt.getPropertyName() == "x") {
            double x = (Double)evt.getNewValue();
            sp.setX(x);
        }

    }

}
