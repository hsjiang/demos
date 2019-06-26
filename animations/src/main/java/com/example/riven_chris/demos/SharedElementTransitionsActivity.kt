package com.example.riven_chris.demos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.*
import android.util.Log
import android.view.View
import android.view.Window
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import com.example.riven_chris.R
import com.example.riven_chris.util.TransitionListenerAdapter
import com.example.riven_chris.util.Utils
import com.example.riven_chris.util.Utils.setImmersiveMode
import kotlinx.android.synthetic.main.activity_transition_shared_element.*

class SharedElementTransitionsActivity : FullScreenBaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transition_shared_element)
        window.sharedElementExitTransition = TransitionInflater.from(this)
                .inflateTransition(R.transition.image_transform).also {
                    it.addListener(object : TransitionListenerAdapter() {
                        override fun onTransitionEnd(transition: Transition?) {
                            Log.d("transitionTest", "shared: sharedElementExitTransition end")
                        }

                        override fun onTransitionStart(transition: Transition?) {
                            Log.d("transitionTest", "shared: sharedElementExitTransition start")
                        }
                    })
                }
        window.sharedElementReenterTransition = TransitionInflater.from(this)
                .inflateTransition(R.transition.image_transform).also {
                    it.addListener(object : TransitionListenerAdapter() {
                        override fun onTransitionEnd(transition: Transition?) {
                            Log.d("transitionTest", "shared: sharedElementReenterTransition end")
                        }

                        override fun onTransitionStart(transition: Transition?) {
                            Log.d("transitionTest", "shared: sharedElementReenterTransition start")
                        }
                    })
                }
        window.enterTransition = Explode()
        window.exitTransition = Explode()


        start.setOnClickListener {
            val intent = Intent(this, SharedElementTargetActivity::class.java)
            val array = arrayOf(Pair<View, String>(findViewById(R.id.iv), "image:image1"),
                    Pair(findViewById(R.id.iv2), "image:image2"),
                    Pair(findViewById(R.id.tv1), "text:text1"))
//            val array = arrayOf(Pair<View, String>(findViewById(R.id.ll_container), "group:group"))
            val activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this, *array)
            // Now we can start the Activity, providing the activity options as a bundle
            ActivityCompat.startActivity(this, intent, activityOptions.toBundle())
        }
    }
}
