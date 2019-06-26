package com.example.riven_chris.demos

import android.os.Bundle
import android.transition.Transition
import android.transition.TransitionInflater
import android.util.Log
import com.example.riven_chris.R
import com.example.riven_chris.util.TransitionListenerAdapter

class SharedElementTargetActivity : FullScreenBaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.sharedElementEnterTransition = TransitionInflater.from(this)
                .inflateTransition(R.transition.image_transform).also {
                    it.addListener(object : TransitionListenerAdapter() {
                        override fun onTransitionEnd(transition: Transition?) {
                            Log.d("transitionTest", "target: sharedElementEnterTransition end")
                        }

                        override fun onTransitionStart(transition: Transition?) {
                            Log.d("transitionTest", "target: sharedElementEnterTransition start")
                        }
                    })
                }
        window.sharedElementReturnTransition = TransitionInflater.from(this)
                .inflateTransition(R.transition.image_transform).also {
                    it.addListener(object : TransitionListenerAdapter() {
                        override fun onTransitionEnd(transition: Transition?) {
                            Log.d("transitionTest", "target: sharedElementReturnTransition end")
                        }

                        override fun onTransitionStart(transition: Transition?) {
                            Log.d("transitionTest", "target: sharedElementReturnTransition start")
                        }
                    })
                }

        setContentView(R.layout.activity_shared_element_target)
    }
}
