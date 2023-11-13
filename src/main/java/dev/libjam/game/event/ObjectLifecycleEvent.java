package dev.libjam.game.event;

import dev.libjam.game.LifecycleState;
import dev.libjam.physx.Object2D;

public class ObjectLifecycleEvent {

    Object2D source;

    LifecycleState state;

    public ObjectLifecycleEvent(Object2D source, LifecycleState state) {
        this.source = source;
        this.state = state;
    }


    public LifecycleState getState() {
        return state;
    }
    public Object2D getSource() {
        return source;
    }

}
