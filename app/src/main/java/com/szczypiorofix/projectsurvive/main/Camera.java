package com.szczypiorofix.projectsurvive.main;




import com.szczypiorofix.projectsurvive.objects.Player;

class Camera {

    private float x, y;
    private float width, height;
    private ObjectsManager objectsManager;
    private TileMap level;

    Camera(float x, float y, float width, float height, ObjectsManager objectsManager, TileMap level)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.objectsManager = objectsManager;
        this.level = level;
    }

    void tick(Player player)
    {
        if ((player.getTileX(0) > (objectsManager.getTilesOnWidth()/2)+1) && (player.getTileX(0) < (level.getTileMapWidth()-10)))
        x = -player.getX() + (width/2) - (player.getWidth()/2);

        if ((player.getTileY(0) > (objectsManager.getTilesOnHeight()/2)+1) && (player.getTileY(0) < (level.getTileMapHeight()-3)))
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