package libjam.gfx.renderer;

import libjam.physx.WorldObject;

public interface WorldRendererFactory {

    ObjectRenderer getRenderer(WorldObject worldObj);

}
