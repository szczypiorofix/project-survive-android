package com.szczypiorofix.projectsurvive.main;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.MotionEvent;

import com.szczypiorofix.projectsurvive.graphics.SteeringButton;
import com.szczypiorofix.projectsurvive.graphics.Textures;
import com.szczypiorofix.projectsurvive.objects.Player;

public class InputController {

    private Player player;
    private SteeringButton buttonLeft, buttonRight, buttonUp, buttonDown;



    InputController(int screenHeight, Player player, float meshScale, Context context) {
        this.player = player;

        Bitmap bitmapDefault = Bitmap.createScaledBitmap(Textures.getInstance(context, meshScale).buttonLeftDefaultImage, (int) meshScale*2, (int) (meshScale*2), false);
        Bitmap bitmapPressed = Bitmap.createScaledBitmap(Textures.getInstance(context, meshScale).buttonLeftPressedImage, (int) meshScale*2, (int) (meshScale*2), false);

        buttonLeft =
                new SteeringButton(bitmapDefault, bitmapPressed, (int) meshScale, screenHeight - ((int) meshScale *3));

        bitmapDefault = Bitmap.createScaledBitmap(Textures.getInstance(context, meshScale).buttonRightDefaultImage, (int) meshScale*2, (int) (meshScale*2), false);
        bitmapPressed = Bitmap.createScaledBitmap(Textures.getInstance(context, meshScale).buttonRightPressedImage, (int) meshScale*2, (int) (meshScale*2), false);

        buttonRight =
                new SteeringButton(bitmapDefault, bitmapPressed, (int) meshScale *5, screenHeight - ((int) meshScale *3));

        bitmapDefault = Bitmap.createScaledBitmap(Textures.getInstance(context, meshScale).buttonJumpDefaultImage, (int) meshScale*2, (int) (meshScale*2), false);
        bitmapPressed = Bitmap.createScaledBitmap(Textures.getInstance(context, meshScale).buttonJumpPressedImage, (int) meshScale*2, (int) (meshScale*2), false);
        buttonUp =
                new SteeringButton(bitmapDefault, bitmapPressed, (int) meshScale *3, screenHeight - ((int) meshScale *4));

        bitmapDefault = Bitmap.createScaledBitmap(Textures.getInstance(context, meshScale).buttonJumpDefaultImage, (int) meshScale*2, (int) (meshScale*2), false);
        bitmapPressed = Bitmap.createScaledBitmap(Textures.getInstance(context, meshScale).buttonJumpPressedImage, (int) meshScale*2, (int) (meshScale*2), false);
        buttonDown =
                new SteeringButton(bitmapDefault, bitmapPressed, (int) meshScale *3, screenHeight - ((int) meshScale*2));;
    }

    void drawButtons(Canvas canvas) {
        buttonLeft.draw(canvas);
        buttonRight.draw(canvas);
        buttonUp.draw(canvas);
        buttonDown.draw(canvas);
    }

    void handleInputs(MotionEvent event) {

        // https://developer.android.com/training/gestures/multi.html

        int i = event.getActionIndex();

        int x = (int) event.getX(i);
        int y = (int) event.getY(i);

        switch (event.getAction() & MotionEvent.ACTION_MASK) {

            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:

                if (x > buttonRight.getX() && x < buttonRight.getX() +buttonRight.getWidth()
                        && y > buttonRight.getY() && y < buttonRight.getY() + buttonRight.getHeight())
                {
                    player.setMoveE(true);
                    buttonRight.setPressed(true);
                }
                else
                if (x > buttonLeft.getX() && x < buttonLeft.getX() +buttonLeft.getWidth()
                        && y > buttonLeft.getY() && y < buttonLeft.getY() + buttonLeft.getHeight())
                {
                    player.setMoveW(true);
                    buttonLeft.setPressed(true);
                }
                else
                if (x > buttonUp.getX() && x < buttonUp.getX() +buttonUp.getWidth()
                        && y > buttonUp.getY() && y < buttonUp.getY() + buttonUp.getHeight())
                {
                    player.setMoveN(true);
                    buttonUp.setPressed(true);
                }
                else
                if (x > buttonDown.getX() && x < buttonDown.getX() +buttonDown.getWidth()
                        && y > buttonDown.getY() && y < buttonDown.getY() + buttonDown.getHeight())
                {
                    player.setMoveS(true);
                    buttonDown.setPressed(true);
                }
                break;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                if (x > buttonRight.getX() && x < buttonRight.getX() +buttonRight.getWidth()
                        && y > buttonRight.getY() && y < buttonRight.getY() + buttonRight.getHeight())
                {
                    player.setMoveE(false);
                    buttonRight.setPressed(false);
                }
                else
                if (x > buttonLeft.getX() && x < buttonLeft.getX() +buttonLeft.getWidth()
                        && y > buttonLeft.getY() && y < buttonLeft.getY() + buttonLeft.getHeight())
                {
                    player.setMoveW(false);
                    buttonLeft.setPressed(false);
                }
                else
                if (x > buttonUp.getX() && x < buttonUp.getX() +buttonUp.getWidth()
                        && y > buttonUp.getY() && y < buttonUp.getY() + buttonUp.getHeight())
                {
                    player.setMoveN(false);
                    buttonUp.setPressed(false);
                }
                else
                if (x > buttonDown.getX() && x < buttonDown.getX() +buttonDown.getWidth()
                        && y > buttonDown.getY() && y < buttonDown.getY() + buttonDown.getHeight())
                {
                    player.setMoveS(false);
                    buttonDown.setPressed(false);
                }
                break;
        }
        System.out.println(player.getX()+":"+player.getY());
    }
}