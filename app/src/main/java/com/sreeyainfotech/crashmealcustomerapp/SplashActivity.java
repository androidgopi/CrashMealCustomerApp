package com.sreeyainfotech.crashmealcustomerapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.sreeyainfotech.crashmealcustomerapp.customeclass.GifImageView;

public class SplashActivity extends AppCompatActivity {

    private Thread mSplashThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        GifImageView gifImageView = (GifImageView) findViewById(R.id.splash_GifImageView);
        gifImageView.setGifImageResource(R.drawable.crashmeal_logo);

        mSplashThread = new Thread() {
            @Override
            public void run() {
                try {
                    synchronized (this) {
                        wait(2000);
                        goToNextScreen();
                    }
                } catch (InterruptedException ex) {
                }
            }
        };

        mSplashThread.start();
    }

    private void goToNextScreen() {
       /* String loginData = Utilities.loadPref(SplashActivity.this, "UserID", "");
        if (loginData != null && !loginData.equals("")) {*/
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
       /* } else {
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }*/
        finish();

    }
}
