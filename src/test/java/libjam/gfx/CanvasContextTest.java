package libjam.gfx;


import libjam.math.Rectangle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CanvasContextTest {

    @SuppressWarnings("checkstyle:MagicNumber")
    @Test
    @DisplayName("CanvasContext")
    void testCanvasContext() {

        CanvasContext c = new CanvasContext(800, 600);

        Assertions.assertEquals(800, c.getWidth());
        Assertions.assertEquals(600, c.getHeight());

        Assertions.assertTrue((new Rectangle(0, 0, 800, 600)).equals(c.getSafeArea()));
    }


}
