package dev.libjam.demo.snowFall;

import dev.libjam.demo.snowFall.gfx.SnowFlakeRenderer;
import dev.libjam.gfx.drawable.DefaultGfxParent;
import dev.libjam.gfx.drawable.GfxNode;
import dev.libjam.gfx.drawable.GfxParent;
import dev.libjam.gfx.drawable.GfxRoot;
import dev.libjam.physx.Object2D;

import java.util.HashMap;
import java.util.Map;

class SnowFallController  {
    Map<Object2D, GfxNode> nodesByObjects = new HashMap<>();


    SnowWorld world;

    GfxRoot gfxRoot;

    GfxParent gfxParent;

    SnowFlakeRenderer snowFlakeRenderer;

    public SnowFallController(
        final SnowWorld world,
        final GfxRoot gfxRoot
    ) {
        this.world = world;
    //    world.addObjectLifecycleListener(this);
        this.gfxRoot = gfxRoot;
        snowFlakeRenderer = new SnowFlakeRenderer();
    }

    public void init() {
        createObjects();
        createAnimation();
        createPhysics();
    }

    private void createObjects() {
        gfxParent = new DefaultGfxParent(0, 0, gfxRoot.getWidth(), gfxRoot.getHeight());
        gfxRoot.add(gfxParent);

        //world.addObject(new SnowCloud(0, 0, world.getWidth(), 0));
    }

    private void createAnimation() {
        AnimationTimer animation = new AnimationTimer(gfxRoot);
        animation.start();
    }

    private void createPhysics() {



        Thread physicUpdate = new Thread(() -> {

            while (true) {

                long now = System.nanoTime();

                world.updateWorld(now);

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        physicUpdate.start();
    }

    public void objectLifecycleChange() {
      /*  if (evt.getType() == LifecycleType.SPAWNED) {
            Object2D obj = evt.getSource();

            SnowFlake node = new SnowFlake(obj, snowFlakeRenderer);
            //node.setWidth(obj.getWidth()).setHeight(obj.getHeight()).setX(obj.getX());
            gfxParent.add(node);
            // linkObjects(obj, node);
        }

        if (evt.getType() == LifecycleType.EXPIRED) {
            Object2D obj = evt.getSource();

            for (int i = 0; i < gfxParent.getChildren().size(); i++) {
                if (((DefaultGfxNode)gfxParent.getChildren().get(i)).obj == obj) {

                    gfxParent.remove(gfxParent.getChildren().get(i));
                }
            }


            // linkObjects(obj, node);
        }*/
    }
}
