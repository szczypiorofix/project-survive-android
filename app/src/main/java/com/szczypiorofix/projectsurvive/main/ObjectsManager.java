package com.szczypiorofix.projectsurvive.main;


import android.content.Context;
import android.graphics.Canvas;

import com.szczypiorofix.projectsurvive.graphics.Textures;
import com.szczypiorofix.projectsurvive.objects.Player;
import com.szczypiorofix.projectsurvive.objects.Scenery;

import java.util.ArrayList;



public class ObjectsManager {


    private ArrayList<GameObject> scenery_List = new ArrayList<>();
    private Context context;
    private float meshScale;
    private TileMap level;
    private Player player;
    private boolean addDefaultPlayer;
    private int tilesToLeft, tilesToRight, tilesToTop, tilesToDown;
    private int tilesOnWidth, tilesOnHeight;



    ObjectsManager(Context context, float meshScale, int screenWidth, int screenHeight) {
        this.context = context;
        this.meshScale = meshScale;
        tilesToTop = -4 - (MainActivity.TILES_ON_HEIGHT /2);
        tilesToDown = 4 + (MainActivity.TILES_ON_HEIGHT /2);
        tilesToLeft =  -3 - (int) ((screenWidth / meshScale) / 2);
        tilesToRight =  5 + (int) ((screenWidth / meshScale) / 2);
        tilesOnHeight = (int) (screenHeight / meshScale) + 2;
        tilesOnWidth = (int) (screenWidth / meshScale) + 1;
        addDefaultPlayer = true;
    }


    private void iteratingTick(ArrayList<GameObject> list)
    {
        for (int x = tilesToLeft; x < tilesToRight; x++)
            for (int y = tilesToTop; y < tilesToDown; y++)
            {
                int index = (player.getTileX(0)+x) * level.getTileMapHeight() + (player.getTileY(0)+y);
                if (index >= 0) list.get(index).tick();
            }
    }

    void tick()
    {
        iteratingTick(scenery_List);

        player.tick();
    }

    private void iteratingRender(Canvas canvas, ArrayList<GameObject> list)
    {

        for (int x = tilesToLeft; x < tilesToRight; x++) {
            for (int y = tilesToTop; y < tilesToDown; y++) {
                int index = (player.getTileX(0) + x) * level.getTileMapHeight() + (player.getTileY(0) + y);
                if (index >= 0) list.get(index).render(canvas);
            }
        }
    }

    void render(Canvas canvas)
    {
        iteratingRender(canvas, scenery_List);

        player.render(canvas);
    }


    void setLevelToManage(TileMap level) {

        this.level = level;

        for (int x = 0; x < level.getTileMapWidth(); x++) {
            for (int y = 0; y < level.getTileMapHeight(); y++) {

                int index = level.getGroundTile(x, y);

                scenery_List.add(new Scenery(Textures.getInstance(context, meshScale).groundTiles[index], x * meshScale, y * meshScale, index));

                index = level.getCollisionTile(x, y);
                if (index == TileMap.PLAYER_TILE) {
                    player = new Player(context, x * meshScale, y * meshScale, meshScale, this, -1);
                    addDefaultPlayer = false;
                }
            }
        }
        if (addDefaultPlayer) player = new Player(context, 15 * meshScale, 15 * meshScale, meshScale, this, -1);
    }



    public int getTilesToLeft() {
        return tilesToLeft;
    }

    public int getTilesToRight() {
        return tilesToRight;
    }

    public int getTilesToTop() {
        return tilesToTop;
    }

    public int getTilesToDown() {
        return tilesToDown;
    }

    public int getTilesOnWidth() {
        return tilesOnWidth;
    }

    public int getTilesOnHeight() {
        return tilesOnHeight;
    }

    Player getPlayer() {
        return player;
    }

    public ArrayList<GameObject> getScenery_List() {
        return scenery_List;
    }
}