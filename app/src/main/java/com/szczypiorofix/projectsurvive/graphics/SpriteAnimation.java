package com.szczypiorofix.projectsurvive.graphics;


import android.graphics.Bitmap;
import android.graphics.Canvas;

public class SpriteAnimation {
    private int frameSkip;
    private int maxKlatki;
    private int index = 0;
    private int klatkaAnimacji = 1;
    private Bitmap[] images;
    private Bitmap currentImage;

    SpriteAnimation(int frameSkip, Bitmap... args)
    {
        this.frameSkip = frameSkip;
        images = new Bitmap[args.length];
        currentImage = args[0];

        System.arraycopy(args, 0, images, 0, args.length);

        maxKlatki = args.length;
    }

    void runAnimation()
    {
        index++;
        if (index > frameSkip)
        {
            index = 0;
            nextFrame();
        }
    }

    public int geMaxKlatki()
    {
        return maxKlatki;
    }

    public int getKlatkaAnimacji()
    {
        return klatkaAnimacji;
    }

    private void nextFrame()
    {
        for (int i = 0; i < maxKlatki; i++)
        {
            if (klatkaAnimacji == i) currentImage = images[i];
        }
        klatkaAnimacji ++;
        if (klatkaAnimacji >= maxKlatki) klatkaAnimacji = 0;
    }

    void drawAnimation(Canvas canvas, int x, int y)
    {
        canvas.drawBitmap(currentImage, x, y, null);
    }
}