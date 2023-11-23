package dev.libjam.game;

import dev.libjam.physx.Object2D;


/**
 * Mediates information between physical objects and their representation as Sprites.
 */
public interface SpriteManager  {


    /**
     * Translates the position of the specified Object2D to coordinates used by the specified Sprite.
     *
     * @param object2D
     * @param sprite#
     *
     * @throws IllegalArgumentException if the specified object2D is not associated with the
     * specified sprite.
     */
    void translate(Object2D object2D, Sprite sprite) throws IllegalArgumentException;


    /**
     * Creates a Sprite for the specified object2D and returns the sprite.
     *
     * @param object2D The specified object2D for which the Sprite should get created.
     *
     * @return Sprite the created Sprite.
     */
    Sprite createSprite(Object2D object2D);

}