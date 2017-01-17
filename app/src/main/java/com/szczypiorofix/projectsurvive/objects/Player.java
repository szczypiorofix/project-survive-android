package com.szczypiorofix.projectsurvive.objects;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.szczypiorofix.projectsurvive.R;
import com.szczypiorofix.projectsurvive.graphics.Textures;
import com.szczypiorofix.projectsurvive.main.Direction;
import com.szczypiorofix.projectsurvive.main.GameObject;
import com.szczypiorofix.projectsurvive.main.ObjectsManager;

import org.w3c.dom.Text;

import java.util.LinkedList;



public class Player extends GameObject {

    private float meshScale;
    private ObjectsManager objectsManager;
    private float x, y;
    private float width, height;
    private boolean action;
    private Direction direction;
    private float MAX_SPEED;
    private boolean visible;
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
        visible = true;
        MAX_SPEED = meshScale / 10;
        direction = Direction.SOUTH;
        image = Textures.getInstance(context, meshScale).idlePlayerL[0];
    }


    @Override
    public void tick(LinkedList<GameObject> objects) {

        x += velX;
        y += velY;
    }

    @Override
    public void render(Canvas canvas) {
        canvas.drawBitmap(image, x, y, null);
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
    public boolean canFall() {
        return canFall;
    }

    @Override
    public void setCanFall(boolean canFall) {
        this.canFall = canFall;
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public void setDirection(Direction direction) {
        this.direction = direction;
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
