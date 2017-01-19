package com.szczypiorofix.projectsurvive.main;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.MotionEvent;

import com.szczypiorofix.projectsurvive.graphics.SteeringButton;
import com.szczypiorofix.projectsurvive.graphics.Textures;

import org.w3c.dom.Text;

class InputController {

    private Context context;
    private float meshScale;
    private SteeringButton buttonW, buttonE, buttonN, buttonS,
                           buttonNW, buttonNE, buttonSW, buttonSE ,buttonCenter;

    private float stick_x, stick_y;
    private boolean stick_move;
    private int DEFAULT_STICK_X, DEFAULT_STICK_Y;


    InputController(int screenHeight, float meshScale, Context context) {

        this.context = context;
        this.meshScale = meshScale;

        Bitmap bitmapDefault = Bitmap.createScaledBitmap(Textures.getInstance(context, meshScale).buttonNWImage, (int) meshScale/2, (int) (meshScale/2), false);
        buttonNW = new SteeringButton(bitmapDefault, (int) meshScale, screenHeight - ((int) (meshScale*1.5)));

        bitmapDefault = Bitmap.createScaledBitmap(Textures.getInstance(context, meshScale).buttonNImage, (int) meshScale/2, (int) (meshScale/2), false);
        buttonN = new SteeringButton(bitmapDefault, (int) (meshScale*1.5), screenHeight - ((int) (meshScale*1.5)));

        bitmapDefault = Bitmap.createScaledBitmap(Textures.getInstance(context, meshScale).buttonNEImage, (int) meshScale/2, (int) (meshScale/2), false);
        buttonNE = new SteeringButton(bitmapDefault, (int) (meshScale*2), screenHeight - ((int) (meshScale*1.5)));

        bitmapDefault = Bitmap.createScaledBitmap(Textures.getInstance(context, meshScale).buttonEImage, (int) meshScale/2, (int) (meshScale/2), false);
        buttonE = new SteeringButton(bitmapDefault, (int) (meshScale*2), screenHeight - ((int) meshScale));

        bitmapDefault = Bitmap.createScaledBitmap(Textures.getInstance(context, meshScale).buttonSEImage, (int) meshScale/2, (int) (meshScale/2), false);
        buttonSE = new SteeringButton(bitmapDefault, (int) (meshScale*2), screenHeight - ((int) (meshScale/2)));

        bitmapDefault = Bitmap.createScaledBitmap(Textures.getInstance(context, meshScale).buttonSImage, (int) meshScale/2, (int) (meshScale/2), false);
        buttonS = new SteeringButton(bitmapDefault, (int) (meshScale*1.5), screenHeight - ((int) (meshScale/2)));

        bitmapDefault = Bitmap.createScaledBitmap(Textures.getInstance(context, meshScale).buttonSWImage, (int) meshScale/2, (int) (meshScale/2), false);
        buttonSW = new SteeringButton(bitmapDefault, (int) (meshScale), screenHeight - ((int) (meshScale/2)));

        bitmapDefault = Bitmap.createScaledBitmap(Textures.getInstance(context, meshScale).buttonWImage, (int) meshScale/2, (int) (meshScale/2), false);
        buttonW = new SteeringButton(bitmapDefault, (int) (meshScale), screenHeight - ((int) (meshScale)));

        bitmapDefault = Bitmap.createScaledBitmap(Textures.getInstance(context, meshScale).buttonCenterImage, (int) meshScale/2, (int) (meshScale/2), false);
        buttonCenter = new SteeringButton(bitmapDefault, (int) (meshScale*1.5), screenHeight - ((int) (meshScale)));

        DEFAULT_STICK_X = (int) (meshScale*1.25);
        DEFAULT_STICK_Y = (int) (screenHeight - (meshScale*1.25));

        stick_x = DEFAULT_STICK_X;
        stick_y = DEFAULT_STICK_Y;
    }

    void drawButtons(Canvas canvas) {
        buttonW.draw(canvas);
        buttonE.draw(canvas);
        buttonN.draw(canvas);
        buttonS.draw(canvas);
        buttonNW.draw(canvas);
        buttonNE.draw(canvas);
        buttonSW.draw(canvas);
        buttonSE.draw(canvas);
        buttonCenter.draw(canvas);
        canvas.drawBitmap(Textures.getInstance(context, meshScale).stickImage, stick_x, stick_y, null);
    }

    void handleInputs(MotionEvent event) {

        // https://developer.android.com/training/gestures/multi.html

        int i = event.getActionIndex();

        int x = (int) event.getX(i);
        int y = (int) event.getY(i);

        switch (event.getAction() & MotionEvent.ACTION_MASK) {

            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:

                if (x > buttonE.getX() && x < buttonE.getX() + buttonE.getWidth()
                        && y > buttonE.getY() && y < buttonE.getY() + buttonE.getHeight())
                {
                    buttonE.setPressed(true);
                    stick_x = x - (Textures.getInstance(context, meshScale).stickImage.getWidth()/2);
                    stick_y = y - (Textures.getInstance(context, meshScale).stickImage.getHeight()/2);
                    break;
                }

                if (x > buttonNE.getX() && x < buttonNE.getX() + buttonNE.getWidth()
                        && y > buttonNE.getY() && y < buttonNE.getY() + buttonNE.getHeight())
                {
                    buttonNE.setPressed(true);
                    stick_x = x - (Textures.getInstance(context, meshScale).stickImage.getWidth()/2);
                    stick_y = y - (Textures.getInstance(context, meshScale).stickImage.getHeight()/2);
                    break;
                }

                if (x > buttonNW.getX() && x < buttonNW.getX() + buttonNW.getWidth()
                        && y > buttonNW.getY() && y < buttonNW.getY() + buttonNW.getHeight())
                {
                    buttonNW.setPressed(true);
                    stick_x = x - (Textures.getInstance(context, meshScale).stickImage.getWidth()/2);
                    stick_y = y - (Textures.getInstance(context, meshScale).stickImage.getHeight()/2);
                    break;
                }

                if (x > buttonSE.getX() && x < buttonSE.getX() + buttonSE.getWidth()
                        && y > buttonSE.getY() && y < buttonSE.getY() + buttonSE.getHeight())
                {
                    buttonSE.setPressed(true);
                    stick_x = x - (Textures.getInstance(context, meshScale).stickImage.getWidth()/2);
                    stick_y = y - (Textures.getInstance(context, meshScale).stickImage.getHeight()/2);
                    break;
                }

                if (x > buttonSW.getX() && x < buttonSW.getX() + buttonSW.getWidth()
                        && y > buttonSW.getY() && y < buttonSW.getY() + buttonSW.getHeight())
                {
                    buttonSW.setPressed(true);
                    stick_x = x - (Textures.getInstance(context, meshScale).stickImage.getWidth()/2);
                    stick_y = y - (Textures.getInstance(context, meshScale).stickImage.getHeight()/2);
                    break;
                }

                if (x > buttonW.getX() && x < buttonW.getX() + buttonW.getWidth()
                        && y > buttonW.getY() && y < buttonW.getY() + buttonW.getHeight())
                {
                    buttonW.setPressed(true);
                    stick_x = x - (Textures.getInstance(context, meshScale).stickImage.getWidth()/2);
                    stick_y = y - (Textures.getInstance(context, meshScale).stickImage.getHeight()/2);
                }

                if (x > buttonN.getX() && x < buttonN.getX() + buttonN.getWidth()
                        && y > buttonN.getY() && y < buttonN.getY() + buttonN.getHeight())
                {
                    buttonN.setPressed(true);
                    stick_x = x - (Textures.getInstance(context, meshScale).stickImage.getWidth()/2);
                    stick_y = y - (Textures.getInstance(context, meshScale).stickImage.getHeight()/2);
                }

                if (x > buttonS.getX() && x < buttonS.getX() + buttonS.getWidth()
                        && y > buttonS.getY() && y < buttonS.getY() + buttonS.getHeight())
                {
                    buttonS.setPressed(true);
                    stick_x = x - (Textures.getInstance(context, meshScale).stickImage.getWidth()/2);
                    stick_y = y - (Textures.getInstance(context, meshScale).stickImage.getHeight()/2);
                }
                break;

            case MotionEvent.ACTION_MOVE:

                    if (x > buttonE.getX() && x < buttonE.getX() + buttonE.getWidth()
                            && y > buttonE.getY() && y < buttonE.getY() + buttonE.getHeight())
                    {
                        buttonE.setPressed(true);
                        stick_x = x - (Textures.getInstance(context, meshScale).stickImage.getWidth()/2);
                        stick_y = y - (Textures.getInstance(context, meshScale).stickImage.getHeight()/2);
                    }
                    else buttonE.setPressed(false);
                    if (x > buttonW.getX() && x < buttonW.getX() + buttonW.getWidth()
                            && y > buttonW.getY() && y < buttonW.getY() + buttonW.getHeight())
                    {
                        buttonW.setPressed(true);
                        stick_x = x - (Textures.getInstance(context, meshScale).stickImage.getWidth()/2);
                        stick_y = y - (Textures.getInstance(context, meshScale).stickImage.getHeight()/2);
                    }
                    else buttonW.setPressed(false);
                    if (x > buttonN.getX() && x < buttonN.getX() + buttonN.getWidth()
                            && y > buttonN.getY() && y < buttonN.getY() + buttonN.getHeight())
                    {
                        buttonN.setPressed(true);
                        stick_x = x - (Textures.getInstance(context, meshScale).stickImage.getWidth()/2);
                        stick_y = y - (Textures.getInstance(context, meshScale).stickImage.getHeight()/2);
                    }
                    else buttonN.setPressed(false);
                    if (x > buttonS.getX() && x < buttonS.getX() + buttonS.getWidth()
                            && y > buttonS.getY() && y < buttonS.getY() + buttonS.getHeight())
                    {
                        buttonS.setPressed(true);
                        stick_x = x - (Textures.getInstance(context, meshScale).stickImage.getWidth()/2);
                        stick_y = y - (Textures.getInstance(context, meshScale).stickImage.getHeight()/2);
                    }
                    else buttonS.setPressed(false);

                if (x > buttonNE.getX() && x < buttonNE.getX() + buttonNE.getWidth()
                        && y > buttonNE.getY() && y < buttonNE.getY() + buttonNE.getHeight())
                {
                    buttonNE.setPressed(true);
                    stick_x = x - (Textures.getInstance(context, meshScale).stickImage.getWidth()/2);
                    stick_y = y - (Textures.getInstance(context, meshScale).stickImage.getHeight()/2);
                }
                else buttonNE.setPressed(false);
                if (x > buttonNW.getX() && x < buttonNW.getX() + buttonNW.getWidth()
                        && y > buttonNW.getY() && y < buttonNW.getY() + buttonNW.getHeight())
                {
                    buttonNW.setPressed(true);
                    stick_x = x - (Textures.getInstance(context, meshScale).stickImage.getWidth()/2);
                    stick_y = y - (Textures.getInstance(context, meshScale).stickImage.getHeight()/2);
                }
                else buttonNW.setPressed(false);
                if (x > buttonSE.getX() && x < buttonSE.getX() + buttonSE.getWidth()
                        && y > buttonSE.getY() && y < buttonSE.getY() + buttonSE.getHeight())
                {
                    buttonSE.setPressed(true);
                    stick_x = x - (Textures.getInstance(context, meshScale).stickImage.getWidth()/2);
                    stick_y = y - (Textures.getInstance(context, meshScale).stickImage.getHeight()/2);
                }
                else buttonSE.setPressed(false);
                if (x > buttonSW.getX() && x < buttonSW.getX() + buttonSW.getWidth()
                        && y > buttonSW.getY() && y < buttonSW.getY() + buttonSW.getHeight())
                {
                    buttonSW.setPressed(true);
                    stick_x = x - (Textures.getInstance(context, meshScale).stickImage.getWidth()/2);
                    stick_y = y - (Textures.getInstance(context, meshScale).stickImage.getHeight()/2);
                }
                else buttonSW.setPressed(false);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:

                buttonN.setPressed(false);
                buttonS.setPressed(false);
                buttonW.setPressed(false);
                buttonE.setPressed(false);
                buttonNW.setPressed(false);
                buttonNE.setPressed(false);
                buttonSW.setPressed(false);
                buttonSE.setPressed(false);
                stick_x = DEFAULT_STICK_X;
                stick_y = DEFAULT_STICK_Y;
                stick_move = false;
                break;

            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_OUTSIDE:
                buttonN.setPressed(false);
                buttonS.setPressed(false);
                buttonW.setPressed(false);
                buttonE.setPressed(false);
                buttonNW.setPressed(false);
                buttonNE.setPressed(false);
                buttonSW.setPressed(false);
                buttonSE.setPressed(false);
                stick_x = DEFAULT_STICK_X;
                stick_y = DEFAULT_STICK_Y;
                stick_move = false;
                break;

        }
    }

    SteeringButton getButtonW() {
        return buttonW;
    }

    SteeringButton getButtonE() {
        return buttonE;
    }

    SteeringButton getButtonN() {
        return buttonN;
    }

    SteeringButton getButtonS() {
        return buttonS;
    }

    SteeringButton getButtonSE() {
        return buttonSE;
    }

    SteeringButton getButtonSW() {
        return buttonSW;
    }

    SteeringButton getButtonNE() {
        return buttonNE;
    }

    SteeringButton getButtonNW() {
        return buttonNW;
    }

}