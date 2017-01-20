package com.szczypiorofix.projectsurvive.main;


import android.content.Context;
import android.graphics.Canvas;

import com.szczypiorofix.projectsurvive.graphics.Textures;
import com.szczypiorofix.projectsurvive.objects.Player;
import com.szczypiorofix.projectsurvive.objects.Scenery;

import java.util.ArrayList;



public class ObjectsManager {


    private ArrayList<GameObject> scenery_List = new ArrayList<>();
    private ArrayList<GameObject> player_List = new ArrayList<>(1);
    private Context context;
    private float meshScale;
    private TileMap level;
    private Player player;
    int tilesToLeft, tilesToRight, tilesToTop, tilesToDown;



    ObjectsManager(Context context, float meshScale, int screenWidth, int screenHeight) {
        this.context = context;
        this.meshScale = meshScale;
        tilesToTop = -1 - (MainActivity.TILES_ON_HEIGHT /2);
        tilesToDown = 1 + (MainActivity.TILES_ON_HEIGHT /2);
        tilesToLeft =  -1 - (int) ((screenWidth / meshScale) / 2);
        tilesToRight =  2 + (int) ((screenWidth / meshScale) / 2);
    }


    private void iteratingTick(ArrayList<GameObject> list)
    {
        for (int x = tilesToLeft; x < tilesToRight; x++)
            for (int y = tilesToTop; y < tilesToDown; y++)
            {
                int index = (player.getTileX()+x) * level.getTileMapHeight() + (player.getTileY()+y);
                if (index < 0) System.out.println("Ticks : POZA KRAWĘDZ !!!!");
                list.get(index).tick(list);
            }
    }

    void tick()
    {
        iteratingTick(scenery_List);

        player.tick(player_List);
    }

    private void iteratingRender(Canvas canvas, ArrayList<GameObject> list)
    {
        for (int x = tilesToLeft; x < tilesToRight; x++)
            for (int y = tilesToTop; y < tilesToDown; y++)
            {
                int index = (player.getTileX()+x) * level.getTileMapHeight() + (player.getTileY()+y);
                if (index < 0) System.out.println("Remder : POZA KRAWĘDZ !!!!");
                list.get(index).render(canvas);
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
            }
        }

        player = new Player(context, 20 * meshScale, 20 * meshScale, meshScale, this, -1);
        player_List.add(player);
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

    Player getPlayer() {
        return player;
    }

    public ArrayList<GameObject> getScenery_List() {
        return scenery_List;
    }
}