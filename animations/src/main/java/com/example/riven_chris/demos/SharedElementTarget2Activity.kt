package com.example.riven_chris.demos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.transition.Slide
import android.transition.TransitionInflater
import android.view.Gravity
import androidx.appcompat.widget.Toolbar
import com.example.riven_chris.R
import kotlinx.android.synthetic.main.activity_shared_element_target2.*

class SharedElementTarget2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_element_target2)

        window.enterTransition = Slide(Gravity.END).apply {
            excludeTarget(R.id.app_bar, true)
            excludeTarget(android.R.id.statusBarBackground, true)
        }
        window.exitTransition = Slide(Gravity.START)

        window.sharedElementEnterTransition = TransitionInflater.from(this)
                .inflateTransition(R.transition.image_transform)
        window.sharedElementReturnTransition = TransitionInflater.from(this)
                .inflateTransition(R.transition.image_transform)

//        postponeEnterTransition()
//        Handler().postDelayed({ startPostponedEnterTransition() }, 2000L)

        setSupportActionBar(toolbar)
    }
}
