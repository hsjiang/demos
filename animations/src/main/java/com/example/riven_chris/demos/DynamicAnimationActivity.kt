package com.example.riven_chris.demos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.FlingAnimation
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import com.example.riven_chris.R
import kotlinx.android.synthetic.main.activity_dynamic_animation.*
import kotlin.math.abs

class DynamicAnimationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dynamic_animation)

        iv.setOnClickListener {
            val animationX = SpringAnimation(iv, SpringAnimation.SCALE_X, 1f)
                    .apply {
                        spring.stiffness = SpringForce.STIFFNESS_HIGH
                        spring.dampingRatio = SpringForce.DAMPING_RATIO_HIGH_BOUNCY
                        setStartValue(0.6f)
                    }
            val animationY = SpringAnimation(iv, SpringAnimation.SCALE_Y, 1f)
                    .apply {
                        spring.stiffness = SpringForce.STIFFNESS_LOW
                        spring.dampingRatio = SpringForce.DAMPING_RATIO_HIGH_BOUNCY
                        setStartValue(0.6f)
                    }

            animationX.start()
//            animationX.animateToFinalPosition(1f)
            animationY.start()
        }


        val gestureListener = object : GestureDetector.SimpleOnGestureListener() {
            override fun onDown(e: MotionEvent?): Boolean {
                return true
            }

            override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
                if (abs(velocityX) > abs(velocityY)) {
                    FlingAnimation(iv2, DynamicAnimation.TRANSLATION_X).apply {
                        setStartVelocity(velocityX)
                        setMinValue(-500f)
                        setMaxValue(500f)
                        friction = 1.1f
                        start()
                    }
                } else {
                    FlingAnimation(iv2, DynamicAnimation.TRANSLATION_Y).apply {
                        setStartVelocity(velocityY)
                        setMinValue(-500f)
                        setMaxValue(500f)
                        friction = 1.1f
                        start()
                    }
                }
                return true
            }
        }
        val gestureDetector = GestureDetector(this, gestureListener)

        iv2.setOnTouchListener { v, event ->
            gestureDetector.onTouchEvent(event)
        }
    }
}
