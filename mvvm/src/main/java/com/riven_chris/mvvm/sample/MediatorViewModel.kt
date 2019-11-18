package com.riven_chris.mvvm.sample

import androidx.arch.core.util.Function
import androidx.lifecycle.*
import com.riven_chris.mvvm.model.A
import com.riven_chris.mvvm.model.B

class MediatorViewModel : ViewModel() {
    val dataA = MutableLiveData<A>()
    val dataB = MutableLiveData<B>()

    val mapA: LiveData<String> = Transformations.map(dataA) { "map A:" + it.text }
    val mapB: LiveData<String> = Transformations.map(dataB) { "map B: " + it.text }

    val switchMapA: LiveData<String> = Transformations.switchMap(dataA) { input ->
        val result = MutableLiveData<String>()
        result.value = "switchMap A: " + input?.text
        result
    }

    val mediatorData = MediatorLiveData<String>()

    init {
        mediatorData.addSource(dataA, object : Observer<A> {
            var count = 0
            override fun onChanged(t: A) {
                mediatorData.value = t.text
                count++
                if (count >= 5) {
                    mediatorData.removeSource(dataA)
                }
            }
        })

        mediatorData.addSource(dataB) {
            mediatorData.value = it.text
        }
    }

    fun setTextA(a: String) {
        dataA.value = A(a)
    }

    fun setTextB(b: String) {
        dataB.value = B(b)
    }

}