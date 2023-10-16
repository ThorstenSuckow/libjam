package libjam.gfx;

import libjam.gfx.offsetRenderer.CoordinateSystemRenderer;
import libjam.gfx.offsetRenderer.OffsetRenderer;
import libjam.physx.physics.FrameOfReference;
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


    public void addOffsetRenderer(final OffsetRenderer renderer) {

        if (renderer instanceof CoordinateSystemRenderer axisRenderer) {

            // X-Axis
            int rangeStartX = (int) frameOfReference.getObservedX(Unit.PIXEL);
            int rangeEndX = (int) frameOfReference.getObservedWidth(Unit.PIXEL);

            axisRenderer.setRangeX(rangeStartX, rangeEndX);

            double newWidth = frameOfReference.getObservedWidth(Unit.PIXEL) - axisRenderer.getOffsetLeft();
            frameOfReference.setObservedWidth(newWidth, Unit.PIXEL);

            offsetX += axisRenderer.getOffsetLeft();


            // Y-Axis
            int rangeStartY = (int) frameOfReference.getObservedY(Unit.PIXEL);
            int rangeEndY = (int) frameOfReference.getObservedHeight(Unit.PIXEL);

            axisRenderer.setRangeY(rangeStartY, rangeEndY);

            double oldHeight = frameOfReference.getObservedHeight(Unit.PIXEL);
            double newHeight = oldHeight - axisRenderer.getOffsetBottom();
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
