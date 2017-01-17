package com.szczypiorofix.projectsurvive.main;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.MotionEvent;

import com.szczypiorofix.projectsurvive.graphics.SteeringButton;
import com.szczypiorofix.projectsurvive.graphics.Textures;
import com.szczypiorofix.projectsurvive.objects.Player;

class InputController {

    private SteeringButton buttonLeft, buttonRight, buttonUp, buttonDown;


    // https://github.com/acaudwell/Gource/wiki/Controls

    // http://gource.io/#news    --   Grafiki




    InputController(int screenHeight, float meshScale, Context context) {

        Bitmap bitmapDefault = Bitmap.createScaledBitmap(Textures.getInstance(context, meshScale).buttonLeftDefaultImage, (int) meshScale, (int) (meshScale), false);
        Bitmap bitmapPressed = Bitmap.createScaledBitmap(Textures.getInstance(context, meshScale).buttonLeftPressedImage, (int) meshScale, (int) (meshScale), false);

        buttonLeft =
                new SteeringButton(bitmapDefault, bitmapPressed, 1, screenHeight - ((int) meshScale));

        bitmapDefault = Bitmap.createScaledBitmap(Textures.getInstance(context, meshScale).buttonRightDefaultImage, (int) meshScale, (int) (meshScale), false);
        bitmapPressed = Bitmap.createScaledBitmap(Textures.getInstance(context, meshScale).buttonRightPressedImage, (int) meshScale, (int) (meshScale), false);

        buttonRight =
                new SteeringButton(bitmapDefault, bitmapPressed, (int) meshScale *2, screenHeight - ((int) meshScale));

        bitmapDefault = Bitmap.createScaledBitmap(Textures.getInstance(context, meshScale).buttonUpDefaultImage, (int) meshScale, (int) (meshScale), false);
        bitmapPressed = Bitmap.createScaledBitmap(Textures.getInstance(context, meshScale).buttonUpPressedImage, (int) meshScale, (int) (meshScale), false);
        buttonUp =
                new SteeringButton(bitmapDefault, bitmapPressed, (int) meshScale, screenHeight - ((int) meshScale *2));

        bitmapDefault = Bitmap.createScaledBitmap(Textures.getInstance(context, meshScale).buttonDownDefaultImage, (int) meshScale, (int) (meshScale), false);
        bitmapPressed = Bitmap.createScaledBitmap(Textures.getInstance(context, meshScale).buttonDownPressedImage, (int) meshScale, (int) (meshScale), false);
        buttonDown =
                new SteeringButton(bitmapDefault, bitmapPressed, (int) meshScale, screenHeight - ((int) meshScale));
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

            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:

                if (x > buttonRight.getX() && x < buttonRight.getX() +buttonRight.getWidth()
                        && y > buttonRight.getY() && y < buttonRight.getY() + buttonRight.getHeight())
                {
                    buttonRight.setPressed(true);
                    //buttonLeft.setPressed(false);
                    //buttonRight.setPressed(false);
                    //buttonUp.setPressed(false);
                    //buttonDown.setPressed(false);
                    break;
                }

                if (x > buttonLeft.getX() && x < buttonLeft.getX() +buttonLeft.getWidth()
                        && y > buttonLeft.getY() && y < buttonLeft.getY() + buttonLeft.getHeight())
                {
                    buttonLeft.setPressed(true);
                }

                if (x > buttonUp.getX() && x < buttonUp.getX() +buttonUp.getWidth()
                        && y > buttonUp.getY() && y < buttonUp.getY() + buttonUp.getHeight())
                {
                    buttonUp.setPressed(true);
                }

                if (x > buttonDown.getX() && x < buttonDown.getX() +buttonDown.getWidth()
                        && y > buttonDown.getY() && y < buttonDown.getY() + buttonDown.getHeight())
                {
                    buttonDown.setPressed(true);
                }
                break;

            case MotionEvent.ACTION_MOVE:

                    if (x > buttonRight.getX() && x < buttonRight.getX() +buttonRight.getWidth()
                            && y > buttonRight.getY() && y < buttonRight.getY() + buttonRight.getHeight())
                    {
                        buttonRight.setPressed(true);
                    }
                    else buttonRight.setPressed(false);
                    if (x > buttonLeft.getX() && x < buttonLeft.getX() +buttonLeft.getWidth()
                            && y > buttonLeft.getY() && y < buttonLeft.getY() + buttonLeft.getHeight())
                    {
                        buttonLeft.setPressed(true);
                    }
                    else buttonLeft.setPressed(false);
                    if (x > buttonUp.getX() && x < buttonUp.getX() +buttonUp.getWidth()
                            && y > buttonUp.getY() && y < buttonUp.getY() + buttonUp.getHeight())
                    {
                        buttonUp.setPressed(true);
                    }
                    else buttonUp.setPressed(false);
                    if (x > buttonDown.getX() && x < buttonDown.getX() +buttonDown.getWidth()
                            && y > buttonDown.getY() && y < buttonDown.getY() + buttonDown.getHeight())
                    {
                        buttonDown.setPressed(true);
                    }
                    else buttonDown.setPressed(false);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:

                buttonUp.setPressed(false);
                buttonDown.setPressed(false);
                buttonLeft.setPressed(false);
                buttonRight.setPressed(false);

                break;

            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_OUTSIDE:
                buttonUp.setPressed(false);
                buttonDown.setPressed(false);
                buttonLeft.setPressed(false);
                buttonRight.setPressed(false);
                break;

        }
    }

    public SteeringButton getButtonLeft() {
        return buttonLeft;
    }

    public void setButtonLeft(SteeringButton buttonLeft) {
        this.buttonLeft = buttonLeft;
    }

    public SteeringButton getButtonRight() {
        return buttonRight;
    }

    public void setButtonRight(SteeringButton buttonRight) {
        this.buttonRight = buttonRight;
    }

    public SteeringButton getButtonUp() {
        return buttonUp;
    }

    public void setButtonUp(SteeringButton buttonUp) {
        this.buttonUp = buttonUp;
    }

    public SteeringButton getButtonDown() {
        return buttonDown;
    }

    public void setButtonDown(SteeringButton buttonDown) {
        this.buttonDown = buttonDown;
    }
}