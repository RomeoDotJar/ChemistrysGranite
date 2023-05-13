package ru.romeo.cg.surface.sprite;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Sprite {
    public static long stepCount = 0;

    private final int frameSize;
    private final Bitmap bitmap;

    private final int framesCountRowInSprite = 4;
    private final int frameCountColInSprite;
    private final Rect[][] frames;
    private int currentFrame;
    private int speed;

    /**
     * speed :
     * 1 - fast
     * 2 - normal
     * 3 - slow
     */

    public Sprite(Bitmap bitmap, int frameSize, int speed) {
        this.bitmap = bitmap;
        this.speed = speed;
        this.frameSize=frameSize;
        frameCountColInSprite = bitmap.getWidth() / frameSize;
        frames = new Rect[framesCountRowInSprite][frameCountColInSprite];

        for (int i = 0; i < framesCountRowInSprite; i++)
            for (int j = 0; j < frameCountColInSprite; j++)
                frames[i][j] = new Rect(
                        frameSize * j, frameSize * i,
                        frameSize * (j + 1), frameSize * (i + 1)
                );
    }


    public void draw(Canvas canvas, Rect rect, int ward) {
        currentFrame = (int) (stepCount / speed) % frameCountColInSprite;
        canvas.drawBitmap(bitmap, frames[ward][currentFrame], rect, new Paint());
    }

    public static void updateNextFrame() {
        stepCount++;
    }
}