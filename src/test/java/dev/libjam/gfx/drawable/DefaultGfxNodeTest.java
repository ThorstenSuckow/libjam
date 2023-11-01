package dev.libjam.gfx.drawable;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.Answer;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class DefaultGfxNodeTest {

    @SuppressWarnings("checkstyle:MagicNumber")
    @Test
    @DisplayName("DefaultGfxNode")
    void testDefaultGfxNode() {

        final int width = 800;
        final int height = 600;
        final int x = 1;
        final int y = 2;

        DefaultGfxNode d = new DefaultGfxNode();

        Assertions.assertInstanceOf(GfxNode.class, d);

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


        d = new DefaultGfxNode(width, height);
        Assertions.assertEquals(width,  d.getWidth());
        Assertions.assertEquals(height,  d.getHeight());
        Assertions.assertEquals(0,  d.getX());
        Assertions.assertEquals(0,  d.getY());

        d = new DefaultGfxNode(x, y, width, height);
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

        DefaultGfxNode dSpy =  spy(new DefaultGfxNode());

        // not visible
        Assertions.assertTrue(dSpy.isVisible());
        Assertions.assertTrue(dSpy.visibleProperty().get());

        dSpy.setVisible(false);

        // make sure colors are set
        dSpy.setBgColor(Color.GREEN);
        dSpy.setBorderColor(Color.BLUE);


        // mock Graphics
        GraphicsContext g = mock(GraphicsContext.class);

        // draw - visible false
        when(dSpy.drawNode(g)).thenThrow(new AssertionError());
        dSpy.draw(g);

        // draw - visible true
        reset(dSpy);
        dSpy.setVisible(true);
        AtomicBoolean drawn = new AtomicBoolean(false);
        when(dSpy.drawNode(g)).thenAnswer((Answer) invocation -> {drawn.set(true); return null;});
        dSpy.draw(g);
        Assertions.assertTrue(drawn.get());

    }


}