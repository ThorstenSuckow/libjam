package libjam.demo.bounce;


import libjam.gfx.FrameOfReferenceRenderer;
import libjam.gfx.GfxCanvas;
import libjam.gfx.offsetRenderer.CoordinateSystemRenderer;
import libjam.gfx.renderer.DefaultWorldRendererFactory;
import libjam.physx.World;
import libjam.physx.event.WorldObjectEnterEvent;
import libjam.physx.object.Ball;
import libjam.physx.physics.FrameOfReference;
import libjam.util.Logger;
import libjam.util.Unit;

import java.awt.geom.Rectangle2D;

final public class BounceCanvas extends GfxCanvas {


    public BounceCanvas(int width, int height) {

        super(width, height);

        DefaultWorldRendererFactory rendererFactory = new DefaultWorldRendererFactory();

        Logger.log( "GfxCanvas: " + width + "px x " + height + "px");
        FrameOfReference fref = new FrameOfReference(
                0.01, new Rectangle2D.Double(0, 0, 3, 3), Unit.METER
        );

        World world = new World(5, 5);
        fref.observe(world);

        FrameOfReferenceRenderer frameOfReferenceRenderer = new FrameOfReferenceRenderer(fref);
        frameOfReferenceRenderer.addOffsetRenderer(new CoordinateSystemRenderer(87, 41));
        frameOfReferenceRenderer.setWorldRendererFactory(rendererFactory);


        world.addWorldChangeListener((WorldObjectEnterEvent obj) -> {
            Logger.log("enter: " + obj.getSource());
        });

        addDrawable(frameOfReferenceRenderer);


        world.add(
            new Ball(0.2),
            1.5,
            1.5
        );

    }

    public void render() {
        (new Thread(this)).start();
    }



}