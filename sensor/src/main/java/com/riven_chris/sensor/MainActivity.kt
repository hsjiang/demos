package com.riven_chris.sensor

import android.app.Service
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlin.math.abs

class MainActivity : AppCompatActivity() {

    var sensorManager: SensorManager? = null
    var sensor: Sensor? = null
    var listener: SensorEventListener = object : SensorEventListener {
        val min = 10

        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

        }

        override fun onSensorChanged(event: SensorEvent?) {
            Log.d("sensor", "${event?.sensor}\n ${event?.accuracy}\n " +
                    "x:${event?.values?.get(0)},y:${event?.values?.get(1)},z:${event?.values?.get(2)}")
            val x = event?.values?.get(0)
            val y = event?.values?.get(1)
            x?.let {
                val xxx = -it * 100
                if (abs(iv.translationX - xxx) > min)
                    iv.translationX = xxx
            }
            y?.let {
                val yyy = it * 100
                if (abs(iv.translationY - yyy) > min) {
                    iv.translationY = yyy
                }
            }
            tv.text = "$x,$y"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        sensorManager = getSystemService(Service.SENSOR_SERVICE) as? SensorManager?

        sensor = sensorManager?.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        sensorManager?.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_UI)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        sensorManager?.unregisterListener(listener)
    }
}
