package com.riven.screencompatibility

import android.content.Context
import android.graphics.Point
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.DisplayMetrics
import android.util.Log
import android.view.WindowManager

class CompatibilityActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        printData()
    }

    private fun printData() {
        Log.d("screenInfo", "${DensityUtils.screenWidth(this)}X" +
                "${DensityUtils.screenHeight(this)}\n" +
                "density: ${resources.displayMetrics.density}\n" +
                "xdp:${DensityUtils.px2dp(this, DensityUtils.screenWidth(this).toFloat())}\n" +
                "ydp:${DensityUtils.px2dp(this, DensityUtils.screenHeight(this).toFloat())}\n" +
                "xdpi:${resources.displayMetrics.xdpi}\n" +
                "ydpi:${resources.displayMetrics.ydpi}\n" +
                "dpi:${resources.displayMetrics.densityDpi}\n")
    }

    override fun onStart() {
        super.onStart()
        DensityUtils.setImmersiveMode(window)
    }

    override fun onResume() {
        super.onResume()
        Handler().postDelayed({
            printData()
            val wm = getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val point = Point()
            wm.defaultDisplay.getRealSize(point)
            Log.d("screenInfo", "point: $point")

            val rect = Rect()
            wm.defaultDisplay.getRectSize(rect)
            Log.d("screenInfo", "rect: ${rect.toShortString()}")
        }, 1000)
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        DensityUtils.setImmersiveMode(window)
    }
}
