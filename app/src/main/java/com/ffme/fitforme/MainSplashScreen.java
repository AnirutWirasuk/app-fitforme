package com.ffme.fitforme;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import com.ffme.fitforme.kbv.KenBurnsView;



public class MainSplashScreen extends AppCompatActivity {
    int SPLASH_TIME_OUT = 3000;

    private KenBurnsView mKenBurns;
    private TextView mLogo;
    private TextView welcomeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE); //Removing ActionBar

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(MainSplashScreen.this, ValidationLogin.class);

                startActivity(i);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);

        setContentView(R.layout.activity_splash_screen);

        mKenBurns = findViewById(R.id.ken_burns_images);
        mLogo =  findViewById(R.id.logo);
        welcomeText = findViewById(R.id.welcome_text);
        setAnimation();


    }

    /**
     * Animation depends on category.
     */
    private void setAnimation() {

        mKenBurns.setImageResource(R.drawable.splash2_bcg);
        animation2();
        animation3();

    }



    private void animation2() {
        mLogo.setAlpha(1.0F);
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.translate_top_to_center);
        mLogo.startAnimation(anim);
    }

    private void animation3() {
        ObjectAnimator alphaAnimation = ObjectAnimator.ofFloat(welcomeText, "alpha", 0.0F, 1.0F);
        alphaAnimation.setStartDelay(1700);
        alphaAnimation.setDuration(500);
        alphaAnimation.start();
    }


}
