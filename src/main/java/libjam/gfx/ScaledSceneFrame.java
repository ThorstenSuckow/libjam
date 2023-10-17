package libjam.gfx;

/**
 * A SceneFrame that has its values scaled according to scalingFactor.
 *
 * <pre>
 *     +-----------------------------+
 *     |     +----------------+      |
 *     |     |                |      |
 *     |     |                |      |
 *     |     +--SceneFrame----+      |
 *     |                             |
 *     +----------Scene--------------+
 * </pre>
 *
 *
 */
public class ScaledSceneFrame extends SceneFrame {

    private double scalingFactor;

    public ScaledSceneFrame(
            final double x,
            final double y,
            final double width,
            final double height,
            final double scalingFactor
    ) {
        super(x, y, width, height);

        this.scalingFactor = scalingFactor;
    }


    public double getScalingFactor() {
        return scalingFactor;
    }



}
