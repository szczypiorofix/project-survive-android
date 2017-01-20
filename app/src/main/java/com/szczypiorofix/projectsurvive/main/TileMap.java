package com.szczypiorofix.projectsurvive.main;


import android.content.Context;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class TileMap {

    private int level;
    private int layers;
    private int tileMapWidth, tileMapHeight;
    private int tileWidth, tileHeight;
    private int groundTiles[][];
    private int collisionTiles[][];
    private int currentLayer;


    public TileMap(Context context, int level) {

        this.level = level;
        layers = 0;
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

            while (eventType != XmlPullParser.END_DOCUMENT){

                String name = "";

                switch (eventType){
                    case XmlPullParser.START_DOCUMENT:
                        //countries = new ArrayList();
                        break;
                    case XmlPullParser.START_TAG:
                        name = parser.getName();
                        //if (name.equals("country")){
                            //country = new Country();
                            //country.id=parser.getAttributeValue(null,"id");
                        //} else if (country != null){
                            //if (name.equals("name")){
                                //country.name = parser.nextText();
                            //} else if (name.equals("capital")){
                                //country.capital = parser.nextText();
                            //}
                        //}
                        break;
                    case XmlPullParser.END_TAG:
                        name = parser.getName();
                        //if (name.equalsIgnoreCase("country") && country != null){
                            //ountries.add(country);
                        //}
                }
                eventType = parser.next();
            }

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