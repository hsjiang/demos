package com.example.riven_chris.demos

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ArgbEvaluator
import android.animation.TypeEvaluator
import android.os.Bundle
import android.view.View
import android.view.ViewAnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.riven_chris.R
import kotlinx.android.synthetic.main.activity_clipping_circle.*
import kotlinx.android.synthetic.main.content_clipping_cicle.*


class ClippingCircleActivity : AppCompatActivity() {

    var showing = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clipping_circle)
        setSupportActionBar(toolbar)
    }

    fun startClipping(view: View) {
        val cx = iv.width / 2
        val cy = iv.height / 2

        val radius = Math.hypot(cx.toDouble(), cy.toDouble()).toFloat()
        if (showing) {
            showing = false
            val anim = ViewAnimationUtils.createCircularReveal(iv, cx, cy, radius, 0f)
            anim.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    iv.visibility = View.GONE
                }
            })
            anim.start()
        } else {
            //首次无动画效果直接显示？？
            showing = true
            iv.visibility = View.VISIBLE
            val anim = ViewAnimationUtils.createCircularReveal(iv, cx, cy, 0f, radius)
            anim.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationStart(animation: Animator?) {
                }
            })
            anim.start()
        }
    }
}
