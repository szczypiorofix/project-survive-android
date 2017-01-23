package com.szczypiorofix.projectsurvive.objects;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.szczypiorofix.projectsurvive.graphics.SpriteAnimation;
import com.szczypiorofix.projectsurvive.graphics.Textures;
import com.szczypiorofix.projectsurvive.main.Direction;
import com.szczypiorofix.projectsurvive.main.GameObject;
import com.szczypiorofix.projectsurvive.main.MainActivity;
import com.szczypiorofix.projectsurvive.main.ObjectsManager;
import com.szczypiorofix.projectsurvive.main.TileMap;



public class Player extends GameObject {

    private float meshScale;
    private ObjectsManager objectsManager;
    private Context context;
    private float x, y;
    private float width, height;
    private boolean action;
    private Direction direction;
    private float MAX_SPEED;
    private SpriteAnimation runE, runN, runS, runW;
    private float velX, velY;
    private TileMap currentLevel;
    private int[][] collisions;
    private boolean collide;
    private int index;
    private Rect bounds, boundsTop, boundsLeft, boundsRight, boundsWhole;


    public Player(Context context, float x, float y, float meshScale, ObjectsManager objectsManager, int index) {

        this.context = context;
        this.meshScale = meshScale;
        this.objectsManager = objectsManager;
        this.x = x;
        this.y = y;
        this.index = index;
        velX = 0;
        velY = 0;

        bounds = new Rect(0, 0 ,0 ,0);
        boundsTop = new Rect(0, 0 ,0 ,0);
        boundsLeft = new Rect(0, 0 ,0 ,0);
        boundsRight = new Rect(0, 0 ,0 ,0);
        boundsWhole = new Rect(0, 0 ,0 ,0);

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

        collide = false;

        width = (int) meshScale;
        height = (int) meshScale;
    }


    @Override
    public void tick() {

        x += velX;
        y += velY;


        // ZABEZPIECZNIENIE PRZED WYCHODZNIEM POZA EKRAN

        if (getTileX(-3) == -1 || getTileX(0) > currentLevel.getTileMapWidth()-objectsManager.getTilesOnWidth())
            x -= velX;

        if (getTileY(-3) == -1 || getTileY(0) > currentLevel.getTileMapHeight()-objectsManager.getTilesOnHeight() +5)
            y -= velY;



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

        // BOUNDS UPDATE
        bounds.left = (int) x;
        bounds.top = (int) (y + height - (height/15));
        bounds.right = (int) (x + width);
        bounds.bottom = (int) (y + height + (height/15));

        boundsTop.left = (int) x;
        boundsTop.top = (int) (y - (height/15));
        boundsTop.right = (int) (x + width);
        boundsTop.bottom = (int) (y + (height/15));

        boundsLeft.left = (int) (x - (width/15));
        boundsLeft.top = (int) (y + (height/20));
        boundsLeft.right = (int) (x + (width/15));
        boundsLeft.bottom = (int) (y + height - (height/20));

        boundsRight.left = (int) (x + width - (width/15));
        boundsRight.top = (int) (y + (height/20));
        boundsRight.right = (int) (x + width + (width/15));
        boundsRight.bottom = (int) (y + height - (height/20));

        boundsWhole.left = (int) x;
        boundsWhole.top = (int) y;
        boundsWhole.right = (int) (x + width);
        boundsWhole.bottom = (int) (y + height);

        manageCollisions();
    }


    private void manageCollisions() {

        collide = (collisions[getTileX(0)][getTileY(0)] == TileMap.COLLISION_TILE);

        int index = collisions[getTileX(0)][getTileY(0)];

        if (index == TileMap.COLLISION_TILE) {
            if (velX > 0) x = x - MAX_SPEED;
            if (velX < 0) x = x + MAX_SPEED;
            if (velY > 0) y = y - MAX_SPEED;
            if (velY < 0) y = y + MAX_SPEED;
        }



        //if (Rect.intersects(getBoundsRight(), objectRight.getBounds())) {
        //    x = objectRight.getX() - (objectRight.getWidth());
        //}
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

        //if (collide) {
        Paint p = new Paint();
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(1);
        p.setColor(Color.RED);
        canvas.drawRect(getBoundsRight(), p);
        canvas.drawRect(getBoundsLeft(), p);
        canvas.drawRect(getBoundsTop(), p);
        canvas.drawRect(getBounds(), p);
        //}
    }



    public Rect getBounds() {
        return bounds;
    }

    private Rect getBoundsTop() {
        return boundsTop;
    }

    private Rect getBoundsRight() {
        return boundsRight;
    }

    private Rect getBoundsLeft() {
        return boundsLeft;
    }

    private Rect getWholeBounds() {
        return boundsWhole;
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
    public Direction getDirection() {
        return direction;
    }

    @Override
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public int getIndex() {
        return index;
    }

    public int getTileX(int offset) {
        return ((int) ((x + (width/2)) / meshScale)) +offset;
    }

    public int getTileY(int offset) {
        return ((int) ((y + (height/2)) / meshScale)) + offset;
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
        this.currentLevel = level;
        collisions = level.getCollisionTiles();
    }
}
