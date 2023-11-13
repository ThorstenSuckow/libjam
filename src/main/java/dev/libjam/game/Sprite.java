package dev.libjam.game;

import dev.libjam.game.event.ObjectLifecycleEvent;
import dev.libjam.game.event.ObjectLifecycleListener;
import dev.libjam.gfx.drawable.DefaultGfxNode;
import dev.libjam.gfx.drawable.GfxNode;
import dev.libjam.physx.Object2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;

import java.beans.PropertyChangeEvent;

public class Sprite extends DefaultGfxNode {

    private Object2D obj;

    private SpriteRenderer renderer;

    private WritableImage img;


    private LifecycleState state;

    public Sprite(Object2D obj) {

    }

    public Sprite(Object2D obj, SpriteRenderer renderer) {

        this.obj = obj;
        this.setX(obj.getX());
        this.setWidth(obj.getWidth()).setHeight(obj.getHeight());
        obj.addPropertyChangeListener(this::object2DPropertyChange);
        this.renderer = renderer;
    }

    public void prepareImg() {
        if (this.renderer != null) {
            // img = this.renderer.render(this);
        }
    }



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


    public void object2DPropertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName() == "y") {
            double y = (Double)evt.getNewValue();
            setY(y);
        }

        if (evt.getPropertyName() == "x") {
            double x = (Double)evt.getNewValue();
            setX(x);
        }
    }




    public long getMaxAge() {
        return -1;
    }

    public long getAge(long time) {
        return obj.getAge(time);
    }

    public long getAge() {
        return obj.getAge();
    }

    public boolean shouldExpire(long time) {
        if (getMaxAge() == -1) {
            return false;
        }
        return getAge(time) > getMaxAge();
    }

    public void expire () {
        this.state = LifecycleState.EXPIRED;
    }

    public void spawn() {
        this.state = LifecycleState.SPAWNED;
    }

    public LifecycleState getState() {
        return state;
    }

   /* public void addObjectLifecycleListener(ObjectLifecycleListener listener) {
        objectLifecycleListeners.add(listener);
    }

    protected void fireObjectLifecycleEvent(Object2D source, LifecycleState state) {
        for (ObjectLifecycleListener lst: objectLifecycleListeners) {
            lst.objectLifecycleChange(new ObjectLifecycleEvent(source, state));
        }
    }*/

}
