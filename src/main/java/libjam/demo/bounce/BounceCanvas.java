package libjam.demo.bounce;


import libjam.gfx.FrameOfReferenceRenderer;
import libjam.gfx.Renderer;
import libjam.gfx.offsetRenderer.CoordinateSystemRenderer;
import libjam.physx.World;
import libjam.physx.event.WorldObjectEnterEvent;
import libjam.physx.physics.FrameOfReference;
import libjam.util.Logger;
import libjam.util.Unit;

import java.awt.geom.Rectangle2D;

final public class BounceCanvas extends Renderer  {


    public BounceCanvas(int width, int height) {
        super(width, height);
        Logger.log( width +" "+ height);
        FrameOfReference fref = new FrameOfReference(
                0.01, new Rectangle2D.Double(0, 0, 2, 3), Unit.METER
        );

        World world = new World(1, 2);
        world.addWorldChangeListener((WorldObjectEnterEvent obj) -> {
            Logger.log(obj.toString());
        });
        fref.observe(world);

        FrameOfReferenceRenderer frameOfReferenceRenderer = new FrameOfReferenceRenderer(fref);

        frameOfReferenceRenderer.addOffsetRenderer(new CoordinateSystemRenderer(87, 41));

        addDrawable(frameOfReferenceRenderer);
    }

    public void render() {
        (new Thread(this)).start();
    }



}