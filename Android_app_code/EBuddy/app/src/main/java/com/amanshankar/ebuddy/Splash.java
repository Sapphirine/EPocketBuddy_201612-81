package com.amanshankar.ebuddy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 * Created by Dell on 10-12-2016.
 */
public class Splash extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        final ImageView iv = (ImageView) findViewById(R.id.imageView);
        final Animation an = AnimationUtils.loadAnimation(getBaseContext(), R.anim.rotate);

        iv.startAnimation(an);

        an.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                finish();
               // try{sleep(1000);}
                //catch (InterruptedException ex){
                //    ex.printStackTrace();
                //}
                Thread t = new Thread();
                try {
                    t.sleep(1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                Intent i = new Intent(getApplicationContext(), login.class);
                startActivity(i);

            }



            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }
}
