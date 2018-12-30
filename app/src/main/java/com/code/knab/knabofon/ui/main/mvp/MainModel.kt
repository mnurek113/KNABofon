package com.code.knab.knabofon.ui.main.mvp

import com.code.knab.knabofon.bluetooth.BluetoothFacade

class MainModel(private val bluetoothFacade: BluetoothFacade) : MainMVP.Model {
    override fun bluetoothEnableDisable(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun checkDiscoveringState() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun cancelDiscovering() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}