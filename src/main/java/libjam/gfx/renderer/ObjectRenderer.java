package libjam.gfx.renderer;

import libjam.gfx.ReferenceRenderingContext;
import libjam.physx.WorldObject;

import java.awt.Graphics;

public interface ObjectRenderer {


    public WorldObject draw(final Graphics g, WorldObject worldObj, ReferenceRenderingContext referenceRenderingContext);

}
