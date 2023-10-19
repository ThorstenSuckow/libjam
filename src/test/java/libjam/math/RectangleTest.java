package libjam.math;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RectangleTest {

    @SuppressWarnings("checkstyle:MagicNumber")
    @Test
    @DisplayName("Rectangle")
    void testRectangle() {

        Rectangle r = new Rectangle(1, 2, 3, 4);

        Assertions.assertEquals(1, r.getX());
        Assertions.assertEquals(2, r.getY());
        Assertions.assertEquals(3, r.getWidth());
        Assertions.assertEquals(4, r.getHeight());
    }


    @SuppressWarnings("checkstyle:MagicNumber")
    @Test
    @DisplayName("contains")
    void testContains() {

        Rectangle r1 = new Rectangle(1, 2, 3, 4);
        Rectangle r2 = new Rectangle(1, 2, 2.5, 3);

        Assertions.assertTrue(r1.contains(r2));

        Rectangle r3 = new Rectangle(1, 2, 5, 6);
        Assertions.assertFalse(r1.contains(r3));
    }


    @SuppressWarnings("checkstyle:MagicNumber")
    @Test
    @DisplayName("intersects")
    void testIntersects() {

        Rectangle r1 = new Rectangle(1, 1, 3, 4);
        Rectangle r2 = new Rectangle(2, 2, 1, 1);

        Assertions.assertFalse(r1.intersects(r2));

        Rectangle r3 = new Rectangle(1, 2, 5, 6);
        Assertions.assertTrue(r1.intersects(r3));
    }

}
