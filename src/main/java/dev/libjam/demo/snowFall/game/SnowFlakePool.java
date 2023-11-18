package dev.libjam.demo.snowFall.game;

import java.util.ArrayList;
import java.util.List;

public class SnowFlakePool {


    private List<SnowFlakeImage> snowFlakes = new ArrayList<>();

    private int size;

    public SnowFlakePool(int size) {
        this.size = size;
    }


    public void init() {

        SnowFlakeImage img;

        for (int i = 0; i < size; i++) {
            img = new SnowFlakeImage();
            snowFlakes.add(img);
        }
    }

    public SnowFlakeImage get(int i) {
        return snowFlakes.get(i);
    }


}
