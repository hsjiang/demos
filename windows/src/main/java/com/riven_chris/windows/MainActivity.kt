package com.riven_chris.windows

import android.app.Dialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.ViewManager
import android.view.WindowManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
        val windowManger = newBase?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        Log.d("windowTest", "MainActivity attachBaseContext: ${windowManger}")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("windowTest", "decorView: ${window.peekDecorView()}")
        super.onCreate(savedInstanceState)
        Log.d("windowTest", "decorView: ${window.peekDecorView()}")
        setContentView(R.layout.activity_main)
        Log.d("windowTest", "decorView: ${window.peekDecorView()}")


        tv_second.setOnClickListener{
            startActivity(Intent(this, SecondActivity::class.java))
        }
    }

    fun test() {
        window
        window.setWindowManager(null, null, null)
        val windowManager: WindowManager = windowManager
        window.decorView
        val viewManager: ViewManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val view: View
        val dialog: Dialog
        val toast: Toast
        val viewGroup: ViewGroup

        setVisible(true)
    }


    override fun onContentChanged() {
        super.onContentChanged()
    }
}
