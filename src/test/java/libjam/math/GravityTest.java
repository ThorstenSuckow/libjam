package libjam.math;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

class GravityTest {

    @Test
    @DisplayName("computeTerminalSpeed")
    void testTerminalSpeed()  {
        DecimalFormat df = new DecimalFormat("####0.00");

        double v = Gravity.computeTerminalSpeed(
            80,
                Gravity.G_EARTH,
                1.2,
                1,
                0.3 * 1.8
        );

        Assertions.assertEquals(
            new BigDecimal(v).setScale(2, RoundingMode.HALF_UP).doubleValue(),
            49.22,
                0.001
        );
    }

    @Test
    @DisplayName("computeVelocityForDuration")
    void testVelocityForDuration() {

        double terminalVelocity = 49.22;

        double v = Gravity.computeVelocityForDuration(1, terminalVelocity, Gravity.G_EARTH);

        Assertions.assertEquals(
                new BigDecimal(v).setScale(2, RoundingMode.HALF_UP).doubleValue(),
                9.68,
                0.001
        );

        v = Gravity.computeVelocityForDuration(40, terminalVelocity, Gravity.G_EARTH);
        Assertions.assertEquals(
                terminalVelocity,
                new BigDecimal(v).setScale(2, RoundingMode.HALF_UP).doubleValue(),
                0.001
        );

    }

    @Test
    @DisplayName("computeVerticalDistanceTravelled")
    void testComputeVerticalDistanceTravelled() {

        double terminalVelocity = 56;

        double p = Gravity.computeVerticalDistanceTravelled(
                10, terminalVelocity, Gravity.G_EARTH
        );

        Assertions.assertEquals(
                 347.9,
                 new BigDecimal(p).setScale(2, RoundingMode.HALF_UP).doubleValue(),
                0.1
        );

        p = Gravity.computeVerticalDistanceTravelled(
                12, terminalVelocity, Gravity.G_EARTH
        );

        Assertions.assertEquals(
                455.15,
                new BigDecimal(p).setScale(21, RoundingMode.HALF_UP).doubleValue(),
                0.1
        );
    }


}