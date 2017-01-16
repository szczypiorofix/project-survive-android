package com.szczypiorofix.projectsurvive.graphics;


import android.graphics.Bitmap;

public class SpriteSheet {

    private Bitmap bitmap;
    private int tileWidth, tileHeight;


    public SpriteSheet(Bitmap bitmap, int rows, int cols) {
        this.bitmap = bitmap;
        tileWidth =  bitmap.getWidth() / rows;
        tileHeight = bitmap.getHeight() / cols;
    }

    public Bitmap getSprite(int col, int row) {
        //System.out.println("Reading sprite: "+col+":"+row+"...");
        return Bitmap.createBitmap(bitmap, (col-1) * tileWidth , (row-1) * tileHeight , tileWidth, tileHeight);
    }

}