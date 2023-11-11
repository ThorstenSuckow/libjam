package dev.libjam.demo.snowFall.gfx;

import dev.libjam.gfx.drawable.DefaultGfxNode;
import dev.libjam.gfx.drawable.GfxNode;
import dev.libjam.physx.Object2D;
import javafx.scene.canvas.GraphicsContext;

/**
 * Should be a flyweight.
 */
public class SnowFlake extends DefaultGfxNode {


    public SnowFlake(double x, double y, double width, double height) {
        super(x, y, width, height);
       // setBgColor(Color.GRAY);
    }

    public SnowFlake(Object2D obj, SnowFlakeRenderer renderer) {
        super(obj, renderer);
        //   setBorderColor(Color.LIGHTPINK);
    }

    public SnowFlake(SnowFlakeRenderer renderer) {
        super(renderer);
     //   setBorderColor(Color.LIGHTPINK);
    }

    @Override
    protected GfxNode drawNode(final GraphicsContext g) {

        super.drawNode(g);

        return this;
    }


}
