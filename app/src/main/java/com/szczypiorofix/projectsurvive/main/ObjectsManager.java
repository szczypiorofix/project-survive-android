package com.szczypiorofix.projectsurvive.main;


import android.content.Context;
import android.graphics.Canvas;

import com.szczypiorofix.projectsurvive.graphics.Textures;
import com.szczypiorofix.projectsurvive.objects.Player;
import com.szczypiorofix.projectsurvive.objects.Scenery;

import java.util.ArrayList;



public class ObjectsManager {


    private ArrayList<GameObject> scemery_List = new ArrayList<>();
    private ArrayList<GameObject> player_List = new ArrayList<>(1);
    private Context context;
    private float meshScale;
    private TileMap level;
    private Player player;



    ObjectsManager(Context context, float meshScale) {
        this.context = context;
        this.meshScale = meshScale;
    }


    private void iteratingTick(ArrayList<GameObject> list)
    {
        for (int x = -4; x < 5; x++)
            for (int y = -2; y < 4; y++)
            {
                int index = (player.getTileX()+x) * level.getTileMapHeight() + (player.getTileY()+y);
                if (index < 0) System.out.println("Ticks : POZA KRAWĘDZ !!!!");
                list.get(index).tick(list);
            }
    }

    void tick()
    {
        iteratingTick(scemery_List);
        //iteratingTick(player_List);
        player.tick(player_List);
    }

    private void iteratingRender(Canvas canvas, ArrayList<GameObject> list)
    {
        for (int x = -4; x < 6; x++)
            for (int y = -2; y < 4; y++)
            {
                int index = (player.getTileX()+x) * level.getTileMapHeight() + (player.getTileY()+y);
                if (index < 0) System.out.println("Remder : POZA KRAWĘDZ !!!!");
                list.get(index).render(canvas);
            }
    }

    void render(Canvas canvas)
    {
        iteratingRender(canvas, scemery_List);
        //iteratingRender(canvas, player_List);
        player.render(canvas);
    }


    void setLevelToManage(TileMap level) {

        this.level = level;
        for (int x = 0; x < level.getTileMapWidth(); x++) {
            for (int y = 0; y < level.getTileMapHeight(); y++) {

                int s = level.getGroundTile(x, y);
                if (s != -1)
                scemery_List.add(new Scenery(Textures.getInstance(context, meshScale).groundTiles[s], x * meshScale, y * meshScale));
            }
        }

        player = new Player(context, 20 * meshScale, 20 * meshScale, meshScale, this);
        player_List.add(player);
    }


    Player getPlayer() {
        return player;
    }
}