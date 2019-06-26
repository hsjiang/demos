package com.riven_chris.mvvm.sample

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.riven_chris.mvvm.R
import com.riven_chris.mvvm.databinding.ActivityEventHandlingBinding

class EventHandlingActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityEventHandlingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_event_handling)
        mBinding.lifecycleOwner = this
        mBinding.handlers = Handlers(this)
    }

    class Handlers(private val mContext: Context) {
        fun onClick1(view: View) {
            Toast.makeText(mContext, "onClick1", Toast.LENGTH_SHORT).show()
        }

        fun onClick2() {
            Toast.makeText(mContext, "onClick2", Toast.LENGTH_SHORT).show()
        }

        fun onClick3(string: String) {
            Toast.makeText(mContext, string, Toast.LENGTH_SHORT).show()
        }

        fun onLongClick1(): Boolean {
            Toast.makeText(mContext, "onLongClick1", Toast.LENGTH_SHORT).show()
            return true
        }
    }
}
