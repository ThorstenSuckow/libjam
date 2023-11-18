package dev.libjam.demo.snowFall;

import dev.libjam.demo.snowFall.game.SnowFlake;
import dev.libjam.demo.snowFall.game.SnowFlakeRenderer;
import dev.libjam.demo.snowFall.physx.SnowFlakeModel;
import dev.libjam.game.SpriteManager;
import dev.libjam.gfx.drawable.GfxParent;
import dev.libjam.physx.Object2D;

public class SnowCloud extends Object2D {

    private SpriteManager spriteManager;

    private SnowFlakeRenderer snowFlakeRenderer;

    private GfxParent gfxParent;

    /**
     * Creates a new Object2D with a null-vector for velocity and it's location-coordinates set to
     * x=-1, y=-1.
     *
     * @param width     The width of this Object2D.
     * @param height    The height of this Object2D.
     * @param gfxParent
     */
    public SnowCloud(double width, double height, SpriteManager spriteManager, SnowFlakeRenderer snowFlakeRenderer, GfxParent gfxParent) {
        super(width, height);
        this.spriteManager = spriteManager;
        this.snowFlakeRenderer = snowFlakeRenderer;

        this.gfxParent = gfxParent;
    }


    public void updateObject(long time) {

        int size = (int)(Math.random() * 18);

        if ((int)(Math.random() * 100) % 2 == 0) {
            SnowFlakeModel snowFlakeModel = new SnowFlakeModel(size, size);


            SnowFlake snowFlake = new SnowFlake(snowFlakeModel, snowFlakeRenderer);
            gfxParent.add(snowFlake);
            spriteManager.registerSprite(snowFlake);

            getWorld().addObject(snowFlakeModel);

            snowFlakeModel.setVelocity(0, 1);
            System.out.println("width: " + getWidth());

            snowFlakeModel.setXY(Math.random() * getWidth() , 0);


        }

    }


}
