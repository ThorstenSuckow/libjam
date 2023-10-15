package libjam.gfx.offsetRenderer;

import java.awt.Color;
import java.awt.Graphics;

public class XAxisOffsetRenderer extends AxisRenderer {

    private int start;

    private int end;



    public XAxisOffsetRenderer(int offset) {
        super(offset, OffsetRenderer.BOTTOM);
    }


    @Override
    public void draw(final Graphics g) {

        int width = getCanvasContext().getWidth();
        int offset = getOffset();

        int height = getCanvasContext().getHeight() - offset;

        g.setColor(Color.CYAN);

        g.setColor(Color.cyan);

        // x
        g.drawLine(0, height, 500, height);
        for (int i = 0; i <= width; i += 5) {
            g.drawLine(i, height + (i % 10 == 0 ? 4 : 2), i, height);
        }

    }


}
