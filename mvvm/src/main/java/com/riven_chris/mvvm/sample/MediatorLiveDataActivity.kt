package com.riven_chris.mvvm.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.riven_chris.mvvm.R
import com.riven_chris.mvvm.viewModelProvider
import kotlinx.android.synthetic.main.activity_mediator_live_data.*
import java.util.*

class MediatorLiveDataActivity : AppCompatActivity() {
    private lateinit var mViewModel: MediatorViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mediator_live_data)
        mViewModel = viewModelProvider()

        mViewModel.mediatorData.observe(this, androidx.lifecycle.Observer {
            tvMediator.text = it
        })

        mViewModel.mapA.observe(this, androidx.lifecycle.Observer {
            tvMapA.text = it
        })

        mViewModel.mapB.observe(this, androidx.lifecycle.Observer {
            tvMapB.text = it
        })

        mViewModel.switchMapA.observe(this, androidx.lifecycle.Observer {
            tvSwitchMapA.text = it
        })
    }

    fun clickA(view: View) {
        val text = Random().nextInt(100).toString()
        mViewModel.setTextA(text)
        tvTextA.text = text
    }

    fun clickB(view: View) {
        val text = Random().nextInt(100).toString()
        mViewModel.setTextB(text)
        tvTextB.text = text
    }
}
