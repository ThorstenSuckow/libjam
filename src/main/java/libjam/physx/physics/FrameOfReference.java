package libjam.physx.physics;


import libjam.physx.World;
import libjam.physx.WorldObject;
import libjam.util.Unit;

import java.awt.geom.Rectangle2D;

/**
 * A FrameOfReference serves as an observator for a ibjam.physx.World and can be
 * undertsood as the layer that translates real-world dimensions to computer graphic dimensions.
 * It provides and translates information about the dimensions the world uses, such as the height
 * and the width and the scale that serves as the reference when translating such  values for rendering
 * purposes.
 * In this regard, an instance is close to the frame-of-reference-definition
 * in physics, albeit less complex.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Frame_of_reference">https://en.wikipedia.org/wiki/Frame_of_reference</a>
 */
public class FrameOfReference {

    /**
     * scaling factor, i.e. number of meters per pixel.
     */
    private double scale;

    /**
     * The dimensions of the observed frame, in meters
     */
    private Rectangle2D.Double observedFrame;


    private World world;


    /**
     *
     * @param scale  Represents the number of meters that should be treated as one pixel,
     * e.g. a value of 100 would mean 1 pixels is used to depict 100 meters,
     * whereas a value of 0.01 means that 1 pixel represents 1 cm and so on.
     * Rendering engines should query the value to properly translate physical
     * effects to the game world and vice versa. Rendering engines should treat the
     * value in accordance.
     * @param observedFrame The initial dimensions of the frame to observe
     * @param unit The unit used for the width and height of the frame.
     *
     */
    public FrameOfReference(
        final double scale,
        final Rectangle2D.Double observedFrame,
        Unit unit
    ) {
        this.observedFrame = observedFrame;
        this.scale = scale;

        updateObservedFrame(observedFrame, unit, Unit.METER);
    }

    public void updateObservedFrame( final Rectangle2D.Double observedFrame, Unit fromUnit, Unit toUnit) {
        updateObservedFrame(
                observedFrame.getX(),
                observedFrame.getY(),
                observedFrame.getWidth(),
                observedFrame.getHeight(),
                fromUnit, toUnit
        );
    }


    public void updateObservedFrame(double x, double y, double width, double height, Unit fromUnit, Unit toUnit) {
        observedFrame.setRect(
            transformScaleValueToUnit(x, fromUnit, toUnit),
            transformScaleValueToUnit(y, fromUnit, toUnit),
            transformScaleValueToUnit(width, fromUnit, toUnit),
            transformScaleValueToUnit(height, fromUnit, toUnit)
        );
    }


    public FrameOfReference observe(final World world) {
        this.world = world;

        return this;
    }

    /**
     * Returns the observed world.
     * @return
     */
    public World getObservedWorld() {
        return world;
    }


    /**
     * Returns the x-coordinate of the observed frame in meters.
     * @return
     */
    public double getObservedX() {
        return observedFrame.getX();
    }

    /**
     * Returns the y-coordinate of the observed frame in meters.
     * @return
     */
    public double getObservedY() {
        return observedFrame.getY();
    }


    /**
     * Returns the x-coordinate of the observed frame in meters.
     *
     * @param unit
     *
     * @return
     */
    public double getObservedX(Unit unit) {

        switch (unit) {

            case METER:
                return observedFrame.getX();

            case PIXEL:
                return observedFrame.getX() / scale;

            default:
                throw new UnsupportedOperationException("unit not supported");
        }

    }

    /**
     * Returns the y-coordinate of the observed frame in meters.
     *
     * @param unit
     *
     * @return
     */
    public double getObservedY(Unit unit) {
        switch (unit) {

            case METER:
                return observedFrame.getY();

            case PIXEL:
                return observedFrame.getY() / scale;

            default:
                throw new UnsupportedOperationException("unit not supported");
        }
    }


    /**
     * Returns the height of the observed frame in meters.
     * @return
     */
    public double getObservedHeight() {
        return observedFrame.getHeight();
    }

    /**
     * Returns the width of the frame in meters.
     * @return
     */
    public double getObservedWidth() {
        return observedFrame.getWidth();
    }


    public Rectangle2D.Double getObservedFrame() {
        return observedFrame;
    }

    /**
     * Returns the world scale.
     * @return
     */
    public double getScale() {
        return scale;
    }

    /**
     * Returns the height translated to the specified unit.
     * @param unit
     * @return
     */
    public double getObservedHeight(final Unit unit) {

        switch (unit) {

            case METER:
                return observedFrame.getHeight();

            case PIXEL:
                return observedFrame.getHeight() / scale;

            default:
                throw new UnsupportedOperationException("unit not supported");
        }

    }

    /**
     * Returns the current with translated to the given unit.
     * @param unit
     * @return
     */
    public double getObservedWidth(final Unit unit) {

        switch (unit) {

            case METER:
                return observedFrame.getWidth();

            case PIXEL:
                return observedFrame.getWidth() / scale;

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
    public double transformScaleValueToUnit(
            final double value,
            final Unit from,
            final Unit to
    ) {
        if (from == to) {
            return value;
        }

        return Unit.transformScaleValueToUnit(value, from, to, scale);
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
    public double transformScaleValueToUnit(
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


    public void setObservedHeight(double newHeight, Unit unit) {

        updateObservedFrame(
            getObservedX(Unit.METER),
            getObservedY(Unit.METER),
            getObservedWidth(Unit.METER),
            Unit.transformScaleValueToUnit(
                newHeight,
                unit,
                Unit.METER,
                getScale()
            ),
            Unit.METER, Unit.METER
        );
    }

    public void setObservedWidth(double newWidth, Unit unit) {
        updateObservedFrame(
                getObservedX(Unit.METER),
                getObservedY(Unit.METER),
                Unit.transformScaleValueToUnit(
                        newWidth,
                        unit,
                        Unit.METER,
                        getScale()
                ),
                getObservedHeight(Unit.METER),

                Unit.METER, Unit.METER
        );
    }

    public void setObservedX(double newX, Unit unit) {

        updateObservedFrame(
                Unit.transformScaleValueToUnit(
                        newX,
                        unit,
                        Unit.METER,
                        getScale()
                ),
                getObservedY(Unit.METER),
                getObservedWidth(Unit.METER),
                getObservedHeight(Unit.METER),

                Unit.METER, Unit.METER
        );
    }

    public void setObservedY(double newY, Unit unit) {

        updateObservedFrame(
                getObservedX(Unit.METER),
                Unit.transformScaleValueToUnit(
                        newY,
                        unit,
                        Unit.METER,
                        getScale()
                ),
                getObservedWidth(Unit.METER),
                getObservedHeight(Unit.METER),

                Unit.METER, Unit.METER
        );
    }


    /**
     * Returns true if this FrameOFReference is able to observe the specified
     * WorldObject. This is the case if any point of the object is within the
     * bounds of THIS frame.
     *
     * @param obj
     * @return
     */
    public boolean canObserve(final WorldObject obj) {

        double objX = Unit.transformScaleValueToUnit(obj.getX(), Unit.METER, Unit.PIXEL, scale);
        double objY = Unit.transformScaleValueToUnit(obj.getY(), Unit.METER, Unit.PIXEL, scale);
        double objH = Unit.transformScaleValueToUnit(obj.getHeight(), Unit.METER, Unit.PIXEL, scale);
        double objW = Unit.transformScaleValueToUnit(obj.getWidth(), Unit.METER, Unit.PIXEL, scale);

        Rectangle2D.Double objRect = new Rectangle2D.Double(
                objX, objY, objW, objH
        );

        return true;
        //return observedFrame.contains(objRect);
        //return observedFrame.contains(objRect);//objRect.intersects(observedFrame);
    }

}
