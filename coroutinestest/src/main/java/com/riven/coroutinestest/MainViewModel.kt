package com.riven.coroutinestest

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

class MainViewModel : ViewModel() {

    val loading = MutableLiveData(false)
    val uiData = MutableLiveData("")

    fun loadData() {
        viewModelScope.launch {
            showSomeData()
            Log.d("CoroutineTest", "data showed")
        }
    }

    suspend fun showSomeData() = coroutineScope {
        Log.d("CoroutineTest", "outer thread:${Thread.currentThread().name}")
        loading.value = true
        val data = async(Dispatchers.IO) { // <- extension on current scope
            delay(3000)
            return@async "some data loaded"
        }

        withContext(Dispatchers.Main) {
            val result = data.await()
            loading.value = false
            uiData.value = result
        }
    }
}