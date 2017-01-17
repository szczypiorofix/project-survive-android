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
import java.util.LinkedList;



public class ObjectsManager {


    private LinkedList<GameObject> player_List = new LinkedList<>();
    private LinkedList<GameObject> scemery_List = new LinkedList<>();
    private Context context;
    private float meshScale;
    private Camera camera;
    private Player player;

    private int tileMapWidth, tileMapHeight;
    private int tileWidth, tileHeight;
    private int tiles[][];

    private final String[] level1 = {
            "011111111111112eeeeeeeeeeeeeeeeeeee",
            "344444444444445eeeeeeeeeeeeeeeeeeee",
            "344444444444445eeeeeeeeeeeeeeeeeeeee",
            "344444444444445eeeeeeeeeeeeeeeeeeeeee",
            "344444444444445eeeeeeeeeeeeeeeeeeeeee",
            "677777777777778eeeeeeeeeeeeeeeeeeeeeee",
            "........................................",
            ".........................................",
            "..............................................",
            "................................................"

    };

    private final String[] level2 = {
            "..........................................................................................................",
            ".........................................................................................................."
    };


    ObjectsManager(Context context, float meshScale, Camera camera) {
        this.context = context;
        this.meshScale = meshScale;
        this.camera = camera;

        // http://theopentutorials.com/tutorials/android/xml/android-simple-xmlpullparser-tutorial/

        XmlPullParserFactory pullParserFactory;
        try {
            pullParserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = pullParserFactory.newPullParser();

            InputStream in_s = context.getApplicationContext().getAssets().open("level1.xml");
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

                            tileWidth = Integer.valueOf(parser.getAttributeValue(2));

                            tileHeight = Integer.valueOf(parser.getAttributeValue(3));

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
                        name = parser.getName();
                        break;
                }
                eventType = parser.next();
            }

            in_s.close();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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


        System.out.println(tileMapWidth +":" +tileMapHeight);

        for (int x = 0; x < tileMapWidth; x++) {
            for (int y = 0; y < tileMapHeight; y++) {

                //System.out.println(x+":"+y+ "  --- " +tiles[x][y]);
                int s = tiles[x][y];

                scemery_List.add(new Scenery(Textures.getInstance(context, meshScale).groundTiles[s], x * meshScale, y * meshScale));
            }
        }

        player = new Player(context, 5 * meshScale, 5 * meshScale, meshScale, this);
        player_List.add(player);
    }


    public Player getPlayer() {
        return player;
    }

    public  LinkedList<GameObject> getScemery_List() {
        return scemery_List;
    }
}