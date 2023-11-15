package dev.libjam.game;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class SpriteManager  {

    List<PropertyChangeListener> propertyChangeListener = new ArrayList<>();

    public void unregisterSprite(Sprite sp) {
        sp.getObject2D().removePropertyChangeListener(this::object2DPropertyChange);
        sp.removePropertyChangeListener(this::spritePropertyChange);
    }

    public void registerSprite(Sprite sp) {
        sp.getObject2D().addPropertyChangeListener(this::object2DPropertyChange);
        sp.addPropertyChangeListener(this::spritePropertyChange);
    }

    private void spritePropertyChange(PropertyChangeEvent evt) {

        if (!(evt.getSource() instanceof Sprite sp)) {
            throw new IllegalArgumentException();
        }

        if (evt.getPropertyName() == "state") {
            if (evt.getNewValue() == LifecycleState.VOID) {
                sp.removePropertyChangeListener(this::spritePropertyChange);
                sp.getObject2D().removePropertyChangeListener(this::object2DPropertyChange);
            }
        }
    }

    private void object2DPropertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName() == "y") {
            double y = (Double)evt.getNewValue();
            //setY(y);
        }

        if (evt.getPropertyName() == "x") {
            double x = (Double)evt.getNewValue();
            //setX(x);
        }
    }

}
