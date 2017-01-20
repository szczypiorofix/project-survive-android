package com.szczypiorofix.projectsurvive.objects;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.szczypiorofix.projectsurvive.graphics.SpriteAnimation;
import com.szczypiorofix.projectsurvive.graphics.Textures;
import com.szczypiorofix.projectsurvive.main.Direction;
import com.szczypiorofix.projectsurvive.main.GameObject;
import com.szczypiorofix.projectsurvive.main.ObjectsManager;
import com.szczypiorofix.projectsurvive.main.TileMap;

import java.util.ArrayList;



public class Player implements GameObject {

    private float meshScale;
    private ObjectsManager objectsManager;
    private Context context;
    private float x, y;
    private int width, height;
    private boolean action;
    private Direction direction;
    private float MAX_SPEED;
    private SpriteAnimation runE, runN, runS, runW;
    private float velX, velY;
    private TileMap curremtLevel;
    private int[][] collisions;


    public Player(Context context, float x, float y, float meshScale, ObjectsManager objectsManager) {

        this.context = context;
        this.meshScale = meshScale;
        this.objectsManager = objectsManager;
        this.x = x;
        this.y = y;
        velX = 0;
        velY = 0;

        runE = new SpriteAnimation(4, Textures.getInstance(context, meshScale).runPlayerE[0],
                Textures.getInstance(context, meshScale).runPlayerE[1],
                Textures.getInstance(context, meshScale).runPlayerE[2]);

        runW = new SpriteAnimation(4, Textures.getInstance(context, meshScale).runPlayerW[0],
                Textures.getInstance(context, meshScale).runPlayerW[1],
                Textures.getInstance(context, meshScale).runPlayerW[2]);

        runN = new SpriteAnimation(4, Textures.getInstance(context, meshScale).runPlayerN[0],
                Textures.getInstance(context, meshScale).runPlayerN[1],
                Textures.getInstance(context, meshScale).runPlayerN[2]);

        runS = new SpriteAnimation(4, Textures.getInstance(context, meshScale).runPlayerS[0],
                Textures.getInstance(context, meshScale).runPlayerS[1],
                Textures.getInstance(context, meshScale).runPlayerS[2]);

        MAX_SPEED = meshScale / 25;
        direction = Direction.SOUTH;

        width = Textures.getInstance(context, meshScale).runPlayerE[0].getWidth();
        height = Textures.getInstance(context, meshScale).runPlayerE[0].getHeight();
    }


    @Override
    public void tick(ArrayList<GameObject> objects) {

        x += velX;
        y += velY;

        switch (direction) {
            case SOUTH:
                if (velY != 0) runS.runAnimation();
                break;
            case SOUTHEAST:
                if (velX != 0) runE.runAnimation();
                break;
            case SOUTHWEST:
                if (velX != 0) runW.runAnimation();
                break;
            case NORTHEAST:
                if (velX != 0) runE.runAnimation();
                break;
            case NORTHWEST:
                if (velX != 0) runW.runAnimation();
                break;
            case NORTH:
                if (velY != 0) runN.runAnimation();
                break;
            case EAST:
                if (velX != 0) runE.runAnimation();
                break;
            case WEST:
                if (velX != 0) runW.runAnimation();
                break;
         }

        manageCollisions();
    }


    private void manageCollisions() {

        if (collisions[getTileX()][getTileY()] == 33) {
            System.out.println("COLLISION !!!");
        }
    }



    @Override
    public void render(Canvas canvas) {

        switch (direction) {
            case SOUTH:
                if (velY != 0) runS.drawAnimation(canvas, (int) x, (int) y);
                else canvas.drawBitmap(Textures.getInstance(context, meshScale).runPlayerS[0], x, y, null);
                break;

            case SOUTHEAST:
                if (velX != 0) runE.drawAnimation(canvas, (int) x, (int) y);
                else canvas.drawBitmap(Textures.getInstance(context, meshScale).runPlayerE[0], x, y, null);
                break;

            case SOUTHWEST:
                if (velX != 0) runW.drawAnimation(canvas, (int) x, (int) y);
                else canvas.drawBitmap(Textures.getInstance(context, meshScale).runPlayerW[0], x, y, null);
                break;

            case NORTH:
                if (velY != 0) runN.drawAnimation(canvas, (int) x, (int) y);
                else canvas.drawBitmap(Textures.getInstance(context, meshScale).runPlayerN[2], x, y, null);
                break;

            case NORTHEAST:
                if (velX != 0) runE.drawAnimation(canvas, (int) x, (int) y);
                else canvas.drawBitmap(Textures.getInstance(context, meshScale).runPlayerE[0], x, y, null);
                break;

            case EAST:
                if (velX != 0) runE.drawAnimation(canvas, (int) x, (int) y);
                else canvas.drawBitmap(Textures.getInstance(context, meshScale).runPlayerE[0], x, y, null);
                break;

            case WEST:
                if (velX != 0) runW.drawAnimation(canvas, (int) x, (int) y);
                else canvas.drawBitmap(Textures.getInstance(context, meshScale).runPlayerW[0], x, y, null);
                break;

            case NORTHWEST:
                if (velX != 0) runW.drawAnimation(canvas, (int) x, (int) y);
                else canvas.drawBitmap(Textures.getInstance(context, meshScale).runPlayerW[0], x, y, null);
                break;
        }
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

    public void setMoveNW(boolean move) {
        if (move) {
            velX = -MAX_SPEED;
            velY = -MAX_SPEED;
            direction = Direction.NORTHWEST;
        }
    }

    public void setMoveNE(boolean move) {
        if (move) {
            velX = MAX_SPEED;
            velY = -MAX_SPEED;
            direction = Direction.NORTHEAST;
        }
    }

    public void setMoveSW(boolean move) {
        if (move) {
            velX = -MAX_SPEED;
            velY = MAX_SPEED;
            direction = Direction.SOUTHWEST;
        }
    }

    public void setMoveSE(boolean move) {
        if (move) {
            velX = MAX_SPEED;
            velY = MAX_SPEED;
            direction = Direction.SOUTHEAST;
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

    public void setCurrentLevel(TileMap level) {
        this.curremtLevel = level;
        collisions = level.getCollisionTiles();
        System.out.println("LEVEL 1: "+collisions[23][20]);
    }
}
