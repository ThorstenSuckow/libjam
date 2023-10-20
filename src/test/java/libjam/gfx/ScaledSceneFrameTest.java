package libjam.gfx;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScaledSceneFrameTest {

    @SuppressWarnings("checkstyle:MagicNumber")
    @Test
    @DisplayName("SceneFrame")
    void testSceneFrame() {

        double scalingFactor = 0.0543;

        ScaledSceneFrame s = new ScaledSceneFrame(1, 2, 3, 4, scalingFactor);

        Assertions.assertInstanceOf(SceneFrame.class, s);

        Assertions.assertEquals(1, s.getX());
        Assertions.assertEquals(2, s.getY());
        Assertions.assertEquals(3, s.getWidth());
        Assertions.assertEquals(4, s.getHeight());

        Assertions.assertEquals(scalingFactor, s.getScalingFactor());
    }

}
