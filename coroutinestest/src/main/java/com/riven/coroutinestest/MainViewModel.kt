package com.riven.coroutinestest

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.*

/**
 * https://developer.android.com/topic/libraries/architecture/coroutines
 * https://medium.com/androiddevelopers/coroutines-on-android-part-iii-real-work-2ba8a2ec2f45
 */

class MainViewModel : ViewModel() {

    val loading = MutableLiveData(false)
    val uiData = MutableLiveData("")

    //当 LiveData 变为活动状态时，代码块开始执行；当 LiveData 变为非活动状态时，
    // 代码块会在可配置的超时过后自动取消。如果代码块在完成前取消，
    // 则会在 LiveData 再次变为活动状态后重启；如果在上次运行中成功完成，则不会重启。
    // 请注意，代码块只有在自动取消的情况下才会重启。如果代码块由于任何其他原因（例如，抛出 CancelationException）而取消，则不会重启
    val liveShowSomeData = liveData(timeoutInMs = 3000L) {
        val data = loadFromDatabase()
        emit(data)
    }

    val submitData = Transformations.switchMap(liveShowSomeData) { data ->
        liveData {
            val disposable = emitSource(loadLiveDataFromDatabase(data))
        }
    }

    fun loadData() {
        viewModelScope.launch {
            showSomeData()
            Log.d("CoroutineTest", "data showed")
        }
    }

    suspend fun loadFromDatabase(): String {
        delay(3000)
        return "data from database"
    }

    suspend fun loadLiveDataFromDatabase(data: String): LiveData<String> {
        delay(3000)
        return MutableLiveData("$data data from database")
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