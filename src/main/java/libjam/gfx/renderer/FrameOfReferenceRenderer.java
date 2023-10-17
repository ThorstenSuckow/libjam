package libjam.gfx.renderer;

import libjam.gfx.Camera;
import libjam.gfx.CanvasContext;
import libjam.gfx.Drawable;
import libjam.gfx.ReferenceRenderingContext;
import libjam.gfx.ScaledSceneFrame;
import libjam.gfx.event.CameraEvent;
import libjam.gfx.event.CameraListener;
import libjam.gfx.renderer.offset.CoordinateSystemRenderer;
import libjam.gfx.renderer.offset.OffsetRenderer;
import libjam.gfx.renderer.overlay.OverlayRenderer;
import libjam.gfx.renderer.overlay.VectorRenderer;
import libjam.physx.WorldObject;
import libjam.util.Logger;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class FrameOfReferenceRenderer implements Drawable, CameraListener {

    private final Camera camera;
    /**
     *
     */
    //FrameOfReference frameOfReference;


    private List<OffsetRenderer> offsetRenderer = new ArrayList<>();
    private CanvasContext canvasContext;

    private WorldRendererFactory worldRendererFactory;

    ReferenceRenderingContext renderingContext;

    /**
     * The scalingFactor required by this renderer.
     */
    double scalingFactor;

    /**
     *
     * @param camera
     * @param scalingFactor The initial scaling factor to pass to SceneFrames.
     */
    public FrameOfReferenceRenderer(Camera camera, double scalingFactor) {
        //this.frameOfReference = frameOfReference;

        this.camera = camera;

        camera.addCameraListener(this);

        this.scalingFactor = scalingFactor;
        this.renderingContext = new ReferenceRenderingContext(camera.getScaledSceneFrame(scalingFactor));
    }

    public void setWorldRendererFactory(WorldRendererFactory worldRendererFactory) {
        this.worldRendererFactory = worldRendererFactory;
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

        this.renderingContext.setCanvasContext(canvasContext);

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

    private WorldObject applyOverlays(Graphics g, WorldObject obj, ReferenceRenderingContext renderingContext) {

        for (OverlayRenderer overlayRenderer: getOverlayRenderer(obj)) {
            overlayRenderer.draw(g, obj, renderingContext);
        }

        return obj;
    }




    private void renderObjects(Graphics g) {

        if (worldRendererFactory != null) {

            ObjectRenderer objRenderer;

            for (WorldObject obj : camera.getObjectsInScene()) {
                objRenderer = worldRendererFactory.getRenderer(obj);
                applyOverlays(
                        g,
                        objRenderer.draw(g, obj, renderingContext),
                        renderingContext
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

                ScaledSceneFrame sceneFrame = renderingContext.getScaledSceneFrame();

                // X-Axis
                int rangeStartX = (int) sceneFrame.getX();
                int rangeEndX = (int) sceneFrame.getWidth();
                axisRenderer.setRangeX(rangeStartX, rangeEndX);

                Logger.log(rangeStartX+ " "+rangeEndX);

                /**
                 * width must be adjusted if width exceedsd acanvas
                 */
                //double newWidth = frameOfReference.getObservedWidth(Unit.PIXEL) - axisRenderer.getOffsetLeft();
               // frameOfReference.setObservedWidth(newWidth, Unit.PIXEL);

                offsetLeft += axisRenderer.getOffsetLeft();
                offsetBottom += axisRenderer.getOffsetBottom();


                // Y-Axis
                int rangeStartY = (int) sceneFrame.getY();
                int rangeEndY = (int) sceneFrame.getHeight();

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

        renderingContext.setOffsetLeft(offsetLeft);
        renderingContext.setOffsetBottom(offsetBottom);

    }

    @Override
    public void cameraActionPerformed(CameraEvent e) {

        Logger.log("moved: " + camera.getX() +" "+ camera.getY());
        this.renderingContext.setScaledSceneFrame(
                camera.getScaledSceneFrame(scalingFactor)
        );
        this.updateOffsetRenderer();
    }
}
