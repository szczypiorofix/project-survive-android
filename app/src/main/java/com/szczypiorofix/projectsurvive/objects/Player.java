package com.szczypiorofix.projectsurvive.objects;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.szczypiorofix.projectsurvive.graphics.Textures;
import com.szczypiorofix.projectsurvive.main.Direction;
import com.szczypiorofix.projectsurvive.main.GameObject;
import com.szczypiorofix.projectsurvive.main.ObjectsManager;


import java.util.ArrayList;
import java.util.LinkedList;



public class Player implements GameObject {

    private float meshScale;
    private ObjectsManager objectsManager;
    private float x, y;
    private int width, height;
    private boolean action;
    private Direction direction;
    private float MAX_SPEED;
    private boolean canFall;
    private float velX, velY;
    private Bitmap image;


    public Player(Context context, float x, float y, float meshScale, ObjectsManager objectsManager) {

        this.meshScale = meshScale;
        this.objectsManager = objectsManager;
        this.x = x;
        this.y = y;
        velX = 0;
        velY = 0;
        MAX_SPEED = meshScale / 10;
        direction = Direction.SOUTH;
        image = Textures.getInstance(context, meshScale).idlePlayerL[0];
        width = image.getWidth();
        height = image.getHeight();
    }


    @Override
    public void tick(ArrayList<GameObject> objects) {

        x += velX;
        y += velY;
    }

    @Override
    public void render(Canvas canvas) {
        canvas.drawBitmap(image, x, y, null);
    }



    public Rect getBounds() {
        return null;
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
        return velX;
    }

    @Override
    public void setVelX(float velX) {
        this.velX = velX;
    }

    @Override
    public float getVelY() {
        return velY;
    }

    @Override
    public void setVelY(float velY) {
        this.velY = velY;
    }

    @Override
    public boolean isAction() {
        return action;
    }

    @Override
    public void setAction(boolean action) {
        this.action = action;
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getTileX() {
        return (int) (x / meshScale);
    }

    public int getTileY() {
        return (int) (y / meshScale);
    }

    public void setMoveW(boolean move) {
        if (move) {
            velX = -MAX_SPEED;
            direction = Direction.WEST;
        }
    }

    public void setMoveE(boolean move) {
        if (move) {
            velX = MAX_SPEED;
            direction = Direction.EAST;
        }
    }

    public void setMoveN(boolean move) {
        if (move) {
            velY = -MAX_SPEED;
            direction = Direction.NORTH;
        }
    }

    public void setMoveS(boolean move) {
        if (move) {
            velY = MAX_SPEED;
            direction = Direction.SOUTH;
        }
    }
}
