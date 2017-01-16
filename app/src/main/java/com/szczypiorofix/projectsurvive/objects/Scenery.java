package com.szczypiorofix.projectsurvive.objects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.szczypiorofix.projectsurvive.main.Direction;
import com.szczypiorofix.projectsurvive.main.GameObject;

import java.util.LinkedList;


public class Scenery extends GameObject {

    private float x, y;
    private Bitmap bitmap;
    private boolean visible;
    private float width, height;


    public Scenery(Bitmap bitmap, float x, float y) {
        this.bitmap = bitmap;
        this.width = this.bitmap.getWidth();
        this.height = this.bitmap.getHeight();
        this.x = x;
        this.y = y;
        visible = true;
    }


    @Override
    public void render(Canvas canvas) {
        canvas.drawBitmap(bitmap, x, y, null);
    }

    @Override
    public void tick(LinkedList<GameObject> objects) {

    }

    @Override
    public Rect getBounds() {
        return null;
    }

    @Override
    public boolean isVisible() {
        return visible;
    }

    @Override
    public void setVisible(boolean visible) {
        this.visible = visible;
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
    public float getWidth() {
        return width;
    }

    @Override
    public void setWidth(float width) {
        this.width = width;
    }

    @Override
    public float getHeight() {
        return height;
    }

    @Override
    public void setHeight(float height) {
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
    public boolean canFall() {
        return false;
    }

    @Override
    public void setCanFall(boolean canFall) {

    }

    @Override
    public Direction getDirection() {
        return null;
    }

    @Override
    public void setDirection(Direction direction) {

    }
}
