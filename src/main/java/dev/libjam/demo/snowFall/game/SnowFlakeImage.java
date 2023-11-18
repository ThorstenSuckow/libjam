package dev.libjam.demo.snowFall.game;

import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class SnowFlakeImage extends WritableImage {


    public SnowFlakeImage() {
        super(5, 5);
        PixelWriter wr = this.getPixelWriter();

        for (int i = 0; i < 5; i++) {
            for (int a = 0; a < 5; a++) {

                wr.setColor(i, a, Color.CYAN);
            }

        }

    }
}
