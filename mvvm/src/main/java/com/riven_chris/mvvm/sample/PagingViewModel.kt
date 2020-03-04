package com.riven_chris.mvvm.sample

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.riven_chris.mvvm.model.A

class PagingViewModel(app: Application) : AndroidViewModel(app) {
    val items = MutableLiveData<List<A>>()

    init {

    }
}