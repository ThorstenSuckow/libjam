package libjam.demo.bounce;


import libjam.gfx.Camera;
import libjam.gfx.GfxCanvas;
import libjam.gfx.renderer.DefaultWorldRendererFactory;
import libjam.gfx.renderer.FrameOfReferenceRenderer;
import libjam.gfx.renderer.offset.CoordinateSystemRenderer;
import libjam.math.Rectangle;
import libjam.physx.World;
import libjam.physx.event.WorldObjectEnterEvent;
import libjam.physx.object.Ball;
import libjam.physx.physics.FrameOfReference;
import libjam.util.Logger;

final public class BounceCanvas extends GfxCanvas {

    private Camera camera;

    public Camera getCamera() {
        return camera;
    }

    public BounceCanvas(int width, int height) {

        super(width, height);

        DefaultWorldRendererFactory rendererFactory = new DefaultWorldRendererFactory();

        Logger.log( "GfxCanvas: " + width + "px x " + height + "px");
        FrameOfReference fref = new FrameOfReference(
                new Rectangle(1, 1, 3, 3)
        );

        World world = new World(5, 6);
        fref.observe(world);

        camera = new Camera(fref);

        FrameOfReferenceRenderer frameOfReferenceRenderer = new FrameOfReferenceRenderer(camera, 0.01);
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