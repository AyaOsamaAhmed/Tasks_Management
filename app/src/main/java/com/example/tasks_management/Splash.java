package com.example.tasks_management;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;

import me.anwarshahriar.calligrapher.Calligrapher;

/**
 * Created by Aya_Osama on 26-Aug-18.
 */

public class Splash extends Activity {
    private Animation an;
    private ImageView text;
    private ProgressBar progressBar ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        text = (ImageView) findViewById(R.id.loading);


         //  progressBar = (ProgressBar) findViewById(R.id.progress);

      /*  Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(4000);
                  //  Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    Intent intent = new Intent(getApplicationContext(),Login.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }}
        };
        thread.start();*/
          an = AnimationUtils.loadAnimation(getBaseContext(), R.anim.rotate);
        text.startAnimation(an);
        an.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                finish();
                startActivity(new Intent(Splash.this, Login.class));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        /*
         <ProgressBar
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:id="@+id/progress"
        style="?android:attr/progressBarStyleHorizontal"
        android:progressDrawable="@drawable/progress_bar"
         android:layout_marginBottom="40dp"
        android:layout_gravity="center"
        android:progress="20"

        android:layout_weight="0.10"
        android:minWidth="25dp"
        />

        */
    }
    public void fontType(){

        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this,"fonts/Ah-moharram-bold.ttf",true);
    }

}

