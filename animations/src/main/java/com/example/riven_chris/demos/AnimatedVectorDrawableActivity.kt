package com.example.riven_chris.demos

import android.graphics.drawable.AnimatedVectorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.riven_chris.R
import kotlinx.android.synthetic.main.activity_animated_vector_drawable.*

class AnimatedVectorDrawableActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animated_vector_drawable)

        val drawable = iv.drawable as AnimatedVectorDrawable
        drawable.start()
    }
}
