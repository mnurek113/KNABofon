package com.code.knab.knabofon.ui.main

import android.bluetooth.BluetoothDevice
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.code.knab.knabofon.R
import com.code.knab.knabofon.ui.main.dagger.MainInjector
import com.code.knab.knabofon.ui.main.mvp.MainMVP
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainMVP.View {

    @Inject
    lateinit var presenter: MainMVP.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MainInjector().inject(this)
    }

    override fun enableBT() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun disableBT() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun error() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun discoverDevices() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun listDevices(context: Context, device: BluetoothDevice) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun startNextActivity(device: BluetoothDevice) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
