package com.example.riven_chris.demos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Slide
import android.transition.TransitionInflater
import android.view.Gravity
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import com.example.riven_chris.R
import kotlinx.android.synthetic.main.activity_shared_element_transitions2.*

class SharedElementTransitions2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_element_transitions2)
        window.enterTransition = Slide(Gravity.END).apply {
            excludeTarget(R.id.app_bar, true)
            excludeTarget(android.R.id.statusBarBackground, true)
        }
        window.exitTransition = Slide(Gravity.START)

        window.sharedElementExitTransition = TransitionInflater.from(this)
                .inflateTransition(R.transition.image_transform)
        window.sharedElementReenterTransition = TransitionInflater.from(this)
                .inflateTransition(R.transition.image_transform)

        start.setOnClickListener {
            val array = arrayOf(Pair<View, String>(iv1, "image:image2"),
                    Pair<View, String>(iv2, "image:image2"))
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, *array)
            startActivity(Intent(this, SharedElementTarget2Activity::class.java), options.toBundle())
        }

        setSupportActionBar(toolbar)
    }
}
