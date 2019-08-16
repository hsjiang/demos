package com.riven_chris.windows

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager

class SecondActivity : AppCompatActivity() {

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
        val windowManger = newBase?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        Log.d("windowTest", "SecondActivity attachBaseContext: ${windowManger}")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
    }
}
