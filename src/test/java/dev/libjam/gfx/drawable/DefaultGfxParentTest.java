package dev.libjam.gfx.drawable;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.Answer;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class DefaultGfxParentTest {

    @SuppressWarnings("checkstyle:MagicNumber")
    @Test
    @DisplayName("DefaultGfxParent")
    void testDefaultGfxParent() {

        final int width = 800;
        final int height = 600;
        final int x = 1;
        final int y = 2;

        DefaultGfxParent p = new DefaultGfxParent();

        Assertions.assertInstanceOf(GfxParent.class, p);
        Assertions.assertInstanceOf(Parent.class, p);

        Assertions.assertEquals(0,  p.getWidth());
        Assertions.assertEquals(0,  p.getHeight());
        Assertions.assertEquals(0,  p.getX());
        Assertions.assertEquals(0,  p.getY());

        // setter
        Assertions.assertSame(p, p.setWidth(width).setHeight(height).setX(x).setY(y));
        Assertions.assertEquals(width,  p.getWidth());
        Assertions.assertEquals(height,  p.getHeight());
        Assertions.assertEquals(x,  p.getX());
        Assertions.assertEquals(y,  p.getY());


        p = new DefaultGfxParent(width, height);
        Assertions.assertEquals(width,  p.getWidth());
        Assertions.assertEquals(height,  p.getHeight());
        Assertions.assertEquals(0,  p.getX());
        Assertions.assertEquals(0,  p.getY());

        p = new DefaultGfxParent(x, y, width, height);
        Assertions.assertEquals(width,  p.getWidth());
        Assertions.assertEquals(height,  p.getHeight());
        Assertions.assertEquals(x,  p.getX());
        Assertions.assertEquals(y,  p.getY());

        Assertions.assertEquals(p.widthProperty().get(), p.getWidth());
        final double newWidth = 47;
        p.widthProperty().set(47);
        Assertions.assertEquals(newWidth, p.getWidth());
    }


    @Test
    @DisplayName("visible = false -> draw()")
    void testDrawVisibleFalse() {

        DefaultGfxParent pSpy = spy(new DefaultGfxParent());

        // not visible
        assertTrue(pSpy.isVisible());
        assertTrue(pSpy.visibleProperty().get());

        pSpy.setVisible(false);

        // make sure colors are set
        pSpy.setBgColor(Color.GREEN);
        pSpy.setBorderColor(Color.BLUE);


        // mock Graphics
        GraphicsContext g = mock(GraphicsContext.class);

        // draw - visible false
        when(pSpy.drawNode(g)).thenThrow(new AssertionError());
        when(pSpy.drawChildren(g)).thenThrow(new AssertionError());

        pSpy.draw(g);

        // draw - visible true
        reset(pSpy);
        pSpy.setVisible(true);
        AtomicBoolean drawn = new AtomicBoolean(false);
        when(pSpy.drawNode(g)).thenAnswer((Answer) invocation -> {drawn.set(true); return null;});
        pSpy.draw(g);
        assertTrue(drawn.get());

    }

    @Test
    @DisplayName("add")
    void testAdd() {

        DefaultGfxParent h1 = new DefaultGfxParent();
        DefaultGfxParent d = new DefaultGfxParent();

        DefaultGfxNode n = new DefaultGfxNode();

        assertTrue(n.isVisible());

        h1.setVisible(false);

        h1.add(n);

        assertFalse(n.isVisible());

        Assertions.assertSame(h1, n.getParent());

        Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> d.add(n));

    }

    @Test
    @DisplayName("children")
    void testChildren() {

        DefaultGfxParent d = new DefaultGfxParent();
        DefaultGfxParent d1 = new DefaultGfxParent();

        DefaultGfxNode d1_1 = new DefaultGfxNode();
        DefaultGfxNode d1_2 = new DefaultGfxNode();

        d.add(d1);

        List<GfxNode> d1Nodes_1 = d1.getChildren();

        d1.add(d1_1);
        d1.add(d1_2);

        // make sure children returned is copy
        List<GfxNode> d1Nodes_2 = d1.getChildren();
        Assertions.assertNotSame(d1Nodes_1, d1Nodes_2);


        Assertions.assertEquals(2, d1.getChildren().size());
        assertTrue(d1.getChildren().contains(d1_1));
        assertTrue(d1.getChildren().contains(d1_2));

    }

}