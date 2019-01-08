package com.code.knab.knabofon.ui.transmition

import android.bluetooth.BluetoothDevice
import android.content.Context
import android.opengl.Visibility
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.code.knab.knabofon.R
import com.code.knab.knabofon.ui.transmition.dagger.TransmitionInjector
import com.code.knab.knabofon.ui.transmition.mvp.TransmitionMVP
import kotlinx.android.synthetic.main.activity_transmition.*
import javax.inject.Inject

class TransmitionActivity : AppCompatActivity(), TransmitionMVP.View {

    @Inject
    lateinit var presenter: TransmitionMVP.Presenter

    private lateinit var vibrator: Vibrator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transmition)

        TransmitionInjector().inject(this)

        val pairedDevice = intent.getParcelableExtra<BluetoothDevice>("device")

        init(pairedDevice)

        vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        thread.start()
    }

    private fun init(pairedDevice: BluetoothDevice) {
        presenter.initConnectionListener()
        presenter.connect(pairedDevice)
    }

    override fun onSuccessfulConnection() {
        progressBar.visibility = View.GONE
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
                presenter.startReading()
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }
}
