package com.code.knab.knabofon.ui.transmition.mvp

import android.bluetooth.BluetoothDevice

import io.reactivex.Observable

/**
 * Created by hp on 2017-09-08.
 */

interface TransmitionMVP {

    interface View {
        fun onStartReading()
        fun showMessage(message: String)
        fun handleError(message: String)
    }

    interface Presenter {
        fun initConnectionListener()
        fun connect(pairedDevice: BluetoothDevice)
        fun sendMessage(message: String)
        fun startReading()
        fun closeConnection()
    }

    interface Model {
        fun startConnectionService(): Boolean
        fun attemptConnection(pairedDevice: BluetoothDevice): Boolean
        fun sendByConnectionService(message: String): Boolean
        fun readFromConnectionService(): Observable<String>
        fun cancelConnectionService(): Boolean
    }
}
