package com.riven_chirs.example.workmanager

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class SleepWorkerTest {
    private lateinit var context: Context

    @Before
    fun setup() {
        context = ApplicationProvider.getApplicationContext()
    }

    @Test
    fun testSleepWorker() {
//        val worker = TestListenableWorkerBuilder<WorkManagerActivity.SleepWorker>(context).build()
//
//        val worker = TestWorkerBuilder<WorkManagerActivity.SleepWorker>(
//                context = context,
//                executor = executor,
//                inputData = workDataOf("SLEEP_DURATION" to 10000L)
//        ).build()
    }
}