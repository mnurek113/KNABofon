package com.code.knab.knabofon.ui.transmition.mvp

import android.bluetooth.BluetoothDevice
import com.code.knab.knabofon.bluetooth.BluetoothFacade
import com.code.knab.knabofon.ui.transmition.connection_service.BluetoothConnectionService
import io.reactivex.Observable
import java.nio.charset.Charset

/**
 * Created by hp on 2017-09-08.
 */

class TModel (private val bluetoothFacade: BluetoothFacade,
              private var bluetoothConnectionService: BluetoothConnectionService?) : TransmitionMVP.Model {


    override fun startConnectionService(): Boolean {

        bluetoothConnectionService!!.init(bluetoothFacade.bluetoothAdapter)

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
        bluetoothFacade.bluetoothAdapter.disable()
        return false
    }
}
