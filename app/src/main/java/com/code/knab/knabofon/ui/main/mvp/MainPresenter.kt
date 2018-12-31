package com.code.knab.knabofon.ui.main.mvp

import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.code.knab.knabofon.utils.rx.RxUtils

class MainPresenter(private val view: MainMVP.View,
                    private val model: MainMVP.Model,
                    private val rxUtils: RxUtils) : MainMVP.Presenter {

    private val TAG = "BTPresenter"

    override fun btEnableDisable() {
        if (!model.bluetoothEnableDisable()) {
            view.enableBT()
        } else {
            view.disableBT()
        }
    }

    override fun btEnableDiscovering() {
        model.checkDiscoveringState()

        view.discoverDevices()
    }

    override fun onDestroy() {
        model.cancelDiscovering()
    }

    override var discoveredDevicesReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val action = intent.action

            Log.d(TAG, "Action found")

            if (action == BluetoothDevice.ACTION_FOUND) {

                val device = intent.getParcelableExtra<BluetoothDevice>(BluetoothDevice.EXTRA_DEVICE)
                view.listDevices(context, device)
            }
        }
    }

    override var bondingDevicesReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val action = intent.action

            Log.d(TAG, "Action found")

            if (action == BluetoothDevice.ACTION_BOND_STATE_CHANGED) {
                val device = intent.getParcelableExtra<BluetoothDevice>(BluetoothDevice.EXTRA_DEVICE)
                val deviceName = device.name
                when (device.bondState) {
                    BluetoothDevice.BOND_BONDED -> {
                        Log.d(TAG, "You are connected with $deviceName")
                        view.startNextActivity(device)
                    }
                    BluetoothDevice.BOND_BONDING -> Log.d(TAG, "connecting...")
                    BluetoothDevice.BOND_NONE -> Log.d(TAG, "error, no connection")
                }
            }
        }
    }


}