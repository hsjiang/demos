package com.riven.coroutinestest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_coroutines.*
import kotlinx.android.synthetic.main.activity_coroutines.view.*
import kotlinx.coroutines.*

class CoroutinesActivity : AppCompatActivity() {
    private lateinit var mViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutines)
        mViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        start.setOnClickListener {
//            myGlobalLaunch()
//            myRunBlocking()
//            myCoroutineScope()
            mViewModel.loadData()
        }

        mViewModel.loading.observe(this, Observer {
            progressBar.visibility = if (it) View.VISIBLE else View.GONE
        })

        mViewModel.uiData.observe(this, Observer {
            tvResult.text = it
        })
    }

    private fun myGlobalLaunch() {
        println("1-currentThread: ${Thread.currentThread()}")
        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                println("2-currentThread: ${Thread.currentThread()}")
                delay(3000)
                println("3-currentThread: ${Thread.currentThread()}")
            }
            println("4-currentThread: ${Thread.currentThread()}")
            println("finish")
        }
        println("coroutines launched")
    }

    private fun myRunBlocking() {
        println("1-currentThread: ${Thread.currentThread()}")
        runBlocking {
            val jobLaunch = launch {
                delay(2000)
                println("launch finish")
            }
            val job = async {
                println("2-currentThread: ${Thread.currentThread()}")
                delay(3000)
                println("3-currentThread: ${Thread.currentThread()}")
            }
            job.await()
            println("4-currentThread: ${Thread.currentThread()}${jobLaunch.join()}")
            println("finish")
        }
        println("coroutines launched")
    }

    private fun myCoroutineScope() {
        runBlocking {

            launch {
                delay(200L)
                println("Task from runBlocking")
            }

            coroutineScope { // 创建一个协程作用域
                launch {
                    delay(500L)
                    println("Task from nested launch")
                }

                delay(100L)
                println("Task from coroutine scope") // 这一行会在内嵌 launch 之前输出
            }

            println("Coroutine scope is over") // 这一行在内嵌 launch 执行完毕后才输出
        }

        println("runBlocking is over")
    }
}
