package dev.libjam.gfx.drawable;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class DefaultDrawableTest {

    @SuppressWarnings("checkstyle:MagicNumber")
    @Test
    @DisplayName("DefaultDrawable")
    void testDefaultDrawable() {

        final int width = 800;
        final int height = 600;
        final int x = 1;
        final int y = 2;

        DefaultDrawable d = new DefaultDrawable();
        Assertions.assertEquals(0,  d.getWidth());
        Assertions.assertEquals(0,  d.getHeight());
        Assertions.assertEquals(0,  d.getX());
        Assertions.assertEquals(0,  d.getY());

        // setter
        Assertions.assertSame(d, d.setWidth(width).setHeight(height).setX(x).setY(y));
        Assertions.assertEquals(width,  d.getWidth());
        Assertions.assertEquals(height,  d.getHeight());
        Assertions.assertEquals(x,  d.getX());
        Assertions.assertEquals(y,  d.getY());


        d = new DefaultDrawable(width, height);
        Assertions.assertEquals(width,  d.getWidth());
        Assertions.assertEquals(height,  d.getHeight());
        Assertions.assertEquals(0,  d.getX());
        Assertions.assertEquals(0,  d.getY());

        d = new DefaultDrawable(x, y, width, height);
        Assertions.assertEquals(width,  d.getWidth());
        Assertions.assertEquals(height,  d.getHeight());
        Assertions.assertEquals(x,  d.getX());
        Assertions.assertEquals(y,  d.getY());

        Assertions.assertEquals(d.widthProperty().get(), d.getWidth());
        final double newWidth = 47;
        d.widthProperty().set(47);
        Assertions.assertEquals(newWidth, d.getWidth());
    }


    @Test
    @DisplayName("visible = false -> draw()")
    void testDrawVisibleFalse() {

        DefaultDrawable d = new DefaultDrawable();


        // not visible
        Assertions.assertFalse(d.isVisible());
        // make sure colors are set
        d.setBgColor(Color.GREEN);
        d.setBorderColor(Color.BLUE);


        // mock Graphics
        GraphicsContext g = mock(GraphicsContext.class);

        // draw
        d.draw(g);

        verify(g, never()).fillRect(
            anyDouble(),
            anyDouble(),
            anyDouble(),
            anyDouble()
        );

        verify(g, never()).strokeRect(
            anyDouble(),
            anyDouble(),
            anyDouble(),
            anyDouble()
        );
    }


    @Test
    @DisplayName("visible = true -> draw()")
    void testDrawVisibleTrue() {

        DefaultDrawable d = new DefaultDrawable();


        // visible!
        d.setVisible(true);
        Assertions.assertTrue(d.isVisible());
        // make sure colors are set
        d.setBgColor(Color.GREEN);
        d.setBorderColor(Color.BLUE);


        // mock Graphics
        GraphicsContext g = mock(GraphicsContext.class);

        // draw
        d.draw(g);

        verify(g, times(1)).fillRect(
            anyDouble(),
            anyDouble(),
            anyDouble(),
            anyDouble()
        );

        verify(g, times(1)).strokeRect(
            anyDouble(),
            anyDouble(),
            anyDouble(),
            anyDouble()
        );
    }

}