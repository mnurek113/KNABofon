package com.code.knab.knabofon.ui.transmition.connection_service

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice

import java.util.concurrent.TimeUnit

import io.reactivex.Observable

class BluetoothConnectionService {

    private var bluetoothAdapter: BluetoothAdapter? = null
    private var acceptanceThread: ConnectionAcceptanceThread? = null
    private var attemptThread: ConnectionAttemptThread? = null
    private var transmitionThread: TransmitionThread? = null

    fun init(bluetoothAdapter: BluetoothAdapter) {
        this.bluetoothAdapter = bluetoothAdapter
        start()
    }

    @Synchronized
    private fun start() {

        if (attemptThread != null) {
            attemptThread!!.cancel()
            attemptThread = null
        }

        if (acceptanceThread == null) {
            acceptanceThread = ConnectionAcceptanceThread(bluetoothAdapter!!)
            acceptanceThread!!.start()
        }
    }

    fun attemptConnection(pairedDevice: BluetoothDevice): Boolean {
        attemptThread = ConnectionAttemptThread(pairedDevice)
        attemptThread!!.start()
        waitForConnection()
        return true
    }

    private fun waitForConnection() {
        while (this.transmitionThread == null) {
            this.transmitionThread = attemptThread!!.transmitionThread
        }
    }

    fun write(out: ByteArray) {
        this.transmitionThread!!.write(out)
    }

    fun read(): Observable<String> {
        return Observable.interval(0, 2, TimeUnit.SECONDS)
                .flatMap { mapper -> transmitionThread!!.getMessageObservable() }
    }

    fun close(): Boolean {
        acceptanceThread!!.cancel()
        attemptThread!!.cancel()
        transmitionThread!!.cancel()
        return true
    }
}