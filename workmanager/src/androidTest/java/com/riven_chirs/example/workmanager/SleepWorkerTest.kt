package com.riven_chirs.example.workmanager

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.work.ListenableWorker
import androidx.work.testing.TestListenableWorkerBuilder
import androidx.work.testing.TestWorkerBuilder
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.Executor
import java.util.concurrent.Executors

@RunWith(AndroidJUnit4::class)
class SleepWorkerTest {
    private lateinit var context: Context
    private lateinit var executor: Executor

    @Before
    fun setup() {
        context = ApplicationProvider.getApplicationContext()
        executor = Executors.newSingleThreadExecutor()
    }

    @Test
    fun testSleepWorker() {
        val worker = TestListenableWorkerBuilder<WorkManagerActivity.SleepWorker>(context).build()
        runBlocking {
            val result = worker.doWork()
            assert(result == ListenableWorker.Result.success())
        }
    }

    @Test
    fun testWorker() {
        val worker = TestWorkerBuilder<WorkManagerActivity.TestWorker>(
                context,
                executor
        ).build()
        runBlocking {
            val result = worker.doWork()
            assert(result == ListenableWorker.Result.success())
        }
    }
}