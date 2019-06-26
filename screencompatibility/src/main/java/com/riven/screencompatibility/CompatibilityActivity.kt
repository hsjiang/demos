package com.riven.screencompatibility

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class CompatibilityActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("screenInfo", "${DensityUtils.screenWidth(this)}X" +
                "${DensityUtils.screenHeight(this)}\n" +
                "density: ${resources.displayMetrics.density}\n" +
                "xdp:${DensityUtils.px2dp(this, DensityUtils.screenWidth(this).toFloat())}\n" +
                "ydp:${DensityUtils.px2dp(this, DensityUtils.screenHeight(this).toFloat())}\n" +
                "xdpi:${resources.displayMetrics.xdpi}\n" +
                "ydpi:${resources.displayMetrics.ydpi}\n" +
                "dpi:${resources.displayMetrics.densityDpi}\n")
    }
}
