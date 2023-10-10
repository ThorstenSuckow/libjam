package math;

public class Vector  {

    public static final Vector NULL_VECTOR = new Vector(new double[]{0, 0});

    private final int size;
    private double[] components;

    public static Vector from(final double... d) {
        return new Vector(d);
    }


    public Vector(final double[] data) {
        size = data.length;

        components = new double[size];

        System.arraycopy(data, 0, components, 0, size);
    }

    public int getSize()  {
        return size;
    }
    public double getAt(int i) {

        if (i >= size) {
            throw new IndexOutOfBoundsException();
        }

        return components[i];
    }

    public Vector setAt(int i, double value) {

        if (i >= size) {
            throw new IndexOutOfBoundsException();
        }

        components[i] = value;

        return this;
    }

    @Override
    public boolean equals(final Object o) {

        if (!(o instanceof Vector)) {
            return false;
        }

        Vector v = (Vector) o;

        for (int i = 0; i < size; i++) {
            if (getAt(i) != v.getAt(i)) {
                return false;
            }
        }

        return true;
    }
}
