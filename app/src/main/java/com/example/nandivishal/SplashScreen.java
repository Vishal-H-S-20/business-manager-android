package com.example.nandivishal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

public class SplashScreen extends AppCompatActivity {

    private View decorview;
    private static int splash_screen=1900;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spalsh_screen);
        SharedPreferences prefs=getSharedPreferences("prefs",MODE_PRIVATE);
        final boolean firstStart= prefs.getBoolean("firstStart", true);
SharedPreferences prefs1 = getSharedPreferences("prefs", MODE_PRIVATE);
    SharedPreferences.Editor editor =prefs.edit();
    editor.putBoolean("firstStart",false);
    editor.apply();

        decorview=getWindow().getDecorView();
        decorview.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {

                if (visibility ==0)
                {
                    decorview.setSystemUiVisibility(hidestatusbar());
                }
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (firstStart) {
                    Intent intent = new Intent(SplashScreen.this, LogInOne.class);
                    startActivity(intent);
                    finish();
                }
              else {
                 activity();
              }
            }
        },splash_screen);


    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus)
        {
            decorview.setSystemUiVisibility(hidestatusbar());
        }
    }

    private int hidestatusbar()
    {
        return   View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                ;
    }

    public void activity()
    {
        Intent intent1 = new Intent(this, LogInTwo.class);
        startActivity(intent1);
        finish();
    }

}