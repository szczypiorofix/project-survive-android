package com.szczypiorofix.projectsurvive.main;


import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.ArrayList;

public abstract class GameObject {



    public abstract void render(Canvas canvas);

    public abstract void tick(ArrayList<GameObject> objects);

    public abstract Rect getBounds();

    public abstract float getX();

    public abstract void setX(float x);

    public abstract float getY();

    public abstract void setY(float y);

    public abstract int getWidth();

    public abstract void setWidth(int width);

    public abstract int getHeight();

    public abstract void setHeight(int height);

    public abstract float getVelX();

    public abstract void setVelX(float velX);

    public abstract float getVelY();

    public abstract void setVelY(float velY);

    public abstract boolean isAction();

    public abstract void setAction(boolean action);

    public abstract Direction getDirection();

    public abstract void setDirection(Direction direction);

    public abstract int getIndex();
}
