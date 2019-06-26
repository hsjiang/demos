package com.example.riven_chris.demos

import android.animation.AnimatorInflater
import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.riven_chris.R
import kotlinx.android.synthetic.main.activity_state_list_animator.*

class StateListAnimatorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_state_list_animator)
        setupView()
    }

    private fun setupView() {
        val animator = AnimatorInflater.loadStateListAnimator(this,
                R.drawable.state_scale_animator)
        clContent.stateListAnimator = animator
    }
}
