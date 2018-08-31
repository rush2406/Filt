package org.technozion.technozion18;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.view.View;
import android.view.Window;

public class SplashActivity extends AppCompatActivity {

    private static int  SPLASH_TIME_OUT=2000;
    private static String TAG = SplashActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setupWindowTransitions();
        }
        setContentView(R.layout.activity_splash);


        // get the common element for the transition in this activity
        final View logoImageView= findViewById(R.id.logo);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Class targetClass = LoginActivity.class;
                Intent i=new Intent(SplashActivity.this, targetClass);
                startActivity(i);
                overridePendingTransition(0, 0);
                finish();
            }
        },SPLASH_TIME_OUT);



    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    void setupWindowTransitions(){
        // inside your activity (if you did not enable transitions in your theme)
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        // set an exit transition
        getWindow().setExitTransition(new Explode());
    }


}
