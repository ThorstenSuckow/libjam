package libjam.demo.bounce;


import libjam.gfx.FrameOfReferenceRenderer;
import libjam.gfx.Renderer;
import libjam.gfx.offsetRenderer.XAxisOffsetRenderer;
import libjam.gfx.offsetRenderer.YAxisOffsetRenderer;
import libjam.physx.physics.FrameOfReference;
import libjam.util.Logger;
import libjam.util.Unit;

import java.awt.geom.Rectangle2D;

final public class BounceCanvas extends Renderer  {


    public BounceCanvas(int width, int height) {
        super(width, height);
        Logger.log( width +" "+ height);
        FrameOfReference fref = new FrameOfReference(
                0.1, new Rectangle2D.Double(0, 0, width, height), Unit.PIXEL
        );

        FrameOfReferenceRenderer frameOfReferenceRenderer = new FrameOfReferenceRenderer(fref);

        frameOfReferenceRenderer.addOffsetRenderer(
            new YAxisOffsetRenderer(20)
        );
        frameOfReferenceRenderer.addOffsetRenderer(
            new XAxisOffsetRenderer(20)
        );


        addDrawable(
           frameOfReferenceRenderer
        );
    }

    public void render() {
        (new Thread(this)).start();
    }



}