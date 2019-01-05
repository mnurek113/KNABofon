package com.code.knab.knabofon.ui.transmition.mvp

import android.bluetooth.BluetoothDevice

import java.nio.charset.Charset

import javax.inject.Inject

import io.reactivex.Observable
import knab.com.smaug.bluetooth.bluetooth_facade.BluetoothFacade
import knab.com.smaug.dagger.ActivityScope
import knab.com.smaug.transmition.connection_service.BluetoothConnectionService

/**
 * Created by hp on 2017-09-08.
 */
@ActivityScope
class TModel @Inject
constructor(private val bluetoothFacade: BluetoothFacade, private var bluetoothConnectionService: BluetoothConnectionService?) : TransmitionMVP.Model {


    override fun startConnectionService(): Boolean {

        bluetoothConnectionService!!.init(bluetoothFacade.getBluetoothAdapter())

        return false
    }

    override fun attemptConnection(pairedDevice: BluetoothDevice): Boolean {
        return if (bluetoothConnectionService!!.attemptConnection(pairedDevice)) true else false
    }

    override fun sendByConnectionService(message: String): Boolean {
        val out = message.toByteArray(Charset.defaultCharset())
        bluetoothConnectionService!!.write(out)
        return true
    }

    override fun readFromConnectionService(): Observable<String> {
        return bluetoothConnectionService!!.read()
    }

    override fun cancelConnectionService(): Boolean {
        bluetoothConnectionService!!.close()
        bluetoothConnectionService = null
        bluetoothFacade.getBluetoothAdapter().disable()
        return false
    }
}
