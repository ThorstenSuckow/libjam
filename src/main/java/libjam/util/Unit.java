package libjam.util;


/**
 * Enumeration of units used throughout libjam.
 */
public enum Unit {

    /**
     * pixel
     */
    METER(),


    /**
     * pixel
     */
    PIXEL(),

    /**
     * centimeter
     */
    CM(),

    /**
     * millimeter
     */
    MM(),

    /**
     * milliseconds
     */
    MS(),

    /**
     * seconds
     */
    SECONDS();


    /**
     * Returns the value translated to the unit given the specified scale.
     *
     * @param value
     * @param from
     * @param to
     * @param scale
     * @return
     */
    public static double transformScaleValueToUnit(
            double value,
            final Unit from,
            final Unit to,
            final double scale
    ) {

        if (to == from) {
            return value;
        }

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

    public static int toPixel(final double value, final double scale) {
        return (int) transformScaleValueToUnit(value, Unit.METER, Unit.PIXEL, scale);
    }

    public static double toScreenY(int y, double referenceHeight) {
        return referenceHeight - y;
    }
}