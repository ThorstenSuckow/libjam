package libjam.gfx;


import libjam.math.Rectangle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SceneFrameTest {

    @SuppressWarnings("checkstyle:MagicNumber")
    @Test
    @DisplayName("SceneFrame")
    void testSceneFrame() {

        SceneFrame s = new SceneFrame(1, 2, 3, 4);

        Assertions.assertInstanceOf(Rectangle.class, s);

        Assertions.assertEquals(1, s.getX());
        Assertions.assertEquals(2, s.getY());
        Assertions.assertEquals(3, s.getWidth());
        Assertions.assertEquals(4, s.getHeight());
    }

}
