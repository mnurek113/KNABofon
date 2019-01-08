package com.code.knab.knabofon.ui.transmition

import android.content.Context
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import com.code.knab.knabofon.R
import com.code.knab.knabofon.ui.transmition.mvp.TransmitionMVP
import com.ebanx.swipebtn.SwipeButton
import kotlinx.android.synthetic.main.activity_transmition.*

class TransmitionActivity : AppCompatActivity(), TransmitionMVP.View {

    private lateinit var vibrator: Vibrator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transmition)

        vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

    }

    override fun onSuccessfulConnection() {

    }

    override fun showMessage(message: String) {

        val vibrationTimings: LongArray = longArrayOf(1000, 2000)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createWaveform(vibrationTimings, 0))
        }

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            vibrator.vibrate(vibrationTimings, 0)
        }

    }

    override fun handleError(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    var thread: Thread = object : Thread() {
        override fun run() {
            try {
                Thread.sleep(2000) // As I am using LENGTH_LONG in Toast

            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }
}
