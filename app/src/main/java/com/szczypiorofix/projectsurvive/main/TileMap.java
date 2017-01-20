package com.szczypiorofix.projectsurvive.main;


import android.content.Context;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;


public class TileMap {

    private int level;
    private int tileMapWidth, tileMapHeight;
    private int tileWidth, tileHeight;
    private int groundTiles[][];
    private int collisionTiles[][];


    public TileMap(Context context, int level) {

        this.level = level;

        // http://theopentutorials.com/tutorials/android/xml/android-simple-xmlpullparser-tutorial/

        XmlPullParserFactory pullParserFactory;
        try {
            pullParserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = pullParserFactory.newPullParser();

            InputStream in_s = context.getApplicationContext().getAssets().open("levels/level1.xml");
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in_s, null);

            int eventType = parser.getEventType();

            groundTiles = new int[1][1];
            collisionTiles = new int[1][1];

            int x = 0, y = 0, t = 0;
            int currentLayer = -1;
            boolean once = false;

            while (eventType != XmlPullParser.END_DOCUMENT){

                if(eventType == XmlPullParser.START_TAG) {
                    if (parser.getName().equals("tilemap")) {

                        tileMapWidth = Integer.parseInt(parser.getAttributeValue(0));
                        tileMapHeight = Integer.parseInt(parser.getAttributeValue(1));
                        tileWidth = Integer.parseInt(parser.getAttributeValue(2));
                        tileHeight = Integer.parseInt(parser.getAttributeValue(3));
                        groundTiles = new int[tileMapWidth][tileMapHeight];
                        collisionTiles = new int[tileMapWidth][tileMapHeight];
                    }

                    if (parser.getName().equals("layer")) {
                        currentLayer = Integer.parseInt(parser.getAttributeValue(0));
                    }

                    if (parser.getName().equals("tile")) {
                        if (currentLayer == 0) {

                            x = Integer.parseInt(parser.getAttributeValue(0));
                            y = Integer.parseInt(parser.getAttributeValue(1));
                            t = Integer.parseInt(parser.getAttributeValue(2));
                            collisionTiles[x][y] = t;
                        }
                        if (currentLayer == 1) {
                            if (!once) {
                                once = true;
                                groundTiles = new int[tileMapWidth][tileMapHeight];
                            }
                            x = Integer.parseInt(parser.getAttributeValue(0));
                            y = Integer.parseInt(parser.getAttributeValue(1));
                            t = Integer.parseInt(parser.getAttributeValue(2));
                            groundTiles[x][y] = t;
                        }
                    }
                }

                eventType = parser.next();
            }

            System.out.println("Map: "+tileMapWidth +":" +tileMapHeight +"   "+tileWidth+":" +tileHeight);

            in_s.close();
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
    }


    public int getLevel() {
        return level;
    }

    public int getGroundTile(int x, int y) {
        return groundTiles[x][y];
    }

    public int getCollisionTile(int x, int y) {
        return collisionTiles[x][y];
    }

    public int[][] getCollisionTiles() {
        return collisionTiles;
    }

    public int[][] getGroundTiles() {
        return groundTiles;
    }

    public int getTileWidth() {
        return tileWidth;
    }

    public int getTileHeight() {
        return tileHeight;
    }

    public int getTileMapWidth() {
        return tileMapWidth;
    }

    public int getTileMapHeight() {
        return tileMapHeight;
    }

}