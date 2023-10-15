package libjam.gfx;

public class CanvasContext {

    private int width;

    private int height;
    public CanvasContext(int width, int height) {

        this.width = width;
        this.height = height;

    }


    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
