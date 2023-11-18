package dev.libjam.demo.snowFall.game;

import dev.libjam.game.Sprite;
import dev.libjam.physx.Object2D;

public class SnowFlake extends Sprite {


    public SnowFlake(
        final Object2D obj,
        final SnowFlakeRenderer renderer) {
        super(obj, renderer);
    }

    @Override
    public long getMaxAge() {
        return 12_000_000_000L;
    }


}
