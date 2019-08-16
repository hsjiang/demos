package com.riven_chris.windows

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
        val windowManger = newBase?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        Log.d("windowTest", "SecondActivity attachBaseContext: ${windowManger}")
    }

    @SuppressLint("ShowToast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        Log.d("windowTest", "SecondActivity windowManager: ${window.windowManager}")

        btnShowToast.setOnClickListener {
            Thread({
                Looper.prepare()
                Toast.makeText(this@SecondActivity, "show", Toast.LENGTH_SHORT).show()
                Looper.loop()
            }).start()
        }
    }
}
