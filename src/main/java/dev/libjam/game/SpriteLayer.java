package dev.libjam.game;

import dev.libjam.gfx.drawable.DefaultGfxParent;

public class SpriteLayer extends DefaultGfxParent {


    @Override
    public String toString() {
        return "SpriteLayer["
            + "x:" + x
            + "; y:" + y
            + "; width:" + width
            + "; height: " + height
            + "]";
    }
}
