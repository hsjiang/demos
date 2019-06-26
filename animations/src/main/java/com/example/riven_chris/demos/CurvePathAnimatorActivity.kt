package com.example.riven_chris.demos

import android.animation.ObjectAnimator
import android.graphics.Path
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.riven_chris.R
import kotlinx.android.synthetic.main.activity_curve_path_animtor.*

class CurvePathAnimatorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_curve_path_animtor)


        val path = Path().apply {
            arcTo(0f, 0f, 1000f, 1000f, 270f, -180f, true)
        }
        val animator = ObjectAnimator.ofFloat(iv, View.X, View.Y, path).apply {
            duration = 2000
            start()
        }
    }
}
