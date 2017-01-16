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
import java.util.LinkedList;



public class ObjectsManager {


    private LinkedList<GameObject> player_List = new LinkedList<>();
    private LinkedList<GameObject> scemery_List = new LinkedList<>();
    private Context context;
    private float meshScale;
    private Camera camera;
    private Player player;

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

        XmlPullParserFactory pullParserFactory;
        try {
            pullParserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = pullParserFactory.newPullParser();

            InputStream in_s = context.getApplicationContext().getAssets().open("level1.xml");
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in_s, null);
            parseXML(parser);

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void parseXML(XmlPullParser parser) throws XmlPullParserException,IOException
    {
        int eventType = parser.getEventType();

        while (eventType != XmlPullParser.END_DOCUMENT){
            String name = null;

            switch (eventType){
                case XmlPullParser.START_DOCUMENT:
                    break;
                case XmlPullParser.START_TAG:
                    name = parser.getName();

                        System.out.println(name);
                        System.out.println("Atrybuty: "+parser.getAttributeCount());
                        int arrtCount = parser.getAttributeCount();
                        for (int i = 0; i < arrtCount; i++) {
                            System.out.println("Atrybut: "+i +": "+parser.getAttributeName(i) +"="+parser.getAttributeValue(i));
                        }

                    break;
                case XmlPullParser.END_TAG:
                    name = parser.getName();
                    break;
            }
            eventType = parser.next();
        }

        //printProducts(products);
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

                switch (s[0]) {
                    case '0':
                        scemery_List.add(new Scenery(Textures.getInstance(context, meshScale).groundTiles[0], x * meshScale, y * meshScale));
                        break;
                    case '1':
                        scemery_List.add(new Scenery(Textures.getInstance(context, meshScale).groundTiles[1], x * meshScale, y * meshScale));
                        break;
                    case '2':
                        scemery_List.add(new Scenery(Textures.getInstance(context, meshScale).groundTiles[2], x * meshScale, y * meshScale));
                        break;
                    case '3':
                        scemery_List.add(new Scenery(Textures.getInstance(context, meshScale).groundTiles[3], x * meshScale, y * meshScale));
                        break;
                    case '4':
                        scemery_List.add(new Scenery(Textures.getInstance(context, meshScale).groundTiles[4], x * meshScale, y * meshScale));
                        break;
                    case '5':
                        scemery_List.add(new Scenery(Textures.getInstance(context, meshScale).groundTiles[5], x * meshScale, y * meshScale));
                        break;
                    case '6':
                        scemery_List.add(new Scenery(Textures.getInstance(context, meshScale).groundTiles[6], x * meshScale, y * meshScale));
                        break;
                    case '7':
                        scemery_List.add(new Scenery(Textures.getInstance(context, meshScale).groundTiles[7], x * meshScale, y * meshScale));
                        break;
                    case '8':
                        scemery_List.add(new Scenery(Textures.getInstance(context, meshScale).groundTiles[8], x * meshScale, y * meshScale));
                        break;
                    case 'a':
                        scemery_List.add(new Scenery(Textures.getInstance(context, meshScale).groundTiles[9], x * meshScale, y * meshScale));
                        break;
                    case 'b':
                        scemery_List.add(new Scenery(Textures.getInstance(context, meshScale).groundTiles[10], x * meshScale, y * meshScale));
                        break;
                    case 'c':
                        scemery_List.add(new Scenery(Textures.getInstance(context, meshScale).groundTiles[11], x * meshScale, y * meshScale));
                        break;
                    case 'd':
                        scemery_List.add(new Scenery(Textures.getInstance(context, meshScale).groundTiles[12], x * meshScale, y * meshScale));
                        break;
                    case 'e':
                        scemery_List.add(new Scenery(Textures.getInstance(context, meshScale).groundTiles[13], x * meshScale, y * meshScale));
                        break;
                    case 'f':
                        scemery_List.add(new Scenery(Textures.getInstance(context, meshScale).groundTiles[14], x * meshScale, y * meshScale));
                        break;
                    case 'g':
                        scemery_List.add(new Scenery(Textures.getInstance(context, meshScale).groundTiles[15], x * meshScale, y * meshScale));
                        break;
                    case 'h':
                        scemery_List.add(new Scenery(Textures.getInstance(context, meshScale).groundTiles[16], x * meshScale, y * meshScale));
                        break;
                    case 'i':
                        scemery_List.add(new Scenery(Textures.getInstance(context, meshScale).groundTiles[17], x * meshScale, y * meshScale));
                        break;
                    case 'j':
                        scemery_List.add(new Scenery(Textures.getInstance(context, meshScale).groundTiles[18], x * meshScale, y * meshScale));
                        break;
                    case 'k':
                        scemery_List.add(new Scenery(Textures.getInstance(context, meshScale).groundTiles[19], x * meshScale, y * meshScale));
                        break;
                    case 'l':
                        scemery_List.add(new Scenery(Textures.getInstance(context, meshScale).groundTiles[20], x * meshScale, y * meshScale));
                        break;
                    case 'm':
                        scemery_List.add(new Scenery(Textures.getInstance(context, meshScale).groundTiles[21], x * meshScale, y * meshScale));
                        break;
                    case 'n':
                        scemery_List.add(new Scenery(Textures.getInstance(context, meshScale).groundTiles[22], x * meshScale, y * meshScale));
                        break;
                    case 'o':
                        scemery_List.add(new Scenery(Textures.getInstance(context, meshScale).groundTiles[23], x * meshScale, y * meshScale));
                        break;
                    case 'q':
                        scemery_List.add(new Scenery(Textures.getInstance(context, meshScale).groundTiles[24], x * meshScale, y * meshScale));
                        break;
                    case 'p':
                        scemery_List.add(new Scenery(Textures.getInstance(context, meshScale).groundTiles[25], x * meshScale, y * meshScale));
                        break;
                    case 'r':
                        scemery_List.add(new Scenery(Textures.getInstance(context, meshScale).groundTiles[26], x * meshScale, y * meshScale));
                        break;
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