package dev.libjam.demo.snowFall.game;

import dev.libjam.game.Sprite;
import dev.libjam.game.SpriteRenderer;
import dev.libjam.physx.Object2D;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class SnowFlakeRenderer implements SpriteRenderer {

    private SnowFlakePool snowFlakePool;

    public SnowFlakeRenderer(SnowFlakePool snowFlakePool) {
        this.snowFlakePool = snowFlakePool;
    }

    private WritableImage computeOpacity(final Sprite sprite, final WritableImage img) {

        Object2D obj = sprite.getObject2D();

        long age = (obj.getAge() / 1_000_000_000);
        //age = age > 0 ? age : 1;

        double opacity = age == 0 ? 1 : 1.0 - (age * 0.1d);

        PixelWriter pw = img.getPixelWriter();

        double width = img.getWidth();
        double height = img.getHeight();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (img != null) {
                   pw.setColor(i, j, Color.rgb(200, 200, 200, Math.max(0.2, opacity)));
                }
            }
        }

        // aging should be probably in a separate thread?
        //((SnowFlake)node).setBgColor(Color.rgb(200, 200, 200, Math.max(0, opacity)));// blue as a hex web value, explicit alpha)

        return img;
    }


    int imgUsed = 0;

    @Override
    public WritableImage render(final Sprite sprite) {

        WritableImage img = sprite.getImage();

        if (img == null) {
            img = snowFlakePool.get(imgUsed++);
            ImageView v = new ImageView(img);
            v.setPreserveRatio(false);
            v.setFitWidth(sprite.getWidth());
            v.setFitHeight(sprite.getHeight());
            img = v.snapshot(null, null);
        } else {
            img = computeOpacity(sprite, img);
        }

        return img;
    }
}
