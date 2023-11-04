package dev.libjam.gfx.canvas;

import dev.libjam.gfx.drawable.GfxNode;
import dev.libjam.gfx.drawable.GfxRoot;
import dev.libjam.gfx.drawable.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class JavaFxCanvas extends Canvas implements GfxRoot  {

    @SuppressWarnings("checkstyle:JavadocVariable")
    protected List<GfxNode> children = new ArrayList<GfxNode>();

    @Override
    public List<GfxNode> getChildren() {
        return new ArrayList<GfxNode>(children);
    }

    @Override
    public Parent add(final GfxNode node) throws IllegalArgumentException {
        if (node.getParent() != null) {
            throw new IllegalArgumentException();
        }

        node.setParent(this);
        children.add(node);
        return this;
    }

    @Override
    public void draw()  {
        GraphicsContext g = getGraphicsContext2D();
        g.setFill(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        for (GfxNode child: children) {
            child.draw(g);
        }
    }
}
