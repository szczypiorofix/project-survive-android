package com.szczypiorofix.projectsurvive.graphics;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.szczypiorofix.projectsurvive.R;

final public class Textures {

    private static Textures instance = null;

    public Bitmap[] groundTiles = new Bitmap[50];

    public Bitmap[] runPlayerE = new Bitmap[3];
    public Bitmap[] runPlayerW = new Bitmap[3];
    public Bitmap[] runPlayerS = new Bitmap[3];
    public Bitmap[] runPlayerN = new Bitmap[3];


    // STEERING PAD
    public Bitmap buttonWImage, buttonEImage, buttonNImage, buttonSImage,
                  buttonNEImage, buttonNWImage, buttonSEImage, buttonSWImage,
                  buttonCenterImage, stickImage;


    //// LibGDX TUTORIAL !!!! https://www.youtube.com/watch?v=a8MPxzkwBwo&list=PLZm85UZQLd2SXQzsF-a0-pPF6IWDDdrXt




    private Textures(Context context, float meshScale) {

        // ILOSC TILESOW NA RYSUNKU: X i Y
        int tiles_x = 3;
        int tiles_y = 4;

        Bitmap buttonTileSheetBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.game_controller), (int) meshScale * tiles_x, (int) meshScale * tiles_y, false);
        SpriteSheet buttonTilesSheet = new SpriteSheet(buttonTileSheetBitmap, tiles_x, tiles_y);


        buttonNWImage = buttonTilesSheet.getSprite(1,1);
        buttonNImage = buttonTilesSheet.getSprite(2, 1);
        buttonNEImage = buttonTilesSheet.getSprite(3,1);
        buttonEImage = buttonTilesSheet.getSprite(3, 2);
        buttonWImage = buttonTilesSheet.getSprite(1, 2);
        buttonSImage = buttonTilesSheet.getSprite(2, 3);
        buttonSWImage = buttonTilesSheet.getSprite(1, 3);
        buttonSEImage = buttonTilesSheet.getSprite(3, 3);
        buttonCenterImage = buttonTilesSheet.getSprite(2,2);
        stickImage = buttonTilesSheet.getSprite(1, 4);

        tiles_x = 3;
        tiles_y = 4;

        Bitmap groundTileSheetBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.tiles_16), (int) meshScale * tiles_x, (int) meshScale * tiles_y, false);
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
        /**
        groundTiles[12] = groundTilesSheet.getSprite(1, 5);
        groundTiles[13] = groundTilesSheet.getSprite(2, 5);
        groundTiles[14] = groundTilesSheet.getSprite(3, 5);
        groundTiles[15] = groundTilesSheet.getSprite(1, 6);
        groundTiles[16] = groundTilesSheet.getSprite(2, 6);
        groundTiles[17] = groundTilesSheet.getSprite(3, 6);
        groundTiles[18] = groundTilesSheet.getSprite(1, 7);
        groundTiles[19] = groundTilesSheet.getSprite(2, 7);
        groundTiles[20] = groundTilesSheet.getSprite(3, 7);
        groundTiles[21] = groundTilesSheet.getSprite(1, 8);
        groundTiles[22] = groundTilesSheet.getSprite(2, 8);
        groundTiles[23] = groundTilesSheet.getSprite(3, 8);
        groundTiles[24] = groundTilesSheet.getSprite(1, 9);
        groundTiles[25] = groundTilesSheet.getSprite(2, 9);
        groundTiles[26] = groundTilesSheet.getSprite(3, 9);
        groundTiles[27] = groundTilesSheet.getSprite(1, 10);
        groundTiles[28] = groundTilesSheet.getSprite(2, 10);
        groundTiles[29] = groundTilesSheet.getSprite(3, 10);
        groundTiles[30] = groundTilesSheet.getSprite(1, 11);
        groundTiles[31] = groundTilesSheet.getSprite(2, 11);
        groundTiles[32] = groundTilesSheet.getSprite(3, 11);
        groundTiles[33] = groundTilesSheet.getSprite(1, 12);
        groundTiles[34] = groundTilesSheet.getSprite(2, 12);
        groundTiles[35] = groundTilesSheet.getSprite(3, 12);
        groundTiles[36] = groundTilesSheet.getSprite(1, 13);
        groundTiles[37] = groundTilesSheet.getSprite(2, 13);
        groundTiles[38] = groundTilesSheet.getSprite(3, 13);
         **/

        tiles_x = 3;
        tiles_y = 4;

        Bitmap playerSheetBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.player_16_new), (int) meshScale * tiles_x, (int) meshScale * tiles_y, false);
        SpriteSheet playerSheet = new SpriteSheet(playerSheetBitmap, tiles_x, tiles_y);

        runPlayerS[0] = playerSheet.getSprite(1, 1);
        runPlayerS[1] = playerSheet.getSprite(2, 1);
        runPlayerS[2] = playerSheet.getSprite(3, 1);

        runPlayerN[0] = playerSheet.getSprite(1, 2);
        runPlayerN[1] = playerSheet.getSprite(2, 2);
        runPlayerN[2] = playerSheet.getSprite(3, 2);

        runPlayerE[0] = playerSheet.getSprite(1, 3);
        runPlayerE[1] = playerSheet.getSprite(2, 3);
        runPlayerE[2] = playerSheet.getSprite(3, 3);

        runPlayerW[0] = playerSheet.getSprite(1, 4);
        runPlayerW[1] = playerSheet.getSprite(2, 4);
        runPlayerW[2] = playerSheet.getSprite(3, 4);
    }

    public synchronized static Textures getInstance(Context context, float meshScale)
    {
        if (instance == null) instance = new Textures(context, meshScale);
        return instance;
    }
}