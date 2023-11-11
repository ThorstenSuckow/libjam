package dev.libjam.demo.snowFall;

import dev.libjam.gfx.drawable.GfxRoot;

public class AnimationTimer extends javafx.animation.AnimationTimer {

    GfxRoot gfxRoot;

    public AnimationTimer(GfxRoot gfxRoot) {
        this.gfxRoot = gfxRoot;
    }

    @Override
    public void handle(long l) {

        gfxRoot.prepareImg();
        gfxRoot.draw();
    }
}
