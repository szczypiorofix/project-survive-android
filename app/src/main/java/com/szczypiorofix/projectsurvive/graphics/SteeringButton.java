package com.szczypiorofix.projectsurvive.graphics;


import android.graphics.Bitmap;
import android.graphics.Canvas;

public class SteeringButton {

    private int x, y;
    private Bitmap image_default, image_pressed;
    private int width, height;
    private boolean pressed;

    public SteeringButton(Bitmap image_default, Bitmap image_pressed, int x, int y) {
        this.image_default = image_default;
        this.image_pressed = image_pressed;
        this.x = x;
        this.y = y;
        width = image_default.getWidth();
        height = image_default.getHeight();
        pressed = false;
    }


    public void draw(Canvas canvas) {
        if (pressed) canvas.drawBitmap(image_pressed, x, y, null);
        else canvas.drawBitmap(image_default, x, y, null);
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