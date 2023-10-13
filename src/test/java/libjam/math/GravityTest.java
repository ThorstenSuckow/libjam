package libjam.math;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GravityTest {



    @Test
    @DisplayName("getVerticalVelocity")
    void testGetVerticalVelocityAtTime() {


        double initialVelocity = 29;
        double angle = 90;
        double time = 0.1;
        double expected = 28.019;

        double v = Gravity.getVerticalVelocityAtTime(time, angle, initialVelocity);
        Assertions.assertEquals(expected, v,0.1);


        initialVelocity = 29;
        angle = 90;
        time = 2.9561;
        expected = 0.000659;

        v = Gravity.getVerticalVelocityAtTime(time, angle, initialVelocity);
        Assertions.assertEquals(expected,v,0.1);

        initialVelocity = 29;
        angle = 90;

        initialVelocity = Gravity.getVerticalVelocityAtTime(2, angle, initialVelocity);
        time = 0.9561;
        expected = 0.000659;

        v = Gravity.getVerticalVelocityAtTime(time, angle, initialVelocity);
        Assertions.assertEquals(expected, v,0.01);


        initialVelocity = 0;
        angle = 270;
        time = 3;
        expected = Gravity.G_EARTH * time;

        v = Gravity.getVerticalVelocityAtTime(time, angle, initialVelocity);
        Assertions.assertEquals(expected, v,0.1);

        initialVelocity = 29;
        angle = 56;
        time = 2.31;
        expected = 1.38;

        v = Gravity.getVerticalVelocityAtTime(time, angle, initialVelocity);
        Assertions.assertEquals(expected, v, 0.01);


        initialVelocity = 29;
        angle = 56;
        initialVelocity = Gravity.getVerticalVelocityAtTime(1.5, angle, initialVelocity);
        time = 0.81;
        expected = -0.2136;
        v = Gravity.getVerticalVelocityAtTime(time, angle, initialVelocity);
        Assertions.assertEquals(expected, v, 0.01);
    }


    @Test
    @DisplayName("getVerticalDistanceAtTime")
    void testGetVerticalDistanceAtTime() {


        double verticalVelocity = 46.4;
        double initialHeight = 0;
        double time = 0.81;
        double expected = 34.38;

        double h = Gravity.getVerticalDistanceAtTime(time, verticalVelocity, initialHeight);
        Assertions.assertEquals(expected, h, 0.1);

        verticalVelocity = 18;
        initialHeight = 0;
        time = 3;
        expected = 9.855;

        h = Gravity.getVerticalDistanceAtTime(time, verticalVelocity, initialHeight);
        Assertions.assertEquals(expected, h, 0.1);


        verticalVelocity = 180;
        initialHeight = 0;
        time = 12;
        expected = 1453.68;

        h = Gravity.getVerticalDistanceAtTime(time, verticalVelocity, initialHeight);
        Assertions.assertEquals(expected, h, 0.1);


        verticalVelocity = 180;
        initialHeight = 0;
        time = 7;

        initialHeight = Gravity.getVerticalDistanceAtTime(time, verticalVelocity, initialHeight);
        verticalVelocity = Gravity.getVerticalVelocityAtTime(time, 90, verticalVelocity);

        time = 5;
        expected = 1453.68;
        h = Gravity.getVerticalDistanceAtTime(time, verticalVelocity, initialHeight);
        Assertions.assertEquals(expected, h, 0.1);


    }




}