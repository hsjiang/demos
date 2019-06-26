package com.example.riven_chris.demos

import android.animation.*
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewPropertyAnimator
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.riven_chris.R
import kotlinx.android.synthetic.main.activity_layout_animations.*

const val TAG = "property_animation"

class DefaultLayoutTransitionsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout_animations)

        setupView()
    }

    private fun setupView() {
        llContent.layoutTransition = LayoutTransition()
//        val animator = valueAnimator()
//        llContent.layoutTransition.setAnimator(LayoutTransition.APPEARING, animator)

        btnAddView.setOnClickListener {
            val view = View(this)
            view.setOnClickListener {
                view.visibility = View.INVISIBLE
            }
            view.setBackgroundColor(Color.BLUE)
            llContent.addView(view)
            view.layoutParams.height = 300
            view.layoutParams.width = 300
            (view.layoutParams as LinearLayout.LayoutParams).bottomMargin = 30
        }

        btnRemoveView.setOnClickListener {
            val count = llContent.childCount
            if (count > 0) {
                llContent.removeView(llContent.getChildAt(0))
            }
        }

        showOrHide.setOnClickListener {
            val count = llContent.childCount
            if (count > 0) {
                llContent.getChildAt(0).let {
                    it.visibility = if (it.visibility == View.GONE) View.VISIBLE else View.GONE
                }
            }
        }
    }

    fun valueAnimator(): Animator {
//        return ValueAnimator.ofObject(TypeEvaluator<Float> { fraction, startValue, endValue ->
//            Log.d(TAG, "fraction:$fraction")
//            startValue + (fraction * (endValue - startValue))
//        }, 0f, 1.5f).apply {
//            duration = 10000
//            addUpdateListener {
//                Log.d(TAG, "onAnimationUpdate:${it.animatedValue}")
//            }
//            start()
//        }

        val valuesHolderX = PropertyValuesHolder.ofFloat("scaleX", 0.1f, 1.0f)
        val valuesHolderY = PropertyValuesHolder.ofFloat("scaleY", 0.1f, 1.0f)
        val animator = ObjectAnimator.ofPropertyValuesHolder(valuesHolderX, valuesHolderY)
        animator.duration = 10000
        return animator
    }
}
