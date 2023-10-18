package libjam.gfx.renderer;

import libjam.gfx.renderer.world.WorldObjectRenderer;
import libjam.physx.WorldObject;

/**
 * Interface for renderer-factories for WorldObjects.
 */
public interface WorldRendererFactory {


    /**
     * Returns the appropriate renderer applicable for the specified
     * WorldObject.
     *
     * @param worldObject The worldObject that requires a renderer.
     *
     * @return A renderer for the specified WorldObject.
     */
    WorldObjectRenderer getRenderer(WorldObject worldObject);

}
