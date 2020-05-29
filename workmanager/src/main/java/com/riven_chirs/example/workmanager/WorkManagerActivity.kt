package com.riven_chirs.example.workmanager

import android.app.NotificationManager
import android.app.job.JobScheduler
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.work.*
import androidx.work.impl.WorkManagerInitializer
import kotlinx.android.synthetic.main.activity_work_manager.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit

const val URL = "url"
const val WORK_TAG = "test_work"
const val LOG_TAG = "log_tag"
const val PROGRESS = "progress"

class WorkManagerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_manager)

        clear.setOnClickListener {
            WorkManager.getInstance(this).cancelAllWorkByTag(WORK_TAG)
        }

        btnAddWork.setOnClickListener {
            setupOneTimeWorkRequest()
        }

        btnStartCoroutineWorker.setOnClickListener {
            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED).build()
            WorkManager.getInstance(this).enqueueUniquePeriodicWork(
                "Blur Work",
                ExistingPeriodicWorkPolicy.KEEP,
                PeriodicWorkRequest
                    .Builder(BlurWorker::class.java, 20, TimeUnit.MINUTES)
//                    .setConstraints(constraints)
                    .setInputData(workDataOf("URL" to "imageUrl:xxxxxxxxxx"))
                    .addTag(WORK_TAG)
                    .build()
            )
        }

        WorkManager.getInstance(this).getWorkInfosByTagLiveData(WORK_TAG)
            .observe(this, Observer {
                it.forEach {
                    Log.d(LOG_TAG, "work state: " + it.state.name)
                    val progress = it.progress.getInt(PROGRESS, -1)
                    Log.d(LOG_TAG, "work progress: $progress")

//                        val url = it.progress.getString(URL)
//                        Log.d(LOG_TAG, "work url: $url")
                }
            })

        Log.d(LOG_TAG, "main Thread: " + Thread.currentThread())
    }

    private fun setupOneTimeWorkRequest() {
        val url = "ssssssssssss"

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
//                .setRequiresDeviceIdle(true)
            .setRequiresCharging(true)
            .build()
        val testWork = OneTimeWorkRequestBuilder<TestWorker>()
            .setConstraints(constraints)
            .setInitialDelay(3, TimeUnit.SECONDS)
//                .setBackoffCriteria(BackoffPolicy.LINEAR, OneTimeWorkRequest.MIN_BACKOFF_MILLIS,
//                        TimeUnit.MILLISECONDS)
            .setInputData(workDataOf(URL to url))//Data 对象的大小上限为 10KB
            .addTag(WORK_TAG)
            .build()
        WorkManager.getInstance(this)
            .beginUniqueWork("testWork", ExistingWorkPolicy.REPLACE, testWork).enqueue()
    }

    private fun setupPeriodicWorkRequest() {
        val constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .build()
        val testWork = PeriodicWorkRequestBuilder<TestWorker>(1, TimeUnit.HOURS)
            .setConstraints(constraints)
            .setInputData(workDataOf(URL to "www.baidu.com"))
            .addTag(WORK_TAG)
            .build()
        WorkManager.getInstance(this).enqueue(testWork)
    }

    /**
     * OverwritingInputMerger 会尝试将所有输入中的所有键添加到输出中。如果发生冲突，它会覆盖先前设置的键
     * ArrayCreatingInputMerger 会尝试合并输入，并在必要时创建数组
     */
    private fun setWorkContinuation() {

        WorkManager.getInstance(this)
            .beginWith(
                listOf(
                    OneTimeWorkRequestBuilder<TestWorker>().build(),
                    OneTimeWorkRequestBuilder<TestWorker>().build()
                )
            )
            .then(
                OneTimeWorkRequestBuilder<TestWorker>()
                    .setInputMerger(ArrayCreatingInputMerger::class.java)
                    .build()
            )
            .enqueue()
    }


    class TestWorker(context: Context, workerParams: WorkerParameters) :
        Worker(context, workerParams) {
        override fun doWork(): Result {
            Log.d(LOG_TAG, "start TestWorker: " + Thread.currentThread())
            val url = inputData.getString(URL)
            setProgressAsync(workDataOf(PROGRESS to 0))
            Thread.sleep(3000)
            setProgressAsync(workDataOf(PROGRESS to 50))
            Thread.sleep(3000)
            setProgressAsync(workDataOf(PROGRESS to 100))
//            delay(3000)
            val outputExtraData = workDataOf(URL to url)
            Log.d(LOG_TAG, "success: hahaha $url")
            return Result.success(outputExtraData)
        }
    }

    class SleepWorker(context: Context, parameters: WorkerParameters) :
        CoroutineWorker(context, parameters) {

        override suspend fun doWork(): Result {
            Log.d(LOG_TAG, "start SleepWorker: " + Thread.currentThread())
            var i = 0
            while (i < 4) {
                delay(1000) // milliseconds
                Log.d(LOG_TAG, "i: $i")
                i++
            }
            return Result.success()
        }
    }
}
