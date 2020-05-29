package com.riven_chirs.example.workmanager

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay
import java.io.File
import java.io.FileOutputStream
import java.io.FileWriter

const val BLUR_WORKER_TAG = "blur_worker_tag"

class BlurWorker(val context: Context, parameters: WorkerParameters) :
    CoroutineWorker(context, parameters) {
    override suspend fun doWork(): Result {

        val url = inputData.getString("URL")
        Log.d(BLUR_WORKER_TAG, "blur url: $url")
        val path = context.externalCacheDir?.absolutePath + "/data/"
        val file = File(path)
        if (!file.exists())
            file.mkdirs()
        val textFile = File(path, "data.txt")
        if (!file.exists())
            textFile.createNewFile()

        val fis = FileWriter(textFile)
        try {
            Log.d(BLUR_WORKER_TAG, "waiting for blur")
            var i = 0
            while (i < 100) {
                delay(1000)
                i++
                Log.d(BLUR_WORKER_TAG, "emit a value $i")
                val data = i.toString()
                if (i % 5 == 0) {
                    println()
                }
                fis.write(data, 0, data.length)
                fis.flush()
            }
            Log.d(BLUR_WORKER_TAG, "blured completed")

        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            fis.close()
        }

        val outputData = Data.Builder().putString("URL", "blured url: blured-${url}").build()

        return Result.success(outputData)
    }
}