package com.szczypiorofix.projectsurvive.main;


import android.content.Context;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;

class TileMap {

    private int level;
    private int layers;
    private int tileMapWidth, tileMapHeight;
    private int tileWidth, tileHeight;
    private int tiles[][];


    TileMap(Context context, int level) {

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

    int getLevel() {
        return level;
    }

    int getTile(int x, int y) {
        return tiles[x][y];
    }

    int getTileWidth() {
        return tileWidth;
    }

    int getTileHeight() {
        return tileHeight;
    }

    int getTileMapWidth() {
        return tileMapWidth;
    }

    int getTileMapHeight() {
        return tileMapHeight;
    }

}