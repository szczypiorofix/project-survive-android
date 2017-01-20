package com.szczypiorofix.projectsurvive.main;


class Camera {

    private float x, y;
    private float width, height;

    Camera(float x, float y, float width, float height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    void tick(GameObject player)
    {
        x = -player.getX() + (width/2) - (player.getWidth()/2);
        y = -player.getY() + (height/2) - (player.getHeight()/2);
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}