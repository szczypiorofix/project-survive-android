package com.szczypiorofix.projectsurvive.main;


import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.ArrayList;

public interface GameObject {

    void render(Canvas canvas);
    void tick(ArrayList<GameObject> objects);
    Rect getBounds();

    float getX();
    void setX(float x);

    float getY();
    void setY(float y);

    int getWidth();
    void setWidth(int width);

    int getHeight();
    void setHeight(int height);

    float getVelX();
    void setVelX(float velX);

    float getVelY();
    void setVelY(float velY);

    boolean isAction();
    void setAction(boolean action);

    Direction getDirection();
    void setDirection(Direction direction);
}
