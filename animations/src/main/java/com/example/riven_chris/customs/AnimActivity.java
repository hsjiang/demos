package com.example.riven_chris.customs;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.riven_chris.R;

public class AnimActivity extends AppCompatActivity {

    ScaleView scaleView;

    private ImageView foreView;
    private ImageView backView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim);
        scaleView = (ScaleView) findViewById(R.id.sv);
    }

    @Override
    protected void onResume() {
        super.onResume();

        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        final float maxRadius = (float) Math.sqrt(width * width + height * height);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                ObjectAnimator animatorX = ObjectAnimator.ofFloat(scaleView, "radius", 0.0f, maxRadius);
//                animatorX.setDuration(500);
//                animatorX.setInterpolator(new DecelerateInterpolator());
//                animatorX.start();
//                ObjectAnimator animatorY = ObjectAnimator.ofFloat(scaleView, "ScaleY", 1.0f, 2.0f);
//                animatorY.setDuration(500);
//                animatorY.start();
//                AnimatorSet set = new AnimatorSet();
//                set.setDuration(1500);
//                set.play(animatorX);
//                set.start();

//                ScaleAnimation animation = new ScaleAnimation(10, 1200, 10, 1200);
//                scaleView.setAnimation(animation);
//                animation.start();
//            }
//        }, 1000);

        foreView = (ImageView) findViewById(R.id.iv_fore);
        backView = (ImageView) findViewById(R.id.iv_back);
    }

    public void toggle(View view) {
        int distance = 16000;
        float scale = getResources().getDisplayMetrics().density * distance;
        foreView.setCameraDistance(scale);
        backView.setCameraDistance(scale);

        final boolean flipToBack = view.getId() == R.id.iv_fore;

        AnimatorSet leftOutSet = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.animator_card_left_out);
        AnimatorSet rightInSet = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.animator_card_right_in);
        int pivotX = getDeviceWidth() / 2;
        foreView.setPivotX(pivotX - foreView.getWidth() / 2);
        backView.setPivotX(pivotX - backView.getWidth() / 2);
        leftOutSet.addListener(new SimpleAnimationListener() {

            @Override
            public void onAnimationEnd(Animator animation) {
                if (flipToBack) {
                    foreView.setVisibility(View.GONE);
                } else {
                    backView.setVisibility(View.GONE);
                }
            }
        });
        rightInSet.addListener(new SimpleAnimationListener() {

            @Override
            public void onAnimationStart(Animator animation) {
                if (flipToBack) {
                    backView.setVisibility(View.VISIBLE);
                } else {
                    foreView.setVisibility(View.VISIBLE);
                }
            }
        });

        if (!flipToBack) {
            rightInSet.setTarget(foreView);
            leftOutSet.setTarget(backView);
        } else {
            rightInSet.setTarget(backView);
            leftOutSet.setTarget(foreView);
        }
        rightInSet.start();
        leftOutSet.start();
    }

    public int getDeviceWidth() {
        WindowManager manager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        return manager.getDefaultDisplay().getWidth();
    }

    public static abstract class SimpleAnimationListener implements Animator.AnimatorListener {
        @Override
        public void onAnimationStart(Animator animation) {

        }

        @Override
        public void onAnimationEnd(Animator animation) {

        }

        @Override
        public void onAnimationCancel(Animator animation) {

        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }
    }
}
