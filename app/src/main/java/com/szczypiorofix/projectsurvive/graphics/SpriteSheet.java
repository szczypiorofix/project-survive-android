package com.szczypiorofix.projectsurvive.graphics;


import android.graphics.Bitmap;

class SpriteSheet {

    private Bitmap bitmap;
    private int tileWidth, tileHeight;
    private int margin;


    SpriteSheet(Bitmap bitmap, int rows, int cols, int margin) {
        this.bitmap = bitmap;
        tileWidth =  bitmap.getWidth() / rows;
        tileHeight = bitmap.getHeight() / cols;
        this.margin = margin;
    }

    SpriteSheet(Bitmap bitmap, int rows, int cols) {
        this.bitmap = bitmap;
        tileWidth =  bitmap.getWidth() / rows;
        tileHeight = bitmap.getHeight() / cols;
        margin = 0;
    }

    Bitmap getSprite(int col, int row) {
        //System.out.println("Reading sprite: "+col+":"+row+"...");
        return Bitmap.createBitmap(bitmap, ((col-1) * tileWidth) + (margin*(col-1)), ((row-1) * tileHeight) + (margin*(row-1)) , tileWidth, tileHeight);
    }

}