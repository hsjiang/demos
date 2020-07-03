package com.riven.coroutinestest

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.suspendCancellableCoroutine
import java.io.IOException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class FlowViewModel : ViewModel() {

//    val stringFlow: LiveData<String> = liveData {
//        emit("letters")
//        fetchStringFlow().collect {
//            emit(it)
//        }
//    }

    val stringFlow: LiveData<String> = fetchStringFlow()
        .onStart {
            emit("letters")
        }.asLiveData()

    fun fetchStringFlow(): Flow<String> {
        return flow {
            delay(1000)
            emit("a")
            emit("b")
            emit("c")
            emit("d")
        }
    }

    fun callbackFlow() = callbackFlow {
        send("hahah")
        offer("hahah")
        close(IOException())

        awaitClose {  }
    }

    suspend fun oneshot(params: String): String = suspendCancellableCoroutine {cont->
        cont.resume("success")
        cont.resumeWithException(IOException())
    }
}