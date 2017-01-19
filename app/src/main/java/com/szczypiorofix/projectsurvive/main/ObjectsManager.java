package com.szczypiorofix.projectsurvive.main;


import android.content.Context;
import android.graphics.Canvas;

import com.szczypiorofix.projectsurvive.graphics.Textures;
import com.szczypiorofix.projectsurvive.objects.Player;
import com.szczypiorofix.projectsurvive.objects.Scenery;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;



public class ObjectsManager {


    private ArrayList<GameObject> scemery_List = new ArrayList<>();
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
        for (int x = -4; x < 6; x++)
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

        player.tick(null);
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
        player.render(canvas);
    }


    void setLevelToManage(TileMap level) {
        //System.out.println("TileMap: " +tileMapWidth +":" +tileMapHeight);

        for (int x = 0; x < level.getTileMapWidth(); x++) {
            for (int y = 0; y < level.getTileMapHeight(); y++) {

                int s = level.getTile(x, y);
                scemery_List.add(new Scenery(Textures.getInstance(context, meshScale).groundTiles[s], x * meshScale, y * meshScale));
            }
        }

        player = new Player(context, 6 * meshScale, 6 * meshScale, meshScale, this);
    }


    Player getPlayer() {
        return player;
    }

}