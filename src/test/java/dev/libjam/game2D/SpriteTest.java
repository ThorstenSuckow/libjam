package dev.libjam.game2D;

import dev.libjam.gfx.drawable.GfxNode;
import javafx.beans.property.ReadOnlyObjectProperty;
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
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
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

        GameObject gameObject = new GameObject(100, 200);
        Sprite sp = new Sprite(gameObject);

        assertInstanceOf(GfxNode.class, sp);

        assertSame(gameObject, sp.getGameObject());
        assertSame(sp, sp.getGameObject().getSprite());

        assertNull(sp.getRenderer());
        assertNull(sp.getImage());

        assertEquals(-1, sp.getMaxAge());

        assertSame(LifecycleState.PREPARING, sp.getLifecycleState());
    }

    @Test
    @DisplayName("Sprite constructor exception")
    public void testSpriteConstructorException() {

        GameObject gameObject = new GameObject(100, 200);
        Sprite sp = new Sprite(gameObject);

        assertThrowsExactly(IllegalArgumentException.class, () -> {
            new Sprite(gameObject);
        });
    }


    @Test
    @DisplayName("setGameObject")
    public void testSetGameObject() {

        GameObject gameObject = new GameObject(100, 200);
        Sprite sp = new Sprite();
        Sprite sp2 = new Sprite();

        sp.setGameObject(gameObject);

        assertSame(gameObject, sp.getGameObject());

        assertThrowsExactly(IllegalArgumentException.class, () -> {
            sp2.setGameObject(gameObject);
        });

        assertThrowsExactly(IllegalStateException.class, () -> {
            sp.setGameObject(new GameObject(100, 100));
        });

    }


    @Test
    @DisplayName("draw - no renderer")
    public void testDrawWithoutRenderer() {

        Sprite sp = new Sprite(new GameObject(100, 200));

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

        WritableImage img = new WritableImage(10,10);
        Sprite sp = new Sprite(new GameObject(100, 200), (Sprite sprite) -> img);

        assertNotNull(sp.getRenderer());

        // mock Graphics
        GraphicsContext g = mock(GraphicsContext.class);
        sp.drawNode(g);

        verify(g, atLeast(1)).drawImage(refEq(img), eq(sp.getX()), eq(sp.getY()));

        assertSame(img, sp.getImage());
    }


    @Test
    @DisplayName("LifecycleProperty")
    public void testLifecycleProperty() {

        Sprite sp = new Sprite(new GameObject(100, 200));

        assertInstanceOf(ReadOnlyObjectProperty.class, sp.lifecycleStateProperty());

        assertSame(LifecycleState.PREPARING, sp.lifecycleStateProperty().get());

        assertSame(sp, sp.lifecycleStateProperty().getBean());

        assertSame("lifecycleState", sp.lifecycleStateProperty().getName());
    }


    @Test
    @DisplayName("setLifecycleState")
    public void testSetLifecycleState() {

        Sprite sp = new Sprite(new GameObject(100, 200));

        sp.setLifecycleState(LifecycleState.SPAWNING);
        assertSame(LifecycleState.SPAWNING, sp.getLifecycleState());

        assertThrowsExactly(IllegalArgumentException.class, () -> sp.setLifecycleState(LifecycleState.PREPARING));

        // SPAWNING -> LIVING
        sp.setLifecycleState(LifecycleState.LIVING);
        assertSame(LifecycleState.LIVING, sp.getLifecycleState());

        // LIVING -> SPAWNING - Exception
        assertThrowsExactly(IllegalArgumentException.class, () -> sp.setLifecycleState(LifecycleState.SPAWNING));

        // LIVING -> EXPIRING
        sp.setLifecycleState(LifecycleState.EXPIRING);
        assertSame(LifecycleState.EXPIRING, sp.getLifecycleState());

        // EXPIRING -> LIVING - Exception
        sp.setLifecycleState(LifecycleState.LIVING);
        assertSame(LifecycleState.LIVING, sp.getLifecycleState());

        // LIVING -> VOID
        sp.setLifecycleState(LifecycleState.VOID);

        // VOID -> EXPIRING - Exception
        assertThrowsExactly(IllegalArgumentException.class, () -> sp.setLifecycleState(LifecycleState.EXPIRING));

        // VOID -> VOID
        sp.setLifecycleState(LifecycleState.VOID);
    }

}
