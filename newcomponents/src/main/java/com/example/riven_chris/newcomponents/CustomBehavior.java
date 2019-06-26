package com.example.riven_chris.newcomponents;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

/**
 * Created by riven_chris on 2016/1/29.
 */
public class CustomBehavior extends CoordinatorLayout.Behavior<ImageView> {

    public CustomBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, ImageView child, View dependency) {
        return true;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, ImageView child, View dependency) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(child, "alpha", 1f, 0.2f);
        animator.setInterpolator(new LinearInterpolator());
        animator.start();
        return true;
    }
}
