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
    private TileMap level1;


    public GameManager(Context context) {
        super(context);
    }

    public GameManager(Context context, int width, int height, float meshScale) {
        super(context);

        holder = getHolder();
        holder.addCallback(this);
        holder.setType(SurfaceHolder.SURFACE_TYPE_GPU);

        camera = new Camera(0, 0, width, height, meshScale);

        level1 = new TileMap(context, 1);

        System.out.println("Level "+level1.getLevel()+" loaded successfuly.");
        System.out.println("TileMap size "+level1.getTileMapWidth() +":" +level1.getTileMapHeight());
        System.out.println("Tile size: " +level1.getTileWidth() +":" +level1.getTileHeight());

        objectsManager = new ObjectsManager(context, meshScale);

        objectsManager.setLevelToManage(level1);

        player = objectsManager.getPlayer();
        player.setCurrentLevel(level1);

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

        if (inputController.getButtonN().isPressed()) player.setMoveN(true);
        if (inputController.getButtonS().isPressed()) player.setMoveS(true);
        if (inputController.getButtonW().isPressed()) player.setMoveW(true);
        if (inputController.getButtonE().isPressed()) player.setMoveE(true);
        if (inputController.getButtonNE().isPressed()) player.setMoveNE(true);
        if (inputController.getButtonNW().isPressed()) player.setMoveNW(true);
        if (inputController.getButtonSW().isPressed()) player.setMoveSW(true);
        if (inputController.getButtonSE().isPressed()) player.setMoveSE(true);

        objectsManager.tick();
        camera.tick(player);
    }

    void render(Canvas canvas) {
        canvas.drawColor(Color.BLACK);

        canvas.translate(camera.getX(), camera.getY());
        objectsManager.render(canvas);
        canvas.translate(-camera.getX(), -camera.getY());

        inputController.drawButtons(canvas);
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
        double ns;
        long now;

        while(isRunning)
        {

            ns = 1000000000 / amountOfTicks;
            now = System.nanoTime();
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