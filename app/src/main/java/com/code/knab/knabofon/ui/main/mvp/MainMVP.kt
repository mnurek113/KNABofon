package com.code.knab.knabofon.ui.main.mvp

import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context

interface MainMVP {

    interface View {
        fun enableBT()
        fun disableBT()
        fun error()
        fun discoverDevices()
        fun listDevices(context: Context, device: BluetoothDevice)
        fun startNextActivity(device: BluetoothDevice)
    }

    interface Presenter {
        fun btEnableDisable()
        fun btEnableDiscovering()
        fun onDestroy()

        var discoveredDevicesReceiver: BroadcastReceiver
        var bondingDevicesReceiver: BroadcastReceiver
    }

    interface Model {
        fun bluetoothEnableDisable(): Boolean
        fun checkDiscoveringState()
        fun cancelDiscovering()
    }
}