package dev.libjam.game;

import dev.libjam.physx.Object2D;
import javafx.beans.Observable;
import javafx.beans.property.ReadOnlyObjectProperty;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SpriteManager  {

    List<PropertyChangeListener> propertyChangeListener = new ArrayList<>();

    HashMap<Object2D, Sprite> objToSprite = new HashMap<>();

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
       // System.out.println(evt);
        if (evt.getPropertyName() == "y") {
            double y = (Double)evt.getNewValue();

            System.out.println("y: " + y);
            //objToSprite.get((Object2D)evt.getSource()).setY(y);
        }

        if (evt.getPropertyName() == "x") {
            double x = (Double)evt.getNewValue();

            System.out.println("x: " + x);
          //  objToSprite.get((Object2D)evt.getSource()).setX(x);
        }
    }

}
