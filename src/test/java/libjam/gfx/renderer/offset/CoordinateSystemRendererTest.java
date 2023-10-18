package libjam.gfx.renderer.offset;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CoordinateSystemRendererTest {


    @Test
    @DisplayName("class instantiation")
    void testInstantiation() {

        final int offsetBottom = 10;
        final int offsetLeft = 12;

        final int zero = 0;

        CoordinateSystemRenderer r = new CoordinateSystemRenderer(
            offsetBottom, offsetLeft
        );

        // offsets
        Assertions.assertEquals(offsetBottom, r.getOffsetBottom());
        Assertions.assertEquals(offsetLeft, r.getOffsetLeft());
        Assertions.assertEquals(zero, r.getOffsetTop());
        Assertions.assertEquals(zero, r.getOffsetRight());

        // ranges
        Assertions.assertEquals(zero, r.getStartX());
        Assertions.assertEquals(zero, r.getStartY());
        Assertions.assertEquals(zero, r.getEndX());
        Assertions.assertEquals(zero, r.getEndY());
    }

    @Test
    @DisplayName("setRange")
    void testsetRange() {

        final int startX = 10;
        final int startY = 12;
        final int endX = 110;
        final int endY = 1312;

        CoordinateSystemRenderer r = new CoordinateSystemRenderer(1, 2);


        r.setRangeX(startX, endX);
        r.setRangeY(startY, endY);

        Assertions.assertEquals(startX, r.getStartX());
        Assertions.assertEquals(startY, r.getStartY());
        Assertions.assertEquals(endX, r.getEndX());
        Assertions.assertEquals(endY, r.getEndY());
    }

}