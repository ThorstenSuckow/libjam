package libjam.gfx;

import libjam.gfx.offsetRenderer.AxisRenderer;
import libjam.gfx.offsetRenderer.OffsetRenderer;
import libjam.physx.physics.FrameOfReference;
import libjam.util.Logger;
import libjam.util.Unit;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class FrameOfReferenceRenderer implements Drawable {

    /**
     *
     */
    FrameOfReference frameOfReference;


    private List<OffsetRenderer> offsetRenderer = new ArrayList<>();
    private CanvasContext canvasContext;


    public FrameOfReferenceRenderer(
            final FrameOfReference frameOfReference
    ) {
        this.frameOfReference = frameOfReference;
    }

    private int offsetX;


    public void addOffsetRenderer(OffsetRenderer renderer) {

        if (renderer.getPosition() == OffsetRenderer.BOTTOM) {

            int rangeStart = (int) frameOfReference.getObservedX(Unit.PIXEL);
            int rangeEnd = (int) frameOfReference.getObservedWidth(Unit.PIXEL);

            if (renderer instanceof AxisRenderer) {
                ((AxisRenderer) renderer).setRange(rangeStart, rangeEnd);
            }

            double newWidth = frameOfReference.getObservedWidth(Unit.PIXEL) - renderer.getOffset();
            frameOfReference.setObservedWidth(newWidth, Unit.PIXEL);

            Logger.log(frameOfReference.getObservedWidth(Unit.PIXEL) + " <- width");
            offsetX += renderer.getOffset();

        } else if (renderer.getPosition() == OffsetRenderer.LEFT) {

            int rangeStart = (int) frameOfReference.getObservedY(Unit.PIXEL);
            int rangeEnd = (int) frameOfReference.getObservedHeight(Unit.PIXEL);

            if (renderer instanceof AxisRenderer) {
                ((AxisRenderer) renderer).setRange(rangeStart, rangeEnd);
            }

            double oldHeight = frameOfReference.getObservedHeight(Unit.PIXEL);
            Logger.log("rangeStart " + rangeStart + "- " + rangeEnd );
            double newHeight = oldHeight - renderer.getOffset();
            frameOfReference.setObservedHeight(newHeight, Unit.PIXEL);
        }

        offsetRenderer.add(renderer);

    }

    @Override
    public void draw(final Graphics g) {


        g.setColor(Color.GRAY);


       /* g.fillRect(
                (int) frameOfReference.getObservedX(Unit.PIXEL) + offsetX,
                (int) frameOfReference.getObservedY(Unit.PIXEL),
                (int) frameOfReference.getObservedWidth(Unit.PIXEL),
                (int)  frameOfReference.getObservedHeight(Unit.PIXEL)
        );*/

       /* g.setColor(Color.RED);
        g.drawRect(
                (int) frameOfReference.getObservedX(Unit.PIXEL) + offsetX,
                (int) frameOfReference.getObservedY(Unit.PIXEL),
                (int) frameOfReference.getObservedWidth(Unit.PIXEL),
                (int)  frameOfReference.getObservedHeight(Unit.PIXEL)
        );*/

        for (OffsetRenderer offRenderer: offsetRenderer) {
            offRenderer.draw(g);
        }

    }

    @Override
    public void setCanvasContext(CanvasContext canvasContext) {
        this.canvasContext = canvasContext;
        for (OffsetRenderer offRenderer: offsetRenderer) {
            offRenderer.setCanvasContext(canvasContext);
        }

    }
}
