package com.example.riven_chris.demos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

open class FullScreenBaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val lp = window.attributes
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
//            lp.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
//        }
//        window.attributes = lp
    }

//    override fun onResume() {
//        super.onResume()
//        Utils.setImmersiveMode(window)
//    }
//
//    override fun onWindowFocusChanged(hasFocus: Boolean) {
//        super.onWindowFocusChanged(hasFocus)
//        Utils.setImmersiveMode(window)
//    }
//
//    override fun onBackPressed() {
//        supportFinishAfterTransition()
//        super.onBackPressed()
//    }
}