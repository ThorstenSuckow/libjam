package libjam.gfx.offsetRenderer;

public abstract  class AxisRenderer extends OffsetRenderer {


    int start;

    int end;

    /**
     * @param offset   offset-size in pixels
     * @param position height of bottom offsets
     */
    public AxisRenderer(int offset, int position) {
        super(offset, position);
    }

    public void setRange(int start, int end) {
        this.start = start;
        this.end = end;
    }


}
