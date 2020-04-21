package com.riven_chris.lottietest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_lottie_test.*

class LottieTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lottie_test)

//        lottieView.scaleType = ImageView.ScaleType.CENTER_CROP
//        lottieView.setMaxFrame(1)
//        lottieView.setMinFrame(0)
//        lottieView.setMaxProgress(0.1f)
        tvLoad.setOnClickListener {
            lottieView.scale = 0.2f
            lottieView.setAnimation("lottie2.json")
        }

        val memorySize = Runtime.getRuntime().maxMemory() / 1024 / 1024
        tvLoad.text = memorySize.toString()
        Log.d("memorySize", "memorySize: $memorySize")
    }
}
