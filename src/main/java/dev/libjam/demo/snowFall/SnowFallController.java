package dev.libjam.demo.snowFall;

import dev.libjam.demo.snowFall.game.SnowFlakePool;
import dev.libjam.demo.snowFall.game.SnowFlakeRenderer;
import dev.libjam.game.SpriteLayer;
import dev.libjam.game.SpriteManager;
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
        this.gfxRoot = gfxRoot;
    }

    public void init() {
        createObjects();
        createAnimation();
        createPhysics();
    }

    private void createObjects() {

        int size = 100_000;

        SnowFlakePool snowFlakePool = new SnowFlakePool(size);
        snowFlakePool.init();

        snowFlakeRenderer = new SnowFlakeRenderer(snowFlakePool);


        SpriteManager mng = new SpriteManager();

        gfxParent = new SpriteLayer(0, 0, gfxRoot.getWidth(), gfxRoot.getHeight());
        gfxRoot.add(gfxParent);

        world.addObject(new SnowCloud(world.getWidth(), 0, mng, snowFlakeRenderer, gfxParent));
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


}
