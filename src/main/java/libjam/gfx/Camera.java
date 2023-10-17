package libjam.gfx;

import libjam.gfx.event.CameraEvent;
import libjam.gfx.event.CameraListener;
import libjam.math.Rectangle;
import libjam.physx.World;
import libjam.physx.WorldObject;
import libjam.physx.physics.FrameOfReference;
import libjam.util.Unit;

import java.util.ArrayList;
import java.util.List;


/**
 *
 */
public class Camera {

    World world;

    FrameOfReference frameOfReference;

    ScaledSceneFrame sceneFrame;

    public Camera(FrameOfReference frameOfReference) {

        this.frameOfReference = frameOfReference;
        this.world = frameOfReference.getObservedWorld();
    }

    List<CameraListener> cameraListeners = new ArrayList<CameraListener>();


    public Camera addCameraListener(CameraListener cameraListener) {
        cameraListeners.add(cameraListener);
        return this;
    }

    private Camera fireCameraEvent(CameraEvent ca) {
        for (CameraListener listener: cameraListeners) {
            listener.cameraActionPerformed(ca);
        }
        return this;
    }


    public double getX() {
        return frameOfReference.getObservedX();
    }


    public double getY() {
        return frameOfReference.getObservedY();
    }

    public Camera addX(double x) {
        frameOfReference.setObservedX(frameOfReference.getObservedX() + x);
        sceneFrame = null;
        fireCameraEvent(new CameraEvent(this));
        return this;
    }


    public Camera addY(double y) {
        frameOfReference.setObservedY(frameOfReference.getObservedY() + y);
        sceneFrame = null;
        fireCameraEvent(new CameraEvent(this));
        return this;
    }


    public double getImageWidth() {
        return frameOfReference.getObservedWidth();
    }

    public double getImageHeight() {
        return frameOfReference.getObservedHeight();
    }

    /**
     * Returns a scene Frame scaled to scalingFactor.
     * The scaling factor is a positive double value that represents the number
     * of
     *
     * @param scalingFactor the number of meters per pixel for scaling.
     *
     * @return the sceneFrame
     */
    public ScaledSceneFrame getScaledSceneFrame(double scalingFactor) {
        if (sceneFrame == null) {
            sceneFrame = new ScaledSceneFrame(
                Unit.toPixel(getX(), scalingFactor),
                Unit.toPixel(getY(), scalingFactor),
                Unit.toPixel(getImageWidth(), scalingFactor),
                Unit.toPixel(getImageHeight(), scalingFactor),
                scalingFactor
            );
        }
        return sceneFrame;
    }

    /**
     * Returns the scene frame with the values form the associated frameOfReference
     *
     * @return the sceneFrame
     */
    public SceneFrame getSceneFrame() {
        return new SceneFrame(getX(), getY(), getImageWidth(), getImageHeight());
    }



    public List<WorldObject> getObjectsInScene() {

        List<WorldObject> result = new ArrayList<WorldObject>();

        List<WorldObject> list = world.getObjects();

        for (WorldObject obj: list) {

            Rectangle rect = obj.getRectangle();

            if (getSceneFrame().contains(rect) || getSceneFrame().intersects(rect)) {
                result.add(obj);
            }
        }

        return result;
    }



}
