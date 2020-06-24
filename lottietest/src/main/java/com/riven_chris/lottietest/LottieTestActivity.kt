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
//            lottieView.setAnimation("lottie2.json")
            lottieView.setAnimationFromUrl("https://weidu-file.putaocdn.com/file/c34ba5fe312aa16497dabe93cd55e0174d9d7d62.json")
            lottieView.loop(true)
            lottieView.playAnimation()
        }

        showMemorySize()
    }

    private fun showMemorySize() {
        val memorySize = Runtime.getRuntime().maxMemory() / 1024 / 1024
        tvLoad.text = memorySize.toString()
        Log.d("memorySize", "memorySize: $memorySize")
    }
}
