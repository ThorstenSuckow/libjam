package libjam.gfx.renderer;

import libjam.gfx.renderer.world.ReferenceRenderingContext;
import libjam.physx.WorldObject;

import java.awt.Graphics;

/**
 * Contract for renderers that need to draw representatives of WorldObject(-related) data.
 * ObjectRenderer generally adhere to the bounds provided by the ReferenceRenderingContext.
 *
 */
public interface ObjectRenderer {

    /**
     * May add visual representations of the specified WorldObject, or skip rendering
     * entirely. When the method is called, it is clear that implementing renderers
     * request a visual representation of WorldObject or its related data.
     *
     * @param g The Graphic-Context to use.
     * @param worldObject The WorldObject to render.
     * @param renderingContext The ReferenceRenderingContext as provided by higher-tier renderers, containing
     *                         scaling and SceneFrame information
     *
     * @return boolean true if a rendering-attempt was made, otherwise
     * false. Note that if false is returned, it is still possible that a previous result
     * of the rendering process exists in the Graphics-context.
     */
    boolean draw(
            Graphics g,
            WorldObject worldObject,
            ReferenceRenderingContext renderingContext
    );

}
