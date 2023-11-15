package dev.libjam.game;

import dev.libjam.physx.Object2D;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SpriteTest {

    @Test
    @DisplayName("Sprite")
    public void testSprite() {

        Object2D obj = new Object2D(100, 200);

        Sprite sp = new Sprite(obj);

        assertSame(obj, sp.getObject2D());
        assertNull(sp.getRenderer());

        assertEquals(-1, sp.getMaxAge());

        assertSame(LifecycleState.PREPARING, sp.getState());
    }


}
