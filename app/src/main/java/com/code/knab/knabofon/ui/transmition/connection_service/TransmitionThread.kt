package com.code.knab.knabofon.ui.transmition.connection_service

import android.bluetooth.BluetoothSocket
import android.util.Log

import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.nio.charset.Charset

import io.reactivex.Observable

/**
 * Created by hp on 2017-09-16.
 */

internal class TransmitionThread(private val bluetoothSocket: BluetoothSocket?) : Thread() {

    private val TAG = "Transmition thread"

    private val inputStream: InputStream?
    private val outputStream: OutputStream?

    var messageObservable: Observable<String>? = null
        private set

    private var string = ""

    init {
        Log.d(TAG, "ConnectedThread: Starting.")

        var tmpInputStream: InputStream? = null
        var tmpOutputStream: OutputStream? = null

        try {
            tmpInputStream = this.bluetoothSocket?.inputStream
            tmpOutputStream = this.bluetoothSocket?.outputStream
        } catch (e: IOException) {
            e.printStackTrace()
        }

        this.inputStream = tmpInputStream
        this.outputStream = tmpOutputStream

    }

    override fun run() {
        val buffer = ByteArray(13)
        var bytes: Int
        this.messageObservable = Observable.just("")
        while (true) {
            try {
                while (!string.contains("#")) {
                    bytes = this.inputStream!!.read(buffer)
                    val incomingMessage = String(buffer, 0, bytes)
                    string = string + incomingMessage
                }

                this.messageObservable = Observable.just(string)
                Log.d(TAG, "InputStream: $string")
                string = ""

            } catch (e: IOException) {
                Log.e(TAG, "write: Error reading Input Stream. " + e.message)
                break
            }

        }
    }

    fun cancel() {
        try {
            this.bluetoothSocket?.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    fun write(out: ByteArray) {
        val message = String(out, Charset.defaultCharset())
        Log.d(TAG, "write: Writing to outputstream: $message")
        try {
            outputStream!!.write(out)
        } catch (e: IOException) {
            Log.e(TAG, "write: Error writing to output stream. " + e.message)
        }

    }

}
