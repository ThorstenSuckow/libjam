package dev.libjam.gfx.canvas;

import dev.libjam.gfx.drawable.DefaultGfxNode;
import dev.libjam.gfx.drawable.DefaultGfxParent;
import dev.libjam.gfx.drawable.GfxNode;
import dev.libjam.gfx.drawable.GfxRoot;
import dev.libjam.gfx.drawable.Parent;
import javafx.beans.property.ReadOnlyListProperty;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class JavaFxCanvasTest {

    @SuppressWarnings("checkstyle:MagicNumber")
    @Test
    @DisplayName("JavaFxCanvas")
    void testJavaFxCanvas() {

        JavaFxCanvas c = new JavaFxCanvas();
        Assertions.assertInstanceOf(GfxRoot.class, c);
        Assertions.assertInstanceOf(Parent.class, c);

    }

    @Test
    @DisplayName("add")
    void testAdd() {

        JavaFxCanvas h1 = new JavaFxCanvas();
        DefaultGfxParent d = new DefaultGfxParent();

        DefaultGfxNode n = new DefaultGfxNode();

        h1.add(n);

        Assertions.assertSame(h1, n.getParent());

        Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> d.add(n));

    }

    @Test
    @DisplayName("children")
    void testChildren() {

        JavaFxCanvas d = new JavaFxCanvas();
        DefaultGfxParent d1 = new DefaultGfxParent();

        DefaultGfxNode d1_1 = new DefaultGfxNode();
        DefaultGfxNode d1_2 = new DefaultGfxNode();

        d.add(d1);

        List<GfxNode> d1Nodes_1 = d1.getChildren();

        d1.add(d1_1);
        d1.add(d1_2);

        // make sure children returned is copy
        ReadOnlyListProperty<GfxNode> d1Nodes_2 = d1.getChildren();
        Assertions.assertSame(d1Nodes_1, d1Nodes_2);


        Assertions.assertEquals(2, d1.getChildren().size());
        Assertions.assertTrue(d1.getChildren().contains(d1_1));
        Assertions.assertTrue(d1.getChildren().contains(d1_2));

    }

}
