package com.code.knab.knabofon.ui.transmition.connection_service

import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.util.Log

import java.io.IOException
import java.util.UUID

/**
 * Created by hp on 2017-09-16.
 */

internal class ConnectionAttemptThread(private val pairedDevice: BluetoothDevice) : Thread() {
    private var bluetoothSocket: BluetoothSocket? = null
    var transmitionThread: TransmitionThread? = null
        private set

    init {
        Log.d(TAG, "ConnectThread: started.")
    }

    override fun run() {
        Log.i(TAG, "RUN connectThread ")
        var tmpBluetoothSocket: BluetoothSocket? = null

        try {
            Log.d(TAG, "ConnectThread: Trying to create InsecureRfcommSocket using UUID: $MY_UUID_INSECURE")
            tmpBluetoothSocket = pairedDevice.createInsecureRfcommSocketToServiceRecord(MY_UUID_INSECURE)
        } catch (e: IOException) {
            Log.e(TAG, "ConnectThread: Could not create InsecureRfcommSocket " + e.message)
        }

        bluetoothSocket = tmpBluetoothSocket

        try {
            bluetoothSocket!!.connect()
            Log.d(TAG, "run: ConnectThread connected.")
        } catch (e: IOException) {
            try {
                bluetoothSocket!!.close()
                Log.d(TAG, "run: Closed Socket.")
            } catch (e1: IOException) {
                Log.e(TAG, "connectThread: run: Unable to close connection in socket " + e1.message)
            }

            Log.d(TAG, "run: ConnectThread: Could not connect to UUID: $MY_UUID_INSECURE")
        }

        devicesConnected(bluetoothSocket)
    }

    fun cancel() {
        try {
            bluetoothSocket!!.close()
            Log.d(TAG, "cancel: Closing Client Socket.")
        } catch (e: IOException) {
            Log.e(TAG, "cancel: close() of mmSocket in Connectthread failed. " + e.message)
        }

    }

    fun devicesConnected(bluetoothSocket: BluetoothSocket?) {
        this.transmitionThread = TransmitionThread(bluetoothSocket)
        transmitionThread!!.start()
    }

    companion object {
        private val TAG = "Attempt thread"

        private val MY_UUID_INSECURE = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb")
    }
}
