package com.riven_chris.windows

import android.app.Application
import android.content.Context
import android.util.Log
import android.view.WindowManager

class MyApplication : Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        val windowManger = base?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        Log.d("windowTest", "MyApplication attachBaseContext: $windowManger")
    }

    override fun onCreate() {
        super.onCreate()
    }
}