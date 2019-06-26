package com.riven_chris.mvvm.sample

import android.database.DatabaseUtils
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.riven_chris.mvvm.R
import com.riven_chris.mvvm.databinding.ActivityIncludeBinding
import com.riven_chris.mvvm.model.UserInfo

class IncludeActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityIncludeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_include)

        val userInfo = UserInfo()
        userInfo.name = "include"

        mBinding.user = userInfo

        //not support merge element

    }
}
