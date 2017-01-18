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
    private Player player;
    private int tileMapWidth, tileMapHeight;
    private int tiles[][];



    ObjectsManager(Context context, float meshScale) {
        this.context = context;
        this.meshScale = meshScale;

        // http://theopentutorials.com/tutorials/android/xml/android-simple-xmlpullparser-tutorial/

        XmlPullParserFactory pullParserFactory;
        try {
            pullParserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = pullParserFactory.newPullParser();

            InputStream in_s = context.getApplicationContext().getAssets().open("levels/level1.xml");
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in_s, null);

            int eventType = parser.getEventType();

            tiles = new int[1][1];

            while (eventType != XmlPullParser.END_DOCUMENT){
                String name;

                switch (eventType){
                    case XmlPullParser.START_DOCUMENT:
                        break;
                    case XmlPullParser.START_TAG:
                        name = parser.getName();

                        if (name.equals("tilemap")) {

                            tileMapWidth = Integer.valueOf(parser.getAttributeValue(0));

                            tileMapHeight = Integer.valueOf(parser.getAttributeValue(1));

                            tiles = new int[tileMapWidth][tileMapHeight];
                        }

                        if (name.equals("tile")) {

                            int x = Integer.valueOf(parser.getAttributeValue(0));
                            int y = Integer.valueOf(parser.getAttributeValue(1));
                            int t = Integer.valueOf(parser.getAttributeValue(2));
                            tiles[x][y] = t;
                        }

                        break;
                    case XmlPullParser.END_TAG:
                        //name = parser.getName();
                        break;
                }
                eventType = parser.next();
            }

            in_s.close();
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
    }


    private void iteratingTick(ArrayList<GameObject> list)
    {
        for (int x = -4; x < 6; x++)
            for (int y = -2; y < 4; y++)
            {
                int index = (player.getTileX()+x) * tileMapHeight + (player.getTileY()+y);
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
                int index = (player.getTileX()+x) * tileMapHeight + (player.getTileY()+y);
                if (index < 0) System.out.println("Remder : POZA KRAWĘDZ !!!!");
                list.get(index).render(canvas);
            }
    }

    void render(Canvas canvas)
    {
        iteratingRender(canvas, scemery_List);
        player.render(canvas);
    }


    void loadLevel(int level_int) {


        System.out.println("TileMap: " +tileMapWidth +":" +tileMapHeight);

        for (int x = 0; x < tileMapWidth; x++) {
            for (int y = 0; y < tileMapHeight; y++) {

                int s = tiles[x][y];
                scemery_List.add(new Scenery(Textures.getInstance(context, meshScale).groundTiles[s], x * meshScale, y * meshScale));
            }
        }

        player = new Player(context, 6 * meshScale, 6 * meshScale, meshScale, this);
    }

    Player getPlayer() {
        return player;
    }

}