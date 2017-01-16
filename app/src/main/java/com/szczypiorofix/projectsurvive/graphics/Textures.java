package com.szczypiorofix.projectsurvive.graphics;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.szczypiorofix.projectsurvive.R;

final public class Textures {

    private static Textures instance = null;

    public Bitmap[] groundTiles = new Bitmap[20];

    public Bitmap[] idlePlayerL = new Bitmap[2];
    public Bitmap[] idlePlayerR = new Bitmap[2];

    public Bitmap[] runPlayerL = new Bitmap[4];
    public Bitmap[] runPlayerR = new Bitmap[4];

    public Bitmap buttonLeftPressedImage, buttonLeftDefaultImage, buttonRightPressedImage, buttonRightDefaultImage, buttonJumpPressedImage, buttonJumpDefaultImage;
    public Bitmap backgroundImage;

    private Textures(Context context, float meshScale) {

        backgroundImage = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.background_quake),400, 400, false);

        // ILOSC TILESOW W RYSUNKU: X i Y
        int tiles_x = 2;
        int tiles_y = 4;

        Bitmap buttonTileSheetBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.direction_buttons_big), (int) meshScale * tiles_x, (int) meshScale * tiles_y, false);
        SpriteSheet buttonTilesSheet = new SpriteSheet(buttonTileSheetBitmap, tiles_x, tiles_y);

        buttonRightDefaultImage = buttonTilesSheet.getSprite(1, 1);
        buttonRightPressedImage = buttonTilesSheet.getSprite(2, 1);
        buttonLeftDefaultImage = buttonTilesSheet.getSprite(1, 2);
        buttonLeftPressedImage = buttonTilesSheet.getSprite(2, 2);
        buttonJumpDefaultImage = buttonTilesSheet.getSprite(1, 3);
        buttonJumpPressedImage = buttonTilesSheet.getSprite(2, 3);
        buttonJumpDefaultImage = buttonTilesSheet.getSprite(1, 3);
        buttonJumpPressedImage = buttonTilesSheet.getSprite(2, 3);

        tiles_x = 3;
        tiles_y = 6;

        Bitmap groundTileSheetBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.tileset_32), (int) meshScale * tiles_x, (int) meshScale * tiles_y, false);
        SpriteSheet groundTilesSheet = new SpriteSheet(groundTileSheetBitmap, tiles_x, tiles_y);

        groundTiles[0] = groundTilesSheet.getSprite(1, 1);
        groundTiles[1] = groundTilesSheet.getSprite(2, 1);
        groundTiles[2] = groundTilesSheet.getSprite(3, 1);
        groundTiles[3] = groundTilesSheet.getSprite(1, 2);
        groundTiles[4] = groundTilesSheet.getSprite(2, 2);
        groundTiles[5] = groundTilesSheet.getSprite(3, 2);
        groundTiles[6] = groundTilesSheet.getSprite(1, 3);
        groundTiles[7] = groundTilesSheet.getSprite(2, 3);
        groundTiles[8] = groundTilesSheet.getSprite(3, 3);
        groundTiles[9] = groundTilesSheet.getSprite(1, 4);
        groundTiles[10] = groundTilesSheet.getSprite(2, 4);
        groundTiles[11] = groundTilesSheet.getSprite(3, 4);
        groundTiles[12] = groundTilesSheet.getSprite(1, 5);
        groundTiles[13] = groundTilesSheet.getSprite(2, 5);
        groundTiles[14] = groundTilesSheet.getSprite(3, 5);
        groundTiles[15] = groundTilesSheet.getSprite(1, 6);
        groundTiles[16] = groundTilesSheet.getSprite(2, 6);


        tiles_x = 4;
        tiles_y = 3;

        Bitmap playerSheetBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.player_16), (int) meshScale * tiles_x, (int) meshScale * tiles_y, false);
        SpriteSheet playerSheet = new SpriteSheet(playerSheetBitmap, tiles_x, tiles_y);


        runPlayerR[0] = playerSheet.getSprite(1,1);
        runPlayerR[1] = playerSheet.getSprite(2,1);
        runPlayerR[2] = playerSheet.getSprite(3,1);
        runPlayerR[3] = playerSheet.getSprite(4,1);

        runPlayerL[0] = playerSheet.getSprite(1,2);
        runPlayerL[1] = playerSheet.getSprite(2,2);
        runPlayerL[2] = playerSheet.getSprite(3,2);
        runPlayerL[3] = playerSheet.getSprite(4,2);

        idlePlayerR[0] = playerSheet.getSprite(1,3);
        idlePlayerR[1] = playerSheet.getSprite(2,3);
        idlePlayerL[0] = playerSheet.getSprite(3,3);
        idlePlayerL[1] = playerSheet.getSprite(4,3);
    }

    public synchronized static Textures getInstance(Context context, float meshScale)
    {
        if (instance == null) instance = new Textures(context, meshScale);
        return instance;
    }
}