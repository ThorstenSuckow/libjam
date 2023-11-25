package dev.libjam.game2D;

import dev.libjam.physx.Object2D;

public final class GameObject extends Object2D {


    @SuppressWarnings("checkstyle:JavadocVariable")
    private Sprite sprite;

    /**
     * Creates a new Object2D with a Velocity-vector with both components set to 0 and the Object2D's
     * location-coordinates set to 0.
     *
     * @param width  The width of this Object2D.
     * @param height The height of this Object2D.
     */
    public GameObject(double width, double height) {
        super(width, height);
    }


    /**
     *
     * @param sprite
     *
     * @throws IllegalArgumentException
     */
    public void setSprite(Sprite sprite) {
        if (this.sprite != null) {
            throw new IllegalArgumentException();
        }
        this.sprite = sprite;
    }


    /**
     *
     * @return
     */
    public Sprite getSprite() {
        return sprite;
    }


}
