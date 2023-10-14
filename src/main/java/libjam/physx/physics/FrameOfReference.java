package libjam.physx.physics;


import libjam.util.Unit;

/**
 * A frame of reference serves as an underlying model for an instance of a
 * libjam.physx.World and can be seen as the layer that translates real-world
 * dimensions to computer graphic dimensions.
 * It provides an installation of a PhysicEngine and provides information about
 * the dimensions the world uses, such as the height and the width and the scale
 * that serves as the reference when translating real-world values for rendering
 * purposes.
 */
public class FrameOfReference {

    /**
     * number of meters per pixel.
     */
    private double worldScale;

    /**
     * Width of the frame in meters.
     */
    private double width;

    /**
     * Height of the frame in meters..
     */
    private double height;

    /**
     *
     */
    PhysicEngine engine;

    /**
     *
     * @param engine The PhysicEngine that should be used with this FrameOfReference.
     * @param width The overall with of this game world in meters
     * @param height The overall height of this game world in meters
     * @param worldScale  Represents the number of meters that should be treated as one pixel,
     * e.g. a value of 100 would mean 1 pixels is used to depict 100 meters,
     * whereas a value of 0.01 means that 1 pixel represents 1 cm and so on.
     * Rendering engines should query the value to properly translate physical
     * effects to the game world and vice versa. Rendering engines should treat the
     * value in accordance.
     */
    public FrameOfReference(
            PhysicEngine engine,
            final int width,
            final int height,
            double worldScale
    ) {
        this.engine = engine;
        this.worldScale = worldScale;
        this.width = width;
        this.height = height;
    }


    /**
     * Returns the PhysicEngine used by this frame.
     *
     * @return
     */
    public PhysicEngine getEngine() {
        return engine;
    }

    /**
     * Returns the height of the frame in meters.
     * @return
     */
    public double getHeight() {
        return height;
    }

    /**
     * Returns the width of the frame in meters.
     * @return
     */
    public double getWidth() {
        return width;
    }


    /**
     * Returns the world scale.
     * @return
     */
    public double getWorldScale() {
        return worldScale;
    }

    /**
     * Returns the height translated to the specified unit.
     * @param unit
     * @return
     */
    public double getHeight(final Unit unit) {

        switch (unit) {

            case METER:
                return height;

            case PIXEL:
                return height / worldScale;

            default:
                throw new UnsupportedOperationException("unit not supported");
        }

    }

    /**
     * Returns the current with translated to the given unit.
     * @param unit
     * @return
     */
    public double getWidth(final Unit unit) {

        switch (unit) {

            case METER:
                return width;

            case PIXEL:
                return width / worldScale;

            default:
                throw new UnsupportedOperationException("unit not supported");
        }
    }

    /**
     * Returns the value translated to the unit given the worldScale.
     *
     * @param value
     * @param from
     * @param to
     *
     * @return
     */
    public double scaleToUnitValue(
            final double value,
            final Unit from,
            final Unit to
    ) {
        return scaleToUnitValue(value, from, to, worldScale);
    }

    /**
     * Returns the value translated to the unit given the specified scale.
     *
     * @param value
     * @param from
     * @param to
     * @param scale
     * @return
     */
    public double scaleToUnitValue(
            double value,
            final Unit from,
            final Unit to,
            final double scale
    ) {

        switch (from) {

            case METER:
                switch (to) {
                    case PIXEL:
                        return value / scale;
                    default:
                        throw new UnsupportedOperationException("unit not supported");
                }

            case PIXEL:
                switch (to) {
                    case METER:
                        return value * scale;
                    default:
                        throw new UnsupportedOperationException("unit not supported");
                }

            default:
                throw new UnsupportedOperationException("unit not supported");
        }

    }


}
