package dev.libjam.demo.snowFall.gfx;

import dev.libjam.game.SpriteRenderer;
import dev.libjam.gfx.drawable.DefaultGfxNode;
import dev.libjam.gfx.drawable.GfxNode;
import dev.libjam.physx.Object2D;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class SnowFlakeRenderer implements SpriteRenderer {


    private double computeOpacity(GfxNode node) {

        Object2D obj = ((DefaultGfxNode)node).getObject2D();

        if (obj == null) {
            return 0;
        }

        long age = (obj.getAge() / 1_000_000_000);
        //age = age > 0 ? age : 1;

        double opacity = age == 0 ? 1 : 1.0 - (age * 0.1d);

        // aging should be probably in a separate thread?
        //((SnowFlake)node).setBgColor(Color.rgb(200, 200, 200, Math.max(0, opacity)));// blue as a hex web value, explicit alpha)

        return Math.max(0, opacity);
    }


   // @Override
    public synchronized WritableImage render(GfxNode node) {

        if (node.getWidth() == 0.0 || node.getHeight() == 0.0) {
            return null;
        }

        double opacity = computeOpacity(node);

        DefaultGfxNode dNode = (DefaultGfxNode)node;

         if (dNode.img == null) {
             dNode.img = new WritableImage((int)Math.ceil(node.getWidth()), (int)Math.ceil(node.getHeight()));
        }

        double width = dNode.img.getWidth();
        double height = dNode.img.getHeight();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (dNode.img != null) {
                    dNode.img.getPixelWriter().setColor(i, j, Color.rgb(200, 200, 200, Math.max(0, opacity)));
                }
            }
        }

        return dNode.img;
    }



}
