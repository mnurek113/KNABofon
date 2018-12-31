package com.code.knab.knabofon.ui.main.mvp

import com.code.knab.knabofon.bluetooth.BluetoothFacade

class MainModel(private val bluetoothFacade: BluetoothFacade) : MainMVP.Model {
    override fun bluetoothEnableDisable(): Boolean {
        if (bluetoothFacade.bluetoothAdapter.isEnabled()) {
            disableBluetoothAdapter()
            return true
        } else {
            return false
        }
    }

    override fun checkDiscoveringState() {
        if (bluetoothFacade.bluetoothAdapter.isDiscovering()) {
            bluetoothFacade.bluetoothAdapter.cancelDiscovery()
            bluetoothFacade.bluetoothAdapter.startDiscovery()
        } else {
            bluetoothFacade.bluetoothAdapter.startDiscovery()
        }
    }

    override fun cancelDiscovering() {
        bluetoothFacade.bluetoothAdapter.cancelDiscovery()
    }

    private fun disableBluetoothAdapter() {
        bluetoothFacade.bluetoothAdapter.disable()
    }
}