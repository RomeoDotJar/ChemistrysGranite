package ru.romeo.cg.surface.particle;

import android.graphics.Point;

public class GenericCircle extends Point {
    private int spd, frame, weight;

    public GenericCircle(int x, int y, int spd, int weight) {
        this.x = x;
        this.y = y;

        this.spd = spd;
        this.weight = weight;
    }

    public void update() {
        y+=spd;
        x+=Math.sin(frame)*spd/weight;
        frame++;
    }

    public int getWeight() {
        return weight;
    }

    public static int genSpd(int minSpd, int maxSpd) {
        return minSpd+(int)(Math.random()*(maxSpd-minSpd));
    }
}
