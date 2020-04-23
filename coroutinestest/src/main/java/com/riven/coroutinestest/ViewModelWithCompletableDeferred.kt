package com.riven.coroutinestest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ViewModelWithCompletableDeferred : ViewModel() {
    val deferredData = CompletableDeferred<String>()

    init {
        viewModelScope.launch {
            val data = loadFromRepo()
            deferredData.complete(data)
        }
    }

    suspend fun loadData(): String = deferredData.await()

    private suspend fun loadFromRepo(): String {
        delay(3000)
        return "data"
    }
}