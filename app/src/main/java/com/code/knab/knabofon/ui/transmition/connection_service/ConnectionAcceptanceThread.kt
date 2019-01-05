package com.code.knab.knabofon.ui.transmition.connection_service

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothServerSocket
import android.bluetooth.BluetoothSocket
import android.util.Log

import java.io.IOException
import java.util.UUID

/**
 * Created by hp on 2017-09-16.
 */

internal class ConnectionAcceptanceThread(bluetoothAdapter: BluetoothAdapter) : Thread() {
    private val serverSocket: BluetoothServerSocket?

    init {
        var tmpServerSocket: BluetoothServerSocket? = null

        try {
            tmpServerSocket = bluetoothAdapter
                    .listenUsingInsecureRfcommWithServiceRecord(APP_NAME, MY_UUID_INSECURE)
            Log.d(TAG, "AcceptThread: Setting up Server using: $MY_UUID_INSECURE")
        } catch (e: IOException) {
            Log.e(TAG, "AcceptThread: IOException: " + e.message)
        }

        serverSocket = tmpServerSocket
    }

    override fun run() {
        Log.d(TAG, "run: AcceptThread Running.")
        var bluetoothSocket: BluetoothSocket? = null

        try {
            Log.d(TAG, "run: RFCOM server socket start.....")
            bluetoothSocket = serverSocket!!.accept()

            Log.d(TAG, "run: RFCOM server socket accepted connection.")
        } catch (e: IOException) {
            Log.e(TAG, "AcceptThread: IOException: " + e.message)
        }

        if (bluetoothSocket != null) {
            devicesConnected(bluetoothSocket)
        }
        Log.i(TAG, "END mAcceptThread ")
    }

    fun cancel() {
        Log.d(TAG, "cancel: Canceling AcceptThread.")
        try {
            serverSocket!!.close()
        } catch (e: IOException) {
            Log.e(TAG, "cancel: Close of AcceptThread ServerSocket failed. " + e.message)
        }

    }

    fun devicesConnected(bluetoothSocket: BluetoothSocket) {
        val transmitionThread = TransmitionThread(bluetoothSocket)
        transmitionThread.start()
    }

    companion object {
        private val TAG = "Acceptance thread"

        private val APP_NAME = "Smaug"
        private val MY_UUID_INSECURE = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb")
    }
}
