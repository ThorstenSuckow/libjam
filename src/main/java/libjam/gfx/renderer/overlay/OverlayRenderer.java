package libjam.gfx.renderer.overlay;

import libjam.gfx.ReferenceRenderingContext;
import libjam.physx.WorldObject;

import java.awt.Graphics;

public interface OverlayRenderer {

    public WorldObject draw(Graphics g, WorldObject obj, ReferenceRenderingContext referenceRenderingContext);

}
