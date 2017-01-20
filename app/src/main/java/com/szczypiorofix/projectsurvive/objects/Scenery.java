package com.szczypiorofix.projectsurvive.objects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.szczypiorofix.projectsurvive.main.Direction;
import com.szczypiorofix.projectsurvive.main.GameObject;

import java.util.ArrayList;


public class Scenery extends GameObject {

    private float x, y;
    private Bitmap bitmap;
    private int width, height;
    private int id;
    private Rect bounds;


    public Scenery(Bitmap bitmap, float x, float y, int id) {
        this.bitmap = bitmap;
        this.width = this.bitmap.getWidth();
        this.height = this.bitmap.getHeight();
        this.x = x;
        this.y = y;
        this.id = id;
        bounds = new Rect((int) x, (int) y, (int) x+width, (int) y+height);
    }


    @Override
    public void render(Canvas canvas) {
        canvas.drawBitmap(bitmap, x, y, null);
    }

    @Override
    public void tick(ArrayList<GameObject> objects) {

    }

    public int getIndex() {
        return id;
    }

    @Override
    public Rect getBounds() {
        return bounds;
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public void setX(float x) {
        this.x = x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public void setY(float y) {
        this.y = y;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public float getVelX() {
        return 0;
    }

    @Override
    public void setVelX(float velX) {
    }

    @Override
    public float getVelY() {
        return 0;
    }

    @Override
    public void setVelY(float velY) {
    }

    @Override
    public boolean isAction() {
        return false;
    }

    @Override
    public void setAction(boolean action) {
    }

    @Override
    public Direction getDirection() {
        return null;
    }

    @Override
    public void setDirection(Direction direction) {

    }
}
