package com.szczypiorofix.projectsurvive.main;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


import com.szczypiorofix.projectsurvive.objects.Player;


public class GameManager extends SurfaceView implements Runnable, SurfaceHolder.Callback{

    private boolean isRunning = false;
    private Thread gameThread;
    private SurfaceHolder holder;
    private int screenWidth;
    private int screenHeight;
    private int width, height;
    private float fps_count, ticks_count;
    private InputController inputController;
    private ObjectsManager objectsManager;
    private Camera camera;
    private Player player;


    public GameManager(Context context) {
        super(context);
    }

    public GameManager(Context context, int width, int height, float meshScale) {
        super(context);

        this.width = width;
        this.height = height;

        holder = getHolder();
        holder.addCallback(this);
        holder.setType(SurfaceHolder.SURFACE_TYPE_GPU);

        objectsManager = new ObjectsManager(context, meshScale, camera);
        objectsManager.loadLevel(1);
        camera = new Camera(0, 0, width, height, meshScale);
        player = objectsManager.getPlayer();

        inputController = new InputController(height, player, meshScale, context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        inputController.handleInputs(event);
        return true;
    }

    void tick() {
        objectsManager.tick();
        camera.tick(player);
    }

    void render(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);

        canvas.drawText("FPS: "+fps_count, 10, 20, paint);
        canvas.drawText("UPS: "+ticks_count, 10, 40, paint);

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
        screenWidth = width;
        screenHeight = height;
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

        while(isRunning)
        {
            double ns = 1000000000 / amountOfTicks;
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            if (! holder.getSurface().isValid()) {
                continue;
            }

            while(delta >= 1)
            {
                // GAME UPDATE
                tick();

                updates++;
                delta--;
            }

            Canvas canvas = holder.lockCanvas();
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
                frames = 0;
                updates = 0;
            }
        }
    }
}