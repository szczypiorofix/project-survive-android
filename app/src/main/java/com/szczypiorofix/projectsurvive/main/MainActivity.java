package com.szczypiorofix.projectsurvive.main;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;


public class MainActivity extends Activity {


    private GameManager gameManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        System.out.println("ACTIVITY: CREATE");

        DisplayMetrics display = this.getResources().getDisplayMetrics();

        int screenWidth = display.widthPixels;
        int screenHeight = display.heightPixels;

        System.out.println("Screen size " +screenWidth +":" +screenHeight);

        gameManager = new GameManager(this, screenWidth, screenHeight, screenHeight / 10);
        setContentView(gameManager);
    }


    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("ACTIVITY: START");
    }

    @Override
    protected void onResume() {
        gameManager.resume();
        super.onResume();
        System.out.println("ACTIVITY: RESUME");
    }

    @Override
    protected void onPause() {
        gameManager.pause();
        super.onPause();
        System.out.println("ACTIVITY: PAUSE");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("ACTIVITY: STOP");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("ACTIVITY: DESTROY");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.out.println("BACK KEY PRESSED");
    }
}