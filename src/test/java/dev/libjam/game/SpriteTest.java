package dev.libjam.game;

import dev.libjam.gfx.drawable.GfxNode;
import dev.libjam.physx.Object2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class SpriteTest {

    @Test
    @DisplayName("Sprite")
    public void testSprite() {

        Object2D obj = new Object2D(100, 200);

        Sprite sp = new Sprite(obj);

        assertInstanceOf(GfxNode.class, sp);

        assertSame(obj, sp.getObject2D());
        assertNull(sp.getRenderer());

        assertEquals(-1, sp.getMaxAge());

        assertSame(LifecycleState.PREPARING, sp.getState());
    }

    @Test
    @DisplayName("draw - no renderer")
    public void testDrawWithoutRenderer() {

        Object2D obj = new Object2D(100, 200);


        Sprite sp = new Sprite(obj);

        assertNull(sp.getRenderer());


        // mock Graphics
        GraphicsContext g = mock(GraphicsContext.class);
        sp.drawNode(g);

        verify(g, never()).drawImage(
            Mockito.any(WritableImage.class),
            Mockito.anyDouble(),
            Mockito.anyDouble()
        );
    }


    @Test
    @DisplayName("draw - renderer")
    public void testDrawWithRenderer() {

        Object2D obj = new Object2D(100, 200);

        WritableImage img = new WritableImage(10,10);

        Sprite sp = new Sprite(obj, (Sprite sprite) -> img);

        assertNotNull(sp.getRenderer());

        // mock Graphics
        GraphicsContext g = mock(GraphicsContext.class);
        sp.drawNode(g);

        verify(g, atLeast(1)).drawImage(refEq(img), eq(sp.getX()), eq(sp.getY()));
    }
}
