package dev.libjam.game;

import javafx.scene.image.WritableImage;


/**
 * Functional interface for producing images for Sprites.
 */
public interface SpriteRenderer {

    /**
     * Returns a visual representation of the specified Sprite as an image.
     *
     * @param sprite the specified Sprite for which an image gets produced.
     *
     * @return the produced WritableImage representing the specified Sprite.
     */
    WritableImage render(Sprite sprite);

}
