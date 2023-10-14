package libjam.math;

import java.util.Arrays;

/**
 * Datatructure for vectors of arbitrary size.
 */
public final class Vector {

    /**
     * The size of the vector, i.e. the number of its components.
     */
    private final int size;

    /**
     * The vector's components.
     */
    private double[] components;

    /**
     * Creates a new vector given the arguments.
     *
     * @param d
     *
     * @return
     */
    public static Vector from(final double... d) {
        return new Vector(d);
    }


    private Vector(final double[] data) {
        size = data.length;

        components = new double[size];

        System.arraycopy(data, 0, components, 0, size);
    }

    /**
     * Returns the size of this vector.
     * @return
     */
    public int getSize()  {
        return size;
    }

    /**
     * Returns the value of this vector at the given index, starting at 0 for the topmost component.
     *
     * @param i
     * @return
     */
    public double getAt(int i) {

        if (i >= size) {
            throw new IndexOutOfBoundsException();
        }

        return components[i];
    }

    /**
     * Sets the value of the vector at the given index. An index of zero represents the
     * topmost component. Returns a new component with the updated values, the components
     * of THIS vector will not be changed.
     *
     * @param i
     * @param value
     *
     * @return
     *
     * @throws IndexOutOfBoundsException
     */
    public Vector setAt(final int i, final double value) throws IndexOutOfBoundsException {

        if (i >= size) {
            throw new IndexOutOfBoundsException();
        }

        double[] arr = toArray();

        arr[i] = value;

        return Vector.from(arr);
    }


    /**
     * Returns ab array representative of this Vector.
     *
     * @return
     */
    public double[] toArray() {
        return Arrays.copyOf(components, size);
    }


    /**
     * Returns a String representative of this Vector.
     * @return
     */
    public String toString() {
        return "V:" + Arrays.toString(components);
    }


    @Override
    public boolean equals(final Object o) {

        if (!(o instanceof Vector)) {
            return false;
        }

        Vector v = (Vector) o;

        if (this == o) {
            return true;
        }

        for (int i = 0; i < size; i++) {
            if (getAt(i) != v.getAt(i)) {
                return false;
            }
        }

        return true;
    }

    /**
     * @return
     *
     *
     * @see http://www.angelikalanger.com/Articles/EffectiveJava/03.HashCode/03.HashCode.html
     */
    public int hashCode() {
        int hc = 17;
        int hashMultiplier = 59;

        hc = hc * hashMultiplier + components.length;
        for (int i=0; i < components.length; i++) {
            hc = hc * hashMultiplier + components.hashCode();
        }

        return hc;
    }


    /**
     * Adds vector y to this vector and returns a new vector with the resulting
     * components. The target vector must be the same size of this vector,
     * otherwise an IllegaArgumentException is thrown.
     * @param y
     * @return
     */
    public Vector add(final Vector y) throws IllegalArgumentException {

        if (y.getSize() != getSize()) {
            throw new IllegalArgumentException("size of vector not matching this vector");
        }

        double[] v = new double[size];

        for (int i = 0; i < size; i++) {
            v[i] = getAt(i) + y.getAt(i);
        }

        return new Vector(v);
    }
}
