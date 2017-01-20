package com.szczypiorofix.projectsurvive.main;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;

import com.szczypiorofix.projectsurvive.graphics.SteeringButton;
import com.szczypiorofix.projectsurvive.graphics.Textures;


class InputController {

    private Context context;
    private float meshScale;
    private SteeringButton buttonW, buttonE, buttonN, buttonS,
                           buttonNW, buttonNE, buttonSW, buttonSE ,buttonCenter;

    private float stick_x, stick_y;
    private int DEFAULT_STICK_X, DEFAULT_STICK_Y;
    private Rect touch;




    InputController(int screenHeight, float meshScale, Context context) {

        this.context = context;
        this.meshScale = meshScale;

        touch = new Rect(0, 0, 10, 10);

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

        touch.left = x-10;
        touch.top = y-10;
        touch.right = x+10;
        touch.bottom = y+10;

        switch (event.getAction() & MotionEvent.ACTION_MASK) {

            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:

                if (Rect.intersects(touch, buttonE.getBounds()))
                {
                    buttonE.setPressed(true);
                    stick_x = x - (Textures.getInstance(context, meshScale).stickImage.getWidth()/2);
                    stick_y = y - (Textures.getInstance(context, meshScale).stickImage.getHeight()/2);
                    break;
                }
                if (Rect.intersects(touch, buttonW.getBounds()))
                {
                    buttonW.setPressed(true);
                    stick_x = x - (Textures.getInstance(context, meshScale).stickImage.getWidth()/2);
                    stick_y = y - (Textures.getInstance(context, meshScale).stickImage.getHeight()/2);
                }

                if (Rect.intersects(touch, buttonN.getBounds()))
                {
                    buttonN.setPressed(true);
                    stick_x = x - (Textures.getInstance(context, meshScale).stickImage.getWidth()/2);
                    stick_y = y - (Textures.getInstance(context, meshScale).stickImage.getHeight()/2);
                }
                if (Rect.intersects(touch, buttonS.getBounds()))
                {
                    buttonS.setPressed(true);
                    stick_x = x - (Textures.getInstance(context, meshScale).stickImage.getWidth()/2);
                    stick_y = y - (Textures.getInstance(context, meshScale).stickImage.getHeight()/2);
                }
                if (Rect.intersects(touch, buttonNE.getBounds()))
                {
                    buttonNE.setPressed(true);
                    stick_x = x - (Textures.getInstance(context, meshScale).stickImage.getWidth()/2);
                    stick_y = y - (Textures.getInstance(context, meshScale).stickImage.getHeight()/2);
                    break;
                }
                if (Rect.intersects(touch, buttonNW.getBounds()))
                {
                    buttonNW.setPressed(true);
                    stick_x = x - (Textures.getInstance(context, meshScale).stickImage.getWidth()/2);
                    stick_y = y - (Textures.getInstance(context, meshScale).stickImage.getHeight()/2);
                    break;
                }
                if (Rect.intersects(touch, buttonSE.getBounds()))
                {
                    buttonSE.setPressed(true);
                    stick_x = x - (Textures.getInstance(context, meshScale).stickImage.getWidth()/2);
                    stick_y = y - (Textures.getInstance(context, meshScale).stickImage.getHeight()/2);
                    break;
                }
                if (Rect.intersects(touch, buttonSW.getBounds()))
                {
                    buttonSW.setPressed(true);
                    stick_x = x - (Textures.getInstance(context, meshScale).stickImage.getWidth()/2);
                    stick_y = y - (Textures.getInstance(context, meshScale).stickImage.getHeight()/2);
                    break;
                }
                break;

            case MotionEvent.ACTION_MOVE:
                if (Rect.intersects(touch, buttonE.getBounds()))
                {
                    buttonE.setPressed(true);
                    stick_x = x - (Textures.getInstance(context, meshScale).stickImage.getWidth()/2);
                    stick_y = y - (Textures.getInstance(context, meshScale).stickImage.getHeight()/2);
                }
                else buttonE.setPressed(false);
                if (Rect.intersects(touch, buttonW.getBounds()))
                {
                    buttonW.setPressed(true);
                    stick_x = x - (Textures.getInstance(context, meshScale).stickImage.getWidth()/2);
                    stick_y = y - (Textures.getInstance(context, meshScale).stickImage.getHeight()/2);
                }
                else buttonW.setPressed(false);
                if (Rect.intersects(touch, buttonN.getBounds()))
                {
                    buttonN.setPressed(true);
                    stick_x = x - (Textures.getInstance(context, meshScale).stickImage.getWidth()/2);
                    stick_y = y - (Textures.getInstance(context, meshScale).stickImage.getHeight()/2);
                }
                else buttonN.setPressed(false);
                if (Rect.intersects(touch, buttonS.getBounds()))
                {
                    buttonS.setPressed(true);
                    stick_x = x - (Textures.getInstance(context, meshScale).stickImage.getWidth()/2);
                    stick_y = y - (Textures.getInstance(context, meshScale).stickImage.getHeight()/2);
                }
                else buttonS.setPressed(false);

                if (Rect.intersects(touch, buttonNE.getBounds()))
                {
                    buttonNE.setPressed(true);
                    stick_x = x - (Textures.getInstance(context, meshScale).stickImage.getWidth()/2);
                    stick_y = y - (Textures.getInstance(context, meshScale).stickImage.getHeight()/2);
                }
                else buttonNE.setPressed(false);
                if (Rect.intersects(touch, buttonNW.getBounds()))
                {
                    buttonNW.setPressed(true);
                    stick_x = x - (Textures.getInstance(context, meshScale).stickImage.getWidth()/2);
                    stick_y = y - (Textures.getInstance(context, meshScale).stickImage.getHeight()/2);
                }
                else buttonNW.setPressed(false);
                if (Rect.intersects(touch, buttonSE.getBounds()))
                {
                    buttonSE.setPressed(true);
                    stick_x = x - (Textures.getInstance(context, meshScale).stickImage.getWidth()/2);
                    stick_y = y - (Textures.getInstance(context, meshScale).stickImage.getHeight()/2);
                }
                else buttonSE.setPressed(false);
                if (Rect.intersects(touch, buttonSW.getBounds()))
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