package com.szczypiorofix.projectsurvive.graphics;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;


public class SteeringButton {

    private int x, y;
    private Bitmap image_default;
    private int width, height;
    private boolean pressed;
    private Rect bounds;

    public SteeringButton(Bitmap image_default, int x, int y) {
        this.image_default = image_default;
        this.x = x;
        this.y = y;
        width = image_default.getWidth();
        height = image_default.getHeight();
        pressed = false;
        bounds = new Rect(x, y, x + width, y + height);
    }


    public void draw(Canvas canvas) {
        canvas.drawBitmap(image_default, x, y, null);
    }

    public Rect getBounds() {
        return bounds;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isPressed() {
        return pressed;
    }

    public void setPressed(boolean pressed) {
        this.pressed = pressed;
    }
}