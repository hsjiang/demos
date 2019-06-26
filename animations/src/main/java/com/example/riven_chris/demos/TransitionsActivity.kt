package com.example.riven_chris.demos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Explode
import android.transition.Fade
import androidx.core.app.ActivityOptionsCompat
import com.example.riven_chris.R
import kotlinx.android.synthetic.main.activity_transitions.*

class TransitionsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transitions)
        btn_layout_transition.setOnClickListener {
            startActivity(Intent(this, LayoutTransitionsActivity::class.java),
                    ActivityOptionsCompat.makeSceneTransitionAnimation(this@TransitionsActivity).toBundle())
        }

        btn_shared_element.setOnClickListener {
            startActivity(Intent(this, SharedElementTransitionsActivity::class.java),
                    ActivityOptionsCompat.makeSceneTransitionAnimation(this@TransitionsActivity).toBundle())
        }

        btn_shared_element2.setOnClickListener {
            startActivity(Intent(this, SharedElementTransitions2Activity::class.java),
                    ActivityOptionsCompat.makeSceneTransitionAnimation(this@TransitionsActivity).toBundle())
        }
    }
}
