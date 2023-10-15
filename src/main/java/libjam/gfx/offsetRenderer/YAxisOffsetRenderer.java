package libjam.gfx.offsetRenderer;

import java.awt.Color;
import java.awt.Graphics;

public class YAxisOffsetRenderer extends AxisRenderer {

    public YAxisOffsetRenderer(int offset) {
        super(offset, OffsetRenderer.LEFT);
    }


    @Override
    public void draw(final Graphics g) {

        int height = getCanvasContext().getHeight();
        int offset = getOffset();

        g.setColor(Color.CYAN);

        // y
        g.drawLine(offset,  0, offset, height);

        for (int i = start; i <= end; i += 5) {
            g.drawLine(
                    offset - (i % 10 == 0 ? 4 : 2),
                i,
                offset,
                i
            );
        }
    }


}
