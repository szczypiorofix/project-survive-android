package com.szczypiorofix.projectsurvive.main;


import android.content.Context;
import android.graphics.Canvas;

import com.szczypiorofix.projectsurvive.graphics.Textures;
import com.szczypiorofix.projectsurvive.objects.Player;
import com.szczypiorofix.projectsurvive.objects.Scenery;

import java.util.LinkedList;

public class ObjectsManager {


    private LinkedList<GameObject> player_List = new LinkedList<>();
    private LinkedList<GameObject> scemery_List = new LinkedList<>();
    private Context context;
    private float meshScale;
    private Camera camera;
    private Player player;

    private final String[] level1 = {
            "0.........................................................................................................",
            "..........................................................................................................",
            "..........................................................................................................",
            "..........................................................................................................",
            "..........................................................................................................",
            "..........................................................................................................",
            "..........................................................................................................",
            "..........................................................................................................",
            "..........................................................................................................",
            ".........................................................................................................."

    };

    private final String[] level2 = {
            "..........................................................................................................",
            ".........................................................................................................."
    };


    ObjectsManager(Context context, float meshScale, Camera camera) {
        this.context = context;
        this.meshScale = meshScale;
        this.camera = camera;
    }



    private void iteratingTick(LinkedList<GameObject> list)
    {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).tick(list);
        }
    }

    void tick()
    {
        iteratingTick(scemery_List);
        iteratingTick(player_List);
    }

    private void iteratingRender(Canvas canvas, LinkedList<GameObject> list)
    {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isVisible()) list.get(i).render(canvas);
        }
    }

    void render(Canvas canvas)
    {
        iteratingRender(canvas, scemery_List);
        iteratingRender(canvas, player_List);
    }

    void clearLevel()
    {
        camera.setX(0);
        camera.setY(0);
        player_List.clear();
    }

    public void loadLevel(int level_int) {
        //camera.setX(0);
        //camera.setY(0);
        char[] s = new char[1];
        String[] level;

        switch (level_int) {
            case 1: level = level1;
                break;
            case 2: level = level2;
                break;
            default:
                level = level1;
                break;
        }

        for (int y = 0; y < level.length; y++)
            for (int x = 0; x < level[0].length(); x++) {
                level[y].getChars(x, x+1, s, 0);

                if (s[0] == 'p') {
                    player = new Player(context, x * meshScale, y * meshScale, meshScale, this);
                    player_List.add(player);
                }

                if (s[0] == '1') {
                    scemery_List.add(new Scenery(Textures.getInstance(context, meshScale).groundTiles[0], x * meshScale, y * meshScale));
                }
            }
    }


    public Player getPlayer() {
        return player;
    }

    public  LinkedList<GameObject> getScemery_List() {
        return scemery_List;
    }
}