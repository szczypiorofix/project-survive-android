package com.szczypiorofix.projectsurvive.main;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


import com.szczypiorofix.projectsurvive.objects.Player;


public class GameManager extends SurfaceView implements Runnable, SurfaceHolder.Callback{

    private boolean isRunning = false;
    private Thread gameThread;
    private SurfaceHolder holder;
    private InputController inputController;
    private ObjectsManager objectsManager;
    private Camera camera;
    private Player player;


    public GameManager(Context context) {
        super(context);
    }

    public GameManager(Context context, int width, int height, float meshScale) {
        super(context);

        holder = getHolder();
        holder.addCallback(this);
        holder.setType(SurfaceHolder.SURFACE_TYPE_GPU);

        camera = new Camera(0, 0, width, height, meshScale);

        objectsManager = new ObjectsManager(context, meshScale);
        objectsManager.loadLevel(1);

        player = objectsManager.getPlayer();

        inputController = new InputController(height, meshScale, context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        inputController.handleInputs(event);
        return true;
    }

    void tick() {

        player.setVelX(0);
        player.setVelY(0);

        if (inputController.getButtonUp().isPressed()) player.setMoveN(true);
        if (inputController.getButtonDown().isPressed()) player.setMoveS(true);
        if (inputController.getButtonLeft().isPressed()) player.setMoveW(true);
        if (inputController.getButtonRight().isPressed()) player.setMoveE(true);

        objectsManager.tick();
        camera.tick(player);
    }

    void render(Canvas canvas) {
        canvas.drawColor(Color.BLACK);

        canvas.translate(camera.getX(), camera.getY());
        objectsManager.render(canvas);
        canvas.translate(-camera.getX(), -camera.getY());


        inputController.drawButtons(canvas);

        //paint = new Paint();
        //paint.setColor(Color.WHITE);
        //canvas.drawText("FPS: "+fps_count, 10, 20, paint);
        //canvas.drawText("UPS: "+ticks_count, 10, 40, paint);
    }


    void resume() {
        isRunning = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    void pause() {
        isRunning = false;
        boolean retry = true;
        while (retry) {
            try {
                gameThread.join();
                retry = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        final double amountOfTicks = 60.0;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        Canvas canvas;
        float fps_count, ticks_count;

        while(isRunning)
        {
/**
            if (!holder.getSurface().isValid()) {
                continue;
            }

            ticks_count = 1000 / 60;
            fps_count = 1000 / 60;

            tick();
            canvas = holder.lockCanvas();
            if (canvas != null) {

                // DRAW METHOD
                render(canvas);

                holder.unlockCanvasAndPost(canvas);
            }
            try {
                Thread.sleep(5);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
**/

            double ns = 1000000000 / amountOfTicks;
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            if (!holder.getSurface().isValid()) {
                continue;
            }

            while(delta >= 1)
            {
                // GAME UPDATE
                tick();

                updates++;
                delta--;
            }

            canvas = holder.lockCanvas();
            if (canvas != null) {

                // DRAW METHOD
                render(canvas);
                holder.unlockCanvasAndPost(canvas);
            }
            frames++;

            if (System.currentTimeMillis() - timer > 1000)
            {
                timer += 1000;
                fps_count = frames;
                ticks_count = updates;

                System.out.println("UPS: "+ticks_count +", FPS: "+fps_count);

                frames = 0;
                updates = 0;
            }
        }
    }
}