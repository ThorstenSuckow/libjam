package libjam.physx.physics;


import libjam.util.Unit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
                physicEngine,
                200,
                400,
                WORLD_SCALE
        );
    }


    @Test
    @DisplayName("testGetHeight")
    void testGetHeight() {

        double height = frame.getHeight();

        Assertions.assertEquals(400, height, 0);

        double heightInPixels = frame.getHeight(Unit.PIXEL);
        Assertions.assertEquals(40000, heightInPixels, 0);
    }


    @Test
    @DisplayName("testGetWidth")
    void testGetWidth() {

        double width = frame.getWidth();
        Assertions.assertEquals(200, width, 0);

        double widthInPixels = frame.getWidth(Unit.PIXEL);
        Assertions.assertEquals(20000, widthInPixels, 0);
    }

    @Test
    @DisplayName("scaleToUnitValue")
    void testScaleToUnitValue() {

        Assertions.assertEquals(
            100 * WORLD_SCALE,
            frame.scaleToUnitValue(100, Unit.PIXEL, Unit.METER),
            0);


        Assertions.assertEquals(
                100,
                frame.scaleToUnitValue(100, Unit.PIXEL, Unit.METER, 1),
                0);

        Assertions.assertEquals(
                200,
                frame.scaleToUnitValue(100, Unit.PIXEL, Unit.METER, 2),
                0);

        Assertions.assertEquals(
                50,
                frame.scaleToUnitValue(100, Unit.METER, Unit.PIXEL, 2),
                0);


    }


}