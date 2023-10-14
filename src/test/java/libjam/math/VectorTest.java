package libjam.math;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class VectorTest {

    @Test
    @DisplayName("Vector")
    void testGetVerticalVelocityAtTime() {

        Vector v1 = Vector.from(1, 2, 3);
        Vector v2 = Vector.from(4, 5, 6);

        Assertions.assertTrue(v1.equals(v1));
        Assertions.assertFalse(v1.equals(v2));

        Vector v1_1 = v1.setAt(0, 0);

        Assertions.assertFalse(v1_1 == v1);
        Assertions.assertFalse(v1_1.equals(v1));
        Assertions.assertEquals(v1_1.getAt(0), 0, 0);

        Assertions.assertEquals(v1_1.toString(), "V:[0.0, 2.0, 3.0]");
    }

}