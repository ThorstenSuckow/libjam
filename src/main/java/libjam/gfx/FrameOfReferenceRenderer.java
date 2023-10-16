package libjam.gfx;

import libjam.gfx.offsetRenderer.CoordinateSystemRenderer;
import libjam.gfx.offsetRenderer.OffsetRenderer;
import libjam.gfx.renderer.ObjectRenderer;
import libjam.gfx.renderer.WorldRendererFactory;
import libjam.gfx.renderer.overlay.OverlayRenderer;
import libjam.gfx.renderer.overlay.VectorRenderer;
import libjam.physx.World;
import libjam.physx.WorldObject;
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

    private WorldRendererFactory worldRendererFactory;

    ReferenceRenderingContext referenceRenderingContext;

    public void setWorldRendererFactory(WorldRendererFactory worldRendererFactory) {
        this.worldRendererFactory = worldRendererFactory;
    }


    public FrameOfReferenceRenderer(
            final FrameOfReference frameOfReference
    ) {
        this.frameOfReference = frameOfReference;

        this.referenceRenderingContext = new ReferenceRenderingContext(
                frameOfReference.getObservedFrame(),
                frameOfReference.getScale()
        );
    }

    public void addOffsetRenderer(final OffsetRenderer renderer) {
        offsetRenderer.add(renderer);
    }

    @Override
    public void draw(final Graphics g) {


        g.setColor(Color.GRAY);

        renderObjects(g);


        for (OffsetRenderer offRenderer: offsetRenderer) {
            offRenderer.draw(g);
        }

    }



    @Override
    public void setCanvasContext(CanvasContext canvasContext) {
        this.canvasContext = canvasContext;

        this.referenceRenderingContext.setCanvasContext(canvasContext);

        for (OffsetRenderer offRenderer: offsetRenderer) {
            offRenderer.setCanvasContext(canvasContext);
        }

        this.updateOffsetRenderer();

    }

    private List<OverlayRenderer> overlayList;
    private List<OverlayRenderer> getOverlayRenderer(WorldObject obj) {

        if (overlayList == null) {
            overlayList = new ArrayList<OverlayRenderer>();

            overlayList.add(new VectorRenderer());
        }

        return overlayList;
    }

    private WorldObject applyOverlays(Graphics g, WorldObject obj, ReferenceRenderingContext referenceRenderingContext) {

        for (OverlayRenderer overlayRenderer: getOverlayRenderer(obj)) {
            overlayRenderer.draw(g, obj, referenceRenderingContext);
        }

        return obj;
    }




    private void renderObjects(Graphics g) {

        if (worldRendererFactory != null) {
            World world = frameOfReference.getObservedWorld();

            ObjectRenderer objRenderer;
            for (WorldObject obj : world.getObjects()) {

                if (!frameOfReference.canObserve(obj)) {
                    Logger.log("Cannot observe " + obj.getX() + " " + obj.getY() + " " + obj.getWidth() + " " + obj.getHeight());
                    continue;
                }

                objRenderer = worldRendererFactory.getRenderer(obj);
                applyOverlays(
                        g,
                        objRenderer.draw(g, obj, referenceRenderingContext),
                        referenceRenderingContext
                );

            }
        } else {
            Logger.log("worldRendererFactory is null.");
        }

    }


    private void updateOffsetRenderer() {

        int offsetLeft = 0;
        int offsetBottom = 0;

        for (OffsetRenderer renderer: offsetRenderer) {

            if (renderer instanceof CoordinateSystemRenderer axisRenderer) {

                // X-Axis
                int rangeStartX = (int) frameOfReference.getObservedX(Unit.PIXEL);
                int rangeEndX = (int) frameOfReference.getObservedWidth(Unit.PIXEL);
                axisRenderer.setRangeX(rangeStartX, rangeEndX);

                /**
                 * width must be adjusted if width exceedsd acanvas
                 */
                //double newWidth = frameOfReference.getObservedWidth(Unit.PIXEL) - axisRenderer.getOffsetLeft();
               // frameOfReference.setObservedWidth(newWidth, Unit.PIXEL);

                offsetLeft += axisRenderer.getOffsetLeft();
                offsetBottom += axisRenderer.getOffsetBottom();


                // Y-Axis
                int rangeStartY = (int) frameOfReference.getObservedY(Unit.PIXEL);
                int rangeEndY = (int) frameOfReference.getObservedHeight(Unit.PIXEL);

                axisRenderer.setRangeY(rangeStartY, rangeEndY);

                /*
                    new height must be adjusted if it exceeds the canvas
                double newHeight =
                        frameOfReference.getObservedHeight(Unit.PIXEL)
                        - axisRenderer.getOffsetBottom()
                        - (int) frameOfReference.getObservedX(Unit.PIXEL);
                */
                //double newHeight = oldHeight - axisRenderer.getOffsetBottom();
               // frameOfReference.setObservedHeight(newHeight, Unit.PIXEL);
            }
        }

        referenceRenderingContext.setOffsetLeft(offsetLeft);
        referenceRenderingContext.setOffsetBottom(offsetBottom);

    }

}
