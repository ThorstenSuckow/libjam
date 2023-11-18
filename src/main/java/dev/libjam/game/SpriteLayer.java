package dev.libjam.game;

import dev.libjam.gfx.drawable.DefaultGfxParent;

public class SpriteLayer extends DefaultGfxParent {


    public SpriteLayer(final int x, final int y, final double width, final double height) {
        super(x, y, width, height);
    }

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
