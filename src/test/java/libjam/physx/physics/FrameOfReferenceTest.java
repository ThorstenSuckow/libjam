package libjam.physx.physics;


import libjam.util.Unit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.geom.Rectangle2D;

class FrameOfReferenceTest {

    private static final double WORLD_SCALE = 0.01;

    PhysicEngine physicEngine = new PhysicEngine() {
        @Override
        public double getVerticalDistanceAtTime(double time, double verticalVelocity, double initialHeight) {
            return 0;
        }

        @Override
        public double getVerticalVelocityAtTime(double time, double alpha, double velocity) {
            return 0;
        }
    };

    FrameOfReference frame;

    @BeforeEach
    void createObj() {
        frame = new FrameOfReference(
                WORLD_SCALE,
                new Rectangle2D.Double(0, 0,200d, 400d)
        );
    }


    @Test
    @DisplayName("testGetHeight")
    void testGetHeight() {

        double height = frame.getObservedHeight();

        Assertions.assertEquals(400, height, 0);

        double heightInPixels = frame.getObservedHeight(Unit.PIXEL);
        Assertions.assertEquals(40000, heightInPixels, 0);
    }


    @Test
    @DisplayName("testGetWidth")
    void testGetWidth() {

        double width = frame.getObservedWidth();
        Assertions.assertEquals(200, width, 0);

        double widthInPixels = frame.getObservedWidth(Unit.PIXEL);
        Assertions.assertEquals(20000, widthInPixels, 0);
    }

    @Test
    @DisplayName("scaleToUnitValue")
    void testScaleToUnitValue() {

        Assertions.assertEquals(
            100 * WORLD_SCALE,
            frame.transformScaleValueToUnit(100, Unit.PIXEL, Unit.METER),
            0);


        Assertions.assertEquals(
                100,
                frame.transformScaleValueToUnit(100, Unit.PIXEL, Unit.METER, 1),
                0);

        Assertions.assertEquals(
                200,
                frame.transformScaleValueToUnit(100, Unit.PIXEL, Unit.METER, 2),
                0);

        Assertions.assertEquals(
                50,
                frame.transformScaleValueToUnit(100, Unit.METER, Unit.PIXEL, 2),
                0);


    }


}