package libjam.gfx;

/**
 * SceneFrame providing a scaled view of a scene.
 */
final public class ScaledSceneFrame extends SceneFrame {

    @SuppressWarnings("checkstyle:JavadocVariable")
    private final double scalingFactor;


    /**
     * Creates a new ScaledSceneFrame with the specified scalingFactor.
     *
     * @param x The x-coordinate of the scene.
     * @param y The y-coordinate of the scene.
     * @param width The width of the scene.
     * @param height The height of the scene.
     * @param scalingFactor The scaling factor to use for this SceneFrame.
     */
    @SuppressWarnings("checkstyle:HiddenField")
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


    /**
     * @return the scalingFactor used for this SceneFrame.
     */
    public double getScalingFactor() {
        return scalingFactor;
    }


    /**
     * Checks the specified ScaledSceneFrame for equality with this ScaledSceneFrame. Two ScaledSceneFrame are
     * considered equal if their x, y, width, height and scalingFactor attributes are the same.
     *
     * @param scaledSceneFrame The specified ScaledSceneFrame.
     * @return true if the specified ScaledSceneFrame is considered equal to this ScaledSceneFrame, otherwise false.
     */
    public boolean equals(final ScaledSceneFrame scaledSceneFrame) {

        return super.equals(scaledSceneFrame)
            && scalingFactor == scaledSceneFrame.getScalingFactor();

    }

}
